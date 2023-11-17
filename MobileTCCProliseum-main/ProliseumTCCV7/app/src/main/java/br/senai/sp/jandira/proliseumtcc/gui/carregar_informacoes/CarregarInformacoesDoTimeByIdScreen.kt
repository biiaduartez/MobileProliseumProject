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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.getTimeById
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesDoTimeByIdScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditarOutro: SharedViewModelPerfilOutro,
    sharedViewModelPerfilJogadorOutro: SharedViewModelPerfilJogadorOutro,
    sharedViewModelPerfilOrganizadorOutro: SharedViewModelPerfilOrganizadorOutro,

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

    // DESIGN TELA
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

                val selectedTimeId by remember { mutableStateOf(sharedGetMyTeamsGeral.selectedTimeId) }
                Log.e("ID DO TIME COMPARTILHADO","ID compartilhado ${selectedTimeId}")


                val team = selectedTimeId?.let { sharedGetMyTeamsGeral.getTeamById(it) }
                Log.e("ID DO TIME ESCOLHIDO","o id do time da tela PerfilTime ${team}")

                val idOutroJogador = sharedViewModelGetListaJogadoresInfoPerfil.idInfoPerfilJogador

                if (team != null) {
                    Log.e("TIME ID","id do time da tela carregarInformacoesDoTimeById ${team.id}")
                }

                if(token != null && token.isNotEmpty()){

                    val perfilTimeByIdService = RetrofitFactoryCadastro().theGetTimeByIdService()

                    if (team != null) {
                        perfilTimeByIdService.theGetTimeById(team.id).enqueue(object : Callback<getTimeById> {
                            override fun onResponse(call: Call<getTimeById>, response: Response<getTimeById>) {
                                if (response.isSuccessful) {
                                    Log.d("GET TIME BY ID CERTO", "Resposta bem-sucedida: ${response.code()}")
                                    val profileTimeResponseData = response.body()

                                    val timeByIdResponse = profileTimeResponseData!!.teams

                                    sharedGetTimeById.teams = timeByIdResponse

                                    if(timeByIdResponse != null){
                                        for ( time in timeByIdResponse){

                                            val idTimeInfoTime = time.id
                                            val nomeTimeInfoTime = time.nome_time
                                            val jogoTimeInfoTime = time.jogo
                                            val biografiaTimeInfoTime = time.biografia

                                            sharedGetTimeByIdTeams.id = time.id
                                            sharedGetTimeByIdTeams.nome_time = time.nome_time
                                            sharedGetTimeByIdTeams.jogo = time.jogo
                                            sharedGetTimeByIdTeams.biografia = time.biografia

                                            val organizacaoTimeById = time.organizacao

                                            if(organizacaoTimeById != null){
                                                val idInfoOrganizacaoTimeById = organizacaoTimeById.id
                                                val nomeOrganizacaoInfoOrganizacaoTimeById = organizacaoTimeById.nome_organizacao
                                                val biografiaInfoOrganizacaoTimeById = organizacaoTimeById.biografia

                                                sharedGetTimeByIdTeamsOrganizacao.id = organizacaoTimeById.id
                                                sharedGetTimeByIdTeamsOrganizacao.nome_organizacao = organizacaoTimeById.nome_organizacao
                                                sharedGetTimeByIdTeamsOrganizacao.biografia = organizacaoTimeById.biografia


                                                val donoOrganizacaoTimeById = organizacaoTimeById.dono_id

                                                sharedGetTimeByIdTeamsOrganizacao.dono_id = donoOrganizacaoTimeById

                                                if(donoOrganizacaoTimeById != null){
                                                    val idInfoDonoOrganizacaoTimeById = donoOrganizacaoTimeById.id
                                                    val nomeUsuarioInfoDonoOrganizacaoTimeById = donoOrganizacaoTimeById.nome_usuario
                                                    val nomeCompletoInfoDonoOrganizacaoTimeById = donoOrganizacaoTimeById.nome_completo
                                                    val emailInfoDonoOrganizacaoTimeById = donoOrganizacaoTimeById.email
                                                    val senhaInfoDonoOrganizacaoTimeById = donoOrganizacaoTimeById.senha
                                                    val dataNascimentoInfoDonoOrganizacaoTimeById = donoOrganizacaoTimeById.data_nascimento
                                                    val generoInfoDonoOrganizacaoTimeById = donoOrganizacaoTimeById.genero
                                                    val nicknameInfoDonoOrganizacaoTimeById = donoOrganizacaoTimeById.nickname
                                                    val biografiaInfoDonoOrganizacaoTimeById = donoOrganizacaoTimeById.biografia

                                                    sharedGetTimeByIdOrganizacaoDonoId.id = donoOrganizacaoTimeById.id
                                                    sharedGetTimeByIdOrganizacaoDonoId.nome_usuario = donoOrganizacaoTimeById.nome_usuario
                                                    sharedGetTimeByIdOrganizacaoDonoId.nome_completo = donoOrganizacaoTimeById.nome_completo
                                                    sharedGetTimeByIdOrganizacaoDonoId.email = donoOrganizacaoTimeById.email
                                                    sharedGetTimeByIdOrganizacaoDonoId.senha = donoOrganizacaoTimeById.senha
                                                    sharedGetTimeByIdOrganizacaoDonoId.data_nascimento = donoOrganizacaoTimeById.data_nascimento
                                                    sharedGetTimeByIdOrganizacaoDonoId.genero = donoOrganizacaoTimeById.genero
                                                    sharedGetTimeByIdOrganizacaoDonoId.nickname = donoOrganizacaoTimeById.nickname
                                                    sharedGetTimeByIdOrganizacaoDonoId.biografia = donoOrganizacaoTimeById.biografia
                                                }
                                            }

                                            val jogadoresTimeById = time.jogadores

                                            sharedGetTimeByIdTeams.jogadores = jogadoresTimeById


                                            if(jogadoresTimeById != null){
                                                for(jogadoresTime in jogadoresTimeById) {
                                                    val idInfoJogadoresTimeById = jogadoresTime.id
                                                    val nickNameInfoJogadoresTimeById = jogadoresTime.nickname
                                                    val jogoInfoJogadoresTimeById = jogadoresTime.jogo
                                                    val funcaoInfoJogadoresTimeById = jogadoresTime.funcao
                                                    val eloInfoJogadoresTimeById = jogadoresTime.elo

                                                    sharedGetTimeByIdTeamsJogadores.id
                                                    sharedGetTimeByIdTeamsJogadores.nickname
                                                    sharedGetTimeByIdTeamsJogadores.jogo
                                                    sharedGetTimeByIdTeamsJogadores.funcao
                                                    sharedGetTimeByIdTeamsJogadores.elo

                                                    val perfil_idInfoJogadoresTimeById = jogadoresTime.perfil_id

                                                    sharedGetTimeByIdTeamsJogadores.perfil_id = perfil_idInfoJogadoresTimeById

                                                    if(perfil_idInfoJogadoresTimeById != null){
                                                        val idInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTimeById.id
                                                        val nomeUsuarioInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTimeById.nome_usuario
                                                        val nomeCompletoInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTimeById.nome_completo
                                                        val emailInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTimeById.email
                                                        val senhaInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTimeById.senha
                                                        val dataNascimentoInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTimeById.data_nascimento
                                                        val generoInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTimeById.genero
                                                        val nicknameInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTimeById.nickname
                                                        val biografiaInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTimeById.biografia

                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.id = perfil_idInfoJogadoresTimeById.id
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.nome_usuario = perfil_idInfoJogadoresTimeById.nome_usuario
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.nome_completo = perfil_idInfoJogadoresTimeById.nome_completo
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.email = perfil_idInfoJogadoresTimeById.email
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.senha = perfil_idInfoJogadoresTimeById.senha
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.data_nascimento = perfil_idInfoJogadoresTimeById.data_nascimento
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.genero = perfil_idInfoJogadoresTimeById.genero
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.nickname = perfil_idInfoJogadoresTimeById.nickname
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.biografia = perfil_idInfoJogadoresTimeById.biografia
                                                    }
                                                }

                                            }

                                            val propostasTimeById = time.propostas

                                            if(propostasTimeById != null){
                                                for(propostaTimeById in propostasTimeById){
                                                    val idInfoPropostasTimeById = propostaTimeById.id
                                                    val menssagemInfoPropostasTimeById = propostaTimeById.menssagem

                                                    sharedGetTimeByIdTeamsPropostas.id = propostaTimeById.id
                                                    sharedGetTimeByIdTeamsPropostas.menssagem = propostaTimeById.menssagem
                                                }
                                            }
                                        }
                                    }

                                    val jogadorIdCompartilhado = sharedGetTimeByIdOrganizacaoDonoId.id // Obtenha o ID do time clicado

                                        onNavigate("perfil_time")



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

                            override fun onFailure(call: Call<getTimeById>, t: Throwable) {
                                // Trate o erro de falha na rede.
                                Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
                            }

                        })
                    }

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
