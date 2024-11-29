package com.example.newsflash.Presentation.MainScreens.MainScreen_Pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsflash.Presentation.Components.Category
import com.example.newsflash.Presentation.Components.CategoryItem
import com.example.newsflash.Presentation.Components.news_shower_Card
import com.example.newsflash.R

@Composable
fun Nots_Page(modifier: Modifier = Modifier, navController: NavHostController) {
    val categories = listOf(
        Category("Philosophy", R.drawable.philosophy),
        Category("Sports", R.drawable.sports),
        Category("Technology", R.drawable.tech),
        Category("Science", R.drawable.science)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // Add consistent padding to the entire column
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Header Section


        // Explore Section
        Text(
            text = "Explore",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary // Subtle accent color for hierarchy
        )

        Spacer(modifier = Modifier.height(16.dp))

        // LazyRow for Categories
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(13.dp),
            contentPadding = PaddingValues(horizontal = 2.dp) // Reduced padding for elegance
        ) {
            items(categories.size) { index ->
                CategoryItem(category = categories[index])
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            repeat(10) { // Dynamically generate cards
                news_shower_Card(
                    navController,
                    Modifier.fillMaxWidth()
                )
            }
        }
    }
}
