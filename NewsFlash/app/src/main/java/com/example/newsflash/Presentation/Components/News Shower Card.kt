package com.example.newsflash.Presentation.Components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsflash.DataModel.Article
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun news_shower_Card(
    navController: NavController,
    modifier: Modifier = Modifier,
    article: Article ,
    title: String = "Breaking News: Major Event Unfolds",
    description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.",
    publisher: String = "Published by Test",
    newsId: Int=123,

    ) {
    Row(
        modifier = modifier
            .clickable {
                // Navigate to the NewsDetailPage and pass the newsId and title
                navController.navigate("News_Details/${article.source?.id}/${article.title}/${article.content}/${URLEncoder.encode(article.urlToImage, StandardCharsets.UTF_8.toString())}")
            }
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)


            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
    ) {
        // Image Section
        AsyncImage(
           model = article.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(100.dp)
                .clip(MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Text Section
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp)
        ) {
            // Title
            article.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Description
            article.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Publisher
            article.source?.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}


