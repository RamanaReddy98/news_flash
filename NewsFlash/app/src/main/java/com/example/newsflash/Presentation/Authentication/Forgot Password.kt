package com.example.newsflash.Presentation.Authentication

import androidx.compose.foundation.Image
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
import androidx.compose.material3.OutlinedTextField
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
import com.example.newsflash.Presentation.Components.Header
import com.example.newsflash.R
import com.example.newsflash.ui.theme.customFontFamily


@Composable

fun ForgotPassword(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 15.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.Top,


        ) {

        Header("Forgot Password",{
            navController.navigate("Sign_In_Page")
        })
        Column (
            modifier = Modifier
                .padding(0.dp, 15.dp, 0.dp, 0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.forgot_pass),
                    contentDescription = "Login image",
                    modifier = Modifier.size(300.dp)
                )
            }



            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = "Reset password", fontSize = 32.sp, fontFamily = customFontFamily, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Enter your number to reset password",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp, fontFamily = customFontFamily
                )
            }



            Spacer(modifier = Modifier.height(15.dp))


            Column (
                modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(value = "", onValueChange = {}, label = {
                    Text(text = "Type your Phone Number")
                }, modifier = Modifier.fillMaxWidth(0.9f))

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = {
                    Text(text = "Type your OTP")
                }, modifier = Modifier.fillMaxWidth(0.9f))
            }

            Spacer(modifier = Modifier.height(15.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .width(550.dp).height(50.dp), // Adjust width as needed
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
            }



        }




    }
}