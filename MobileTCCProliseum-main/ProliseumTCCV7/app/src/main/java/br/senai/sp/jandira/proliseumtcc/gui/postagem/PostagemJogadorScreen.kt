package br.senai.sp.jandira.proliseumtcc.gui.postagem

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.EloLol
import br.senai.sp.jandira.proliseumtcc.components.FuncaoLol
import br.senai.sp.jandira.proliseumtcc.components.Jogo
import br.senai.sp.jandira.proliseumtcc.components.TimePickerComponent
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonEloLol
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonFuncaoLolUI
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonJogoUI
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.isEmailValido
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.isSenhaValida
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun PostagemJogadorScreen(
    onNavigate: (String) -> Unit
){
    // FONTE E TECLADO

    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    val keyboardController = LocalSoftwareKeyboardController.current

    var descricaoPublicacao by rememberSaveable { mutableStateOf("") }
    var prosJogador by rememberSaveable { mutableStateOf("") }

    var selectedFuncaoLol by remember { mutableStateOf<FuncaoLol?>(null) }
    var selectedJogo by remember { mutableStateOf<Jogo?>(null) }
    var selectedEloLol by remember { mutableStateOf<EloLol?>(null) }

    val jogadorRemunerado = remember { mutableStateOf(false) }

    var camposPreenchidosCorretamente by rememberSaveable { mutableStateOf(true) }
    var mensagemErroInputsPerfil = rememberSaveable { mutableStateOf("") }

    var selectedStartTime by remember { mutableStateOf(LocalTime.now()) }
    var selectedEndTime by remember { mutableStateOf(LocalTime.now()) }




    //DESIGN DA TELA
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
                        onNavigate("login")
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
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Image(
                painter = painterResource(id = R.drawable.logocadastro),
                contentDescription = ""
            )
            Text(
                text = stringResource(id = R.string.text_cadastro),
                fontFamily = customFontFamily,
                fontSize = 48.sp,
                textAlign = TextAlign.Center,
                color = Color.White

            )

            Spacer(modifier = Modifier.height(10.dp))

            //PopUp
            LaunchedEffect(camposPreenchidosCorretamente) {
                if (!camposPreenchidosCorretamente) {
                    delay(15000)
                    camposPreenchidosCorretamente = true
                }
            }

            AnimatedVisibility(
                visible = !camposPreenchidosCorretamente,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                Snackbar(
                    modifier = Modifier.padding(top = 16.dp),
                    action = {}
                ) {
                    Text(text = mensagemErroInputsPerfil.value)
                }
            }

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
                    .fillMaxHeight()
                    .padding(top = 300.dp)
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
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {



                    OutlinedTextField(
                        value = descricaoPublicacao,
                        onValueChange = { descricaoPublicacao = it },
                        modifier = Modifier
                            .height(200.dp)
                            .width(320.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = "DESCRICAO PUBLICACAO",
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

                    Spacer(modifier = Modifier.height(20.dp))


                    Column {
                        ToggleButtonJogoUI{ jogo ->
                            selectedJogo = jogo
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
                            selectedFuncaoLol = funcao
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
                            selectedEloLol = eloLol
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    TimePickerComponent(
                        onStartTimeSelected = { selectedStartTime = it },
                        onEndTimeSelected = { selectedEndTime = it }
                    )

                    Log.e("HORARIO INICIO", "Horario de inicio${selectedStartTime}")
                    Log.e("HORARIO SAIDA", "Horario de saida${selectedEndTime}")



                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = jogadorRemunerado.value,
                            onCheckedChange = { jogadorRemunerado.value = it },
                            modifier = Modifier
                                .scale(scale = 1.6f)
                                .size(40.dp)
                                .padding(16.dp),
                            colors = CheckboxDefaults.colors(checkedColor = Color(0, 255, 165, 255)),
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Remuneração?",
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(600),
                            fontSize = 22.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))




                    OutlinedTextField(
                        value = prosJogador,
                        onValueChange = {  prosJogador = it },
                        modifier = Modifier
                            .height(200.dp)
                            .width(320.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = "PROS:",
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

                    Spacer(modifier = Modifier.height(20.dp))

//                    var emailErrorText by rememberSaveable { mutableStateOf("") }
//
//                    OutlinedTextField(
//                        value = userEmailPerfilState,
//                        onValueChange = {  userEmailPerfilState = it
//                            emailErrorText = if (isEmailValido(it)) "" else "Email inválido"
//                        },
//                        modifier = Modifier
//
//                            .width(320.dp),
//                        shape = RoundedCornerShape(16.dp),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                        label = {
//                            Text(
//                                text = stringResource(id = R.string.label_email),
//                                color = Color.White,
//                                fontFamily = customFontFamilyText,
//                                fontWeight = FontWeight(600),
//                            )
//                        },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            unfocusedBorderColor = Color(255, 255, 255, 255),
//                            focusedBorderColor = Color(255, 255, 255, 255),
//                            cursorColor = Color.White
//                        ),
//                        textStyle = TextStyle(color = Color.White)
//                    )
//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    var senhaErrorText by rememberSaveable { mutableStateOf("") }
//
//                    OutlinedTextField(
//                        value = userPasswordPerfilState,
//                        onValueChange = {  userPasswordPerfilState = it
//                            senhaErrorText = if (isSenhaValida(it)) "" else "Senha inválida"
//                        },
//                        modifier = Modifier
//
//                            .width(320.dp),
//                        shape = RoundedCornerShape(16.dp),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                        visualTransformation = PasswordVisualTransformation(),
//                        label = {
//                            Text(
//                                text = stringResource(id = R.string.label_senha),
//                                color = Color.White,
//                                fontFamily = customFontFamilyText,
//                                fontWeight = FontWeight(600),
//                            )
//                        },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            unfocusedBorderColor = Color(255, 255, 255, 255),
//                            focusedBorderColor = Color(255, 255, 255, 255),
//                            cursorColor = Color.White
//                        ),
//                        textStyle = TextStyle(color = Color.White)
//                    )

//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 20.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//
//
//                        Button(
//                            onClick = {
//                                if (descricaoPublicacao.isBlank()) {
//                                    camposPreenchidosCorretamente = false
//                                    mensagemErroInputsPerfil.value = "Preencha o Nome de usuário."
//                                } else if (prosJogador.isBlank()) {
//                                    camposPreenchidosCorretamente = false
//                                    mensagemErroInputsPerfil.value = "Preencha o Nome completo."
//                                } else if (userEmailPerfilState.isBlank() || emailErrorText.isNotEmpty()) {
//                                    camposPreenchidosCorretamente = false
//                                    mensagemErroInputsPerfil.value = "Preencha o Email corretamente."
//                                } else if (userPasswordPerfilState.isBlank() || senhaErrorText.isNotEmpty()) {
//                                    camposPreenchidosCorretamente = false
//                                    mensagemErroInputsPerfil.value =
//                                        "Para a sua segurança, a senha deve apresentar ao menos 1 número, 1 letra minúscula, " +
//                                                "1 letra maiúscula 1 caracter especial, não deve conter espaço em brancos " +
//                                                "e precisa ter um comprimento de ao menos 8 Caracteres."
//                                } else {
//                                    // Navega para a próxima tela ou executa outras ações
//                                    keyboardController?.hide()
//
//                                    sharedViewModelSimpleDataCadastroUser.userName = descricaoPublicacao
//                                    sharedViewModelSimpleDataCadastroUser.fullName = prosJogador
//                                    sharedViewModelSimpleDataCadastroUser.email = userEmailPerfilState
//                                    sharedViewModelSimpleDataCadastroUser.password = userPasswordPerfilState
//
//                                    onNavigate("cadastro_tipo_usuario")
//                                    Log.i("Argumentos inseridos", "$descricaoPublicacao/$prosJogador/$userEmailPerfilState/$userPasswordPerfilState")
//                                }
//                            },
//                            modifier = Modifier
//                                .padding(top = 20.dp)
//                                .width(300.dp)
//                                .height(48.dp),
//                            shape = RoundedCornerShape(73.dp),
//                            colors = ButtonDefaults.buttonColors(RedProliseum)
//
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.logocadastro),
//                                contentDescription = stringResource(id = R.string.button_proximo),
//                                tint = Color(255, 255, 255, 255)
//                            )
//                            Text(
//                                text = stringResource(id = R.string.button_proximo),
//                                fontSize = 22.sp,
//                                textAlign = TextAlign.Center,
//                                color = Color.White,
//                                fontFamily = customFontFamilyText,
//                                fontWeight = FontWeight(900),
//
//                                )
//                        }
//
//                        Spacer(modifier = Modifier.padding( top = 20.dp))
//
//                    }

                }


            }

        }
    }
}
