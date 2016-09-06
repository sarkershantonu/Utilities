
import java.util.Map;
import java.util.Objects;

public class StagingRequest {

    static final String STRING = "StagingRequest [requestId=%s, op=%s, parameters=%s]";

    public static enum StagingOp {
        CREATE, UPDATE
    }

    private final String requestId;

    private final StagingOp op;

    private final Map<String, Object> parameters;

    public StagingRequest(final String requestId, final StagingOp op, final Map<String, Object> parameters) {
        this.requestId = requestId;
        this.op = op;
        this.parameters = parameters;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public String getRequestId() {
        return requestId;
    }

    public StagingOp getOp() {
        return op;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, op, parameters);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof StagingRequest)) {
            return false;
        }
        final StagingRequest other = (StagingRequest) obj;
        return Objects.equals(requestId, other.requestId) && Objects.equals(op, other.op) && Objects.equals(parameters, other.parameters);
    }

    @Override
    public String toString() {
        return String.format(STRING, requestId, op, parameters);
    }

}
