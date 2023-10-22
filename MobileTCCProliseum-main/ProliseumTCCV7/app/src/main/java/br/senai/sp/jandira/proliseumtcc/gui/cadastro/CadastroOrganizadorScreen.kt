package br.senai.sp.jandira.proliseumtcc.gui.cadastro

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import br.senai.sp.jandira.proliseumtcc.model.PerfilUsuario
//import br.senai.sp.jandira.proliseumtcc.model.UsuarioJogador
//import br.senai.sp.jandira.proliseumtcc.model.UsuarioOrganizador
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.PerfilUsuarioService
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroOrganizadorScreen(rememberNavController: NavController, dateNascimentoUsuario: String, generoUsuario: String, userName: String, fullName: String, email: String, password: String) {

//    //FOTO ORGANIZADOR
//    var photoOrganizadorUri by remember {
//        mutableStateOf<Uri?>(null)
//    }
//
//    var launcherOrganizador = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ){
//        photoOrganizadorUri = it
//    }
//
//    var painter = rememberAsyncImagePainter(
//        ImageRequest.Builder(LocalContext.current)
//            .data(photoOrganizadorUri)
//            .build()
//    )
//
//    val customFontFamily = FontFamily(Font(R.font.font_title))
//    val customFontFamilyText = FontFamily(Font(R.font.font_poppins))
//
//    var nomeOrganizadorState by remember { mutableStateOf("") }
//    var fullBioOrganizadorState by remember { mutableStateOf("") }
//
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                brush = Brush.horizontalGradient(
//                    listOf(
//                        AzulEscuroProliseum,
//                        AzulEscuroProliseum
//                    )
//                )
//            )
//            .verticalScroll(rememberScrollState())
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(15.dp),
//                horizontalArrangement = Arrangement.Start,
//                verticalAlignment = Alignment.Top
//            ) {
//                Icon(
//                    modifier = Modifier.clickable { navController.navigate("cadastro_tipo_usuario") },
//                    painter = painterResource(id = R.drawable.arrow_back_32),
//                    contentDescription = stringResource(id = R.string.button_sair),
//                    tint = Color(255, 255, 255, 255)
//                )
//            }
//        }
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 20.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.logocadastro),
//                contentDescription = ""
//            )
//            Text(
//                text = "CADASTRO",
//                fontFamily = customFontFamily,
//                fontSize = 48.sp,
//                textAlign = TextAlign.Center,
//                color = Color.White
//
//            )
//        }
//        Column(
//            modifier = Modifier
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 190.dp)
//                    .background(
//                        brush = Brush.horizontalGradient(
//                            listOf(
//                                BlackTransparentProliseum,
//                                BlackTransparentProliseum
//                            )
//                        ),
//                        shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
//                    )
//
//
//            ) {
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(20.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize(),
//
//                        ) {
//
//                        Spacer(modifier = Modifier.height(10.dp))
//
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                        ) {
//                            Text(
//                                text = "NOME DA ORGANIZAÇÃO:",
//                                fontFamily = customFontFamilyText,
//                                fontSize = 25.sp,
//                                fontWeight = FontWeight(900),
//                                color = Color.White
//                            )
//                            Text(
//                                text = "*",
//                                fontFamily = customFontFamilyText,
//                                fontSize = 25.sp,
//                                fontWeight = FontWeight(900),
//                                color = RedProliseum
//                            )
//                        }
//
//                        Spacer(modifier = Modifier.height(10.dp))
//
//
//
//
//
//                        OutlinedTextField(
//                            value = nomeOrganizadorState,
//                            onValueChange = { newNomeOrganizador -> nomeOrganizadorState = newNomeOrganizador },
//                            modifier = Modifier
//
//                                .width(370.dp),
//                            shape = RoundedCornerShape(16.dp),
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                            label = {
//                                Text(
//                                    text = "NOME DA ORGANIZAÇÃO",
//                                    color = Color.White,
//                                    fontFamily = customFontFamilyText,
//                                    fontWeight = FontWeight(600),
//                                )
//                            },
//                            colors = TextFieldDefaults.outlinedTextFieldColors(
//                                unfocusedBorderColor = Color(255, 255, 255, 255),
//                                focusedBorderColor = Color(255, 255, 255, 255),
//                                cursorColor = Color.White
//                            ),
//                            textStyle = TextStyle(color = Color.White)
//                        )
//
//                        Spacer(modifier = Modifier.height(40.dp))
//
//                        Text(
//                            text = "FOTO DO ORGANIZAÇÃO:",
//                            fontFamily = customFontFamilyText,
//                            fontSize = 25.sp,
//                            fontWeight = FontWeight(900),
//                            color = Color.White
//                        )
//
//
//                        Spacer(modifier = Modifier.height(20.dp))
//
//                        Column(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Box(contentAlignment = Alignment.BottomEnd){
//                                Card(
//                                    modifier = Modifier
//                                        .size(100.dp)
//                                        .clickable {
//                                            launcherOrganizador.launch("image/*")
//                                            var message = "nada"
//                                            Log.i(
//                                                "PROLISEUM",
//                                                "URI: ${photoOrganizadorUri?.path ?: message} "
//                                            )
//                                        },
//                                    shape = CircleShape
//                                ){
//                                    Image(
//                                        modifier = Modifier
//                                            .background(Color.White),
//                                        painter = if(photoOrganizadorUri == null) painterResource (id = R.drawable.superpersonicon) else painter,
//                                        contentDescription = "",
//                                        contentScale = ContentScale.Crop
//                                    )
//                                }
//                                Icon(
//                                    painter = painterResource(id = R.drawable.add_a_photo),
//                                    contentDescription = "",
//                                    tint = RedProliseum
//                                )
//
//                            }
//                        }
//
//                        Spacer(modifier = Modifier.height(20.dp))
//
//                        Text(
//                            text = "BIOGRAFIA:",
//                            fontFamily = customFontFamilyText,
//                            fontSize = 25.sp,
//                            fontWeight = FontWeight(900),
//                            color = Color.White
//                        )
//
//
//
//                        OutlinedTextField(
//                            value = fullBioOrganizadorState,
//                            onValueChange = { newFullBioOrganizador -> fullBioOrganizadorState = newFullBioOrganizador },
//                            modifier = Modifier
//                                .height(220.dp)
//                                .width(370.dp),
//                            shape = RoundedCornerShape(16.dp),
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                            label = {
//                                Text(
//                                    text = "BIOGRAFIA",
//                                    color = Color.White,
//                                    fontFamily = customFontFamilyText,
//                                    fontWeight = FontWeight(600),
//                                )
//                            },
//                            colors = TextFieldDefaults.outlinedTextFieldColors(
//                                unfocusedBorderColor = Color(255, 255, 255, 255),
//                                focusedBorderColor = Color(255, 255, 255, 255),
//                                cursorColor = Color.White
//                            ),
//                            textStyle = TextStyle(color = Color.White)
//                        )
//
//
//
//                    }
//
//                    Button(
//                        onClick = {
//                            if (tipoUsuarioPlataforma == "0"){
//
//                                Log.i("JSON REJEITADO", "Estrutura de JSON Errada!")
//
//                            } else if (tipoUsuarioPlataforma == "1"){
//                                saveUserOrganizador(
//                                    userNamePerfilState =  userName,
//                                    fullNamePerfilState = fullName,
//                                    userEmailPerfilState =  email,
//                                    userPasswordPerfilState = password,
//                                    dataInputTipoUsuario = dateNascimentoUsuario,
//                                    generoUsuarioJogador = generoUsuario,
//                                    tipoUsuarioPlataforma = "1",
//                                    nomeOrganizacao = nomeOrganizadorState,
//                                    biografiaOrganizacao = fullBioOrganizadorState,
//                                )
//                                Log.i("JSON ACEITO", "Estrutura de JSON Correta!")
//                            }
//                            Log.i("BOTAO", "O botão funciona, mas não entra no IF")
//                        },
//                        modifier = Modifier
//                            .padding(top = 20.dp)
//                            .width(320.dp)
//                            .height(48.dp),
//                        shape = RoundedCornerShape(73.dp),
//                        colors = ButtonDefaults.buttonColors(RedProliseum)
//
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.logocadastro),
//                            contentDescription = stringResource(id = R.string.button_proximo),
//                            tint = Color(255, 255, 255, 255)
//                        )
//                        Text(
//                            text = "FINALIZAR CADASTRO",
//                            fontSize = 16.sp,
//                            textAlign = TextAlign.Center,
//                            color = Color.White,
//                            fontFamily = customFontFamilyText,
//                            fontWeight = FontWeight(900),
//                        )
//                    }
//
//                }
//            }
//        }
    }
//}

@Preview(showBackground = true)
@Composable
fun CadastroOrganizadorScreenPreview() {
    ProliseumTCCTheme{

    }
}

//fun saveUserOrganizador(
//    userNamePerfilState: String,
//    fullNamePerfilState: String,
//    userEmailPerfilState: String,
//    userPasswordPerfilState: String,
//    dataInputTipoUsuario: String,
//    generoUsuarioJogador: String,
//    tipoUsuarioPlataforma: String,
//    nomeOrganizacao: String,
//    biografiaOrganizacao: String?
//){
//
//    val newUserOrganizador = UsuarioOrganizador(
//        nome = nomeOrganizacao,
//        biografia = biografiaOrganizacao
//    )
//
//    val newUserProliseumOrganizador = PerfilUsuario(
//        nome_usuario = userNamePerfilState,
//        nome_completo = fullNamePerfilState,
//        email = userEmailPerfilState,
//        senha = userPasswordPerfilState,
//        data_nascimento = dataInputTipoUsuario,
//        genero = generoUsuarioJogador,
//        tipo_de_usuario = tipoUsuarioPlataforma,
//        jogador = null,
//        organizador = newUserOrganizador
//    )
//
//    val retrofitFactoryCadastro = RetrofitFactoryCadastro().getRetrofitFactoryCadastro()
//
//    val meuPerfilUsuarioService = retrofitFactoryCadastro.create(PerfilUsuarioService::class.java)
//
//    val call = meuPerfilUsuarioService.postPerfilUsuario(newUserProliseumOrganizador)
//
//    call.enqueue(object : Callback<PerfilUsuario> {
//        override fun onResponse(call: Call<PerfilUsuario>, response: Response<PerfilUsuario>) {
//            if (response.isSuccessful) {
//                Log.i("SUCESSO", "Os dados foram cadastrados!")
//
//
//            } else {
//                Log.e("Erro na solicitação", "Corpo da resposta: ${response.errorBody()?.string()}")
//
//            }
//        }
//
//        override fun onFailure(call: Call<PerfilUsuario>, t: Throwable) {
//            Log.e("Erro na solicitação", t.message, t)
//
//        }
//    })
//}