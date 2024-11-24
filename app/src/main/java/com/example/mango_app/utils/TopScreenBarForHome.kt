package com.example.mango_app.utils


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mango_app.R
import com.example.mango_app.ui.theme.Mango_AppTheme

@Composable
fun TopScreenBarForHome(username: String = "user") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(16.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(32.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(32.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(start = 32.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "profile",
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .size(30.dp),
                tint = MaterialTheme.colorScheme.primary,
            )

            Text(
                text = stringResource(id = R.string.welcome) + "\n${username}",
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
            )

            Row(
                modifier = Modifier.padding(end = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_notifications_24),
                    contentDescription = "Notificaciones",
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .size(30.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
