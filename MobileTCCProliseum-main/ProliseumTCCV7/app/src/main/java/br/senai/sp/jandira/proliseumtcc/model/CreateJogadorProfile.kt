package br.senai.sp.jandira.proliseumtcc.model

data class CreateJogadorProfile(
    val nickname: String,
    val jogo: String?,
    val funcao: String?,
    val elo: String?
)
