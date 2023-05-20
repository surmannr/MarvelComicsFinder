package hu.bme.aut.android.marvelcomicsfinder.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun MarvelPagerButtons(
    modifier: Modifier = Modifier,
    prevPage: () -> Unit,
    nextPage: () -> Unit,
    disablePrevButton: Boolean,
    disableNextButton: Boolean
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Button(
            onClick = prevPage,
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            enabled = !disablePrevButton
        ) {
            Text(text = "Előző oldal")
        }
        Button(
            onClick = nextPage,
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            enabled = !disableNextButton
        ) {
            Text(text = "Következő oldal")
        }
    }
}