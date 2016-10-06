package utils;

/**
 * Created by suman.bn on 07/10/16.
 */
public class SampleClientException extends Exception {

    private static final long serialVersionUID = 1L;
    private int status;
    private String message;

    public SampleClientException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Gets the HTTP status code of the failure, such as 404.
     */
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message + " (http status: " + status + ")";
    }

    @Override
    public String toString() {
        return message + " (http status: " + status + ")";
    }
}

