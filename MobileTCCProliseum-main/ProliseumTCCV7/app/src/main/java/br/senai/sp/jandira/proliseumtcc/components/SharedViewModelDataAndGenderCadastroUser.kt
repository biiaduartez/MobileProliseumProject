package br.senai.sp.jandira.proliseumtcc.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModelDataAndGenderCadastroUser : ViewModel() {
    var selectedDate: String by mutableStateOf("")
    var selectedGender: String by mutableStateOf("")
    //var selectedGender: Int? by mutableStateOf(null)
}