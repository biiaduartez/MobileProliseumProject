package br.senai.sp.jandira.proliseumtcc.gui

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.senai.sp.jandira.proliseumtcc.R

@Composable
fun ListaDeTimesScreen(
    onNavigate: (String) -> Unit
) {
    Icon(
        modifier = Modifier.clickable {
            //rememberNavController.navigate("home")
            onNavigate("home")
        },
        painter = painterResource(id = R.drawable.arrow_back_32),
        contentDescription = stringResource(id = R.string.button_sair),
        tint = Color.Black
    )
    Text(text = "Aqui é uma lista de times")
}