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

package ke.co.tulivuapps.hoteltours.features.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hoteltours.R
import ke.co.tulivuapps.hoteltours.features.calendar.model.CalendarState
import ke.co.tulivuapps.hoteltours.features.screen.booknow.BookNowViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(
    onBackPressed: () -> Unit,
    bookNowViewModel: BookNowViewModel
) {
    val calendarState = remember { bookNowViewModel.calendarState  }

    CalendarContent(
        calendarState = calendarState,
        onDayClicked = { dateClicked ->
            bookNowViewModel.onDaySelected(dateClicked)
        },
        onBackPressed = onBackPressed
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CalendarContent(
    calendarState: CalendarState,
    onDayClicked: (LocalDate) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.navigationBars.only(WindowInsetsSides.Start + WindowInsetsSides.End)
        ),
        backgroundColor = MaterialTheme.colors.primary,
        topBar = {
            CalendarTopAppBar(calendarState, onBackPressed)
        }
    ) { contentPadding ->
        Calendar(
            calendarState = calendarState,
            onDayClicked = onDayClicked,
            contentPadding = contentPadding
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CalendarTopAppBar(calendarState: CalendarState, onBackPressed: () -> Unit) {
    val calendarUiState = calendarState.calendarUiState.value
    Column {
        Spacer(
            modifier = Modifier
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant)
        )
        TopAppBar(
            title = {
                Text(
                    text = if (!calendarUiState.hasSelectedDates) {
                        stringResource(id = R.string.calendar_select_dates_title)
                    } else {
                        calendarUiState.selectedDatesFormatted
                    }
                )
            },actions = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(id = R.string.cd_done),
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.cd_back),
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.primaryVariant,
            elevation = 0.dp
        )
    }
}
