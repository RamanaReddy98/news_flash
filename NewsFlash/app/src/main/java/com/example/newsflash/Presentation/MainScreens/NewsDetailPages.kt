package com.example.newsflash.Presentation.MainScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsflash.Presentation.Components.Header
import com.example.newsflash.Presentation.Components.similarCard
import com.example.newsflash.R

@Composable
fun NewsDetailPage(
    navController: NavController = rememberNavController(),
    newsId: Int,
    newsTitle: String
) {
    // Make the whole page scrollable
    Column(
        modifier = Modifier
            .fillMaxSize()
             // Apply scroll to the whole page
    ) {
        // Header
        Header(
            headerText = "News Detail",
            onArrowClick = { navController.navigate("Main_Screen") } // Navigate back
        )

        // Image Section
        Image(
            painter = painterResource(id = R.drawable.sample), // Replace with your image resource
            contentDescription = "News Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Adjust height as needed
        )

        // Content Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Date and Read Time
            Text(
                text = "Nov 21, 2023 · 10 Mins Read",
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = newsTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Author Name
            Text(
                text = "By Eren Yeager",
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Article Content
            Text(
                text = "So you’ve got a new computer. Awesome! That humble metal box is the key to a wide world of potential. It can bring new levels of productivity, help you juggle your workload, or simply entertain your family by blowing off some steam...",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "But a new PC isn’t like a new car; you can’t just turn a key and put the pedal to the metal. Performing just a few simple activities when you first fire it up can help it be safer, faster, and better poised for the future.",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color.Black
            )
        }

        // Horizontal Scrolling Row of Cards

    }
}

