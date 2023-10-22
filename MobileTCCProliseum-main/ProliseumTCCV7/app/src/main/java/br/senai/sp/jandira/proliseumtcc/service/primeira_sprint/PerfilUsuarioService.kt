package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.PerfilUsuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PerfilUsuarioService {

    //PERFIL JOGADOR
    @POST("register")
    fun postPerfilUsuario(@Body usuario: PerfilUsuario): Call<PerfilUsuario>
}