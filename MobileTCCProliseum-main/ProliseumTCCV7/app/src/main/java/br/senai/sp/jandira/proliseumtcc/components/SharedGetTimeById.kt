package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeams

class SharedGetTimeById: ViewModel() {
    var teams: List<getTimeByIdTeams>? = null
    var limit: Int? = 0
}