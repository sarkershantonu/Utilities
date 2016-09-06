
public class StagingServiceImpl implements StagingService {

    @Override
    public StagingResponse handle(StagingRequest request) {
        switch (request.getOp()) {
        case CREATE:
            return create(request);
        case UPDATE:
            return update(request);
        default:
            throw new IllegalArgumentException("Unexpected value for StagingOp enum: " + request.getOp());
        }
    }

    private StagingResponse update(StagingRequest request) {
        WfDataServiceEnum.SET_PROC_INST_ID_WORKFLOW.init().addAllParam(request.getParameters()).update();
        return new StagingResponse(request, true, "", null);
    }

    private StagingResponse create(StagingRequest request) {
        final long nodeId = WfDataServiceEnum.SET_NODE_ATTRIBUTES.init().addAllParam(request.getParameters()).update();
        return new StagingResponse(request, true, "", nodeId);
    }
}
