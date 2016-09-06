
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultAction implements Action {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultAction.class);

    private final WFDataService wfDataService;

    private final WF wf;

    public DefaultAction(WFDataService wfDataService, WF wf) {
        this.wfDataService = wfDataService;
        this.wf = wf;
    }

    @Override
    public Map<String, Object> preHandle(WfRequest wfRequest) {
        final Map<String, Object> parameters = wfRequest.getParameters();
        final String userGpn = (String) parameters.get("userGpn");
        final long nodeId = getNodeIdWithFallback(wfRequest, Optional.empty(), Optional.empty());
        final WorkflowType wfType = getWorkflowTypeWithFallback(wfRequest);

        final Map<String, Object> instanceState = getWorkflowInstanceStateWithLock(userGpn, nodeId, wfType);
        @SuppressWarnings("unchecked")
        final Map<String, Object> state = (Map<String, Object>) instanceState.get("StateItem");

        if (wfRequest.getOp() == WFOP.START) {
            final String wfId = UUID.randomUUID().toString();
            state.put("workflowInstanceId", wfId);
            parameters.putAll(state);
        } else if (state.isEmpty()) {
            throw new RuntimeException(String.format("There is no active %s workflow for Node with NodeId %s", wfType.getDescription(), nodeId));
        }

        return instanceState;
    }

    @Override
    public Map<String, Object> postHandle(WfRequest wfRequest, WfResponse wfResponse, Map<String, Object> params) {
        final Map<String, Object> parameters = wfRequest.getParameters();
        final String userGpn = (String) parameters.get("userGpn");
        final long nodeId = getNodeIdWithFallback(wfRequest, Optional.of(wfResponse), Optional.of(params));
        final WorkflowType wfType = getWorkflowTypeWithFallback(wfRequest);

        @SuppressWarnings("unchecked")
        final Map<String, Object> previousWorkflowState = (Map<String, Object>) params.get("StateItem");
        @SuppressWarnings("unchecked")
        final Map<String, Object> responseWorkflowState = (Map<String, Object>) wfResponse.getData();

        final Map<String, Object> state = mergeWorkflowStates(previousWorkflowState, responseWorkflowState);

        final String lockKey = (String) params.get("LockKey");

        setWorkflowInstanceState(userGpn, nodeId, wfType, state, lockKey);
        return params;
    }

    @Override
    public void handleError(WfRequest wfRequest, WfResponse wfResponse, Map<String, Object> params) {
        try {
            final Map<String, Object> parameters = wfRequest.getParameters();
            final String userGpn = (String) parameters.get("userGpn");
            final String lockKey = (String) params.get("LockKey");

            if (lockKey != null && !lockKey.trim().isEmpty()) {
                wfDataService.unlockWorkflowInstanceState(userGpn, lockKey);
            }
        } catch (Exception ex) {
            LOGGER.error("Error handling error in WorkflowService", ex);
        }
    }

    private Map<String, Object> mergeWorkflowStates(Map<String, Object> previousState, Map<String, Object> currentState) {
        final Map<String, Object> state = new HashMap<String, Object>(previousState);
        state.putAll(currentState);
        return state;
    }

    private Map<String, Object> getWorkflowInstanceStateWithLock(final String userGpn, final long nodeId, final WorkflowType wfType) {
        final Map<String, Object> instanceState = wfDataService.lockWorkflowInstanceState(userGpn, nodeId, wfType);
        @SuppressWarnings("unchecked")
        final List<Map<String, Object>> stateItem = (List<Map<String, Object>>) instanceState.get("StateItem");
        final Map<String, Object> state = new HashMap<>();

        for (Map<String, Object> map : stateItem) {
            final String varName = (String) map.get("VAR_NAME");
            final String varValue = (String) map.get("VAR_VALUE");
            final String varType = (String) map.get("VAR_TYPE");

            state.put(varName, getObjFromString(varValue, varType));
        }

        instanceState.put("StateItem", state);

        return instanceState;
    }

    private void setWorkflowInstanceState(final String userGpn, final long nodeId, final WorkflowType wfType,
            final Map<String, Object> workflowInstanceState, final String lockKey) {
        final int size = workflowInstanceState.entrySet().size();
        final List<String> names = new ArrayList<>(size);
        final List<String> values = new ArrayList<>(size);
        final List<String> types = new ArrayList<>(size);

        workflowInstanceState.forEach((k, v) -> {
            names.add(k);
            Tuple<String, String> tuple = getStringAndTypeFromObj(v);
            values.add(tuple.val1);
            types.add(tuple.val2);
        });

        wfDataService.setWorkflowInstanceState(userGpn, nodeId, wfType, lockKey, names, values, types);
    }

    private Object getObjFromString(String value, String type) {
        switch (type) {
        case "String":
            return value;
        case "Int32":
            return Long.valueOf(value);
        case "Guid":
            return value;
        case "GroupType":
            return GroupType.getGroupType(Integer.parseInt(value));
        case "NodeWorkflowStatus":
            return NodeWorkflowStatus.getNodeWorkflowStatus(Integer.parseInt(value));
        default:
            throw new IllegalArgumentException(String.format("Unexpected workflow instance argument type: %s", type));
        }
    }

    private Tuple<String, String> getStringAndTypeFromObj(Object value) {
        if (value instanceof String) {
            return new Tuple<String, String>((String) value, "String");
        }

        if (value instanceof Integer || value instanceof Long) {
            return new Tuple<String, String>(String.valueOf(value), "Int32");
        }

        if (value instanceof GroupType) {
            return new Tuple<String, String>(String.valueOf(((GroupType) value).getId()), "GroupType");
        }

        if (value instanceof NodeWorkflowStatus) {
            return new Tuple<String, String>(String.valueOf(((NodeWorkflowStatus) value).getId()), "NodeWorkflowStatus");
        }

        throw new IllegalArgumentException(String.format("Unexpected workflow instance argument type: %s", value.getClass().getCanonicalName()));
    }

    private Long getNodeIdWithFallback(WfRequest request, Optional<WfResponse> responseOpt, Optional<Map<String, Object>> paramsOpt) {
        final Map<String, Object> parameters = request.getParameters();
        if (parameters.containsKey("nodeId")) {
            return (long) parameters.get("nodeId");
        }

        final Optional<Long> nodeIdOpt = responseOpt.map(resp -> {
            @SuppressWarnings("unchecked")
            final Map<String, Object> data = (Map<String, Object>) resp.getData();
            return (long) data.get("nodeId");
        });

        final long nodeId = nodeIdOpt.orElseGet(() -> getNodeId(paramsOpt, parameters));

        return nodeId;
    }

    private Long getNodeId(Optional<Map<String, Object>> paramsOpt, Map<String, Object> parameters) {
        final Optional<Long> nodeIdOpt = paramsOpt.map(r -> (Long) r.get("nodeId"));

        final Long nodeId = nodeIdOpt.orElseGet(() -> {
            final Map<String, Object> wfIn = getWorkflowInput(parameters).<IllegalArgumentException>orElseThrow(
                    () -> {
                        throw new IllegalArgumentException(
                                "Failed to get NodeId for the process. Neither the variable processInstanceId nor taskId was found.");
                    });

            if (!wfIn.containsKey("nodeId")) {
                throw new IllegalArgumentException("Failed to get NodeId for the process identified by request parameters " + parameters);
            }

            final Long id = (Long) wfIn.get("nodeId");
            return id;
        });

        return nodeId;
    }

    private Optional<Map<String, Object>> getWorkflowInput(final Map<String, Object> parameters) {
        if (parameters.containsKey("processInstanceId")) {
            final String processInstanceId = (String) parameters.get("processInstanceId");
            @SuppressWarnings("unchecked")
            final Map<String, Object> wfIn = (Map<String, Object>) wf.processVariable(processInstanceId, "wfIn");
            return Optional.of(wfIn);
        }

        if (parameters.containsKey("taskId")) {
            final String taskId = (String) parameters.get("taskId");
            @SuppressWarnings("unchecked")
            final Map<String, Object> wfIn = (Map<String, Object>) wf.processVariableByTaskId(taskId, "wfIn");
            return Optional.of(wfIn);
        }

        return Optional.empty();
    }

    private WorkflowType getWorkflowTypeWithFallback(WfRequest request) {
        final String workflowType = request.getWorkflowType();
        if (workflowType != null) {
            return WorkflowType.getByDescription(workflowType);
        }

        final String processInstanceId = request.getProcessInstanceId();
        if (processInstanceId != null) {
            final String processDefinitionKey = wf.processDefinitionKey(processInstanceId);
            return WorkflowType.getByDescription(processDefinitionKey);
        }

        final String taskId = request.getTaskId();
        if (taskId != null) {
            final String processDefinitionKey = wf.processDefinitionKeyByTaskId(taskId);
            return WorkflowType.getByDescription(processDefinitionKey);
        }

        throw new IllegalArgumentException(
                "Failed to get WorkflowType for the process. Neither the variable processInstanceId nor taskId was found.");
    }

    public class Tuple<T1, T2> {
        private final T1 val1;

        private final T2 val2;

        public Tuple(T1 val1, T2 val2) {
            this.val1 = val1;
            this.val2 = val2;
        }
    }

}
