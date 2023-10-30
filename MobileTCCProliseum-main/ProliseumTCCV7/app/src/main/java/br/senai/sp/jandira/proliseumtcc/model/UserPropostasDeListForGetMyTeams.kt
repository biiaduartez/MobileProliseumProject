package br.senai.sp.jandira.proliseumtcc.model

data class UserPropostasDeListForGetMyTeams(

    var id: Int,
    var nome_time: String,
    var jogo: Int,
    var biografia: String,
    var jogadores: List<TimeJogadoresListForGetMyTeams>?,
    var jogadores_ativos: List<TimeJogadoresListForGetMyTeams>?,
    var propostas: List<UserPropostasDePropostasListForGetMyTeams>?,
)