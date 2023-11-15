package br.senai.sp.jandira.proliseumtcc.gui

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.components.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeById
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeByIdTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeTeamsPropostas
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
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilJogadorOutro
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilOrganizadorOutro
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfilOutro
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.gui.perfis.verificarIdDoTime
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadoresList
import br.senai.sp.jandira.proliseumtcc.model.getTime
import br.senai.sp.jandira.proliseumtcc.model.getTimeById
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeams
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.GetTimeById
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
fun CarregarInformacoesDoTime   (
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

                        val timeResponse = profileTimeResponseData?.teams



                        if (timeResponse != null) {
                            minhaListaDeTimes = timeResponse
                        }

                        sharedGetTime.teams = timeResponse ?: emptyList()

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

                                val jogadoresTime = time.jogadores

                                sharedGetTimeTeams.jogadores = jogadoresTime


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
                        val jogoTimeInfoTime = playerListTimes?.jogo ?: ""

//                        val listaJogadoresTimeInfoTime = playerListTimes?.jogadores ?: ""
//                        val listaOrganizacaoTimeInfoTime = playerListTimes?.organizacao ?: 0
//                        val listaPropostasTimeInfoTime = playerListTimes?.propostas ?: ""

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
//                                                sharedViewModelGetListaJogadoresInfoPerfil.idInfoPerfilJogador = idInfoPerfilJogador
//                                                sharedViewModelGetListaJogadoresInfoPerfil.nomeUsuarioInfoPerfilJogador = nomeUsuarioInfoPerfilJogador
//                                                sharedViewModelGetListaJogadoresInfoPerfil.nomeCompletoInfoPerfilJogador = nomeCompletoInfoPerfilJogador
//                                                sharedViewModelGetListaJogadoresInfoPerfil.emailInfoPerfilJogador = emailInfoPerfilJogador
//                                                sharedViewModelGetListaJogadoresInfoPerfil.dataNascimentoInfoPerfilJogador = dataNascimentoInfoPerfilJogador
//                                                sharedViewModelGetListaJogadoresInfoPerfil.generoInfoPerfilJogador = generoInfoPerfilJogador
//                                                sharedViewModelGetListaJogadoresInfoPerfil.nickNameInfoPerfilJogador = nickNameInfoPerfilJogador
//                                                sharedViewModelGetListaJogadoresInfoPerfil.biografiaInfoPerfilJogador = biografiaInfoPerfilJogador
//
//                                                onNavigate("carregar_informacoes_perfil_outro_jogador")

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
                                                id = R.drawable.iconcsgo
                                            )
                                            else if ("${playerListTimes.jogo}" == "1") painterResource(id = R.drawable.iconlol)
                                            else if ("${playerListTimes.jogo}" == "2") painterResource(id = R.drawable.iconvalorant)
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


