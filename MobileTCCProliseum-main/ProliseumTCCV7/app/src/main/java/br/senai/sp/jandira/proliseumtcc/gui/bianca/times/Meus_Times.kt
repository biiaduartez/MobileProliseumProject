package br.senai.sp.jandira.proliseumtcc.gui.bianca.times

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
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
//import com.example.mobileproliseum.MainActivity
//import com.example.mobileproliseum.R
//import com.example.mobileproliseum.perfil_jogador.screen.PerfilJogadorScreen
//import com.example.mobileproliseum.ui.theme.AzulEscuroProliseum
//import com.example.mobileproliseum.ui.theme.MobileProliseumTheme
//import com.example.mobileproliseum.ui.theme.RedProliseum
//
////@Composable
//fun ListTimesScreen() {
//
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
//        }
//
//
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                item {
//                        Card(
//                            modifier = Modifier
//                                .height(85.dp)
//                                .width(85.dp),
//                            colors = CardDefaults.cardColors(RedProliseum)
//                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.lol),
//                                contentDescription = "",
//                                modifier = Modifier.fillMaxSize(),
//                                alignment = Alignment.Center,
//                                colorFilter = ColorFilter.tint(AzulEscuroProliseum)
//                            )
//                        }
//
//                }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun MeusTimesPreviewScreen() {
//    MobileProliseumTheme {
//        ListTimesScreen()
//    }
//}