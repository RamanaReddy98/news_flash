package com.example.newsflash.Presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsflash.R
import com.example.newsflash.ui.theme.customFontFamily


@Composable
fun news_shower_Card(
    modifier: Modifier = Modifier,

){
Row (modifier =modifier.clickable {  } ) {
    Image(
        painter = painterResource(id = R.drawable.signin),
        contentDescription = null,
        modifier = Modifier
            .size(120.dp)
            .clip(MaterialTheme.shapes.medium)
    )

    Column(
        verticalArrangement = Arrangement.SpaceAround, modifier = Modifier
            .padding(horizontal = 9.dp)
            .height(120.dp)
    ) {

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamc",
           fontSize = 18.sp,
            fontFamily = customFontFamily,
            color = Color.Black,
            maxLines = 2,
            fontWeight = FontWeight.SemiBold,
            overflow = TextOverflow.Ellipsis
        )

        Column (horizontalAlignment = Alignment.Start){
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
                fontSize = 13.sp,
                fontFamily = customFontFamily,
                color = Color.Black,
                maxLines = 2,
                lineHeight = (13*1.6).sp,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Published by Test",
                fontSize = 13.sp,
                fontFamily = customFontFamily,
                color = Color.Black,
                maxLines = 2,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis
            )
        }

    }

}
}



