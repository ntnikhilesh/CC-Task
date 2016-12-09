package com.example.dell.cc_task.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dell.cc_task.R;
import com.example.dell.cc_task.controller.RecyclerViewClickListener;
import com.example.dell.cc_task.model.adapter.FavQuesAdapter;
import com.example.dell.cc_task.model.api.NetworkApiGenerator;
import com.example.dell.cc_task.model.api.ServiceInterface;
import com.example.dell.cc_task.model.pojo.FavQuestions;
import com.example.dell.cc_task.model.pojo.Items;
import com.example.dell.cc_task.model.pojo.Questions;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;



public class Fav_Fragment extends Fragment implements RecyclerViewClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private RecyclerView recyclerView;
    FrameLayout fl;
    private ServiceInterface serviceInterface;
    Items[] items;
    private FavQuesAdapter quesAdapter;
    private Integer user_id[];


    public Fav_Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fl=(FrameLayout) inflater.inflate(R.layout.fragment_fav_, container, false);



        //Intialize view of Recycler View
        initViews();
        //Toast.makeText(getActivity(),"ID ="+user_id+" and Total Like ="+totoal_like,Toast.LENGTH_LONG).show();

        return fl;

    }



//Intialize view of ReciclerView
    private void initViews(){
        recyclerView = (RecyclerView)fl.findViewById(R.id.fav_ques_card_recycler_view);
        // recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

//inflate favorite Questions
        inflate_FavQues();


    }


    //In fav ques

    public void inflate_FavQues()

    {
        user_id=new Integer[12];
        for(int i=0;i<12;i++) {
            user_id[i] = 0;
        }
        //Retreive data from Shared preference
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        //getting Total like form Main Activity
        String totoal_like =sharedPreferences.getString("total_like","0");
        Log.d("FAvFrag total like =",totoal_like);
        //  SharedPreferences pref = getActivity().getPreferences(0);
        for(int i=0;i<Integer.parseInt(totoal_like);i++)
        {
            //use i value as Key ;
            int mid=i+1;
            //convert i to String as Key for SP
            String sid= String.valueOf(mid);
            //get data from SP for key "sid"
             user_id[i] = Integer.valueOf(sharedPreferences.getString(sid, "default link"));
            Log.d("Fkey and  d user id =",mid+""+ user_id);


        }




        serviceInterface = NetworkApiGenerator.createService(ServiceInterface.class);

       serviceInterface.getFavQues(user_id[0],user_id[1],user_id[2],user_id[3],user_id[4],user_id[5],user_id[6],user_id[7],user_id[8],user_id[9],user_id[10],user_id[11],"VZhcpZM*4qY7QhxpPc7OYw((","stackoverflow.com","android","desc","votes", new Callback<FavQuestions>() {

            @Override
            public void success(FavQuestions favQuestions, Response response) {
                Log.d("Success FavQues","");
                //Toast.makeText(getActivity(),"from success",Toast.LENGTH_LONG).show();
                //fetcch array objects
                items=favQuestions.getItems();
                //print values in Log
                for(int i = 0; i < items.length; i++) {
                    Log.d("Item data"+i, items[i].getTitle());
                    //Toast.makeText(getActivity(),items[i].getTitle(),Toast.LENGTH_LONG).show();
                    //System.out.println(items.get(i).getLink());
                }

                //convert Array into ArrayList
                ArrayList<Items> arrayList = new ArrayList<Items>(Arrays.asList(items));

                // send listner interface object(this) to adapter along with context and listner
                quesAdapter = new FavQuesAdapter(getActivity(),arrayList,Fav_Fragment.this);

                // finaly set into adapter
                recyclerView.setAdapter(quesAdapter);

            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.d("FQAPI error", error.getMessage());
            }
        });





    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    // handle onClick events of Recyclde view items
    @Override
    public void onRowClicked(int position) {
        Log.d("Row clicked",items[position].getLink());
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(items[position].getLink()));
        getActivity().startActivity(i);


    }

    @Override
    public void onViewClicked(View v, int position) {
        if(v.getId()==R.id.tv_ques) {
            Log.d("List item clicked", items[position].getLink());
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(items[position].getLink()));
            getActivity().startActivity(i);
        }
        if (v.getId()==R.id.button_share)
        {
            Intent i=new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
            i.putExtra(android.content.Intent.EXTRA_TEXT, items[position].getLink());
            startActivity(Intent.createChooser(i,"Share via"));
        }

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
