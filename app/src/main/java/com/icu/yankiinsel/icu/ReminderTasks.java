package com.icu.yankiinsel.icu;


import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

public class ReminderTasks{
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    public static final String ACTION_MESSAGE_REMINDER = "message-notification";

    public static void executeTask(Context context, String action){
        if (ACTION_DISMISS_NOTIFICATION.equals(action)){
            NotificationUtils.clearAllNotifications(context);
        } else if (ACTION_MESSAGE_REMINDER.equals(action)) {
            NotificationUtils.remindUserNewMessages(context);
            Log.v("NOTIFICATION ICU", "SENT SUCCESSFULLY");
        }
    }
}

