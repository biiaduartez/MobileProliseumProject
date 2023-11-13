package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel

class SharedViewModelPerfilOutro: ViewModel() {
    var id: Int = 0
    var nome_usuario: String = ""
    var nome_completo: String = ""
    var email: String = ""
    var data_nascimento: String = ""
    var genero: Int = 0
    var nickname: String = ""
    var biografia: String = ""
}