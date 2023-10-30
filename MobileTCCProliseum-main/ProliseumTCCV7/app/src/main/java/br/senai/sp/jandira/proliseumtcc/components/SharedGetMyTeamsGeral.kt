package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.MeusTimes
import br.senai.sp.jandira.proliseumtcc.model.TimeForGetMyTeams

class SharedGetMyTeamsGeral: ViewModel() {
    var myTeamsDadosGeral: MeusTimes? = null
    var myTeamsDadosTime: List<TimeForGetMyTeams>? = null
}