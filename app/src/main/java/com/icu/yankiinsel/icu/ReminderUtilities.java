package com.icu.yankiinsel.icu;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

public class ReminderUtilities {

    private static final int REMINDER_INTERVAL_SECONDS = 60;
    private static final String REMINDER_JOB_TAG = "message_reminder_tag";
    synchronized public static void scheduleMessageReminder(@NonNull final Context context) {

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        Job constraintReminderJob = dispatcher.newJobBuilder()
                .setService(ICUFirebaseJobService.class)
                .setTag(REMINDER_JOB_TAG)
                .setTrigger(Trigger.executionWindow(0, REMINDER_INTERVAL_SECONDS))
                .build();
        dispatcher.mustSchedule(constraintReminderJob);

    }
}