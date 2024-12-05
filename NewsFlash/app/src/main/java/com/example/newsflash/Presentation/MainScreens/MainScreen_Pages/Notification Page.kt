package com.example.newsflash.Presentation.MainScreens.MainScreen_Pages

import android.util.Log // Import the Log class
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsflash.DataModel.Article
import com.example.newsflash.Presentation.Components.Category
import com.example.newsflash.Presentation.Components.CategoryItem
import com.example.newsflash.Presentation.Components.news_shower_Card
import com.example.newsflash.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun Nots_Page(modifier: Modifier = Modifier, navController: NavHostController) {

    val articlesList = remember { mutableStateListOf<Article>() }
    val filterList = remember { mutableStateListOf<Article>() } // Keep this mutable
    val database = FirebaseDatabase.getInstance()
    val articlesRef = database.getReference("articles")
    var clickedCategory by remember { mutableStateOf("Sports") } // Use `by` for cleaner state management
    val categories = listOf(
        Category("Entertainment", R.drawable.philosophy),
        Category("Sports", R.drawable.sports),
        Category("World News", R.drawable.tech),
        Category("Health", R.drawable.health),
        Category("Politics", R.drawable.politics)
    )

    LaunchedEffect(clickedCategory) {
        articlesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                articlesList.clear() // Clear old data
                for (child in snapshot.children) {
                    val article = child.getValue(Article::class.java) // Map to Article class
                    article?.let { articlesList.add(it) }
                }

                // Filter articles based on the selected category
                filterList.clear()
                filterList.addAll(articlesList.filter { it.type == clickedCategory })
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error fetching data: ${error.message}")
            }
        })
    }

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
                CategoryItem(
                    category = categories[index],
                    onClick = { category ->
                        Log.d("CategoryClick", "Clicked category: ${category.name}")
                        clickedCategory = category.name // Update clickedCategory
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Display articles dynamically based on the selected category
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            filterList.forEach { news ->
                news_shower_Card(
                    navController,
                    Modifier.fillMaxWidth(),
                    news
                )
            }
        }
    }
}

