package ke.co.tulivuapps.hoteltours.domain.usecase.hotels

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ke.co.tulivuapps.hoteltours.data.model.dto.HotelDto
import ke.co.tulivuapps.hoteltours.data.model.dto.extension.toHotelDtoList
import ke.co.tulivuapps.hoteltours.domain.repository.HotelRepository
import java.io.IOException

/**
 * Created by Rushi on 12.04.2023
 */

class HotelsFilterPagingSource(
    internal val repository: HotelRepository,
    private val options: Map<String, String>
) : PagingSource<Int, HotelDto>() {

    override fun getRefreshKey(state: PagingState<Int, HotelDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HotelDto> {
        val page = params.key ?: 1
        return try {
            val response = repository.getFilterHotels(page, options)

            val characterList = if (response.isSuccessful) {
                response.body()?.results.orEmpty().toHotelDtoList()
            } else {
                emptyList()
            }

            if (characterList.isNotEmpty()) {
                characterList.map {
                    val characterFav = repository.getFavorite(it.localId ?: 0)
                    it.isFavorite = characterFav != null
                }
            }

            LoadResult.Page(
                data = characterList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (characterList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}