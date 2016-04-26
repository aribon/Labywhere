package me.aribon.labywhere.backend.preferences;

/**
 * Created on 26/04/2016
 *
 * @author Anthony
 */
public class UserPreferences {

    private static final String KEY_ID = "users.id";
    private static final String KEY_TYPE = "users.type";
    private static final String KEY_EMAIL = "users.email";
    private static final String KEY_CREATED_AT = "users.created_at";
    private static final String KEY_CHANGED_AT = "users.changed_at";
    private static final String KEY_PROFILE_ID = "users.profile.id";
    private static final String KEY_PROFILE_FIRSTNAME = "users.profile.firstname";
    private static final String KEY_PROFILE_LASTNAME = "users.profile.lastname";
    private static final String KEY_PROFILE_GENDER = "users.profile.gender";
    private static final String KEY_PROFILE_COUNTRY = "users.profile.country";
    private static final String KEY_PROFILE_CITY = "users.profile.city";
    private static final String KEY_PROFILE_BIRTHDATE = "users.profile.birthdate";
    private static final String KEY_PROFILE_CREATED_AT = "users.profile.created_at";
    private static final String KEY_PROFILE_CHANGED_AT = "users.profile.changed_at";

    public static void setUserId(int userId) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putInt(KEY_ID, userId)
                .commit();
    }

    public static int getUserId() {
        return PreferencesManager.getInstance().getSharedPreferences().getInt(KEY_ID, -1);
    }

    public static void setUserType(int userType) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putInt(KEY_TYPE, userType)
                .commit();
    }

    public static int getUserType() {
        return PreferencesManager.getInstance().getSharedPreferences().getInt(KEY_TYPE, -1);
    }

    public static void setUserEmail(String userEmail) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_EMAIL, userEmail)
                .commit();
    }

    public static String getUserEmail() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_EMAIL, null);
    }

    public static void setUserCreatedAt(String userCreatedAt) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_CREATED_AT, userCreatedAt)
                .commit();
    }

    public static String getUserCreatedAt() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_CREATED_AT, null);
    }

    public static void setUserChangedAt(String userChangedAt) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_CHANGED_AT, userChangedAt)
                .commit();
    }

    public static String getUserChangedAt() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_CHANGED_AT, null);
    }

    public static void setUserProfileId(int userProfileId) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putInt(KEY_PROFILE_ID, userProfileId)
                .commit();
    }

    public static int getUserProfileId() {
        return PreferencesManager.getInstance().getSharedPreferences().getInt(KEY_PROFILE_ID, -1);
    }

    public static void setUserProfileFirstname(String userProfileFirstname) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_FIRSTNAME, userProfileFirstname)
                .commit();
    }

    public static String getUserProfileFirstname() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_FIRSTNAME, null);
    }

    public static void setUserProfileLastname(String userProfileLastname) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_LASTNAME, userProfileLastname)
                .commit();
    }

    public static String getUserProfileLastname() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_LASTNAME, null);
    }

    public static void setUserProfileGender(String userProfileGender) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_GENDER, userProfileGender)
                .commit();
    }

    public static String getUserProfileGender() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_GENDER, null);
    }

    public static void setUserProfileCity(String userProfileCity) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_CITY, userProfileCity)
                .commit();
    }

    public static String getUserProfileCity() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_CITY, null);
    }

    public static void setUserProfileCountry(String userProfileCountry) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_COUNTRY, userProfileCountry)
                .commit();
    }

    public static String getUserProfileCountry() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_COUNTRY, null);
    }

    public static void setUserProfileBirthdate(String userProfileBirthdate) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_BIRTHDATE, userProfileBirthdate)
                .commit();
    }

    public static String getUserProfileBirthdate() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_BIRTHDATE, null);
    }

    public static void setUserProfileCreatedAt(String userProfileCreatedAt) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_CREATED_AT, userProfileCreatedAt)
                .commit();
    }

    public static String getUserProfileCreatedAt() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_CREATED_AT, null);
    }

    public static void setUserProfileChangedAt(String userProfileChangedAt) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_CHANGED_AT, userProfileChangedAt)
                .commit();
    }

    public static String getUserProfileChangedAt() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_CHANGED_AT, null);
    }
}