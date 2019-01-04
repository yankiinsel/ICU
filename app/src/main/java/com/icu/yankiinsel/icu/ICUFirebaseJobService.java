package com.icu.yankiinsel.icu;
import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class ICUFirebaseJobService  extends JobService{

    private AsyncTask mBackgroundTask;
    public boolean onStartJob(final JobParameters jobParameters) {
        mBackgroundTask = new AsyncTask() {
            protected Object doInBackground(Object[] params) {
                Context context = ICUFirebaseJobService.this;
                ReminderTasks.executeTask(context, ReminderTasks.ACTION_MESSAGE_REMINDER);
                return null;
            }
            protected void onPostExecute(Object o) {
                ReminderUtilities.scheduleMessageReminder(getApplicationContext());
                jobFinished(jobParameters, false);
            }
        };
        mBackgroundTask.execute();
        return true;
    }
    public boolean onStopJob(JobParameters jobParameters) {
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }
}

