package com.icu.yankiinsel.icu;

import android.content.Context;
import android.widget.ImageView;

import com.icu.yankiinsel.icu.Model.Gender;
import com.icu.yankiinsel.icu.Model.Message;
import com.icu.yankiinsel.icu.Model.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final List<User> userSet;
    private static final List<Message> messageSet;

    static
    {
        userSet = new ArrayList<>();
        setExampleUsers();
        messageSet = new ArrayList<>();
        setExampleMessages();
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

        User user1 = new User("Mihriban", "Kocabiyik", 32, Gender.FEMALE, "Kadikoy");
        User user2 = new User("Mefkure", "Cambaz", 24, Gender.FEMALE, "Besiktas");

        user1.imageName = "girl1";
        user2.imageName = "girl2";

        userSet.add(user1);
        userSet.add(user2);
    }

    private static void setExampleMessages()
    {
        Message msg1 = new Message(userSet.get(0), userSet.get(1), "Hello dear friend!", 23122016);
        Message msg2 = new Message(userSet.get(1), userSet.get(0), "Hello Ancuez!", 23122016);

        messageSet.add(msg1);
        messageSet.add(msg2);
    }

    public static List<Message> getExampleMessages()
    {
        return messageSet;
    }

    public static List<User> getExampleUsers()
    {
        return userSet;
    }
}
