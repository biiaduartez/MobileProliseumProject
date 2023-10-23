package br.senai.sp.jandira.proliseumtcc.gui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesPerfilUsuario(sharedViewModelTokenEId: SharedViewTokenEId, sharedViewModelPerfilEditar: SharedViewModelPerfil, sharedViewModelPerfilJogador: SharedViewModelPerfilJogador, onNavigate: (String) -> Unit) {



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        AzulEscuroProliseum, AzulEscuroProliseum
                    )
                )
            )
    ) {
        // Imagem Capa
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            val token = sharedViewModelTokenEId.token
            Log.d("CarregarPerfilUsuarioScreen", "Token: $token")

            if(token != null && token.isNotEmpty()){

                val profileService = RetrofitFactoryCadastro().getPerfilUsuarioService()

                profileService.getProfile("Bearer " + token).enqueue(object : Callback<ProfileResponse> {
                    override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                        if (response.isSuccessful) {
                            Log.d("PerfilUsuarioJogadorScreen", "Resposta bem-sucedida: ${response.code()}")
                            val profileResponseData = response.body()

                            val user = profileResponseData!!.user
//                    idUsuario.value = user.id.toString()
//                    nomeUsuarioPerfil.value = user.nome_usuario
//                    nickNamePerfil.value = user.nickname
//                    emailPerfil.value = user.email
//                    biografiaPerfil.value = user.biografia
//                    generoPerfil.value = user.genero.toString()

                            sharedViewModelPerfilEditar.id = user.id
                            sharedViewModelPerfilEditar.nome_usuario = user.nome_usuario
                            sharedViewModelPerfilEditar.nome_completo = user.nome_completo.toString()
                            sharedViewModelPerfilEditar.email = user.email
                            sharedViewModelPerfilEditar.data_nascimento = user.data_nascimento
                            sharedViewModelPerfilEditar.genero = user.genero
                            sharedViewModelPerfilEditar.nickname = user.nickname
                            sharedViewModelPerfilEditar.biografia = user.biografia

                            if (profileResponseData.playerProfile != null) {
                                val playerProfile = profileResponseData.playerProfile
                                sharedViewModelPerfilJogador.id = playerProfile.id
                                sharedViewModelPerfilJogador.nickname = playerProfile.nickname
                                sharedViewModelPerfilJogador.jogo = playerProfile.jogo
                                sharedViewModelPerfilJogador.funcao = playerProfile.funcao
                                sharedViewModelPerfilJogador.elo = playerProfile.elo
                            }



//                    val storage = Firebase.storage
//
//                    if (idUsuario.value != null && idUsuario.value != "0") {
//                        imageRef.value = storage.reference.child("${idUsuario.value}/profile")
//                    }
//
//                    if (idUsuario.value != null && idUsuario.value != "0") {
//                        imageCapaRef.value = storage.reference.child("${idUsuario.value}/capa")
//                    }
//
//                    if (profileResponseData.playerProfile != null) {
//                        val playerProfile = profileResponseData.playerProfile
//                        nickNameJogadorPerfil.value = playerProfile.nickname
//                        jogoJogadorPerfil.value = playerProfile.jogo.toString()
//                        funcaoJogadorPerfil.value = playerProfile.funcao.toString()
//                        eloJogadorPerfil.value = playerProfile.elo.toString()
//                    }


                            Log.d("INFORMAÇOES DE USUARIO 01", "Token: $token, Id: ${sharedViewModelPerfilEditar.id}, Nome de usuario: ${sharedViewModelPerfilEditar.nome_usuario}")
                            Log.d("CarregarPerfilUsuarioScreen", "Resposta corpo bem-sucedida: ${response.code()}")

                            if( sharedViewModelPerfilEditar.id != 0){

                                Log.d("INFORMAÇOES DE USUARIO 02", "Token: $token, Id: ${sharedViewModelPerfilEditar.id}, Nome de usuario: ${sharedViewModelPerfilEditar.nome_usuario}")

                                onNavigate("home")
                            }

//                            onNavigate("home")
                        } else {
                            // Trate a resposta não bem-sucedida
                            Log.d("PerfilUsuarioJogadorScreen", "Resposta não bem-sucedida: ${response.code()}")
                            // Log de corpo da resposta (se necessário)
                            Log.d(
                                "PerfilUsuarioJogadorScreen",
                                "Corpo da resposta: ${response.errorBody()?.string()}"
                            )
                        }
                    }

                    override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                        // Trate o erro de falha na rede.
                        Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
                    }

                })

                Log.e("SUCESSO","Informações do usuario carregadas com sucesso")
            } else{
                Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
                // Lidar com o caso em que o token é nulo ou vazio
                // Por exemplo, você pode exibir uma mensagem de erro ou redirecionar o usuário para fazer login.
                // Ou então, pode simplesmente não fazer nada.
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )
            }
        }
    }
    
}