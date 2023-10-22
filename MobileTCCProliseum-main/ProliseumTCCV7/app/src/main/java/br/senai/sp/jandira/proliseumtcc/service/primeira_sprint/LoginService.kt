package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.Login
import br.senai.sp.jandira.proliseumtcc.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    fun loginUsuario(@Body login: Login): Call<LoginResponse>
}