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
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeById
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresDentroDeTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresDentroDeTimeList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresInfoPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresPropostasList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresPropostasRecebidas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelNomeJogadorListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
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

@Composable
fun PerfilJogadorDoMeuTimeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditarOutro: SharedViewModelPerfilOutro,
    sharedViewModelPerfilJogadorOutro: SharedViewModelPerfilJogadorOutro,
    sharedViewModelPerfilOrganizadorOutro: SharedViewModelPerfilOrganizadorOutro,

    // SharedViewModel GET MY TEAMS GERAL
    sharedGetMyTeamsGeral: SharedGetMyTeamsGeral,

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

    sharedViewModelNomeJogadorListaJogadores: SharedViewModelNomeJogadorListaJogadores,
    sharedViewModelGetListaJogadores: SharedViewModelGetListaJogadores,
    sharedViewModelGetListaJogadoresList: SharedViewModelGetListaJogadoresList,
    sharedViewModelGetListaJogadoresInfoPerfil: SharedViewModelGetListaJogadoresInfoPerfil,
    sharedViewModelGetListaJogadoresTimeAtual: SharedViewModelGetListaJogadoresTimeAtual,
    sharedViewModelGetListaJogadoresDentroDeTime: SharedViewModelGetListaJogadoresDentroDeTime,
    sharedViewModelGetListaJogadoresDentroDeTimeList: SharedViewModelGetListaJogadoresDentroDeTimeList,
    sharedViewModelGetListaJogadoresPropostasList: SharedViewModelGetListaJogadoresPropostasList,
    sharedViewModelGetListaJogadoresPropostasRecebidas: SharedViewModelGetListaJogadoresPropostasRecebidas,

    // SharedViewModel GET TIME BY ID
    sharedGetTimeById: SharedGetTimeById,
    sharedGetTimeByIdTeams: SharedGetTimeByIdTeams,
    sharedGetTimeByIdTeamsJogadores: SharedGetTimeByIdTeamsJogadores,
    sharedGetTimeByIdTeamsJogadoresPerfilId: SharedGetTimeByIdTeamsJogadoresPerfilId,
    sharedGetTimeByIdTeamsOrganizacao: SharedGetTimeByIdTeamsOrganizacao,
    sharedGetTimeByIdOrganizacaoDonoId: SharedGetTimeByIdOrganizacaoDonoId,
    sharedGetTimeByIdTeamsPropostas: SharedGetTimeByIdTeamsPropostas,
    onNavigate: (String) -> Unit
) {

    val token = sharedViewModelTokenEId.token
    Log.d("PerfilUsuarioJogadorScreen", "Token: $token")

    val imageRef = remember { mutableStateOf<StorageReference?>(null) }
    val imageCapaRef = remember { mutableStateOf<StorageReference?>(null) }

    val idUser = sharedViewModelPerfilEditarOutro.id
    val nomeUser = sharedViewModelPerfilEditarOutro.nome_usuario
    val fullNomeUser = sharedViewModelPerfilEditarOutro.nome_completo
    val dataNascimentoUser = sharedViewModelPerfilEditarOutro.data_nascimento
    val emailUser = sharedViewModelPerfilEditarOutro.email
    val nickNameUser = sharedViewModelPerfilEditarOutro.nickname
    val biografiaUser = sharedViewModelPerfilEditarOutro.biografia
    val generoPerfilUser = sharedViewModelPerfilEditarOutro.genero

    val idUsuarioJogadorPerfilUser = sharedViewModelPerfilJogadorOutro.id
    val nickNamejogadorPerfilUser = sharedViewModelPerfilJogadorOutro.nickname
    val jogoJogadorPerfilUser = sharedViewModelPerfilJogadorOutro.jogo
    val funcaoJogadorPerfilUser = sharedViewModelPerfilJogadorOutro.funcao
    val eloJogadorPerfilUser = sharedViewModelPerfilJogadorOutro.elo


    val orgProfile = sharedViewModelPerfilOrganizadorOutro.orgProfile
    val nomeOrganizacao = sharedViewModelPerfilOrganizadorOutro.nome_organizacao
    val biografiaOrganizacao = sharedViewModelPerfilOrganizadorOutro.biografia

    val idjogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.idData
    val nickNameJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.nickNameData
    val jogoJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.jogoData
    val funcaoJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.funcaoData
    val eloJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.eloData


    if(idUser != null && idUser != 0){


        val storage = Firebase.storage

        if (idUser != null && idUser != 0) {
            imageRef.value = storage.reference.child("${idUser}/profile")
        }

        if (idUser != null && idUser != 0) {
            imageCapaRef.value = storage.reference.child("${idUser}/capa")
        }

    } else{
        Log.e("TOKEN NULO", "Token do usuario esta nulo")
        Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
    }

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
    Log.e("URL IMAGEM DO USUARIO 03", "Id do URL da imagem do usuario ${idUser}")
    Log.e("URI IMAGEM DO USUARIO 03", "URI da imagem do usuario ${imageUri}")
    Log.e("URI CAPA DO USUARIO 03", "URI da imagem do usuario ${imageCapaUri}")


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
                modifier = Modifier.clickable {
                    //rememberNavController.navigate("home")
                    onNavigate("perfil_time")
                },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Button(
                onClick = {
                    //rememberNavController.navigate("editar_perfil_jogador_part_1")
                    //onNavigate("editar_perfil_usuario_padrao_1")
                },
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {

                Text(
                    text = "ENVIAR PROPOSTA",
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp
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

                    if (idUser != null && idUser != 0) {
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
                        text = "${nickNameUser}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    if(orgProfile == null) {
                        Spacer(modifier = Modifier.height(12.dp))
                    } else if (orgProfile != null){

                        Text(
                            text = "${nomeOrganizacao}",
                            modifier = Modifier.clickable {
//                                onNavigate("carregar_informacoes_perfil_organizacao")
                            },
                            fontSize = 18.sp,
                            fontWeight = FontWeight(600),
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }


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
                                painter =
                                if ("${jogoJogadorPerfilUser}" == "0") painterResource(
                                    id = R.drawable.iconlol
                                )
                                else if ("${jogoJogadorPerfilUser}" == "1") painterResource(id = R.drawable.iconlol)
                                else if ("${jogoJogadorPerfilUser}" == "2") painterResource(id = R.drawable.iconlol)
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
                                painter = if ("${funcaoJogadorPerfilUser}" == "0") painterResource(
                                    id = R.drawable.icontoplane
                                )
                                else if ("${funcaoJogadorPerfilUser}" == "1") painterResource(id = R.drawable.iconjungle)
                                else if ("${funcaoJogadorPerfilUser}" == "2") painterResource(id = R.drawable.iconmidlane)
                                else if ("${funcaoJogadorPerfilUser}" == "3") painterResource(id = R.drawable.iconsupport)
                                else if ("${funcaoJogadorPerfilUser}" == "4") painterResource(id = R.drawable.iconadc)
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
                                if ("${generoPerfilUser}" == "0") painterResource(id = R.drawable.generomasculino)
                                else if ("${generoPerfilUser}" == "1") painterResource(id = R.drawable.generofeminino)
                                else if ("${generoPerfilUser}" == "2") painterResource(id = R.drawable.generoindefinido)
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
                                text = "${biografiaUser}",
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
                                painter = if ("${eloJogadorPerfilUser}" == "0") painterResource(id = R.drawable.icone_iron)
                                else if ("${eloJogadorPerfilUser}" == "1") painterResource(id = R.drawable.icone_bronze)
                                else if ("${eloJogadorPerfilUser}" == "2") painterResource(id = R.drawable.icone_silver)
                                else if ("${eloJogadorPerfilUser}" == "3") painterResource(id = R.drawable.icone_gold)
                                else if ("${eloJogadorPerfilUser}" == "4") painterResource(id = R.drawable.icone_platinum)
                                else if ("${eloJogadorPerfilUser}" == "5") painterResource(id = R.drawable.icone_diamond)
                                else if ("${eloJogadorPerfilUser}" == "6") painterResource(id = R.drawable.icone_master)
                                else if ("${eloJogadorPerfilUser}" == "7") painterResource(id = R.drawable.icone_grandmaster)
                                else if ("${eloJogadorPerfilUser}" == "8") painterResource(id = R.drawable.icone_challenger)
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