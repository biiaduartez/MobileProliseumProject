package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeams

class SharedGetTime: ViewModel() {
    var teams: List<getTimeTeams>? = null
    var limit: Int? = 0
}