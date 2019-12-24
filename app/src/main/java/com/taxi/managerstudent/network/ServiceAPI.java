package com.taxi.managerstudent.network;
import com.taxi.managerstudent.models.StudentResponse;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAPI {

    @POST("/api/student/do-search")
    Call<List<StudentResponse>> getStudent(@Body RequestBody responseBody);

    @POST("/api/student/save")
    Call<List<StudentResponse>> addStudent(@Body RequestBody responseBody);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json;charset=utf-8"
    })
    @POST("/api/sandbox/user/login")
    Call<ResponseBody> loginAccountKit(@Body RequestBody responseBody);

    @GET("/api/sandbox/user/logout")
    Call<ResponseBody> logout(@Query("sid") String sessionKey);
}
