package me.aribon.labywhere.backend.model;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class Profile {

    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    private String country;
    private String city;
    private String birthdate;
    private String createdAt;
    private String changedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Profile() {
    }

    /**
     *
     * @param id
     * @param createdAt
     * @param birthdate
     * @param gender
     * @param lastname
     * @param firstname
     * @param changedAt
     * @param city
     * @param country
     */
    public Profile(int id, String firstname, String lastname, String gender, String country, String city, String birthdate, String createdAt, String changedAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.birthdate = birthdate;
        this.createdAt = createdAt;
        this.changedAt = changedAt;
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

    public Profile withId(int id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return
     * The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     * The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Profile withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    /**
     *
     * @return
     * The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     * The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Profile withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Profile withGender(String gender) {
        this.gender = gender;
        return this;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public Profile withCountry(String country) {
        this.country = country;
        return this;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    public Profile withCity(String city) {
        this.city = city;
        return this;
    }

    /**
     *
     * @return
     * The birthdate
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     *
     * @param birthdate
     * The birthdate
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Profile withBirthdate(String birthdate) {
        this.birthdate = birthdate;
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

    public Profile withCreatedAt(String createdAt) {
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

    public Profile withChangedAt(String changedAt) {
        this.changedAt = changedAt;
        return this;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", changedAt='" + changedAt + '\'' +
                '}';
    }
}