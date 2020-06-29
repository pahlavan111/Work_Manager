package com.bp.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ActivityPassData extends AppCompatActivity {

    public static final String KEY_TASK_DESC = "key_task_description";

    Button btn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_data);
        btn = findViewById(R.id.btn_pass_data);
        txt = findViewById(R.id.txt_show_result);

        Data data= new Data.Builder()  //AndroidX.Data
                .putString(KEY_TASK_DESC,"hey I am sending the work data")  // you can but more component
                .build();
    }
}