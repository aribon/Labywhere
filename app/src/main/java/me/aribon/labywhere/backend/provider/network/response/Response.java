package me.aribon.labywhere.backend.provider.network.response;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class Response {

    private boolean error;
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public Response() {
    }

    /**
     *
     * @param message
     * @param error
     */
    public Response(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    /**
     *
     * @return
     * The error
     */
    public boolean isError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(boolean error) {
        this.error = error;
    }

    public Response withError(boolean error) {
        this.error = error;
        return this;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Response withMessage(String message) {
        this.message = message;
        return this;
    }

}
