package br.senai.sp.jandira.proliseumtcc.sharedview

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.MeusTimes
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadoresList
import br.senai.sp.jandira.proliseumtcc.model.TimeForGetMyTeams
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeamsDonoId
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeamsPropostas

class SharedGetTimeTeams: ViewModel() {

    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var dono: getTimeTeamsDonoId? = null
    var jogadores: List<getTimeTeamsJogadores>? = null
    var propostas: List<getTimeTeamsPropostas>? = null
}

