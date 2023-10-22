package br.senai.sp.jandira.proliseumtcc

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProliseumTCCTheme {
                MainScreen()
            }
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        val  navControler = rememberNavController()

        NavHost(navController = navControler, startDestination = Screen.OtherMainScreen.route) {
            composable(route = Screen.OtherMainScreen.route) {
                OtherMainScreen(navController = navControler)
            }
            composable(
                route = Screen.DetailScreen.route + "/{name}/{age}/{uriCapa}",
                arguments = listOf(
                    navArgument("name", ) {
                        type = NavType.StringType
                        defaultValue = "Prevelaz"
                        nullable = true
                    },
                    navArgument("age", ) {
                        type = NavType.StringType
                        defaultValue = "Prevelaz"
                        nullable = true
                    },
                    navArgument("uriCapa", ) {
                        type = NavType.StringType
                        defaultValue = "Prevelaz"
                        nullable = true
                    }

                )
            ) {entry ->
                DetailScreen(
                    name = entry.arguments?.getString("name"),
                    age = entry.arguments?.getString("age"),
                    uriCapa = entry.arguments?.getString("uriCapa")
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherMainScreen(navController: NavController) {

    //FOTO CAPA DE PERFIL

    var uriCapa by remember{
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

    var text by remember {
        mutableStateOf("")
    }

    var age by remember {
        mutableStateOf("")
    }

//    var idade by remember {
//        mutableStateOf<Int?>(null)
//    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        TextField(value = text, onValueChange = {
            text = it
        },
            modifier = Modifier.fillMaxWidth()
            )

        OutlinedTextField(
            value = age,
            onValueChange = { newUserNameJogador -> age = newUserNameJogador },
            modifier = Modifier

                .width(350.dp),
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {
                Text(
                    text = "idade",
                    color = Color.Blue,
                    fontWeight = FontWeight(600),
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color(0, 155, 55, 255),
                focusedBorderColor = Color(25, 255, 255, 255),
                cursorColor = Color.Red
            ),
            textStyle = TextStyle(color = Color.Blue)
        )

//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//
//        }

        Box( contentAlignment = Alignment.BottomEnd){
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
            ){
                Image(
                    painter = if(uriCapa == null) painterResource (id = R.drawable.capa_perfil_usuario) else painterPhotoCapaPerfil,
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


        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                      navController.navigate(Screen.DetailScreen.withArgs(text, age, uriCapa.toString()))
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "To DetailScreen")
        }
    }
}

@Composable
fun DetailScreen(name: String?, age: String?, uriCapa: String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Ola, $name")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Idade, $age")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Capa, $uriCapa")
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

