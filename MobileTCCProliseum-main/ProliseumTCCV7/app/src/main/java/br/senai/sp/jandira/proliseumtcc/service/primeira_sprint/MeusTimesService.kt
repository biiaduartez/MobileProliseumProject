package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.MeusTimes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MeusTimesService {

    @GET("team/myteams")
    fun getMyTeams(@Header("Authorization") token: String): Call<MeusTimes>
}