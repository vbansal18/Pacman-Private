package com.example.datencechatbotapp.api

import com.example.datencechatbotapp.models.GetAllCasesModel
import com.example.datencechatbotapp.models.GetConsultancyResponse
import com.example.datencechatbotapp.models.Session
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ChatbotApi {
    @POST("uploadAnswers?userId=sanepike")
    suspend fun uploadAnswers(@Body answers : JsonObject): Response<JsonObject>

    @Multipart
    @POST("uploadFile?userId=sanepike")
    suspend fun uploadPdf(
        @Part filePart: MultipartBody.Part
    ): Response<JSONObject>

    @Multipart
    @POST("changeProfilePicture?userId=sanepike")
    suspend fun changeProfilePicture(
        @Part image: MultipartBody.Part
    ): Response<JsonObject>

    @POST("uploadLink?userId=sanepike")
    suspend fun uploadLink(@Body link: JsonObject) : Response<JsonObject>

    @POST("changeUsername?userId=sanepike")
    suspend fun changeUsername(@Body username: JsonObject) : Response<JsonObject>

    @GET("getAllCases?userId=sanepike")
    suspend fun getAllCases(): Response<GetAllCasesModel>

    @GET("getUsername?userId=sanepike")
    suspend fun getUsername(): Response<JsonObject>

    @GET("getResponse?userId=sanepike")
    suspend fun getResponse(): Response<GetConsultancyResponse>

    @GET("getLeads?userId=sanepike")
    suspend fun getLeads(): Response<JsonObject>

//    @GET("getProfilePicture?userId=sanepike")
//    suspend fun getProfilePicture(): Call<ResponseBody>
}

interface ImageServiceApi{
    @GET("getProfilePicture?userId=sanepike")
    suspend fun getProfilePicture(): Call<ResponseBody>

}