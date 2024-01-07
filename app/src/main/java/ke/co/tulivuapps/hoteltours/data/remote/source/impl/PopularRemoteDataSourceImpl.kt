package ke.co.tulivuapps.hoteltours.data.remote.source.impl

import ke.co.tulivuapps.hoteltours.data.model.popular.PopularResponse
import ke.co.tulivuapps.hoteltours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hoteltours.data.remote.api.PopularService
import ke.co.tulivuapps.hoteltours.data.remote.source.PopularRemoteDataSource
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Rushi on 19.03.2023
 */

class PopularRemoteDataSourceImpl @Inject constructor(private val episodesService: PopularService) :
    BaseRemoteDataSource(), PopularRemoteDataSource {

    override suspend fun getAllPopular(): Flow<DataState<PopularResponse>> = getResult {
        episodesService.getAllPopular()
    }

    override suspend fun getPopular(episodeId: Int): Flow<DataState<ResultPopular>> = getResult {
        episodesService.getPopular(episodeId = episodeId)
    }

}
