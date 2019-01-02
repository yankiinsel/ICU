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
        //setExampleUsers();
        messageSet = new ArrayList<>();
        likedUserSet = new ArrayList<>();

        likedUserSet.add(new User("Manolya", "Elmas", 32, "Female", "Kadikoy", "SDAFHDUFIN"));
        likedUserSet.get(0).imageName = "girl11";

        dislikedUserSet = new ArrayList<>();
    }

    public static void displayRoundImageFromUrl(Context context, String url, ImageView imageView)
    {

    }

    public static String formatDateTime(long time)
    {
        return null;
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
