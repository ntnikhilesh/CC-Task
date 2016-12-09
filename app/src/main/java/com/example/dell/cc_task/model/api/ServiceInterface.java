package com.example.dell.cc_task.model.api;

import com.example.dell.cc_task.model.pojo.FavQuestions;
import com.example.dell.cc_task.model.pojo.Questions;
import com.example.dell.cc_task.model.pojo.TagHub;


import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by hp on 20-02-2016.
 */
public interface ServiceInterface {

    @GET("/questions/unanswered")
    void getUnansweresandroidquestions(@Query("api_key") String api_key, @Query ("site") String site,@Query("tagged") String tagged,@Query("order") String order,@Query("sort") String sort,Callback<Questions> responseCallback);

    @GET("/tags")
    void getAllTags(@Query("api_key") String api_key, @Query ("site") String site,Callback<TagHub> responseCallback);

    @GET("/questions/{id1};{id2};{id3};{id4};{id5};{id6};{id7};{id8}{id9};{id10};{id11};{id12}")
    void getFavQues(@Path("id1") int quesId1,@Path("id2") int quesId2,@Path("id3") int quesId3,@Path("id4") int quesId4,@Path("id5") int quesId5,@Path("id6") int quesId6,@Path("id7") int quesId7,@Path("id8") int quesId8,@Path("id9") int quesId9,@Path("id10") int quesId10,@Path("id11") int quesId11,@Path("id12") int quesId12, @Query("api_key") String api_key, @Query ("site") String site, @Query("tagged") String tagged, @Query("order") String order, @Query("sort") String sort, Callback<FavQuestions> responseCallback);


}
