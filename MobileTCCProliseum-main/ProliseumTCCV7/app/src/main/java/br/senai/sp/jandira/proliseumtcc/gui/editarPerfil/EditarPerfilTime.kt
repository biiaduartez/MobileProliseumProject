package br.senai.sp.jandira.proliseumtcc.gui.editarPerfil

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.components.StorageUtil
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonFuncaoLolUI
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonJogoUI
import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilJogador
import br.senai.sp.jandira.proliseumtcc.model.infoAtualizarTime
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
fun EditarPerfilTime(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedGetMyTeamsGeral: SharedGetMyTeamsGeral,
    sharedViewModelGetMyTeamsTime: SharedViewModelGetMyTeamsTime,
    onNavigate: (String) -> Unit
) {

    val customFontFamilyTitle = FontFamily(Font(R.font.font_title))
    val customFontFamilyText = FontFamily(Font(R.font.font_poppins))

//    var editarFotoPerfilTimeUri by remember {
//        mutableStateOf<Uri?>(null)
//    }
//
//    var launcherEditarFotoPerfilTime = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ){
//        editarFotoPerfilTimeUri = it
//    }
//
//    var painterEditarFotoPerfilTime = rememberAsyncImagePainter(
//        ImageRequest.Builder(LocalContext.current)
//            .data(editarFotoPerfilTimeUri)
//            .build()
//    )

//    //EDITAR FOTO DE CAPA DE PERFIL TIME
//
//    var editarFotoCapaPerfilTimeUri by remember {
//        mutableStateOf<Uri?>(null)
//    }
//
//    var launcherEditarFotoCapaPerfilTime = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ){
//        editarFotoCapaPerfilTimeUri = it
//    }
//
//    var painterEditarFotoCapaPerfilTime = rememberAsyncImagePainter(
//        ImageRequest.Builder(LocalContext.current)
//            .data(editarFotoCapaPerfilTimeUri)
//            .build()
//    )

    val token = sharedViewModelTokenEId.token

    var nomeTime by remember { mutableStateOf(sharedViewModelGetMyTeamsTime.nomeTimeData) }
    var biografiaTime by remember { mutableStateOf(sharedViewModelGetMyTeamsTime.biografiaData) }

    val selectedTimeId = sharedGetMyTeamsGeral.selectedTimeId

    val idUsuarioOrganizador = sharedViewModelPerfilEditar.id


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
                    modifier = Modifier.clickable {
                        onNavigate("perfil_time")
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
                painter = painterResource(id = R.drawable.logocadastro),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "EDITAR TIME",
                fontFamily = customFontFamilyTitle,
                fontSize = 40.sp,
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
                            text = "NOME DO TIME:",
                            fontFamily = customFontFamilyText,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    //var nomeOrganizacaoState by remember { mutableStateOf("") }


                    OutlinedTextField(
                        value = nomeTime,
                        onValueChange = { newNomeTime -> nomeTime = newNomeTime },
                        modifier = Modifier
                            .width(370.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = "Nome do time:",
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
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "FOTO TIME",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    Column(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Box(contentAlignment = Alignment.BottomEnd){
//                            Card(
//                                modifier = Modifier
//                                    .size(100.dp)
//                                    .clickable {
//                                        launcherEditarFotoPerfilTime.launch("image/*")
//                                        var message = "nada"
//                                        Log.i(
//                                            "PROLISEUM",
//                                            "URI: ${editarFotoPerfilTimeUri?.path ?: message} "
//                                        )
//                                    },
//                                shape = CircleShape
//                            ){
//                                Image(
//                                    modifier = Modifier
//                                        .background(Color.White),
//                                    painter = if(editarFotoPerfilTimeUri == null) painterResource (id = R.drawable.superpersonicon) else painterEditarFotoPerfilTime,
//                                    contentDescription = "",
//                                    contentScale = ContentScale.Crop
//                                )
//                            }
//                            Icon(
//                                painter = painterResource(id = R.drawable.add_a_photo),
//                                contentDescription = "",
//                                tint = RedProliseum
//                            )
//
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                    ) {
//                        Text(
//                            text = "CAPA PERFIL:",
//                            fontFamily = customFontFamilyText,
//                            fontSize = 25.sp,
//                            fontWeight = FontWeight(900),
//                            color = Color.White
//                        )
//                    }
//
//
//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    Column(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Box(contentAlignment = Alignment.BottomEnd){
//                            Card(
//                                modifier = Modifier
//                                    .height(200.dp)
//                                    .width(370.dp)
//                                    .clickable {
//                                        launcherEditarFotoCapaPerfilTime.launch("image/*")
//                                        var message = "nada"
//                                        Log.i(
//                                            "PROLISEUM",
//                                            "URI: ${editarFotoCapaPerfilTimeUri?.path ?: message} "
//                                        )
//                                    },
//                                shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
//                            ){
//                                Image(
//                                    modifier = Modifier
//                                        .background(Color.White),
//                                    painter = if(editarFotoCapaPerfilTimeUri == null) painterResource (id = R.drawable.capa_perfil_usuario) else painterEditarFotoCapaPerfilTime,
//                                    contentDescription = "",
//                                    contentScale = ContentScale.Crop
//                                )
//                            }
//                            Icon(
//                                painter = painterResource(id = R.drawable.add_circle),
//                                contentDescription = "",
//                                tint = RedProliseum
//                            )
//
//                        }
//                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    //var fullBioJogadorState by remember { mutableStateOf("") }

                    biografiaTime?.let {
                        OutlinedTextField(
                            value = it,
                            onValueChange = { newBioTime -> biografiaTime = newBioTime },
                            modifier = Modifier
                                .height(220.dp)
                                .width(370.dp),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            label = {
                                Text(
                                    text = "BIOGRAFIA",
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
                    }

                    Button(
                        onClick = {
                            if(selectedTimeId != null && token != null && idUsuarioOrganizador != null){
                                AtualizarDadosPerfilTime(
                                    sharedViewModelTokenEId = sharedViewModelTokenEId,
                                    sharedGetMyTeamsGeral = sharedGetMyTeamsGeral,
                                    nomeTimeAtualizar = nomeTime,
                                    biografiaTimeAtualizar = biografiaTime
                                )
                                onNavigate("carregar_informacoes_perfil_usuario")
                            }

//                            uriOrganizacao?.let {
//                                StorageUtil.uploadToStorage(
//                                    uri = it,
//                                    context = contextoEditarPerfilJogador1,
//                                    type = "image",
//                                    id = "${idUserSharedState}",
//                                    "orgprofile"
//                                )
//                            }
//
//                            uriCapaOrganizacao?.let {
//                                StorageUtil.uploadToStorage(
//                                    uri = it,
//                                    context = contextoEditarPerfilJogador1,
//                                    type = "image",
//                                    id = "${idUserSharedState}",
//                                    "orgcapa"
//                                )
//                            }


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
                            text = "SALVAR ALTERAÇÕES DE TIME",
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

fun AtualizarDadosPerfilTime(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedGetMyTeamsGeral: SharedGetMyTeamsGeral,
    nomeTimeAtualizar: String,
    biografiaTimeAtualizar: String?,
) {
    val token = sharedViewModelTokenEId.token

    val selectedTimeId = sharedGetMyTeamsGeral.selectedTimeId

    // Criar uma instância da classe EditarPerfilUsuario com os dados a serem atualizados
    val editarPerfilTimeData = infoAtualizarTime(
        nome_time = nomeTimeAtualizar,
        biografia = biografiaTimeAtualizar
    )

    // Obtenha o serviço Retrofit para editar o perfil do usuário
    val editarPerfilTimeService = RetrofitFactoryCadastro().postUpdateTimeService()

    // Realize a chamada de API para editar o perfil
    if (selectedTimeId != null) {
        editarPerfilTimeService.postUpdateTime("Bearer " + token, selectedTimeId, editarPerfilTimeData)
            .enqueue(object : Callback<infoAtualizarTime> {
                override fun onResponse(
                    call: Call<infoAtualizarTime>,
                    response: Response<infoAtualizarTime>
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

                override fun onFailure(call: Call<infoAtualizarTime>, t: Throwable) {
                    // Trate o erro de falha na rede.
                    Log.d("EditarPerfilJogadorPart1", "Erro de rede: ${t.message}")
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun EditarPerfilTimeScreenPreview() {
    ProliseumTCCTheme{

    }
}