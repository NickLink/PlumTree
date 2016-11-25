package com.plumsdealscalendar.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NickNb on 24.11.2016.
 */
public class UserData {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("payload")
    @Expose
    private Payload payload;
    @SerializedName("error")
    @Expose
    private String error;

    /**
     *
     * @return
     * The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The payload
     */
    public Payload getPayload() {
        return payload;
    }

    /**
     *
     * @param payload
     * The payload
     */
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    /**
     *
     * @return
     * The error
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(String error) {
        this.error = error;
    }


    public class Payload {

        @SerializedName("user_id")
        @Expose
        private int userId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("theme_id")
        @Expose
        private int themeId;
        @SerializedName("age")
        @Expose
        private Object age;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("api_hash")
        @Expose
        private String apiHash;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("zip")
        @Expose
        private Object zip;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("birthday")
        @Expose
        private String birthday;
        @SerializedName("plum_points")
        @Expose
        private int plumPoints;
        @SerializedName("followed_business")
        @Expose
        private int followedBusiness;
        @SerializedName("contacts_count")
        @Expose
        private int contactsCount;

        /**
         *
         * @return
         * The userId
         */
        public int getUserId() {
            return userId;
        }

        /**
         *
         * @param userId
         * The user_id
         */
        public void setUserId(int userId) {
            this.userId = userId;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
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

        /**
         *
         * @return
         * The themeId
         */
        public int getThemeId() {
            return themeId;
        }

        /**
         *
         * @param themeId
         * The theme_id
         */
        public void setThemeId(int themeId) {
            this.themeId = themeId;
        }

        /**
         *
         * @return
         * The age
         */
        public Object getAge() {
            return age;
        }

        /**
         *
         * @param age
         * The age
         */
        public void setAge(Object age) {
            this.age = age;
        }

        /**
         *
         * @return
         * The image
         */
        public String getImage() {
            return image;
        }

        /**
         *
         * @param image
         * The image
         */
        public void setImage(String image) {
            this.image = image;
        }

        /**
         *
         * @return
         * The apiHash
         */
        public String getApiHash() {
            return apiHash;
        }

        /**
         *
         * @param apiHash
         * The api_hash
         */
        public void setApiHash(String apiHash) {
            this.apiHash = apiHash;
        }

        /**
         *
         * @return
         * The city
         */
        public Object getCity() {
            return city;
        }

        /**
         *
         * @param city
         * The city
         */
        public void setCity(Object city) {
            this.city = city;
        }

        /**
         *
         * @return
         * The zip
         */
        public Object getZip() {
            return zip;
        }

        /**
         *
         * @param zip
         * The zip
         */
        public void setZip(Object zip) {
            this.zip = zip;
        }

        /**
         *
         * @return
         * The phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         *
         * @param phone
         * The phone
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         *
         * @return
         * The birthday
         */
        public String getBirthday() {
            return birthday;
        }

        /**
         *
         * @param birthday
         * The birthday
         */
        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        /**
         *
         * @return
         * The plumPoints
         */
        public int getPlumPoints() {
            return plumPoints;
        }

        /**
         *
         * @param plumPoints
         * The plum_points
         */
        public void setPlumPoints(int plumPoints) {
            this.plumPoints = plumPoints;
        }

        /**
         *
         * @return
         * The followedBusiness
         */
        public int getFollowedBusiness() {
            return followedBusiness;
        }

        /**
         *
         * @param followedBusiness
         * The followed_business
         */
        public void setFollowedBusiness(int followedBusiness) {
            this.followedBusiness = followedBusiness;
        }

        /**
         *
         * @return
         * The contactsCount
         */
        public int getContactsCount() {
            return contactsCount;
        }

        /**
         *
         * @param contactsCount
         * The contacts_count
         */
        public void setContactsCount(int contactsCount) {
            this.contactsCount = contactsCount;
        }

    }

}