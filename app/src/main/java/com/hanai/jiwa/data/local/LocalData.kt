package com.hanai.jiwa.data.local

import android.content.Context
import android.content.SharedPreferences
import com.hanai.jiwa.LANGUAGE_EN
import com.hanai.jiwa.SHARED_PREFERENCES_LANGUAGE
import com.hanai.jiwa.data.dto.Response
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalData @Inject constructor(val context: Context) {

    suspend fun getLanguage() = flow<Response<String>> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_LANGUAGE, 0)
        val language = sharedPref.getString(SHARED_PREFERENCES_LANGUAGE, LANGUAGE_EN) ?: LANGUAGE_EN
        emit(Response.Success(language))
    }

    fun setLanguage(language: String) = flow<Response<Boolean>> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_LANGUAGE, 0)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(SHARED_PREFERENCES_LANGUAGE, language)
        editor.apply()
        val isSuccess = editor.commit()
        emit(Response.Success(isSuccess))
    }
}