package br.senai.sp.jandira.proliseumtcc.gui

import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.BottomNavigationScreeen
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.AsyncImage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

@Composable
fun HomeScreen(sharedViewModelTokenEId: SharedViewTokenEId, onNavigate: (String) -> Unit) {

    // Define a família da fonte personalizada
    val customFontFamily = FontFamily(
        Font(R.font.font_title) // Substitua pelo nome da fonte personalizada
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var selectedNavigation by remember { mutableStateOf<Int?>(null) }

    val idUserPerfilForImage = sharedViewModelTokenEId.idUsuario

    val storage = Firebase.storage

    val imageRef = remember { mutableStateOf<StorageReference?>(null) }

    if (idUserPerfilForImage != null && idUserPerfilForImage != 0) {
        imageRef.value = storage.reference.child("${idUserPerfilForImage}/profile")
    }

    Log.d("ID PERFIL USUARIO", "ID DO PERFIL DO USUARIO ${idUserPerfilForImage}")

    //    FIREBASE
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

    // FIREBASE
    Log.e("URL IMAGEM DO USUARIO 03", "Id do URL da imagem do usuario ${idUserPerfilForImage}")
    Log.e("URI IMAGEM DO USUARIO 03", "URI da imagem do usuario ${imageUri}")

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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, top = 10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            //rememberNavController.navigate("navigation_proliseum")
                            onNavigate("navigation_proliseum")
                                   },
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color(255, 255, 255, 255)
                )

                Spacer(modifier = Modifier.padding(150.dp))

                Box(contentAlignment = Alignment.BottomEnd) {
                    Card(
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp),
                        shape = CircleShape
                    ) {
                    if (idUserPerfilForImage != null && idUserPerfilForImage != 0) {

                    // Exiba a imagem no formato circular
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                //rememberNavController.navigate("perfil_usuario_jogador")
                                onNavigate("perfil_usuario_jogador")
                            }
                            .background(Color.Gray, CircleShape) // Define um shape circular
                    ) {
                        AsyncImage(
                            model = imageUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                } else {
                    // Caso a URI não esteja definida, você pode mostrar uma mensagem ou um indicador de carregamento
                    Text("Carregando imagem...")
                }
                    }
                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom)
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(
                            RedProliseum,
                            shape = RoundedCornerShape(70.dp, 70.dp, 70.dp, 70.dp)
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly) {}

                Spacer(modifier = Modifier.height(5.dp))

                Row {
//                    BottomNavigationScreeen(rememberNavController) { navigation ->
//                        selectedNavigation = navigation
//                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeAposLoginScreenPreview() {
    ProliseumTCCTheme{

    }
}