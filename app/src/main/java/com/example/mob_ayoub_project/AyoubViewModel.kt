package com.example.mob_ayoub_project

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.mob_ayoub_project.data.DataPerson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AyoubViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(DataPerson())

    //make the data stream read-only
    val uiState: StateFlow<DataPerson> = _uiState.asStateFlow()



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
    fun resetEmail(){
        _uiState.value = DataPerson();
    }

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
