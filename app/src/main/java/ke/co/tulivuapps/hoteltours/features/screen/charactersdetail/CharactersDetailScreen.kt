package ke.co.tulivuapps.hoteltours.features.screen.charactersdetail

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ke.co.tulivuapps.hoteltours.R
import ke.co.tulivuapps.hoteltours.data.model.Result
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursNetworkImage
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursText
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursTopBar
import ke.co.tulivuapps.hoteltours.features.ui.theme.VeryDarkBlue
import kotlinx.coroutines.launch

/**
 * Created by Rushi on 13.03.2023
 */

@Composable
fun CharactersDetailScreen(
    viewModel: CharactersDetailViewModel = viewModel(),
    navigateToBack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is CharactersDetailViewEvent.SnackBarError -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message.orEmpty())
                    }
                }
            }
        }
    }

    HobbyHorseToursScaffold(
        topBar = {
            HobbyHorseToursTopBar(
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
                actions = {
                    IconButton(onClick = {}) {}
                },
                text = stringResource(id = R.string.character_detail_screen_title)
            )
        },
        content = {
            Content(
                viewState.data
            )
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun Content(data: Result?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterImage(data)
        CharacterInfoContainer(data)
    }
}

@Composable
private fun CharacterImage(data: Result?) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(top = 20.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            2.dp,
            color = Color.Red //if (data?. == Status.Alive) Color.Green else Color.Red
        ),
    ) {
        HobbyHorseToursNetworkImage(
            imageURL = data?.img,
            modifier = Modifier
                .fillMaxSize(),
            placeholder = R.drawable.ic_place_holder,
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
private fun CharacterInfoContainer(data: Result?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            CharacterInfoRow(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.character_detail_card_name),
                value = data?.title.orEmpty()
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_species),
                value = data?.title.orEmpty()
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_gender),
                value = data?.title.orEmpty()
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_last_know_location),
                value = data?.title.orEmpty()
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_location),
                value = data?.location.orEmpty()
            )
            Divider(thickness = 0.5.dp)
        }
    }
}

@Composable
private fun CharacterInfoRow(modifier: Modifier, text: String, value: String) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        HobbyHorseToursText(
            text = text,
            style = MaterialTheme.typography.body2,
            color = VeryDarkBlue
        )

        HobbyHorseToursText(
            text = value,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary
        )
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
    CharactersDetailScreen(viewModel = hiltViewModel(), navigateToBack = {})
}