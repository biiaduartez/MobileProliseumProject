package br.senai.sp.jandira.proliseumtcc.gui

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
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeById
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresDentroDeTime
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresDentroDeTimeList
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresInfoPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresList
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresPropostasList
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresPropostasRecebidas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelNomeJogadorListaJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilJogadorOutro
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilOrganizadorOutro
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilOutro
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesPerfilJogadorMeuTime(
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

    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 3 segundos antes de continuar
        delay(3000)
        loading = false
    }


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

                // Se o tempo de espera terminou, continue com a validação do token
                // Restante do código aqui
                val token = sharedViewModelTokenEId.token
                // Restante do seu código de validação do token
                Log.d("CarregarPerfilUsuarioScreen", "Token: $token")

                //val idOutroJogador = sharedViewModelGetListaJogadoresInfoPerfil.idInfoPerfilJogador
                val  idGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.id

                if(token != null && token.isNotEmpty()){

                    val profileOutroJogadorService = RetrofitFactoryCadastro().getJogadoresByIdService()

                    profileOutroJogadorService.getProfileById(idGetMyTeamCompartilhadoPerfilId).enqueue(object : Callback<ProfileResponse> {
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

}