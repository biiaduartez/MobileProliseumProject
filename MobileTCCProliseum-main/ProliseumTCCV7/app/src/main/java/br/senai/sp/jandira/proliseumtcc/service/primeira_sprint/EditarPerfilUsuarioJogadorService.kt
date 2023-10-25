package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilJogador
import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilOrganizacao
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT

interface EditarPerfilUsuarioJogadorService {

    @PUT("organizer")
    fun getProfile(@Header("Authorization") token: String, @Body EditarPerfilJogador: EditarPerfilJogador): Call<EditarPerfilJogador>
}