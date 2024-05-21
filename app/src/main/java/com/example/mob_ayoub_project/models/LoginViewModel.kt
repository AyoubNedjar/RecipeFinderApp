package com.example.mob_ayoub_project.models

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.DataPerson
import com.example.mob_ayoub_project.network.login.AuthService
import com.example.mob_ayoub_project.network.login.TokenResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DataPerson())

    //make the data stream read-only
    val uiState: StateFlow<DataPerson> = _uiState.asStateFlow()

    private var access_token: TokenResponse = TokenResponse()


    val _fetchResult = MutableStateFlow(ConnectionResult.UNINITIALIZED)
    val fetchResult: StateFlow<ConnectionResult> = _fetchResult.asStateFlow()

    enum class ConnectionResult {
        SUCCES, ERROR, UNINITIALIZED;
    }


    /**
     *
     */
    fun fetchUserInfos() {
        viewModelScope.launch {

            try {

                Log.i("Pre_Autentification", uiState.value.toString())
                access_token = AuthService.authClient.authenticate(uiState.value)

                if (access_token.access_token != null) {

                    _fetchResult.value = ConnectionResult.SUCCES
                    Log.i("jeton d'acces  : ", access_token.toString())
                    Log.i("resulat apres jeton d'acces : ", fetchResult.value.toString())

                } else {

                    _fetchResult.value = ConnectionResult.ERROR
                    Log.i("Resultat_Valeur", fetchResult.value.toString())
                    Log.e("MainviewModel", "Le token d'accès est null ")
                }

            } catch (httpException: HttpException) {
                _fetchResult.value = ConnectionResult.ERROR

            } catch (e: Exception) {
                _fetchResult.value = ConnectionResult.ERROR
                Log.e("MainviewModel", e.message, e)
            }

        }
        Log.i("verif_resulat_thread_#Login.kt", fetchResult.value.toString())

    }


    fun setEmail(newEmail: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = newEmail
            )
        }
    }

    fun setPasswd(newPswd: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = newPswd
            )
        }
    }

    fun resetAll() {
        _uiState.value = DataPerson()
    }


    /**
     * Receives a string and verifies if it is in email format.
     *
     * @param email The string to be validated as an email.
     * @return True if the string is in email format, false otherwise.
     */
    fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
