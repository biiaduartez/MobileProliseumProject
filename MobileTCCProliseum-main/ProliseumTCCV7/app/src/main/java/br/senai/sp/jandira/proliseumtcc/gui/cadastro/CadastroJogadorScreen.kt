package br.senai.sp.jandira.proliseumtcc.gui.cadastro

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
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonEloLol
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonFuncaoLolUI
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonJogoUI
import br.senai.sp.jandira.proliseumtcc.model.CreateJogadorProfile
import br.senai.sp.jandira.proliseumtcc.model.PerfilUsuario
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.CreateJogadorProfileService
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.PerfilUsuarioService
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroUsuarioPadraoScreen(rememberNavController: NavController, sharedViewModelTokenEId: SharedViewTokenEId) {

    val token = sharedViewModelTokenEId.token
    Log.d("CadastroJogadorScreen", "Token: $token")

    val customFontFamilyTitle = FontFamily(Font(R.font.font_title))

    val customFontFamilyText = FontFamily(Font(R.font.font_poppins))

    var selectedFuncaoLol by remember { mutableStateOf<String?>(null) }

    var selectedJogo by remember { mutableStateOf<String?>(null) }

    var selectedEloLol by remember { mutableStateOf<String?>(null) }

    var createNickNameJogador by remember { mutableStateOf("") }

    fun saveCadastroJogador(
        nickNameState: String,
        jogoState: String?,
        funcaoState: String?,
        eloState: String?,
    ) {
        val newJogadorProfile = CreateJogadorProfile(
            nickname = nickNameState,
            jogo = jogoState,
            funcao = funcaoState,
            elo = eloState,

        )

        // Obtenha o serviço de criação do perfil de jogador
        val createProfileService = RetrofitFactoryCadastro().createJogadorProfileService()

        // Execute a chamada passando o token no cabeçalho
        createProfileService.postCreateJogadorProfile("Bearer $token", newJogadorProfile)
            .enqueue(object : Callback<CreateJogadorProfile> {
                override fun onResponse(call: Call<CreateJogadorProfile>, response: Response<CreateJogadorProfile>) {
                    if (response.isSuccessful) {
                        Log.i("SUCESSO", "Os dados foram enviados para o Banco de Dados!")

                        // Você pode adicionar aqui a navegação para a próxima tela
                        // Exemplo: navController.navigate("tela_sucesso")
                    } else {
                        Log.e("Erro na solicitação", "Corpo da resposta: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<CreateJogadorProfile>, t: Throwable) {
                    Log.e("Erro na solicitação", t.message, t)
                }
            })
    }

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
        // Cabeçalho
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            // Botão de retorno
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    modifier = Modifier.clickable { rememberNavController.navigate("cadastro_tipo_usuario") },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color.White
                )
            }
        }

        // Título e imagem de logotipo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logocadastro),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "CADASTRO",
                fontFamily = customFontFamilyTitle,
                fontSize = 48.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        // Conteúdo do formulário
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 250.dp), // Ajuste o valor do topo para centralizar verticalmente
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                BlackTransparentProliseum,
                                BlackTransparentProliseum
                            )
                        ),
                        shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
                    )
                    .padding(40.dp)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {



                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "JOGO ESCOLHIDO:",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                        Text(
                            text = "*",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = RedProliseum
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = createNickNameJogador,
                        onValueChange = { newNickNameJogador -> createNickNameJogador = newNickNameJogador },
                        modifier = Modifier

                            .width(320.dp),
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
                        textStyle = TextStyle(color = Color.White)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Column {
                        ToggleButtonJogoUI{ jogo ->
                            selectedJogo = jogo.toString()
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "FUNCAO JOGO:",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }


                    Column {
                        ToggleButtonFuncaoLolUI{ funcao ->
                            selectedFuncaoLol = funcao.toString()
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "FUNCAO JOGO:",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }


                    Column {
                        ToggleButtonEloLol{ eloLol ->
                            selectedEloLol = eloLol.toString()
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {



                            saveCadastroJogador(
                                nickNameState = createNickNameJogador,
                                jogoState = selectedJogo,
                                funcaoState = selectedFuncaoLol,
                                eloState = selectedEloLol,
                            )

                            Log.i("JSON ACEITO", "Estrutura de JSON Correta!")
                            rememberNavController.navigate("perfil_usuario_jogador")

                            Log.i(
                                "Jogo, Funcao e Elo inseridos com sucesso",
                                "Valores retornados, Jogo: ${selectedJogo}, Funcao: ${selectedFuncaoLol}, Elo: ${selectedEloLol}"
                            )

                        },
                        modifier = Modifier
                            .width(320.dp)
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
                            text = "FINALIZAR CADASTRO",
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
