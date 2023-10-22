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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonFuncaoLolUI
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonJogoUI
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriarTime(rememberNavController: NavController) {

    val customFontFamilyTitle = FontFamily(Font(R.font.font_title))
    val customFontFamilyText = FontFamily(Font(R.font.font_poppins))

    var criarFotoTimeUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var launcherCriarFotoTime = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        criarFotoTimeUri = it
    }

    var painterCriarFotoTime = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(criarFotoTimeUri)
            .build()
    )

    var selectedGame by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        AzulEscuroProliseum,
                        AzulEscuroProliseum
                    )
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        // Cabeçalho
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            // Botão de retorno
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    modifier = Modifier.clickable { rememberNavController.navigate("home") },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color.White
                )
            }
        }

        // Título e imagem de logotipo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logocadastro),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.label_criar_time),
                fontFamily = customFontFamilyTitle,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        // Conteúdo do formulário
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 250.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                BlackTransparentProliseum,
                                BlackTransparentProliseum
                            )
                        ),
                        shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
                    )
                    .padding(40.dp)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_nome_time),
                            fontFamily = customFontFamilyText,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                        Text(
                            text = "*",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = RedProliseum
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    var nomeOrganizacaoState by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = nomeOrganizacaoState,
                        onValueChange = { newNomeOrganizacao ->
                            nomeOrganizacaoState = newNomeOrganizacao
                        },
                        modifier = Modifier
                            .width(370.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_nome_time),
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(600),
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(255, 255, 255, 255),
                            focusedBorderColor = Color(255, 255, 255, 255),
                            cursorColor = Color.White
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_foto_time),
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(contentAlignment = Alignment.BottomEnd) {
                            Card(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable {
                                        launcherCriarFotoTime.launch("image/*")
                                        var message = "nada"
                                        Log.i(
                                            "PROLISEUM",
                                            "URI: ${criarFotoTimeUri?.path ?: message} "
                                        )
                                    },
                                shape = CircleShape
                            ) {
                                Image(
                                    modifier = Modifier
                                        .background(Color.White),
                                    painter = if (criarFotoTimeUri == null) painterResource(id = R.drawable.superpersonicon) else painterCriarFotoTime,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.add_a_photo),
                                contentDescription = "",
                                tint = RedProliseum
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_game),
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                        Text(
                            text = "*",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = RedProliseum
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    ToggleButtonJogoUI { jogo ->
                        selectedGame = jogo.toString()
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_rede_social),
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    var editarRedeSocialPerfilJogador1State by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = editarRedeSocialPerfilJogador1State,
                        onValueChange = { newEditarRedeSocialPerfilJogador1 ->
                            editarRedeSocialPerfilJogador1State = newEditarRedeSocialPerfilJogador1
                        },
                        modifier = Modifier
                            .width(370.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_rede_social),
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(600),
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(255, 255, 255, 255),
                            focusedBorderColor = Color(255, 255, 255, 255),
                            cursorColor = Color.White
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    var editarRedeSocialPerfilJogador2State by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = editarRedeSocialPerfilJogador2State,
                        onValueChange = { newEditarRedeSocialPerfilJogador2 ->
                            editarRedeSocialPerfilJogador2State = newEditarRedeSocialPerfilJogador2
                        },
                        modifier = Modifier
                            .width(370.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_rede_social),
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(600),
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(255, 255, 255, 255),
                            focusedBorderColor = Color(255, 255, 255, 255),
                            cursorColor = Color.White
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    var editarRedeSocialPerfilJogador3State by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = editarRedeSocialPerfilJogador3State,
                        onValueChange = { newEditarRedeSocialPerfilJogador3 ->
                            editarRedeSocialPerfilJogador3State = newEditarRedeSocialPerfilJogador3
                        },
                        modifier = Modifier
                            .width(370.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_rede_social),
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(600),
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(255, 255, 255, 255),
                            focusedBorderColor = Color(255, 255, 255, 255),
                            cursorColor = Color.White
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    var editarRedeSocialPerfilJogador4State by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = editarRedeSocialPerfilJogador4State,
                        onValueChange = { newEditarRedeSocialPerfilJogador4 ->
                            editarRedeSocialPerfilJogador4State = newEditarRedeSocialPerfilJogador4
                        },
                        modifier = Modifier
                            .width(370.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_rede_social),
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(600),
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(255, 255, 255, 255),
                            focusedBorderColor = Color(255, 255, 255, 255),
                            cursorColor = Color.White
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_biografia),
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    var fullBioJogadorState by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = fullBioJogadorState,
                        onValueChange = { newFullBioJogador ->
                            fullBioJogadorState = newFullBioJogador
                        },
                        modifier = Modifier
                            .height(220.dp)
                            .width(370.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_biografia),
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(600),
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(255, 255, 255, 255),
                            focusedBorderColor = Color(255, 255, 255, 255),
                            cursorColor = Color.White
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )

                    Button(
                        onClick = { rememberNavController.navigate("editar_perfil_time") },
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .width(300.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(73.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum)

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.logocadastro),
                            contentDescription = stringResource(id = R.string.button_proximo),
                            tint = Color(255, 255, 255, 255)
                        )
                        Text(
                            text = stringResource(id = R.string.button_salvar),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CriarTimeScreenPreview() {
    ProliseumTCCTheme {

    }
}