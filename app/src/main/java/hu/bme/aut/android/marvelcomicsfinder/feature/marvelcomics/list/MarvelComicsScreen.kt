package hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.marvelcomicsfinder.R
import hu.bme.aut.android.marvelcomicsfinder.ui.common.MarvelAppBar
import hu.bme.aut.android.marvelcomicsfinder.ui.common.MarvelComicElementUi
import hu.bme.aut.android.marvelcomicsfinder.ui.common.MarvelListFilterUi
import hu.bme.aut.android.marvelcomicsfinder.ui.common.MarvelPagerButtons
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import me.saket.swipe.SwipeableActionsState

@ExperimentalMaterial3Api
@Composable
fun MarvelComicsScreen(
    onListItemClick: (String) -> Unit,
    viewModel: MarvelComicsViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var titleStartWithValue by remember { mutableStateOf("") }
    var startYearValue by remember { mutableStateOf("") }

    var enabledFiltering by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                .padding(it)
                .background(
                    color = if (!state.isLoading && !state.isError) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.background
                    }
                ),
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
                            .weight(2.5f),
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
                                viewModel.onEvent(MarvelComicsEvent.Filter(titleStartWithValue, startYearValue))
                            },
                            deleteTitleStartWithValue = {
                                titleStartWithValue = ""
                                viewModel.onEvent(MarvelComicsEvent.Filter(titleStartWithValue, startYearValue))
                            },
                            onFilter = {
                                viewModel.onEvent(MarvelComicsEvent.Filter(titleStartWithValue, startYearValue))
                            }
                        )
                    }
                    LazyColumn(
                        modifier = Modifier
                            .weight(8f)
                    ) {
                        items(state.marvelComicsList, itemContent = { comic ->
                            SwipeableActionsBox(
                                modifier = Modifier,
                                endActions = listOf(
                                    SwipeAction(
                                        icon = rememberVectorPainter(Icons.TwoTone.Favorite),
                                        background = Color.Red,
                                        onSwipe = {

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
                            viewModel.onEvent(MarvelComicsEvent.NextPage(titleStartWithValue, startYearValue))
                        },
                        prevPage = {
                            viewModel.onEvent(MarvelComicsEvent.PrevPage(titleStartWithValue, startYearValue))
                        },
                        disableNextButton = state.pageCount == state.maxPageCount,
                        disablePrevButton = state.pageCount == 1,
                    )
                }
                else -> {
                    Text(
                        text = "A lista üres"
                    )
                }
            }
        }
    }
}