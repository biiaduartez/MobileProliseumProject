package br.senai.sp.jandira.proliseumtcc.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.rememberImagePainter

@Composable
fun BottomNavigationScreeen(onNavigate: (String) -> Unit, onGenderSelected: (Int?) -> Unit) {

    val toggleButtonsNavigation = listOf(
        ToggleButtonNavigation(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.home_icon, id = 0),
        ToggleButtonNavigation(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.teams_icon, id = 1),
        ToggleButtonNavigation(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.search_icon, id = 2),
        ToggleButtonNavigation(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.trofeu_icon, id = 3),
        ToggleButtonNavigation(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.free_agent_icon, id = 4)
    )

    val selectedButtonGenero = remember { mutableStateOf<Int?>(null) }


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    AzulEscuroProliseum,
                ),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            toggleButtonsNavigation.forEach { button ->
                val isSelected = button.id == selectedButtonGenero.value

                val painter = rememberImagePainter(data = button.imageRes)

                Card(
                    modifier = Modifier.size(50.dp),
                    shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                ) {
                    Box(
                        modifier = Modifier.clickable {
                            if (isSelected) {
                                selectedButtonGenero.value = null
                            } else {
                                selectedButtonGenero.value = button.id
                            }

                            if(button.id == 0){
                                onNavigate("home")
                            } else if(button.id == 1){
                                //navController.navigate("login")
                                //onNavigate("perfil_usuario_jogador")
                            }else if(button.id == 2){
                                //navController.navigate("login")
                            }else if(button.id == 3){
                                //navController.navigate("login")
                            }else if(button.id == 4){
                                //navController.navigate("login")
                            }
                            // Chame a função de retorno para notificar a seleção
                            onGenderSelected(selectedButtonGenero.value)
                        }
                            .background(
                                if (isSelected) RedProliseum else AzulEscuroProliseum,
                                shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier.size(50.dp).padding(10.dp)
                                .background(
                                    if (isSelected) RedProliseum else AzulEscuroProliseum,
                                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
                                ),
                            alignment = Alignment.Center
                        )
                    }
                }
            }
        }
    }
}

data class ToggleButtonNavigation(val imageRes: Int, val id: Int)

@Preview(showBackground = true)
@Composable
fun BottomNavigationScreenPreview() {

}