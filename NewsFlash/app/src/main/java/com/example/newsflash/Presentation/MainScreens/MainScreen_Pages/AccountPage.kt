package com.example.newsflash.Presentation.MainScreens.MainScreen_Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsflash.Presentation.Components.CategoryItem
import com.example.newsflash.R
// Mock data for categories
data class Category(val name: String, val imageRes: Int)

@Composable
fun AccountPage(modifier: Modifier = Modifier) {
    val categories = listOf(
        Category("Philosophy", R.drawable.philosophy),
        Category("Sports", R.drawable.sports),
        Category("Technology", R.drawable.tech),
        Category("Science", R.drawable.science)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1690AF)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Header Section
        Text(
            text = "Account Page",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Explore Section
        Text(
            text = "Explore",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // LazyRow for Categories
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(categories.size) { index ->
                CategoryItem(category = categories[index])
            }
        }
    }
}
