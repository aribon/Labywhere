package me.aribon.labywhere.backend.provider.network.response;

import me.aribon.labywhere.backend.model.User;

/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class UserResponse extends Response {

    private User user;

    public UserResponse(User user) {
        this.user = user;
    }

    public UserResponse(boolean error, String message, User user) {
        super(error, message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
