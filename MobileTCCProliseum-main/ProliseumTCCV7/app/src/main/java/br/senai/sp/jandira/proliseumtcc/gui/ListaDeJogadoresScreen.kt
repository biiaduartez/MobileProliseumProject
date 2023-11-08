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
import androidx.compose.material3.CircularProgressIndicator
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
import br.senai.sp.jandira.proliseumtcc.gui.perfis.verificarIdDoTime
import br.senai.sp.jandira.proliseumtcc.model.GetJogadores
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadores
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
fun ListaDeJogadoresScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,

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

    val dadosGeraisGetMyTeams = sharedGetMyTeamsGeral.myTeamsDadosGeral
    val dadosTimeGetMyTeams = sharedGetMyTeamsGeral.time

    val userIdGetMyTeams = sharedViewModelGetMyTeamsUser.idData

    val idTime = sharedViewModelGetMyTeamsTime.idData

    val nomeTime = sharedViewModelGetMyTeamsTime.nomeTimeData
    val jogoTime = sharedViewModelGetMyTeamsTime.jogoData

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

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(start = 0.dp, top = 0.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically

                ){
                    if (dadosTimeGetMyTeams != null) {
                        items(dadosTimeGetMyTeams.size) {index ->
                            val time = dadosTimeGetMyTeams[index]
                            //jogos
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, top = 20.dp)
                                ,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    onClick = {
                                        val timeId = time.id // Obtenha o ID do time clicado
                                        val verificacao = true

                                        if (verificacao == true) {
                                            verificarIdDoTime(sharedViewModelGetMyTeamsTime, sharedGetMyTeamsGeral, timeId)
                                            sharedGetMyTeamsGeral.selectedTimeId = timeId
                                            Log.e("SHAREDVIEW ID"," Aqui esta o id do time que ficou salvo no SharedViewModel${sharedGetMyTeamsGeral.selectedTimeId}")
                                            onNavigate("perfil_time")
                                        }

                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                        .padding(start = 0.dp, top = 0.dp),
                                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                    colors = ButtonDefaults.buttonColors(RedProliseum)
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .height(55.dp)
                                            .width(55.dp),
                                        colors = CardDefaults.cardColors(RedProliseum)
                                    ) {
                                        Image(
                                            painter =
                                            if ("${time.jogo}" == "0") painterResource(
                                                id = R.drawable.iconcsgo
                                            )
                                            else if ("${time.jogo}" == "1") painterResource(id = R.drawable.iconlol)
                                            else if ("${time.jogo}" == "2") painterResource(id = R.drawable.iconvalorant)
                                            else painter,
                                            contentDescription = "",
                                            modifier = Modifier.fillMaxSize(),
                                            alignment = Alignment.Center,
                                            colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(5.dp))

                                    Text(
                                        text = "${time.nome_time}",
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

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(start = 0.dp, top = 0.dp)
                        ,
                        verticalAlignment = Alignment.CenterVertically

                    ){
                        if (dadosTimeGetMyTeams != null) {
                            items(dadosTimeGetMyTeams.size) {index ->
                                val time = dadosTimeGetMyTeams[index]
                                //jogos
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, top = 20.dp)
                                    ,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Button(
                                        onClick = {
                                            val timeId = time.id // Obtenha o ID do time clicado
                                            val verificacao = true

                                            if (verificacao == true) {
                                                verificarIdDoTime(sharedViewModelGetMyTeamsTime, sharedGetMyTeamsGeral, timeId)
                                                sharedGetMyTeamsGeral.selectedTimeId = timeId
                                                Log.e("SHAREDVIEW ID"," Aqui esta o id do time que ficou salvo no SharedViewModel${sharedGetMyTeamsGeral.selectedTimeId}")
                                                onNavigate("perfil_time")
                                            }

                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(80.dp)
                                            .padding(start = 0.dp, top = 0.dp),
                                        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                        colors = ButtonDefaults.buttonColors(RedProliseum)
                                    ) {
                                        Card(
                                            modifier = Modifier
                                                .height(55.dp)
                                                .width(55.dp),
                                            colors = CardDefaults.cardColors(RedProliseum)
                                        ) {
                                            Image(
                                                painter =
                                                if ("${time.jogo}" == "0") painterResource(
                                                    id = R.drawable.iconcsgo
                                                )
                                                else if ("${time.jogo}" == "1") painterResource(id = R.drawable.iconlol)
                                                else if ("${time.jogo}" == "2") painterResource(id = R.drawable.iconvalorant)
                                                else painter,
                                                contentDescription = "",
                                                modifier = Modifier.fillMaxSize(),
                                                alignment = Alignment.Center,
                                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(5.dp))

                                        Text(
                                            text = "${time.nome_time}",
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

@Composable
fun GetListajogadoresFunction(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,
    sharedViewModelNomeJogadorListaJogadores: SharedViewModelNomeJogadorListaJogadores,
    nomeJogadorPesquisado: String?,
){
    // Se o tempo de espera terminou, continue com a validação do token
    // Restante do código aqui
    val token = sharedViewModelTokenEId.token
    // Restante do seu código de validação do token
    Log.d("CarregarPerfilUsuarioScreen", "Token: $token")

    if(token != null && token.isNotEmpty()){

        val perPage by remember { mutableStateOf<Int?>(null) }
        val page by remember { mutableStateOf<Int?>(null) }
        val name by remember { mutableStateOf<String?>("") }

        val getInformacoesListaJogadores = GetJogadores(
            name = nomeJogadorPesquisado
        )


        val getInfoJogadoresService = RetrofitFactoryCadastro().getJogadoresService()

        getInfoJogadoresService.getListajogadores(perPage, page, name, getInformacoesListaJogadores).enqueue(object :
            Callback<ResponseGetListaJogadores> {
            override fun onResponse(call: Call<ResponseGetListaJogadores>, response: Response<ResponseGetListaJogadores>) {
                if (response.isSuccessful) {
                    Log.d("PerfilUsuarioJogadorScreen", "Resposta bem-sucedida: ${response.code()}")
                    val filtragemPorNomeData = response.body()

                    val listaJogadores = filtragemPorNomeData?.players

                    if(listaJogadores != null){
                        for (infoJogadores in listaJogadores){
                            val idJogador = infoJogadores.id
                            val nicknameJogador = infoJogadores.nickname
                            val jogoJogador = infoJogadores.jogo
                            val funcaoJogador = infoJogadores.funcao
                            val eloJogador = infoJogadores.elo


                            val infoPerfilId = infoJogadores.perfil_id

                            val idInfoPerfilJogador = infoPerfilId?.id
                            val nomeUsuarioInfoPerfilJogador = infoPerfilId?.nome_usuario
                            val nomeCompletoInfoPerfilJogador = infoPerfilId?.nome_completo
                            val emailInfoPerfilJogador = infoPerfilId?.email
                            val senhaInfoPerfilJogador = infoPerfilId?.senha
                            val dataNascimentoInfoPerfilJogador = infoPerfilId?.data_nascimento
                            val generoInfoPerfilJogador = infoPerfilId?.genero
                            val nickNameInfoPerfilJogador = infoPerfilId?.nickname
                            val biografiaInfoPerfilJogador = infoPerfilId?.biografia

                            val infoTimeAtual = infoJogadores.time_atual

                            val idInfoTime = infoTimeAtual?.id
                            val nomeInfoTime = infoTimeAtual?.nome_time
                            val jogoInfoTime = infoTimeAtual?.jogo
                            val biografiaInfoTime = infoTimeAtual?.biografia


                            val infoJogadoresDentroTime = infoTimeAtual?.jogadores

                            if(infoJogadoresDentroTime != null){
                                for (infoJogadoresTime in infoJogadoresDentroTime){
                                    val idInfoJogadoresDentroDoTime = infoJogadoresTime.id
                                    val nickNameInfoJogadoresDentroDoTime = infoJogadoresTime.nickname
                                    val jogoInfoJogadoresDentroDoTime = infoJogadoresTime.jogo
                                    val funcaoInfoJogadoresDentroDoTime = infoJogadoresTime.funcao
                                    val eloInfoJogadoresDentroDoTime = infoJogadoresTime.elo
                                }
                            }

                            val infoPropostas = infoTimeAtual?.propostas

                            if (infoPropostas != null){
                                for (infoProposta in infoPropostas){
                                    val idInfoProposta = infoProposta.id
                                    val menssagemInfoProposta = infoProposta.menssagem
                                }
                            }
                        }
                    }



                    Log.d("INFORMAÇOES DE USUARIO 01", "Token: $token, Id: ${sharedViewModelPerfilEditar.id}, Nome de usuario: ${sharedViewModelPerfilEditar.nome_usuario}")
                    Log.d("CarregarPerfilUsuarioScreen", "Resposta corpo bem-sucedida: ${response.code()}")



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

            override fun onFailure(call: Call<ResponseGetListaJogadores>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
            }

        })

    } else{
        Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")

    }
}