package hu.bme.aut.android.marvelcomicsfinder.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@ExperimentalMaterial3Api
@Composable
fun MarvelAppBar(
    modifier: Modifier = Modifier,
    title: String,
    actions: @Composable() RowScope.() -> Unit,
    onNavigateBack: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title)},
        navigationIcon =  {
            if (onNavigateBack != null) {
                IconButton(onClick = onNavigateBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)

                }
            } else {
                null
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White,
        )
    )
}