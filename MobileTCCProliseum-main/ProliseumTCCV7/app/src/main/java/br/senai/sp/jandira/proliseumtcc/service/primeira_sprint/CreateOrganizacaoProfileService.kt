package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.CreateJogadorProfile
import br.senai.sp.jandira.proliseumtcc.model.CreateOrgProfile
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CreateOrganizacaoProfileService {

        @POST("organizer")
        fun postCreateOrganizacaoProfile(@Header("Authorization") token: String, @Body createOrganizacaoProfile: CreateOrgProfile): Call<CreateOrgProfile>

}