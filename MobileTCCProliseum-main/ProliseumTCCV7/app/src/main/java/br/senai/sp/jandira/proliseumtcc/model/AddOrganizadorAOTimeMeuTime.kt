package br.senai.sp.jandira.proliseumtcc.model

data class AddOrganizadorAOTimeMeuTime(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val organizacao: AddOrganizadorAOTimeOrganizacao?,
    val jogadores: List<AddOrganizadorAOTimeJogadores>?,
    val propostas: List<AddOrganizadorAOTimePropostas>?,

)
