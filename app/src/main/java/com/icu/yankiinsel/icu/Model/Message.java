package com.icu.yankiinsel.icu.Model;

public class Message {
    String message;
    long createdAt;
    User sender;
    User reciever;

    public Message(User sender, User reciever, String message, long createdAt)
    {
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        this.createdAt = createdAt;
    }

    public User getSender()
    {
        return sender;
    }

    public User getReciever()
    {
        return reciever;
    }

    public long getCreatedAt()
    {
        return createdAt;
    }

    public String getMessage() {return message;}
}