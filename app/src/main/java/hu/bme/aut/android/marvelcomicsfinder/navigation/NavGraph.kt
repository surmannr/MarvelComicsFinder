package hu.bme.aut.android.marvelcomicsfinder.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.details.FavMarvelComicScreen
import hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.list.FavMarvelComicsScreen
import hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.random.RandomFavMarvelComicScreen
import hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.details.MarvelComicScreen
import hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.list.MarvelComicsScreen
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.details.FavMarvelComicViewModel
import hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.list.FavMarvelComicsViewModel
import hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.random.RandomFavMarvelComicViewModel
import hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.details.MarvelComicViewModel
import hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.list.MarvelComicsViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MarvelComicsList.route
    ) {
        composable(Screen.MarvelComicsList.route) {
            val viewModel = hiltViewModel<MarvelComicsViewModel>()
            MarvelComicsScreen(
                onListItemClick = {
                    navController.navigate(Screen.MarvelComicDetail.passId(it))
                },
                viewModel,
                navController
            )
        }
        composable(
            route = Screen.MarvelComicDetail.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            val viewModel = hiltViewModel<MarvelComicViewModel>()
            MarvelComicScreen(
                viewModel,
                onNavigateBack = {navController.popBackStack()},
                navController
            )
        }
        composable(Screen.FavMarvelComicsList.route) {
            val viewModel = hiltViewModel<FavMarvelComicsViewModel>()
            FavMarvelComicsScreen(
                viewModel,
                navController,
                onListItemClick = {
                    navController.navigate(Screen.FavMarvelComicDetail.passId(it))
                },
            )
        }
        composable(
            route = Screen.FavMarvelComicDetail.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            val viewModel = hiltViewModel<FavMarvelComicViewModel>()
            FavMarvelComicScreen(
                viewModel,
                navController
            )
        }
        composable(Screen.RandomFavMarvelComic.route) {
            val viewModel = hiltViewModel<RandomFavMarvelComicViewModel>()
            RandomFavMarvelComicScreen(
                viewModel,
                navController
            )
        }
    }
}