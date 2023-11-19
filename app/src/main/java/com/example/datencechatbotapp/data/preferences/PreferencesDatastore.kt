package com.example.datencechatbotapp.data.preferences

import android.app.Application
import android.content.Context
import android.provider.Settings.Global
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class PreferencesDatastore(context: Context) {

    val pref = context.datastore
    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("USER_DATA")
        var isLogin = booleanPreferencesKey("IS_USER_LOGIN")
        var isSignOutStarted = booleanPreferencesKey("IS_SIGN_OUT_STARTED")
        var isLightModeActive = booleanPreferencesKey("IS_LIGHT_MODE")
        var userName = stringPreferencesKey("USER_NAME")
        var profilePic = stringPreferencesKey("PROFILE_PIC")

    }


    suspend fun setIsLogin(islogin: Boolean) {
        pref.edit {
            it[isLogin] = islogin
        }
    }
    suspend fun setIsSignOutStarted(IsSignOutStarted_: Boolean) {
        pref.edit {
            it[isSignOutStarted] = IsSignOutStarted_
        }
    }
    suspend fun setUser(userName_: String, profilePic_: String) {
        pref.edit {
            it[userName] = userName_
            it[profilePic] = profilePic_
        }
    }

    suspend fun setisLightModeActive(IsLightModeActive: Boolean) {
        pref.edit {
            it[isLightModeActive] = IsLightModeActive
        }
    }

    fun getIsLogin() = pref.data.map {
        BooleanModel(
            value  = it[isLogin]?:false,
        )
    }
    fun getIsSignOutStarted() = pref.data.map {
        BooleanModel(
            value  = it[isSignOutStarted]?:false,
        )
    }
    fun getisLightModeActive() = pref.data.map {
        BooleanModel(
            value  = it[isLightModeActive]?:true,
        )
    }

    fun getUser() = pref.data.map {
        StringModel(
            name  = it[userName]?:"",
            pic  = it[profilePic]?:"",
        )
    }
}

data class BooleanModel(
    val value : Boolean,
)
data class StringModel(
    val name : String,
    val pic : String
)