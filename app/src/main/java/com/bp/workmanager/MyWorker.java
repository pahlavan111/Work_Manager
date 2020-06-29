package com.bp.workmanager;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {


    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

    }


    @NonNull
    @Override
    public Result doWork() {

        displayNotification("my notification from worker","work is finished");

        return  Result.success();    // it can be "Result.Retry"  or  "Result.Failure"
    }

    private void displayNotification(String title, String desc){

        NotificationManager manager= (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel("my_channel_id","my_channel_name",NotificationManager.IMPORTANCE_DEFAULT);
            assert manager != null;
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder= new NotificationCompat.Builder(getApplicationContext(),"my_channel_id")
                .setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);

        assert manager != null;
        manager.notify(1,builder.build());


    }
}
