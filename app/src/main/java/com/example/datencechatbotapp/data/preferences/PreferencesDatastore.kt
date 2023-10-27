package com.example.datencechatbotapp.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class PreferencesDatastore(context: Context) {
    val Context.datastore: DataStore<Preferences> by preferencesDataStore("USER_DATA")
    val pref = context.datastore

    companion object {
        var userName = stringPreferencesKey("USER_NAME")
        var isLogin = booleanPreferencesKey("IS_USER_LOGIN")
        var isLightModeActive = booleanPreferencesKey("IS_LIGHT_MODE")
        var isUserHaveExistingCases = booleanPreferencesKey("IS_USER_HAVING_EXISTING_CASES")
        var isUserFirstTimeLoginEver = booleanPreferencesKey("IS_USER_FIRST_TIME_LOGIN_EVER")
        var profilePicture = stringPreferencesKey("PROFILE_PICTURE")
    }

    suspend fun setUsername(username: String) {
        pref.edit {
            it[userName] = username
        }
    }

    suspend fun setIsLogin(islogin: Boolean) {
        pref.edit {
            it[isLogin] = islogin
        }
    }

    suspend fun setisLightModeActive(IsLightModeActive: Boolean) {
        pref.edit {
            it[isLightModeActive] = IsLightModeActive
        }
    }

    suspend fun setisUserHaveExistingCases(IsUserHaveExistingCases: Boolean) {
        pref.edit {
            it[isUserHaveExistingCases] = IsUserHaveExistingCases
        }
    }

    suspend fun setisUserFirstTimeLoginEver(IsUserFirstTimeLoginEver: Boolean) {
        pref.edit {
            it[isUserFirstTimeLoginEver] = IsUserFirstTimeLoginEver
        }
    }

    suspend fun profilePicture(ProfilePicture: String) {
        pref.edit {
            it[profilePicture] = ProfilePicture
        }
    }

    suspend fun getUsername() = pref.data.map {
        it[userName]
    }
}