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
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModel
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelDataAndGenderCadastroUser
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelDataEGeneroETipoUsuario
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelSimpleDataCadastroUser
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.gui.LoginScreen
import br.senai.sp.jandira.proliseumtcc.gui.StartScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroUsuarioPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroPerfilScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroTipoUsuario


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

        val navController = rememberNavController()


        val sharedViewModel = viewModel<SharedViewModel>()

        val sharedViewModelDataEGeneroETipoUsuario = viewModel<SharedViewModelDataEGeneroETipoUsuario>()

        val sharedViewModelSimpleDataCadastroUser = remember { SharedViewModelSimpleDataCadastroUser()}

        val sharedViewModelDataAndGenderCadastroUser = remember { SharedViewModelDataAndGenderCadastroUser()}

        val sharedViewModelTokenEId = remember { SharedViewTokenEId() }


        val sharedViewModelImageUri = remember { SharedViewModelImageUri() }

        val sharedViewModelPerfilEditar = remember { SharedViewModelPerfil() }

        var currentScreen by remember { mutableStateOf("start") }

        // Tela de início
        val startScreen: @Composable () -> Unit = {
            StartScreen {
                currentScreen = it
            }
        }

        // Tela de login
        val loginScreen: @Composable () -> Unit = {
            LoginScreen(sharedViewModelTokenEId) {
                currentScreen = it
            }
        }

        // Tela de cadastro perfil
        val cadastroPerfilScreen: @Composable () -> Unit = {
            CadastroPerfilScreen(sharedViewModelSimpleDataCadastroUser) {
                currentScreen = it
            }
        }

        // Tela de cadastro tipo usuario
        val cadastroTipoUsuarioScreen: @Composable () -> Unit = {
            CadastroTipoUsuario(
                sharedViewModelDataAndGenderCadastroUser,
                sharedViewModelSimpleDataCadastroUser,
                sharedViewModelImageUri,
            ) {
                currentScreen = it
            }
        }

        // Tela de home
        val cadastroUsuarioPadraoScreen: @Composable () -> Unit = {
            CadastroUsuarioPadraoScreen(
                sharedViewModelDataAndGenderCadastroUser ,
                sharedViewModelSimpleDataCadastroUser,
                sharedViewModelImageUri,
            ) {
                currentScreen = it
            }
        }

        // Navegação animada
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
                    else -> startScreen()
                }
            }
        )
//        {
//            //NAVEGAÇÃO CADASTRO - SPRINT 01
//            composable(route = "start") { StartScreen(rememberNavController) }
//            composable(route = "login") { LoginScreen(rememberNavController, sharedViewModelTokenEId) }
//            composable(route = "redefinir_senha") { RecuperarSenha(rememberNavController) }
//            composable(route = "cadastro_perfil") {CadastroPerfilScreen(rememberNavController) }
//            composable(route = "cadastro_tipo_usuario/{userName}/{fullName}/{email}/{password}") {
//                var username = it.arguments?.getString("userName") ?: ""
//                var fullname = it.arguments?.getString("fullName") ?: ""
//                var email = it.arguments?.getString("email") ?: ""
//                var password = it.arguments?.getString("password") ?: ""
//
//                sharedViewModel.username = username
//                sharedViewModel.fullname = fullname
//                sharedViewModel.email = email
//                sharedViewModel.password = password
//
//
//                CadastroTipoUsuario(rememberNavController, username, fullname, email, password, sharedViewModelImageUri)
//            }
//            composable(route = "cadastro_organizador/{dateNascimentoUsuario}/{generoUsuario}/{userName}/{fullName}/{email}/{password}") {
//
//                val userNamePerfilOrganizador = sharedViewModel.username
//                val fullNamePerfilOrganizador = sharedViewModel.fullname
//                val emailPerfilOrganizador = sharedViewModel.email
//                val passwordPerfilOrganizador = sharedViewModel.password
//                val date_nascimento_organizador = sharedViewModelDataEGeneroETipoUsuario.date_nascimento_usuario
//                val genero_organizador = sharedViewModelDataEGeneroETipoUsuario.genero_usuario
//
//
//
//                CadastroOrganizadorScreen(rememberNavController, userNamePerfilOrganizador, fullNamePerfilOrganizador, emailPerfilOrganizador, passwordPerfilOrganizador, date_nascimento_organizador, genero_organizador)
//            }
//            composable(route = "cadastro_jogador/{dateNascimentoUsuario}/{generoUsuario}/{userName}/{fullName}/{email}/{password}") {
//
//                var date_nascimento_usuario = it.arguments?.getString("dateNascimentoUsuario") ?: ""
//                var genero_usuario = it.arguments?.getString("generoUsuario") ?: ""
//
//                val userNamePerfiljogador = sharedViewModel.username
//                val fullNamePerfilJogador = sharedViewModel.fullname
//                val emailPerfilJogador = sharedViewModel.email
//                val passwordPerfilJogador = sharedViewModel.password
//
//                sharedViewModelDataEGeneroETipoUsuario.date_nascimento_usuario = date_nascimento_usuario
//                sharedViewModelDataEGeneroETipoUsuario.genero_usuario = genero_usuario
//
//
//                CadastroJogadorScreen(rememberNavController, date_nascimento_usuario, genero_usuario, userNamePerfiljogador, fullNamePerfilJogador, emailPerfilJogador, passwordPerfilJogador, sharedViewModelImageUri) }
//            composable(route = "home") { HomeScreen(rememberNavController, sharedViewModelTokenEId) }
//
//            //NAVEGAÇÃO PERFIL - SPRINT 02
//            composable(route = "editar_perfil_jogador_part_1") { EditarPerfilJogadorPart1(rememberNavController, sharedViewModelTokenEId, sharedViewModelPerfilEditar, sharedViewModelImageUri) }
//            composable(route = "editar_perfil_jogador_part_2") { EditarPerfilJogadorPart2(rememberNavController, sharedViewModelTokenEId) }
//            composable(route = "editar_perfil_organizador_part_1") { EditarPerfilOrganizadorPart1(rememberNavController) }
//            composable(route = "editar_perfil_organizador_part_2") { EditarPerfilOrganizadorPart2(rememberNavController) }
//            composable(route = "criar_time") { CriarTime(rememberNavController) }
//            composable(route = "editar_perfil_time") { EditarPerfilTime(rememberNavController) }
//            composable(route = "perfil_jogador") { PerfilJogadorScreen(rememberNavController) }
//            composable(route = "perfil_organizacao") { PerfilOrganizacaoScreen(rememberNavController) }
//            composable(route = "perfil_time") { PerfilTimeScreen(rememberNavController) }
//            composable(route = "perfil_usuario_jogador") { PerfilUsuarioJogadorScreen(rememberNavController, sharedViewModelTokenEId, sharedViewModelPerfilEditar) }
//            composable(route = "navigation_proliseum") { NavigationProliseumScreen(rememberNavController) }
//            composable(route = "navigation_configuracoes_perfil") { NavigationConfiguracoesPerfilScreen(rememberNavController) }
//            composable(route = "cadastro_jogador_profile") { CadastroJogadorScreen(rememberNavController, sharedViewModelTokenEId) }
//            // ... outras composições ...
//        }
//
//
//
//
////        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

