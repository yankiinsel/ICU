package com.icu.yankiinsel.icu;


import android.app.NotificationManager;
import android.content.Context;

public class ReminderTasks{
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    public static void executeTask(Context context, String action){
        if (ACTION_DISMISS_NOTIFICATION.equals(action)){
            NotificationUtils.clearAllNotifications(context);
        }
    }
}

