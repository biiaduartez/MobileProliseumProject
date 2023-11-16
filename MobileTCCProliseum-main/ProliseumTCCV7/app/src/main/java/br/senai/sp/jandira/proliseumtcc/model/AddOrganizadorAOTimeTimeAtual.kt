package br.senai.sp.jandira.proliseumtcc.model

data class AddOrganizadorAOTimeTimeAtual(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<AddOrganizadorAOTimeTimeAtualJogadores>?,
    val propostas: List<AddOrganizadorAOTimePropostas>?,



)
