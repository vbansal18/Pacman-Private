package com.example.datencechatbotapp.api

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
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
import java.util.concurrent.TimeUnit


object RetrofitClient {
    private const val BASE_URL =
        "https://4988-2409-4089-2e0d-6cc4-4cb1-c6f-a0fc-aa23.ngrok-free.app"

    val okhttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okhttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun create(java: Class<ChatbotApi>): ChatbotApi {
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
}