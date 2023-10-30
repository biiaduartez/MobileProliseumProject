package br.senai.sp.jandira.proliseumtcc.model

data class TimeForGetMyTeams(
    val id: Int,
    val nome_time: String?,
    val jogo: Int,
    val biografia: String?,
    val jogadores: List<TimeJogadoresListForGetMyTeams>?,
    val jogadores_ativos: List<TimeJogadoresListForGetMyTeams>?,
    val propostas: List<UserPropostasDePropostasListForGetMyTeams>?,
)
