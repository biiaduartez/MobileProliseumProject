package br.senai.sp.jandira.proliseumtcc.gui

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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.BottomNavigationScreeen
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.Login
import br.senai.sp.jandira.proliseumtcc.model.LoginResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.LoginService
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro


import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(sharedViewModelTokenEId: SharedViewTokenEId, onNavigate: (String) -> Unit) {

    Log.e("TESTE SPAWN 01", "somente um teste spawn")

    // Define a família da fonte personalizada
    val customFontFamily = FontFamily(
        Font(R.font.font_title) // Substitua pelo nome da fonte personalizada
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var camposLoginPreenchidosCorretamente by remember { mutableStateOf(true) }

    var mensagemErroInputsLogin = remember { mutableStateOf("") }

    // Use remember para armazenar o estado dos campos de texto
    var userNameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Log.e("TESTE SPAWN 02", "somente um teste spawn")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1F1E31), Color(0xFFFF3130)),
                    startY = 700f,
                    endY = 1200f
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.wallpaperlogin),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.55f),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Image(painter = painterResource(id = R.drawable.logologin), contentDescription = "")

                LaunchedEffect(camposLoginPreenchidosCorretamente) {
                    if (!camposLoginPreenchidosCorretamente) {
                        delay(5000)
                        camposLoginPreenchidosCorretamente = true
                    }
                }

                AnimatedVisibility(
                    visible = !camposLoginPreenchidosCorretamente,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    Snackbar(
                        modifier = Modifier.padding(top = 16.dp),
                        action = {}
                    ) {
                        Text(text = mensagemErroInputsLogin.value)
                    }
                }
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 135.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id =  R.string.login),
                fontFamily = customFontFamily,
                fontSize = 64.sp,
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
                    .height(420.dp)
                    .width(350.dp)
                    .padding(top = 70.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                BlackTransparentProliseum,
                                BlackTransparentProliseum
                            )
                        ),
                        shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                    )


            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {

                    var userNameState by remember{ mutableStateOf("") }

                    OutlinedTextField(
                        value = userNameState,
                        onValueChange = {newUserName -> userNameState = newUserName},
                        modifier = Modifier

                            .width(320.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
                            cursorColor = Color.White,
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    var passwordState by remember{ mutableStateOf("") }

                    OutlinedTextField(
                        value = passwordState,
                        onValueChange = {newpassword -> passwordState = newpassword},
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
                                fontWeight = FontWeight(600)
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(255, 255, 255, 255),
                            focusedBorderColor = Color(255, 255, 255, 255),
                            cursorColor = Color.White,
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp, end = 20.dp),
                        horizontalAlignment = Alignment.End
                    ) {

                        Text(
                            text = stringResource(id = R.string.esqueci_senha),
                            modifier = Modifier.clickable {
                                //rememberNavController.navigate("redefinir_senha")
                                onNavigate("redefinir_senha")
                                                          },
                            color = RedProliseum,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(600)
                        )
                        Button(
                            onClick = {
                                Log.e("TESTE SPAWN 03", "somente um teste spawn")
                                // Verifique se os campos de nome de usuário ou senha estão em branco
                                if (userNameState.isBlank() || passwordState.isBlank()) {
                                    camposLoginPreenchidosCorretamente = false
                                    mensagemErroInputsLogin.value = "Preencha o Nome de usuário e a Senha."
                                    Log.i("ERRO", "Preencha o Nome de usuário e a Senha.")
                                } else {

                                    keyboardController?.hide()

                                    // Criar a instância do Login com os dados do usuário
                                    val login = Login(userNameState, passwordState)

                                    // Obter a instância do serviço de login
                                    val loginService = RetrofitFactoryCadastro().getRetrofitFactoryCadastro().create(LoginService::class.java)

                                    // Fazer a chamada para o servidor
                                    loginService.loginUsuario(login).enqueue(object : Callback<LoginResponse> {
                                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                                            if (response.isSuccessful) {
                                                val loginResponse = response.body()
                                                if (loginResponse != null) {
                                                    val token = loginResponse.token
                                                    val idUsuario = loginResponse.user.id

                                                    Log.e("TOKEN ANTES", "${token}")
                                                    Log.e("idUsuario ANTES", "${idUsuario}")

                                                    sharedViewModelTokenEId.idUsuario = idUsuario
                                                    sharedViewModelTokenEId.token = token

                                                    Log.e("TOKEN DEPOIS", "${sharedViewModelTokenEId.token}")
                                                    Log.e("idUsuario DEPOIS", "${sharedViewModelTokenEId.idUsuario}")


                                                    // Navegue para a próxima tela
                                                    if (idUsuario > 0 && token.isNotBlank()) {
                                                        //rememberNavController.navigate("home")
                                                        onNavigate("carregar_informacoes_perfil_usuario")

                                                    } else {
                                                        Log.e("ERRO DE NAVEGAÇÃO", "Tentativa de navegação sem valores de ID e token válidos")
                                                    }
                                                }
                                            } else {
                                                camposLoginPreenchidosCorretamente = false
                                                mensagemErroInputsLogin.value = "Nome ou Senha incorretos!"
                                                Log.i("REJEITADO", "Nome ou Senha incorretos!")
                                            }
                                        }
                                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                            // Trate o erro de falha na rede.
                                            Log.i("ERRO", "Erro de rede: ${t.message}")
                                        }
                                    })
                                }
                            },
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .width(320.dp)
                                .height(48.dp),
                            shape = RoundedCornerShape(73.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum)
                        ) {
                            Text(
                                text = stringResource(id = R.string.button_logar),
                                fontFamily = customFontFamilyText,
                                fontSize = 25.sp,
                                fontWeight = FontWeight(900),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        Log.e("TESTE SPAWN 04", "somente um teste spawn")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp, end = 20.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {

            Text(
                text = stringResource(id = R.string.button_cadrasto),
                color = Color.White,
                fontFamily = customFontFamilyText,
                fontWeight = FontWeight(600)
            )
            Button(
                onClick = {
                    //rememberNavController.navigate("cadastro_perfil")
                    onNavigate("cadastro_perfil")
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(200.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(RedProliseum)

            ) {
                Text(
                    text = stringResource(id = R.string.button_cadrasto),
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(900)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ProliseumTCCTheme{

    }
}