package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadoresList

class SharedViewModelGetListaJogadores: ViewModel() {
    var playerList: List<ResponseGetListaJogadoresList>? = null
}