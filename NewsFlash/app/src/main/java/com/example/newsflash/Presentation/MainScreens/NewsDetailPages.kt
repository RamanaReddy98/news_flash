package com.example.newsflash.Presentation.MainScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import coil.compose.AsyncImage
import com.example.newsflash.Presentation.Components.Header
import com.example.newsflash.R

@Composable
fun NewsDetailPage(
    navController: NavController = rememberNavController(),
    newsId: String,
    newsTitle: String,
    content:String,
    img:String

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
        AsyncImage(
            model = img,
            contentDescription = null,
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
                .verticalScroll(rememberScrollState())
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
                text = "By $newsId",
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Article Content
            Text(
                text = content,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color.Black
            )


        }

        // Horizontal Scrolling Row of Cards

    }
}

