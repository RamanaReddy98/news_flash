package com.example.newsflash.Presentation.MainScreens.MainScreen_Pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.newsflash.DataModel.Article
import com.example.newsflash.Presentation.Components.news_shower_Card
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.log

@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavHostController) {
    // Mutable state list to hold articles
    val articlesList = remember { mutableStateListOf<Article>() }

    // Loading state
    val isLoading = remember { mutableStateOf(true) }

    // Reactive name state
    val name = remember { mutableStateOf("") }

    // Firebase Database reference
    val database = FirebaseDatabase.getInstance()
    val articlesRef = database.getReference("articles")

    val firebase = FirebaseAuth.getInstance().uid

    Log.d("Firebase", "HomePage: $firebase")

    fun getName() {
        firebase?.let {
            FirebaseDatabase.getInstance().reference.child("users").child(it).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val snapshot = task.result
                        if (snapshot.exists()) {
                            name.value = snapshot.child("name").value.toString() // Update state
                            Log.d("MyName", "getName: ${name.value}")
                        } else {
                            println("User not found")
                        }
                    } else {
                        println("Error fetching user: ${task.exception?.message}")
                    }
                }
        }
    }

    // Fetch data from Firebase
    LaunchedEffect(Unit) {
        articlesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                articlesList.clear() // Clear old data
                for (child in snapshot.children) {
                    val article = child.getValue(Article::class.java) // Map to Article class
                    article?.let { articlesList.add(it) }
                }
                isLoading.value = false // Data loaded, stop showing progress bar
                Log.d("FirebaseData", "Fetched articles: $articlesList")
                getName() // Fetch the name after articles are loaded
            }

            override fun onCancelled(error: DatabaseError) {
                isLoading.value = false // Stop showing progress bar even if there's an error
                Log.e("FirebaseError", "Error fetching data: ${error.message}")
            }
        })
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Header Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://image.pollinations.ai/prompt/${name.value}",
                contentDescription = "Profile Picture",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(60.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Hey there,",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Light
                    )
                )
                Text(
                    text = "${name.value}, how are you?",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }

        // Divider
        androidx.compose.material3.Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Show Circular Progress Bar if Loading
        if (isLoading.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Loading News...",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Light
                    )
                )
            }
        } else {
            // Content Section
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                // Display news items from Firebase
                articlesList.forEach { news ->
                    news_shower_Card(
                        navController,
                        Modifier.fillMaxWidth(),
                        news
                    )
                }
            }
        }
    }
}


