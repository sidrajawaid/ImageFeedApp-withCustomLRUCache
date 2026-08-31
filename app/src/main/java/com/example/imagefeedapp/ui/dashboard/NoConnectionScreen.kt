package com.example.imagefeedapp.ui.dashboard



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.R


@Composable
fun NoConnectionScreen(onRetry: () -> Unit) {

    val ctxt=  LocalContext.current

    Box(modifier = Modifier.background(color=Color.Gray, shape = RoundedCornerShape(6.dp))) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(R.drawable.no_connectivity),
                contentDescription = "No Connection",
                contentScale = ContentScale.Inside,
                modifier = Modifier.padding(8.dp)
                    .size(40.dp)
                    .background(color=Color(ctxt.resources.getColor(android.R.color.holo_blue_bright)),
                        shape = RoundedCornerShape(18.dp))
            )
            Text(
                text = "No Connection", modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.titleSmall
            )
            Text(text = "check your connection and try again.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp))

            OutlinedButton(
                onClick = onRetry,
                modifier = Modifier.padding(8.dp),
                border = BorderStroke(1.dp,
                    Color(ctxt.resources.getColor(android.R.color.holo_blue_dark))),
                shape = RoundedCornerShape(8.dp)
            )
            {
                Icon(
                    painter = painterResource(R.drawable.refresh),
                    contentDescription = "Refresh button icon"
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text("Retry", style = MaterialTheme.typography. labelSmall)
            }
        }
    }
}


@Preview
@Composable
fun PreviewNoConnectionScreen(){
    NoConnectionScreen({})
}