package br.senai.sp.jandira.proliseumtcc.gui

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
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeById
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresDentroDeTime
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresDentroDeTimeList
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresInfoPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresList
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresPropostasList
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresPropostasRecebidas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetListaJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelNomeJogadorListaJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
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

//@Composable
//fun ListaDeTimeScreen(
//    sharedViewModelTokenEId: SharedViewTokenEId,
////    sharedViewModelPerfilEditarOutro: SharedViewModelPerfilOutro,
////    sharedViewModelPerfilJogadorOutro: SharedViewModelPerfilJogadorOutro,
////    sharedViewModelPerfilOrganizadorOutro: SharedViewModelPerfilOrganizadorOutro,
//    sharedViewModelPerfilEditar: SharedViewModelPerfil,
//    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
//    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,
//
//    // SharedViewModel GET MY TEAMS GERAL
//    sharedGetMyTeamsGeral: SharedGetMyTeamsGeral,
//
//    // SharedViewModelGetMyTeams de USUARIO
//    sharedViewModelGetMyTeamsUser: SharedViewModelGetMyTeamsUser,
//    sharedViewModelGetMyTeamsUserPropostas: SharedViewModelGetMyTeamsUserPropostas,
//    sharedViewModelGetMyTeamsUserPropostasDe: SharedGetMyTeamsUserPropostasDe,
//    sharedViewModelGetMyTeamsUserPropostasDeJogadores: SharedGetMyTeamsUserPropostasDeJogadores,
//    sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos: SharedGetMyTeamsUserPropostasDeJogadoresAtivos,
//    sharedViewModelGetMyTeamsUserPropostasDePropostas: SharedGetMyTeamsUserPropostasDePropostas,
//
//    // SharedViewModelGetMyTeams de TIME
//    sharedViewModelGetMyTeamsTime: SharedViewModelGetMyTeamsTime,
//    sharedViewModelGetMyTeamsTimeJogadores: SharedViewModelGetMyTeamsTimeJogadores,
//    sharedViewModelGetMyTeamsTimeJogadoresAtivos: SharedGetMyTeamsTimeJogadoresAtivos,
//    sharedViewModelGetMyTeamsTimePropostas: SharedViewModelGetMyTeamsTimePropostas,
//
//    sharedViewModelNomeJogadorListaJogadores: SharedViewModelNomeJogadorListaJogadores,
//    sharedViewModelGetListaJogadores: SharedViewModelGetListaJogadores,
//    sharedViewModelGetListaJogadoresList: SharedViewModelGetListaJogadoresList,
//    sharedViewModelGetListaJogadoresInfoPerfil: SharedViewModelGetListaJogadoresInfoPerfil,
//    sharedViewModelGetListaJogadoresTimeAtual: SharedViewModelGetListaJogadoresTimeAtual,
//    sharedViewModelGetListaJogadoresDentroDeTime: SharedViewModelGetListaJogadoresDentroDeTime,
//    sharedViewModelGetListaJogadoresDentroDeTimeList: SharedViewModelGetListaJogadoresDentroDeTimeList,
//    sharedViewModelGetListaJogadoresPropostasList: SharedViewModelGetListaJogadoresPropostasList,
//    sharedViewModelGetListaJogadoresPropostasRecebidas: SharedViewModelGetListaJogadoresPropostasRecebidas,
//
//    // SharedViewModel GET TIME BY ID
//    sharedGetTimeById: SharedGetTimeById,
//    sharedGetTimeByIdTeams: SharedGetTimeByIdTeams,
//    sharedGetTimeByIdTeamsJogadores: SharedGetTimeByIdTeamsJogadores,
//    sharedGetTimeByIdTeamsJogadoresPerfilId: SharedGetTimeByIdTeamsJogadoresPerfilId,
//    sharedGetTimeByIdTeamsOrganizacao: SharedGetTimeByIdTeamsOrganizacao,
//    sharedGetTimeByIdOrganizacaoDonoId: SharedGetTimeByIdOrganizacaoDonoId,
//    sharedGetTimeByIdTeamsPropostas: SharedGetTimeByIdTeamsPropostas,
//    onNavigate: (String) -> Unit
//) {
//
//    val token = sharedViewModelTokenEId.token
//    Log.d("PerfilUsuarioJogadorScreen", "Token: $token")
//
//    val imageRef = remember { mutableStateOf<StorageReference?>(null) }
//
//    val imageTimeRef = remember { mutableStateOf<StorageReference?>(null) }
//    val imageTimeCapaRef = remember { mutableStateOf<StorageReference?>(null) }
//
//    val idUser = sharedViewModelPerfilEditar.id
//    val nomeUser = sharedViewModelPerfilEditar.nome_usuario
//    val fullNomeUser = sharedViewModelPerfilEditar.nome_completo
//    val dataNascimentoUser = sharedViewModelPerfilEditar.data_nascimento
//    val emailUser = sharedViewModelPerfilEditar.email
//    val nickNameUser = sharedViewModelPerfilEditar.nickname
//    val biografiaUser = sharedViewModelPerfilEditar.biografia
//    val generoPerfilUser = sharedViewModelPerfilEditar.genero
//
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
//
//    val myTeamsDataTime = sharedGetMyTeamsGeral.time
//    val infoListaJogadores = sharedViewModelGetMyTeamsTimeJogadores.infoJogadoresEmTime
//
//    val idTime = sharedViewModelGetMyTeamsTime.idData
//    val nomeTime = sharedViewModelGetMyTeamsTime.nomeTimeData
//    val jogoEscolhidoTime = sharedViewModelGetMyTeamsTime.jogoData
//    val biografiaTime = sharedViewModelGetMyTeamsTime.biografiaData
//
//    val idjogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.idData
//    val nickNameJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.nickNameData
//    val jogoJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.jogoData
//    val funcaoJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.funcaoData
//    val eloJogadorNoTime = sharedViewModelGetMyTeamsTimeJogadores.eloData
//
//
//    val selectedTimeId by remember { mutableStateOf(sharedGetMyTeamsGeral.selectedTimeId) }
//    Log.e("ID DO TIME COMPARTILHADO","ID compartilhado ${selectedTimeId}")
//
//
//    val team = selectedTimeId?.let { sharedGetMyTeamsGeral.getTeamById(it) }
//    Log.e("ID DO TIME ESCOLHIDO","o id do time da tela PerfilTime ${team}")
//
//
//
////    val selectedJogadorTimeById by remember { mutableStateOf(sharedGetTimeByIdTeams.selectedJogadorIdTimeById) }
////    Log.e("AAA","ID jogador compartilhado ${selectedJogadorTimeById}")
////
////
////    val teamById = selectedJogadorTimeById?.let { sharedGetTimeByIdTeams.getTeamByIdJogadores(it) }
////    Log.e("BBB","teste teste:  ${teamById}")
//
//    val idProposta = sharedViewModelGetMyTeamsTimePropostas.idData
//    val menssagemProposta = sharedViewModelGetMyTeamsTimePropostas.mensagemData
//
//    // GET TIME BY ID
//
//    val  idGetMyTeamCompartilhado = sharedGetTimeByIdTeamsJogadores.id
//    val  nickNameGetMyTeamCompartilhado = sharedGetTimeByIdTeamsJogadores.nickname
//    val  jogoGetMyTeamCompartilhado = sharedGetTimeByIdTeamsJogadores.jogo
//    val  funcaoGetMyTeamCompartilhado = sharedGetTimeByIdTeamsJogadores.funcao
//    val  eloGetMyTeamCompartilhado = sharedGetTimeByIdTeamsJogadores.elo
//
//    val  idGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.id
//    val  nomeUsuarioGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.nome_usuario
//    val  nomeCompletoGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.nome_completo
//    val  emailGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.email
//    val  senhaGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.senha
//    val  dataNascimentoGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.data_nascimento
//    val  biografiaGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.biografia
//    val  generoGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.genero
//    val  nickNameGetMyTeamCompartilhadoPerfilId = sharedGetTimeByIdTeamsJogadoresPerfilId.nickname
//
//    val somenteUmTesteJogadores = sharedGetTimeByIdTeams.jogadores
//
//    if(idUser != null && idUser != 0){
//
//
//        val storage = Firebase.storage
//
//
//        if (idUser != null && idUser != 0) {
//            imageRef.value = storage.reference.child("${idUser}/profile")
//        }
//
//        if (idUser != null && idUser != 0) {
//            if (team != null) {
//                imageTimeRef.value = storage.reference.child("team/${team.id}/profile")
//            }
//        }
//
//        if (idUser != null && idUser != 0) {
//            if (team != null) {
//                imageTimeCapaRef.value = storage.reference.child("team/${team.id}/capa")
//            }
//        }
//
//    } else{
//        Log.e("TOKEN NULO", "Token do usuario esta nulo")
//        Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
//    }
//
//    //    FIREBASE
//
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    var imageTimeUri by remember { mutableStateOf<Uri?>(null) }
//    var imageTimeCapaUri by remember { mutableStateOf<Uri?>(null) }
//
//    if (imageRef.value != null) { // Verifique a referência do Firebase
//        LaunchedEffect(Unit) {
//            try {
//                val uriOrg = imageRef.value!!.downloadUrl.await()
//                imageUri = uriOrg
//
//                Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uriOrg}")
//
//            } catch (e: Exception) {
//                // Trate os erros, se houver algum
//                Log.e("DEBUG", "Erro ao buscar imagem: $e")
//            }
//        }
//    }
//
//    if (imageTimeRef.value != null) { // Verifique a referência do Firebase
//        LaunchedEffect(Unit) {
//            try {
//                val uriTime = imageTimeRef.value!!.downloadUrl.await()
//                imageTimeUri = uriTime
//
//                Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uriTime}")
//
//            } catch (e: Exception) {
//                // Trate os erros, se houver algum
//                Log.e("DEBUG", "Erro ao buscar imagem: $e")
//            }
//        }
//    }
//
//    if (imageTimeCapaRef.value != null) { // Verifique a referência do Firebase
//        LaunchedEffect(Unit) {
//            try {
//                val uriTimeCapa = imageTimeCapaRef.value!!.downloadUrl.await()
//                imageTimeCapaUri = uriTimeCapa
//
//
//                Log.e("URI CAPA DO USUARIO 02", "URI da imagem do usuario ${uriTimeCapa}")
//            } catch (e: Exception) {
//                // Trate os erros, se houver algum
//                Log.e("DEBUG", "Erro ao buscar imagem: $e")
//            }
//        }
//    }
//
//    // FIREBASE
//    Log.e("URL IMAGEM DO USUARIO 03", "Id do URL da imagem do usuario ${idUser}")
//    Log.e("URI IMAGEM DO USUARIO 03", "URI da imagem do usuario ${imageUri}")
//    Log.e("URI CAPA DO USUARIO 03", "URI da imagem do usuario ${imageTimeCapaRef}")
//
//
//    val customFontFamily = FontFamily(
//        Font(R.font.font_title)
//    )
//    val customFontFamilyText = FontFamily(
//        Font(R.font.font_poppins)
//    )
//
//    var photoUri by remember {
//        mutableStateOf<Uri?>(null)
//    }
//
//    var launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) {
//        photoUri = it
//    }
//
//    var painter = rememberAsyncImagePainter(
//        ImageRequest.Builder(LocalContext.current)
//            .data(photoUri)
//            .build()
//    )
//
//
//}