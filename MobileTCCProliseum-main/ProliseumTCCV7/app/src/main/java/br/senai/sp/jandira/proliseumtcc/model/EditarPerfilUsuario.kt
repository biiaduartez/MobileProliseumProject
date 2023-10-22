package br.senai.sp.jandira.proliseumtcc.model

data class EditarPerfilUsuario(
    val nome_usuario: String,
    val nome_completo: String?,
    val data_nascimento: String,
    val genero: Int?,
    val nickname: String,
    val biografia: String
)
