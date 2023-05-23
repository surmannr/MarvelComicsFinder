package hu.bme.aut.android.marvelcomicsfinder

import android.R.attr.name
import android.R.id
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.marvelcomicsfinder.navigation.NavGraph
import hu.bme.aut.android.marvelcomicsfinder.ui.theme.MarvelComicsFinderTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAnalytics = Firebase.analytics
        super.onCreate(savedInstanceState)
        setContent {
            MarvelComicsFinderTheme() {
                NavGraph()
            }
        }
    }
}
