package ke.co.tulivuapps.hoteltours.features.screen.favorites

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ke.co.tulivuapps.hoteltours.R
import ke.co.tulivuapps.hoteltours.data.model.FavoriteEntity
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursAlertDialog
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursCharacterShimmer
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursFavoriteRowCard
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursText
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursTopBar

/**
 * Created by Rushi on 30.03.2023
 */

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    navigateCharacterDetail: (FavoriteEntity) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState = viewModel.uiState.collectAsState().value

    HobbyHorseToursScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            HobbyHorseToursTopBar(
                text = stringResource(R.string.favorite_screen_title),
                elevation = 10.dp,
                navigationIcon = {
                    IconButton(onClick = {
                        //navigateToBack()
                    }) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onTriggerEvent(FavoritesViewEvent.OnIsDeleteAllFavoritesChange)
                    }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = rememberVectorPainter(Icons.Default.Delete),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                },
            )
        },
        content = {
            Content(
                isLoading = viewState.isLoading,
                favoriteId = viewState.favoriteId,
                isDisplay = viewState.isDisplay,
                isAllDeleteFavorites = viewState.isAllDeleteFavorites,
                favoritesList = viewState.favoritesList,
                triggerEvent = {
                    viewModel.onTriggerEvent(it)
                },
                clickDetail = {
                    navigateCharacterDetail.invoke(it)
                })
        },
        backgroundColor = MaterialTheme.colors.background
    )

}

@Composable
private fun Content(
    isLoading: Boolean,
    favoriteId: Int?,
    isDisplay: Boolean,
    isAllDeleteFavorites: Boolean,
    favoritesList: List<FavoriteEntity>,
    triggerEvent: (FavoritesViewEvent) -> Unit,
    clickDetail: (FavoriteEntity) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowRickAndMortAlertDialog(
            isDisplay = isDisplay,
            isAllDeleteFavorites = isAllDeleteFavorites,
            triggerEvent = triggerEvent,
            favoriteId = favoriteId
        )

        if (isLoading.not() && favoritesList.isEmpty()) {
            EmptyListAnimation()
        }
        FavoriteList(isLoading, favoritesList, clickDetail, triggerEvent)
    }
}

@Composable
private fun ShowRickAndMortAlertDialog(
    isDisplay: Boolean,
    isAllDeleteFavorites: Boolean,
    triggerEvent: (FavoritesViewEvent) -> Unit,
    favoriteId: Int?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        HobbyHorseToursAlertDialog(
            isDisplayed = isDisplay,
            onClickDelete = {
                if (isAllDeleteFavorites) {
                    triggerEvent.invoke(FavoritesViewEvent.OnDeleteAllFavorites)
                } else {
                    triggerEvent.invoke(FavoritesViewEvent.OnDeleteFavorite)
                }
                triggerEvent.invoke(
                    FavoritesViewEvent.OnDisplayChange(
                        isDisplay = false,
                        favoriteId = favoriteId
                    )
                )
            },
            onBackPressed = {
                triggerEvent.invoke(
                    FavoritesViewEvent.OnDisplayChange(
                        isDisplay = false,
                        favoriteId = favoriteId
                    )
                )
            },
        )
    }
}

@Composable
private fun FavoriteList(
    isLoading: Boolean,
    favoritesList: List<FavoriteEntity>,
    clickDetail: (FavoriteEntity) -> Unit,
    triggerEvent: (FavoritesViewEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (isLoading) {
            items(10) {
                HobbyHorseToursCharacterShimmer()
            }
        } else if (favoritesList.isNotEmpty()) {
            items(items = favoritesList) { item ->
                HobbyHorseToursFavoriteRowCard(
                    status = item.status,
                    detailClick = {
                        clickDetail.invoke(item)
                    },
                    dto = item,
                    onDeleteClick = {
                        triggerEvent.invoke(
                            FavoritesViewEvent.OnDisplayChange(
                                isDisplay = true,
                                favoriteId = item.id
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun EmptyListAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_search))
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            LottieAnimation(
                composition,
                modifier = Modifier,
                restartOnPlay = true,
                alignment = Alignment.Center,
                iterations = LottieConstants.IterateForever,
            )
            HobbyHorseToursText(
                text = stringResource(R.string.favorite_screen_empty_list_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1
            )
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
    Content(false, 1, false, false, listOf(), triggerEvent = {}, clickDetail = {})
}