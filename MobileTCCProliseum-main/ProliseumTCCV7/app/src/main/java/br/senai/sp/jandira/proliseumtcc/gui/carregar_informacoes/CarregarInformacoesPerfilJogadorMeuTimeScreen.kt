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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeById
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresDentroDeTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresDentroDeTimeList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresInfoPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresPropostasList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresPropostasRecebidas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelNomeJogadorListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOutro
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
fun CarregarInformacoesPerfilJogadorMeuTimeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditarOutro: SharedViewModelPerfilOutro,
    sharedViewModelPerfilJogadorOutro: SharedViewModelPerfilJogadorOutro,
    sharedViewModelPerfilOrganizadorOutro: SharedViewModelPerfilOrganizadorOutro,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,

    // SharedViewModel GET MY TEAMS GERAL
    sharedGetMyTeamsGeral: SharedGetMyTeamsGeral,

    // SharedViewModelGetMyTeams de USUARIO
    sharedViewModelGetMyTeamsUser: SharedViewModelGetMyTeamsUser,
    sharedViewModelGetMyTeamsUserPropostas: SharedViewModelGetMyTeamsUserPropostas,
    sharedViewModelGetMyTeamsUserPropostasDe: SharedGetMyTeamsUserPropostasDe,
    sharedViewModelGetMyTeamsUserPropostasDeJogadores: SharedGetMyTeamsUserPropostasDeJogadores,
    sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos: SharedGetMyTeamsUserPropostasDeJogadoresAtivos,
    sharedViewModelGetMyTeamsUserPropostasDePropostas: SharedGetMyTeamsUserPropostasDePropostas,

    // SharedViewModelGetMyTeams de TIME
    sharedViewModelGetMyTeamsTime: SharedViewModelGetMyTeamsTime,
    sharedViewModelGetMyTeamsTimeJogadores: SharedViewModelGetMyTeamsTimeJogadores,
    sharedViewModelGetMyTeamsTimeJogadoresAtivos: SharedGetMyTeamsTimeJogadoresAtivos,
    sharedViewModelGetMyTeamsTimePropostas: SharedViewModelGetMyTeamsTimePropostas,

    sharedViewModelNomeJogadorListaJogadores: SharedViewModelNomeJogadorListaJogadores,
    sharedViewModelGetListaJogadores: SharedViewModelGetListaJogadores,
    sharedViewModelGetListaJogadoresList: SharedViewModelGetListaJogadoresList,
    sharedViewModelGetListaJogadoresInfoPerfil: SharedViewModelGetListaJogadoresInfoPerfil,
    sharedViewModelGetListaJogadoresTimeAtual: SharedViewModelGetListaJogadoresTimeAtual,
    sharedViewModelGetListaJogadoresDentroDeTime: SharedViewModelGetListaJogadoresDentroDeTime,
    sharedViewModelGetListaJogadoresDentroDeTimeList: SharedViewModelGetListaJogadoresDentroDeTimeList,
    sharedViewModelGetListaJogadoresPropostasList: SharedViewModelGetListaJogadoresPropostasList,
    sharedViewModelGetListaJogadoresPropostasRecebidas: SharedViewModelGetListaJogadoresPropostasRecebidas,

    // SharedViewModel GET TIME BY ID
    sharedGetTimeById: SharedGetTimeById,
    sharedGetTimeByIdTeams: SharedGetTimeByIdTeams,
    sharedGetTimeByIdTeamsJogadores: SharedGetTimeByIdTeamsJogadores,
    sharedGetTimeByIdTeamsJogadoresPerfilId: SharedGetTimeByIdTeamsJogadoresPerfilId,
    sharedGetTimeByIdTeamsOrganizacao: SharedGetTimeByIdTeamsOrganizacao,
    sharedGetTimeByIdOrganizacaoDonoId: SharedGetTimeByIdOrganizacaoDonoId,
    sharedGetTimeByIdTeamsPropostas: SharedGetTimeByIdTeamsPropostas,
    onNavigate: (String) -> Unit
) {

    // CARREGAR TELA
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

                val  idGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.id

                if(token != null && token.isNotEmpty()){

                    val profileOutroJogadorService = RetrofitFactoryCadastro().getJogadoresByIdService()

                    profileOutroJogadorService.getProfileById(idGetMyTeamCompartilhadoPerfilId).enqueue(object : Callback<ProfileResponse> {
                        override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                            if (response.isSuccessful) {
                                Log.d("PerfilUsuarioJogadorScreen", "Resposta bem-sucedida: ${response.code()}")
                                val profileResponseData = response.body()

                                val user = profileResponseData!!.user

                                sharedViewModelPerfilEditarOutro.id = user.id
                                sharedViewModelPerfilEditarOutro.nome_usuario = user.nome_usuario
                                sharedViewModelPerfilEditarOutro.nome_completo = user.nome_completo.toString()
                                sharedViewModelPerfilEditarOutro.email = user.email
                                sharedViewModelPerfilEditarOutro.data_nascimento = user.data_nascimento
                                sharedViewModelPerfilEditarOutro.genero = user.genero
                                sharedViewModelPerfilEditarOutro.nickname = user.nickname
                                sharedViewModelPerfilEditarOutro.biografia = user.biografia

                                if (profileResponseData.playerProfile != null) {
                                    val playerProfile = profileResponseData.playerProfile
                                    sharedViewModelPerfilJogadorOutro.id = playerProfile.id
                                    sharedViewModelPerfilJogadorOutro.nickname = playerProfile.nickname
                                    sharedViewModelPerfilJogadorOutro.jogo = playerProfile.jogo!!
                                    sharedViewModelPerfilJogadorOutro.funcao = playerProfile.funcao!!
                                    sharedViewModelPerfilJogadorOutro.elo = playerProfile.elo!!
                                }

                                if (profileResponseData.orgProfile == null) {

                                    Log.e("NENHUMA ORGANIZACAO","Nenhuma organização para retornar dados")

                                } else if (profileResponseData.orgProfile != null){
                                    val orgProfile = profileResponseData.orgProfile
                                    sharedViewModelPerfilOrganizadorOutro.orgProfile = orgProfile
                                    sharedViewModelPerfilOrganizadorOutro.nome_organizacao = orgProfile.nome_organizacao
                                    sharedViewModelPerfilOrganizadorOutro.biografia = orgProfile.biografia
                                }



                                Log.d("INFORMAÇOES DE USUARIO 01", "Token: $token, Id: ${sharedViewModelPerfilEditarOutro.id}, Nome de usuario: ${sharedViewModelPerfilEditarOutro.nome_usuario}")
                                Log.d("CarregarPerfilUsuarioScreen", "Resposta corpo bem-sucedida: ${response.code()}")

                                if( idGetMyTeamCompartilhadoPerfilId != 0){

                                    onNavigate("perfil_jogador_do_meu_time")
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