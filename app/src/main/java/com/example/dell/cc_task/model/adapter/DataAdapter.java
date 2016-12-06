package com.example.dell.cc_task.model.adapter;

/**
 * Created by DELL on 12/6/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.cc_task.R;
import com.example.dell.cc_task.model.pojo.AndroidVersion;

import java.util.ArrayList;

//We pass Androidversion model class as ArrayList in constructor.
// We obtain the Android version name, version number,
// API level from the AndroidVersion object using the getter methods and set it to TextView widgets.
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<AndroidVersion> android;

    public DataAdapter(ArrayList<AndroidVersion> android) {
        this.android = android;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ques_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

       // viewHolder.tv_name.setText(android.get(i).getName());
        //viewHolder.tv_version.setText(android.get(i).getVer());
        //viewHolder.tv_api_level.setText(android.get(i).getApi());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level;
        public ViewHolder(View view) {
            super(view);

           // tv_name = (TextView)view.findViewById(R.id.tv_name);
            //tv_version = (TextView)view.findViewById(R.id.tv_version);
            //tv_api_level = (TextView)view.findViewById(R.id.tv_api_level);

        }
    }

}
