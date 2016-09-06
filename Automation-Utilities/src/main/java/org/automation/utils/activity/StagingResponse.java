

import java.util.Objects;

public class StagingResponse {

    static final String STRING = "StagingResponse [request=%s, success=%s, message=%s, data=%s]";

    private StagingRequest request;

    private boolean success;

    private String message;

    private Object data;

    public StagingResponse() {
    }

    public StagingResponse(StagingRequest request, boolean success, String message, Object data) {
        this.request = request;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public StagingRequest getRequest() {
        return request;
    }

    public void setRequest(StagingRequest request) {
        this.request = request;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, success, message, data);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof StagingResponse)) {
            return false;
        }
        final StagingResponse other = (StagingResponse) obj;
        return Objects.equals(data, other.data) && Objects.equals(message, other.message) && Objects.equals(success, other.success)
                && Objects.equals(request, other.request);
    }

    @Override
    public String toString() {
        return String.format(STRING, request, success, message, data);
    }

}
