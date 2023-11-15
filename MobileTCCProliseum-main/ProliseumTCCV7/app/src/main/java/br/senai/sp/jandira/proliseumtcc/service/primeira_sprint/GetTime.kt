package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.model.getTime
import br.senai.sp.jandira.proliseumtcc.model.getTimeById
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetTime {

    @GET("team")
    fun theGetTime(@Query("name") nome: String?): Call<getTime>


}