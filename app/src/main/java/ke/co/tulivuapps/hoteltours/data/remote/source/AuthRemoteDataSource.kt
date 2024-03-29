package ke.co.tulivuapps.hoteltours.data.remote.source

import ke.co.tulivuapps.hoteltours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.user.UserLoginInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.user.UserSignupInfoResponse
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 12.03.2023
 */

interface AuthRemoteDataSource {
    suspend fun getLoginDetails(data: UserLoginInfoResponse): Flow<DataState<UserInfoResponse>>
    suspend fun getRegistrationDetails(data: UserSignupInfoResponse): Flow<DataState<UserInfoResponse>>

}