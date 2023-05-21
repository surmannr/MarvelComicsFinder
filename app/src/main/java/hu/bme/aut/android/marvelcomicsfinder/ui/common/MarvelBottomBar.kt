package hu.bme.aut.android.marvelcomicsfinder.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.android.marvelcomicsfinder.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
  navController: NavController
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route ?: ""
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = currentRoute == Screen.MarvelComicsList.route,
            onClick = { navController.navigate(Screen.MarvelComicsList.route) },
            label = {
                Text(
                    text = "Kezdőlap",
                    fontWeight = FontWeight.SemiBold,
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home Icon",
                )
            }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.FavMarvelComicsList.route,
            onClick = { navController.navigate(Screen.FavMarvelComicsList.route) },
            label = {
                Text(
                    text = "Kedvencek",
                    fontWeight = FontWeight.SemiBold,
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Icon",
                )
            }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.RandomFavMarvelComic.route,
            onClick = { navController.navigate(Screen.RandomFavMarvelComic.route) },
            label = {
                Text(
                    text = "Véletlen",
                    fontWeight = FontWeight.SemiBold,
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.TipsAndUpdates,
                    contentDescription = "Random Icon",
                )
            }
        )
    }
}
