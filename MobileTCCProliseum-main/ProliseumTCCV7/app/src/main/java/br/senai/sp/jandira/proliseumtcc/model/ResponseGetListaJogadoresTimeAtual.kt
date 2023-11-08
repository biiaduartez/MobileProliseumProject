package br.senai.sp.jandira.proliseumtcc.model

data class ResponseGetListaJogadoresTimeAtual(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<ResponseGetListaJogadoresTimeAtualListaJogadores>?,
    val propostas: List<ResponseGetListaJogadoresTimeAtualListaPropostas>?,

)
