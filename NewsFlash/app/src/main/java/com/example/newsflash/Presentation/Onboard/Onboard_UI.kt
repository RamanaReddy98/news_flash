package com.example.newsflash.Presentation.Onboard

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SigninOnboard(navController: NavController, modifier: Modifier = Modifier) {
    val auth = FirebaseAuth.getInstance()

    // Navigate to main screen if user is already authenticated
    if (auth.currentUser?.uid != null) {
        Log.d("onboard", "User already signed in: " + auth.currentUser?.uid)

        LaunchedEffect(Unit) {
            navController.navigate("Main_Screen") {
                popUpTo("SigninOnboard") { inclusive = true }
            }
        }
    } else {
        OnboardingContent(navController)
    }
}

@Composable
fun OnboardingContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Onboarding Image
        Image(
            painter = painterResource(id = R.drawable.loginonboard),
            contentDescription = "Onboarding Image",
            modifier = Modifier
                .size(300.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Onboarding Text Content
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Get Latest News",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = customFontFamily
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Welcome to your personalized news experience! Quickly explore trending stories, breaking updates, and curated topics that matter most to you.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = customFontFamily,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Action Buttons
        ActionButtons(navController)
    }
}

@Composable
fun ActionButtons(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { navController.navigate("Sign_In_Page") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7DF73)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Sign in",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("SignUp") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp)
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