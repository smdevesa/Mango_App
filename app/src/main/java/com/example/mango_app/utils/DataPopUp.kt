package com.example.mango_app.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mango_app.R


@Composable
fun DataPopUp(
    cvu: String,
    alias: String,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(id = R.string.information),
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Column {
                Text(
                    text = "CVU:",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = cvu,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Alias:",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = alias,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(
                    text = stringResource(id = R.string.close),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onError
                )
            }
        }

    )
}
