package me.aribon.labywhere.backend.provider.preferences;

import android.support.annotation.NonNull;

import me.aribon.labywhere.backend.model.Profile;
import me.aribon.labywhere.backend.model.User;

/**
 * Created on 26/04/2016
 *
 * @author Anthony
 */
public class AccountPreferences {

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
                .apply();
    }

    public static int getUserId() {
        return PreferencesManager.getInstance().getSharedPreferences().getInt(KEY_ID, -1);
    }

    public static void setUserType(int userType) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putInt(KEY_TYPE, userType)
                .apply();
    }

    public static int getUserType() {
        return PreferencesManager.getInstance().getSharedPreferences().getInt(KEY_TYPE, -1);
    }

    public static void setUserEmail(String userEmail) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_EMAIL, userEmail)
                .apply();
    }

    public static String getUserEmail() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_EMAIL, null);
    }

    public static void setUserCreatedAt(String userCreatedAt) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_CREATED_AT, userCreatedAt)
                .apply();
    }

    public static String getUserCreatedAt() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_CREATED_AT, null);
    }

    public static void setUserChangedAt(String userChangedAt) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_CHANGED_AT, userChangedAt)
                .apply();
    }

    public static String getUserChangedAt() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_CHANGED_AT, null);
    }

    public static void setUserProfileId(int userProfileId) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putInt(KEY_PROFILE_ID, userProfileId)
                .apply();
    }

    public static int getUserProfileId() {
        return PreferencesManager.getInstance().getSharedPreferences().getInt(KEY_PROFILE_ID, -1);
    }

    public static void setUserProfileFirstname(String userProfileFirstname) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_FIRSTNAME, userProfileFirstname)
                .apply();
    }

    public static String getUserProfileFirstname() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_FIRSTNAME, null);
    }

    public static void setUserProfileLastname(String userProfileLastname) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_LASTNAME, userProfileLastname)
                .apply();
    }

    public static String getUserProfileLastname() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_LASTNAME, null);
    }

    public static void setUserProfileGender(String userProfileGender) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_GENDER, userProfileGender)
                .apply();
    }

    public static String getUserProfileGender() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_GENDER, null);
    }

    public static void setUserProfileCity(String userProfileCity) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_CITY, userProfileCity)
                .apply();
    }

    public static String getUserProfileCity() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_CITY, null);
    }

    public static void setUserProfileCountry(String userProfileCountry) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_COUNTRY, userProfileCountry)
                .apply();
    }

    public static String getUserProfileCountry() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_COUNTRY, null);
    }

    public static void setUserProfileBirthdate(String userProfileBirthdate) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_BIRTHDATE, userProfileBirthdate)
                .apply();
    }

    public static String getUserProfileBirthdate() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_BIRTHDATE, null);
    }

    public static void setUserProfileCreatedAt(String userProfileCreatedAt) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_CREATED_AT, userProfileCreatedAt)
                .apply();
    }

    public static String getUserProfileCreatedAt() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_CREATED_AT, null);
    }

    public static void setUserProfileChangedAt(String userProfileChangedAt) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_PROFILE_CHANGED_AT, userProfileChangedAt)
                .apply();
    }

    public static String getUserProfileChangedAt() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_PROFILE_CHANGED_AT, null);
    }

    public static void setAccount(@NonNull User user) {
        setUserId(user.getId());
        setUserType(user.getType());
        setUserEmail(user.getEmail());
        setUserCreatedAt(user.getCreatedAt());
        setUserChangedAt(user.getChangedAt());

        setUserProfileId(user.getProfile().getId());
        setUserProfileFirstname(user.getProfile().getFirstname());
        setUserProfileLastname(user.getProfile().getLastname());
        setUserProfileGender(user.getProfile().getGender());
        setUserProfileCity(user.getProfile().getCity());
        setUserProfileCountry(user.getProfile().getCountry());
        setUserProfileBirthdate(user.getProfile().getBirthdate());
        setUserProfileCreatedAt(user.getProfile().getCreatedAt());
        setUserProfileChangedAt(user.getProfile().getChangedAt());
    }

    public static User getAccount() {
        User user = new User();

        user.setId(getUserId());
        user.setEmail(getUserEmail());
        user.setType(getUserType());
        user.setCreatedAt(getUserCreatedAt());
        user.setChangedAt(getUserChangedAt());

        Profile profile = new Profile();

        profile.setId(getUserProfileId());
        profile.setFirstname(getUserProfileFirstname());
        profile.setLastname(getUserProfileLastname());
        profile.setGender(getUserProfileGender());
        profile.setCity(getUserProfileCity());
        profile.setCountry(getUserProfileCountry());
        profile.setBirthdate(getUserProfileBirthdate());
        profile.setCreatedAt(getUserCreatedAt());
        profile.setChangedAt(getUserChangedAt());

        user.setProfile(profile);

        return user;
    }
}
