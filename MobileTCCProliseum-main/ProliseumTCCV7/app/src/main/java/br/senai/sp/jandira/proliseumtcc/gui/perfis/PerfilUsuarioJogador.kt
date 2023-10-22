package br.senai.sp.jandira.proliseumtcc.gui.perfis

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PerfilUsuarioJogadorScreen(
    rememberNavController: NavController,
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil
) {

    val token = sharedViewModelTokenEId.token
    Log.d("PerfilUsuarioJogadorScreen", "Token: $token")

    val profileService = RetrofitFactoryCadastro().getPerfilUsuarioService()

    Log.d("PerfilUsuarioJogadorScreen", "Chamando a API...")

    val nomeUsuarioPerfil = remember { mutableStateOf("") }
    val emailPerfil = remember { mutableStateOf("") }
    val nickNamePerfil = remember { mutableStateOf("") }
    val biografiaPerfil = remember { mutableStateOf("") }
    val generoPerfil = remember { mutableStateOf("") }

    val nickNameJogadorPerfil = remember { mutableStateOf("") }
    val jogoJogadorPerfil = remember { mutableStateOf("") }
    val funcaoJogadorPerfil = remember { mutableStateOf("") }
    val eloJogadorPerfil = remember { mutableStateOf("") }
    val idUsuario = remember { mutableStateOf("") }

    val imageRef = remember { mutableStateOf<StorageReference?>(null) }
    val imageCapaRef = remember { mutableStateOf<StorageReference?>(null) }

    profileService.getProfile("Bearer " + token).enqueue(object : Callback<ProfileResponse> {
        override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
            if (response.isSuccessful) {
                Log.d("PerfilUsuarioJogadorScreen", "Resposta bem-sucedida: ${response.code()}")
                val profileResponseData = response.body()

                val user = profileResponseData!!.user
                idUsuario.value = user.id.toString()
                nomeUsuarioPerfil.value = user.nome_usuario
                nickNamePerfil.value = user.nickname
                emailPerfil.value = user.email
                biografiaPerfil.value = user.biografia
                generoPerfil.value = user.genero.toString()

                sharedViewModelPerfilEditar.id = user.id
                sharedViewModelPerfilEditar.nome_usuario = user.nome_usuario
                sharedViewModelPerfilEditar.nome_completo = user.nome_completo.toString()
                sharedViewModelPerfilEditar.email = user.email
                sharedViewModelPerfilEditar.data_nascimento = user.data_nascimento
                sharedViewModelPerfilEditar.genero = user.genero
                sharedViewModelPerfilEditar.nickname = user.nickname
                sharedViewModelPerfilEditar.biografia = user.biografia

                val storage = Firebase.storage

                if (idUsuario.value != null && idUsuario.value != "0") {
                    imageRef.value = storage.reference.child("${idUsuario.value}/profile")
                }

                if (idUsuario.value != null && idUsuario.value != "0") {
                    imageCapaRef.value = storage.reference.child("${idUsuario.value}/capa")
                }

                if (profileResponseData.playerProfile != null) {
                    val playerProfile = profileResponseData.playerProfile
                    nickNameJogadorPerfil.value = playerProfile.nickname
                    jogoJogadorPerfil.value = playerProfile.jogo.toString()
                    funcaoJogadorPerfil.value = playerProfile.funcao.toString()
                    eloJogadorPerfil.value = playerProfile.elo.toString()
                }


            } else {
                // Trate a resposta não bem-sucedida
                Log.d("PerfilUsuarioJogadorScreen", "Resposta não bem-sucedida: ${response.code()}")
                // Log de corpo da resposta (se necessário)
                Log.d(
                    "PerfilUsuarioJogadorScreen",
                    "Corpo da resposta: ${response.errorBody()?.string()}"
                )
            }
        }

        override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
            // Trate o erro de falha na rede.
            Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
        }

    })

    //    FIREBASE

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageCapaUri by remember { mutableStateOf<Uri?>(null) }

    if (imageRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uri = imageRef.value!!.downloadUrl.await()
                imageUri = uri

                Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uri}")

            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }

    if (imageCapaRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uriCapa = imageCapaRef.value!!.downloadUrl.await()
                imageCapaUri = uriCapa


                Log.e("URI CAPA DO USUARIO 02", "URI da imagem do usuario ${uriCapa}")
            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }

    // FIREBASE
    Log.e("URL IMAGEM DO USUARIO 03", "Id do URL da imagem do usuario ${idUsuario.value}")
    Log.e("URI IMAGEM DO USUARIO 03", "URI da imagem do usuario ${imageUri}")
    Log.e("URI CAPA DO USUARIO 03", "URI da imagem do usuario ${imageCapaUri}")


    Column {
        Text(text = "${nomeUsuarioPerfil.value}")
        Text(text = "${emailPerfil.value}")
        Text(text = "${biografiaPerfil.value}")
        Text(text = "${generoPerfil.value}")
    }

    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        photoUri = it
    }

    var painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(photoUri)
            .build()
    )

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
    ) {
        // Imagem Capa
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)

        ) {

            if (idUsuario.value != null && idUsuario.value != "0") {
                // Exiba a imagem se a URI estiver definida
                AsyncImage(
                    model = imageCapaUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            } else {
                // Caso a URI não esteja definida, você pode mostrar uma mensagem ou um indicador de carregamento

            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            Icon(
                modifier = Modifier.clickable { rememberNavController.navigate("home") },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Button(
                onClick = {
                    rememberNavController.navigate("editar_perfil_jogador_part_1")
                },
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {

                Text(
                    text = stringResource(id = R.string.button_editar),
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(3.dp))

                Icon(
                    painter = painterResource(id = R.drawable.escrever),
                    contentDescription = "Editar"
                )
            }
        }


        // Imagem Perfil
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(contentAlignment = Alignment.BottomEnd) {
                Card(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp),

                    shape = CircleShape
                ) {

                    if (idUsuario.value != null && idUsuario.value != "0") {
                        // Exiba a imagem se a URI estiver definida
                        AsyncImage(
                            model = imageUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        // Caso a URI não esteja definida, você pode mostrar uma mensagem ou um indicador de carregamento
                        Text("Carregando imagem...")
                    }
                }
            }
        }

        Column(
            modifier = Modifier.padding(top = 250.dp),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "${nomeUsuarioPerfil.value}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "${nickNamePerfil.value}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    //jogos
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .height(85.dp)
                                .width(85.dp),
                            colors = CardDefaults.cardColors(RedProliseum)
                        ) {
                            Image(
                                painter = if ("${jogoJogadorPerfil.value}" == "0") painterResource(
                                    id = R.drawable.iconcsgo
                                )
                                else if ("${jogoJogadorPerfil.value}" == "1") painterResource(id = R.drawable.iconlol)
                                else if ("${jogoJogadorPerfil.value}" == "2") painterResource(id = R.drawable.iconvalorant)
                                else painter,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                alignment = Alignment.Center,
                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                            )
                        }

                        Spacer(modifier = Modifier.width(24.dp))

                        Card(
                            modifier = Modifier
                                .height(85.dp)
                                .width(85.dp),
                            colors = CardDefaults.cardColors(RedProliseum)
                        ) {
                            Image(
                                painter = if ("${funcaoJogadorPerfil.value}" == "0") painterResource(
                                    id = R.drawable.icontoplane
                                )
                                else if ("${funcaoJogadorPerfil.value}" == "1") painterResource(id = R.drawable.iconjungle)
                                else if ("${funcaoJogadorPerfil.value}" == "2") painterResource(id = R.drawable.iconmidlane)
                                else if ("${funcaoJogadorPerfil.value}" == "3") painterResource(id = R.drawable.iconsupport)
                                else if ("${funcaoJogadorPerfil.value}" == "4") painterResource(id = R.drawable.iconadc)
                                else painter,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                alignment = Alignment.Center,
                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                            )
                        }
                    }

                    //Social
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Card(
                            modifier = Modifier
                                .height(45.dp)
                                .width(45.dp),
                            colors = CardDefaults.cardColors(RedProliseum)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.discord),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                alignment = Alignment.Center,
                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.label_nome_jogador),
                            color = Color.White,
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight(600),
                            fontFamily = customFontFamilyText,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Card(
                            modifier = Modifier
                                .height(45.dp)
                                .width(45.dp),
                            colors = CardDefaults.cardColors(RedProliseum)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.twitter),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                alignment = Alignment.Center,
                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.label_nome_jogador),
                            color = Color.White,
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight(600),
                            fontFamily = customFontFamilyText,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Card(
                            modifier = Modifier
                                .height(45.dp)
                                .width(45.dp),
                            colors = CardDefaults.cardColors(RedProliseum)
                        ) {
                            Image(
                                painter =
                                if ("${generoPerfil.value}" == "1") painterResource(id = R.drawable.generomasculino)
                                else if ("${generoPerfil.value}" == "2") painterResource(id = R.drawable.generofeminino)
                                else if ("${generoPerfil.value}" == "3") painterResource(id = R.drawable.generoindefinido)
                                else painter,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                alignment = Alignment.Center,
                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.label_genero),
                            color = Color.White,
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight(600),
                            fontFamily = customFontFamilyText,
                            fontSize = 14.sp
                        )

                    }

                    //Biografia
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    Brush
                                        .horizontalGradient(
                                            listOf(
                                                BlackTransparentProliseum,
                                                BlackTransparentProliseum
                                            )
                                        ), shape = RoundedCornerShape(16.dp)
                                )
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "${biografiaPerfil.value}",
                                fontSize = 16.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(400),
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                    // linha
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(Color.Red)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,

                        )
                    {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.label_atualmente),
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                            Image(
                                painter = painterResource(id = R.drawable.brasao),
                                contentDescription = ""
                            )
                            Text(
                                text = stringResource(id = R.string.label_fa),
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(400),
                            )
                        }

                        Column(
                            modifier = Modifier

                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.elo),
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                            Image(
                                painter = if ("${eloJogadorPerfil.value}" == "0") painterResource(id = R.drawable.icone_iron)
                                else if ("${eloJogadorPerfil.value}" == "1") painterResource(id = R.drawable.icone_bronze)
                                else if ("${eloJogadorPerfil.value}" == "2") painterResource(id = R.drawable.icone_silver)
                                else if ("${eloJogadorPerfil.value}" == "3") painterResource(id = R.drawable.icone_gold)
                                else if ("${eloJogadorPerfil.value}" == "4") painterResource(id = R.drawable.icone_platinum)
                                else if ("${eloJogadorPerfil.value}" == "5") painterResource(id = R.drawable.icone_diamond)
                                else if ("${eloJogadorPerfil.value}" == "6") painterResource(id = R.drawable.icone_master)
                                else if ("${eloJogadorPerfil.value}" == "7") painterResource(id = R.drawable.icone_grandmaster)
                                else if ("${eloJogadorPerfil.value}" == "8") painterResource(id = R.drawable.icone_challenger)
                                else painter,
                                contentDescription = "",
                                modifier = Modifier.size(100.dp)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.label_trofeu),
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                            Image(
                                painter = painterResource(id = R.drawable.trofeu_padrao),
                                contentDescription = "",
                                modifier = Modifier.size(80.dp)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(Color.Red)
                    )
                }
            }
        }
    }
}


