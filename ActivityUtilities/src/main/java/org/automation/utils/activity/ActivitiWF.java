package org.automation.utils.activity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.activiti.engine.ActivitiOptimisticLockingException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;

public class ActivitiWF implements WF {

    private static final String STATE = "state";

    private static final String CANCELLATION_SIGNAL = "cancelProcess";
    
    private final ProcessEngine engine;

    public ActivitiWF(ProcessEngine engine) {
        this.engine = engine;
    }

    
    @Override
    public String start(String workflowType, Map<String, Object> parameters, String userId) {
        identity().setAuthenticatedUserId(userId);
        final String processInstanceId = runtime().startProcessInstanceByKey(workflowType, parameters).getProcessInstanceId();
        identity().setAuthenticatedUserId(null);
        return processInstanceId;
    }

    
    @Override
    public void complete(String taskId, String userId, String comment, Map<String, Object> parameters) {
        try {
            identity().setAuthenticatedUserId(userId);
            task().claim(taskId, userId);
            completeWithRetry(taskId, comment, parameters);
            identity().setAuthenticatedUserId(null);
        } catch (RuntimeException re) {
            task().setVariableLocal(taskId, STATE, "Failed");
            throw re;
        }
    }

    
    @Override
    public void complete(String processInstanceId, String taskName, String userId, String comment, Map<String, Object> parameters) {
        final String taskId = findTask(processInstanceId, taskName);
        complete(taskId, userId, comment, parameters);
    }

    
    @Override
    public void cancel(String processInstanceId, String userId, String comment) {
        identity().setAuthenticatedUserId(userId);
        task().addComment(null, processInstanceId, "Cancel", comment);
        cancel(processInstanceId, CANCELLATION_SIGNAL);
        identity().setAuthenticatedUserId(null);
    }

    
    @Override
    public void cancel(String processInstanceId, String signalId) {
        final ExecutionQuery executionQuery = runtime().createExecutionQuery().signalEventSubscriptionName(signalId)
                .processInstanceId(processInstanceId);

        for (Execution execution : executionQuery.list()) {
            runtime().signalEventReceived(signalId, execution.getId());
        }
    }

    
    @Override
    public Object processVariable(final String processInstanceId, final String variableName) {
        final HistoricVariableInstance variable = history().createHistoricVariableInstanceQuery().processInstanceId(processInstanceId)
                .variableName(variableName).singleResult();
        if (variable == null) {
            return null;
        }
        return variable.getValue();
    }

    
    @Override
    public Object processVariableByTaskId(String taskId, String variableName) {
        final HistoricTaskInstance taskInstance = history().createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        final HistoricVariableInstance variable = history().createHistoricVariableInstanceQuery()
                .processInstanceId(taskInstance.getProcessInstanceId()).variableName(variableName).singleResult();
        if (variable == null) {
            return null;
        }
        return variable.getValue();
    }

    
    @Override
    public String findTask(final String processInstanceId, final String taskName) {
        final Task task = task().createTaskQuery().processInstanceId(processInstanceId).taskName(taskName).singleResult();
        if (task == null) {
            return null;
        }
        return task.getId();
    }

    
    @Override
    public List<String> getActivities(final String processInstanceId) {
        final List<HistoricActivityInstance> list = history().createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime().asc().list();

        final List<String> activities = list.stream().map(e -> e.getActivityId()).collect(Collectors.toList());

        return activities;
    }

    
    @Override
    public void deleteProcess(final String processInstanceId, final String deleteReason) {
        runtime().deleteProcessInstance(processInstanceId, deleteReason);
    }

    
    @Override
    public void deleteHistoricProcess(final String processInstanceId) {
        history().deleteHistoricProcessInstance(processInstanceId);
    }

    
    @Override
    public String processDefinitionKey(final String processInstanceId) {
        final HistoricProcessInstance processInstance = history().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId)
                .singleResult();
        return repository().getProcessDefinition(processInstance.getProcessDefinitionId()).getKey();
    }
    
    
    @Override
    public String processDefinitionKeyByTaskId(final String taskId) {
        final HistoricTaskInstance task = history().createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        return repository().getProcessDefinition(task.getProcessDefinitionId()).getKey();
    }

    
    @Override
    public Set<String> candidateGroups(final String taskId) {
        final List<IdentityLink> identityLinks = task().getIdentityLinksForTask(taskId);
        final Set<String> candidateGroups = identityLinks.stream().filter(r -> IdentityLinkType.CANDIDATE.equals(r.getType()))
                .map(r -> r.getGroupId()).collect(Collectors.toSet());
        return candidateGroups;
    }

    @Override
    public boolean isActive(String processInstanceId) {
        final HistoricProcessInstance processInstance = history().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        return processInstance.getEndTime() == null;
    }

    @Override
    public void setProcessVariable(String processInstanceId, String name, Object value) {
        runtime().setVariable(processInstanceId, name, value);
    }

    @Override
    public Collection<String> getActiveTaskNames(final String processInstanceId) {
        final List<Task> list = task().createTaskQuery().processInstanceId(processInstanceId).list();
        return list.stream().map(t -> t.getName()).collect(Collectors.toSet());
    }

    @Override
    public String getTaskName(String taskId) {
        final Task task = task().createTaskQuery().taskId(taskId).singleResult();
        return task.getName();
    }

    public Object getEndEvent(final String processInstanceId) {
        final HistoricActivityInstance endEvent = history().createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
                .activityType("endEvent").orderByHistoricActivityInstanceStartTime().desc().singleResult();

        return endEvent;
    }

    private void completeWithRetry(final String taskId, final String comment, final Map<String, Object> parameters) {
        try {
            task().addComment(taskId, null, "Complete", comment);
            task().complete(taskId, parameters, true);
        } catch (ActivitiOptimisticLockingException aole) {
            try {
                Thread.sleep(10);
                completeWithRetry(taskId, comment, parameters);
            } catch (InterruptedException ie) {
                throw new RuntimeException("Unable to complete task with id: " + taskId, ie);
            }
        }
    }

    private IdentityService identity() {
        return engine.getIdentityService();
    }

    private RuntimeService runtime() {
        return engine.getRuntimeService();
    }

    private TaskService task() {
        return engine.getTaskService();
    }

    private RepositoryService repository() {
        return engine.getRepositoryService();
    }

    private HistoryService history() {
        return engine.getHistoryService();
    }

}
