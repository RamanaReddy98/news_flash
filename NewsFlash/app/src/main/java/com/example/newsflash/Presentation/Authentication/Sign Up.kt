package com.example.newsflash.Presentation.Authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.google.firebase.database.FirebaseDatabase

@Composable
fun Sign_Up(navController: NavController) {
    // Mutable states for user inputs
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Firebase instances
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header Section
        Header(headerText = "Sign up") {
            navController.navigate("Onboard 1")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Illustration
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.signup),
                contentDescription = "Sign up image",
                modifier = Modifier.size(180.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Welcome Text
        Text(
            text = "Welcome to NewsFlash",
            fontSize = 28.sp,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Already have an account?",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("Sign_In_Page") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Input Fields
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
                placeholder = { Text(text = "Enter your name") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                placeholder = { Text(text = "Enter your email") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Enter your password") },
                modifier = Modifier.fillMaxWidth(0.9f),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password") },
                placeholder = { Text(text = "Confirm your password") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Submit Button
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    isLoading = true
                    errorMessage = ""
                    if (password == confirmPassword && name.isNotEmpty() && email.isNotEmpty()) {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                isLoading = false
                                if (task.isSuccessful) {
                                    val user = auth.currentUser
                                    user?.let {
                                        val userDetails = mapOf(
                                            "userId" to it.uid,
                                            "name" to name,
                                            "email" to email
                                        )
                                        database.child("users").child(it.uid).setValue(userDetails)
                                            .addOnCompleteListener { dbTask ->
                                                if (dbTask.isSuccessful) {
                                                    navController.navigate("Main_Screen")
                                                } else {
                                                    errorMessage = "Failed to save user details"
                                                }
                                            }
                                    }
                                } else {
                                    errorMessage = task.exception?.message ?: "Signup failed"
                                }
                            }
                    } else {
                        isLoading = false
                        errorMessage = "Passwords do not match or fields are empty"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA7DF73)
                ),
                shape = RoundedCornerShape(12)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = "Sign up",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Error Message
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
            }
        }
    }
}

