package me.aribon.labywhere.ui.model;

/**
 * Created by anthony.ribon
 * On 15/08/2017
 */

public class UserParcelable {

    private String email;
    private String login;
    private String password;

    public UserParcelable() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmpty() {
        return email == null
                && login == null
                && password == null;
    }
}
