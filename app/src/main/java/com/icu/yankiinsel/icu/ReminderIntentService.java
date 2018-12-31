package com.icu.yankiinsel.icu;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class ReminderIntentService extends IntentService {
    public ReminderIntentService() {
        super("ReminderIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        ReminderTasks.executeTask(this, action);
    }
}