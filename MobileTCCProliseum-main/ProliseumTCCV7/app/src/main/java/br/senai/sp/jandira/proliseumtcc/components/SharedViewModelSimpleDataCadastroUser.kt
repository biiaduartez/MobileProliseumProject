package br.senai.sp.jandira.proliseumtcc.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModelSimpleDataCadastroUser: ViewModel() {
    var userName: String by mutableStateOf("")
    var fullName: String by mutableStateOf("")
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
}