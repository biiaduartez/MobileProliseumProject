package br.senai.sp.jandira.proliseumtcc.gui.editarPerfil


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
import br.senai.sp.jandira.proliseumtcc.components.DateInputSample
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonGeneroUI
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilOrganizadorPart1(rememberNavController: NavController) {

    //EDITAR FOTO DE PERFIL JOGADOR

    var editarFotoPerfilJogadorUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var launcherEditarFotoPerfilJogador = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){
        editarFotoPerfilJogadorUri = it
    }

    var painterFotoPerfilJogador = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(editarFotoPerfilJogadorUri)
            .build()
    )

    //EDITAR FOTO DE CAPA DE PERFIL ORGANIZADOR

    var editarFotoCapaPerfilOrganizadorUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var launcherEditarFotoCapaPerfilOrganizador = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){
        editarFotoCapaPerfilOrganizadorUri = it
    }

    var painterEditarFotoCapaPerfilOrganizador = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(editarFotoCapaPerfilOrganizadorUri)
            .build()
    )


    val customFontFamily = FontFamily(
        Font(R.font.font_title) // Substitua pelo nome da fonte personalizada
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    val context = LocalContext.current

    var selectedDate by remember { mutableStateOf("")}

    var selectedGender by remember { mutableStateOf<Int?>(null) }


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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    modifier = Modifier.clickable { rememberNavController.navigate("perfil_organizacao") },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color(255, 255, 255, 255)
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(
                painter = painterResource(id = R.drawable.logocadastro),
                contentDescription = ""
            )
            Text(
                text = "EDITAR ORGANIZADOR",
                fontFamily = customFontFamily,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = Color.White

            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 190.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                BlackTransparentProliseum,
                                BlackTransparentProliseum
                            )
                        ),
                        shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
                    )


            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var userNameState by remember { mutableStateOf("") }

                        OutlinedTextField(
                            value = userNameState,
                            onValueChange = { newUserName -> userNameState = newUserName },
                            modifier = Modifier

                                .width(350.dp),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            label = {
                                Text(
                                    text = stringResource(id = R.string.label_user),
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
                        Spacer(modifier = Modifier.height(10.dp))

                        var fullNameState by remember { mutableStateOf("") }

                        OutlinedTextField(
                            value = fullNameState,
                            onValueChange = { newFullName -> fullNameState = newFullName },
                            modifier = Modifier

                                .width(350.dp),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            label = {
                                Text(
                                    text = stringResource(id = R.string.label_nome),
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
                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            DateInputSample(context = context) { date ->
                                selectedDate = date
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "GÊNERO:",
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),

                            )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ToggleButtonGeneroUI { gender ->
                            selectedGender = gender
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "FOTO PERFIL:",
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),
                        )
                    }


                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(contentAlignment = Alignment.BottomEnd){
                            Card(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable {
                                        launcherEditarFotoPerfilJogador.launch("image/*")
                                        var message = "nada"
                                        Log.i(
                                            "PROLISEUM",
                                            "URI: ${editarFotoPerfilJogadorUri?.path ?: message} "
                                        )
                                    },
                                shape = CircleShape
                            ){
                                Image(
                                    modifier = Modifier
                                        .background(Color.White),
                                    painter = if(editarFotoPerfilJogadorUri == null) painterResource (id = R.drawable.superpersonicon) else painterFotoPerfilJogador,
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

                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "CAPA PERFIL:",
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),
                        )
                    }


                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(contentAlignment = Alignment.BottomEnd){
                            Card(
                                modifier = Modifier
                                    .height(200.dp)
                                    .width(370.dp)
                                    .clickable {
                                        launcherEditarFotoCapaPerfilOrganizador.launch("image/*")
                                        var message = "nada"
                                        Log.i(
                                            "PROLISEUM",
                                            "URI: ${editarFotoCapaPerfilOrganizadorUri?.path ?: message} "
                                        )
                                    },
                                shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                            ){
                                Image(
                                    modifier = Modifier
                                        .background(Color.White),
                                    painter = if(editarFotoCapaPerfilOrganizadorUri == null) painterResource (id = R.drawable.capa_perfil_usuario) else painterEditarFotoCapaPerfilOrganizador,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.add_circle),
                                contentDescription = "",
                                tint = RedProliseum
                            )

                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Button(
                            onClick = { rememberNavController.navigate("home") },
                            modifier = Modifier
                                .width(180.dp)
                                .height(38.dp),
                            shape = RoundedCornerShape(73.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum)

                        ) {
                            Text(
                                text = "SALVAR ALTERAÇÕES",
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),

                                )
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Button(
                            onClick = { rememberNavController.navigate("editar_perfil_organizador_part_2") },
                            modifier = Modifier
                                .width(180.dp)
                                .height(38.dp),
                            shape = RoundedCornerShape(73.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum)

                        ) {
                            Text(
                                text = "PRÓXIMO",
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 30.dp))

                }


            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditarPerfilOrganizadorPart1ScreenPreview() {
    ProliseumTCCTheme{

    }
}