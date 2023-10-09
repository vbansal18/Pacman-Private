package com.example.datencechatbotapp.api

import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ChatbotApi {
    @Multipart
    @POST("/uploadAnswers?userId=sanepike")
    suspend fun uploadAnswers(
        @Part filePart: MultipartBody.Part
    ) : Response<JSONObject>

    @Multipart
    @POST("/uploadFile?userId=sanepike")
    suspend fun uploadPdf(
        @Part filePart: MultipartBody.Part
    ) : Response<JSONObject>

    @Multipart
    @POST("/changeProfilePicture?userId=sanepike")
    suspend fun changeProfilePicture(
        @Part filePart: MultipartBody.Part
    ) : Response<JSONObject>


    @POST("/uploadLink?userId=sanepike")
    suspend fun uploadLink(link : JSONObject)

    @POST("/changeUsername?userId=sanepike")
    suspend fun changeUsername(username : JSONObject)





    @GET("/getAllCases?userId=sanepike")
    suspend fun getAllCases(): Response<JSONObject>

    @GET("/getUsername?userId=sanepike")
    suspend fun getUsername(): Response<JSONObject>

    @GET("/getProfilePicture?userId=sanepike")
    suspend fun getProfilePicture(): Response<HTTP>
}