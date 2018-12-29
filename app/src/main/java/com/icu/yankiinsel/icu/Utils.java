package com.icu.yankiinsel.icu;

import android.content.Context;
import android.widget.ImageView;

import com.icu.yankiinsel.icu.Model.Message;
import com.icu.yankiinsel.icu.Model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    private static final List<User> userSet;
    private static final List<Message> messageSet;
    private static final List<User> likedUserSet;


    static
    {
        userSet = new ArrayList<>();
        setExampleUsers();
        messageSet = new ArrayList<>();
        setExampleMessages();
        likedUserSet = new ArrayList<>();
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
        User user0 = new User("Mahmut", "Gulbayir", 27, "MALE", "Sariyer", "WEOJNFDLS");
        User user1 = new User("Mihriban", "Kocabiyik", 32, "FEMALE", "Kadikoy", "SDAFHDUFIN");
        User user2 = new User("Mefkure", "Cambaz", 24, "MALE", "Besiktas", "jfhihqjkSD");

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

    private static void setExampleMessages()
    {
        Message msg1 = new Message(userSet.get(0), userSet.get(1), "Hi Mihriban!", 23122016);
        Message msg2 = new Message(userSet.get(1), userSet.get(0), "Hello Mahmut!", 23122016);

        Message msg3 = new Message(userSet.get(0), userSet.get(2), "Hello Mefkure!", 23122016);
        Message msg4 = new Message(userSet.get(2), userSet.get(0), "I hv a byfrnd!", 23122016);

        messageSet.add(msg1);
        messageSet.add(msg2);
        messageSet.add(msg3);
        messageSet.add(msg4);
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
