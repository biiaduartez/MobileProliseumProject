package br.senai.sp.jandira.proliseumtcc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelDataAndGenderCadastroUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelSimpleDataCadastroUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeById
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresDentroDeTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresDentroDeTimeList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresInfoPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresPropostasList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresPropostasRecebidas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelNomeJogadorListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOutro
import br.senai.sp.jandira.proliseumtcc.gui.CampeonatoScreen
import br.senai.sp.jandira.proliseumtcc.gui.CarregarDeletarOrganizacao
import br.senai.sp.jandira.proliseumtcc.gui.CarregarInformacoesDoTimeById
import br.senai.sp.jandira.proliseumtcc.gui.CarregarInformacoesListaTimes
import br.senai.sp.jandira.proliseumtcc.gui.CarregarInformacoesPerfilJogadorMeuTime
import br.senai.sp.jandira.proliseumtcc.gui.CarregarInformacoesPerfilOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.CarregarInformacoesPerfilOutroJogador
import br.senai.sp.jandira.proliseumtcc.gui.CarregarInformacoesPerfilUsuario
import br.senai.sp.jandira.proliseumtcc.gui.HomeScreen
import br.senai.sp.jandira.proliseumtcc.gui.ListaDeJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.ListaDePublicacoesDeJogadores
import br.senai.sp.jandira.proliseumtcc.gui.ListaDePublicacoesDeTimes
import br.senai.sp.jandira.proliseumtcc.gui.ListaDeTimes
import br.senai.sp.jandira.proliseumtcc.gui.LoginScreen
import br.senai.sp.jandira.proliseumtcc.gui.NavigationConfiguracoesMeuTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.NavigationConfiguracoesPerfilScreen
import br.senai.sp.jandira.proliseumtcc.gui.NavigationProliseumScreen
import br.senai.sp.jandira.proliseumtcc.gui.PremiumScreen
import br.senai.sp.jandira.proliseumtcc.gui.PropostasScreen
import br.senai.sp.jandira.proliseumtcc.gui.StartScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroOrganizadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroUsuarioJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroPerfilScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroTipoUsuario
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroUsuarioPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.criar.CriarTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.deletar.DeletarOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.editarPerfil.EditarPerfilJogadorPart1
import br.senai.sp.jandira.proliseumtcc.gui.editarPerfil.EditarPerfilUsuarioPadraoPart1
import br.senai.sp.jandira.proliseumtcc.gui.editarPerfil.EditarPerfilOrganizadorPart1
import br.senai.sp.jandira.proliseumtcc.gui.editarPerfil.EditarPerfilTime
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilDeOutroJogador
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilDeOutroTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilJogadorDoMeuTime
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilUsuarioJogadorScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProliseumTCCTheme {
                MainScreen()
            }
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        //VÁRIAVEL PARA LEMBRAR DA TELA ATUAL
        var currentScreen by remember { mutableStateOf("start") }


        /*****************************************************************************************/
        // SHARED VIEW MODEL
        //SERVE PARA FAZER O COMPARTILHAMENTO DE INFORMAÇÕES ENTRE AS ACTIVITY
        /*****************************************************************************************/

        // SharedViewModel TOKEN

        val sharedViewModelTokenEId = remember { SharedViewTokenEId() }

        // SharedViewModel IMAGEM COMPARTILHADA

        val sharedViewModelImageUri = remember { SharedViewModelImageUri() }

        // SharedViewModel DADOS DE CADASTRO DE USUÁRIO

        val sharedViewModelSimpleDataCadastroUser = remember { SharedViewModelSimpleDataCadastroUser() }
        val sharedViewModelDataAndGenderCadastroUser = remember { SharedViewModelDataAndGenderCadastroUser() }

        // SharedViewModel DADOS DE PERFIS

        val sharedViewModelPerfil = remember { SharedViewModelPerfil() }
        val sharedViewModelPerfilJogador = remember { SharedViewModelPerfilJogador() }
        val sharedViewModelPerfilOrganizador = remember { SharedViewModelPerfilOrganizador() }


        // SharedViewModel OUTROS DADOS DE PERFIS

        val sharedViewModelPerfilEditarOutro = remember { SharedViewModelPerfilOutro() }
        val sharedViewModelPerfilJogadorOutro = remember { SharedViewModelPerfilJogadorOutro() }
        val sharedViewModelPerfilOrganizadorOutro = remember { SharedViewModelPerfilOrganizadorOutro() }

        // SharedViewModel GET MY TEAMS USER
        // SharedViewModel GET MY TEAMS GERAL
        val sharedGetMyTeamsGeral = remember { SharedGetMyTeamsGeral() }

        val sharedViewModelGetMyTeamsUser = remember { SharedViewModelGetMyTeamsUser() }
        val sharedViewModelGetMyTeamsUserPropostas = remember { SharedViewModelGetMyTeamsUserPropostas() }
        val sharedViewModelGetMyTeamsUserPropostasDe = remember { SharedGetMyTeamsUserPropostasDe() }
        val sharedViewModelGetMyTeamsUserPropostasDeJogadores = remember { SharedGetMyTeamsUserPropostasDeJogadores() }
        val sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos = remember { SharedGetMyTeamsUserPropostasDeJogadoresAtivos() }
        val sharedViewModelGetMyTeamsUserPropostasDePropostas = remember { SharedGetMyTeamsUserPropostasDePropostas() }

        // SharedViewModel GET MY TEAMS TIME

        val sharedViewModelGetMyTeamsTime = remember { SharedViewModelGetMyTeamsTime() }
        val sharedViewModelGetMyTeamsTimeJogadores = remember { SharedViewModelGetMyTeamsTimeJogadores() }
        val sharedViewModelGetMyTeamsTimeJogadoresAtivos = remember { SharedGetMyTeamsTimeJogadoresAtivos() }
        val sharedViewModelGetMyTeamsTimePropostas = remember { SharedViewModelGetMyTeamsTimePropostas() }

        // SharedViewModel GET LISTA DE JOGADORES

        val sharedViewModelNomeJogadorListaJogadores = remember { SharedViewModelNomeJogadorListaJogadores() }
        val sharedViewModelGetListaJogadores = remember { SharedViewModelGetListaJogadores() }
        val sharedViewModelGetListaJogadoresList = remember { SharedViewModelGetListaJogadoresList() }
        val sharedViewModelGetListaJogadoresInfoPerfil = remember { SharedViewModelGetListaJogadoresInfoPerfil() }
        val sharedViewModelGetListaJogadoresTimeAtual = remember { SharedViewModelGetListaJogadoresTimeAtual() }
        val sharedViewModelGetListaJogadoresDentroDeTime = remember { SharedViewModelGetListaJogadoresDentroDeTime() }
        val sharedViewModelGetListaJogadoresDentroDeTimeList = remember { SharedViewModelGetListaJogadoresDentroDeTimeList() }
        val sharedViewModelGetListaJogadoresPropostasList = remember { SharedViewModelGetListaJogadoresPropostasList() }
        val sharedViewModelGetListaJogadoresPropostasRecebidas = remember { SharedViewModelGetListaJogadoresPropostasRecebidas() }

        // SharedViewModel GET TIME BY ID

        val  sharedGetTimeById = remember { SharedGetTimeById() }
        val  sharedGetTimeByIdTeams = remember { SharedGetTimeByIdTeams() }
        val  sharedGetTimeByIdTeamsJogadores = remember { SharedGetTimeByIdTeamsJogadores() }
        val  sharedGetTimeByIdTeamsJogadoresPerfilId = remember { SharedGetTimeByIdTeamsJogadoresPerfilId() }
        val  sharedGetTimeByIdTeamsOrganizacao = remember { SharedGetTimeByIdTeamsOrganizacao() }
        val  sharedGetTimeByIdOrganizacaoDonoId = remember { SharedGetTimeByIdOrganizacaoDonoId() }
        val  sharedGetTimeByIdTeamsPropostas = remember { SharedGetTimeByIdTeamsPropostas() }

        // SharedViewModel GET TIME FILTER

        val  sharedGetTime = remember { SharedGetTime() }
        val  sharedGetTimeTeams = remember { SharedGetTimeTeams() }
        val  sharedGetTimeTeamsJogadores = remember { SharedGetTimeTeamsJogadores() }
        val  sharedGetTimeTeamsJogadoresPerfilId = remember { SharedGetTimeTeamsJogadoresPerfilId() }
        val  sharedGetTimeTeamsOrganizacao = remember { SharedGetTimeTeamsOrganizacao() }
        val  sharedGetTimeOrganizacaoDonoId = remember { SharedGetTimeOrganizacaoDonoId() }
        val  sharedGetTimeTeamsPropostas = remember { SharedGetTimeTeamsPropostas() }


        // TELA DE INÍCIO
        val startScreen: @Composable () -> Unit = {
            StartScreen {
                currentScreen = it
            }
        }

        // TELA DE LOGIN
        val loginScreen: @Composable () -> Unit = {
            LoginScreen(sharedViewModelTokenEId) {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO DE PERFIL
        val cadastroPerfilScreen: @Composable () -> Unit = {
            CadastroPerfilScreen(sharedViewModelSimpleDataCadastroUser) {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO TIPO DE USUÁRIO
        val cadastroTipoUsuarioScreen: @Composable () -> Unit = {
            CadastroTipoUsuario(
                sharedViewModelDataAndGenderCadastroUser,
                sharedViewModelSimpleDataCadastroUser,
                sharedViewModelImageUri,
            ) {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO DE USUÁRIO PADRÃO
        val cadastroUsuarioPadraoScreen: @Composable () -> Unit = {
            CadastroUsuarioPadraoScreen(
                sharedViewModelDataAndGenderCadastroUser ,
                sharedViewModelSimpleDataCadastroUser,
                sharedViewModelImageUri,
            ) {
                currentScreen = it
            }
        }

        // TELA HOME
        val homeScreen: @Composable () -> Unit = {
            HomeScreen(sharedViewModelTokenEId, sharedViewModelPerfil) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR INFORMAÇÕES DE PERFIL DE USUÁRIO
        val carregarInformacoesPerfilUsuarioScreen: @Composable () -> Unit = {
            CarregarInformacoesPerfilUsuario(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelPerfilJogador, sharedViewModelPerfilOrganizador) {
                currentScreen = it
            }
        }

        // TELA PERFIL DE USUÁRIO JOGADOR
        val perfilUsuarioJogador: @Composable () -> Unit = {
            PerfilUsuarioJogadorScreen(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelPerfilJogador, sharedViewModelPerfilOrganizador) {
                currentScreen = it
            }
        }

        // TELA EDITAR USUÁRIO PADRÃO
        val editarInformacoesPerfilUsuarioPadrao: @Composable () -> Unit = {
            EditarPerfilUsuarioPadraoPart1(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelImageUri) {
                currentScreen = it
            }
        }

        // TELA PARA NAVEGAÇÃO ENTRE AS TELAS
        val navigationProliseum: @Composable () -> Unit = {
            NavigationProliseumScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeTeamsOrganizacao,
                sharedGetTimeOrganizacaoDonoId,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE NAVEGAÇÃO DA PARTE DE CONFIGURAÇÕES DE PERFIL
        val navigationConfiguracoesPerfil: @Composable () -> Unit = {
            NavigationConfiguracoesPerfilScreen() {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO DO USUÁRIO JOGADOR
        val cadastroUsuarioJogador: @Composable () -> Unit = {
            CadastroUsuarioJogadorScreen(sharedViewModelTokenEId) {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO DA ORGANIZACAO
        val cadastroUsuarioOrganizacao: @Composable () -> Unit = {
            CadastroOrganizadorScreen(sharedViewModelTokenEId, sharedViewModelPerfilOrganizador, sharedViewModelImageUri) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DA ORGANIZAÇÃO
        val perfilOrganizacaoScreen: @Composable () -> Unit = {
            PerfilOrganizacaoScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,
                sharedGetMyTeamsGeral,
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas
            ) {
                currentScreen = it
            }
        }

        // TELA DE EDITAR INFORMAÇÕES DA ORGANIZAÇÃO
        val editarInformacoesPerfilUsuarioOrganizador: @Composable () -> Unit = {
            EditarPerfilOrganizadorPart1(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelPerfilOrganizador, sharedViewModelImageUri) {
                currentScreen = it
            }
        }

        // TELA DE EDITAR INFORMAÇÕES DO JOGADOR
        val editarInformacoesPerfilUsuarioJogador: @Composable () -> Unit = {
            EditarPerfilJogadorPart1(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelPerfilJogador) {
                currentScreen = it
            }
        }

        // TELA PARA DELETAR ORGANIZAÇÃO
        val deletarOrganizacao: @Composable () -> Unit = {
            DeletarOrganizacaoScreen(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelPerfilOrganizador) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR INFORMAÇÕES PARA DELETAR A ORGANIZAÇÃO
        val carregarDeletarOrganizacao: @Composable () -> Unit = {
            CarregarDeletarOrganizacao(sharedViewModelTokenEId, sharedViewModelPerfil) {
                currentScreen = it
            }
        }

        // TELA DE CRIAR TIME
        val criarTime: @Composable () -> Unit = {
            CriarTimeScreen(sharedViewModelTokenEId) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR INFORMAÇÕES DA ORGANIZAÇÃO
        val carregarInformacoesPerfilOrganizacao: @Composable () -> Unit = {
            CarregarInformacoesPerfilOrganizacaoScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,
                sharedGetMyTeamsGeral,
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas
                ) {
                currentScreen = it
            }
        }

        // TELA PERFIL DE TIME
        val perfilTimeScreen: @Composable () -> Unit = {
            PerfilTimeScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTimeById,
                sharedGetTimeByIdTeams,
                sharedGetTimeByIdTeamsJogadores,
                sharedGetTimeByIdTeamsJogadoresPerfilId,
                sharedGetTimeByIdTeamsOrganizacao,
                sharedGetTimeByIdOrganizacaoDonoId,
                sharedGetTimeByIdTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA EDITAR PERFIL DO TIME
        val editarPerfilTime: @Composable () -> Unit = {
            EditarPerfilTime(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedGetMyTeamsGeral,
                sharedViewModelGetMyTeamsTime,
                sharedViewModelImageUri
            ) {
                currentScreen = it
            }
        }

        // TELA LISTAGEM DE JOGADORES
        val listaDeJogadoresScreen: @Composable () -> Unit = {
            ListaDeJogadoresScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PROPOSTAS RECEBIDAS
        val propostasScreen: @Composable () -> Unit = {
            PropostasScreen(

            ) {
                currentScreen = it
            }
        }

        // TELA DE LISTAGEM DE PUBLICAÇÕES DE JOGADORES
        val listaDePublicacoesDeJogadores: @Composable () -> Unit = {
            ListaDePublicacoesDeJogadores(

            ) {
                currentScreen = it
            }
        }

        // TELA DE PUBLICAÇÕES DE TIMES
        val listaDePublicacoesDeTimes: @Composable () -> Unit = {
            ListaDePublicacoesDeTimes(

            ) {
                currentScreen = it
            }
        }

        // TELA DE CAMPEONATO
        // /* FUTURAMENTE */
        val campeonatoScreen: @Composable () -> Unit = {
            CampeonatoScreen(

            ) {
                currentScreen = it
            }
        }

        // TELA PREMIUM PROLISEUM
        // /* FUTURAMENTE */
        val premiumScreen: @Composable () -> Unit = {
            PremiumScreen(

            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO JOGADOR
        val perfilDeOutroJogador: @Composable () -> Unit = {
            PerfilDeOutroJogador(
                sharedViewModelTokenEId,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR INFORMAÇÕES DE OUTRO JOGADOR
        val carregarInformacoesPerfilOutroJogador: @Composable () -> Unit = {
            CarregarInformacoesPerfilOutroJogador(
                sharedViewModelTokenEId,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,
            ) {
                currentScreen = it
            }
        }

        // TELA CARREGAR INFORMAÇÕES DO TIME PELO ID
        val carregarInformacoesDoTimeById: @Composable () -> Unit = {
            CarregarInformacoesDoTimeById(
                sharedViewModelTokenEId,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                sharedGetTimeById,
                sharedGetTimeByIdTeams,
                sharedGetTimeByIdTeamsJogadores,
                sharedGetTimeByIdTeamsJogadoresPerfilId,
                sharedGetTimeByIdTeamsOrganizacao,
                sharedGetTimeByIdOrganizacaoDonoId,
                sharedGetTimeByIdTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR AS INFORMAÇÕES DE PERFIL DE UM JOGADOR DO MEU TIME
        val carregarInformacoesPerfilJogadorMeuTime: @Composable () -> Unit = {
            CarregarInformacoesPerfilJogadorMeuTime(
                sharedViewModelTokenEId,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,
                sharedViewModelPerfil,
                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTimeById,
                sharedGetTimeByIdTeams,
                sharedGetTimeByIdTeamsJogadores,
                sharedGetTimeByIdTeamsJogadoresPerfilId,
                sharedGetTimeByIdTeamsOrganizacao,
                sharedGetTimeByIdOrganizacaoDonoId,
                sharedGetTimeByIdTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE UM JOGADOR DENTRO DO MEU TIME
        val perfilJogadorDoMeuTime: @Composable () -> Unit = {
            PerfilJogadorDoMeuTime(
                sharedViewModelTokenEId,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTimeById,
                sharedGetTimeByIdTeams,
                sharedGetTimeByIdTeamsJogadores,
                sharedGetTimeByIdTeamsJogadoresPerfilId,
                sharedGetTimeByIdTeamsOrganizacao,
                sharedGetTimeByIdOrganizacaoDonoId,
                sharedGetTimeByIdTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE LISTAGEM DE TIMES
        val listaDeTimes: @Composable () -> Unit = {
            ListaDeTimes(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeTeamsOrganizacao,
                sharedGetTimeOrganizacaoDonoId,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR AS INFORMAÇÕES DE LISTAGEM DE TIMES
        val carregarInformacoesListaTimes: @Composable () -> Unit = {
            CarregarInformacoesListaTimes(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeTeamsOrganizacao,
                sharedGetTimeOrganizacaoDonoId,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val perfilDeOutroTimeScreen: @Composable () -> Unit = {
            PerfilDeOutroTimeScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeTeamsOrganizacao,
                sharedGetTimeOrganizacaoDonoId,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE NAVEGAÇÃO DE CONFIGURAÇÕES/GERENCIAMENTO DO MEU TIME
        val navigationConfiguracoesMeuTimeScreen: @Composable () -> Unit = {
            NavigationConfiguracoesMeuTimeScreen(
            ) {
                currentScreen = it
            }
        }

        // NAVEGAÇÃO DO PROJETO
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = {
                slideInHorizontally() + fadeIn() with slideOutHorizontally() + fadeOut()
            },
            content = { screen ->
                when (screen) {
                    "start" -> startScreen()
                    "login" -> loginScreen()
                    "cadastro_perfil" -> cadastroPerfilScreen()
                    "cadastro_tipo_usuario" -> cadastroTipoUsuarioScreen()
                    "cadastro_usuario_padrao" -> cadastroUsuarioPadraoScreen()
                    "home" -> homeScreen()
                    "carregar_informacoes_perfil_usuario" -> carregarInformacoesPerfilUsuarioScreen()
                    "perfil_usuario_jogador" -> perfilUsuarioJogador()
                    "editar_perfil_usuario_padrao_1" -> editarInformacoesPerfilUsuarioPadrao()
                    "navigation_proliseum" -> navigationProliseum()
                    "navigation_configuracoes_perfil" -> navigationConfiguracoesPerfil()
                    "cadastro_usuario_jogador" -> cadastroUsuarioJogador()
                    "cadastro_usuario_organizador" -> cadastroUsuarioOrganizacao()
                    "perfil_organizacao" -> perfilOrganizacaoScreen()
                    "editar_perfil_organizador_1" -> editarInformacoesPerfilUsuarioOrganizador()
                    "editar_perfil_jogador_1" -> editarInformacoesPerfilUsuarioJogador()
                    "deletar_organizacao" -> deletarOrganizacao()
                    "carregar_deletar_organizacao" -> carregarDeletarOrganizacao()
                    "criar_time" -> criarTime()
                    "carregar_informacoes_perfil_organizacao" -> carregarInformacoesPerfilOrganizacao()
                    "perfil_time" -> perfilTimeScreen()
                    "editar_perfil_time" -> editarPerfilTime()
                    "lista_de_jogadores" -> listaDeJogadoresScreen()
                    "propostas" -> propostasScreen()
                    "lista_de_publicacoes_jogadores" -> listaDePublicacoesDeJogadores()
                    "lista_de_publicacoes_times" -> listaDePublicacoesDeTimes()
                    "campeonatos" -> campeonatoScreen()
                    "premium" -> premiumScreen()
                    "perfil_outro_jogador" -> perfilDeOutroJogador()
                    "carregar_informacoes_perfil_outro_jogador" -> carregarInformacoesPerfilOutroJogador()
                    "carregar_informacoes_do_time_by_id" -> carregarInformacoesDoTimeById()
                    "carregar_informacoes_perfil_jogador_meu_time" -> carregarInformacoesPerfilJogadorMeuTime()
                    "perfil_jogador_do_meu_time" -> perfilJogadorDoMeuTime()
                    "carregar_informacoes_lista_times" -> carregarInformacoesListaTimes()
                    "lista_times" -> listaDeTimes()
                    "perfil_outro_time" -> perfilDeOutroTimeScreen()
                    "navigation_configuracoes_meu_time" -> navigationConfiguracoesMeuTimeScreen()
                    else -> startScreen()
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

