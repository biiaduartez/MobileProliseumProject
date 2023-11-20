package br.senai.sp.jandira.proliseumtcc.model

data class PostagemJogadorResponse(
    val descricao: String?,
    val jogo: Int?,
    val funcao: Int?,
    val elo: Int?,
    val hora: String?,
    val tipo: Boolean?,
    val pros: String?,
    val dono_id: PostagemJogadorResponseDonoId?,
    val id: Int?
)
