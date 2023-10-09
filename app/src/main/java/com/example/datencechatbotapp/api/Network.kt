package com.example.datencechatbotapp.api

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.concurrent.TimeUnit


object RetrofitClient {
    private const val BASE_URL =
        "https://fea7-14-139-198-165.ngrok-free.app/"

    fun create(java: Class<ChatbotApi>): ChatbotApi {
        val okhttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor{
                Log.d("URL", it.request().url.toString())
                it.proceed(it.request())

            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ChatbotApi::class.java)
    }
}

class FileUploadViewModel : ViewModel() {
    private val chatbotApi = RetrofitClient.create(ChatbotApi::class.java)

    suspend fun uploadPdfFile(fileUri: Uri, context: Context): Response<JSONObject> {
        val file = createFileDuplicate(context, fileUri)
        val requestFile = file.asRequestBody("application/pdf".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("file", file.name, requestFile)

        return chatbotApi.uploadPdf(filePart)
    }

    fun createFileDuplicate(context: Context, uri: Uri): File {
        val file = File(context.filesDir, "file.pdf")

        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)

        inputStream!!.copyTo(outputStream)

        inputStream.close()

        return file
    }

    suspend fun uploadPdfLink(link : URL): Response<JsonObject> {
        val linkObj = JsonObject()
        linkObj.addProperty("url", link.toString())
        Log.d("LINKPDF", linkObj.toString())
        return chatbotApi.uploadLink(linkObj)
    }


    suspend fun changeUsername(name : String) : Response<JsonObject> {
        val jsonObject = JsonObject()
        jsonObject.addProperty("username", name)
        return chatbotApi.changeUsername(jsonObject)
    }



    suspend fun getAllCases(): Response<JsonObject> {
        return chatbotApi.getAllCases()
    }
    suspend fun getUserName(): Response<JsonObject> {
        return chatbotApi.getUsername()
    }
    suspend fun getProfilePicture(): Response<JsonObject> {
        return chatbotApi.getProfilePicture()
    }
}