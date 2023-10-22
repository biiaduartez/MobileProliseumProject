package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.CreateJogadorProfile
import br.senai.sp.jandira.proliseumtcc.model.Login
import br.senai.sp.jandira.proliseumtcc.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CreateJogadorProfileService {

    @POST("player")
    fun postCreateJogadorProfile(@Header("Authorization") token: String, @Body createProfile: CreateJogadorProfile): Call<CreateJogadorProfile>
}