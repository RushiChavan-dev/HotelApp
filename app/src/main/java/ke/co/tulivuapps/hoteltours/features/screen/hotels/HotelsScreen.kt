package ke.co.tulivuapps. hobbyhorsetours.features.screen.hotels

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ke.co.tulivuapps.hoteltours.R
import ke.co.tulivuapps.hoteltours.data.model.Status
import ke.co.tulivuapps.hoteltours.data.model.dto.HotelDto
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursCharacterShimmer
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursHotelsCard
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursTopBar
import ke.co.tulivuapps.hoteltours.utils.Utility.rememberFlowWithLifecycle
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 13.03.2023
 */

@Composable
fun HotelsScreen(
    viewModel: HotelsViewModel,
    navigateToBack: () -> Unit,
    navigateToDetail: (HotelDto?) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState = viewModel.uiState.collectAsState().value

    HobbyHorseToursScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            HobbyHorseToursTopBar(
                text = stringResource(id = R.string.episodes_hotels_title),
                elevation = 10.dp,
                navigationIcon = {
                    IconButton(onClick = {
                        navigateToBack()
                    }) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                },
            )
        },
        content = {
            Content(
                isLoading = viewState.isLoading,
                pagedData = viewState.pagedData,
                onTriggerEvent = {
                  viewModel.onTriggerEvent(it)
                },
                clickDetail = {
                    navigateToDetail.invoke(it)
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun Content(
    isLoading :Boolean = false,
    pagedData: Flow<PagingData<HotelDto>>? = null,
    onTriggerEvent: (HotelsViewEvent) -> Unit,
    clickDetail: (HotelDto?) -> Unit
) {
    var pagingItems: LazyPagingItems<HotelDto>? = null
    pagedData?.let {
        pagingItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
    ) {
            if (isLoading) {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 2.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(10) {
                        HobbyHorseToursCharacterShimmer()

                    }
                }
            }
        if (pagedData != null && pagingItems != null && pagingItems?.itemCount!! > 0) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(2.dp)
                ) {
                    items(
                        pagingItems!!.itemCount
                    ) { index ->
                        pagingItems!![index]?.let {
                                HobbyHorseToursHotelsCard(
                                    status = Status.Unknown,
                                    detailClick = {
                                        clickDetail.invoke(it)
                                    },
                                    dto = it,
                                    onTriggerEvent = {
//                                          onTriggerEvent.invoke(HotelsViewEvent.UpdateFavorite(it))
                                    }
                                )
                        }
                    }
                }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun DetailContentItemViewPreview() {
    HotelsScreen(viewModel = hiltViewModel(), navigateToDetail = {}, navigateToBack = {})
}