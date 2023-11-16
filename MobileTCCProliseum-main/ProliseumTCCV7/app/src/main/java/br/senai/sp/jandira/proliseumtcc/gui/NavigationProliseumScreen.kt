package br.senai.sp.jandira.proliseumtcc.gui

import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.components.SharedGetTimeTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.getTime
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.AsyncImage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NavigationProliseumScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedGetTime: SharedGetTime,
    sharedGetTimeTeams: SharedGetTimeTeams,
    sharedGetTimeTeamsJogadores: SharedGetTimeTeamsJogadores,
    sharedGetTimeTeamsJogadoresPerfilId: SharedGetTimeTeamsJogadoresPerfilId,
    sharedGetTimeTeamsOrganizacao: SharedGetTimeTeamsOrganizacao,
    sharedGetTimeOrganizacaoDonoId: SharedGetTimeOrganizacaoDonoId,
    sharedGetTimeTeamsPropostas: SharedGetTimeTeamsPropostas,
    onNavigate: (String) -> Unit
) {

    val token = sharedViewModelTokenEId.token
    Log.d("PerfilUsuarioJogadorScreen", "Token: $token")

    val idUser = sharedViewModelPerfilEditar.id

    val imageRef = remember { mutableStateOf<StorageReference?>(null) }

    val nickNameUser = sharedViewModelPerfilEditar.nickname

    // Define a família da fonte personalizada
    val customFontFamily = FontFamily(
        Font(R.font.font_title) // Substitua pelo nome da fonte personalizada
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    if(idUser != null && idUser != 0){


        val storage = Firebase.storage

        if (idUser != null && idUser != 0) {
            imageRef.value = storage.reference.child("${idUser}/profile")
        }


    } else{
        Log.e("TOKEN NULO", "Token do usuario esta nulo")
        Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
    }

    var imageUri by remember { mutableStateOf<Uri?>(null) }

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

    val qualquerCoisa = ""

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(AzulEscuroProliseum, AzulEscuroProliseum),
                    startY = 700f,
                    endY = 1200f
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
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

                Spacer(modifier = Modifier.padding(15.dp))

                Button(
                    onClick = {
                        onNavigate("perfil_usuario_jogador")
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                )
                {
                    Card(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp),

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

                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "${nickNameUser}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.5.dp)
                    .background(Color.Red)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Button(
                    onClick = {
                        onNavigate("home")
                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.home_icon),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp),
                        tint = Color(255, 255, 255, 255)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = stringResource(id = R.string.home),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        onNavigate("lista_times")

                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.teams_icon),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = "LISTA DE TIMES",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),

                        )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                              onNavigate("lista_de_jogadores")
                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.jogadores_icon),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp),
                        tint = Color(255, 255, 255, 255)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = "LISTA DE JOGADORES",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                              onNavigate("propostas")
                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.propostas_icon),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp),
                        tint = Color(255, 255, 255, 255)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = "PROPOSTAS",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),

                        )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                              onNavigate("lista_de_publicacoes_jogadores")
                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.buscar_jogadores_icon),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = "BUSCAR JOGADORES",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),

                        )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                              onNavigate("lista_de_publicacoes_times")
                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp),
                        tint = Color(255, 255, 255, 255)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = "BUSCAR TIMES",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),

                        )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        onNavigate("campeonatos")
                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.trofeu_icon),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = "CAMPEONATOS",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),

                        )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        onNavigate("navigation_configuracoes_perfil")
                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.editar_perfis_icon),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp),
                        tint = Color(255, 255, 255, 255)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = "MENU DE CONFIGURAÇÕES",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.5.dp)
                        .background(Color.Red)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                                  onNavigate("premium")
                        },
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .height(48.dp)
                            .width(320.dp),
                        shape = RoundedCornerShape(73.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum)

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_proliseum),
                            contentDescription = stringResource(id = R.string.button_proximo),
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.padding(start = 20.dp))
                        Text(
                            text = "PREMIUM",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),
                        )
                    }
                }

                Button(
                    onClick = {
                        onNavigate("start")
                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),

                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sair_icon),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp),
                        tint = Color(255, 255, 255, 255)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = "SAIR",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun NavigationProliseumScreenPreview() {
}