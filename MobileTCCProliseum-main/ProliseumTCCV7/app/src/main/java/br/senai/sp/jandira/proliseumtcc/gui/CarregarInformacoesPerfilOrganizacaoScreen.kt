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
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.model.MeusTimes
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesPerfilOrganizacaoScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,

    // SharedViewModelGetMyTeams GERAL
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
    onNavigate: (String) -> Unit
) {


    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 5 segundos antes de continuar
        delay(5000)
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

                if (token != null && token.isNotEmpty()){

                    val profileMyTeamsService = RetrofitFactoryCadastro().getMyTeamsService()

                    profileMyTeamsService.getMyTeams("Bearer $token").enqueue(object : Callback<MeusTimes> {
                        override fun onResponse(call: Call<MeusTimes>, response: Response<MeusTimes>) {
                            if (response.isSuccessful) {
                                val profileMyTeamsData = response.body()

                                sharedGetMyTeamsGeral.myTeamsDadosGeral = profileMyTeamsData

                                val dataMyTeamsUser = profileMyTeamsData?.user

                                val dataMyTeamsTeam = profileMyTeamsData?.time

                                sharedGetMyTeamsGeral.time = profileMyTeamsData?.time

                                if (dataMyTeamsUser != null) {
                                    Log.e("ID DO USUARIO", "${dataMyTeamsUser.id}")
                                    val userId = dataMyTeamsUser.id
                                    val userName = dataMyTeamsUser.nome_usuario
                                    val userFullName = dataMyTeamsUser.nome_completo
                                    val userEmail = dataMyTeamsUser.email
                                    val userDataNascimento = dataMyTeamsUser.data_nascimento
                                    val userGenero = dataMyTeamsUser.genero
                                    val userNickName = dataMyTeamsUser.nickname
                                    val userBiografia = dataMyTeamsUser.biografia

                                    // Atualize os objetos no ViewModel, se necessário
                                    sharedViewModelGetMyTeamsUser.idData = dataMyTeamsUser.id
                                    sharedViewModelGetMyTeamsUser.nomeUsuarioData = dataMyTeamsUser.nome_usuario
                                    sharedViewModelGetMyTeamsUser.nomeCompletoData = dataMyTeamsUser.nome_completo
                                    sharedViewModelGetMyTeamsUser.emailData = dataMyTeamsUser.email
                                    sharedViewModelGetMyTeamsUser.dataNascimentoData = dataMyTeamsUser.data_nascimento
                                    sharedViewModelGetMyTeamsUser.generoData = dataMyTeamsUser.genero
                                    sharedViewModelGetMyTeamsUser.nickNameData = dataMyTeamsUser.nickname
                                    sharedViewModelGetMyTeamsUser.biografiaData = dataMyTeamsUser.biografia



                                        val propostas = dataMyTeamsUser.propostas

                                        if (propostas != null) {
                                            for (proposta in propostas) {
                                                val userPropostaId = proposta.id
                                                val userMensagem = proposta.menssagem

                                                sharedViewModelGetMyTeamsUserPropostas.idData = proposta.id
                                                sharedViewModelGetMyTeamsUserPropostas.mensagemData = proposta.menssagem

                                                val userDe = proposta.de

                                                if (userDe != null) {

                                                        val userIdDe = userDe.id
                                                        val userNomeTime = userDe.nome_time
                                                        val userJogo = userDe.jogo
                                                        val userBiografia = userDe.biografia

                                                        sharedViewModelGetMyTeamsUserPropostasDe.idData = userDe.id
                                                        sharedViewModelGetMyTeamsUserPropostasDe.nomeTimeData = userDe.nome_time
                                                        sharedViewModelGetMyTeamsUserPropostasDe.jogoData = userDe.jogo
                                                        sharedViewModelGetMyTeamsUserPropostasDe.biografiaData = userDe.biografia

                                                        val jogadoresList = userDe.jogadores

                                                        sharedViewModelGetMyTeamsTimeJogadores.infoJogadoresEmTime = userDe.jogadores

                                                        if(jogadoresList != null){
                                                            for (jogadoresInfoList in jogadoresList){
                                                                val userJogadorId = jogadoresInfoList.id
                                                                val userJogadorNickName = jogadoresInfoList.nickname
                                                                val userJogadorJogo = jogadoresInfoList.jogo
                                                                val userJogadorFuncao = jogadoresInfoList.funcao
                                                                val userJogadorElo = jogadoresInfoList.elo

                                                                sharedViewModelGetMyTeamsUserPropostasDeJogadores.idData = jogadoresInfoList.id
                                                                sharedViewModelGetMyTeamsUserPropostasDeJogadores.nickNameData = jogadoresInfoList.nickname
                                                                sharedViewModelGetMyTeamsUserPropostasDeJogadores.jogoData = jogadoresInfoList.jogo
                                                                sharedViewModelGetMyTeamsUserPropostasDeJogadores.funcaoData = jogadoresInfoList.funcao
                                                                sharedViewModelGetMyTeamsUserPropostasDeJogadores.eloData = jogadoresInfoList.elo
                                                            }
                                                        }

                                                        val jogadoresAtivosList = userDe.jogadores_ativos

                                                        if(jogadoresAtivosList != null){
                                                            for (jogadoresAtivosInfoList in jogadoresAtivosList){
                                                                val userJogadorAtivoId = jogadoresAtivosInfoList.id
                                                                val userJogadorAtivoNickName = jogadoresAtivosInfoList.nickname
                                                                val userJogadorAtivoJogo = jogadoresAtivosInfoList.jogo
                                                                val userJogadorAtivoFuncao = jogadoresAtivosInfoList.funcao
                                                                val userJogadorAtivoElo = jogadoresAtivosInfoList.elo

                                                                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos.idData = jogadoresAtivosInfoList.id
                                                                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos.nickNameData = jogadoresAtivosInfoList.nickname
                                                                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos.jogoData = jogadoresAtivosInfoList.jogo
                                                                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos.funcaoData = jogadoresAtivosInfoList.funcao
                                                                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos.eloData = jogadoresAtivosInfoList.elo
                                                            }
                                                        }

                                                        val propostasDesseTime = userDe.propostas

                                                        if(propostasDesseTime != null){
                                                            for (propostasInfoList in propostasDesseTime){
                                                                val userPropostaId = propostasInfoList.id
                                                                val userPropostaMensagem = propostasInfoList.menssagem

                                                                sharedViewModelGetMyTeamsUserPropostasDePropostas.idData = propostasInfoList.id
                                                                sharedViewModelGetMyTeamsUserPropostasDePropostas.mensagemData = propostasInfoList.menssagem
                                                            }
                                                        }

                                                        // Faça o que você precisa com os dados de UserPropostasDeListForGetMyTeams

                                                }
                                                // Faça o que você precisa com os dados da proposta
                                            }
                                        }
                                        // Faça o que você precisa com os dados do usuário
                                    }

                                if (dataMyTeamsTeam != null) {
                                    for (times in  dataMyTeamsTeam) {
                                        Log.e("ID DO TIME", "${times.id}")

                                        val timeIdTime = times.id
                                        val timeNomeTime = times.nome_time
                                        val timeJogoTime = times.jogo
                                        val timeBiografiaTime = times.biografia

                                        sharedViewModelGetMyTeamsTime.idData = times.id
                                        sharedViewModelGetMyTeamsTime.nomeTimeData = times.nome_time.toString()
                                        sharedViewModelGetMyTeamsTime.jogoData = times.jogo
                                        sharedViewModelGetMyTeamsTime.biografiaData = times.biografia

                                        val jogadoresTimeList = times.jogadores

                                        if(jogadoresTimeList != null){
                                            for (infoJogadoresTimeList in jogadoresTimeList){
                                                val infoIdJogadorTime = infoJogadoresTimeList.id
                                                val infoNickNameJogadorTime = infoJogadoresTimeList.nickname
                                                val infoJogoJogadorTime = infoJogadoresTimeList.jogo
                                                val infoFuncaoJogadorTime = infoJogadoresTimeList.funcao
                                                val infoEloJogadorTime = infoJogadoresTimeList.elo

                                                sharedViewModelGetMyTeamsTimeJogadores.idData = infoJogadoresTimeList.id
                                                sharedViewModelGetMyTeamsTimeJogadores.nickNameData = infoJogadoresTimeList.nickname
                                                sharedViewModelGetMyTeamsTimeJogadores.jogoData = infoJogadoresTimeList.jogo
                                                sharedViewModelGetMyTeamsTimeJogadores.funcaoData = infoJogadoresTimeList.funcao
                                                sharedViewModelGetMyTeamsTimeJogadores.eloData = infoJogadoresTimeList.elo

                                            }
                                        }

                                        val jogadoresAtivosTimeList = times.jogadores_ativos

                                        if(jogadoresAtivosTimeList != null){
                                            for (infoJogadoresAtivosTimeList in jogadoresAtivosTimeList){
                                                val infoIdJogadorTime = infoJogadoresAtivosTimeList.id
                                                val infoNickNameJogadorTime = infoJogadoresAtivosTimeList.nickname
                                                val infoJogoJogadorTime = infoJogadoresAtivosTimeList.jogo
                                                val infoFuncaoJogadorTime = infoJogadoresAtivosTimeList.funcao
                                                val infoEloJogadorTime = infoJogadoresAtivosTimeList.elo

                                                sharedViewModelGetMyTeamsTimeJogadoresAtivos.idData = infoJogadoresAtivosTimeList.id
                                                sharedViewModelGetMyTeamsTimeJogadoresAtivos.nickNameData = infoJogadoresAtivosTimeList.nickname
                                                sharedViewModelGetMyTeamsTimeJogadoresAtivos.jogoData = infoJogadoresAtivosTimeList.jogo
                                                sharedViewModelGetMyTeamsTimeJogadoresAtivos.funcaoData = infoJogadoresAtivosTimeList.funcao
                                                sharedViewModelGetMyTeamsTimeJogadoresAtivos.eloData = infoJogadoresAtivosTimeList.elo
                                            }
                                        }

                                        val propostasRecebidasJogadoresDentroTimeList = times.propostas

                                        if(propostasRecebidasJogadoresDentroTimeList != null){
                                            for (infoPropostasRecebidasJogadoresDentroTimeList in propostasRecebidasJogadoresDentroTimeList){
                                                val infoPropostaIdJogadorTime = infoPropostasRecebidasJogadoresDentroTimeList.id
                                                val infoPropostaMensagemJogadorTime = infoPropostasRecebidasJogadoresDentroTimeList.menssagem

                                                sharedViewModelGetMyTeamsTimePropostas.idData = infoPropostasRecebidasJogadoresDentroTimeList.id
                                                sharedViewModelGetMyTeamsTimePropostas.mensagemData = infoPropostasRecebidasJogadoresDentroTimeList.menssagem
                                            }
                                        }
                                    }
                                }


                                }

                                if(sharedViewModelGetMyTeamsUser.idData != 0){
                                    onNavigate("perfil_usuario_jogador")
                                    Log.d("CarregarInformacoesPerfilOrganizacaoScreen", "SUCESSO NA NAVEGACAO!  ${sharedViewModelGetMyTeamsUser.idData}")
                                }else{
                                    Log.d("CarregarInformacoesPerfilOrganizacaoScreen", "VISH NAO NAVEGOU ${sharedViewModelGetMyTeamsUser.idData}")
                                }
                            }

                        override fun onFailure(call: Call<MeusTimes>, t: Throwable) {
                            Log.e("CarregarInformacoesPerfilOrganizacaoScreen", "Erro na chamada da API: ${t.message}")
                        }


                    })


                } else {
                    Log.d("CarregarInformacoesPerfilOrganizacaoScreen", "ERRO não entrou no if, token invalido")
                }

            }
        }
    }
}