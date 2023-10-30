package com.example.mobileproliseum.publicacao.screen

//import android.content.Intent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import com.example.mobileproliseum.MainActivity
//import com.example.mobileproliseum.R
//import com.example.mobileproliseum.components.CustomButtonWithText
//import com.example.mobileproliseum.components.TimePickerComponent
//import com.example.mobileproliseum.ui.theme.AzulEscuroProliseum
//import com.example.mobileproliseum.ui.theme.BlackTransparentProliseum
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EditarPubliJogadorScreen(){
//    val customFontFamilyTitle = FontFamily(Font(R.font.font_title))
//    val customFontFamilyText = FontFamily(Font(R.font.font_poppins))
//    val context = LocalContext.current
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                brush = Brush.horizontalGradient(
//                    listOf(
//                        AzulEscuroProliseum,
//                        AzulEscuroProliseum
//                    )
//                )
//            )
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Top
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(15.dp),
//                horizontalArrangement = Arrangement.Start,
//                verticalAlignment = Alignment.Top
//            ) {
//                // Botão de retorno
//                Icon(
//                    modifier = Modifier.clickable {
//                        val intent = Intent(context, MainActivity::class.java)
//                        context.startActivity(intent)
//                    },
//                    painter = painterResource(id = R.drawable.arrow_back_32),
//                    contentDescription = stringResource(id = R.string.button_sair),
//                    tint = Color.White
//                )
//            }
//        }
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 50.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Título e imagem de logotipo
//            Image(
//                painter = painterResource(id = R.drawable.logocadastro),
//                contentDescription = ""
//            )
//        }
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 150.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Box(
//                modifier = Modifier
//                    .background(
//                        brush = Brush.horizontalGradient(
//                            listOf(
//                                BlackTransparentProliseum,
//                                BlackTransparentProliseum
//                            )
//                        ),
//                        shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
//                    )
//                    .padding(40.dp)
//                    .verticalScroll(rememberScrollState())
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Top,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    var fullNameState by remember { mutableStateOf("") }
//                    OutlinedTextField(
//                        value = fullNameState,
//                        onValueChange = { newFullName -> fullNameState = newFullName },
//                        modifier = Modifier
//                            .width(320.dp)
//                            .padding(bottom = 16.dp),
//                        shape = RoundedCornerShape(16.dp),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                        label = {
//                            Text(
//                                text = "Título",
//                                color = Color.White,
//                                fontFamily = customFontFamilyText,
//                                fontWeight = FontWeight(600),
//                            )
//                        },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            unfocusedBorderColor = Color(255, 255, 255, 255),
//                            focusedBorderColor = Color(255, 255, 255, 255),
//                            cursorColor = Color.White,
//                            disabledPlaceholderColor = Color.White,
//                        ),
//                        textStyle = TextStyle(color = Color.White)
//                    )
//                    Spacer(modifier = Modifier.height(25.dp))
//
//                    TimePickerComponent()
//
//
//                    Spacer(modifier = Modifier.height(25.dp))
//
//                    Column(
//                        modifier = Modifier.fillMaxSize(),
//                        verticalArrangement = Arrangement.Top,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        var fullDescriptionState by remember { mutableStateOf("") }
//                        OutlinedTextField(
//                            value = fullDescriptionState,
//                            onValueChange = { newFullDescription ->
//                                fullDescriptionState = newFullDescription
//                            },
//                            modifier = Modifier
//                                .width(320.dp)
//                                .padding(bottom = 16.dp),
//                            shape = RoundedCornerShape(16.dp),
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                            label = {
//                                Text(
//                                    text = "Descrição da vaga",
//                                    color = Color.White,
//                                    fontFamily = customFontFamilyText,
//                                    fontWeight = FontWeight(600),
//                                )
//                            },
//                            colors = TextFieldDefaults.outlinedTextFieldColors(
//                                unfocusedBorderColor = Color(255, 255, 255, 255),
//                                focusedBorderColor = Color(255, 255, 255, 255),
//                                cursorColor = Color.White,
//                                disabledPlaceholderColor = Color.White,
//                            ),
//                            textStyle = TextStyle(color = Color.White)
//                        )
//                        Spacer(modifier = Modifier.height(25.dp))
//                    }
//                    CustomButtonWithText(
//                        text = "EDITAR PUBLICAÇÃO",
//                        size = "2rem"
//                    ){}
//                }
//
//            }
//        }
//    }
//}

