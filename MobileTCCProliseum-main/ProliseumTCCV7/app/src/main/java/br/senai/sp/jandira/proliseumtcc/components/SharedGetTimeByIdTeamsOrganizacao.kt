package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsOrganizacaoDonoId

class SharedGetTimeByIdTeamsOrganizacao: ViewModel() {

    var id: Int? = 0
    var nome_organizacao: String? = ""
    var biografia: String? = ""
    var dono_id: getTimeByIdTeamsOrganizacaoDonoId? = null
}