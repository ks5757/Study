package com.example.student.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ServiceActivity extends BaseActivity {

    private EditText mEditStopCount;
    private Button startButton;
    public static final String EXTRA_STOP_TIME = "EXTRA_STOP_TIME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mEditStopCount = (EditText) findViewById(R.id.edit_stop_count);
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this,
                        StartService.class);

               intent.putExtra(EXTRA_STOP_TIME, mEditStopCount.getText().toString());

               startService(intent);
            }
        });

    }



}
