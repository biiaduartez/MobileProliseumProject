package br.senai.sp.jandira.proliseumtcc.gui.cadastro

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.DateInputSample
import br.senai.sp.jandira.proliseumtcc.components.Genero
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelDataAndGenderCadastroUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelSimpleDataCadastroUser
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonGeneroUI
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun CadastroGeneroEDataNascimentoScreen(
    sharedViewModelDataAndGenderCadastroUser: SharedViewModelDataAndGenderCadastroUser,
    sharedViewModelSimpleDataCadastroUser: SharedViewModelSimpleDataCadastroUser,
    sharedViewModelImageUri: SharedViewModelImageUri,
    onNavigate: (String) -> Unit
) {

    val userName = sharedViewModelSimpleDataCadastroUser.userName
    val fullName = sharedViewModelSimpleDataCadastroUser.fullName
    val email = sharedViewModelSimpleDataCadastroUser.email
    val password = sharedViewModelSimpleDataCadastroUser.password

    Log.i(
        "TESTE DE SPAWM 01",
        "Aqui esta um teste de spawn aaaaaaaaa"
    )

    //FOTO DE PERFIL

    var uri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )

    var painterPhotoPerfil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .build()
    )

    //FOTO CAPA DE PERFIL

    var uriCapa by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoCapaPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriCapa = it
        }
    )

    var painterPhotoCapaPerfil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uriCapa)
            .build()
    )


    //FONTE E CONTEXTO

    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )
    val contextTipoUsuario = LocalContext.current

    var selectedDate by remember { mutableStateOf("") }

    var selectedGender by remember { mutableStateOf<Genero?>(null) }


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
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {

                Icon(
                    modifier = Modifier.clickable {
//                        rememberNavController.navigate("cadastro_perfil")
                        onNavigate("cadastro_perfil")
                                                  },
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
                text = stringResource(id = R.string.text_cadastro),
                fontFamily = customFontFamily,
                fontSize = 48.sp,
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
                    .padding(top = 180.dp)
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
                        .fillMaxSize(),
                ) {

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        DateInputSample(context = contextTipoUsuario) { date ->
                            selectedDate = date
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp),
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = stringResource(id = R.string.label_genero),
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
                            text = stringResource(id = R.string.foto_perfil),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // FOTO DE PERFIL
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Box(contentAlignment = Alignment.BottomEnd) {
                            Card(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable {
                                        singlePhotoPicker.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        )
                                        var message = "nada"
                                        Log.i(
                                            "PROLISEUM",
                                            "URI: ${uri?.path ?: message} "
                                        )
                                    },
                                shape = CircleShape
                            ) {
                                Image(
                                    painter =
                                    if (uri == null)
                                        painterResource(id = R.drawable.superpersonicon)
                                    else painterPhotoPerfil,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
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

                    //FOTO CAPA DE PERFIL
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Box(contentAlignment = Alignment.BottomEnd) {
                            Card(
                                modifier = Modifier
                                    .height(200.dp)
                                    .width(320.dp)
                                    .clickable {
                                        singlePhotoCapaPicker.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        )
                                        var message = "nada"
                                        Log.i(
                                            "PROLISEUM",
                                            "URI: ${uriCapa?.path ?: message} "
                                        )
                                    },
                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
                            ) {
                                Image(
                                    painter =
                                    if (uriCapa == null)
                                        painterResource(id = R.drawable.capa_perfil_usuario)
                                    else painterPhotoCapaPerfil,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
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

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Button(
                            onClick = {

                                if (selectedDate.isNotEmpty() && selectedGender?.toRepresentationStrinGenero() != null) {

                                    // Armazene a URI da imagem no ViewModel
                                    sharedViewModelImageUri.imageUri = uri
                                    sharedViewModelImageUri.imageCapaUri = uriCapa

                                    Log.i(
                                        "URI IMAGEM 00",
                                        "Aqui estaa URI de imagem da tela cadastroTipoUsuarioScreen ${sharedViewModelImageUri.imageUri}"
                                    )
                                    Log.i(
                                        "URI CAPA IMAGEM 00",
                                        "Aqui esta URI de imagem da capa de perfil tela cadastroTipoUsuarioScreen ${sharedViewModelImageUri.imageCapaUri}"
                                    )

                                    //val route =
                                        //"cadastro_jogador/${selectedDate}/${selectedGender}/${userName}/${fullName}/${email}/${password}"
//                                    rememberNavController.navigate(route)

                                    val simpleGeneroSelected = selectedGender?.toRepresentationStrinGenero()

                                    sharedViewModelDataAndGenderCadastroUser.selectedDate = selectedDate
                                    sharedViewModelDataAndGenderCadastroUser.selectedGender = simpleGeneroSelected

                                    onNavigate("cadastro_usuario_padrao")
                                    Log.i(
                                        "Data e Gênero inseridos com sucesso",
                                        "Valores enviados para cadastroJogador, Data: ${selectedDate}, Gênero: ${selectedGender?.toRepresentationStrinGenero()}, UserName: ${userName}, FullName: ${fullName}, Email: ${email} Password ${password}, URI da Imagem ${uri},URI da Imagem da Capa ${uriCapa}"
                                    )
                                } else {
                                    Log.i("A Data e/ou Gênero não foram inseridos", "")
                                }

                            },
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
                                text = stringResource(id = R.string.button_proximo),
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),

                                )
                        }

                        Spacer(modifier = Modifier.padding(10.dp))
                    }

                    Spacer(modifier = Modifier.padding(top = 40.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroTipoUsuarioScreenPreview() {
    ProliseumTCCTheme {

    }
}








