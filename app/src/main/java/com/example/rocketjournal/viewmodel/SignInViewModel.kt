package com.example.rocketjournal.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.rocketjournal.model.Repositories.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    private val _password = MutableStateFlow("")
    val password = _password

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onSignIn(navController: NavController, context: Context) {
        viewModelScope.launch {

            val result = authenticationRepository.signIn(
                email = _email.value,
                password = _password.value
            )

            if (result){
                Log.e("SignInViewModel", "Log in successful")
                navController.navigate("home")
            }
            else {
                Log.e("SignInViewModel", "Log in failed")
                Toast.makeText(context, "Log in failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
