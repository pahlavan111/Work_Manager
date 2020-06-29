package com.bp.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPassData extends AppCompatActivity {

    public static final String KEY_TASK_DESC = "key_task_description";

    Button btn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_data);
        btn = findViewById(R.id.btn_pass);
        txt = findViewById(R.id.txt_show_result);

        Data data= new Data.Builder()  //AndroidX.Worker.Data                           // put it in request
                .putString(KEY_TASK_DESC,"hey I am sending the work data")  // you can but more component
                .build();



        final OneTimeWorkRequest request= new OneTimeWorkRequest.Builder(MyWorkerGetInputData.class)
                .setInputData(data)                 //you pud data in request and receive it in worker class
                .build();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(getApplicationContext()).enqueue(request);
            }
        });

        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(request.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {

                        if (workInfo!=null && workInfo.getState().isFinished()) {

                           // String res = workInfo.getState().name();
                            //txt.append(res+"\n");

                            Data data1=workInfo.getOutputData();
                            String outPut = data1.getString(MyWorkerGetInputData.KEY_DATA_OUTPUT);
                            txt.append(outPut);

                        }
                    }
                });

    }
}