package com.example.cfm.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ll_refresh_interval).setOnClickListener(this);
        findViewById(R.id.ll_control_interval).setOnClickListener(this);
        findViewById(R.id.ll_upload_interval).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_refresh_interval:
                setRefreshInterval();
                break;
            case R.id.ll_control_interval:
                setControlInterval();
                break;
            case R.id.ll_upload_interval:
                setUploadInterval();
                break;
        }
    }

    private void setRefreshInterval(){
        View view=View.inflate(this, R.layout.view_config_refresh_interval, null);
        final EditText et_refresh_interval=(EditText)view.findViewById(R.id.et_refresh_interval);
        et_refresh_interval.setText(String.valueOf(ApplicationConfig.getInstance(this).getRefreshInterval()));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setTitle(R.string.refresh_interval).setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApplicationConfig.getInstance(getParent()).setRefreshInterval(
                                Long.parseLong(et_refresh_interval.getText().toString()));
                        ApplicationConfig.getInstance(getParent()).save();
                    }

                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        alertDialog.show();
    }

    private void setControlInterval(){
        View view = View.inflate(this, R.layout.view_config_control_interval, null);
        final EditText et_control_interval = (EditText)view.findViewById(R.id.et_control_interval);
        et_control_interval.setText(String.valueOf(ApplicationConfig.getInstance(this).getControlInterval()));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setTitle(R.string.control_interval).setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApplicationConfig.getInstance(getParent()).setControlInterval(
                                Long.parseLong(et_control_interval.getText().toString()));
                        ApplicationConfig.getInstance(getParent()).save();
                    }

                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        alertDialog.show();
    }

    private void setUploadInterval(){
        View view = View.inflate(this, R.layout.view_config_upload_interval,null);
        final EditText et_upload_interval = (EditText)view.findViewById(R.id.et_upload_interval);
        et_upload_interval.setText(String.valueOf(ApplicationConfig.getInstance(this).getUploadInterval()));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setTitle(R.string.upload_interval).setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApplicationConfig.getInstance(getParent()).setUploadInterval(
                                Long.parseLong(et_upload_interval.getText().toString()));
                        ApplicationConfig.getInstance(getParent()).save();
                    }

                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        alertDialog.show();
    }
}
