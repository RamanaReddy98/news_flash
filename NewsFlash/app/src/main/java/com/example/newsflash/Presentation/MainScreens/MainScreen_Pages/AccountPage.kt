package com.example.newsflash.Presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsflash.Presentation.Components.Header
import com.example.newsflash.Presentation.Components.ProfileOption
import com.example.newsflash.R


@Composable
fun ProfileScreen(navController: NavController = rememberNavController()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {
        // Header Section
        Header(
            headerText = "Profile",
            onArrowClick = { navController.navigate("Onboard 1") } // Navigate back on arrow click
        )

        // Profile Picture and Info Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
            )

            // Name
            Text(
                text = "Alex Sharma",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Subtitle
            Text(
                text = "UPSC Aspirant",
                fontSize = 16.sp,
                color = Color.Gray
            )

            // Email
            Text(
                text = "sharmaalex@gmail.com",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Divider
        Divider(
            color = Color.LightGray.copy(alpha = 0.5f),
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Options Section
        Column(modifier = Modifier.fillMaxWidth()) {
            ProfileOption(
                iconRes = R.drawable.user,
                title = "Personal Details",
                onClick = { /* Navigate to Personal Details */ }
            )
            ProfileOption(
                iconRes = R.drawable.heart,
                title = "Preference Video",
                onClick = { /* Navigate to Preference Video */ }
            )
            ProfileOption(
                iconRes = R.drawable.refer,
                title = "Referral Code",
                onClick = { /* Navigate to Referral Code */ }
            )
            ProfileOption(
                iconRes = R.drawable.help,
                title = "Help Center",
                onClick = { /* Navigate to Help Center */ }
            )
            ProfileOption(
                iconRes = R.drawable.privacy,
                title = "Privacy",
                onClick = { /* Navigate to Privacy Settings */ }
            )
        }
    }
}

