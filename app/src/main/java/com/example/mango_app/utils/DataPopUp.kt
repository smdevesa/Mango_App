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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f) // Ocupa el 90% del ancho
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp)) // Esquinas redondeadas
                .background(MaterialTheme.colorScheme.surface), // Fondo del popup
            contentAlignment = Alignment.TopCenter // Centra el contenido en el popup
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 20.dp, end = 4.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
            ) {
                // Botón de cerrar (cruz)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                        //.padding(bottom = 16.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "Cerrar",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onDismissRequest() }
                    )
                }

                //Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre la cruz y los textos

                // Textos centrados
                Text(
                    text = "CVU: $cvu",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally) // Asegura que esté centrado
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Alias: $alias",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally) // Asegura que esté centrado
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PopUpPreviewDarkTF() {
    Mango_AppTheme(darkTheme = true, content = {
        DataPopUp("1234567890", "alias123") { }
    })
}

@Preview(showBackground = true)
@Composable
fun PopUpPreviewLightTF() {
    Mango_AppTheme(darkTheme = false , content = {
        DataPopUp("1234567890", "alias123") { }
    })
}
