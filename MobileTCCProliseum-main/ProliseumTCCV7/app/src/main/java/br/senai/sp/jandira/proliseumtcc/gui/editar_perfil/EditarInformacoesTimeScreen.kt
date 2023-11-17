package br.senai.sp.jandira.proliseumtcc.gui.editar_perfil

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
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
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.firebase.StorageTeamUtil
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
fun EditarInformacoesTimeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedGetMyTeamsGeral: SharedGetMyTeamsGeral,
    sharedViewModelGetMyTeamsTime: SharedViewModelGetMyTeamsTime,
    sharedViewModelImageUri: SharedViewModelImageUri,
    onNavigate: (String) -> Unit
) {
    //TOKEN
    val token = sharedViewModelTokenEId.token

    //FONTE
    val customFontFamilyTitle = FontFamily(Font(R.font.font_title))
    val customFontFamilyText = FontFamily(Font(R.font.font_poppins))
    

    // ID DE TIME COMPARTILHADO
    val selectedTimeId by remember { mutableStateOf(sharedGetMyTeamsGeral.selectedTimeId) }
    Log.e("ID DO TIME COMPARTILHADO 02","ID compartilhado EditarPerfilTime ${selectedTimeId}")

    val team = selectedTimeId?.let { sharedGetMyTeamsGeral.getTeamById(it) }
    Log.e("ID DO TIME ESCOLHIDO 02","o id do time da tela EditarPerfilTime ${team}")

    // OUTRAS INFORMAÇÕES
    val idUsuarioOrganizador = sharedViewModelPerfilEditar.id

    var nomeTime by remember { mutableStateOf(team?.nome_time ?: "Nome do Time não encontrado") }
    var biografiaTime by remember { mutableStateOf(team?.biografia ?: "Biografia do Time não encontrado") }

    val idTime by remember { mutableStateOf(sharedViewModelGetMyTeamsTime.idData) }

    //FOTO DE PERFIL

    var uriTime by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriTime = it
        }
    )

    var painterPhotoPerfil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uriTime)
            .build()
    )

    //FOTO CAPA DE PERFIL
    var uriCapaTime by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoCapaPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriCapaTime = it
        }
    )

    var painterPhotoCapaPerfil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uriCapaTime)
            .build()
    )

    // CONTEXTO E URI DE IMAGENS

    val uriImageTime = sharedViewModelImageUri.imageUri
    val uriImageCapaTime = sharedViewModelImageUri.imageCapaUri

    val context = LocalContext.current


    // DESIGN DA TELA
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
                        onNavigate("navigation_configuracoes_meu_time")
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

                    // FOTO DE PERFIL
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Box(contentAlignment = Alignment.BottomEnd) {

                            Card(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable {
                                        singlePhotoPicker.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        )
                                        var message = "nada"
                                        Log.i(
                                            "PROLISEUM",
                                            "URI: ${uriTime?.path ?: message} "
                                        )
                                    },
                                shape = CircleShape
                            ) {
                                Image(
                                    painter =
                                    if (uriTime == null)
                                        painterResource(id = R.drawable.superpersonicon)
                                    else painterPhotoPerfil,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.add_a_photo),
                                contentDescription = "",
                                tint = RedProliseum
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    //FOTO CAPA DE PERFIL
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Box(contentAlignment = Alignment.BottomEnd) {

                            Card(
                                modifier = Modifier
                                    .height(200.dp)
                                    .width(320.dp)
                                    .clickable {
                                        singlePhotoCapaPicker.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        )
                                        var message = "nada"
                                        Log.i(
                                            "PROLISEUM",
                                            "URI: ${uriCapaTime?.path ?: message} "
                                        )
                                    },
                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
                            ) {
                                Image(
                                    painter =
                                    if (uriCapaTime == null)
                                        painterResource(id = R.drawable.capa_perfil_usuario)
                                    else painterPhotoCapaPerfil,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.add_circle),
                                contentDescription = "",
                                tint = RedProliseum
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "BIOGRAFIA",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

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
                            
                            if (team != null) {
                                if (team.id != null && team.id != 0) {
                                    uriTime?.let {
                                        StorageTeamUtil.uploadToTeamStorage(
                                            uri = it,
                                            context = context,
                                            type = "team",
                                            id = "${team.id}",
                                            "profile"
                                        )
                                    }

                                    Log.i(
                                        "URI IMAGEM 06",
                                        "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uriTime}"
                                    )

                                    uriCapaTime?.let {
                                        StorageTeamUtil.uploadToTeamStorage(
                                            uri = it,
                                            context = context,
                                            type = "team",
                                            id = "${team.id}",
                                            "capa"
                                        )
                                    }

                                    Log.i(
                                        "FOTO TIME 01",
                                        "Aqui esta a URI da imagem na EditarPerfilTime -> ${uriTime}"
                                    )

                                    Log.i(
                                        "CAPA TIME 01",
                                        "Aqui esta a URI da imagem na EditarPerfilTime -> ${uriCapaTime}"
                                    )

                                    onNavigate("carregar_informacoes_perfil_usuario")
                                }
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
                            text = "SALVAR ALTERAÇÕES",
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