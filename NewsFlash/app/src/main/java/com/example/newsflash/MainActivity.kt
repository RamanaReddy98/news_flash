package com.example.newsflash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsflash.Presentation.Authentication.ForgotPassword
import com.example.newsflash.Presentation.Authentication.Sign_Up
import com.example.newsflash.Presentation.Authentication.Signin_UI
import com.example.newsflash.Presentation.MainScreens.MainScreen
import com.example.newsflash.Presentation.MainScreens.NewsDetailPage
import com.example.newsflash.Presentation.Onboard.SigninOnboard
import com.example.newsflash.ui.theme.NewsFlashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen=installSplashScreen()
        setContent {
            NewsFlashTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Onboard 1" , builder = {
                        composable("Onboard 1") {
                            SigninOnboard(navController)
                        }
                        composable("Sign_In_Page"){
                            Signin_UI(navController)
                        }
                        composable("SignUp"){
                            Sign_Up(navController)
                        }
                        composable("Forgot_password"){
                            ForgotPassword(navController)
                        }
                        composable ("Main_Screen"){
                            MainScreen(navController)
                        }
                        composable(
                            "News_Details/{newsId}/{newsTitle}/{content}/{img}",
                            arguments = listOf(
                                navArgument("newsId") { type = NavType.StringType },
                                navArgument("newsTitle") { type = NavType.StringType },
                                navArgument("content") { type = NavType.StringType },
                                navArgument("img") { type = NavType.StringType }
                            )
                        ) {
                                backStackEntry ->
                            val newsId = backStackEntry.arguments?.getString("newsId") ?: ""
                            val newsTitle = backStackEntry.arguments?.getString("newsTitle") ?: ""
                            val content = backStackEntry.arguments?.getString("content") ?: ""
                            val img = backStackEntry.arguments?.getString("img") ?: ""
                            NewsDetailPage(navController, newsId, newsTitle,content,img)
                        }


                    }
                    )

                }
            }
        }
    }
}

