package com.example.dell.cc_task.model.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.cc_task.R;
import com.example.dell.cc_task.controller.RecyclerViewClickListener;
import com.example.dell.cc_task.model.pojo.Items;
import com.example.dell.cc_task.model.pojo.Owner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DELL on 12/9/2016.
 */

public class FavQuesAdapter extends RecyclerView.Adapter<FavQuesAdapter.ViewHolder> {
    private ArrayList<Items> mDataset;
    int position1;
    String all_tags,mtags="",profile_img="";
    private Context context;
    int item_flag;

    //handle click event by interface
    private RecyclerViewClickListener listener;
    // Provide a suitable constructor (depends on the kind of dataset)
    //getting context and data from Main Activity
    public FavQuesAdapter(Context applicationContext, ArrayList<Items> myDataset, RecyclerViewClickListener listener){
        this.context=applicationContext;
        mDataset = myDataset;
        this.listener = listener;

    }



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_ques;
        public TextView tv_ques_rating;
        public TextView tv_ques_tags;
        public ImageView iv_profile_image;
        public Button button_like;
        public Button button_share;


        public ViewHolder(View itemView, final RecyclerViewClickListener listener) {

            super(itemView);
            tv_ques = (TextView) itemView.findViewById(R.id.tv_ques);
            tv_ques_rating = (TextView) itemView.findViewById(R.id.tv_ques_rating);
            tv_ques_tags = (TextView) itemView.findViewById(R.id.tv_ques_tag);
            iv_profile_image=(ImageView)itemView.findViewById(R.id.iv_profile_img);
            button_like=(Button) itemView.findViewById(R.id.button_like);
            button_share=(Button) itemView.findViewById(R.id.button_share);
            button_like.setVisibility(View.INVISIBLE);
            item_flag=0;

            // handle click event

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("row clicked","");
                    if(listener != null)
                        listener.onRowClicked(getAdapterPosition());
                }
            });

            tv_ques.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Ques","");
                    if(listener != null)
                        listener.onViewClicked(v, getAdapterPosition());
                }
            });

            button_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Share clicked","");
                    if(listener != null)
                        listener.onViewClicked(v, getAdapterPosition());
                }
            });



        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FavQuesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_row, parent, false);

        //set listner
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v,listener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        position1=position;


        //get total like from bundle


        //String totoal_like = getArguments().getString("total_like");




        //Set Values to List Items
        holder.tv_ques.setText(mDataset.get(position).getTitle());
        getTags();
        getImage();
        Picasso.with(context).load(mDataset.get(position).getOwner().getProfile_image()).resize(120, 60).into(holder.iv_profile_image);

        holder.tv_ques_tags.setText(mtags);
        holder.tv_ques_rating.setText("Rating: " + mDataset.get(position).getScore().toString());



    }


    //fetch tags of individual ques
    public void getTags()
    {
        mtags="";
        String[] tag_list=mDataset.get(position1).getTags();
        for (int j=0;j<tag_list.length;j++)
        {
            Log.d("Tag hub"+j,tag_list[j]);
            all_tags=tag_list[j];
            mtags=mtags.concat("# "+all_tags+" , ");
        }
    }

    //get profile image
    public void getImage()
    {
        Owner owner=mDataset.get(position1).getOwner();
        profile_img=owner.getProfile_image();
        if (profile_img==null) {

            Log.d("No image for item " + position1,"");

        }
        else
        {
            Log.d("Profile image" + position1, profile_img);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
