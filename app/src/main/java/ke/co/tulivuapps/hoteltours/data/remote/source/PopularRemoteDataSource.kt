package ke.co.tulivuapps.hoteltours.data.remote.source

import ke.co.tulivuapps.hoteltours.data.model.popular.PopularResponse
import ke.co.tulivuapps.hoteltours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 19.03.2023
 */

interface PopularRemoteDataSource {
    suspend fun getAllPopular(): Flow<DataState<PopularResponse>>
    suspend fun getPopular(episodeId: Int): Flow<DataState<ResultPopular>>
}