package com.example.mob_ayoub_project

import androidx.lifecycle.ViewModel
import com.example.mob_ayoub_project.data.DataPerson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AyoubViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(DataPerson())
    val uiState: StateFlow<DataPerson> = _uiState.asStateFlow()



    fun setEmail(newEmail: String) {
        _uiState.update { currentState ->
            currentState.copy(
               email = newEmail
            )
        }
    }
    fun resetEmail(){
        _uiState.value = DataPerson();
    }
}
