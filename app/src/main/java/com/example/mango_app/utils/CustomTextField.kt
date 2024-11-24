package com.example.mango_app.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    errorMessage: String? = null,
    onFieldFocusChange: () -> Unit = {},
    filterDigitsOnly: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                val filteredValue = if (filterDigitsOnly) it.filter { char -> char.isDigit() } else it
                onValueChange(filteredValue)
            },
            label = { Text(label) },
            leadingIcon = leadingIcon?.let { { Icon(it, contentDescription = null) } },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (it.isFocused && !isFocused) {
                        isFocused = true
                        onFieldFocusChange()
                    }
                },
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            isError = errorMessage != null,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (errorMessage != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = if (errorMessage != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                focusedLabelColor = if (errorMessage != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                cursorColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.surface
            ),
            keyboardOptions = if (filterDigitsOnly) {
                KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            } else {
                keyboardOptions
            },
            visualTransformation = visualTransformation
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.error),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

