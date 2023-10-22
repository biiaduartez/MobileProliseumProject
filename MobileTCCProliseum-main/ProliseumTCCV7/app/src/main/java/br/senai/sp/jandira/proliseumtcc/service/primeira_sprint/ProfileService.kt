package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileService {

    @GET("profile")
    fun getProfile(@Header("Authorization") token: String): Call<ProfileResponse>
}

