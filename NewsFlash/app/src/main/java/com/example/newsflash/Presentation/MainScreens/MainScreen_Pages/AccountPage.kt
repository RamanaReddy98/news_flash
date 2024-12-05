import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.newsflash.MainActivity
import com.example.newsflash.Presentation.Components.Header
import com.example.newsflash.Presentation.Components.ProfileOption
import com.example.newsflash.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun ProfileScreen(
    navController: NavController = rememberNavController(),

) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    val firebase = FirebaseAuth.getInstance().uid

    Log.d("Firebase", "HomePage: $firebase")


        firebase?.let {
            FirebaseDatabase.getInstance().reference.child("users").child(it).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val snapshot = task.result
                        if (snapshot.exists()) {
                            name.value = snapshot.child("name").value.toString() // Update state
                            email.value = snapshot.child("email").value.toString() // Update state
                            Log.d("MyName", "getName: ${name.value}")
                        } else {
                            println("User not found")
                        }
                    } else {
                        println("Error fetching user: ${task.exception?.message}")
                    }
                }
        }





    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {
        // Header Section
        Header(
            headerText = "Profile",
            onArrowClick = { navController.navigate("Onboard 1") } // Navigate back on arrow click
        )

        // Profile Picture and Info Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture
            AsyncImage(
                model = "https://image.pollinations.ai/prompt/${name.value}",
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
            )

            // Name
            Text(
                text = "${name.value}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Subtitle
            Text(
                text = "News Reader",
                fontSize = 16.sp,
                color = Color.Gray
            )

            // Email
            Text(
                text = "${email.value}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Divider
        Divider(
            color = Color.LightGray.copy(alpha = 0.5f),
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Options Section
        Column(modifier = Modifier.fillMaxWidth()) {
            ProfileOption(
                iconRes = R.drawable.user,
                title = "Personal Details",
                onClick = { /* Navigate to Personal Details */ }
            )
            ProfileOption(
                iconRes = R.drawable.heart,
                title = "Preference Video",
                onClick = { /* Navigate to Preference Video */ }
            )
            ProfileOption(
                iconRes = R.drawable.refer,
                title = "Referral Code",
                onClick = { /* Navigate to Referral Code */ }
            )
            ProfileOption(
                iconRes = R.drawable.help,
                title = "Help Center",
                onClick = { /* Navigate to Help Center */ }
            )
            ProfileOption(
                iconRes = R.drawable.privacy,
                title = "Privacy",
                onClick = { /* Navigate to Privacy Settings */ }
            )
            ProfileOption(
                iconRes = R.drawable.logout, // Replace with a logout icon
                title = "Sign Out",
                onClick = {
                    // Sign out the user
                    signOutUser( navController)
                }
            )
        }
    }
}

// Function to Sign Out the User
fun signOutUser(navController: NavController) {
    val firebaseAuth = FirebaseAuth.getInstance()

    // Sign out the user
    firebaseAuth.signOut()

    // Check if the user is actually signed out
    val currentUser = firebaseAuth.currentUser
    if (currentUser == null) {
        // User successfully signed out
        navController.navigate("Onboard 1") {
            // Clear backstack
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    } else {
        // Debug: User still exists
        Log.e("SignOutError", "User is still signed in: ${currentUser.uid}")
    }
}

