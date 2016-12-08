package com.example.dell.cc_task.model.api;

import com.example.dell.cc_task.model.pojo.Questions;
import com.example.dell.cc_task.model.pojo.TagHub;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by hp on 20-02-2016.
 */
public interface ServiceInterface {

    @GET("/questions/unanswered")
    void getUnansweresandroidquestions(@Query("api_key") String api_key, @Query ("site") String site,@Query("tagged") String tagged,@Query("order") String order,@Query("sort") String sort,Callback<Questions> responseCallback);

    @GET("/tags")
    void getAllTags(@Query("api_key") String api_key, @Query ("site") String site,Callback<TagHub> responseCallback);


}
