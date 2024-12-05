package com.example.newsflash.Presentation.Authentication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsflash.Presentation.Components.Header
import com.example.newsflash.R
import com.example.newsflash.ui.theme.customFontFamily
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Signin_UI(navController: NavController) {
    // Firebase authentication instance
    val auth = FirebaseAuth.getInstance()

    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var isLoading = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Section
        Header("Sign in", {
            navController.navigate("Onboard 1")
        })

        // Login Image
        Image(
            painter = painterResource(id = R.drawable.signup),
            contentDescription = "Login image",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Welcome Text and Description
        Text(
            text = "Welcome to NewsFlash",
            fontSize = 30.sp,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = "Your one stop destination for all the news that you can think of. Just sit back and enjoy your daily dose of news.",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = customFontFamily,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Email and Password Fields
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Type your email") },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.baseline_email_24), contentDescription = "Email Icon")
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 15.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Type your password") },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.baseline_lock_24), contentDescription = "Password Icon")
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 15.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        // Sign In Button
        Button(
            onClick = {
                isLoading.value = true
                auth.signInWithEmailAndPassword(email.value, password.value)
                    .addOnCompleteListener { task ->
                        isLoading.value = false
                        if (task.isSuccessful) {
                            navController.navigate("Main_Screen")
                        } else {
                            Toast.makeText(
                                navController.context,
                                "Authentication failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7DF73)),
            shape = RoundedCornerShape(12),
            enabled = !isLoading.value
        ) {
            if (isLoading.value) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = "Sign in",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Forgot Password Link
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("Forgot_password") }
                .padding(end = 20.dp),
            text = "Forgot password?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            textAlign = TextAlign.End
        )
    }
}

