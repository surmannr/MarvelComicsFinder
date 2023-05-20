package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.random

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import hu.bme.aut.android.marvelcomicsfinder.ui.common.BottomBar

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
    ) {
        Text(
            text = "teszt",
            modifier = Modifier.padding(it)
        )
    }
}