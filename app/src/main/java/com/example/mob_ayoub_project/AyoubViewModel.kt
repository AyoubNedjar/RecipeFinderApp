package com.example.mob_ayoub_project

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob_ayoub_project.data.DataPerson
import com.example.mob_ayoub_project.network.AuthService
import com.example.mob_ayoub_project.network.TokenResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class AyoubViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(DataPerson())

    //make the data stream read-only
    val uiState: StateFlow<DataPerson> = _uiState.asStateFlow()

    var access_token: TokenResponse = TokenResponse()

    val fetchResult = mutableStateOf(ConnectionResult.UNINITIALIZED)
    enum class ConnectionResult {
        SUCCES, ERROR, UNINITIALIZED;
    }

    fun fetchUserInfos(){
        viewModelScope.launch{

            try {

                access_token = AuthService.authClient.authenticate(uiState.value)


                if (access_token.access_token != null) {

                    fetchResult.value = ConnectionResult.SUCCES
                    //Log.i("MainviewModel", access_token.toString())

                } else {
                    fetchResult.value = ConnectionResult.ERROR
                    Log.e("MainviewModel", "Le token d'accès est null ")
                }

            }catch (httpException: HttpException){
                fetchResult.value = ConnectionResult.ERROR

            } catch (e: Exception) {
                fetchResult.value = ConnectionResult.ERROR
                Log.e("MainviewModel", e.message, e)
            }

        }
    }


    fun setEmail(newEmail: String) {
        _uiState.update { currentState ->
            currentState.copy(
               email = newEmail
            )
        }
    }
    fun setPasswd(newPswd : String){
        _uiState.update { currentState ->
            currentState.copy(
                password = newPswd
            )
        }
    }
    fun resetAll(){
        _uiState.value = DataPerson()
    }
    /*

    58183@etu.he2b.be     email ok
    nnnnn                  psw  = pas ok donc met a faux





     */

    /**
     * Receives a string and verifies if it is in email format.
     *
     * @param email The string to be validated as an email.
     * @return True if the string is in email format, false otherwise.
     */
    fun validateEmail(email : String) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
