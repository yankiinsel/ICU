package com.icu.yankiinsel.icu.Model;

import android.database.Cursor;

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
    public String profileUrl;

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

    public static User populatePerson(Cursor cursor) {
        try {
            int idIndex = cursor.getColumnIndexOrThrow("_id");
            int nameIndex = cursor.getColumnIndexOrThrow("name");
            int surnameIndex = cursor.getColumnIndexOrThrow("surname");
            int ageIndex = cursor.getColumnIndexOrThrow("age");
            int genderIndex = cursor.getColumnIndexOrThrow("gender");
            int locationIndex = cursor.getColumnIndexOrThrow("location");
            int imageIndex = cursor.getColumnIndexOrThrow("imageName");

            long id = cursor.getLong(idIndex);
            String name = cursor.getString(nameIndex);
            String surname = cursor.getString(surnameIndex);
            int age = cursor.getInt(ageIndex);
            String gender = cursor.getString(genderIndex);
            String location = cursor.getString(locationIndex);
            String image = cursor.getString(imageIndex);


            User user = new User(name, surname, age, gender, location, Long.toString(id));
            user.imageName = image;
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

