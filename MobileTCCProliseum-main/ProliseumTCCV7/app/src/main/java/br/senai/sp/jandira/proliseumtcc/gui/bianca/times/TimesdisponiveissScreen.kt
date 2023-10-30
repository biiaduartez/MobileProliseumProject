package br.senai.sp.jandira.proliseumtcc.gui.bianca.times

//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.mobileproliseum.R
//import com.example.mobileproliseum.components.TimePickerComponent
//import com.example.mobileproliseum.publicacao.screen.PubliJogadorScreen
//import com.example.mobileproliseum.ui.theme.AzulEscuroProliseum
//import com.example.mobileproliseum.ui.theme.BlackTransparentProliseum
//
//@Composable
//fun TimesDisponiveisScreen() {
//    val customFontFamily = FontFamily(
//        Font(R.font.font_title)
//    )
//    val customFontFamilyText = FontFamily(
//        Font(R.font.font_poppins)
//    )
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
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 50.dp)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.fundotelas),
//                contentDescription = "",
//            )
//        }
//
//        Text(
//            text = "VAGAS DISPONIVEIS",
//            modifier = Modifier
//                .padding(start = 20.dp, top = 65.dp)
//                .width(180.dp),
//            color = Color.White,
//            fontSize = 24.sp,
//            fontFamily = customFontFamilyText,
//            fontWeight = FontWeight(900)
//        )
//
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 180.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            item {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .align(Alignment.Center),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .background(
//                                Brush.horizontalGradient(
//                                    listOf(
//                                        BlackTransparentProliseum,
//                                        BlackTransparentProliseum
//                                    )
//                                )
//                            )
//                            .width(280.dp)
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(16.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.simbolo),
//                                contentDescription = " ",
//                                alignment = Alignment.Center
//                            )
//
//                            Text(
//                                text = "BOOM",
//                                textAlign = TextAlign.Center,
//                                color = Color.White,
//                                fontFamily = customFontFamilyText,
//                                fontWeight = FontWeight(900),
//                                fontSize = 22.sp,
//                                modifier = Modifier.padding(top = 10.dp)
//                            )
//
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(16.dp),
//
//                                ) {
//                                Column(
//                                    verticalArrangement = Arrangement.Center,
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Text(
//                                        text = "ELO",
//                                        textAlign = TextAlign.Center,
//                                        color = Color.White,
//                                        fontFamily = customFontFamilyText,
//                                        fontWeight = FontWeight(600),
//                                        modifier = Modifier.padding(10.dp)
//                                    )
//
//                                    Image(
//                                        painter = painterResource(id = R.drawable.elo),
//                                        contentDescription = ""
//                                    )
//
//                                    Text(
//                                        text = "DIAMOND V",
//                                        textAlign = TextAlign.Center,
//                                        color = Color.White,
//                                        fontFamily = customFontFamilyText,
//                                        fontWeight = FontWeight(600),
//                                        modifier = Modifier.padding(top = 10.dp)
//                                    )
//                                }
//
//                                Spacer(modifier = Modifier.width(25.dp))
//
//                                Column(
//                                    verticalArrangement = Arrangement.Center,
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Text(
//                                        text = "FUNÇÃO",
//                                        textAlign = TextAlign.Center,
//                                        color = Color.White,
//                                        fontFamily = customFontFamilyText,
//                                        fontWeight = FontWeight(600),
//                                        modifier = Modifier.padding(10.dp)
//                                    )
//
//                                    Image(
//                                        painter = painterResource(id = R.drawable.mid),
//                                        contentDescription = "",
//                                        modifier = Modifier.height(45.dp)
//                                    )
//
//                                    Text(
//                                        text = "MID",
//                                        textAlign = TextAlign.Center,
//                                        color = Color.White,
//                                        fontFamily = customFontFamilyText,
//                                        fontWeight = FontWeight(600),
//                                        modifier = Modifier.padding(top = 10.dp)
//                                    )
//                                }
//                            }
//
//
//
//                            TimePickerComponent()
//
//
//
//                            Button(
//                                onClick = { /*TODO*/ },
//                                colors = ButtonDefaults.buttonColors(Color.Transparent)
//                            ) {
//                                Text(text = "Mais Informações")
//                            }
//
//
//                        }
//
//                    }
//
//                    Spacer(modifier = Modifier.height(30.dp))
//
//                    Box(
//                        modifier = Modifier
//                            .background(
//                                Brush.horizontalGradient(
//                                    listOf(
//                                        BlackTransparentProliseum,
//                                        BlackTransparentProliseum
//                                    )
//                                )
//                            )
//                            .width(280.dp)
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(16.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.simbolo),
//                                contentDescription = " ",
//                                alignment = Alignment.Center
//                            )
//
//                            Text(
//                                text = "BOOM",
//                                textAlign = TextAlign.Center,
//                                color = Color.White,
//                                fontFamily = customFontFamilyText,
//                                fontWeight = FontWeight(900),
//                                fontSize = 22.sp,
//                                modifier = Modifier.padding(top = 10.dp)
//                            )
//
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(16.dp),
//
//                                ) {
//                                Column(
//                                    verticalArrangement = Arrangement.Center,
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Text(
//                                        text = "ELO",
//                                        textAlign = TextAlign.Center,
//                                        color = Color.White,
//                                        fontFamily = customFontFamilyText,
//                                        fontWeight = FontWeight(600),
//                                        modifier = Modifier.padding(10.dp)
//                                    )
//
//                                    Image(
//                                        painter = painterResource(id = R.drawable.elo),
//                                        contentDescription = ""
//                                    )
//
//                                    Text(
//                                        text = "DIAMOND V",
//                                        textAlign = TextAlign.Center,
//                                        color = Color.White,
//                                        fontFamily = customFontFamilyText,
//                                        fontWeight = FontWeight(600),
//                                        modifier = Modifier.padding(top = 10.dp)
//                                    )
//                                }
//
//                                Spacer(modifier = Modifier.width(25.dp))
//
//                                Column(
//                                    verticalArrangement = Arrangement.Center,
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Text(
//                                        text = "FUNÇÃO",
//                                        textAlign = TextAlign.Center,
//                                        color = Color.White,
//                                        fontFamily = customFontFamilyText,
//                                        fontWeight = FontWeight(600),
//                                        modifier = Modifier.padding(10.dp)
//                                    )
//
//                                    Image(
//                                        painter = painterResource(id = R.drawable.mid),
//                                        contentDescription = "",
//                                        modifier = Modifier.height(45.dp)
//                                    )
//
//                                    Text(
//                                        text = "MID",
//                                        textAlign = TextAlign.Center,
//                                        color = Color.White,
//                                        fontFamily = customFontFamilyText,
//                                        fontWeight = FontWeight(600),
//                                        modifier = Modifier.padding(top = 10.dp)
//                                    )
//                                }
//                            }
//
//                            Text(
//                                text = "HORÁRIO",
//                                textAlign = TextAlign.Center,
//                                color = Color.White,
//                                fontFamily = customFontFamilyText,
//                                fontWeight = FontWeight(600),
//                                modifier = Modifier.padding(top = 10.dp)
//                            )
//
//                            TimePickerComponent()
//
//
//                            Button(
//                                onClick = { /*TODO*/ },
//                                colors = ButtonDefaults.buttonColors(Color.Transparent)
//                            ) {
//                                Text(text = "Mais Informações")
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun TimeDisponivelPreview() {
//    TimesDisponiveisScreen()
//}