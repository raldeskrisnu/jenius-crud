package com.id.jenius.jenius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("firstName")
    @Expose
    private String firstname;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("age")
    @Expose
    private int age;

    @SerializedName("photo")
    @Expose
    private String photo;

    public Data(String firstname, String lastName, int age, String photo){
        this.firstname = firstname;
        this.lastName = lastName;
        this.age = age;
        this.photo = photo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }
}
