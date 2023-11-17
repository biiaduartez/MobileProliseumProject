package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeams

class SharedGetTimeById: ViewModel() {
    var teams: List<getTimeByIdTeams>? = null
    var limit: Int? = 0
}