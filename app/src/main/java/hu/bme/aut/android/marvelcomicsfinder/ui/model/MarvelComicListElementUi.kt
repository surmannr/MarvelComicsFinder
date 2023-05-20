package hu.bme.aut.android.marvelcomicsfinder.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.util.DebugLogger
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics

@ExperimentalMaterial3Api
@Composable
fun MarvelComicElementUi(
    comic: MarvelComics,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .logger(DebugLogger())
        .build()
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(90.dp)
            .clickable { onClick(comic.id) },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8. dp,
            pressedElevation = 2. dp,
            focusedElevation = 4. dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Box(
                modifier
                    .weight(1f)
            ) {
                AsyncImage(
                    model = comic.thumbnail.path + "." + comic.thumbnail.extension,
                    imageLoader = imageLoader,
                    contentDescription = "marvel thumbnail",
                    modifier = modifier
                        .clip(RoundedCornerShape(16.dp))
                        .width(60.dp)
                        .fillMaxHeight()
                )
            }
            Box(
                modifier
                    .weight(4f)
                    .padding(10.dp)
            ) {
                Column(
                    verticalArrangement =  Arrangement.Bottom,
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .weight(2f),
                        Alignment.CenterStart
                    ) {
                        Text(
                            fontSize = 16.sp,
                            text = comic.title,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    val year = comic.modified.substring(0, 4)
                    if (year == "-000") {
                        Text(
                            text = "N/A",
                            fontSize = 14.sp,
                            modifier = modifier
                                .weight(1.5f),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    else {
                        Text(
                            text = year,
                            fontSize = 14.sp,
                            modifier = modifier
                                .weight(1.5f),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}