package br.senai.sp.jandira.proliseumtcc.model

data class AddOrganizadorAOTimeJogadores(
    val id: Int?,
    val nickname: String,
    val jogo: Int?,
    val funcao: Int?,
    val elo: Int?,
    val perfil_id: AddOrganizadorAOTimePerfilId?,
    val time_atual: AddOrganizadorAOTimeTimeAtual?,

)
