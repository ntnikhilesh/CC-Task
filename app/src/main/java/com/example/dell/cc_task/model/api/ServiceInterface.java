package com.example.dell.cc_task.model.api;

import com.example.dell.cc_task.model.pojo.Questions;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by hp on 20-02-2016.
 */
public interface ServiceInterface {

    @GET("/questions/unanswered")
    void getUnansweresandroidquestions(@Query("api_key") String api_key, @Query("tagged") String tagged, @Query ("site") String site,Callback<Questions> responseCallback);


}
