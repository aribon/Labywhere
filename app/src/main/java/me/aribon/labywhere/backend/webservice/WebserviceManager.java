package me.aribon.labywhere.backend.webservice;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class WebserviceManager {

    private static final String SERVER_URL = "http://dev.aribon.me/api/labywhere/v1/";
    private static WebserviceManager instance;

    public static synchronized void initializeInstance() {
        if (instance == null)
            instance = new WebserviceManager();
    }

    public static synchronized WebserviceManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(WebserviceManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return instance;
    }
}
