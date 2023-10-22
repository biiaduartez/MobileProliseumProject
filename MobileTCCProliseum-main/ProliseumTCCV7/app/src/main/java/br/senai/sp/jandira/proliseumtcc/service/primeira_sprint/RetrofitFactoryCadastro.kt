package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitFactoryCadastro {

    private val URL_BASE = "https://proliseum-back.cyclic.app/"

    //Guarda a conexão com o servidor, devolve a conexão
    private val retrofitFactoryCadastro = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitFactoryCadastro(): Retrofit {
        return retrofitFactoryCadastro
    }

    fun postLoginService(): Retrofit{
        return retrofitFactoryCadastro
    }

    // Adicione um método para obter o serviço PerfilUsuarioService com o token
    fun getPerfilUsuarioService(): ProfileService {
//        val client = OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                // Adicione o token de autorização ao cabeçalho de cada solicitação
//                val request = chain.request()
//                    .newBuilder()
//                    .build()
//                chain.proceed(request)
//            }
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(URL_BASE)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)  // Use o cliente com o token no cabeçalho
//            .build()

        return retrofitFactoryCadastro.create(ProfileService::class.java)

    }

    fun putEditarPerfilUsuarioService(): EditarPerfilUsuarioService {
//        val client = OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                // Adicione o token de autorização ao cabeçalho de cada solicitação
//                val request = chain.request()
//                    .newBuilder()
//                    .build()
//                chain.proceed(request)
//            }
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(URL_BASE)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)  // Use o cliente com o token no cabeçalho
//            .build()

        return retrofitFactoryCadastro.create(EditarPerfilUsuarioService::class.java)
    }

    fun createJogadorProfileService(): CreateJogadorProfileService{
//        val client = OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                // Adicione o token de autorização ao cabeçalho de cada solicitação
//                val request = chain.request()
//                    .newBuilder()
//                    .build()
//                chain.proceed(request)
//            }
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(URL_BASE)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)  // Use o cliente com o token no cabeçalho
//            .build()

        return retrofitFactoryCadastro.create(CreateJogadorProfileService::class.java)
    }



}