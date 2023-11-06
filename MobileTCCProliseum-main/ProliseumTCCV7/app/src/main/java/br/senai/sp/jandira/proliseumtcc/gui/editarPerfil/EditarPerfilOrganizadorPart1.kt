package br.senai.sp.jandira.proliseumtcc.gui.editarPerfil


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
import androidx.compose.runtime.LaunchedEffect
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
import br.senai.sp.jandira.proliseumtcc.components.DateInputSample
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.components.StorageUtil
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonGeneroUI
import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilOrganizacao
import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilUsuario
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
fun EditarPerfilOrganizadorPart1(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,
    sharedViewModelImageUri: SharedViewModelImageUri,
    onNavigate: (String) -> Unit
) {

    //FONTE
    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    val context = LocalContext.current


    var idUserSharedState by remember { mutableStateOf(sharedViewModelPerfilEditar.id) }
    var userNameUserOrganizadorSharedState by remember { mutableStateOf(sharedViewModelPerfilOrganizador.nome_organizacao) }
    var biografiaUserOrganizadorSharedState by remember { mutableStateOf(sharedViewModelPerfilOrganizador.biografia) }

    // Declare outras variáveis de estado para outros campos da mesma maneira
    LaunchedEffect(sharedViewModelPerfilEditar, sharedViewModelPerfilOrganizador) {

        // Esta parte só será executada quando o composable for inicializado
        idUserSharedState = sharedViewModelPerfilEditar.id
        userNameUserOrganizadorSharedState = sharedViewModelPerfilOrganizador.nome_organizacao
        biografiaUserOrganizadorSharedState = sharedViewModelPerfilOrganizador.biografia
        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    Log.i("ID USUARIO", "Id do usuario EditarPerfilJogadorPart1Screen ${idUserSharedState}")


    //FOTO DE PERFIL

    var uriOrganizacao by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriOrganizacao = it
        }
    )

    var painterPhotoPerfil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uriOrganizacao)
            .build()
    )

    //FOTO CAPA DE PERFIL
    var uriCapaOrganizacao by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoCapaPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriCapaOrganizacao = it
        }
    )

    var painterPhotoCapaPerfil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uriCapaOrganizacao)
            .build()
    )

    val uriImageOrganizacao = sharedViewModelImageUri.imageUri

    val uriImageCapaOrganizacao = sharedViewModelImageUri.imageCapaUri
//    Log.i(
//        "URI CAPA 04",
//        "Aqui esta a URI da imagem de capa de perfil na EditarPerfilJogadorPart1Screen ${uriImageCapaOrganizacao}"
//    )

    val contextoEditarPerfilJogador1 = LocalContext.current

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
                        onNavigate("perfil_organizacao")
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
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        OutlinedTextField(
                            value = userNameUserOrganizadorSharedState,
                            onValueChange = { newUserNameOrganizador ->
                                userNameUserOrganizadorSharedState = newUserNameOrganizador
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
                    }

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
                                            "URI: ${uriOrganizacao?.path ?: message} "
                                        )
                                    },
                                shape = CircleShape
                            ) {
                                Image(
                                    painter =
                                    if (uriOrganizacao == null)
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
                                            "URI: ${uriCapaOrganizacao?.path ?: message} "
                                        )
                                    },
                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
                            ) {
                                Image(
                                    painter =
                                    if (uriCapaOrganizacao == null)
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

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = biografiaUserOrganizadorSharedState,
                            onValueChange = { newFullBioOrganizador ->
                                biografiaUserOrganizadorSharedState = newFullBioOrganizador
                            },
                            modifier = Modifier
                                .height(220.dp)
                                .width(350.dp),
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

                        Button(
                            onClick = {
                                AtualizarDadosPerfilUsuarioOrganizador(
                                    sharedViewModelTokenEId = sharedViewModelTokenEId,
                                    nomeOrganizadorAtualizar = userNameUserOrganizadorSharedState,
                                    biografiaAtualizar = biografiaUserOrganizadorSharedState
                                )

                                Log.i("JSON ACEITO", "Estrutura de JSON Correta!")


                                Log.i(
                                    "ID USUARIO 02",
                                    "Id do usuario EditarPerfilJogadorPart1Screen ${idUserSharedState}"
                                )
//                                Log.i(
//                                    "URI IMAGEM 05",
//                                    "Aqui esta a URI da imagem na EditarPerfilJogadorPart1Screen ${uriImageOrganizacao}"
//                                )
//                                Log.i(
//                                    "URI CAPA 05",
//                                    "Aqui esta a URI da imagem de capa de perfil na EditarPerfilJogadorPart1Screen ${uriImageCapaOrganizacao}"
//                                )

                                if (idUserSharedState != null && idUserSharedState != 0) {
                                    uriOrganizacao?.let {
                                        StorageUtil.uploadToStorage(
                                            uri = it,
                                            context = contextoEditarPerfilJogador1,
                                            type = "image",
                                            id = "${idUserSharedState}",
                                            "orgprofile"
                                        )
                                    }

                                    Log.i(
                                        "URI IMAGEM 06",
                                        "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uriOrganizacao}"
                                    )

                                    uriCapaOrganizacao?.let {
                                        StorageUtil.uploadToStorage(
                                            uri = it,
                                            context = contextoEditarPerfilJogador1,
                                            type = "image",
                                            id = "${idUserSharedState}",
                                            "orgcapa"
                                        )
                                    }

                                    Log.i(
                                        "URI CAPA 06",
                                        "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uriCapaOrganizacao}"
                                    )

                                    onNavigate("carregar_informacoes_perfil_usuario")
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

fun AtualizarDadosPerfilUsuarioOrganizador(
    sharedViewModelTokenEId: SharedViewTokenEId,
    nomeOrganizadorAtualizar: String,
    biografiaAtualizar: String
) {
    val token = sharedViewModelTokenEId.token

    // Criar uma instância da classe EditarPerfilUsuario com os dados a serem atualizados
    val editarPerfilOrgData = EditarPerfilOrganizacao(
        nome_organizacao = nomeOrganizadorAtualizar,
        biografia = biografiaAtualizar
    )

    // Obtenha o serviço Retrofit para editar o perfil do usuário
    val editarPerfilOrgService = RetrofitFactoryCadastro().putEditarPerfilOrganizacaoService()

    // Realize a chamada de API para editar o perfil
    editarPerfilOrgService.getProfile("Bearer " + token, editarPerfilOrgData)
        .enqueue(object : Callback<EditarPerfilOrganizacao> {
            override fun onResponse(
                call: Call<EditarPerfilOrganizacao>,
                response: Response<EditarPerfilOrganizacao>
            ) {
                if (response.isSuccessful) {
                    Log.d(
                        "EditarPerfilOrganizadorPart1",
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

            override fun onFailure(call: Call<EditarPerfilOrganizacao>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("EditarPerfilOrganizadorPart1", "Erro de rede: ${t.message}")
            }
        })
}

@Preview(showBackground = true)
@Composable
fun EditarPerfilOrganizadorPart1ScreenPreview() {
    ProliseumTCCTheme{

    }
}