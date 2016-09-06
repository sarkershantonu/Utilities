package org.automation.utils.activity;

import java.util.List;
import java.util.Map;

/**
 * Created by shantonu on 9/6/16.
 */
public class WFDataService {
    public void setWorkflowInstanceState(String userGpn, long nodeId, WorkflowType wfType, String lockKey, List<String> names, List<String> values, List<String> types) {
    }

    public Map<String,Object> lockWorkflowInstanceState(String userGpn, long nodeId, WorkflowType wfType) {
        return null;
    }

    public void unlockWorkflowInstanceState(String userGpn, String lockKey) {
    }
}
