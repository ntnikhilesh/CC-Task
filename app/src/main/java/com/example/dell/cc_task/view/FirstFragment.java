package com.example.dell.cc_task.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.example.dell.cc_task.model.adapter.EndlessRecyclerOnScrollListener;
import com.example.dell.cc_task.model.adapter.MyAdapter;
import com.example.dell.cc_task.model.api.NetworkApiGenerator;
import com.example.dell.cc_task.model.api.ServiceInterface;

import com.example.dell.cc_task.model.pojo.Items;
import com.example.dell.cc_task.model.pojo.Questions;
import com.example.dell.cc_task.model.pojo.TagHub;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


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
    static int total_like;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    onSomeEventListener1 someEventListener1;



    private ServiceInterface serviceInterface;

    public FirstFragment() {
        // Required empty public constructor
    }


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
        //get arguments from Bundle
        mtag = getArguments().getString("tag");
        order="desc";
        sort="votes";
        flag2=Integer.parseInt(getArguments().getString("flag2"));
        order = getArguments().getString("order");
        sort = getArguments().getString("sort");
        total_like =0;
        Log.d("Value from MC ",mtag+"\n"+flag2+"\n"+order+"\n"+sort);

        //Ininitalze view of Recycle View
        initViews();

        return fl;
    }

    //initialize views
    private void initViews(){
        recyclerView = (RecyclerView)fl.findViewById(R.id.card_recycler_view);
       // recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //Handle pagination
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                // do something...
                if(flag2==0)
                {
                    inflate_RecyclerView();
                }
                else if (flag2==1)
                {
                    loadTagJSON();
                }
            }
        });

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

    //inflate Recycle view items
    public void inflate_RecyclerView()

    {
        serviceInterface = NetworkApiGenerator.createService(ServiceInterface.class);

        serviceInterface.getUnansweresandroidquestions("VZhcpZM*4qY7QhxpPc7OYw((","stackoverflow.com",mtag,order,sort, new Callback<Questions>() {
            @Override
            public void success(Questions questions, Response response)
            {
                flag1=0;


                //fetcch array objects
                items=questions.getItems();

                //convert Array into ArrayList
                ArrayList<Items> arrayList = new ArrayList<Items>(Arrays.asList(items));

                // send listner interface object(this) to adapter along with context and listner
                adapter = new MyAdapter(getActivity(),arrayList,FirstFragment.this);

                // finaly set into adapter
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.d("API error", error.getMessage());
            }
        });




    }

    //Update Recycle View on the basis of short parameter(Activity , Creation , Votes , Asc , Desc )

    public void loadTagJSON()

    {
        serviceInterface = NetworkApiGenerator.createService(ServiceInterface.class);

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
                //if tag does not exist then default tag(android) will use
                if (flag1 ==0)
                {
                    mtag="android";
                    inflate_RecyclerView();
                   // Toast.makeText(getActivity(),"Tag does not exist.....",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.d("API error", error.getMessage());
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
        try {
           someEventListener1 = (onSomeEventListener1) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener1");
        }
    }

    //Interface to send data to Main Activity
    public interface onSomeEventListener1 {
        public void someEvent(int total_like);
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
        if (v.getId()==R.id.button_like)
        {
            Log.d("Like button clicked", items[position].getLink());
            total_like++;//will use total like as total like and key for shared pref of that id
            String mtotal_like= Integer.toString(total_like);
            String uid=items[position].getQuestion_id();
            Log.d("total like d id =", total_like +""+uid);

            //Save user ID in shared preference
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
           // SharedPreferences pref = getActivity().getPreferences(0);
            SharedPreferences.Editor edt = pref.edit();
            edt.putString(mtotal_like, uid);
            //edt.putString(mtotal_like, "total_like");
            edt.commit();

            Log.d("total sp =", String.valueOf(pref.getAll().size()));
            //Sending total count to Main Activity
            someEventListener1.someEvent(total_like);


        }

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
