package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GetJogadores
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.model.infoAtualizarTime
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface GetJogadoresService {

    @GET("player")
    fun getListajogadores(
        @Query("perPage") perPage: Int?,
        @Query("page") page: Int?,
        @Query("name") name: String?,
        @Body getJogadores: GetJogadores
    ): Call<ResponseGetListaJogadores>


}