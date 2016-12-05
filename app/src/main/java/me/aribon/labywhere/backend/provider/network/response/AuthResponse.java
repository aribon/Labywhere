package me.aribon.labywhere.backend.provider.network.response;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class AuthResponse extends Response {

    private String token;

    public AuthResponse(boolean error, String message, String token) {
        super(error, message);
        this.token = token;
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
