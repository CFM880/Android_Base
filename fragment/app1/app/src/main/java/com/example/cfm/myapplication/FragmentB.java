package com.example.cfm.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by cfm on 16-1-22.
 */
public class FragmentB extends Fragment {
    TextView textView;
    String data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_b, container, false );

        if (savedInstanceState == null){

        } else {
            data = savedInstanceState.getString("text");
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(data);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView = (TextView) getActivity().findViewById(R.id.textView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("text", data);


    }

    public void changeData(String data){
        this.data = data;
        textView.setText(data);
    }
}
