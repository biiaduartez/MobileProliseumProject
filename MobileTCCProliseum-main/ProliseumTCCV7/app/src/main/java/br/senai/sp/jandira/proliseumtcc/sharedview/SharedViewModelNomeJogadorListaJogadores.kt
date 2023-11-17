package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class SharedViewModelNomeJogadorListaJogadores: ViewModel() {
    var perPage: Int? = 0
    var page: Int? = 0
    var name: String? = ""
}