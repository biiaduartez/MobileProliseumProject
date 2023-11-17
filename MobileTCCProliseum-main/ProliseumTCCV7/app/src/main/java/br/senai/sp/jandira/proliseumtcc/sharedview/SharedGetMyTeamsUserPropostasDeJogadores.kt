package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.TimeJogadoresListForGetMyTeams

class SharedGetMyTeamsUserPropostasDeJogadores: ViewModel() {
    var idData: Int? = 0
    var nickNameData: String? = ""
    var jogoData: Int? = 0
    var funcaoData: Int? = 0
    var eloData: Int? = 0

}