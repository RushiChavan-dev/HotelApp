package ke.co.tulivuapps.hoteltours.data.remote.api

import ke.co.tulivuapps.hoteltours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.user.UserLoginInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.user.UserSignupInfoResponse
import ke.co.tulivuapps.hoteltours.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Rushi on 10.03.2023
 */

interface AuthService {

    @POST(Constants.AUTH_LOGIN_LIST)
    suspend fun postLoginDetails(@Body data: UserLoginInfoResponse): Response<UserInfoResponse>

    @POST(Constants.AUTH_REGISTRATION_LIST)
    suspend fun postRegistrationDetails(@Body data: UserSignupInfoResponse): Response<UserInfoResponse>

}