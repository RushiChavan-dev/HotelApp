package ke.co.tulivuapps.hoteltours.data.remote.utils

import ke.co.tulivuapps.hoteltours.data.model.APIError

/**
 * Created by Rushi on 12.03.2023
 */

sealed class DataState<T> {
    class Success<T>(val data: T) : DataState<T>()
    class Loading<T> : DataState<T>()
    class Error<T>(val apiError: APIError?) : DataState<T>()
}