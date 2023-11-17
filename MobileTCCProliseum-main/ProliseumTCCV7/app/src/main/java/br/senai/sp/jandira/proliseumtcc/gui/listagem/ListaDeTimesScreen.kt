package br.senai.sp.jandira.proliseumtcc.gui.listagem

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeOrganizacaoDonoId
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
import br.senai.sp.jandira.proliseumtcc.model.getTime
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeams
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDeTimesScreen (
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

    // SharedViewModel GET TIME BY ID
    sharedGetTime: SharedGetTime,
    sharedGetTimeTeams: SharedGetTimeTeams,
    sharedGetTimeTeamsJogadores: SharedGetTimeTeamsJogadores,
    sharedGetTimeTeamsJogadoresPerfilId: SharedGetTimeTeamsJogadoresPerfilId,
    sharedGetTimeTeamsOrganizacao: SharedGetTimeTeamsOrganizacao,
    sharedGetTimeOrganizacaoDonoId: SharedGetTimeOrganizacaoDonoId,
    sharedGetTimeTeamsPropostas: SharedGetTimeTeamsPropostas,
    onNavigate: (String) -> Unit
) {

    var loading by remember { mutableStateOf(true) }

    val idUser = sharedViewModelPerfilEditar.id

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

    var nomeTimePesquisado by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        // Espera por 3 segundos antes de continuar
        delay(3000)
        loading = false
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var exibirConteudo by remember { mutableStateOf(false) }

    var minhaListaDeTimes by remember {
        mutableStateOf(listOf<getTimeTeams>())
    }

    Log.e("TESTE SHARED VIEW", "TESTE DE COMPARTILHAMENTO COM TESTE VIEW MODEL: ${sharedGetTime.teams}")

    val token = sharedViewModelTokenEId.token

    if(token != null && token.isNotEmpty()){

        val qualquerNome = ""

        val perfilTimeService = RetrofitFactoryCadastro().theGetTimeService()

        perfilTimeService.theGetTime(qualquerNome).enqueue(object : Callback<getTime> {
            override fun onResponse(call: Call<getTime>, response: Response<getTime>) {
                if (response.isSuccessful) {
                    Log.d("GET TIME FILTER CERTO", "Resposta bem-sucedida: ${response.code()}")
                    val profileTimeResponseData = response.body()

                    Log.d("BODY", "$profileTimeResponseData")

                    val timeResponse = profileTimeResponseData!!.teams



                    if (timeResponse != null) {
                        minhaListaDeTimes = timeResponse
                    }

                    sharedGetTime.teams = timeResponse

                    Log.d("BODY TIMES", "$timeResponse")



                    if(timeResponse != null){
                        for ( time in timeResponse){

                            val idTimeInfoTime = time.id
                            val nomeTimeInfoTime = time.nome_time
                            val jogoTimeInfoTime = time.jogo
                            val biografiaTimeInfoTime = time.biografia

                            sharedGetTimeTeams.id = time.id
                            sharedGetTimeTeams.nome_time = time.nome_time
                            sharedGetTimeTeams.jogo = time.jogo
                            sharedGetTimeTeams.biografia = time.biografia

                            val organizacaoTime = time.organizacao

                            if(organizacaoTime != null){
                                val idInfoOrganizacaoTimeById = organizacaoTime.id
                                val nomeOrganizacaoInfoOrganizacaoTimeById = organizacaoTime.nome_organizacao
                                val biografiaInfoOrganizacaoTimeById = organizacaoTime.biografia

                                sharedGetTimeTeamsOrganizacao.id = organizacaoTime.id
                                sharedGetTimeTeamsOrganizacao.nome_organizacao = organizacaoTime.nome_organizacao
                                sharedGetTimeTeamsOrganizacao.biografia = organizacaoTime.biografia


                                val donoOrganizacaoTime = organizacaoTime.dono_id

                                sharedGetTimeTeamsOrganizacao.dono_id = donoOrganizacaoTime

                                if(donoOrganizacaoTime != null){
                                    val idInfoDonoOrganizacaoTimeById = donoOrganizacaoTime.id
                                    val nomeUsuarioInfoDonoOrganizacaoTimeById = donoOrganizacaoTime.nome_usuario
                                    val nomeCompletoInfoDonoOrganizacaoTimeById = donoOrganizacaoTime.nome_completo
                                    val emailInfoDonoOrganizacaoTimeById = donoOrganizacaoTime.email
                                    val senhaInfoDonoOrganizacaoTimeById = donoOrganizacaoTime.senha
                                    val dataNascimentoInfoDonoOrganizacaoTimeById = donoOrganizacaoTime.data_nascimento
                                    val generoInfoDonoOrganizacaoTimeById = donoOrganizacaoTime.genero
                                    val nicknameInfoDonoOrganizacaoTimeById = donoOrganizacaoTime.nickname
                                    val biografiaInfoDonoOrganizacaoTimeById = donoOrganizacaoTime.biografia

                                    sharedGetTimeOrganizacaoDonoId.id = donoOrganizacaoTime.id
                                    sharedGetTimeOrganizacaoDonoId.nome_usuario = donoOrganizacaoTime.nome_usuario
                                    sharedGetTimeOrganizacaoDonoId.nome_completo = donoOrganizacaoTime.nome_completo
                                    sharedGetTimeOrganizacaoDonoId.email = donoOrganizacaoTime.email
                                    sharedGetTimeOrganizacaoDonoId.senha = donoOrganizacaoTime.senha
                                    sharedGetTimeOrganizacaoDonoId.data_nascimento = donoOrganizacaoTime.data_nascimento
                                    sharedGetTimeOrganizacaoDonoId.genero = donoOrganizacaoTime.genero
                                    sharedGetTimeOrganizacaoDonoId.nickname = donoOrganizacaoTime.nickname
                                    sharedGetTimeOrganizacaoDonoId.biografia = donoOrganizacaoTime.biografia
                                }
                            }

                            sharedGetTimeTeams.jogadores = time.jogadores

                            val jogadoresTime = time.jogadores

                            if(jogadoresTime != null){
                                for(jogadoresTime in jogadoresTime) {
                                    val idInfoJogadoresTimeById = jogadoresTime.id
                                    val nickNameInfoJogadoresTimeById = jogadoresTime.nickname
                                    val jogoInfoJogadoresTimeById = jogadoresTime.jogo
                                    val funcaoInfoJogadoresTimeById = jogadoresTime.funcao
                                    val eloInfoJogadoresTimeById = jogadoresTime.elo

                                    sharedGetTimeTeamsJogadores.id
                                    sharedGetTimeTeamsJogadores.nickname
                                    sharedGetTimeTeamsJogadores.jogo
                                    sharedGetTimeTeamsJogadores.funcao
                                    sharedGetTimeTeamsJogadores.elo

                                    val perfil_idInfoJogadoresTime = jogadoresTime.perfil_id

                                    sharedGetTimeTeamsJogadores.perfil_id = perfil_idInfoJogadoresTime

                                    if(perfil_idInfoJogadoresTime != null){
                                        val idInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.id
                                        val nomeUsuarioInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.nome_usuario
                                        val nomeCompletoInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.nome_completo
                                        val emailInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.email
                                        val senhaInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.senha
                                        val dataNascimentoInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.data_nascimento
                                        val generoInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.genero
                                        val nicknameInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.nickname
                                        val biografiaInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.biografia

                                        sharedGetTimeTeamsJogadoresPerfilId.id = perfil_idInfoJogadoresTime.id
                                        sharedGetTimeTeamsJogadoresPerfilId.nome_usuario = perfil_idInfoJogadoresTime.nome_usuario
                                        sharedGetTimeTeamsJogadoresPerfilId.nome_completo = perfil_idInfoJogadoresTime.nome_completo
                                        sharedGetTimeTeamsJogadoresPerfilId.email = perfil_idInfoJogadoresTime.email
                                        sharedGetTimeTeamsJogadoresPerfilId.senha = perfil_idInfoJogadoresTime.senha
                                        sharedGetTimeTeamsJogadoresPerfilId.data_nascimento = perfil_idInfoJogadoresTime.data_nascimento
                                        sharedGetTimeTeamsJogadoresPerfilId.genero = perfil_idInfoJogadoresTime.genero
                                        sharedGetTimeTeamsJogadoresPerfilId.nickname = perfil_idInfoJogadoresTime.nickname
                                        sharedGetTimeTeamsJogadoresPerfilId.biografia = perfil_idInfoJogadoresTime.biografia
                                    }
                                }

                            }

                            val propostasTime = time.propostas

                            if(propostasTime != null){
                                for(propostaTime in propostasTime){
                                    val idInfoPropostasTimeById = propostaTime.id
                                    val menssagemInfoPropostasTimeById = propostaTime.menssagem

                                    sharedGetTimeTeamsPropostas.id = propostaTime.id
                                    sharedGetTimeTeamsPropostas.menssagem = propostaTime.menssagem
                                }
                            }
                        }
                    }

                    val jogadorIdCompartilhado = sharedGetTimeOrganizacaoDonoId.id // Obtenha o ID do time clicado

//                                    if(jogadorIdCompartilhado != null){
//                                        val verificacao = true
//
//                                        if (verificacao == true) {
//                                            verificarIdDoJogadorTimeById( sharedGetTimeByIdTeams, jogadorIdCompartilhado)
//                                            sharedGetTimeByIdTeams.selectedJogadorIdTimeById = jogadorIdCompartilhado
//                                            Log.e("SHAREDVIEW ID"," Aqui esta o id do time que ficou salvo no SharedViewModel${sharedGetMyTeamsGeral.selectedTimeId}")
//                                            onNavigate("carregar_informacoes_do_time_by_id")
//                                        }

//                                    }



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

            override fun onFailure(call: Call<getTime>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
            }

        })


    } else{
        Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
        // Lidar com o caso em que o token é nulo ou vazio
        // Por exemplo, você pode exibir uma mensagem de erro ou redirecionar o usuário para fazer login.
        // Ou então, pode simplesmente não fazer nada.
    }

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

        Row(
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Row(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        //rememberNavController.navigate("home")
                        onNavigate("home")
                    },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "TIMES",
                    fontFamily = customFontFamilyText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight(900),
                    color = Color.White
                )
            }


            Row(
                modifier = Modifier.padding(top = 12.dp)
            ){
                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = {
                        onNavigate("criar_time")
                    },
                    modifier = Modifier
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.teams_icon),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Text(
                        text = "CRIAR TIME",
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

        }

        Spacer(modifier = Modifier.height(100.dp))

        val token = sharedViewModelTokenEId.token

        Log.d("CarregarPerfilUsuarioScreen", "Token: $token")

        val listaIdsTimes = remember { mutableListOf<Int>() }

        Column(
            modifier = Modifier
                .padding(top = 20.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp),
                content = {
                    items(minhaListaDeTimes.size){ index ->
                        val playerListTimes = minhaListaDeTimes[index]





                        val infoPerfilId = playerListTimes.id

                        Log.d("ID TIMES", "Number of ids: ${infoPerfilId}")

                        val idInfoTime = playerListTimes?.id ?: 0
                        val nomeTimeInfoTime = playerListTimes?.nome_time ?: ""
                        val biografiaTimeInfoTime = playerListTimes?.biografia ?: ""
                        val jogoTimeInfoTime = playerListTimes?.jogo ?: 0

                        val listaJogadoresTimeInfoTime = playerListTimes?.jogadores ?: null
                        val listaOrganizacaoTimeInfoTime = playerListTimes?.organizacao
                        val listaPropostasTimeInfoTime = playerListTimes?.propostas

//                        val listaJogadoresTimeInfoTime = playerListTimes?.jogadores
//                        val listaOrganizacaoTimeInfoTime = playerListTimes?.organizacao
//                        val listaPropostasTimeInfoTime = playerListTimes?.propostas

                        val nomeGerenciadorTime = playerListTimes.organizacao?.dono_id?.nickname ?: ""


                        listaIdsTimes.add(idInfoTime)

                        val imageRef = remember { mutableStateOf<StorageReference?>(null) }

                        if(idInfoTime != null && idInfoTime != 0){


                            val storage = Firebase.storage

                            if (playerListTimes != null) {

                                if (idInfoTime != null && idInfoTime != 0) {
                                    // Utilize o ID do perfil aqui
                                    imageRef.value = storage.reference.child("team/$idInfoTime/profile")
                                } else {
                                    Log.e("ID DO PERFIL INVÁLIDO", "O ID do perfil é nulo ou igual a zero")
                                }
                            } else {
                                Log.e("LISTA DE JOGADORES VAZIA", "A lista de jogadores está vazia ou nula")
                            }


                        } else{
                            Log.e("TOKEN NULO", "Token do usuario esta nulo")
                            Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
                        }

                        var imageUri by remember { mutableStateOf<Uri?>(null) }

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

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(250.dp)
                                .padding(start = 20.dp, top = 20.dp),
                        ) {
                            Button(
                                onClick = {

//                                    sharedGetTimeTeams.id = idInfoTime
//                                    sharedGetTimeTeams.nome_time = nomeTimeInfoTime
//                                    sharedGetTimeTeams.biografia = biografiaTimeInfoTime
//                                    sharedGetTimeTeams.jogo = jogoTimeInfoTime
//
//                                    sharedGetTimeTeams.jogadores = listaJogadoresTimeInfoTime
//
//                                    if (listaJogadoresTimeInfoTime != null){
//                                        for (listaJogadoresTime in listaJogadoresTimeInfoTime){
//                                            val idInfoTimeCompartilhado = listaJogadoresTime.id ?: 0
//                                            val nicknameInfoTimeCompartilhado = listaJogadoresTime.nickname ?: ""
//                                            val jogoInfoTimeCompartilhado = listaJogadoresTime.jogo ?: 0
//                                            val funcaoInfoTimeCompartilhado = listaJogadoresTime.funcao ?: 0
//                                            val eloInfoTimeCompartilhado = listaJogadoresTime.elo ?: 0
//
//                                            val idPerfilInfoTimeCompartilhado = listaJogadoresTime.perfil_id?.id ?: 0
//                                            val nomeCompletoPerfilInfoTimeCompartilhado = listaJogadoresTime.perfil_id?.nome_completo ?: ""
//                                            val nomeUsuarioPerfilInfoTimeCompartilhado = listaJogadoresTime.perfil_id?.nome_usuario ?: ""
//                                            val dataNascimentoPerfilInfoTimeCompartilhado = listaJogadoresTime.perfil_id?.data_nascimento ?: ""
//                                            val emailPerfilInfoTimeCompartilhado = listaJogadoresTime.perfil_id?.email ?: ""
//                                            val generoPerfilInfoTimeCompartilhado = listaJogadoresTime.perfil_id?.genero ?: 0
//                                            val nicknamePerfilInfoTimeCompartilhado = listaJogadoresTime.perfil_id?.nickname ?: ""
//                                            val biografiaPerfilInfoTimeCompartilhado = listaJogadoresTime.perfil_id?.biografia ?: ""
//
//
//                                            sharedGetTimeTeamsJogadores.id = idInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadores.nickname = nicknameInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadores.jogo = jogoInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadores.funcao = funcaoInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadores.elo = eloInfoTimeCompartilhado
//
//                                            sharedGetTimeTeamsJogadoresPerfilId.id = idPerfilInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadoresPerfilId.nome_completo = nomeCompletoPerfilInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadoresPerfilId.nome_usuario = nomeUsuarioPerfilInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadoresPerfilId.email =  emailPerfilInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadoresPerfilId.data_nascimento = dataNascimentoPerfilInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadoresPerfilId.genero =  generoPerfilInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadoresPerfilId.biografia = biografiaPerfilInfoTimeCompartilhado
//                                            sharedGetTimeTeamsJogadoresPerfilId.nickname = nicknamePerfilInfoTimeCompartilhado
//
//                                        }
//
//                                    }

                                    val timeId = infoPerfilId // Obtenha o ID do time clicado
                                    val verificacao = true

                                    if (verificacao == true) {
                                        if (timeId != null) {
                                            verificarIdDoTimeFilter(
                                                sharedGetTime,
                                                timeId
                                            )
                                        }
                                        sharedGetTime.selectedTimeFilterId = timeId
                                        Log.e("SHAREDVIEW ID"," Aqui esta o id do time que ficou salvo no SharedViewModel${sharedGetTime.selectedTimeFilterId}")
                                        onNavigate("carregar_informacoes_lista_times")
                                    }

                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .padding(start = 0.dp, top = 0.dp),
                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                colors = ButtonDefaults.buttonColors(RedProliseum),
                            ) {

                                //Times
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
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

                                    Spacer(modifier = Modifier.padding(start = 20.dp))

                                    Column {

                                        Text(
                                            text = "${playerListTimes.nome_time}",
                                            color = Color.White,
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight(600),
                                            fontFamily = customFontFamilyText,
                                            fontSize = 16.sp
                                        )

                                        Card(
                                            modifier = Modifier
                                                .height(75.dp)
                                                .width(75.dp),
                                            colors = CardDefaults.cardColors(RedProliseum)
                                        ) {
                                            Image(
                                                painter =
                                                if ("${playerListTimes.jogo}" == "0") painterResource(
                                                    id = R.drawable.iconlol
                                                )
                                                else if ("${playerListTimes.jogo}" == "1") painterResource(id = R.drawable.iconlol)
                                                else if ("${playerListTimes.jogo}" == "2") painterResource(id = R.drawable.iconlol)
                                                else painter,
                                                contentDescription = "",
                                                modifier = Modifier.fillMaxSize(),
                                                alignment = Alignment.Center,
                                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(5.dp))

//                            Text(
//                                text = "${playerListTimes.id}",
//                                color = Color.White,
//                                modifier = Modifier.padding(5.dp),
//                                fontWeight = FontWeight(600),
//                                fontFamily = customFontFamilyText,
//                                fontSize = 12.sp
//                            )
//
//                            Spacer(modifier = Modifier.width(5.dp))


                                        Text(
                                            text = "GERENCIADO POR ${nomeGerenciadorTime}",
                                            color = Color.White,
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight(600),
                                            fontFamily = customFontFamilyText,
                                            fontSize = 16.sp
                                        )

                                    }


                                }


                            }
                        }
                    }
                }
            )
        }
    }
}

fun verificarIdDoTimeFilter(
    sharedGetTime: SharedGetTime,
    timeId: Int // Adicione um novo parâmetro para o ID do time
) {
    val team = sharedGetTime.getTeamFilter(timeId) // Use o ID do time passado como argumento
    Log.e("ID TIME 01", "Aqui está o id do time escolhido $team")
}