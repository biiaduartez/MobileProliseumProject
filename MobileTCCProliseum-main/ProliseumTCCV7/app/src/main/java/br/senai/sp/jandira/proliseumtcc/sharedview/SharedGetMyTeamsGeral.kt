package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.MeusTimes
import br.senai.sp.jandira.proliseumtcc.model.TimeForGetMyTeams

class SharedGetMyTeamsGeral: ViewModel() {
    var myTeamsDadosGeral: MeusTimes? = null
    var time: List<TimeForGetMyTeams>? = null

    // Utilize um MutableState para armazenar o ID do time selecionado
    var selectedTimeId by mutableStateOf<Int?>(null)

    fun getTeamById(teamId: Int): TimeForGetMyTeams? {
        return time?.find { it.id == teamId }
    }
}