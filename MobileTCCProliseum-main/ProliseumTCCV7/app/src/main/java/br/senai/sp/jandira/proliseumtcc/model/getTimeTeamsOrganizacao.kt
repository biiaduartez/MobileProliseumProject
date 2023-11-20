package br.senai.sp.jandira.proliseumtcc.model

data class getTimeTeamsOrganizacao(
    val id: Int?,
    val nome_organizacao: String?,
    val biografia: String?,
    val dono_id: getTimeTeamsDonoId?,
)
