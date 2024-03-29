package com.hanai.jiwa.data

import com.hanai.jiwa.data.dto.Response
import com.hanai.jiwa.data.dto.user.UserModel
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun createNewAccount(): Flow<Response<Boolean>>
    suspend fun getUserProfile(): Flow<Response<UserModel>>
    suspend fun setFirebaseLanguage(language: String): Flow<Response<Boolean>>
    suspend fun getLanguage(): Flow<Response<String>>
    suspend fun setLanguage(language: String): Flow<Response<Boolean>>
    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        nickname: String,
        phoneNumber: String
    ): Flow<Response<Boolean>>

}
