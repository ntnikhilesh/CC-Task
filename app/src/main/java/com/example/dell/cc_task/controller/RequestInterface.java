package com.example.dell.cc_task.controller;

/**
 * Created by DELL on 12/6/2016.
 */

//Here we use GET request to obtain JSON data.
// The endpoint is defined using @GET annotation.
// Our request URL is http://api.learn2crack.com/android/jsonandroid,
// where http://api.learn2crack.com is base url and android/jsonandroid is endpoint.

//For our request method getJSON() the JSONResponse object is wrapped in Call object. By using Call,
// the request is made Asynchronous so you need not worry about UI blocking or AsyncTask.
// The JSON response received after making the request it is stored in JSONResponse object.
import com.example.dell.cc_task.model.pojo.JSONResponse;
import com.example.dell.cc_task.model.utilities.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET(Constants.END_POINT)
    Call<JSONResponse> getJSON();
}
