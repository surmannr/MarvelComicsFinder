package hu.bme.aut.android.marvelcomicsfinder.ui.model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.util.DebugLogger
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import kotlin.text.Typography.bullet

@ExperimentalMaterial3Api
@Composable
fun MarvelComicDetailUI(
    comic: MarvelComics,
    onButtonClick: (() -> Unit)?,
    buttonText: String = "",
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        MarvelComicDetailHeader(
            comic = comic,
            modifier = modifier
                .padding(0.dp),
        )
        Card(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp,
                pressedElevation = 2.dp,
                focusedElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Column(
                modifier = modifier
                    .padding(10.dp)
            ) {
                val description = comic.description.isEmpty()
                Text(
                    text = "Leírás:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = if (!description) comic.description else "Nincs",
                    fontSize = 16.sp,
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.secondary
                )

            }
        }

        MarvelComicListWithBulletPoints(
            modifier = modifier,
            title = "Alkotók",
            items = comic.creators.items.map { item -> item.name }
        )
        MarvelComicListWithBulletPoints(
            modifier = modifier,
            title = "Karakterek",
            items = comic.characters.items.map { item -> item.name }
        )
        MarvelComicListWithBulletPoints(
            modifier = modifier,
            title = "Kapcsolódó történetek",
            items = comic.stories.items.map { item -> item.name }
        )
        MarvelComicListWithBulletPoints(
            modifier = modifier,
            title = "Kapcsolódó képregények",
            items = comic.variants.map { item -> item.name }
        )

        if (onButtonClick != null) {
            Button(
                onClick = onButtonClick,
                shape = RoundedCornerShape(10),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                enabled = true
            ) {
                Text(
                    text = buttonText,
                    color = MaterialTheme.colorScheme.secondary)

            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MarvelComicListWithBulletPoints(
    items: List<String>,
    title: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 2.dp,
            focusedElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ){
        Column(
            modifier = modifier
                .padding(10.dp)
        ) {
            Text(
                text = "$title:",
                fontSize = 18.sp,
                modifier = modifier
                    .padding(bottom = 8.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
            Column(modifier = modifier) {
                if (items.isEmpty()) {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                                append("\t\t")
                                append(bullet)
                                append("\t\t")
                                append("Nincs megjeleníthető elem.")
                            }
                        }
                    )
                } else {
                    Text(
                        buildAnnotatedString {
                            items.forEach {
                                withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                                    append("\t\t")
                                    append(bullet)
                                    append("\t\t")
                                    append(it)
                                }
                            }
                        }
                    )
                }

            }

        }
    }

}

@ExperimentalMaterial3Api
@Composable
fun MarvelComicDetailHeader(
    comic: MarvelComics,
    modifier: Modifier = Modifier,
) {
    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .logger(DebugLogger())
        .build()
    Row(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .requiredHeightIn(max = 200.dp),
    ) {
        val hasImage = comic.images.isNotEmpty()
        if (hasImage) {
            AsyncImage(
                model = comic.images[0].path + "/portrait_uncanny." + comic.images[0].extension,
                imageLoader = imageLoader,
                contentDescription = "marvel image",
                modifier = modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
            )
        } else {
            AsyncImage(
                model = comic.thumbnail.path + "." + comic.thumbnail.extension,
                imageLoader = imageLoader,
                contentDescription = "marvel thumbnail",
                contentScale = ContentScale.FillHeight,
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .scale(1.0f)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
        Card(
            modifier = modifier
                .weight(2f)
                .padding(start = 10.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp,
                pressedElevation = 2.dp,
                focusedElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = comic.title,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = modifier
                            .padding(bottom = 5.dp)
                    )
                    val year = comic.modified.substring(0, 4)
                    if (year == "-000") {
                        Text(
                            text = "N/A",
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = modifier
                                .padding(bottom = 10.dp),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    else {
                        Text(
                            text = year,
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = modifier
                                .padding(bottom = 10.dp),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                Column(
                    modifier = modifier.padding(bottom = 10.dp)
                ) {
                    Text(
                        text = "Formátum: ${comic.format.ifEmpty { "Ismeretlen" }}",
                        fontSize = 16.sp,
                        modifier = modifier
                            .padding(bottom = 5.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Variáns: ${comic.variantDescription.ifEmpty { "Ismeretlen" }}",
                        fontSize = 16.sp,
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

            }
        }

    }
}