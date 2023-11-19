package com.example.datencechatbotapp.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.datencechatbotapp.models.FeedbackModel
import com.example.datencechatbotapp.models.GetAllCasesModel
import com.example.datencechatbotapp.models.GetConsultancyResponse
import com.example.datencechatbotapp.models.SampleLeads
import com.example.datencechatbotapp.models.Session
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

private const val BASE_URL =
    "https://pacmanbackend.azurewebsites.net/"

object RetrofitClient {
    fun create(): ChatbotApi {

        val okhttpClient = OkHttpClient.Builder()
            .connectTimeout(2000, TimeUnit.SECONDS)
            .writeTimeout(2000, TimeUnit.SECONDS)
            .readTimeout(2000, TimeUnit.SECONDS)
            .addInterceptor {
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
    private val chatbotApi = RetrofitClient.create()

    suspend fun changeProfilePicture(uri: Uri, context: Context): Response<JsonObject> {
        val file = createImageDuplicate(context, uri)
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        return chatbotApi.changeProfilePicture(body)

    }

    fun createImageDuplicate(context: Context, uri: Uri): File {
        val file = File(context.filesDir, "file.jpeg")

        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)

        inputStream!!.copyTo(outputStream)

        inputStream.close()

        return file
    }

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

    suspend fun uploadAnswers(ansObj: JsonObject): Response<JsonObject> {
        Log.d("ANSWERS_", ansObj.toString())
        return chatbotApi.uploadAnswers(ansObj)
    }

    suspend fun uploadPdfLink(link: String): Response<JsonObject> {
        val linkObj = JsonObject()
        linkObj.addProperty("url", link)
        return chatbotApi.uploadLink(linkObj)
    }

    suspend fun changeUsername(name: String): Response<JsonObject> {
        val jsonObject = JsonObject()
        jsonObject.addProperty("username", name)
        return chatbotApi.changeUsername(jsonObject)
    }

    suspend fun feedback(feedbackModel: FeedbackModel): Response<JsonObject> {
        return chatbotApi.feedback(feedbackModel)
    }


    suspend fun getAllCases(): Response<GetAllCasesModel> {
        return chatbotApi.getAllCases()
    }

    suspend fun getUserName(): Response<JsonObject> {
        return chatbotApi.getUsername()
    }

    suspend fun getResponse(): Response<GetConsultancyResponse> {
        return chatbotApi.getResponse()
    }

    suspend fun getLeads(): Response<SampleLeads> {
        return chatbotApi.getLeads()
    }

}


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)
    return capabilities != null &&
            (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
}



