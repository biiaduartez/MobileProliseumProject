package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.MeusTimes
import br.senai.sp.jandira.proliseumtcc.model.TimeForGetMyTeams

class SharedGetMyTeamsGeral: ViewModel() {
    var myTeamsDadosGeral: MeusTimes? = null
    var time: List<TimeForGetMyTeams>? = null

    fun getTeamById(teamId: Int): TimeForGetMyTeams? {
        return time?.find { it.id == teamId }
    }
}