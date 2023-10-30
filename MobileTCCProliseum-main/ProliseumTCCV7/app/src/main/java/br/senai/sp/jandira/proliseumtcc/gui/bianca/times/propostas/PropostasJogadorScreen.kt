package com.example.mobileproliseum.propostas.screen

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
//import androidx.compose.foundation.layout.size
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
//import com.example.mobileproliseum.jogadorDisponivel.screen.JogadoresDisponiveisScreen
//import com.example.mobileproliseum.ui.theme.AzulEscuroProliseum
//import com.example.mobileproliseum.ui.theme.BlackTransparentProliseum
//
//@Composable
//fun PropostaJogadorScreen() {
//    val customFontFamily = FontFamily(
//        Font(R.font.font_title)
//    )
//    val customFontFamilyText = FontFamily(
//        Font(R.font.font_poppins)
//    )
//
//    val context = LocalContext.current
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
//
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
//        }
//
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(60.dp)
//        ) {
//            item {
//
//                Box(
//                    modifier = Modifier
//                        .background(
//                            Brush.horizontalGradient(
//                                listOf(
//                                    BlackTransparentProliseum,
//                                    BlackTransparentProliseum
//                                )
//                            )
//                        )
//                        .width(280.dp)
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(16.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.superpersonicon),
//                            contentDescription = " ",
//                            alignment = Alignment.Center
//                        )
//
//                        Text(
//                            text = "NX-KING",
//                            textAlign = TextAlign.Center,
//                            color = Color.White,
//                            fontFamily = customFontFamilyText,
//                            fontWeight = FontWeight(900),
//                            fontSize = 22.sp,
//                            modifier = Modifier.padding(top = 10.dp)
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        Row {
//                            Button(
//                                onClick = { /*TODO*/ },
//                                colors = ButtonDefaults.buttonColors(Color.White),
//                                shape = RoundedCornerShape(10.dp),
//
//                                ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.baseline_check_circle_24),
//                                    contentDescription = "",
//                                    modifier = Modifier.size(50.dp)
//                                )
//                            }
//                            Spacer(modifier = Modifier.width(16.dp))
//
//                            Button(
//                                onClick = { /*TODO*/ },
//                                colors = ButtonDefaults.buttonColors(Color.White),
//                                shape = RoundedCornerShape(10.dp),
//
//                                ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.baseline_not_interested_24),
//                                    contentDescription = "",
//                                    modifier = Modifier.size(50.dp)
//                                )
//                            }
//                        }
//
//                    }
//
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Box(
//                    modifier = Modifier
//                        .background(
//                            Brush.horizontalGradient(
//                                listOf(
//                                    BlackTransparentProliseum,
//                                    BlackTransparentProliseum
//                                )
//                            )
//                        )
//                        .width(280.dp)
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(16.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.superpersonicon),
//                            contentDescription = " ",
//                            alignment = Alignment.Center
//                        )
//
//                        Text(
//                            text = "NX-KING",
//                            textAlign = TextAlign.Center,
//                            color = Color.White,
//                            fontFamily = customFontFamilyText,
//                            fontWeight = FontWeight(900),
//                            fontSize = 22.sp,
//                            modifier = Modifier.padding(top = 10.dp)
//                        )
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        Row {
//                            Button(
//                                onClick = { /*TODO*/ },
//                                colors = ButtonDefaults.buttonColors(Color.White),
//                                shape = RoundedCornerShape(10.dp),
//
//                                ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.baseline_check_circle_24),
//                                    contentDescription = "",
//                                    modifier = Modifier.size(50.dp)
//                                )
//                            }
//                            Spacer(modifier = Modifier.width(16.dp))
//
//                            Button(
//                                onClick = { /*TODO*/ },
//                                colors = ButtonDefaults.buttonColors(Color.White),
//                                shape = RoundedCornerShape(10.dp),
//
//                                ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.baseline_not_interested_24),
//                                    contentDescription = "",
//                                    modifier = Modifier.size(50.dp)
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun JogadorPropostaPreview() {
//    PropostaJogadorScreen()
//}