
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface WF {
String start(String workflowType, Map<String, Object> parameters, String userId);
void complete(String taskId, String userId, String comment, Map<String, Object> parameters);
void complete(String processInstanceId, String taskName, String userId, String comment, Map<String, Object> parameters);
Object processVariable(String processInstanceId, String variableName);
Object processVariableByTaskId(String taskId, String variableName);
void cancel(String processInstanceId, String signalId);
void cancel(String processInstanceId, String userId, String comment);
String findTask(String processInstanceId, String taskName);
List<String> getActivities(String processInstanceId);
void deleteProcess(String processInstanceId, String deleteReason);
void deleteHistoricProcess(String processInstanceId);
String processDefinitionKey(String processInstanceId);
String processDefinitionKeyByTaskId(String taskId);
Set<String> candidateGroups(String taskId);
boolean isActive(String processInstanceId);
void setProcessVariable(String processInstanceId, String name, Object value);
Collection<String> getActiveTaskNames(String processInstanceId);
String getTaskName(String taskId);
}
