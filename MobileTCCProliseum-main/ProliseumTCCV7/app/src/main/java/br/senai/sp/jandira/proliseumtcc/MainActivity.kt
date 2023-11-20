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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeDono
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
import br.senai.sp.jandira.proliseumtcc.gui.futuramente.CampeonatoScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesDeletarOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesDoTimeByIdScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesListaTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilJogadorMeuTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilOutroJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilUsuarioPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.HomeScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDeJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDePublicacoesDeJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDePublicacoesDeTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDeTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.LoginScreen
import br.senai.sp.jandira.proliseumtcc.gui.navegacao.NavegacaoConfiguracoesMeuTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.navegacao.NavegacaoConfiguracoesPerfilScreen
import br.senai.sp.jandira.proliseumtcc.gui.navegacao.NavigationPrincipalScreen
import br.senai.sp.jandira.proliseumtcc.gui.futuramente.PremiumScreen
import br.senai.sp.jandira.proliseumtcc.gui.PropostasScreen
import br.senai.sp.jandira.proliseumtcc.gui.StartScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroOrganizadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroUsuarioJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroDadosPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroGeneroEDataNascimentoScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.FinalizarCadastroUsuarioPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilOutroJogadorListaTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.criar.CriarTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.deletar.DeletarOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.EditarInformacoesJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.EditarInformacoesMeuPerfilPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.EditarInformacoesOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.EditarInformacoesTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.outros_perfis.PerfilDeOutroJogadorListaTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.outros_perfis.PerfilDeOutroJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.outros_perfis.PerfilDeOutroTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilJogadorDoMeuTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilUsuarioPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.postagem.PostagemJogadorScreen


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
//        val  sharedGetTimeTeamsOrganizacao = remember { SharedGetTimeTeamsOrganizacao() }
//        val  sharedGetTimeOrganizacaoDonoId = remember { SharedGetTimeDono() }
        val  sharedGetTimeDono = remember { SharedGetTimeDono() }
        val  sharedGetTimeTeamsPropostas = remember { SharedGetTimeTeamsPropostas() }

        /**********************************************************************************************************************************/

        // TELAS DO PROJETO

        /**********************************************************************************************************************************/

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

        // TELA DE CADASTRO DE DADOS PADRAO
        val cadastroDadosPadraoScreen: @Composable () -> Unit = {
            CadastroDadosPadraoScreen(sharedViewModelSimpleDataCadastroUser) {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO GENERO E DATA DE NASCIMENTO
        val cadastroGeneroEDataNascimentoScreen: @Composable () -> Unit = {
            CadastroGeneroEDataNascimentoScreen(
                sharedViewModelDataAndGenderCadastroUser,
                sharedViewModelSimpleDataCadastroUser,
                sharedViewModelImageUri,
            ) {
                currentScreen = it
            }
        }

        // TELA PARA FINALIZAR CADASTRO DO USUARIO PADRAO
        val finalizarCadastroUsuarioPadraoScreen: @Composable () -> Unit = {
            FinalizarCadastroUsuarioPadraoScreen(
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

        // TELA DE CARREGAR INFORMAÇÕES DE PERFIL DE USUÁRIO PADRÃO
        val carregarInformacoesPerfilUsuarioPadraoScreen: @Composable () -> Unit = {
            CarregarInformacoesPerfilUsuarioPadraoScreen(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelPerfilJogador, sharedViewModelPerfilOrganizador) {
                currentScreen = it
            }
        }

        // TELA PERFIL DE USUÁRIO PADRÃO
        val perfilUsuarioPadraoScreen: @Composable () -> Unit = {
            PerfilUsuarioPadraoScreen(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelPerfilJogador, sharedViewModelPerfilOrganizador) {
                currentScreen = it
            }
        }

        // TELA EDITAR INFORMAÇÕES DO MEU PERFIL DE USUÁRIO PADRÃO
        val editarInformacoesMeuPerfilPadraoScreen: @Composable () -> Unit = {
            EditarInformacoesMeuPerfilPadraoScreen(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelImageUri) {
                currentScreen = it
            }
        }

        // TELA DE NAVEGAÇÃO PRINCIPAL ENTRE AS TELAS
        val navegacaoPrincipalScreen: @Composable () -> Unit = {
            NavigationPrincipalScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE NAVEGAÇÃO DE CONFIGURAÇÕES DE PERFIL
        val navegacaoConfiguracoesPerfilScreen: @Composable () -> Unit = {
            NavegacaoConfiguracoesPerfilScreen() {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO DO USUÁRIO JOGADOR
        val cadastroUsuarioJogadorScreen: @Composable () -> Unit = {
            CadastroUsuarioJogadorScreen(sharedViewModelTokenEId) {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO DA ORGANIZACAO
        val cadastroOrganizacaoScreen: @Composable () -> Unit = {
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
        val editarInformacoesOrganizacaoScreen: @Composable () -> Unit = {
            EditarInformacoesOrganizacaoScreen(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelPerfilOrganizador, sharedViewModelImageUri) {
                currentScreen = it
            }
        }

        // TELA DE EDITAR INFORMAÇÕES DO JOGADOR
        val editarInformacoesJogadorScreen: @Composable () -> Unit = {
            EditarInformacoesJogadorScreen(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelPerfilJogador) {
                currentScreen = it
            }
        }

        // TELA DE DELETAR ORGANIZAÇÃO
        val deletarOrganizacaoScreen: @Composable () -> Unit = {
            DeletarOrganizacaoScreen(sharedViewModelTokenEId, sharedViewModelPerfil, sharedViewModelPerfilOrganizador) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR INFORMAÇÕES PARA DELETAR A ORGANIZAÇÃO
        val carregarInformacoesDeletarOrganizacaoScreen: @Composable () -> Unit = {
            CarregarInformacoesDeletarOrganizacaoScreen(sharedViewModelTokenEId, sharedViewModelPerfil) {
                currentScreen = it
            }
        }

        // TELA DE CRIAR TIME
        val criarTimeScreen: @Composable () -> Unit = {
            CriarTimeScreen(sharedViewModelTokenEId) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR INFORMAÇÕES DO PERFIL DA ORGANIZAÇÃO
        val carregarInformacoesPerfilOrganizacaoScreen: @Composable () -> Unit = {
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

        // TELA EDITAR INFORMAÇÕES DO TIME
        val editarInformacoesTimeScreen: @Composable () -> Unit = {
            EditarInformacoesTimeScreen(
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

        // TELA DE PROPOSTAS
        val propostasScreen: @Composable () -> Unit = {
            PropostasScreen(

            ) {
                currentScreen = it
            }
        }

        // TELA DE LISTAGEM DE PUBLICAÇÕES DE JOGADORES
        val listaDePublicacoesDeJogadoresScreen: @Composable () -> Unit = {
            ListaDePublicacoesDeJogadoresScreen(

            ) {
                currentScreen = it
            }
        }

        // TELA DE PUBLICAÇÕES DE TIMES
        val listaDePublicacoesDeTimesScreen: @Composable () -> Unit = {
            ListaDePublicacoesDeTimesScreen(

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

        // TELA PREMIUM
        // /* FUTURAMENTE */
        val premiumScreen: @Composable () -> Unit = {
            PremiumScreen(

            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO JOGADOR
        val perfilDeOutroJogadorScreen: @Composable () -> Unit = {
            PerfilDeOutroJogadorScreen(
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

        // TELA DE CARREGAR INFORMAÇÕES DE PERFIL DE OUTRO JOGADOR
        val carregarInformacoesPerfilOutroJogadorScreen: @Composable () -> Unit = {
            CarregarInformacoesPerfilOutroJogadorScreen(
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
        val carregarInformacoesDoTimeByIdScreen: @Composable () -> Unit = {
            CarregarInformacoesDoTimeByIdScreen(
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
        val carregarInformacoesPerfilJogadorMeuTimeScreen: @Composable () -> Unit = {
            CarregarInformacoesPerfilJogadorMeuTimeScreen(
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

        // TELA DE PERFIL DE UM JOGADOR DENTRO DE UM DO(S) MEU(S) TIME(S)
        val perfilJogadorDoMeuTimeScreen: @Composable () -> Unit = {
            PerfilJogadorDoMeuTimeScreen(
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
        val listaDeTimesScreen: @Composable () -> Unit = {
            ListaDeTimesScreen(
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
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR AS INFORMAÇÕES DE LISTAGEM DE TIMES
        val carregarInformacoesListaTimesScreen: @Composable () -> Unit = {
            CarregarInformacoesListaTimesScreen(
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
                sharedGetTimeDono,
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
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE NAVEGAÇÃO DE CONFIGURAÇÕES/GERENCIAMENTO DO MEU TIME
        val navegacaoConfiguracoesMeuTimeScreen: @Composable () -> Unit = {
            NavegacaoConfiguracoesMeuTimeScreen(
            ) {
                currentScreen = it
            }
        }


        // TELA DE NAVEGAÇÃO DE CONFIGURAÇÕES/GERENCIAMENTO DO MEU TIME
        val carregarInformacoesPerfilOutroJogadorListaTimesScreen: @Composable () -> Unit = {
            CarregarInformacoesPerfilOutroJogadorListaTimesScreen(
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

                // SharedViewModel GET TIME FILTER
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas
            ) {
                currentScreen = it
            }
        }



        // TELA DE PERFIL DE OUTRO TIME
        val perfilDeOutroJogadorListaTimesScreen: @Composable () -> Unit = {
            PerfilDeOutroJogadorListaTimesScreen(
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
                sharedViewModelGetListaJogadoresPropostasRecebidas
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val postagemJogadorScreen: @Composable () -> Unit = {
            PostagemJogadorScreen(
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
                    "cadastro_perfil" -> cadastroDadosPadraoScreen()
                    "cadastro_tipo_usuario" -> cadastroGeneroEDataNascimentoScreen()
                    "cadastro_usuario_padrao" -> finalizarCadastroUsuarioPadraoScreen()
                    "home" -> homeScreen()
                    "carregar_informacoes_perfil_usuario" -> carregarInformacoesPerfilUsuarioPadraoScreen()
                    "perfil_usuario_jogador" -> perfilUsuarioPadraoScreen()
                    "editar_perfil_usuario_padrao_1" -> editarInformacoesMeuPerfilPadraoScreen()
                    "navigation_proliseum" -> navegacaoPrincipalScreen()
                    "navigation_configuracoes_perfil" -> navegacaoConfiguracoesPerfilScreen()
                    "cadastro_usuario_jogador" -> cadastroUsuarioJogadorScreen()
                    "cadastro_usuario_organizador" -> cadastroOrganizacaoScreen()
                    "perfil_organizacao" -> perfilOrganizacaoScreen()
                    "editar_perfil_organizador_1" -> editarInformacoesOrganizacaoScreen()
                    "editar_perfil_jogador_1" -> editarInformacoesJogadorScreen()
                    "deletar_organizacao" -> deletarOrganizacaoScreen()
                    "carregar_deletar_organizacao" -> carregarInformacoesDeletarOrganizacaoScreen()
                    "criar_time" -> criarTimeScreen()
                    "carregar_informacoes_perfil_organizacao" -> carregarInformacoesPerfilOrganizacaoScreen()
                    "perfil_time" -> perfilTimeScreen()
                    "editar_perfil_time" -> editarInformacoesTimeScreen()
                    "lista_de_jogadores" -> listaDeJogadoresScreen()
                    "propostas" -> propostasScreen()
                    "lista_de_publicacoes_jogadores" -> listaDePublicacoesDeJogadoresScreen()
                    "lista_de_publicacoes_times" -> listaDePublicacoesDeTimesScreen()
                    "campeonatos" -> campeonatoScreen()
                    "premium" -> premiumScreen()
                    "perfil_outro_jogador" -> perfilDeOutroJogadorScreen()
                    "carregar_informacoes_perfil_outro_jogador" -> carregarInformacoesPerfilOutroJogadorScreen()
                    "carregar_informacoes_do_time_by_id" -> carregarInformacoesDoTimeByIdScreen()
                    "carregar_informacoes_perfil_jogador_meu_time" -> carregarInformacoesPerfilJogadorMeuTimeScreen()
                    "perfil_jogador_do_meu_time" -> perfilJogadorDoMeuTimeScreen()
                    "carregar_informacoes_lista_times" -> carregarInformacoesListaTimesScreen()
                    "lista_times" -> listaDeTimesScreen()
                    "perfil_outro_time" -> perfilDeOutroTimeScreen()
                    "navigation_configuracoes_meu_time" -> navegacaoConfiguracoesMeuTimeScreen()
                    "carregar_informacoes_perfil_outro_jogador_lista_jogadores" -> carregarInformacoesPerfilOutroJogadorListaTimesScreen()
                    "perfil_outro_jogador_lista_times" -> perfilDeOutroJogadorListaTimesScreen()
                    "postagem_jogador_screen" -> postagemJogadorScreen()
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

