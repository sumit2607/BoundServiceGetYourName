package com.example.boundservicegetyourname;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mBtnStartService, mBtnStopService, mBtnGetName;
    private TextView mTvName;
    private GetNameService getNameService;
    private  Boolean ServiceStarted = false;
    Intent intent;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GetNameService.ServiceBinder serviceBinder = (GetNameService.ServiceBinder) service;
            getNameService = serviceBinder.getNameService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
    }

    private void initviews() {
    mBtnStartService = findViewById(R.id.btnStartService);
    mBtnStopService = findViewById(R.id.btnStopService);
    mBtnGetName = findViewById(R.id.btnGetName);
     mTvName = findViewById(R.id.tvMyName);
      mBtnStartService.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
         intent = new Intent(MainActivity.this,GetNameService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
        ServiceStarted = true;
    }
});
               mBtnGetName.setOnClickListener(new View.OnClickListener() {
         @Override
    public void onClick(View v) {
       if (ServiceStarted)
         mTvName.setText(getNameService.getName());
        }
     });
       mBtnStopService.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mTvName.setText("text will be updated");
        stopService(intent);
        ServiceStarted = false;
    }
});
    }
}