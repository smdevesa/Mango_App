package com.example.mango_app.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.model.data.Card
import com.example.mango_app.ui.theme.Mango_AppTheme


@Composable
fun CardDesign(card: Card) {
    Box(
        modifier = Modifier
            .height(180.dp) // Altura fija
            .width(300.dp) // Ancho más reducido
            .clip(RoundedCornerShape(16.dp)) // Esquinas redondeadas
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .padding(16.dp) // Espaciado interior
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(bottom = 0.dp),
        ) {
            // Parte superior: Tipo de tarjeta o logo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = card.type.uppercase(), // Ejemplo: "DEBIT" o "CREDIT"
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.weight(0.4f)
                        .padding(start = 16.dp),
                    fontSize = 20.sp,

                )
                Spacer(modifier = Modifier.weight(0.4f))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_credit_card_24), // Reemplaza con tu logo
                    contentDescription = "Logo",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                        .weight(0.2f),
                )
            }

            // Número de tarjeta y detalles inferiores
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 0.dp) // Ajusta esta separación para que empiece más arriba
            ) {
                // Número de tarjeta
                Text(
                    text = card.number.chunked(4).joinToString(" "), // Formato 4-4-4-4
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(start = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 18.sp,
                )

                Spacer(modifier = Modifier.height(10.dp)) // Separación entre el número y los detalles

                Text(
                    text = card.expirationDate, // Ejemplo: "Vence: 12/24"
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(start = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 18.sp,
                    )

                    Text(
                        text = card.fullName.uppercase(), // Nombre en mayúsculas
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(start = 16.dp),
                        fontSize = 18.sp,
                    )
            }
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//fun CardDesignPreviewDarkTF() {
//    Mango_AppTheme(darkTheme = true, content = {
//            CardDesign(card = Card(id = 0, cumber = "1234567891011121", type = "CREDIT", fullName = "John Doe", expirationDate = "12/23", cvv = "123"))
//    })
//}