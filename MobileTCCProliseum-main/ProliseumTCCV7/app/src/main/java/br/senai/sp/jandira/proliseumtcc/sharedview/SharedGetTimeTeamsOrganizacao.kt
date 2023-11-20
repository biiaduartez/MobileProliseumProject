package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeamsDonoId

class SharedGetTimeTeamsOrganizacao: ViewModel() {

    var id: Int? = 0
    var nome_organizacao: String? = ""
    var biografia: String? = ""
    var dono_id: getTimeTeamsDonoId? = null
}