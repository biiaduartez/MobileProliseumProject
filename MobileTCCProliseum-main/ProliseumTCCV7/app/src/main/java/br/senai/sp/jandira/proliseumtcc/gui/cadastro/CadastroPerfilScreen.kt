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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TextField
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelSimpleDataCadastroUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroPerfilScreen(sharedViewModelSimpleDataCadastroUser: SharedViewModelSimpleDataCadastroUser, onNavigate: (String) -> Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    Log.i(
        "TESTE DE SPAWM 00",
        "Aqui esta um teste de spawn aaaaaaaaa"
    )

    var userNamePerfilState by rememberSaveable { mutableStateOf("") }
    var fullNamePerfilState by rememberSaveable { mutableStateOf("") }
    var userEmailPerfilState by rememberSaveable { mutableStateOf("") }
    var userPasswordPerfilState by rememberSaveable { mutableStateOf("") }



    var camposPreenchidosCorretamente by rememberSaveable { mutableStateOf(true) }
    var mensagemErroInputsPerfil = rememberSaveable { mutableStateOf("") }


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
                        //rememberNavController.navigate("login")
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
                        value = userNamePerfilState,
                        onValueChange = { userNamePerfilState = it },
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
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(255, 255, 255, 255),
                            focusedBorderColor = Color(255, 255, 255, 255),
                            cursorColor = Color.White
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )
                    Spacer(modifier = Modifier.height(20.dp))


                    OutlinedTextField(
                        value = fullNamePerfilState,
                        onValueChange = {  fullNamePerfilState = it },
                        modifier = Modifier

                            .width(320.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_nome),
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

                    var emailErrorText by rememberSaveable { mutableStateOf("") }

                    OutlinedTextField(
                        value = userEmailPerfilState,
                        onValueChange = {  userEmailPerfilState = it
                            emailErrorText = if (isEmailValido(it)) "" else "Email inválido"
                                        },
                        modifier = Modifier

                            .width(320.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_email),
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

                    var senhaErrorText by rememberSaveable { mutableStateOf("") }

                    OutlinedTextField(
                        value = userPasswordPerfilState,
                        onValueChange = {  userPasswordPerfilState = it
                            senhaErrorText = if (isSenhaValida(it)) "" else "Senha inválida"
                        },
                        modifier = Modifier

                            .width(320.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_senha),
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

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Button(
                            onClick = {
                                if (userNamePerfilState.isBlank()) {
                                    camposPreenchidosCorretamente = false
                                    mensagemErroInputsPerfil.value = "Preencha o Nome de usuário."
                                } else if (fullNamePerfilState.isBlank()) {
                                    camposPreenchidosCorretamente = false
                                    mensagemErroInputsPerfil.value = "Preencha o Nome completo."
                                } else if (userEmailPerfilState.isBlank() || emailErrorText.isNotEmpty()) {
                                    camposPreenchidosCorretamente = false
                                    mensagemErroInputsPerfil.value = "Preencha o Email corretamente."
                                } else if (userPasswordPerfilState.isBlank() || senhaErrorText.isNotEmpty()) {
                                    camposPreenchidosCorretamente = false
                                    mensagemErroInputsPerfil.value =
                                        "Para a sua segurança, a senha deve apresentar ao menos 1 número, 1 letra minúscula, " +
                                                "1 letra maiúscula 1 caracter especial, não deve conter espaço em brancos " +
                                                "e precisa ter um comprimento de ao menos 8 Caracteres."
                                } else {
                                    // Navega para a próxima tela ou executa outras ações
                                    keyboardController?.hide()

                                    sharedViewModelSimpleDataCadastroUser.userName = userNamePerfilState
                                    sharedViewModelSimpleDataCadastroUser.fullName = fullNamePerfilState
                                    sharedViewModelSimpleDataCadastroUser.email = userEmailPerfilState
                                    sharedViewModelSimpleDataCadastroUser.password = userPasswordPerfilState

                                    onNavigate("cadastro_tipo_usuario")
                                    Log.i("Argumentos inseridos", "$userNamePerfilState/$fullNamePerfilState/$userEmailPerfilState/$userPasswordPerfilState")
                                }
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
                                text = stringResource(id = R.string.button_proximo),
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),

                                )
                        }

                        Spacer(modifier = Modifier.padding( top = 20.dp))

                    }

                }


            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroPerfilScreenPreview() {
    ProliseumTCCTheme{

    }
}

fun isSenhaValida(senha: String): Boolean {
    val senhaPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+\$).{8,}\$".toRegex()
    return senhaPattern.matches(senha)
}

fun isEmailValido(email: String): Boolean{
    val emailPattern = "[a-zA-Z0-9!#%ˆ&*_+-]{3,}+[@](gmail|hotmail|outlook)[.][a-zA-Z]{3,}+([.][a-zA-Z]+)?".toRegex()
    return emailPattern.matches(email)
}
