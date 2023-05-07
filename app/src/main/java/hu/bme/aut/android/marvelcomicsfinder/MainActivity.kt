package hu.bme.aut.android.marvelcomicsfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.marvelcomicsfinder.navigation.NavGraph
import hu.bme.aut.android.marvelcomicsfinder.ui.theme.MarvelComicsFinderTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelComicsFinderTheme() {
                NavGraph()
            }
        }
    }
}
