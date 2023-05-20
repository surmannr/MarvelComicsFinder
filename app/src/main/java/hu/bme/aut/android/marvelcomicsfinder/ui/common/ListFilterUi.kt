package hu.bme.aut.android.marvelcomicsfinder.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun MarvelListFilterUi(
    modifier: Modifier = Modifier,
    onFilter: () -> Unit,
    titleStartWithValue: String,
    startYearValue: String,
    titleStartWithValueChange: (String) -> Unit,
    startYearValueChange: (String) -> Unit,
    deleteTitleStartWithValue: () -> Unit,
    deleteStartYearValue: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(10.dp)
    ) {
        Column(
            modifier = modifier
                .weight(2f)
        ) {
            TextField(
                value = titleStartWithValue,
                onValueChange = titleStartWithValueChange,
                label = { Text(text = "Cím kezdete") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.Black
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = deleteTitleStartWithValue,
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                singleLine = true,
                readOnly = false,
                isError = false,
                enabled = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = null
                )
            )
            TextField(
                value = startYearValue,
                onValueChange = startYearValueChange,
                label = { Text(text = "Kezdés éve") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.Black
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = deleteStartYearValue,
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                singleLine = true,
                readOnly = false,
                isError = false,
                enabled = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = null
                )
            )
        }
        Button(
            onClick = onFilter,
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .weight(1f)
        ) {
            Text(text = "Keresés")
        }
    }

}