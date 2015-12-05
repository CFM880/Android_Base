package com.example.cfm.ch02_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    File currentParent;
    File[] currentFiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);
        textView = (TextView)findViewById(R.id.path);

        File root = new File("/");
        if (root.exists()){
            currentParent = root;
            currentFiles = root.listFiles();
            inflateListView(currentFiles);
        }

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (currentFiles[position].isFile()) return;
                File[] tmp = currentFiles[position].listFiles();
                if (tmp == null || tmp.length == 0){
                    Toast.makeText(MainActivity.this, "当前路径不可访问或该路径下没有文件",Toast.LENGTH_LONG).show();
                } else{
                    currentParent = currentFiles[position];
                    currentFiles = tmp;
                    inflateListView(currentFiles);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button parent = (Button)findViewById(R.id.parent);

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (!currentParent.getCanonicalPath().equals("/")){
                        currentParent = currentParent.getParentFile();
                        currentFiles = currentParent.listFiles();
                        inflateListView(currentFiles);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void inflateListView(File[] files){
        List<Map<String, Object>> listIterms = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < files.length; i++){
            Map<String,Object> listIterm = new HashMap<String, Object>();
            if (files[i].isDirectory()){
                listIterm.put("icon", R.drawable.ic_folder_black_48dp);

            }else {
                listIterm.put("icon", R.drawable.ic_attachment_black_48dp);
            }
            listIterm.put("fileName",files[i].getName());
            listIterms.add(listIterm);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listIterms,R.layout.line, new String[]{"icon",
        "fileName"},new int[]{R.id.icon, R.id.file_name});
        listView.setAdapter(simpleAdapter);
        try {
            textView.setText("当前路径位：" + currentParent.getCanonicalPath());
        } catch (IOException e){
            e.printStackTrace();
        }


    }
}
