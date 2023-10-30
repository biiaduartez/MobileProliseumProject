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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.AsyncImage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

@Composable
fun PerfilOrganizacaoScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,

    // SharedViewModelGetMyTeams de USUARIO
    sharedViewModelGetMyTeamsUser: SharedViewModelGetMyTeamsUser,
    sharedViewModelGetMyTeamsUserPropostas: SharedViewModelGetMyTeamsUserPropostas,
    sharedViewModelGetMyTeamsUserPropostasDe: SharedGetMyTeamsUserPropostasDe,
    sharedViewModelGetMyTeamsUserPropostasDeJogadores: SharedGetMyTeamsUserPropostasDeJogadores,
    sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos: SharedGetMyTeamsUserPropostasDeJogadoresAtivos,
    sharedViewModelGetMyTeamsUserPropostasDePropostas: SharedGetMyTeamsUserPropostasDePropostas,

    // SharedViewModelGetMyTeams de TIME
    sharedViewModelGetMyTeamsTime: SharedViewModelGetMyTeamsTime,
    sharedViewModelGetMyTeamsTimeJogadores: SharedViewModelGetMyTeamsTimeJogadores,
    sharedViewModelGetMyTeamsTimeJogadoresAtivos: SharedGetMyTeamsTimeJogadoresAtivos,
    sharedViewModelGetMyTeamsTimePropostas: SharedViewModelGetMyTeamsTimePropostas,
    onNavigate: (String) -> Unit
) {



    val token = sharedViewModelTokenEId.token
    Log.d("PerfilUsuarioJogadorScreen", "Token: $token")

    val imageRef = remember { mutableStateOf<StorageReference?>(null) }
    val imageOrgRef = remember { mutableStateOf<StorageReference?>(null) }
    val imageOrgCapaRef = remember { mutableStateOf<StorageReference?>(null) }

    val idUser = sharedViewModelPerfilEditar.id
    val nomeUser = sharedViewModelPerfilEditar.nome_usuario
    val fullNomeUser = sharedViewModelPerfilEditar.nome_completo
    val dataNascimentoUser = sharedViewModelPerfilEditar.data_nascimento
    val emailUser = sharedViewModelPerfilEditar.email
    val nickNameUser = sharedViewModelPerfilEditar.nickname
    val biografiaUser = sharedViewModelPerfilEditar.biografia
    val generoPerfilUser = sharedViewModelPerfilEditar.genero

    val idUsuarioJogadorPerfilUser = sharedViewModelPerfilJogador.id
    val nickNamejogadorPerfilUser = sharedViewModelPerfilJogador.nickname
    val jogoJogadorPerfilUser = sharedViewModelPerfilJogador.jogo
    val funcaoJogadorPerfilUser = sharedViewModelPerfilJogador.funcao
    val eloJogadorPerfilUser = sharedViewModelPerfilJogador.elo

    val nomeOrganizacao = sharedViewModelPerfilOrganizador.nome_organizacao
    val biografiaOrganizacao = sharedViewModelPerfilOrganizador.biografia

    val userIdGetMyTeams = sharedViewModelGetMyTeamsUser.idData

    Log.d("PerfilUsuarioJogadorScreen", "Id do usuario organizador: $userIdGetMyTeams")

    if(idUser != null && idUser != 0){


        val storage = Firebase.storage

        if (idUser != null && idUser != 0) {
            imageRef.value = storage.reference.child("${idUser}/profile")
        }

        if (idUser != null && idUser != 0) {
            imageOrgRef.value = storage.reference.child("${idUser}/orgprofile")
        }

        if (idUser != null && idUser != 0) {
            imageOrgCapaRef.value = storage.reference.child("${idUser}/orgcapa")
        }

    } else{
        Log.e("TOKEN NULO", "Token do usuario esta nulo")
        Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
    }

    //    FIREBASE

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageOrgUri by remember { mutableStateOf<Uri?>(null) }
    var imageOrgCapaUri by remember { mutableStateOf<Uri?>(null) }

    if (imageRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uriOrg = imageRef.value!!.downloadUrl.await()
                imageUri = uriOrg

                Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uriOrg}")

            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }

    if (imageOrgRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uri = imageOrgRef.value!!.downloadUrl.await()
                imageOrgUri = uri

                Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uri}")

            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }

    if (imageOrgCapaRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uriCapa = imageOrgCapaRef.value!!.downloadUrl.await()
                imageOrgCapaUri = uriCapa


                Log.e("URI CAPA DO USUARIO 02", "URI da imagem do usuario ${uriCapa}")
            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }

    // FIREBASE
    Log.e("URL IMAGEM DO USUARIO 03", "Id do URL da imagem do usuario ${idUser}")
    Log.e("URI IMAGEM DO USUARIO 03", "URI da imagem do usuario ${imageUri}")
    Log.e("URI CAPA DO USUARIO 03", "URI da imagem do usuario ${imageOrgCapaRef}")


    Column {
        Text(text = "${nomeUser}")
        Text(text = "${emailUser}")
        Text(text = "${biografiaUser}")
        Text(text = "${generoPerfilUser}")
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

            if (idUser != null && idUser != 0) {
                // Exiba a imagem se a URI estiver definida
                AsyncImage(
                    model = imageOrgCapaUri,
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
                modifier = Modifier.clickable {
                    //rememberNavController.navigate("home")
                    onNavigate("perfil_usuario_jogador")
                },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Button(
                onClick = {
                    //rememberNavController.navigate("editar_perfil_jogador_part_1")
                    onNavigate("editar_perfil_organizador_1")
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
                .padding(top = 90.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Box(contentAlignment = Alignment.TopEnd) {
                    Card(
                        modifier = Modifier
                            .size(150.dp),
                        shape = CircleShape
                    ) {
                        if (idUser != null && idUser != 0) {
                            // Exiba a imagem se a URI estiver definida
                            AsyncImage(
                                model = imageOrgUri,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            // Caso a URI não esteja definida, você pode mostrar uma mensagem ou um indicador de carregamento
                            Text("Carregando imagem...")
                        }
                    }
                    Card(
                        modifier = Modifier
                            .size(40.dp),
                        shape = CircleShape
                    ) {
                        if (idUser != null && idUser != 0) {
                            // Exiba a imagem se a URI estiver definida
                            AsyncImage(
                                model = imageUri,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            // Caso a URI não esteja definida, você pode mostrar uma mensagem ou um indicador de carregamento
                            Text("Carregando imagem...")
                        }
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
                        text = "${nomeOrganizacao}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(12.dp))


//                    //jogos
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Card(
//                            modifier = Modifier
//                                .height(85.dp)
//                                .width(85.dp),
//                            colors = CardDefaults.cardColors(RedProliseum)
//                        ) {
//                            Image(
//                                painter =
//                                if ("${jogoJogadorPerfilUser}" == "0") painterResource(
//                                    id = R.drawable.iconcsgo
//                                )
//                                else if ("${jogoJogadorPerfilUser}" == "1") painterResource(id = R.drawable.iconlol)
//                                else if ("${jogoJogadorPerfilUser}" == "2") painterResource(id = R.drawable.iconvalorant)
//                                else painter,
//                                contentDescription = "",
//                                modifier = Modifier.fillMaxSize(),
//                                alignment = Alignment.Center,
//                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
//                            )
//                        }
//
//                        Spacer(modifier = Modifier.width(24.dp))
//
//                        Card(
//                            modifier = Modifier
//                                .height(85.dp)
//                                .width(85.dp),
//                            colors = CardDefaults.cardColors(RedProliseum)
//                        ) {
//                            Image(
//                                painter = if ("${funcaoJogadorPerfilUser}" == "0") painterResource(
//                                    id = R.drawable.icontoplane
//                                )
//                                else if ("${funcaoJogadorPerfilUser}" == "1") painterResource(id = R.drawable.iconjungle)
//                                else if ("${funcaoJogadorPerfilUser}" == "2") painterResource(id = R.drawable.iconmidlane)
//                                else if ("${funcaoJogadorPerfilUser}" == "3") painterResource(id = R.drawable.iconsupport)
//                                else if ("${funcaoJogadorPerfilUser}" == "4") painterResource(id = R.drawable.iconadc)
//                                else painter,
//                                contentDescription = "",
//                                modifier = Modifier.fillMaxSize(),
//                                alignment = Alignment.Center,
//                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
//                            )
//                        }
//                    }

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
                                text = "${biografiaOrganizacao}",
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




                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilOrganizadorScreenPreview() {
    ProliseumTCCTheme {

    }
}