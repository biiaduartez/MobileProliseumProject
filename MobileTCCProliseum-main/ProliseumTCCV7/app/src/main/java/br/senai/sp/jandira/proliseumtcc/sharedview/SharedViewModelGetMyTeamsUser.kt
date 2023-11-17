package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel

class SharedViewModelGetMyTeamsUser: ViewModel() {
    var idData: Int? = 0
    var nomeUsuarioData: String? = ""
    var nomeCompletoData: String? = ""
    var emailData: String? = ""
    var dataNascimentoData: String? = ""
    var generoData: Int? = 0
    var nickNameData: String? = ""
    var biografiaData: String? = ""
}