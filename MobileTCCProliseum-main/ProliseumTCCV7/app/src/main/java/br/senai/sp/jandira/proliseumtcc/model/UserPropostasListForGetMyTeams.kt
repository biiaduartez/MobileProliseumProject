package br.senai.sp.jandira.proliseumtcc.model

data class UserPropostasListForGetMyTeams(
    var id: Int,
    var menssagem: String,
    var de: UserPropostasDeListForGetMyTeams?,
)