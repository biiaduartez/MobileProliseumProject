package br.senai.sp.jandira.proliseumtcc.gui.criar

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
import br.senai.sp.jandira.proliseumtcc.components.Jogo
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.firebase.StorageTeamUtil
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonJogoUI
import br.senai.sp.jandira.proliseumtcc.model.CreateTime
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
fun CriarTimeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    onNavigate: (String) -> Unit
) {

    val token = sharedViewModelTokenEId.token

    //FONTE
    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var nomeTime by remember { mutableStateOf("") }
    var biografiaTime by remember { mutableStateOf("") }
    var selectedJogoUser by remember { mutableStateOf<Jogo?>(null) }

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

    var context = LocalContext.current

    val userResponseDataId = remember { mutableStateOf("") }


//    var selectedGender by remember { mutableStateOf<Int?>(null) }

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
                        onNavigate("lista_times")
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
                            value = nomeTime,
                            onValueChange = { newNomeTime->
                                nomeTime = newNomeTime
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

                        Column(
                            modifier = Modifier
                                .padding(start = 30.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = stringResource(id = R.string.label_biografia),
                                fontFamily = customFontFamilyText,
                                fontSize = 22.sp,
                                fontWeight = FontWeight(900),
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            value = biografiaTime,
                            onValueChange = { newBiografiaTime -> biografiaTime = newBiografiaTime },
                            modifier = Modifier
                                .height(200.dp)
                                .width(350.dp),
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
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        ToggleButtonJogoUI() { jogo ->
                            selectedJogoUser = jogo
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        fun CriarTime(
                            sharedViewModelTokenEId: SharedViewTokenEId,
                            nomeTime: String,
                            biografiaTime: String?,
                            jogoTime: String?,

                            ) {
                            val token = sharedViewModelTokenEId.token

                            val criarTimeData = CreateTime(
                                nome_time = nomeTime,
                                biografia = biografiaTime,
                                jogo = jogoTime,
                                id = null
                            )

                            // Obtenha o serviço Retrofit para editar o perfil do usuário
                            val createTimeService = RetrofitFactoryCadastro().createTimeService()

                            // Realize a chamada de API para editar o perfil
                            createTimeService.postCreateTime("Bearer " + token, criarTimeData)
                                .enqueue(object : Callback<CreateTime> {
                                    override fun onResponse(
                                        call: Call<CreateTime>,
                                        response: Response<CreateTime>
                                    ) {
                                        if (response.isSuccessful) {
                                            val dadosUsuario = response.body()

                                            Log.d(
                                                "EditarPerfilJogadorPart1",
                                                "Perfil de usuário atualizado com sucesso: ${response.code()}"
                                            )

                                            if (dadosUsuario != null) {
                                                userResponseDataId.value = dadosUsuario.id.toString()
                                                Log.i(
                                                    "ID USUARIO",
                                                    "Aqui esta o ID do usuario ${userResponseDataId.value}"
                                                )
                                            }

                                            if (userResponseDataId.value != null && userResponseDataId.value != "0") {
                                                uriTime?.let {
                                                    StorageTeamUtil.uploadToTeamStorage(
                                                        uri = it,
                                                        context = context,
                                                        type = "team",
                                                        id = "${userResponseDataId.value}",
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
                                                        id = "${userResponseDataId.value}",
                                                        "capa"
                                                    )
                                                }

                                                Log.i(
                                                    "URI CAPA 06",
                                                    "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uriCapaTime}"
                                                )

                                            }

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

                                    override fun onFailure(call: Call<CreateTime>, t: Throwable) {
                                        // Trate o erro de falha na rede.
                                        Log.d("EditarPerfilJogadorPart1", "Erro de rede: ${t.message}")
                                    }
                                })
                        }

                        Button(
                            onClick = {
                                CriarTime(
                                    sharedViewModelTokenEId = sharedViewModelTokenEId,
                                    nomeTime = nomeTime,
                                    biografiaTime = biografiaTime,
                                    jogoTime = selectedJogoUser?.toRepresentationStringJogo()
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



@Preview(showBackground = true)
@Composable
fun CriarTimeScreenPreview() {
    ProliseumTCCTheme {

    }
}