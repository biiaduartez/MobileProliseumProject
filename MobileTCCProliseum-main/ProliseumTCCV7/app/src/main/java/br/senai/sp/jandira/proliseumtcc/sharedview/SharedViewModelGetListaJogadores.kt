package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadoresList

class SharedViewModelGetListaJogadores: ViewModel() {
    var playerList by mutableStateOf<List<ResponseGetListaJogadoresList>>(emptyList())
}