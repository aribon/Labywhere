package me.aribon.labywhere.ui.module;

import java.util.Map;

/**
 * Created on 21/03/2017
 *
 * @author Anthony
 */
public class LoginModule {

    public interface View {
        String getEmail();
        String getPassword();

        void setEmail(String email);
        void setPassword(String password);

        void setEmailError(String msg);
        void setPasswordError(String msg);
    }

    public interface Presenter {
        void checkLogin();
        void login(Map<String, String> body);
    }
}
