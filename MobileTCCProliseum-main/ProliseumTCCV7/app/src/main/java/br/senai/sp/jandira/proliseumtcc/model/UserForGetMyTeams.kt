package br.senai.sp.jandira.proliseumtcc.model

data class UserForGetMyTeams(
    var id: Int,
    var nome_usuario: String?,
    var nome_completo: String,
    var email: String?,
    var data_nascimento: String,
    var genero: Int?,
    var nickname: String,
    var biografia: String?,
    var propostas: List<UserPropostasListForGetMyTeams>?,
)
