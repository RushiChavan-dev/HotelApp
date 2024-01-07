package ke.co.tulivuapps.hoteltours.domain.repository

import ke.co.tulivuapps.hoteltours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.user.UserLoginInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.user.UserSignupInfoResponse
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRemoteRepository {

    suspend fun loginWithCredentials(userLoginInfoResponse: UserLoginInfoResponse): Flow<DataState<UserInfoResponse>>

    suspend fun signUpWithCredentials(userSignupInfoResponse: UserSignupInfoResponse): Flow<DataState<UserInfoResponse>>

    suspend fun sendPasswordResetEmail(email: String): Unit

}