package br.senai.sp.jandira.proliseumtcc.components

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadoresTimeAtualListaPropostas

class SharedViewModelGetListaJogadoresPropostasRecebidas: ViewModel() {
    var InfoListaPropostas: List<ResponseGetListaJogadoresTimeAtualListaPropostas>? = null
}