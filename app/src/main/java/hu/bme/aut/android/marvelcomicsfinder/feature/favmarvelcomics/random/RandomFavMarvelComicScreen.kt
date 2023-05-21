package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.random

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import hu.bme.aut.android.marvelcomicsfinder.ui.common.BottomBar
import hu.bme.aut.android.marvelcomicsfinder.ui.common.MarvelAppBar
import hu.bme.aut.android.marvelcomicsfinder.ui.model.MarvelComicDetailUI

@ExperimentalMaterial3Api
@Composable
fun RandomFavMarvelComicScreen(
    viewModel: RandomFavMarvelComicViewModel,
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController)
        },
        topBar = {
            MarvelAppBar(
                title = "Random Marvel képregény",
                actions = {},
                onNavigateBack = null,
                modifier = Modifier
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ){
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                }
                state.isError -> {
                    Text(
                        text = state.error?.message ?: "Hiba a kérés során."
                    )
                }
                state.randomMarvelComic != null -> {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Button(
                            onClick = {
                                viewModel.loadRandomMarvelComicFromDb()
                            },
                            shape = RoundedCornerShape(10),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            enabled = true
                        ) {
                            Text(
                                text = "Véletlenszerű képregény sorsolás",
                                color = MaterialTheme.colorScheme.secondary)

                        }
                        MarvelComicDetailUI(
                            comic = state.randomMarvelComic!!,
                            onButtonClick = null
                        )
                    }
                }
                else -> {
                    Button(
                        onClick = {
                            viewModel.loadRandomMarvelComicFromDb()
                        },
                        shape = RoundedCornerShape(10),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        enabled = true
                    ) {
                        Text(
                            text = "Véletlenszerű képregény sorsolás",
                            color = MaterialTheme.colorScheme.secondary)

                    }
                }
            }
        }
    }
}