package br.senai.sp.jandira.proliseumtcc.gui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarSenhaScreen(rememberNavController: NavController){

    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        AzulEscuroProliseum, AzulEscuroProliseum
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    modifier = Modifier.clickable { rememberNavController.navigate("login") },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color(255, 255, 255, 255)
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Image(
                painter = painterResource(id = R.drawable.logocadastro),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Redefinir Senha",
                fontFamily = customFontFamily,
                fontSize = 42.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 240.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                BlackTransparentProliseum, BlackTransparentProliseum
                            )
                        ), shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(35.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Text(
                        text = stringResource(id = R.string.recuperar_senha),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = stringResource(id = R.string.recuperar_senha_dois),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    var userEmailState by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = userEmailState,
                        onValueChange = { newUserEmail -> userEmailState = newUserEmail },
                        modifier = Modifier.width(320.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = {
                            Text(
                                text = stringResource(id = R.string.label_email),
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(600),
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(255, 255, 255, 255),
                            focusedBorderColor = Color(255, 255, 255, 255),
                            cursorColor = Color.White
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .width(320.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(73.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum)

                    ) {

                        Text(
                            text = stringResource(id = R.string.button_redefinir_senha),
                            fontWeight = FontWeight(900),
                            fontFamily = customFontFamilyText,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
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
fun RecuperarSenhaScreenPreview() {
    ProliseumTCCTheme {

    }
}