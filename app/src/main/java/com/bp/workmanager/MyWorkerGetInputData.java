package com.bp.workmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorkerGetInputData extends Worker {
    public static final String KEY_DATA_OUTPUT = "key_task_output";

    public MyWorkerGetInputData(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Data data = getInputData();  // you get the data here from activity PassData request
        String desc = data.getString(ActivityPassData.KEY_TASK_DESC);
        displayNotification("notification from worker get data", desc);

        Data data1 = new Data.Builder()
                .putString(KEY_DATA_OUTPUT, "your task finished -- string from output")
                .build();
        // setOutputData(data1);  in previous version
        return Result.success(data1);
    }

    private void displayNotification(String title, String desc) {

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("my_channel_id", "my_channel_name", NotificationManager.IMPORTANCE_DEFAULT);
            assert manager != null;
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "my_channel_id")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(desc)
                .setContentTitle(title);

        Notification notification = builder.build();

        assert manager != null;
        manager.notify(2, notification);

    }
}
