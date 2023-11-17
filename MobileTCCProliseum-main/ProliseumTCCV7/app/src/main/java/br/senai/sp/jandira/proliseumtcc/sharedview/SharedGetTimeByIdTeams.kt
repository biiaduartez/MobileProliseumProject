package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.MeusTimes
import br.senai.sp.jandira.proliseumtcc.model.TimeForGetMyTeams
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsPropostas

class SharedGetTimeByIdTeams: ViewModel() {

    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var organizacao: getTimeByIdTeamsOrganizacao? = null
    var jogadores: List<getTimeByIdTeamsJogadores>? = null
    var propostas: List<getTimeByIdTeamsPropostas>? = null


//    // Utilize um MutableState para armazenar o ID do time selecionado
//    var selectedJogadorIdTimeById by mutableStateOf<Int?>(null)
//
//    fun getTeamByIdJogadores(idJogador: Int): getTimeByIdTeamsJogadores? {
//        return jogadores?.find { it.id == idJogador }
//    }

}