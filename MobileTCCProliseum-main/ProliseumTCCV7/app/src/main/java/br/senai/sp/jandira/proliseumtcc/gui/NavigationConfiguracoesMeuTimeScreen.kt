package br.senai.sp.jandira.proliseumtcc.gui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum

@Composable
fun NavigationConfiguracoesMeuTimeScreen(
    onNavigate: (String) -> Unit
) {
    // Define a família da fonte personalizada
    val customFontFamily = FontFamily(
        Font(R.font.font_title) // Substitua pelo nome da fonte personalizada
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

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
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {

            Row(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        //rememberNavController.navigate("home")
                        onNavigate("perfil_time")
                    },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "GERENCIAR TIME",
                    fontFamily = customFontFamilyText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight(900),
                    color = Color.White
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
//                    onNavigate("cadastro_usuario_organizador")
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = stringResource(id = R.string.button_proximo),
                    modifier = Modifier.size(30.dp),
                    tint = Color(255, 255, 255, 255)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "MINHA POSTAGEM",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(900),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
//                    onNavigate("editar_perfil_jogador_1")
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.juntar_se_a_time),
                    contentDescription = stringResource(id = R.string.button_proximo),
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "ENTRAR NO TIME",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(900),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    onNavigate("editar_perfil_time")
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.escrever),
                    contentDescription = stringResource(id = R.string.button_proximo),
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "EDITAR PERFIL",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(900),
                )
            }

            Spacer(modifier = Modifier.height(20.dp))


        }
    }
}