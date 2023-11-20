package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.CreateOrgProfile
import br.senai.sp.jandira.proliseumtcc.model.PostagemJogador
import br.senai.sp.jandira.proliseumtcc.model.PostagemJogadorResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PostagemJogadorService {




    @POST("post")
    fun postagemJogadorService(
        @Header("Authorization") token: String,
        @Body postagemJogador: PostagemJogador
    ): Call<PostagemJogadorResponse>
}