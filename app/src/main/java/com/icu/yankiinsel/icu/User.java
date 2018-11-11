package com.icu.yankiinsel.icu;


import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String surname;
    public String location;
    public int age;
    public Gender gender;
    public String imageName;
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


}

