package com.example.newsflash.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val _authState = MutableLiveData<AuthState>()

    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signup(email: String, password: String,name:String) {
        if (email.isEmpty() || password.isEmpty() ) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        } else if (name.isEmpty()){
            _authState.value = AuthState.Error("Name can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        // Store user details in Realtime Database
                        val userDetails = mapOf(
                            "userId" to user.uid,
                            "email" to email,
                            "name" to "Anonymous" // Default name, change if name input is available
                        )

                        database.child("users").child(user.uid).setValue(userDetails)
                            .addOnCompleteListener { dbTask ->
                                if (dbTask.isSuccessful) {
                                    _authState.value = AuthState.Authenticated
                                } else {
                                    _authState.value =
                                        AuthState.Error("Failed to store user details: ${dbTask.exception?.message}")
                                }
                            }
                    } else {
                        _authState.value = AuthState.Error("User creation succeeded, but no user info found")
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}
