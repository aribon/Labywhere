package me.aribon.labywhere.ui.module;

import java.util.Map;

/**
 * Created on 23/04/2017
 *
 * @author Anthony
 */
public class RegisterModule {

    public interface View {
        String getEmail();
        String getPassword();
        String getFirstname();
        String getLastname();

        void setEmail(String email);
        void setPassword(String password);
        void setFirstname(String firstname);
        void setLastname(String lastname);

        void setEmailError(String msg);
        void setPasswordError(String msg);
        void setFirstnameError(String msg);
        void setLastnameError(String msg);
    }

    public interface Presenter {
        void checkRegister();
        void register(Map<String, String> body);
    }
}
