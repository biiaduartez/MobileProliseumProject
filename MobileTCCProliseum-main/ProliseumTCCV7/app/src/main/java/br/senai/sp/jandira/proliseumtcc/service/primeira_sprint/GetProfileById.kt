package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GetProfileById {

    @GET("profile/{id}")
    fun getProfileById(@Path("id") id: Int?): Call<ProfileResponse>
}