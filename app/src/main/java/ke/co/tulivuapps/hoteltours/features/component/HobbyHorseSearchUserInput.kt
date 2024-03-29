/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ke.co.tulivuapps.hoteltours.features.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ke.co.tulivuapps.hoteltours.R
import ke.co.tulivuapps.hoteltours.features.component.PeopleUserInputAnimationState.*
import ke.co.tulivuapps.hoteltours.features.ui.theme.HobbyHorseToursAppTheme

const val MAX_PEOPLE = 4
enum class PeopleUserInputAnimationState { Valid, Invalid }

class PeopleUserInputState {
    var people by mutableStateOf(1)
        private set

    val animationState: MutableTransitionState<PeopleUserInputAnimationState> =
        MutableTransitionState(Valid)

    fun addPerson() {
        people = (people % (MAX_PEOPLE + 1)) + 1
        updateAnimationState()
    }

    private fun updateAnimationState() {
        val newState =
            if (people > MAX_PEOPLE) Invalid
            else Valid

        if (animationState.currentState != newState) animationState.targetState = newState
    }
}


class ChildrenInputState {
    var children by mutableStateOf(1)
        private set

    val animationState: MutableTransitionState<PeopleUserInputAnimationState> =
        MutableTransitionState(Valid)

    fun addChildren() {
        children = (children % (MAX_PEOPLE + 1)) + 1
        updateAnimationState()
    }

    private fun updateAnimationState() {
        val newState =
            if (children > MAX_PEOPLE) Invalid
            else Valid

        if (animationState.currentState != newState) animationState.targetState = newState
    }
}


class RoomsInputState {
    var rooms by mutableStateOf(1)
        private set

    val animationState: MutableTransitionState<PeopleUserInputAnimationState> =
        MutableTransitionState(Valid)

    fun addRooms() {
        rooms = (rooms % (MAX_PEOPLE + 1)) + 1
        updateAnimationState()
    }

    private fun updateAnimationState() {
        val newState =
            if (rooms > MAX_PEOPLE) Invalid
            else Valid

        if (animationState.currentState != newState) animationState.targetState = newState
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PeopleUserInput(
    titleSuffix: String = "",
    onPeopleChanged: (Int) -> Unit,
    peopleState: PeopleUserInputState = remember { PeopleUserInputState() }
) {
    Column {
        val transitionState = remember { peopleState.animationState }
        val tint = tintPeopleUserInput(transitionState)

        val people = peopleState.people
        CraneUserInput(
            text = pluralStringResource(
                id = R.plurals.number_adults_selected,
                count = people,
                people,
                titleSuffix
            ),
            vectorImageId = R.drawable.ic_person,
            tint = tint.value,
            onClick = {
                peopleState.addPerson()
                onPeopleChanged(peopleState.people)
            }
        )
        if (transitionState.targetState == Invalid) {
            Text(
                text = stringResource(
                    id = R.string.error_max_people,
                    MAX_PEOPLE
                ),
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.secondary)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChildrenUserInput(
    titleSuffix: String = "",
    onPeopleChanged: (Int) -> Unit,
    childrenState: ChildrenInputState = remember { ChildrenInputState() }
) {
    Column {
        val transitionState = remember { childrenState.animationState }
        val tint = tintPeopleUserInput(transitionState)

        val people = childrenState.children
        CraneUserInput(
            text = pluralStringResource(
                id = R.plurals.number_children_selected,
                count = people,
                people,
                titleSuffix
            ),
            vectorImageId = R.drawable.ic_outline_people,
            tint = tint.value,
            onClick = {
                childrenState.addChildren()
                onPeopleChanged(childrenState.children)
            }
        )
        if (transitionState.targetState == Invalid) {
            Text(
                text = stringResource(
                    id = R.string.error_max_people,
                    MAX_PEOPLE
                ),
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.secondary)
            )
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RoomsInput(
    titleSuffix: String = "",
    onPeopleChanged: (Int) -> Unit,
    roomState: RoomsInputState = remember { RoomsInputState() }
) {
    Column {
        val transitionState = remember { roomState.animationState }
        val tint = tintPeopleUserInput(transitionState)

        val people = roomState.rooms
        CraneUserInput(
            text = pluralStringResource(
                id = R.plurals.number_rooms_selected,
                count = people,
                people,
                titleSuffix
            ),
            vectorImageId = R.drawable.ic_calendar,
            tint = tint.value,
            onClick = {
                roomState.addRooms()
                onPeopleChanged(roomState.rooms)
            }
        )
        if (transitionState.targetState == Invalid) {
            Text(
                text = stringResource(
                    id = R.string.error_max_people,
                    MAX_PEOPLE
                ),
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.secondary)
            )
        }
    }
}

@Composable
fun FromDestination() {
    CraneUserInput(text = "Seoul, South Korea", vectorImageId = R.drawable.ic_location)
}

@Composable
fun ToDestinationUserInput(onToDestinationChanged: (String) -> Unit) {
    CraneEditableUserInput(
        hint = stringResource(R.string.select_destination_hint),
        caption = stringResource(R.string.select_destination_to_caption),
        vectorImageId = R.drawable.ic_plane,
        onInputChanged = onToDestinationChanged
    )
}

@Composable
fun DatesUserInput(datesSelected: String, onDateSelectionClicked: () -> Unit) {

    CraneUserInput(
        onClick = onDateSelectionClicked,
        caption = if (datesSelected.isEmpty()) stringResource(R.string.select_dates) else null,
        text = datesSelected,
        vectorImageId = R.drawable.ic_calendar
    )
}

@Composable
private fun tintPeopleUserInput(
    transitionState: MutableTransitionState<PeopleUserInputAnimationState>
): State<Color> {
    val validColor = MaterialTheme.colors.onSurface
    val invalidColor = MaterialTheme.colors.secondary

    val transition = updateTransition(transitionState, label = "tintTransition")
    return transition.animateColor(
        transitionSpec = { tween(durationMillis = 300) }, label = "tintTransitionSpec"
    ) {
        if (it == Valid) validColor else invalidColor
    }
}

@Preview
@Composable
fun PeopleUserInputPreview() {
    HobbyHorseToursAppTheme {
        PeopleUserInput(onPeopleChanged = {})
    }
}
