package com.hanai.jiwa.data

import com.hanai.jiwa.data.dto.Response
import com.hanai.jiwa.data.dto.user.UserModel
import com.hanai.jiwa.data.local.LocalData
import com.hanai.jiwa.data.remote.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(
    private val remoteRepository: AuthRepository,
    private val localRepository: LocalData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        nickname: String,
        phoneNumber: String
    ): Flow<Response<Boolean>> {
        return remoteRepository.createUserWithEmailAndPassword(
            email,
            password,
            nickname,
            phoneNumber
        ).flowOn(ioDispatcher)
    }

    override suspend fun createNewAccount(): Flow<Response<Boolean>> {
        return remoteRepository.createNewProfile().flowOn(ioDispatcher)
    }

    override suspend fun getUserProfile(): Flow<Response<UserModel>> {
        return remoteRepository.getUserProfile().flowOn(ioDispatcher)
    }

    override suspend fun setLanguage(): Flow<Response<Boolean>> {
        TODO("Not yet implemented")
    }
}