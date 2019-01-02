package com.icu.yankiinsel.icu;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.icu.yankiinsel.icu.Activities.HomeActivity;
import com.icu.yankiinsel.icu.Activities.MatchesActivity;
import com.icu.yankiinsel.icu.Activities.MessageListActivity;

public class NotificationUtils {

    private static final int NEW_MATCHES_NOTIFICATION_ID = 1138;
    private static final String NEW_MATCHES_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";

    private static final int ACTION_MATCHES_PENDING_INTENT_ID = 1;
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;

    private static final int MATCHES_REMINDER_PENDING_INTENT_ID = 3417;

    private static PendingIntent matchesIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MatchesActivity.class);
        return PendingIntent.getActivity(
                context,
                MATCHES_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static PendingIntent messagesIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MessageListActivity.class);
        return PendingIntent.getActivity(
                context,
                MATCHES_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void remindUserNewMessages(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel( NEW_MATCHES_NOTIFICATION_CHANNEL_ID,
                    "Primary",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NEW_MATCHES_NOTIFICATION_CHANNEL_ID);
        notificationBuilder
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_icu)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getResources().getString(R.string.new_messages_title))
                .setContentText(context.getResources().getString(R.string.new_messages))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getResources().getString(R.string.new_messages)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(messagesIntent(context))
                .addAction(checkMessagesAction(context))
                .addAction(ignoreReminderAction(context))
                .setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(NEW_MATCHES_NOTIFICATION_ID, notificationBuilder.build());
    }

    public static void remindUserNewMatches(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel( NEW_MATCHES_NOTIFICATION_CHANNEL_ID,
                    "Primary",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NEW_MATCHES_NOTIFICATION_CHANNEL_ID);
        notificationBuilder
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_icu)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getResources().getString(R.string.new_matches_title))
                .setContentText(context.getResources().getString(R.string.new_matches))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getResources().getString(R.string.new_matches)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(matchesIntent(context))
                .addAction(checkMatchesAction(context))
                .addAction(ignoreReminderAction(context))
                .setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(NEW_MATCHES_NOTIFICATION_ID, notificationBuilder.build());
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_icu);
        return largeIcon;
    }

    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        Log.v("NotificationsICU","cancel All Called");
    }

    private static NotificationCompat.Action checkMatchesAction(Context context) {
        NotificationCompat.Action checkMatchesAction = new NotificationCompat.Action(R.drawable.ic_icu,
                "Check Matches!",
                matchesIntent(context));
        return checkMatchesAction;
    }

    private static NotificationCompat.Action checkMessagesAction(Context context) {
        NotificationCompat.Action checkMessagesAction = new NotificationCompat.Action(R.drawable.ic_icu,
                "Check Messages!",
                messagesIntent(context));
        return checkMessagesAction;
    }

    private static NotificationCompat.Action ignoreReminderAction(Context context) {
        Intent ignoreReminderIntent = new Intent(context,
                ReminderIntentService.class);
        ignoreReminderIntent.setAction(ReminderTasks.ACTION_DISMISS_NOTIFICATION);
        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_icu,
                "Ignore",
                ignoreReminderPendingIntent);
        return ignoreReminderAction;
    }
}


