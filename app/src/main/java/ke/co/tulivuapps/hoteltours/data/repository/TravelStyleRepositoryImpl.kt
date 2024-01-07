package ke.co.tulivuapps.hoteltours.data.repository

import ke.co.tulivuapps.hoteltours.data.local.dao.TravelStyleDao
import ke.co.tulivuapps.hoteltours.data.model.travelstyle.TravelStyleFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.travelstyle.TravelStyleInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.travelstyle.TravelStyleResponse
import ke.co.tulivuapps.hoteltours.data.remote.source.TravelStyleRemoteDataSource
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import ke.co.tulivuapps.hoteltours.domain.repository.TravelStyleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Rushi on 12.03.2023
 */

class TravelStyleRepositoryImpl @Inject constructor(
    private val travelStyleRemoteDataSource: TravelStyleRemoteDataSource,
    private val dao: TravelStyleDao
) : TravelStyleRepository {

    override suspend fun getAllTravelStyles(
        page: Int,
        options: Map<String, String>
    ): Response<TravelStyleResponse> =
        travelStyleRemoteDataSource.getAllTravelStyle(page = page, options = options)

    override fun getTravelStyle(travelStyleId: Int): Flow<DataState<TravelStyleInfoResponse>> = flow {
        emitAll(travelStyleRemoteDataSource.getTravelStyle(travelStyleId = travelStyleId))
    }

    override fun getTravelStyle(url: String): Flow<DataState<TravelStyleInfoResponse>> = flow {
        emitAll(travelStyleRemoteDataSource.getTravelStyle(url = url))
    }

    override suspend fun getFilterTravelStyle(
        page: Int,
        options: Map<String, String>
    ): Response<TravelStyleResponse> = travelStyleRemoteDataSource.getFilterTravelStyle(page, options)

    override suspend fun getFavoriteTravelStyle(favoriteId: Int): TravelStyleFavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getTravelStyleFavoriteList(): List<TravelStyleFavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteTravelStyleById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteTravelStyleList() = dao.deleteFavoriteList()

    override suspend fun saveFavoriteTravelStyle(entity: TravelStyleFavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteTravelStyleList(entityList: List<TravelStyleFavoriteEntity>) = dao.insert(entityList)
}