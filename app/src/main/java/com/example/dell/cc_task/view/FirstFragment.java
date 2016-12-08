package com.example.dell.cc_task.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dell.cc_task.R;
import com.example.dell.cc_task.controller.RecyclerViewClickListener;
import com.example.dell.cc_task.model.adapter.MyAdapter;
import com.example.dell.cc_task.model.api.NetworkApiGenerator;
import com.example.dell.cc_task.model.api.ServiceInterface;

import com.example.dell.cc_task.model.pojo.Items;
import com.example.dell.cc_task.model.pojo.Questions;
import com.example.dell.cc_task.model.pojo.TagHub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment implements RecyclerViewClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    // fragment var
    private RecyclerView recyclerView;
    private ArrayList<Items> data;
    private MyAdapter adapter;
    SearchView searchView;
    FrameLayout fl;
    private RecyclerViewClickListener listener;
    Items[] items;
    String mtag="android";
    int flag1 =0;
    int flag2=0;
    String order,sort;

    private ServiceInterface serviceInterface;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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
         fl=(FrameLayout) inflater.inflate(R.layout.fragment_first, container, false);
        mtag = getArguments().getString("tag");
        order="desc";
        sort="votes";
        flag2=Integer.parseInt(getArguments().getString("flag2"));
        Log.d("user tag and flag : ",mtag+"\n"+flag2);
        initViews();

        return fl;
    }

    //initialize views
    private void initViews(){
        recyclerView = (RecyclerView)fl.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        if(flag2==0)
        {
            inflate_RecyclerView();
        }
        else if (flag2==1)
        {
            loadTagJSON();
        }


    }


    //

    //
    public void inflate_RecyclerView()

    {                serviceInterface = NetworkApiGenerator.createService(ServiceInterface.class);



        serviceInterface.getUnansweresandroidquestions("VZhcpZM*4qY7QhxpPc7OYw((","stackoverflow.com",mtag,order,sort, new Callback<Questions>() {
            @Override
            public void success(Questions questions, Response response)
            {
                flag1=0;
                // Owner owner=new Owner();
                Log.d("URL", response.getUrl());
                Log.d("BODY", response.getBody().toString());

                //fetcch array objects
                items=questions.getItems();

                //convert Array into ArrayList
                ArrayList<Items> arrayList = new ArrayList<Items>(Arrays.asList(items));

                // send listner interface object(this) to adapter along with context and listner
                adapter = new MyAdapter(getActivity(),arrayList,FirstFragment.this);

                // finaly set into adapter
                recyclerView.setAdapter(adapter);

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

    //loadTagJson

    public void loadTagJSON()

    {                serviceInterface = NetworkApiGenerator.createService(ServiceInterface.class);



        serviceInterface.getAllTags("VZhcpZM*4qY7QhxpPc7OYw((","stackoverflow.com", new Callback<TagHub>() {

            @Override
            public void success(TagHub tagHub, Response response)
            {
                items=tagHub.getItems();
                for(int i = 0; i < items.length; i++) {
                    Log.d("Tag data", items[i].getName());

                    //if tag exist then list itme will update
                    if (mtag.equals(items[i].getName()))
                    {
                        flag1 =1;
                        inflate_RecyclerView();
                    }

                }
                //if not exist then default tag(android) will update
                if (flag1 ==0)
                {
                    mtag="android";
                    inflate_RecyclerView();
                    Toast.makeText(getActivity(),"Tag not exist.....",Toast.LENGTH_LONG).show();
                }
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
