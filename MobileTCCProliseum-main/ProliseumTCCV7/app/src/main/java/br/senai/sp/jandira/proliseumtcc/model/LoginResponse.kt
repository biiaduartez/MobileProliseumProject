package br.senai.sp.jandira.proliseumtcc.model

data class LoginResponse(
    val user: ResponsePerfilUsuario, // O modelo PerfilUsuario deve ser definido
    val token: String
)
