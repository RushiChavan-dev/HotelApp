package ke.co.tulivuapps.hoteltours.domain.repository

import ke.co.tulivuapps.hoteltours.data.model.FavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.character.CharacterInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.character.CharacterResponse
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by Rushi on 12.03.2023
 */

interface CharacterRepository {
    suspend fun getAllCharacters(page: Int, options: Map<String, String>): Response<CharacterResponse>
    fun getCharacter(characterId: Int): Flow<DataState<CharacterInfoResponse>>
    fun getCharacter(url: String): Flow<DataState<CharacterInfoResponse>>
    suspend fun getFilterCharacters(page: Int, options: Map<String, String>): Response<CharacterResponse>
    suspend fun getFavoriteList(): List<FavoriteEntity>
    suspend fun getFavorite(favoriteId: Int): FavoriteEntity? = null
    suspend fun deleteFavoriteById(favoriteId: Int)
    suspend fun deleteFavoriteList()
    suspend fun saveFavorite(entity: FavoriteEntity)
    suspend fun saveFavoriteList(entityList: List<FavoriteEntity>)
}