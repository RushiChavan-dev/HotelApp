package ke.co.tulivuapps.hoteltours.data.repository

import ke.co.tulivuapps.hoteltours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.user.UserLoginInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.user.UserSignupInfoResponse
import ke.co.tulivuapps.hoteltours.data.remote.source.AuthRemoteDataSource
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import ke.co.tulivuapps.hoteltours.domain.repository.AuthRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class AuthRemoteRepositoryImpl(private val authRemoteDataSource: AuthRemoteDataSource) : AuthRemoteRepository {

    override suspend fun loginWithCredentials(userLoginInfoResponse: UserLoginInfoResponse) = flow {
        emitAll(authRemoteDataSource.getLoginDetails(userLoginInfoResponse))
    }

    override suspend fun signUpWithCredentials(userSignupInfoResponse: UserSignupInfoResponse): Flow<DataState<UserInfoResponse>> = flow {
        emitAll(authRemoteDataSource.getRegistrationDetails(userSignupInfoResponse))
    }

    override suspend fun sendPasswordResetEmail(email: String) {
        TODO("Not yet implemented")
    }

}