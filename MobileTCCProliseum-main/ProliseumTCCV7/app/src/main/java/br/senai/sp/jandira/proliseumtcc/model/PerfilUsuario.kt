package br.senai.sp.jandira.proliseumtcc.model

data class PerfilUsuario(
    val nome_usuario: String,
    val nome_completo: String?,
    val email: String,
    val senha: String,
    val data_nascimento: String,
    val genero: String?,
    val nickname: String,
    val biografia: String?,
    val id: String?
)
