package com.example.newsflash.Presentation.MainScreens.MainScreen_Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsflash.Presentation.Components.Header
import com.example.newsflash.Presentation.Components.news_shower_Card
import com.example.newsflash.R
import com.example.newsflash.ui.theme.customFontFamily

@Composable
fun HomePage (modifier: Modifier = Modifier){
Column (
    modifier = modifier
        .fillMaxSize(),

    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.Start

) {

    Row (modifier= Modifier.padding(15.dp)){
        Image(painter = painterResource(id = R.drawable.signin), contentDescription = "PFP", modifier= Modifier.size(60.dp).clip(MaterialTheme.shapes.extraLarge))
        Column {
            Text(
                text = "Hey there,", fontWeight = FontWeight.Light, fontSize = 15.sp,
                fontFamily = customFontFamily,
            )
            Text(
                text = "Alex, how are you?,", fontWeight = FontWeight.SemiBold, fontSize = 25.sp,
                fontFamily = customFontFamily,
            )
        }
    }

    Spacer(
        modifier = Modifier
            .height(5.dp)
            .fillMaxWidth()

            .drawBehind {
                // Draw a slim line at the bottom
                drawLine(
                    color = Color.White,
                    start = androidx.compose.ui.geometry.Offset(0f, size.height), // Start at bottom-left
                    end = androidx.compose.ui.geometry.Offset(size.width, size.height), // End at bottom-right
                    strokeWidth = 1.dp.toPx() // Border thickness
                )
            }
    )


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        news_shower_Card(Modifier)
        Spacer(modifier = Modifier.height(15.dp))
        news_shower_Card(Modifier)
        Spacer(modifier = Modifier.height(15.dp))
        news_shower_Card(Modifier)
        Spacer(modifier = Modifier.height(15.dp))
        news_shower_Card(Modifier)
        Spacer(modifier = Modifier.height(15.dp))
        news_shower_Card(Modifier)
        Spacer(modifier = Modifier.height(15.dp))
        news_shower_Card(Modifier)
        Spacer(modifier = Modifier.height(15.dp))
        news_shower_Card(Modifier)
        Spacer(modifier = Modifier.height(15.dp))
        news_shower_Card(Modifier)
        Spacer(modifier = Modifier.height(15.dp))
        news_shower_Card(Modifier)
        Spacer(modifier = Modifier.height(15.dp))
    }

}
}