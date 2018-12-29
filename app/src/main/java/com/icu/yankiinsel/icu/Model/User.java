package com.icu.yankiinsel.icu.Model;

import java.util.ArrayList;
import java.util.List;

public class User {

    public String name;
    public String surname;
    public String location;
    public int age;
    public String gender;
    public String imageName;
    public String id;
    String profileUrl;

    public List<String> interests = new ArrayList<>();

    public User(String name, String surname, int age, String gender, String location, String id) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.id = id;
    }

    public String getNameAge() {
        return name + ", " + age;
    }

    public String getProfileUrl() {return profileUrl;}

    public String getName() {return name;}

    public String getUserId() {
        return id;
    }

}

