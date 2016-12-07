package com.example.dell.cc_task.model.adapter;

/**
 * Created by DELL on 12/7/2016.
 */

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.cc_task.R;
import com.example.dell.cc_task.model.pojo.Item;
import com.example.dell.cc_task.model.pojo.Owner;
import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Item> mDataset;
    int position1;
    String all_tags,mtags="",profile_img;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_ques;
        public TextView tv_ques_rating;
        public TextView tv_ques_tags;


        public ViewHolder(View v) {

            super(v);
            tv_ques = (TextView) v.findViewById(R.id.tv_ques);
            tv_ques_rating = (TextView) v.findViewById(R.id.tv_ques_rating);
            tv_ques_tags = (TextView) v.findViewById(R.id.tv_ques_tag);
        }
    }
/*
   public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    } */
/*
    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }*/

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Item> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
           position1=position;

            holder.tv_ques.setText(mDataset.get(position).getTitle());
            getTags();
           // getImage();
            holder.tv_ques_tags.setText(mtags);
            holder.tv_ques_rating.setText("Rating: " + mDataset.get(position).getScore().toString());

    }

    //fetch tags of individual ques
    public void getTags()
    {
        mtags="";
        List<String> tag_list=mDataset.get(position1).getTags();
        for (int j=0;j<tag_list.size();j++)
        {
            Log.d("Tag hub"+j,tag_list.get(j));
            all_tags=tag_list.get(j);
            mtags=mtags.concat("# "+all_tags+" , ");
        }
    }

    //get profile image
    public void getImage()
    {
        Owner owner=mDataset.get(position1).getOwner();
        profile_img=owner.getLink();
        if (profile_img!=null) {

            Log.d("Profile image" + position1, profile_img);
        }
        else
        {
            Log.d("No image for item " + position1,"");
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}