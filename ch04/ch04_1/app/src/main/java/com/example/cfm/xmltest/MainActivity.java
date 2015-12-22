package com.example.cfm.xmltest;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        findViewById(R.id.bn_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = 0;

                StringBuilder sb = new StringBuilder("");

                Resources r = getResources();

                XmlResourceParser xrp = r.getXml(R.xml.tvs_epg);

                try {

                    while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
                        if (xrp.getEventType() == XmlResourceParser.START_TAG){
                            String pkg = xrp.getName();
                            if (pkg.equals("package")){
                                counter ++;
                                sb.append("节目分类"+":");

                                sb.append(xrp.getAttributeValue(0) + "\n\n");
                            }

                            if (pkg.equals("CHANNEL")){
                                counter ++;
                                sb.append("CHANNEL");
                                sb.append(xrp.getAttributeValue(0) + "\t"  + "\t" + xrp.getAttributeValue(2) + "\t\t" + xrp.getAttributeValue(3) + "\n\n");
                            }



                        } else if (xrp.getEventType() == XmlPullParser.END_TAG) {
                        } else if (xrp.getEventType() == XmlPullParser.TEXT) {
                        }
                        //下一个标签
                        xrp.next();
                    }
                    textView.setText(sb.toString());

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });




    }
}
