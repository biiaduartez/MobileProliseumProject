package br.senai.sp.jandira.proliseumtcc.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.MeusTimes
import br.senai.sp.jandira.proliseumtcc.model.TimeForGetMyTeams
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeams

class SharedGetTime: ViewModel() {
    var teams: List<getTimeTeams>? = null
    var limit: Int? = 0

    // Utilize um MutableState para armazenar o ID do time selecionado
    var selectedTimeFilterId by mutableStateOf<Int?>(null)

    fun getTeamFilter(teamId: Int): getTimeTeams? {
        return teams?.find { it.id == teamId }
    }
}