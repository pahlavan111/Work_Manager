package com.bp.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.impl.WorkerWrapper;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(getApplicationContext()).enqueue(request);

            }
        });

        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(request.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                String data = workInfo.getState().name();
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });



    }


}