package ke.co.tulivuapps.hoteltours.data.repository

import ke.co.tulivuapps.hoteltours.data.model.popular.PopularResponse
import ke.co.tulivuapps.hoteltours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hoteltours.data.remote.source.PopularRemoteDataSource
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import ke.co.tulivuapps.hoteltours.domain.repository.PopularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Rushi on 19.03.2023
 */

class PopularRepositoryImpl @Inject constructor(private val episodesRemoteDataSource: PopularRemoteDataSource) :
    PopularRepository {

    override fun getAllPopular(): Flow<DataState<PopularResponse>> = flow {
        emitAll(episodesRemoteDataSource.getAllPopular())
    }

    override fun getEpisode(episodeId: Int): Flow<DataState<ResultPopular>> = flow {
        emitAll(episodesRemoteDataSource.getPopular(episodeId))
    }
}