package com.example.mango_app.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mango_app.R
import com.example.mango_app.ui.theme.Mango_AppTheme


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
                Text(text = stringResource(id = R.string.close))
            }
        }

    )
}
