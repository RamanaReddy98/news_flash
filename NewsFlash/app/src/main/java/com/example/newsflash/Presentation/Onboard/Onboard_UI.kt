package com.example.newsflash.Presentation.Onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsflash.R
import com.example.newsflash.ui.theme.customFontFamily

@Composable
fun SigninOnboard(navController: NavController, modifier: Modifier = Modifier) {
    Column  (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.loginonboard), 
            contentDescription =  "Login image",
            modifier = Modifier.size(400.dp))

        Spacer(modifier = Modifier.height(30.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Get Latest News",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = customFontFamily
            )

            Spacer(modifier = Modifier.height(10.dp)) // Adjust height for desired gap

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontFamily = customFontFamily
            )
        }
        Spacer(modifier = Modifier.height(22.dp))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        ) {
            Button(
                onClick = { navController.navigate("Sign_In_Page") },
                modifier = Modifier
                    .width(550.dp), // Adjust width as needed
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA7DF73) // Light green color
                ),
                shape = RoundedCornerShape(12) // Rounded edges
            ) {
                Text(
                    text = "Sign in",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(20.dp)) // Add 20 dp gap between buttons

            Button(
                onClick = { navController.navigate("SignUp") },
                modifier = Modifier
                    .width(550.dp).border(2.dp, Color.Black), // Adjust width as needed
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White // Adjust color as needed
                ),
                shape = RoundedCornerShape(12) // Rounded edges
            ) {
                Text(
                    text = "Sign up",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }

    }




}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NewsFlashTheme {
//        SigninOnboard()
//    }
//}