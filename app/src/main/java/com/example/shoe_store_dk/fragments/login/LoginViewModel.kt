package com.example.shoe_store_dk.fragments.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {

    companion object {
        const val MAX_EMAIL_LENGTH = 30
        const val MAX_PASSWORD_LENGTH = 15
        const val MIM_PASSWORD_LENGTH = 4
    }

    val authenticationState = MutableLiveData<AuthenticationState>()

    // As an example https://developer.android.com/guide/navigation/navigation-conditional was used
    // Since login functionality is not the a main focus of the app. At the moment it implements a simple (dummy) login with only two states.
    enum class AuthenticationState {
        UNAUTHENTICATED, // The user enters the app for the first time or the user logged out
        AUTHENTICATED // The user is authenticated and saved
    }

    init {
        authenticationState.value =
            AuthenticationState.UNAUTHENTICATED  // User is not authenticated on the activity start
    }

    /**
     * When user enters the correct credentials, the authentications sate is set to AUTHENTICATED
     */
    fun authenticate() {
        authenticationState.value = AuthenticationState.AUTHENTICATED
    }

    fun validateEmail(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && email.length <= MAX_EMAIL_LENGTH
                && password.trim().length <= MAX_PASSWORD_LENGTH
                && password.trim().length >= MIM_PASSWORD_LENGTH
    }
}


