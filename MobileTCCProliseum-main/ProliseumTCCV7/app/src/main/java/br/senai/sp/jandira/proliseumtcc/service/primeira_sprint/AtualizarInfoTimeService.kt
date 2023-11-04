package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.CreateTime
import br.senai.sp.jandira.proliseumtcc.model.infoAtualizarTime
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface AtualizarInfoTimeService {

    @PUT("team/{id}")
    fun postUpdateTime(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body updateTime: infoAtualizarTime
    ): Call<infoAtualizarTime>

}