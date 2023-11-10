package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel

class SharedViewModelGetListaJogadoresInfoPerfil: ViewModel() {
    var idInfoPerfilJogador: Int? = 0
    var nomeUsuarioInfoPerfilJogador: String? = ""
    var nomeCompletoInfoPerfilJogador: String? = ""
    var emailInfoPerfilJogador: String? = ""
    var senhaInfoPerfilJogador: String? = ""
    var dataNascimentoInfoPerfilJogador: String? = ""
    var generoInfoPerfilJogador: Int? = 0
    var nickNameInfoPerfilJogador: String? = ""
    var biografiaInfoPerfilJogador: String? = ""
}