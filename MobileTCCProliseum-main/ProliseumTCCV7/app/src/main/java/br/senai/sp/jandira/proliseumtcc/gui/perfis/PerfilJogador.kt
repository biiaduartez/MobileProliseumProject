package br.senai.sp.jandira.proliseumtcc.gui.perfis

import android.content.Intent
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import br.senai.sp.jandira.proliseumtcc.MainActivity
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilJogadorScreen(rememberNavController: NavController) {
    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    val context = LocalContext.current

    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val bioText =
        "Esta é a minha biografia estática. Eu sou um exemplo de texto que não pode ser editado."

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
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
            )
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
                    text = "Editar",
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
                        .size(150.dp)
                        .clickable {
                            launcher.launch("image/*")
                            var message = "nada"
                            Log.i(
                                "PROLISEUM",
                                "URI: ${photoUri?.path ?: message} "
                            )
                        },
                    shape = CircleShape
                ) {
                    Image(
                        modifier = Modifier
                            .background(Color.White),
                        painter = if (photoUri == null) painterResource(id = R.drawable.superpersonicon) else painter,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
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
                        text = stringResource(id = R.string.label_nome_jogador),
                        fontSize = 28.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = stringResource(id = R.string.label_nome_jogador),
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
                                painter = painterResource(id = R.drawable.iconlol),
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
                                painter = painterResource(id = R.drawable.iconadc),
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

                        Spacer(modifier = Modifier.width(15.dp))

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
                                text = bioText,
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
                                text = "ATUALMENTE",
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
                                text = "FA",
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(400),
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "ELO",
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                            Image(
                                painter = painterResource(id = R.drawable.elo),
                                contentDescription = ""
                            )
                            Text(
                                text = "DIAMOND V",
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(400),
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "DESTAQUE",
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                            Image(
                                painter = painterResource(id = R.drawable.trofeu),
                                contentDescription = ""
                            )
                            Text(
                                text = "1º LUGAR COPA PORO",
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(400),
                                textAlign = TextAlign.Center
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

@Preview(showBackground = true)
@Composable
fun PerfilJogadorScreenPreview() {
    ProliseumTCCTheme {

    }
}