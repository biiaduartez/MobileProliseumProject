package br.senai.sp.jandira.proliseumtcc.gui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.BottomNavigationScreeen
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum

@Composable
fun StartScreen(onNavigate: (String) -> Unit) {
    // Define a família da fonte personalizada
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    val customFontFamily = FontFamily(
        Font(R.font.font_title) // Substitua pelo nome da fonte personalizada
    )



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xE6000000), Color(0xFFFF3130)),
                    startY = 1600f,
                    endY = 3500f
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagemfundohometeste),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.75f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 58.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.name),
                    fontFamily = customFontFamily,
                    fontSize = 64.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White

                )

            }

            Spacer(modifier = Modifier.padding(100.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.trofeu),
                    contentDescription = ""
                )
                Row {
                    Button(
                        onClick = { onNavigate("cadastro_perfil")},
                        modifier = Modifier
                            .width(250.dp)
                            .height(70.dp)
                            .padding(vertical = 0.dp),
                        shape = RoundedCornerShape(73.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum)

                    ) {
                        Text(
                            text = stringResource(id = R.string.button_inscricao),
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),
                            fontSize = 20.sp,
                            color = Color.White,

                            )
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom

                ) {
                    Button(
                        onClick = { onNavigate("login") },
                        modifier = Modifier
                            .width(108.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(73.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum)

                    ) {
                        Text(
                            text = stringResource(id = R.string.login),
                            fontSize = 16.sp,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ProliseumTCCTheme{

    }
}