package br.senai.sp.jandira.proliseumtcc.model

data class getTimeByIdTeamsJogadores(
    val id: Int?,
    val nickname: String?,
    val jogo: Int?,
    val funcao: Int?,
    val elo: Int?,
    val perfil_id: getTimeByIdTeamsJogadoresPerfilId?,
)
