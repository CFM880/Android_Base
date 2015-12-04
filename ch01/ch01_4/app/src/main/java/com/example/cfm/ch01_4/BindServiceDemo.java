package com.example.cfm.ch01_4;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BindServiceDemo extends AppCompatActivity {
    Button bind, unbind, getSerivceStatus;
    BindService.MyBinder binder;

    // define a ServiceConnecttion Object
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("--- Service Connected ---");
            binder = (BindService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("--- Service Disconnected ---");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = (Button) findViewById(R.id.bind);
        unbind = (Button)findViewById(R.id.unbind);
        getSerivceStatus = (Button)findViewById(R.id.getServiceStatus);

        final Intent intent = new Intent(this, BindService.class);
        intent.setAction("com.example.service.BIND_SERVICE");
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });

        getSerivceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BindServiceDemo.this, "Service的count值为："+binder.getCount(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
