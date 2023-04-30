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
            MarvelComicsScreen(

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
            MarvelComicScreen(

            )
        }
        composable(Screen.FavMarvelComicsList.route) {
            FavMarvelComicsScreen(

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
            FavMarvelComicScreen(

            )
        }
        composable(Screen.RandomFavMarvelComic.route) {
            RandomFavMarvelComicScreen(

            )
        }
    }
}