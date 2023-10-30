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
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.mobileproliseum.MainActivity
//import com.example.mobileproliseum.R
//import com.example.mobileproliseum.components.TimePickerComponent
//import com.example.mobileproliseum.ui.theme.AzulEscuroProliseum
//import com.example.mobileproliseum.ui.theme.BlackTransparentProliseum
//import com.example.mobileproliseum.ui.theme.RedProliseum
//
//
//@Composable
//fun PubliTimeScreen() {
//    val customFontFamily = FontFamily(
//        Font(R.font.font_title)
//    )
//    val customFontFamilyText = FontFamily(
//        Font(R.font.font_poppins)
//    )
//
//    val context = LocalContext.current
//
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                brush = Brush.horizontalGradient(
//                    listOf(
//                        AzulEscuroProliseum, AzulEscuroProliseum
//                    )
//                )
//            )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(15.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.Top
//        ) {
//
//            Icon(
//                modifier = Modifier.clickable {
//                    val intent = Intent(context, MainActivity::class.java)
//                    context.startActivity(intent)
//                },
//                painter = painterResource(id = R.drawable.arrow_back_32),
//                contentDescription = stringResource(id = R.string.button_sair),
//                tint = Color.White
//            )
//            LazyColumn(modifier = Modifier.fillMaxSize()) {
//                item {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(top = 80.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .background(
//                                    Brush.horizontalGradient(
//                                        listOf(
//                                            BlackTransparentProliseum,
//                                            BlackTransparentProliseum
//                                        )
//                                    )
//                                )
//                                .width(280.dp)
//                        ) {
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(16.dp),
//                                horizontalAlignment = Alignment.CenterHorizontally
//                            ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.superpersonicon),
//                                    contentDescription = " ",
//                                    alignment = Alignment.Center
//                                )
//
//                                Text(
//                                    text = stringResource(id = R.string.label_nome_jogador),
//                                    textAlign = TextAlign.Center,
//                                    color = Color.White,
//                                    fontFamily = customFontFamilyText,
//                                    fontWeight = FontWeight(900),
//                                    fontSize = 22.sp,
//                                    modifier = Modifier.padding(top = 10.dp)
//                                )
//
//                                Row(
//                                    modifier = Modifier
//                                        .fillMaxSize()
//                                        .padding(16.dp),
//
//                                    ) {
//                                    Column(
//                                        verticalArrangement = Arrangement.Center,
//                                        horizontalAlignment = Alignment.CenterHorizontally
//                                    ) {
//                                        Text(
//                                            text = "ELO",
//                                            textAlign = TextAlign.Center,
//                                            color = Color.White,
//                                            fontFamily = customFontFamilyText,
//                                            fontWeight = FontWeight(600),
//                                            modifier = Modifier.padding(10.dp)
//                                        )
//
//                                        Image(
//                                            painter = painterResource(id = R.drawable.elo),
//                                            contentDescription = ""
//                                        )
//
//                                        Text(
//                                            text = "DIAMOND V",
//                                            textAlign = TextAlign.Center,
//                                            color = Color.White,
//                                            fontFamily = customFontFamilyText,
//                                            fontWeight = FontWeight(600),
//                                            modifier = Modifier.padding(top = 10.dp)
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.width(25.dp))
//
//                                    Column(
//                                        verticalArrangement = Arrangement.Center,
//                                        horizontalAlignment = Alignment.CenterHorizontally
//                                    ) {
//                                        Text(
//                                            text = "FUNÇÃO",
//                                            textAlign = TextAlign.Center,
//                                            color = Color.White,
//                                            fontFamily = customFontFamilyText,
//                                            fontWeight = FontWeight(600),
//                                            modifier = Modifier.padding(10.dp)
//                                        )
//
//                                        Image(
//                                            painter = painterResource(id = R.drawable.mid),
//                                            contentDescription = "",
//                                            modifier = Modifier.height(45.dp)
//                                        )
//
//                                        Text(
//                                            text = "MID",
//                                            textAlign = TextAlign.Center,
//                                            color = Color.White,
//                                            fontFamily = customFontFamilyText,
//                                            fontWeight = FontWeight(600),
//                                            modifier = Modifier.padding(top = 10.dp)
//                                        )
//                                    }
//                                }
//
//                                Text(
//                                    text = "HORÁRIO",
//                                    textAlign = TextAlign.Center,
//                                    color = Color.White,
//                                    fontFamily = customFontFamilyText,
//                                    fontWeight = FontWeight(600),
//                                    modifier = Modifier.padding(top = 10.dp)
//                                )
//
//                                // SUBSTITUIR OS TEXT PELO COMPONENTE DE TIME ( IMPORTAR DO GIT DO GABRIEL)
//
//                                TimePickerComponent()
//
//
//
//                                Button(
//                                    onClick = { /*TODO*/ },
//                                    colors = ButtonDefaults.buttonColors(Color.Transparent)
//                                ) {
//                                    Text(text = "Mais Informações")
//                                }
//
//
//                            }
//
//                        }
//
//                        Row {
//
//                            Button(
//                                onClick = { /*TODO*/ },
//                                modifier = Modifier
//                                    .padding(top = 20.dp),
//                                shape = RoundedCornerShape(73.dp),
//                                colors = ButtonDefaults.buttonColors(RedProliseum)
//
//                            ) {
//                                Text(
//                                    text = "FECHAR INSCRIÇÃO",
//                                    fontSize = 13.sp,
//                                    textAlign = TextAlign.Center,
//                                    color = Color.White,
//                                    fontFamily = customFontFamilyText,
//                                    fontWeight = FontWeight(900),
//
//                                    )
//                            }
//                            Spacer(modifier = Modifier.width(25.dp))
//                            Button(
//                                onClick = { /*TODO*/ },
//                                modifier = Modifier
//                                    .padding(top = 20.dp),
//                                shape = RoundedCornerShape(73.dp),
//                                colors = ButtonDefaults.buttonColors(RedProliseum)
//
//                            ) {
//                                Text(
//                                    text = "DESFAZER PUBLICAÇÃO",
//                                    fontSize = 13.sp,
//                                    textAlign = TextAlign.Center,
//                                    color = Color.White,
//                                    fontFamily = customFontFamilyText,
//                                    fontWeight = FontWeight(900),
//
//                                    )
//                            }
//
//                        }
//                        Button(
//                            onClick = { /*TODO*/ },
//                            modifier = Modifier
//                                .padding(top = 10.dp),
//                            shape = RoundedCornerShape(73.dp),
//                            colors = ButtonDefaults.buttonColors(RedProliseum)
//
//                        ) {
//                            Text(
//                                text = "EDITAR",
//                                fontSize = 13.sp,
//                                textAlign = TextAlign.Center,
//                                color = Color.White,
//                                fontFamily = customFontFamilyText,
//                                fontWeight = FontWeight(900),
//
//                                )
//                        }
//
//                        Spacer(modifier = Modifier.height(30.dp))
//
//                        Row(
//                            horizontalArrangement = Arrangement.Center,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//
//                            Text(
//                                text = "INSCRITOS",
//                                color = Color.White,
//                                fontSize = 25.sp,
//                                fontWeight = FontWeight(900),
//                                fontFamily = customFontFamilyText,
//                                textAlign = TextAlign.Center
//                            )
//
//                            Spacer(modifier = Modifier.width(15.dp))
//
//                            Button(
//                                onClick = { /*TODO*/ },
//                                modifier = Modifier
//                                    .padding(top = 5.dp),
//                                shape = RoundedCornerShape(73.dp),
//                                colors = ButtonDefaults.buttonColors(RedProliseum)
//
//                            ) {
//                                Text(
//                                    text = "GERENCIAR INSCRITOS",
//                                    fontSize = 11.sp,
//                                    textAlign = TextAlign.Center,
//                                    color = Color.White,
//                                    fontFamily = customFontFamilyText,
//                                    fontWeight = FontWeight(900),
//
//                                    )
//                            }
//
//                        }
//
//                        Spacer(modifier = Modifier.height(30.dp))
//                        Box(
//                            modifier = Modifier
//                                .background(
//                                    Brush.horizontalGradient(
//                                        listOf(
//                                            BlackTransparentProliseum,
//                                            BlackTransparentProliseum
//                                        )
//                                    )
//                                )
//                                .width(100.dp)
//                        ) {
//
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(16.dp),
//                                horizontalAlignment = Alignment.CenterHorizontally
//                            ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.superpersonicon),
//                                    contentDescription = " ",
//                                    alignment = Alignment.Center
//                                )
//
//                                Text(
//                                    text = stringResource(id = R.string.label_nome_jogador),
//                                    textAlign = TextAlign.Center,
//                                    color = Color.White,
//                                    fontFamily = customFontFamilyText,
//                                    fontWeight = FontWeight(900),
//                                    fontSize = 12.sp,
//                                    modifier = Modifier.padding(top = 10.dp)
//                                )
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
//
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PubliTimePreview() {
//    PubliTimeScreen()
//}