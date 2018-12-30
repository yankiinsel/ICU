package com.icu.yankiinsel.icu.Model;

public class Message {
    String message;
    User user;

    public Message(User user, String message)
    {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {return message;}
}