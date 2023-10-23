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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelDataAndGenderCadastroUser
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelSimpleDataCadastroUser
import br.senai.sp.jandira.proliseumtcc.components.StorageUtil
import br.senai.sp.jandira.proliseumtcc.model.PerfilUsuario
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.PerfilUsuarioService
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroUsuarioPadraoScreen(
    sharedViewModelDataAndGenderCadastroUser: SharedViewModelDataAndGenderCadastroUser,
    sharedViewModelSimpleDataCadastroUser: SharedViewModelSimpleDataCadastroUser,
    sharedViewModelImageUri: SharedViewModelImageUri,
    onNavigate: (String) -> Unit
) {

    val userName = sharedViewModelSimpleDataCadastroUser.userName
    val fullName = sharedViewModelSimpleDataCadastroUser.fullName
    val email = sharedViewModelSimpleDataCadastroUser.email
    val password = sharedViewModelSimpleDataCadastroUser.password

    val dateNascimentoUsuario = sharedViewModelDataAndGenderCadastroUser.selectedDate
    val generoUsuario = sharedViewModelDataAndGenderCadastroUser.selectedGender

    val customFontFamilyTitle = FontFamily(Font(R.font.font_title))

    val customFontFamilyText = FontFamily(Font(R.font.font_poppins))

    val userResponseDataId = remember { mutableStateOf("") }

    val uriImage = sharedViewModelImageUri.imageUri

    val uriImageCapa = sharedViewModelImageUri.imageCapaUri

    Log.i("URI IMAGEM 01", "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uriImage}")
    Log.i(
        "URI CAPA 01",
        "Aqui esta a URI da imagem de capa de perfil na CadastroUsuarioPadraoScreen ${uriImageCapa}"
    )

    val contextTipoUsuario = LocalContext.current

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
            .verticalScroll(rememberScrollState())
    ) {
        // Cabeçalho
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top
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
                    modifier = Modifier.clickable {
                        //rememberNavController.navigate("cadastro_tipo_usuario")
                        onNavigate("cadastro_tipo_usuario")
                                                  },
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
                painter = painterResource(id = R.drawable.logocadastro), contentDescription = ""
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.text_cadastro),
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
                                BlackTransparentProliseum, BlackTransparentProliseum
                            )
                        ), shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
                    )
                    .padding(40.dp)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_nickname),
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

                    var NickNameJogadorState by remember { mutableStateOf("") }



                    OutlinedTextField(
                        value = NickNameJogadorState,
                        onValueChange = { newNickNameJogador ->
                            NickNameJogadorState = newNickNameJogador
                        },
                        modifier = Modifier.width(370.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_nickname),
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

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_biografia),
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    var fullBioJogadorState by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = fullBioJogadorState,
                        onValueChange = { newFullBioJogador ->
                            fullBioJogadorState = newFullBioJogador
                        },
                        modifier = Modifier
                            .height(220.dp)
                            .width(370.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_biografia),
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

                    fun saveUserJogador(
                        userNamePerfilState: String,
                        fullNamePerfilState: String?,
                        userEmailPerfilState: String,
                        userPasswordPerfilState: String,
                        dataInputTipoUsuario: String,
                        generoUsuarioJogador: String,
                        nickNameUserJogador: String,
                        biografiaUserJogador: String?,
                    ) {

                        val newUserProliseum = PerfilUsuario(
                            nome_usuario = userNamePerfilState,
                            nome_completo = fullNamePerfilState,
                            email = userEmailPerfilState,
                            senha = userPasswordPerfilState,
                            data_nascimento = dataInputTipoUsuario,
                            genero = generoUsuarioJogador,
                            nickname = nickNameUserJogador,
                            biografia = biografiaUserJogador,
                            id = null
                        )

                        val retrofitFactoryCadastro =
                            RetrofitFactoryCadastro().getRetrofitFactoryCadastro()
                        val meuPerfilUsuarioService =
                            retrofitFactoryCadastro.create(PerfilUsuarioService::class.java)

                        val call = meuPerfilUsuarioService.postPerfilUsuario(newUserProliseum)

                        call.enqueue(object : Callback<PerfilUsuario> {
                            override fun onResponse(
                                call: Call<PerfilUsuario>, response: Response<PerfilUsuario>
                            ) {
                                if (response.isSuccessful) {

                                    val dadosUsuario = response.body()
                                    Log.i(
                                        "SUCESSO", "Os dados foram enviados para o Banco de Dados!"
                                    )

                                    if (dadosUsuario != null) {
                                        userResponseDataId.value = dadosUsuario.id.toString()
                                        Log.i(
                                            "ID USUARIO",
                                            "Aqui esta o ID do usuario ${userResponseDataId.value}"
                                        )
                                    }

                                    if (userResponseDataId.value != null && userResponseDataId.value != "0") {
                                        uriImage?.let {
                                            StorageUtil.uploadToStorage(
                                                uri = it,
                                                context = contextTipoUsuario,
                                                type = "image",
                                                id = "${userResponseDataId.value}",
                                                "profile"
                                            )
                                        }

                                        Log.i(
                                            "URI IMAGEM 02",
                                            "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uriImage}"
                                        )

                                        uriImageCapa?.let {
                                            StorageUtil.uploadToStorage(
                                                uri = it,
                                                context = contextTipoUsuario,
                                                type = "image",
                                                id = "${userResponseDataId.value}",
                                                "capa"
                                            )
                                        }

                                        Log.i(
                                            "URI CAPA 02",
                                            "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uriImageCapa}"
                                        )
                                    }

                                } else {
                                    Log.e(
                                        "Erro na solicitação",
                                        "Corpo da resposta: ${response.errorBody()?.string()}"
                                    )

                                }
                            }

                            override fun onFailure(call: Call<PerfilUsuario>, t: Throwable) {
                                Log.e("Erro na solicitação", t.message, t)

                            }
                        })
                    }

                    Button(
                        onClick = {
                            saveUserJogador(
                                userNamePerfilState = userName,
                                fullNamePerfilState = fullName,
                                userEmailPerfilState = email,
                                userPasswordPerfilState = password,
                                dataInputTipoUsuario = dateNascimentoUsuario,
                                generoUsuarioJogador = generoUsuario,
                                nickNameUserJogador = NickNameJogadorState,
                                biografiaUserJogador = fullBioJogadorState,
                            )

                            Log.i("JSON ACEITO", "Estrutura de JSON Correta!")
                            //rememberNavController.navigate("login")
                            onNavigate("login")
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
                            text = stringResource(id = R.string.button_finalizar),
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

@Preview(showBackground = true)
@Composable
fun CadastroJogadorScreenPreview() {
    ProliseumTCCTheme {

    }
}
