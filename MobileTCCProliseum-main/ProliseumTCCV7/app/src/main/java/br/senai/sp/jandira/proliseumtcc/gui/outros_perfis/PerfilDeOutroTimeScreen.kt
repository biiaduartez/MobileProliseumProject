package br.senai.sp.jandira.proliseumtcc.gui.outros_perfis

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
import androidx.compose.foundation.lazy.LazyRow
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
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsPropostas
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
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
fun PerfilDeOutroTimeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
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

    // SharedViewModel GET TIME FILTER
    sharedGetTime: SharedGetTime,
    sharedGetTimeTeams: SharedGetTimeTeams,
    sharedGetTimeTeamsJogadores: SharedGetTimeTeamsJogadores,
    sharedGetTimeTeamsJogadoresPerfilId: SharedGetTimeTeamsJogadoresPerfilId,
    sharedGetTimeTeamsOrganizacao: SharedGetTimeTeamsOrganizacao,
    sharedGetTimeOrganizacaoDonoId: SharedGetTimeDono,
    sharedGetTimeTeamsPropostas: SharedGetTimeTeamsPropostas,
    onNavigate: (String) -> Unit
) {

    val token = sharedViewModelTokenEId.token
    Log.d("PerfilUsuarioJogadorScreen", "Token: $token")

    val imageRef = remember { mutableStateOf<StorageReference?>(null) }

    val imageTimeRef = remember { mutableStateOf<StorageReference?>(null) }
    val imageTimeCapaRef = remember { mutableStateOf<StorageReference?>(null) }

    val idUser = sharedViewModelPerfilEditar.id
    val nomeUser = sharedViewModelPerfilEditar.nome_usuario
    val fullNomeUser = sharedViewModelPerfilEditar.nome_completo
    val dataNascimentoUser = sharedViewModelPerfilEditar.data_nascimento
    val emailUser = sharedViewModelPerfilEditar.email
    val nickNameUser = sharedViewModelPerfilEditar.nickname
    val biografiaUser = sharedViewModelPerfilEditar.biografia
    val generoPerfilUser = sharedViewModelPerfilEditar.genero

//    val idUsuarioJogadorPerfilUser = sharedViewModelPerfilJogador.id
//    val nickNamejogadorPerfilUser = sharedViewModelPerfilJogador.nickname
//    val jogoJogadorPerfilUser = sharedViewModelPerfilJogador.jogo
//    val funcaoJogadorPerfilUser = sharedViewModelPerfilJogador.funcao
//    val eloJogadorPerfilUser = sharedViewModelPerfilJogador.elo
//
//
//    val orgProfile = sharedViewModelPerfilOrganizador.orgProfile
//    val nomeOrganizacao = sharedViewModelPerfilOrganizador.nome_organizacao
//    val biografiaOrganizacao = sharedViewModelPerfilOrganizador.biografia

    val myTeamsDataTime = sharedGetMyTeamsGeral.time
    val infoListaJogadores = sharedViewModelGetMyTeamsTimeJogadores.infoJogadoresEmTime

    val idTime = sharedViewModelGetMyTeamsTime.idData
    val nomeTime = sharedViewModelGetMyTeamsTime.nomeTimeData
    val jogoEscolhidoTime = sharedViewModelGetMyTeamsTime.jogoData
    val biografiaTime = sharedViewModelGetMyTeamsTime.biografiaData

    val idjogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.idData
    val nickNameJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.nickNameData
    val jogoJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.jogoData
    val funcaoJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.funcaoData
    val eloJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.eloData


    val selectedTimeId by remember { mutableStateOf(sharedGetTime.selectedTimeFilterId) }
    Log.e("ID DO TIME COMPARTILHADO","ID compartilhado ${selectedTimeId}")


    val team = selectedTimeId?.let { sharedGetTime.getTeamFilter(it) }
    Log.e("ID DO TIME FILTER HAHA","o id do time FILTER da tela PerfilOutroTime ${team}")



//    val selectedJogadorTimeById by remember { mutableStateOf(sharedGetTimeByIdTeams.selectedJogadorIdTimeById) }
//    Log.e("AAA","ID jogador compartilhado ${selectedJogadorTimeById}")
//
//
//    val teamById = selectedJogadorTimeById?.let { sharedGetTimeByIdTeams.getTeamByIdJogadores(it) }
//    Log.e("BBB","teste teste:  ${teamById}")

    val idProposta = sharedViewModelGetMyTeamsTimePropostas.idData
    val menssagemProposta = sharedViewModelGetMyTeamsTimePropostas.mensagemData

    // GET TIME BY ID

    val  idGetMyTeamCompartilhado = sharedGetTimeTeamsJogadores.id
    val  nickNameGetMyTeamCompartilhado = sharedGetTimeTeamsJogadores.nickname
    val  jogoGetMyTeamCompartilhado = sharedGetTimeTeamsJogadores.jogo
    val  funcaoGetMyTeamCompartilhado = sharedGetTimeTeamsJogadores.funcao
    val  eloGetMyTeamCompartilhado = sharedGetTimeTeamsJogadores.elo

    val  idGetMyTeamCompartilhadoPerfilId = sharedGetTimeTeamsJogadoresPerfilId.id
    val  nomeUsuarioGetMyTeamCompartilhadoPerfilId = sharedGetTimeTeamsJogadoresPerfilId.nome_usuario
    val  nomeCompletoGetMyTeamCompartilhadoPerfilId = sharedGetTimeTeamsJogadoresPerfilId.nome_completo
    val  emailGetMyTeamCompartilhadoPerfilId = sharedGetTimeTeamsJogadoresPerfilId.email
    val  senhaGetMyTeamCompartilhadoPerfilId = sharedGetTimeTeamsJogadoresPerfilId.senha
    val  dataNascimentoGetMyTeamCompartilhadoPerfilId = sharedGetTimeTeamsJogadoresPerfilId.data_nascimento
    val  biografiaGetMyTeamCompartilhadoPerfilId = sharedGetTimeTeamsJogadoresPerfilId.biografia
    val  generoGetMyTeamCompartilhadoPerfilId = sharedGetTimeTeamsJogadoresPerfilId.genero
    val  nickNameGetMyTeamCompartilhadoPerfilId = sharedGetTimeTeamsJogadoresPerfilId.nickname

    val somenteUmTesteJogadores = sharedGetTimeTeams.jogadores

    //val jogadoresDoTime: List<getTimeTeamsJogadores>? = sharedGetTimeTeams.jogadores

    if(idUser != null && idUser != 0){


        val storage = Firebase.storage


        if (idUser != null && idUser != 0) {
            if (team != null) {
                imageTimeRef.value = storage.reference.child("team/${team.id}/profile")
            }
        }

        if (idUser != null && idUser != 0) {
            if (team != null) {
                imageTimeCapaRef.value = storage.reference.child("team/${team.id}/capa")
            }
        }

    } else{
        Log.e("TOKEN NULO", "Token do usuario esta nulo")
        Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
    }

    //    FIREBASE

    var imageTimeUri by remember { mutableStateOf<Uri?>(null) }
    var imageTimeCapaUri by remember { mutableStateOf<Uri?>(null) }


    if (imageTimeRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uriTime = imageTimeRef.value!!.downloadUrl.await()
                imageTimeUri = uriTime

                Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uriTime}")

            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }

    if (imageTimeCapaRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uriTimeCapa = imageTimeCapaRef.value!!.downloadUrl.await()
                imageTimeCapaUri = uriTimeCapa


                Log.e("URI CAPA DO USUARIO 02", "URI da imagem do usuario ${uriTimeCapa}")
            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
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
        ///////
        // Imagem Capa
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)

        ) {

            if (idUser != null && idUser != 0) {
                // Exiba a imagem se a URI estiver definida
                AsyncImage(
                    model = imageTimeCapaUri,
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
                    onNavigate("lista_times")
                },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Button(
                onClick = {
                    //rememberNavController.navigate("editar_perfil_jogador_part_1")
//                    onNavigate("editar_perfil_time")
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


                    Card(
                        modifier = Modifier
                            .size(150.dp),
                        shape = CircleShape
                    ) {
                        if (idUser != null && idUser != 0) {
                            // Exiba a imagem se a URI estiver definida
                            AsyncImage(
                                model = imageTimeUri,
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
                        text = team?.nome_time ?: "Time não encontrado",
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(12.dp))


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

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                        ,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier
                                .height(55.dp)
                                .width(55.dp),
                            colors = CardDefaults.cardColors(RedProliseum)
                        ) {
                            Image(
                                painter =
                                if ("${team?.jogo}" == "0") painterResource(
                                    id = R.drawable.iconlol
                                )
                                else if ("${team?.jogo}" == "1") painterResource(id = R.drawable.iconlol)
                                else if ("${team?.jogo}" == "2") painterResource(id = R.drawable.iconlol)
                                else painter,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                alignment = Alignment.Center,
                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                            )
                        }
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
                                text = "${team?.biografia ?: "Biografia não encontrada"}",
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

                    if (somenteUmTesteJogadores != null) {
                        Log.e("BODY GET TIME FILTER 01 ","Informacao de jogadores do GET TIME FILTER: ${somenteUmTesteJogadores.size}")
                    }
                    if (team != null) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(start = 0.dp, top = 0.dp)
                            ,
                            verticalAlignment = Alignment.CenterVertically

                        ){
                            if (team != null) {
                                if (somenteUmTesteJogadores != null) {
                                    items(somenteUmTesteJogadores.size) { index ->
                                        val jogador = somenteUmTesteJogadores[index]

                                        val infoPerfilId = jogador.perfil_id?.id ?: 0
                                        val nomeUsuarioInfoPerfilOutroJogadorListaTimes = jogador.perfil_id?.nome_usuario ?: ""
                                        val nomeCompletoInfoPerfilOutroJogadorListaTimes = jogador.perfil_id?.nome_completo ?: ""
                                        val emailInfoPerfilOutroJogadorListaTimes = jogador.perfil_id?.email ?: ""
                                        val dataNascimentoInfoPerfilOutroJogadorListaTimes = jogador.perfil_id?.data_nascimento ?: ""
                                        val generoInfoPerfilOutroJogadorListaTimes = jogador.perfil_id?.genero ?: 0
                                        val nicknameInfoPerfilOutroJogadorListaTimes = jogador.perfil_id?.nickname ?: ""
                                        val biografiaInfoPerfilOutroJogadorListaTimes = jogador.perfil_id?.biografia ?: ""

                                        val idInfoIdJogador = infoPerfilId ?: 0

                                        val imageRefJogadorTime = remember { mutableStateOf<StorageReference?>(null) }

                                        if(idUser != null && idUser != 0){


                                            val storage = Firebase.storage

                                            if (idInfoIdJogador != null && idInfoIdJogador != 0) {
                                                imageRefJogadorTime.value = storage.reference.child("${idInfoIdJogador}/profile")
                                            }


                                        } else{
                                            Log.e("TOKEN NULO", "Token do usuario esta nulo")
                                            Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
                                        }

                                        var imageUriJogadorTime by remember { mutableStateOf<Uri?>(null) }

                                        if (imageRefJogadorTime.value != null) { // Verifique a referência do Firebase
                                            LaunchedEffect(Unit) {
                                                try {
                                                    val uriJogadorTime = imageRefJogadorTime.value!!.downloadUrl.await()
                                                    imageUriJogadorTime = uriJogadorTime

                                                    Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uriJogadorTime}")

                                                } catch (e: Exception) {
                                                    // Trate os erros, se houver algum
                                                    Log.e("DEBUG", "Erro ao buscar imagem: $e")
                                                }
                                            }
                                        }

                                        Log.e("BODY GET MY TIME ID 02 ","Informacao de jogadores do GET MY TIME BY ID: ${somenteUmTesteJogadores}")
                                        //jogos
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 20.dp, top = 20.dp),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Button(
                                                onClick = {
                                                    sharedGetTimeTeamsJogadoresPerfilId.id = idInfoIdJogador
                                                    sharedGetTimeTeamsJogadoresPerfilId.nome_usuario = nomeUsuarioInfoPerfilOutroJogadorListaTimes
                                                    sharedGetTimeTeamsJogadoresPerfilId.nome_completo = nomeCompletoInfoPerfilOutroJogadorListaTimes
                                                    sharedGetTimeTeamsJogadoresPerfilId.email = emailInfoPerfilOutroJogadorListaTimes
                                                    sharedGetTimeTeamsJogadoresPerfilId.data_nascimento = dataNascimentoInfoPerfilOutroJogadorListaTimes
                                                    sharedGetTimeTeamsJogadoresPerfilId.genero = generoInfoPerfilOutroJogadorListaTimes
                                                    sharedGetTimeTeamsJogadoresPerfilId.nickname = nicknameInfoPerfilOutroJogadorListaTimes
                                                    sharedGetTimeTeamsJogadoresPerfilId.biografia = biografiaInfoPerfilOutroJogadorListaTimes

                                                    onNavigate("carregar_informacoes_perfil_outro_jogador_lista_jogadores")
                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(80.dp)
                                                    .padding(start = 0.dp, top = 0.dp),
                                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                                colors = ButtonDefaults.buttonColors(RedProliseum)
                                            ) {


                                                Column(
                                                    modifier = Modifier.fillMaxSize(),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                ) {
                                                    Card(
                                                        modifier = Modifier
                                                            .width(40.dp)
                                                            .height(40.dp),
                                                        shape = CircleShape
                                                    ) {
                                                        if (idUser != null && idUser != 0) {
                                                            // Exiba a imagem se a URI estiver definida
                                                            AsyncImage(
                                                                model = imageUriJogadorTime,
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



                                                Spacer(modifier = Modifier.width(5.dp))

                                                Card(
                                                    modifier = Modifier
                                                        .height(55.dp)
                                                        .width(55.dp),
                                                    colors = CardDefaults.cardColors(RedProliseum)
                                                ) {
                                                    Image(
                                                        painter = when (jogador.funcao) {
                                                            0 -> painterResource(id = R.drawable.icontoplane)
                                                            1 -> painterResource(id = R.drawable.iconjungle)
                                                            2 -> painterResource(id = R.drawable.iconmidlane)
                                                            3 -> painterResource(id = R.drawable.iconsupport)
                                                            4 -> painterResource(id = R.drawable.iconadc)
                                                            else -> painter
                                                        },
                                                        contentDescription = "",
                                                        modifier = Modifier.fillMaxSize(),
                                                        alignment = Alignment.Center,
                                                        colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                                                    )
                                                }

                                                Spacer(modifier = Modifier.width(5.dp))

                                                Text(
                                                    text = "${jogador.nickname}",
                                                    color = Color.White,
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight(600),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 12.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}