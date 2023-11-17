package br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesPerfilUsuarioPadraoScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,
    onNavigate: (String) -> Unit
) {

    //CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 3 segundos antes de continuar
        delay(3000)
        loading = false
    }

    // DESIGN DA TELA
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
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {


            if (loading) {
                // Exibe um indicador de progresso enquanto carrega
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )

                //TOKEN
                val token = sharedViewModelTokenEId.token

                if(token != null && token.isNotEmpty()){

                    val profileService = RetrofitFactoryCadastro().getPerfilUsuarioService()

                    profileService.getProfile("Bearer " + token).enqueue(object : Callback<ProfileResponse> {
                        override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                            if (response.isSuccessful) {
                                Log.d("PerfilUsuarioJogadorScreen", "Resposta bem-sucedida: ${response.code()}")
                                val profileResponseData = response.body()

                                val user = profileResponseData!!.user

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
                                    sharedViewModelPerfilJogador.jogo = playerProfile.jogo!!
                                    sharedViewModelPerfilJogador.funcao = playerProfile.funcao!!
                                    sharedViewModelPerfilJogador.elo = playerProfile.elo!!
                                }

                                if (profileResponseData.orgProfile == null) {

                                    Log.e("NENHUMA ORGANIZACAO","Nenhuma organização para retornar dados")

                                } else if (profileResponseData.orgProfile != null){
                                    val orgProfile = profileResponseData.orgProfile
                                    sharedViewModelPerfilOrganizador.orgProfile = orgProfile
                                    sharedViewModelPerfilOrganizador.nome_organizacao = orgProfile.nome_organizacao
                                    sharedViewModelPerfilOrganizador.biografia = orgProfile.biografia
                                }


                                Log.d("INFORMAÇOES DE USUARIO 01", "Token: $token, Id: ${sharedViewModelPerfilEditar.id}, Nome de usuario: ${sharedViewModelPerfilEditar.nome_usuario}")
                                Log.d("CarregarPerfilUsuarioScreen", "Resposta corpo bem-sucedida: ${response.code()}")

                                if( sharedViewModelPerfilEditar.id != 0){

                                    Log.d("INFORMAÇOES DE USUARIO 02", "Token: $token, Id: ${sharedViewModelPerfilEditar.id}, Nome de usuario: ${sharedViewModelPerfilEditar.nome_usuario}")

                                    onNavigate("home")
                                }

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

                } else{
                    Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(),
                        color = RedProliseum
                    )
                }
            }
        }
    }
}