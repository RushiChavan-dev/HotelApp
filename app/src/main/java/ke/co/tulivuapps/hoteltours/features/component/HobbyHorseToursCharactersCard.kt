package ke.co.tulivuapps.hoteltours.features.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hoteltours.R
import ke.co.tulivuapps.hoteltours.data.model.Status
import ke.co.tulivuapps.hoteltours.data.model.dto.CharacterDto

/**
 * Created by Rushi on 13.03.2023
 */

@Composable
fun HobbyHorseToursCharactersCard(
    modifier: Modifier = Modifier,
    status: Status,
    dto: CharacterDto?,
    detailClick: () -> Unit,
    onTriggerEvent: (CharacterDto) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { detailClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .size(100.dp)
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
            ) {
                HobbyHorseToursNetworkImage(
                    imageURL = dto?.image,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(80.dp)
                        .clip(shape = RoundedCornerShape(15)),
                    placeholder = R.drawable.ic_place_holder,
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.fillMaxHeight()) {
                    HobbyHorseToursText(
                        text = dto?.name.orEmpty(),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                    HobbyHorseToursText(
                        text = dto?.species.orEmpty(),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondaryVariant,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Card(
                            modifier = Modifier
                                .size(12.dp),
                            backgroundColor = if (status == Status.Alive) Color.Green else Color.Red,
                            shape = RoundedCornerShape(50)
                        ) {}
                        Spacer(modifier = Modifier.width(5.dp))
                        HobbyHorseToursText(
                            text = status.name,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.secondaryVariant,
                        )
                    }
                }
            }
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.CenterEnd) {
                dto?.let {
                    HobbyHorseToursFavoriteButton(
                        dto = it,
                        onTriggerEvent = { dto ->
                            onTriggerEvent.invoke(dto)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BodyPreview() {
    HobbyHorseToursCharactersCard(
        status = Status.Alive,
        detailClick = {},
        onTriggerEvent = {},
        dto = CharacterDto(
            id = 1,
            name = "Rick Sanchez",
            status = Status.Alive,
            type = "Human",
            created = "2017-11-04T18:48:46.250Z",
            episode = listOf("https://HobbyHorseToursapi.com/api/episode/1"),
            image = "https://HobbyHorseToursapi.com/api/character/avatar/1.jpeg",
            gender = "",
            location = null,
            origin = null,
            species = "",
            url = "",
            isFavorite = true
        ),
    )
}