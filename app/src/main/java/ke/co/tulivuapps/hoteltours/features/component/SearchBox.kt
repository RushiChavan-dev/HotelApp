package ke.co.tulivuapps.hoteltours.features.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.co.tulivuapps.hoteltours.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    visibility: MutableTransitionState<Boolean> =MutableTransitionState(false),
    detailClick: () -> Unit) {

    AnimatedVisibility(
        visibility,
        enter = expandIn(animationSpec = tween(
            delayMillis = 300,
            easing = LinearOutSlowInEasing,
            durationMillis = 800
        )) { IntSize(0, 0) },
        exit = ExitTransition.None
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateEnterExit(
                        enter = expandIn { IntSize(0, 0) }
                    )
                    .height(55.dp)
                    .border(
                        1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable(onClick = detailClick),
                enabled = false,
                readOnly = true,
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(30.dp),
                placeholder = {
                    Text(modifier = Modifier
                        .animateEnterExit(enter = slideIn(
                            tween(
//                                delayMillis = 300,
                                easing = LinearOutSlowInEasing,
                                durationMillis = 800
                            )
                        ) { IntOffset(0, 120) }),
                        text = "Search...",
                        color = Color.LightGray,
                        fontSize = 13.sp,
                    )
                },
                singleLine = true,
                trailingIcon = {
                    Box(
                        modifier = Modifier
                            .animateEnterExit(
                                enter = slideInHorizontally(tween(easing = LinearOutSlowInEasing,durationMillis = 5500)),
                            )
                            .size(30.dp)
                            .clip(shape = CircleShape)
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "Filter Icon", tint = Color.White
                        )

                    }
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(25.dp)
                            .animateEnterExit(
                            enter = slideInHorizontally(tween(easing = LinearOutSlowInEasing,durationMillis = 5500)),
                        ),
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search Icon",
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBox() {
    SearchBox(visibility= MutableTransitionState(true)){}
}