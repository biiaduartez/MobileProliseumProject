package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.model.getTimeById
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetTimeById {

    @GET("team/{id}")
    fun theGetTimeById(@Path("id") id: Int?): Call<getTimeById>


}