package com.example.dell.cc_task.view;

import android.content.Context;
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
import com.example.dell.cc_task.model.adapter.EndlessRecyclerOnScrollListener;
import com.example.dell.cc_task.model.adapter.FavQuesAdapter;
import com.example.dell.cc_task.model.adapter.MyAdapter;
import com.example.dell.cc_task.model.api.NetworkApiGenerator;
import com.example.dell.cc_task.model.api.ServiceInterface;
import com.example.dell.cc_task.model.pojo.Items;
import com.example.dell.cc_task.model.pojo.Questions;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fav_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fav_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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


    public Fav_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fav_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fav_Fragment newInstance(String param1, String param2) {
        Fav_Fragment fragment = new Fav_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

       /* //get total like from bundle
        String totoal_like = getArguments().getString("total_like");
        Log.d("Like from MC =",totoal_like);
        //Retreive data from Shared preference
        SharedPreferences pref = getActivity().getPreferences(0);
        for(int i=0;i<Integer.parseInt(totoal_like);i++)
        {
            //use i value as Key ;
            int mid=i+1;
            //convert i to String as Key for SP
            String sid= String.valueOf(mid);
            //get data from SP for key "sid"
            String user_id = pref.getString(sid, "default link");
            Log.d("key and  d user id =",mid+""+user_id);
        } */


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


//Hit API
        inflate_RecyclerView();

    }


    //Hit API an inflte Recycler View

    //
    public void inflate_RecyclerView()

    {                serviceInterface = NetworkApiGenerator.createService(ServiceInterface.class);



        serviceInterface.getUnansweresandroidquestions("VZhcpZM*4qY7QhxpPc7OYw((","stackoverflow.com","android","desc","votes", new Callback<Questions>() {
            @Override
            public void success(Questions questions, Response response)
            {

                // Owner owner=new Owner();
                Log.d("URL", response.getUrl());
                Log.d("BODY", response.getBody().toString());

                //fetcch array objects
                items=questions.getItems();

                //convert Array into ArrayList
                ArrayList<Items> arrayList = new ArrayList<Items>(Arrays.asList(items));

                // send listner interface object(this) to adapter along with context and listner
                quesAdapter = new FavQuesAdapter(getActivity(),arrayList,Fav_Fragment.this);

                // finaly set into adapter
                recyclerView.setAdapter(quesAdapter);

            /*   // print on log...
            List<Item> items=questions.getItems();
                for(int i = 0; i < items.size(); i++) {
                    Log.d("Item data", items.get(i).getTitle());
                    //System.out.println(items.get(i).getLink());
                } */
            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.d("API error", error.getMessage());
            }
        });





    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    public void onRowClicked(int position) {

    }

    @Override
    public void onViewClicked(View v, int position) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
