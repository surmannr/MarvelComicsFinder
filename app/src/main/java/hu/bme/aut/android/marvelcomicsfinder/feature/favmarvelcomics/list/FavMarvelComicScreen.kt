package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Remove
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.list.MarvelComicsEvent
import hu.bme.aut.android.marvelcomicsfinder.ui.common.BottomBar
import hu.bme.aut.android.marvelcomicsfinder.ui.common.MarvelAppBar
import hu.bme.aut.android.marvelcomicsfinder.ui.common.MarvelComicElementUi
import hu.bme.aut.android.marvelcomicsfinder.ui.common.MarvelListFilterUi
import hu.bme.aut.android.marvelcomicsfinder.ui.common.MarvelPagerButtons
import hu.bme.aut.android.marvelcomicsfinder.ui.model.UiEvent
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@ExperimentalMaterial3Api
@Composable
fun FavMarvelComicsScreen(
    viewModel: FavMarvelComicsViewModel,
    navController: NavController,
    onListItemClick: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val hostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    var titleStartWithValue by remember { mutableStateOf("") }
    var startYearValue by remember { mutableStateOf("") }

    var enabledFiltering by remember { mutableStateOf(false) }

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
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomBar(navController)
        },
        topBar = {
            MarvelAppBar(
                title = "Marvel képregénykereső",
                actions = {
                    IconButton(onClick = {
                        enabledFiltering = !enabledFiltering
                    }) {
                        Icon(imageVector = if(!enabledFiltering) Icons.Default.FilterAlt else Icons.Default.Clear, contentDescription = null)
                    }
                },
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
                state.marvelComicsList.isNotEmpty() -> {
                    AnimatedVisibility (
                        modifier = Modifier
                            .weight(3f),
                        visible = enabledFiltering,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        MarvelListFilterUi(
                            modifier = Modifier,
                            startYearValue = startYearValue,
                            titleStartWithValue = titleStartWithValue,
                            titleStartWithValueChange = { newValue ->
                                titleStartWithValue = newValue
                            },
                            startYearValueChange = { newValue ->
                                startYearValue = newValue
                            },
                            deleteStartYearValue = {
                                startYearValue = ""
                                viewModel.onEvent(FavMarvelComicsEvent.Filter(titleStartWithValue, startYearValue))
                            },
                            deleteTitleStartWithValue = {
                                titleStartWithValue = ""
                                viewModel.onEvent(FavMarvelComicsEvent.Filter(titleStartWithValue, startYearValue))
                            },
                            onFilter = {
                                viewModel.onEvent(FavMarvelComicsEvent.Filter(titleStartWithValue, startYearValue))
                            }
                        )
                    }
                    LazyColumn(
                        modifier = Modifier
                            .weight(7f)
                    ) {
                        items(state.marvelComicsList, itemContent = { comic ->
                            SwipeableActionsBox(
                                modifier = Modifier,
                                endActions = listOf(
                                    SwipeAction(
                                        icon = rememberVectorPainter(Icons.TwoTone.Remove),
                                        background = Color.Red,
                                        onSwipe = {
                                            viewModel.onEvent(FavMarvelComicsEvent.DeleteFavourite(comic))
                                        }
                                    )
                                ),
                                swipeThreshold = 40.dp
                            ) {
                                MarvelComicElementUi(
                                    comic = comic,
                                    onClick = onListItemClick,
                                    modifier = Modifier
                                )
                            }

                        })
                    }
                    MarvelPagerButtons(
                        modifier = Modifier
                            .weight(1f),
                        nextPage = {
                            viewModel.onEvent(FavMarvelComicsEvent.NextPage(titleStartWithValue, startYearValue))
                        },
                        prevPage = {
                            viewModel.onEvent(FavMarvelComicsEvent.PrevPage(titleStartWithValue, startYearValue))
                        },
                        disableNextButton = state.pageCount == state.maxPageCount,
                        disablePrevButton = state.pageCount == 1,
                    )
                }
                else -> {
                    AnimatedVisibility (
                        modifier = Modifier,
                        visible = enabledFiltering,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        MarvelListFilterUi(
                            modifier = Modifier,
                            startYearValue = startYearValue,
                            titleStartWithValue = titleStartWithValue,
                            titleStartWithValueChange = { newValue ->
                                titleStartWithValue = newValue
                            },
                            startYearValueChange = { newValue ->
                                startYearValue = newValue
                            },
                            deleteStartYearValue = {
                                startYearValue = ""
                                viewModel.onEvent(FavMarvelComicsEvent.Filter(titleStartWithValue, startYearValue))
                            },
                            deleteTitleStartWithValue = {
                                titleStartWithValue = ""
                                viewModel.onEvent(FavMarvelComicsEvent.Filter(titleStartWithValue, startYearValue))
                            },
                            onFilter = {
                                viewModel.onEvent(FavMarvelComicsEvent.Filter(titleStartWithValue, startYearValue))
                            }
                        )
                    }
                    Text(
                        text = "A kedvencek listád üres. Nézz körül a képregények közül és add a kedvenceidhez!",
                        modifier = Modifier.padding(10.dp),
                        fontSize = 17.sp,
                    )
                }
            }
        }
    }
}