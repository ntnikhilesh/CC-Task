package com.example.dell.cc_task.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dell.cc_task.R;
import com.example.dell.cc_task.controller.RequestInterface;
import com.example.dell.cc_task.model.adapter.DataAdapter;
import com.example.dell.cc_task.model.pojo.AndroidVersion;
import com.example.dell.cc_task.model.pojo.JSONResponse;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<AndroidVersion> data;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }
    private void loadJSON(){

        //initialize Retrofit and carry out network operations
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //RequestInterface object is created using
        RequestInterface request = retrofit.create(RequestInterface.class);

        //  To carry out a asyncronous request Call object should be created from RequestInterface object by calling getJSON() method.
        Call<JSONResponse> call = request.getJSON();

        //The Asyncronous request is executed by calling enqueue() method on the call object.
        // If the request is success and response is received onResponse() callback method is executed.
        // The JSONResponse object is obtained by calling body() method on the Response object.
        // From the JSONResponse object we obtain the AndroidVersion array object.
        // We convert the AndroidVersion array object to ArrayList.
        // Finally a new DataAdapter object is created and set to RecyclerView using setAdapter() method.
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new DataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
