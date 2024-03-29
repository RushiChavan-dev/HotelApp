package ke.co.tulivuapps.hoteltours.features.screen.destinationsdetail

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import ke.co.tulivuapps.hoteltours.R
import ke.co.tulivuapps.hoteltours.data.model.Result
import ke.co.tulivuapps.hoteltours.data.model.img
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
fun DestinationsDetailScreen(
    viewModel: DestinationsDetailViewModel = viewModel(),
    navigateToBack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is DestinationsDetailViewEvent.SnackBarError -> {
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
                text = if (viewState.data != null ) viewState.data!!.title else "Title"
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
            .padding(start = 14.dp, end = 14.dp),
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
            .fillMaxWidth()
            .height(400.dp)
            .padding(top = 20.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke( 1.dp, color = Color.LightGray),
    ) {
        HobbyHorseToursNetworkImage(
            imageURL = data?.img!![0].url,
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
    DestinationsDetailScreen(viewModel = hiltViewModel(), navigateToBack = {})
}


//New Screen

@Composable
fun DetailScreen(
    viewModel: DestinationsDetailViewModel = viewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navigateToBack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is DestinationsDetailViewEvent.SnackBarError -> {
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
        content = {innerPadding ->
            DetailScreenContent(modifier = Modifier.padding(innerPadding), viewModel= viewModel, navigateToBack = { navigateToBack })
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun DetailScreenContent(
    modifier: Modifier,
    viewModel: DestinationsDetailViewModel,
    navigateToBack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is DestinationsDetailViewEvent.SnackBarError -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message.orEmpty())
                    }
                }
            }
        }
    }

    Column(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxHeight()
            .semantics { contentDescription = "Detail Screen" }
    ) {
        CharacterImage(viewState.data)
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            Modifier
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            viewState.data?.let { Text(text = it.title, style = MaterialTheme.typography.h5) }
            Spacer(modifier = Modifier.weight(1F))
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                Modifier.size(10.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "(4.8)", style = MaterialTheme.typography.caption)
        }
        Spacer(modifier = Modifier.size(16.dp))
        viewState.data?.description?.let {Text(
            text = it,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 12.dp)
        )}
        Spacer(modifier = Modifier.size(16.dp))
        viewState.data?.let { MoreImages(it.img) }
        Spacer(modifier = Modifier.size(16.dp))
        Row(Modifier.fillMaxWidth()) {
            Column {
                Text(text = "Total Price", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.size(12.dp))
                Row {
                    Text(text = "", style = MaterialTheme.typography.subtitle2)
                    viewState.data?.price?.let { Text(text = it, style = MaterialTheme.typography.subtitle2) }
                }
            }
            Spacer(modifier = Modifier.weight(1F))
//            Button(
//                onClick = {},
//                modifier = Modifier.padding(bottom = 56.dp).size(170.dp, 56.dp),
//                shape = RoundedCornerShape(72.dp),
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = MaterialTheme.colors.background
//                )
//            ) {
//                Text(text = "Book Now", style = MaterialTheme.typography.button)
//            }
        }
    }
}

@Composable
private fun MoreImages(img: List<img>) {

    Spacer(modifier = Modifier.size(24.dp))
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)) {
        Text(text = "More Images", style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.size(16.dp))
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            img.map {
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = it.url).apply(block = fun ImageRequest.Builder.() {
                        crossfade(false)
                        size(Size(1200,900))
                        error(R.drawable.coffee)
                        fallback(R.drawable.coffee)
                    }).build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp)
                )
            }
        }
    }
}


