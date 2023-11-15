package br.senai.sp.jandira.proliseumtcc.model

data class getTimeTeams(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val organizacao: getTimeTeamsOrganizacao?,
    val jogadores: List<getTimeTeamsJogadores>?,
    val propostas: List<getTimeTeamsPropostas>?
)
