package br.senai.sp.jandira.proliseumtcc.model

data class getTimeByIdTeamsOrganizacao(
    val id: Int?,
    val nome_organizacao: String?,
    val biografia: String?,
    val dono_id: getTimeByIdTeamsOrganizacaoDonoId?,
)
