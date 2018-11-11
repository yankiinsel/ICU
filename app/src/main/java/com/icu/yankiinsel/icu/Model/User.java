package com.icu.yankiinsel.icu.Model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String surname;
    public String location;
    public int age;
    public Gender gender;
    public String imageName;

    String profileUrl;

    public List<String> interests = new ArrayList<>();

    public User(String name, String surname, int age, Gender gender, String location) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }

    public String getNameAge() {
        return name + ", " + age;
    }

    public String getProfileUrl() {return profileUrl;}

    public String getName() {return name;}

    public String getUserId() {
        return "";
    }
}

