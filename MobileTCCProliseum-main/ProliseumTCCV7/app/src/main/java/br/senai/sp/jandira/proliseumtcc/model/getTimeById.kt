package br.senai.sp.jandira.proliseumtcc.model

data class getTimeById(
    val teams: List<getTimeByIdTeams>?,
    val limit: Int?,
)
