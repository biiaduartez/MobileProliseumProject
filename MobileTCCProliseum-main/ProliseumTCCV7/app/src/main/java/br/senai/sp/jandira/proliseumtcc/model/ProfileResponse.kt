package br.senai.sp.jandira.proliseumtcc.model

data class ProfileResponse(
    val user: User,
    val playerProfile: PlayerProfileResponse?,
    val orgProfile: CreateOrgProfile?
)
