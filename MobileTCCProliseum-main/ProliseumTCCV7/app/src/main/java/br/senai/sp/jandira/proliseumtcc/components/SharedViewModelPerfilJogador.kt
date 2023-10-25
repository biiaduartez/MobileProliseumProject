package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel

class SharedViewModelPerfilJogador: ViewModel() {
    var id: Int = 0
    var nickname: String = ""
    var jogo: Int = 0
    var funcao: Int = 0
    var elo: Int = 0
}