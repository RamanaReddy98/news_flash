package com.example.newsflash.Presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsflash.R
import com.example.newsflash.ui.theme.customFontFamily

@Composable
fun Header(
    headerText: String,
    onArrowClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "Go back",
            modifier = Modifier
                .size(28.dp)
                .rotate(90f)
                .clickable { onArrowClick() } // Trigger navigation action on click
        )

        Spacer(modifier = Modifier.weight(1f)) // Spacer pushes text to center

        Text(
            text = headerText, // Use headerText parameter
            fontSize = 22.sp,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.weight(1f)) // Balances space on the right side
    }

    Spacer(modifier = Modifier.height(15.dp))
}
