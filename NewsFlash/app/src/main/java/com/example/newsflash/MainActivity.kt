package com.example.newsflash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsflash.Presentation.Authentication.ForgotPassword
import com.example.newsflash.Presentation.Authentication.Login_UI
import com.example.newsflash.Presentation.Authentication.Signin_UI
import com.example.newsflash.Presentation.MainScreens.MainScreen
import com.example.newsflash.Presentation.MainScreens.NewsDetailPage

import com.example.newsflash.Presentation.Onboard.SigninOnboard
import com.example.newsflash.ui.theme.NewsFlashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsFlashTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Main_Screen" , builder = {
                        composable("Onboard 1") {
                            SigninOnboard(navController)
                        }
                        composable("Sign_In_Page"){
                            Signin_UI(navController)
                        }
                        composable("SignUp"){
                            Login_UI(navController)
                        }
                        composable("Forgot_password"){
                            ForgotPassword(navController)
                        }
                        composable ("Main_Screen"){
                            MainScreen(navController)
                        }
                        composable(
                            "News_Details/{newsId}/{newsTitle}",
                            arguments = listOf(
                                navArgument("newsId") { type = NavType.IntType },
                                navArgument("newsTitle") { type = NavType.StringType }
                            )
                        ) {
                                backStackEntry ->
                            val newsId = backStackEntry.arguments?.getInt("newsId") ?: 0
                            val newsTitle = backStackEntry.arguments?.getString("newsTitle") ?: ""
                            NewsDetailPage(navController, newsId, newsTitle)
                        }


                    }
                    )

                }
            }
        }
    }
}

