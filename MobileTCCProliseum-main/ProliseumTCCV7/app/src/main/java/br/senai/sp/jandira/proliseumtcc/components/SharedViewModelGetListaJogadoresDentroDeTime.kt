package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadoresTimeAtualListaJogadores

class SharedViewModelGetListaJogadoresDentroDeTime: ViewModel() {
    var infoListaJogadoresDentroDeTime: List<ResponseGetListaJogadoresTimeAtualListaJogadores>? = null
}