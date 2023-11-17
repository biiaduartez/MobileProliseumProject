package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadoresTimeAtualListaPropostas

class SharedViewModelGetListaJogadoresPropostasRecebidas: ViewModel() {
    var InfoListaPropostas: List<ResponseGetListaJogadoresTimeAtualListaPropostas>? = null
}