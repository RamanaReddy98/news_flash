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

@Composable
fun Header(
    headerText: String,
    onArrowClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 25.dp), // Adjust padding for balance
        verticalAlignment = Alignment.CenterVertically

    ) {
        // Back Arrow
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "Go back",
            modifier = Modifier
                .size(24.dp) // Size to match the minimal aesthetic
                .rotate(90f) // Ensure the arrow is in its correct orientation
                .clickable { onArrowClick() }
        )

        Spacer(modifier = Modifier.weight(1f)) // Push text to the center

        // Header Text
        Text(
            text = headerText,
            fontSize = 20.sp, // Slightly smaller font size for modern look
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.weight(1f)) // Balance right side
    }
}
