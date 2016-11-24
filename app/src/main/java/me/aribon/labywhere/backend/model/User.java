package me.aribon.labywhere.backend.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
@RealmClass
public class User extends RealmObject implements Data {

    public final static String TABLE_NAME = User.class.getSimpleName();

    public static final long STALE_MS = 20 * 1000; //20 seconds

    public final static String KEY_ID = "id";
    public final static String KEY_TYPE = "type";
    public final static String KEY_EMAIL = "email";
    public final static String KEY_PROFILE = "profile";
    public final static String KEY_CREATED_AT = "createdAt";
    public final static String KEY_CHANGED_AT = "changedAt";
    public final static String KEY_TIMESTAMP = "timestamp";

    @PrimaryKey
    private int id;
    private int type;
    private String email;
    private Profile profile;
    private String createdAt;
    private String changedAt;
    private long timestamp;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
        this.timestamp = System.currentTimeMillis();
        this.profile = new Profile();
    }

    /**
     *
     * @param id
     * @param email
     * @param createdAt
     * @param type
     * @param changedAt
     * @param profile
     */
    public User(int id, int type, String email, Profile profile, String createdAt, String changedAt) {
        this.id = id;
        this.type = type;
        this.email = email;
        this.profile = profile;
        this.createdAt = createdAt;
        this.changedAt = changedAt;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     *
     * @param user
     */
    public User(User user) {
        this(user.id, user.type, user.email, user.profile, user.createdAt, user.changedAt);
    }

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    public User withId(int id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return
     * The type
     */
    public int getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(int type) {
        this.type = type;
    }

    public User withType(int type) {
        this.type = type;
        return this;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     *
     * @return
     * The profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     *
     * @param profile
     * The profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public User withProfile(Profile profile) {
        this.profile = profile;
        return this;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     *
     * @return
     * The changedAt
     */
    public String getChangedAt() {
        return changedAt;
    }

    /**
     *
     * @param changedAt
     * The changed_at
     */
    public void setChangedAt(String changedAt) {
        this.changedAt = changedAt;
    }

    public User withChangedAt(String changedAt) {
        this.changedAt = changedAt;
        return this;
    }

    @Override
    public String toString() {
        return "User {" + "\n" +
                "\tid=" + id +
                ",\n\t type=" + type +
                ",\n\t email=" + email +
                ",\n\t\t profile=" + profile.toString() +
                ",\n\t createdAt=" + createdAt +
                ",\n\t changedAt=" + changedAt +
                "\n}";
    }

    @Override
    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }
}