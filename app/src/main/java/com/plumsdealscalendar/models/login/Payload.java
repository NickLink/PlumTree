package com.plumsdealscalendar.models.login;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NickNb on 28.11.2016.
 */
public class Payload extends RealmObject{
    @PrimaryKey public int id = 1;
    public int userId;
    public String name;
    public String email;
    public String gender;
    public int themeId;
    public int age;
    public String image;
    public String apiHash;
    public int city;
    public int zip;
    public String phone;
    public String birthday;
    public int plumPoints;
    public int followedBusiness;
    public int contactsCount;
    public String saved_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getApiHash() {
        return apiHash;
    }

    public void setApiHash(String apiHash) {
        this.apiHash = apiHash;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getPlumPoints() {
        return plumPoints;
    }

    public void setPlumPoints(int plumPoints) {
        this.plumPoints = plumPoints;
    }

    public int getFollowedBusiness() {
        return followedBusiness;
    }

    public void setFollowedBusiness(int followedBusiness) {
        this.followedBusiness = followedBusiness;
    }

    public int getContactsCount() {
        return contactsCount;
    }

    public void setContactsCount(int contactsCount) {
        this.contactsCount = contactsCount;
    }

    public String getSaved_image() {
        return saved_image;
    }

    public void setSaved_image(String saved_image) {
        this.saved_image = saved_image;
    }
}