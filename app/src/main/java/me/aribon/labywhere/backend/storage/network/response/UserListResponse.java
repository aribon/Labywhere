package me.aribon.labywhere.backend.storage.network.response;

import java.util.List;

import me.aribon.labywhere.backend.model.User;

/**
 * Created by aribon on 16/05/2016.
 */
public class UserListResponse extends Response {

    private List<User> users;

    public UserListResponse(List<User> users) {
        this.users = users;
    }

    public UserListResponse(boolean error, String message, List<User> users) {
        super(error, message);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
