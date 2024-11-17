package com.example.mango_app.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.mango_app.ui.theme.BackgroundColor
import com.example.mango_app.ui.theme.MangoOrange


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium, // Borde redondeado
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors( //HAY QUE CAMBIARLO
            focusedBorderColor = MangoOrange,
            unfocusedBorderColor = Color.Gray,
            containerColor = Color.White,
            cursorColor = MangoOrange
        ),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )

}
