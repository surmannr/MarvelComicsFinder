package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import hu.bme.aut.android.marvelcomicsfinder.navigation.Screen
import hu.bme.aut.android.marvelcomicsfinder.ui.common.BottomBar
import hu.bme.aut.android.marvelcomicsfinder.ui.common.MarvelAppBar
import hu.bme.aut.android.marvelcomicsfinder.ui.model.MarvelComicDetailUI
import hu.bme.aut.android.marvelcomicsfinder.ui.model.UiEvent
import hu.bme.aut.android.marvelcomicsfinder.utils.FirebaseExtensions
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun FavMarvelComicScreen(
    viewModel: FavMarvelComicViewModel,
    onNavigateBack: () -> Unit,
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val hostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var dialogOpen by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.Success -> {
                    scope.launch {
                        hostState.showSnackbar(uiEvent.message, duration = SnackbarDuration.Short)
                    }
                }
                is UiEvent.Failure -> {
                    scope.launch {
                        hostState.showSnackbar(uiEvent.error, duration = SnackbarDuration.Short)
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState) },
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController)
        },
        topBar = {
            MarvelAppBar(
                title = "Marvel képregény részletek",
                actions = {
                    IconButton(onClick = {
                        throw RuntimeException("Test crash!")
                    }) {
                        Icon(imageVector = Icons.Default.PieChart, contentDescription = null)
                    }
                },
                onNavigateBack = onNavigateBack,
                modifier = Modifier
            )
        },
    ) {
        if (dialogOpen) {
            AlertDialog(
                onDismissRequest = {
                    dialogOpen = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.removeFavourite(state.marvelComic!!)
                            FirebaseExtensions.logAnalytics(
                                eventName = FirebaseExtensions.REMOVE_FAVOURITE,
                                bundleProperties = mapOf(
                                    "screen" to "KedvencReszletek",
                                    "id" to state.marvelComic!!.id,
                                    "title" to state.marvelComic!!.title
                                ),
                                context = context
                            )
                            navController.navigate(Screen.FavMarvelComicsList.route)
                            dialogOpen = false
                        }
                    ) {
                        Text(text = "Igen")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            dialogOpen = false
                        }
                    ) {
                        Text(text = "Nem")
                    }
                },
                title = {
                    Text(text = "Törlés")
                },
                text = {
                    Text(text = "Biztosan törölni akarod a kedvenceid közül?")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                shape = RoundedCornerShape(5.dp),
            )
        }
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
                state.marvelComic != null -> {
                    MarvelComicDetailUI(
                        comic = state.marvelComic!!,
                        onButtonClick = {
                            dialogOpen = true
                        },
                        buttonText = "Törlés a kedvencek közül"
                    )
                }
                else -> {
                    Text(
                        text = "Nincs ilyen képregény."
                    )
                }
            }
        }
    }
}