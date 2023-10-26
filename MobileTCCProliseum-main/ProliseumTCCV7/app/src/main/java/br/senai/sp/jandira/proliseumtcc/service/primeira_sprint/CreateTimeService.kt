package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.CreateOrgProfile
import br.senai.sp.jandira.proliseumtcc.model.CreateTime
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CreateTimeService {

    @POST("team")
    fun postCreateTime(@Header("Authorization") token: String, @Body createTime: CreateTime): Call<CreateTime>
}