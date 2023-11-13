package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsJogadoresPerfilId

class SharedGetTimeByIdTeamsJogadores: ViewModel() {

    var id: Int? = 0
    var nickname: String? = ""
    var jogo: Int? = 0
    var funcao: Int? = 0
    var elo: Int? = 0
    var perfil_id: getTimeByIdTeamsJogadoresPerfilId? = null
}