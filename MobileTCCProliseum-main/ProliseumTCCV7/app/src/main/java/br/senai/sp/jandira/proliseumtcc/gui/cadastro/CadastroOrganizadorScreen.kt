package br.senai.sp.jandira.proliseumtcc.gui.cadastro

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.components.StorageUtil
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonEloLol
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonFuncaoLolUI
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonJogoUI
import br.senai.sp.jandira.proliseumtcc.model.CreateJogadorProfile
import br.senai.sp.jandira.proliseumtcc.model.CreateOrgProfile
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
fun CadastroOrganizadorScreen(sharedViewModelTokenEId: SharedViewTokenEId, sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador, sharedViewModelImageUri: SharedViewModelImageUri, onNavigate: (String) -> Unit) {

    val token = sharedViewModelTokenEId.token
    Log.d("CadastroJogadorScreen", "Token: $token")

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
    val contextoCadastroOrganizador = LocalContext.current

    val uriImage = sharedViewModelImageUri.imageUri

    val uriImageCapa = sharedViewModelImageUri.imageCapaUri

    val customFontFamilyTitle = FontFamily(Font(R.font.font_title))

    val customFontFamilyText = FontFamily(Font(R.font.font_poppins))


    var idUserProfile by remember { mutableStateOf(sharedViewModelTokenEId.idUsuario) }
    var nomeOrganizacao  by remember { mutableStateOf(sharedViewModelPerfilOrganizador.nome_organizacao) }
    var biografiaOrganizacao by remember { mutableStateOf(sharedViewModelPerfilOrganizador.biografia) }

    // Declare outras variáveis de estado para outros campos da mesma maneira
    LaunchedEffect(sharedViewModelPerfilOrganizador) {

        // Esta parte só será executada quando o composable for inicializado
        nomeOrganizacao = sharedViewModelPerfilOrganizador.nome_organizacao
        biografiaOrganizacao = sharedViewModelPerfilOrganizador.biografia
        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }


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
                    modifier = Modifier.clickable { onNavigate("navigation_configuracoes_perfil") },
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
                text = "CADASTRO",
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
                            text = "NOME DA ORGANIZAÇÃO:",
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

                    OutlinedTextField(
                        value = nomeOrganizacao,
                        onValueChange = { newNomeOrganizacao ->
                            nomeOrganizacao = newNomeOrganizacao
                        },
                        modifier = Modifier

                            .width(320.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = "Nome da organização:",
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(600),
                            )
                        },
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "BIOGRAFIA:",
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

                    OutlinedTextField(
                        value = biografiaOrganizacao,
                        onValueChange = { newBioOrganizacao ->
                            biografiaOrganizacao = newBioOrganizacao
                        },
                        modifier = Modifier
                            .height(200.dp)
                            .width(320.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = "Biografia:",
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(600),
                            )
                        },
                        textStyle = TextStyle(color = Color.White)
                    )


                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {

                            fun saveCadastroOrganizador(
                                nomeOrganizacao: String,
                                biografiaOrganizacao: String,

                            ) {
                                val newOrganizadorProfile = CreateOrgProfile(
                                    nome_organizacao = nomeOrganizacao,
                                    biografia = biografiaOrganizacao,
                                    )

                                // Obtenha o serviço de criação do perfil de jogador
                                val createOrgProfileService = RetrofitFactoryCadastro().createOrganizacaoProfileService()

                                // Execute a chamada passando o token no cabeçalho
                                createOrgProfileService.postCreateOrganizacaoProfile(
                                    "Bearer $token",
                                    newOrganizadorProfile
                                ).enqueue(object : Callback<CreateOrgProfile> {
                                        override fun onResponse(
                                            call: Call<CreateOrgProfile>,
                                            response: Response<CreateOrgProfile>
                                        ) {
                                            if (response.isSuccessful) {
                                                Log.i(
                                                    "SUCESSO",
                                                    "Os dados foram enviados para o Banco de Dados!"
                                                )

                                                // Você pode adicionar aqui a navegação para a próxima tela
                                                // Exemplo: navController.navigate("tela_sucesso")
                                            } else {
                                                Log.e(
                                                    "Erro na solicitação",
                                                    "Corpo da resposta: ${
                                                        response.errorBody()?.string()
                                                    }"
                                                )
                                            }
                                        }

                                        override fun onFailure(
                                            call: Call<CreateOrgProfile>,
                                            t: Throwable
                                        ) {
                                            Log.e("Erro na solicitação", t.message, t)
                                        }
                                    })

                                if (idUserProfile != null && idUserProfile != 0) {
                                    uri?.let {
                                        StorageUtil.uploadToStorage(
                                            uri = it,
                                            context = contextoCadastroOrganizador,
                                            type = "image",
                                            id = "${idUserProfile}",
                                            "orgprofile"
                                        )
                                    }

                                    Log.i(
                                        "URI IMAGEM 06",
                                        "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uri}"
                                    )

                                    uriCapa?.let {
                                        StorageUtil.uploadToStorage(
                                            uri = it,
                                            context = contextoCadastroOrganizador,
                                            type = "image",
                                            id = "${idUserProfile}",
                                            "orgcapa"
                                        )
                                    }

                                    Log.i(
                                        "URI CAPA 06",
                                        "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uriCapa}"
                                    )
                                }
                            }

                            saveCadastroOrganizador(
                                nomeOrganizacao = nomeOrganizacao,
                                biografiaOrganizacao = biografiaOrganizacao,
                            )

                            Log.i("JSON ACEITO", "Estrutura de JSON Correta!")


                            Log.i(
                                "SUCESSO CREATE ORGANIZADOR",
                                "Valores retornados, Nome da Organização ${nomeOrganizacao}, Biografia da Organização ${biografiaOrganizacao}"
                            )

                            onNavigate("carregar_informacoes_perfil_usuario")

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
                            text = "FINALIZAR CADASTRO",
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