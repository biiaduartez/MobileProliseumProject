package br.senai.sp.jandira.proliseumtcc.gui.editar_perfil

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.EloLol
import br.senai.sp.jandira.proliseumtcc.components.FuncaoLol
import br.senai.sp.jandira.proliseumtcc.components.Jogo
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonEloLol
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonFuncaoLolUI
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonJogoUI
import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilJogador
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarInformacoesJogadorScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
    onNavigate: (String) -> Unit
) {

    //FONTE
    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var idUserSharedState by remember { mutableStateOf(sharedViewModelPerfilEditar.id) }
    var nickNameUserSharedState by remember { mutableStateOf(sharedViewModelPerfilJogador.nickname) }
    var jogoUserSharedState by remember { mutableStateOf(sharedViewModelPerfilJogador.jogo) }
    var funcaoUserSharedState by remember { mutableStateOf(sharedViewModelPerfilJogador.funcao) }
    var eloUserSharedState by remember { mutableStateOf(sharedViewModelPerfilJogador.elo) }

    // Declare outras variáveis de estado para outros campos da mesma maneira
    LaunchedEffect(sharedViewModelPerfilEditar) {

        // Esta parte só será executada quando o composable for inicializado
        idUserSharedState = sharedViewModelPerfilEditar.id
        nickNameUserSharedState = sharedViewModelPerfilJogador.nickname
        jogoUserSharedState = sharedViewModelPerfilJogador.jogo
        funcaoUserSharedState = sharedViewModelPerfilJogador.funcao
        eloUserSharedState = sharedViewModelPerfilJogador.elo
        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    Log.i("ID USUARIO", "Id do usuario EditarPerfilJogadorPart1Screen ${idUserSharedState}")


    var selectedJogo by remember { mutableStateOf<Jogo?>(null) }

    var selectedFuncao by remember { mutableStateOf<FuncaoLol?>(null) }

    var selectedElo by remember { mutableStateOf<EloLol?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        AzulEscuroProliseum,
                        AzulEscuroProliseum
                    )
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        //rememberNavController.navigate("perfil_usuario_jogador")
                        onNavigate("home")
                    },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color(255, 255, 255, 255)
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(
                painter = painterResource(id = R.drawable.logocadastro),
                contentDescription = ""
            )
            Text(
                text = stringResource(id = R.string.label_editar_jogador),
                fontFamily = customFontFamily,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = Color.White

            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 190.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                BlackTransparentProliseum,
                                BlackTransparentProliseum
                            )
                        ),
                        shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp),
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        OutlinedTextField(
                            value = nickNameUserSharedState,
                            onValueChange = { newUserNameJogador ->
                                nickNameUserSharedState = newUserNameJogador
                            },
                            modifier = Modifier

                                .width(350.dp),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            label = {
                                Text(
                                    text = stringResource(id = R.string.label_user),
                                    color = Color.White,
                                    fontFamily = customFontFamilyText,
                                    fontWeight = FontWeight(600),
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color(255, 255, 255, 255),
                                focusedBorderColor = Color(255, 255, 255, 255),
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ToggleButtonJogoUI(
                            ) { jogo ->
                                selectedJogo = jogo
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ToggleButtonFuncaoLolUI(
                            ) { funcaoLol->
                                selectedFuncao = funcaoLol
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ToggleButtonEloLol(
                            ) { elo ->
                                selectedElo = elo
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Button(
                            onClick = {
                                AtualizarDadosPerfilUsuarioJogador(
                                    sharedViewModelTokenEId = sharedViewModelTokenEId,
                                    nickNameUsuarioAtualizar = nickNameUserSharedState,
                                    jogoUsuariojogadorAtualizar = selectedJogo?.toRepresentationStringJogo(),
                                    funcaoUsuariojogadorAtualizar = selectedFuncao?.toRepresentationStrinFuncao(),
                                    eloUsuariojogadorAtualizar = selectedElo?.toRepresentationStringEloLol()
                                )

                                Log.i("JSON ACEITO", "Estrutura de JSON Correta!")
                                onNavigate("carregar_informacoes_perfil_usuario")

                            },
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .width(300.dp)
                                .height(48.dp),
                            shape = RoundedCornerShape(73.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum)

                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.logocadastro),
                                contentDescription = stringResource(id = R.string.button_proximo),
                                tint = Color(255, 255, 255, 255)
                            )
                            Text(
                                text = stringResource(id = R.string.button_salvar),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                        }
                    }
                }
            }
        }
    }
}

fun AtualizarDadosPerfilUsuarioJogador(
    sharedViewModelTokenEId: SharedViewTokenEId,
    nickNameUsuarioAtualizar: String,
    jogoUsuariojogadorAtualizar: String?,
    funcaoUsuariojogadorAtualizar: String?,
    eloUsuariojogadorAtualizar: String?
) {
    val token = sharedViewModelTokenEId.token

    // Criar uma instância da classe EditarPerfilUsuario com os dados a serem atualizados
    val editarPerfilJogadorData = EditarPerfilJogador(
        nickname = nickNameUsuarioAtualizar,
        jogo = jogoUsuariojogadorAtualizar,
        funcao = funcaoUsuariojogadorAtualizar,
        elo = eloUsuariojogadorAtualizar

    )

    // Obtenha o serviço Retrofit para editar o perfil do usuário
    val editarPerfilJogadorService = RetrofitFactoryCadastro().putEditarPerfilJogadorService()

    // Realize a chamada de API para editar o perfil
    editarPerfilJogadorService.getProfile("Bearer " + token, editarPerfilJogadorData)
        .enqueue(object : Callback<EditarPerfilJogador> {
            override fun onResponse(
                call: Call<EditarPerfilJogador>,
                response: Response<EditarPerfilJogador>
            ) {
                if (response.isSuccessful) {
                    Log.d(
                        "EditarPerfilJogadorPart1",
                        "Perfil de usuário atualizado com sucesso: ${response.code()}"
                    )
                    // Trate a resposta bem-sucedida, se necessário
                } else {
                    // Trate a resposta não bem-sucedida
                    Log.d(
                        "EditarPerfilJogadorPart1",
                        "Falha ao atualizar o perfil do usuário: ${response.code()}"
                    )
                    // Log do corpo da resposta (se necessário)
                    Log.d(
                        "EditarPerfilJogadorPart1",
                        "Corpo da resposta: ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<EditarPerfilJogador>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("EditarPerfilJogadorPart1", "Erro de rede: ${t.message}")
            }
        })
}