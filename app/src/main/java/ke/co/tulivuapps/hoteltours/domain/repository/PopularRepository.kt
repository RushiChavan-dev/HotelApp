package ke.co.tulivuapps.hoteltours.domain.repository

import ke.co.tulivuapps.hoteltours.data.model.popular.PopularResponse
import ke.co.tulivuapps.hoteltours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 19.03.2023
 */
interface PopularRepository {
    fun getAllPopular(): Flow<DataState<PopularResponse>>
    fun getEpisode(episodeId: Int): Flow<DataState<ResultPopular>>
}