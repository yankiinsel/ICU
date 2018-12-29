package com.icu.yankiinsel.icu;

import android.content.Context;
import android.widget.ImageView;

import com.icu.yankiinsel.icu.Model.Gender;
import com.icu.yankiinsel.icu.Model.Message;
import com.icu.yankiinsel.icu.Model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    private static final List<User> userSet;
    private static final List<Message> messageSet;
    private static final List<User> likedUserSet;
    public static final List<User> dislikedUserSet;


    static
    {
        userSet = new ArrayList<>();
        setExampleUsers();
        messageSet = new ArrayList<>();
        likedUserSet = new ArrayList<>();
        dislikedUserSet = new ArrayList<>();
    }

    public static void displayRoundImageFromUrl(Context context, String url, ImageView imageView)
    {

    }

    public static String formatDateTime(long time)
    {
        return null;
    }

    private static void setExampleUsers()
    {
        User user0 = new User("Mahmut", "Gulbayir", 27, Gender.MALE, "Sariyer", "WEOJNFDLS");
        User user1 = new User("Mihriban", "Kocabiyik", 32, Gender.FEMALE, "Kadikoy", "SDAFHDUFIN");
        User user2 = new User("Mefkure", "Cambaz", 24, Gender.FEMALE, "Besiktas", "jfhihqjkSD");

        user0.imageName = "man1";
        user1.imageName = "girl1";
        user2.imageName = "girl2";

        Collections.addAll(user0.interests, "Movies", "Video Games", "Music");
        Collections.addAll(user1.interests, "Movies", "Video Games", "Music");
        Collections.addAll(user2.interests, "Travel", "Books", "Food");


        userSet.add(user0);
        userSet.add(user1);
        userSet.add(user2);
    }

    public static List<Message> getExampleMessages()
    {
        return messageSet;
    }

    public static List<User> getExampleUsers()
    {
        return userSet;
    }

    public static List<User> getLikedUsers()
    {
        return likedUserSet;
    }

}
