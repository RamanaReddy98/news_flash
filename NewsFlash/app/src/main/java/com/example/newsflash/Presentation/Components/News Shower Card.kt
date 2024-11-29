package com.example.newsflash.Presentation.Components


import androidx.compose.foundation.Image
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import coil.compose.rememberImagePainter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.size.Scale
import com.example.newsflash.R
import com.example.newsflash.ui.theme.customFontFamily
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun news_shower_Card(
    navController: NavController,
    modifier: Modifier = Modifier,
    imageUrl: String = "https://media.tegna-media.com/assets/CCT/images/b9edd90e-228e-475c-8306-8fea2ad8ecc0/20241117T050402/b9edd90e-228e-475c-8306-8fea2ad8ecc0_16x9.jpg",
    title: String = "Breaking News: Major Event Unfolds",
    description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.",
    publisher: String = "Published by Test",
    newsId: Int=123,

    ) {
    Row(
        modifier = modifier
            .clickable {
                // Navigate to the NewsDetailPage and pass the newsId and title
                navController.navigate("News_Details/$newsId/$title")
            }
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)


            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
    ) {
        // Image Section
        AsyncImage(
           model = "https://image.pollinations.ai/prompt/Breaking",
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
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Description
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Publisher
            Text(
                text = publisher,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}


