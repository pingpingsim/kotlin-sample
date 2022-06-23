package com.hanai.jiwa.data.remote


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.hanai.jiwa.*
import com.hanai.jiwa.data.dto.Response
import com.hanai.jiwa.data.dto.user.UserModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    @Named(USERS_REF)
    private val usersRef: CollectionReference
) {
    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        nickname: String,
        phoneNumber: String
    ) =
        callbackFlow<Response<Boolean>> {
            trySend(Response.Loading)

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    currentUser?.let { currentUser ->
                        val user = hashMapOf(
                            ID to currentUser.uid,
                            EMAIL to email,
                            NICKNAME to nickname,
                            PHONE_NUMBER to phoneNumber,
                        )

                        db.collection(COLLECTION_PATH).document(currentUser.uid)
                            .set(user)
                            .addOnSuccessListener {
                            }
                            .addOnFailureListener { e ->
                            }
                    }
                    trySend(Response.Success(true))
                } else {
                    trySend(Response.Failure(task.exception?.message.toString()))
                }
            }

            awaitClose()
        }

    suspend fun getUserProfile() = callbackFlow<Response<UserModel>> {
        trySend(Response.Loading)

        auth.currentUser?.let { user ->
            val displayName = user.displayName
            val email = user.email
            val phoneNumber = user.phoneNumber
            val uid = user.uid

            val userRef = db.collection("users").document(user.uid)
            userRef.get()
                .addOnSuccessListener { document ->
                    document?.let {
                        val userInfo = UserModel(
                            user.email,
                            document.get("nickname") as String,
                            document.get("phonenumber") as String
                        )
                        trySend(Response.Success(userInfo))
                    }
                }
                .addOnFailureListener { exception ->
                    trySend(Response.Failure("error"))
                }
        }
        awaitClose()
    }

    suspend fun createNewProfile() = flow {
        try {
            emit(Response.Loading)
            auth.currentUser?.apply {
                usersRef.document(uid).set(
                    mapOf(
                        NAME to displayName,
                        EMAIL to email,
                    )
                ).await().also {
                    emit(Response.Success(true))
                }
            }
        } catch (e: Exception) {
            emit(Response.Failure(e.message ?: ERROR_MESSAGE))
        }
    }
}