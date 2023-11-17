package br.senai.sp.jandira.proliseumtcc.gui.editar_perfil

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.DatePicker
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
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.Genero
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.firebase.StorageUtil
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonGeneroUI
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarInformacoesMeuPerfilPadraoScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedViewModelImageUri: SharedViewModelImageUri,
    onNavigate: (String) -> Unit
) {

    //EDITAR FOTO DE PERFIL JOGADOR
    var editarFotoPerfilJogadorUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var launcherEditarFotoPerfilJogador = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        editarFotoPerfilJogadorUri = it
    }
    var painterFotoPerfilJogador = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(editarFotoPerfilJogadorUri)
            .build()
    )


    //EDITAR FOTO DE CAPA DE PERFIL JOGADOR
    var editarFotoCapaPerfilJogadorUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var launcherEditarFotoCapaPerfilJogador = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        editarFotoCapaPerfilJogadorUri = it
    }
    var painterEditarFotoCapaPerfilJogador = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(editarFotoCapaPerfilJogadorUri)
            .build()
    )

    //FONTE
    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    val context = LocalContext.current


    var idUserSharedState by remember { mutableStateOf(sharedViewModelPerfilEditar.id) }
    var userNameUserSharedState by remember { mutableStateOf(sharedViewModelPerfilEditar.nome_usuario) }
    var fullNameSharedState by remember { mutableStateOf(sharedViewModelPerfilEditar.nome_completo) }
    var dataNascimentoSharedState by remember { mutableStateOf(sharedViewModelPerfilEditar.data_nascimento) }
    var generoUserSharedState by remember { mutableStateOf<Int?>(sharedViewModelPerfilEditar.genero) }
    var nickNameUserSharedState by remember { mutableStateOf(sharedViewModelPerfilEditar.nickname) }
    var biografiaUserSharedState by remember { mutableStateOf(sharedViewModelPerfilEditar.biografia) }

    // Declare outras variáveis de estado para outros campos da mesma maneira
    LaunchedEffect(sharedViewModelPerfilEditar) {

        // Esta parte só será executada quando o composable for inicializado
        idUserSharedState = sharedViewModelPerfilEditar.id
        userNameUserSharedState = sharedViewModelPerfilEditar.nome_usuario
        fullNameSharedState = sharedViewModelPerfilEditar.nome_completo
        dataNascimentoSharedState = sharedViewModelPerfilEditar.data_nascimento
        generoUserSharedState = sharedViewModelPerfilEditar.genero
        nickNameUserSharedState = sharedViewModelPerfilEditar.nickname
        biografiaUserSharedState = sharedViewModelPerfilEditar.biografia
        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    var selectedGeneroUser by remember { mutableStateOf<Genero?>(null) }

    Log.i("ID USUARIO", "Id do usuario EditarPerfilJogadorPart1Screen ${idUserSharedState}")

    //FOTO DE PERFIL

    var uri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )

    var painterPhotoPerfil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .build()
    )

    //FOTO CAPA DE PERFIL
    var uriCapa by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoCapaPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriCapa = it
        }
    )

    var painterPhotoCapaPerfil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uriCapa)
            .build()
    )

    val uriImage = sharedViewModelImageUri.imageUri

    val uriImageCapa = sharedViewModelImageUri.imageCapaUri

    Log.i(
        "URI IMAGEM 04",
        "Aqui esta a URI da imagem na EditarPerfilJogadorPart1Screen ${uriImage}"
    )
    Log.i(
        "URI CAPA 04",
        "Aqui esta a URI da imagem de capa de perfil na EditarPerfilJogadorPart1Screen ${uriImageCapa}"
    )
    Log.i(
        "GENERO 01",
        "Aqui esta o genero de perfil na EditarPerfilJogadorPart1Screen ${generoUserSharedState}"
    )

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
                        onNavigate("perfil_usuario_jogador")
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
                            value = userNameUserSharedState,
                            onValueChange = { newUserNameJogador ->
                                userNameUserSharedState = newUserNameJogador
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

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = fullNameSharedState,
                            onValueChange = { newFullName -> fullNameSharedState = newFullName },
                            modifier = Modifier

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
                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            DateInputSamplePerfilUser(
                                sharedViewModelPerfilEditar,
                                context = context
                            ) { date ->
                                dataNascimentoSharedState = date
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_genero),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

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
                                            "URI: ${uri?.path ?: message} "
                                        )
                                    },
                                shape = CircleShape
                            ) {
                                Image(
                                    painter =
                                    if (uri == null)
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
                                            "URI: ${uriCapa?.path ?: message} "
                                        )
                                    },
                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
                            ) {
                                Image(
                                    painter =
                                    if (uriCapa == null)
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
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        ToggleButtonGeneroUI { gender ->
                            selectedGeneroUser = gender
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_nickname),
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
                            value = nickNameUserSharedState,
                            onValueChange = { newNickNameJogador ->
                                nickNameUserSharedState = newNickNameJogador
                            },
                            modifier = Modifier
                                .width(350.dp),
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
                            value = biografiaUserSharedState,
                            onValueChange = { newFullBioJogador ->
                                biografiaUserSharedState = newFullBioJogador
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
                                AtualizarDadosPerfilUsuario(
                                    sharedViewModelTokenEId = sharedViewModelTokenEId,
                                    nomeUsuarioAtualizar = userNameUserSharedState,
                                    nomeCompletoAtualizar = fullNameSharedState,
                                    dataNascimentoAtualizar = dataNascimentoSharedState,
                                    generoAtualizar = selectedGeneroUser?.toRepresentationStrinGenero(),
                                    nickNameAtualizar = nickNameUserSharedState,
                                    biografiaAtualizar = biografiaUserSharedState
                                )

                                Log.i("JSON ACEITO", "Estrutura de JSON Correta!")
                                onNavigate("carregar_informacoes_perfil_usuario")

                                Log.i(
                                    "ID USUARIO 02",
                                    "Id do usuario EditarPerfilJogadorPart1Screen ${idUserSharedState}"
                                )
                                Log.i(
                                    "URI IMAGEM 05",
                                    "Aqui esta a URI da imagem na EditarPerfilJogadorPart1Screen ${uriImage}"
                                )
                                Log.i(
                                    "URI CAPA 05",
                                    "Aqui esta a URI da imagem de capa de perfil na EditarPerfilJogadorPart1Screen ${uriImageCapa}"
                                )

                                if (idUserSharedState != null && idUserSharedState != 0) {
                                    uri?.let {
                                        StorageUtil.uploadToStorage(
                                            uri = it,
                                            context = contextoEditarPerfilJogador1,
                                            type = "image",
                                            id = "${idUserSharedState}",
                                            "profile"
                                        )
                                    }

                                    Log.i(
                                        "URI IMAGEM 06",
                                        "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uri}"
                                    )

                                    uriCapa?.let {
                                        StorageUtil.uploadToStorage(
                                            uri = it,
                                            context = contextoEditarPerfilJogador1,
                                            type = "image",
                                            id = "${idUserSharedState}",
                                            "capa"
                                        )
                                    }

                                    Log.i(
                                        "URI CAPA 06",
                                        "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uriCapa}"
                                    )
                                }

                                Log.i(
                                    "GENERO 02",
                                    "Aqui esta o genero de perfil na EditarPerfilJogadorPart1Screen ${generoUserSharedState}"
                                )
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

fun AtualizarDadosPerfilUsuario(
    sharedViewModelTokenEId: SharedViewTokenEId,
    nomeUsuarioAtualizar: String,
    nomeCompletoAtualizar: String?,
    dataNascimentoAtualizar: String,
    generoAtualizar: String?,
    nickNameAtualizar: String,
    biografiaAtualizar: String
) {
    val token = sharedViewModelTokenEId.token

    // Criar uma instância da classe EditarPerfilUsuario com os dados a serem atualizados
    val editarPerfilData = EditarPerfilUsuario(
        nome_usuario = nomeUsuarioAtualizar,
        nome_completo = nomeCompletoAtualizar,
        data_nascimento = dataNascimentoAtualizar,
        genero = generoAtualizar,
        nickname = nickNameAtualizar,
        biografia = biografiaAtualizar
    )

    // Obtenha o serviço Retrofit para editar o perfil do usuário
    val editarPerfilService = RetrofitFactoryCadastro().putEditarPerfilUsuarioService()

    // Realize a chamada de API para editar o perfil
    editarPerfilService.getProfile("Bearer " + token, editarPerfilData)
        .enqueue(object : Callback<EditarPerfilUsuario> {
            override fun onResponse(
                call: Call<EditarPerfilUsuario>,
                response: Response<EditarPerfilUsuario>
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

            override fun onFailure(call: Call<EditarPerfilUsuario>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("EditarPerfilJogadorPart1", "Erro de rede: ${t.message}")
            }
        })
}

//@Composable
//fun ToggleButtonGeneroUIPerfilUser(
//    onGenderSelected: (Int?) -> Unit
//) {
//
//    val toggleButtons = listOf(
//        ToggleButtonGenero(
//            imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.generomasculino,
//            id = "0"
//        ),
//        ToggleButtonGenero(
//            imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.generofeminino,
//            id = "1"
//        ),
//        ToggleButtonGenero(
//            imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.generoindefinido,
//            id = "2"
//        )
//    )
//
//    val selectedButtonGenero = remember { mutableStateOf<Int?>(null) }
//
////    var generoUserSharedState by remember { mutableStateOf<Int?>(sharedViewModelPerfilEditar.genero) }
////
////    // Declare outras variáveis de estado para outros campos da mesma maneira
////
////    LaunchedEffect(sharedViewModelPerfilEditar) {
////        // Esta parte só será executada quando o composable for inicializado
////        generoUserSharedState = sharedViewModelPerfilEditar.genero
////        // Atribua outras variáveis de estado para outros campos da mesma maneira
////    }
//
//    Column {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            toggleButtons.forEach { button ->
//                val isSelected = button.id == selectedButtonGenero.value.toString()
//
//                val painter = rememberImagePainter(data = button.imageRes)
//
//                Card(
//                    modifier = Modifier.size(80.dp),
//                    shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .clickable {
//                                if (isSelected) {
//                                    selectedButtonGenero.value = null
//                                } else {
//                                    selectedButtonGenero.value = button.id.toInt()
//                                }
//                                // Chame a função de retorno para notificar a seleção
//                                onGenderSelected(selectedButtonGenero.value)
//                            }
//                            .background(
//                                if (isSelected) RedProliseum else Color.White,
//                                shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
//                            ),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Image(
//                            painter = painter,
//                            contentDescription = null,
//                            modifier = Modifier
//                                .size(80.dp)
//                                .padding(10.dp)
//                                .background(
//                                    if (isSelected) RedProliseum else Color.White,
//                                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
//                                ),
//                            alignment = Alignment.Center
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//data class ToggleButtonGenero(val imageRes: Int, val id: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInputSamplePerfilUser(
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    context: Context,
    onDateSelected: (String) -> Unit
) {
    val customFontFamily = FontFamily(Font(R.font.font_title))
    val customFontFamilyText = FontFamily(Font(R.font.font_poppins))

    val calendar = Calendar.getInstance()

    var dataNascimentoSharedState by remember { mutableStateOf(sharedViewModelPerfilEditar.data_nascimento) }
    var dataNascimentoDate: Date? by remember { mutableStateOf(null) }
    val dateOfDatePickerDialog = remember { mutableStateOf(calendar.time) }
    var isInitialDateDisplayed by remember { mutableStateOf(true) } // Controla se a data inicial já foi exibida

    if (dataNascimentoSharedState.isNotEmpty()) {
        dataNascimentoDate =
            SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(dataNascimentoSharedState)
        if (isInitialDateDisplayed) {
            dateOfDatePickerDialog.value = dataNascimentoDate
            isInitialDateDisplayed = false // Marca a data inicial como já exibida
        }
    }

    // Declare outras variáveis de estado para outros campos da mesma maneira

    LaunchedEffect(sharedViewModelPerfilEditar) {
        // Esta parte só será executada quando o composable for inicializado
        dataNascimentoSharedState = sharedViewModelPerfilEditar.data_nascimento
        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    var datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            dateOfDatePickerDialog.value = selectedDate.time

            val universalDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val formattedDateForBackend = universalDateFormat.format(selectedDate.time)
            onDateSelected(formattedDateForBackend)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    OutlinedTextField(
        value = formatDateForDisplay(dateOfDatePickerDialog.value, context),
        onValueChange = { datePickerDialog.show() },
        modifier = Modifier.width(350.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(16.dp),
        label = {
            Text(
                text = "Data de Nascimento",
                fontFamily = customFontFamilyText,
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color.White
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

// Função para formatar a data para exibição de acordo com as configurações locais do dispositivo
private fun formatDateForDisplay(date: Date?, context: Context): String {
    if (date == null) {
        return ""
    }
    val displayFormat = SimpleDateFormat("dd-MM-yyyy", context.resources.configuration.locales[0])
    return displayFormat.format(date)
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ProliseumTCCTheme {

    }
}