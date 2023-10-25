package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilOrganizacao
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Header

interface DeleteOrganizacaoService {

    @DELETE("organizer")
    fun deleteProfileOrganizador(@Header("Authorization") token: String): Call<EditarPerfilOrganizacao>
}