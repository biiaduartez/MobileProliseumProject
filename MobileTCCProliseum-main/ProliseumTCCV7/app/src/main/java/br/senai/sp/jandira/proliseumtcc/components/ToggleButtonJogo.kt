package br.senai.sp.jandira.proliseumtcc.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.rememberImagePainter

enum class Jogo(val imageRes: Int, val id: Int) {
    CSGO(br.senai.sp.jandira.proliseumtcc.R.drawable.iconcsgo, 0),
    LOL(br.senai.sp.jandira.proliseumtcc.R.drawable.iconlol, 1),
    VALORANT(br.senai.sp.jandira.proliseumtcc.R.drawable.iconvalorant, 2)
}

@Composable
fun ToggleButtonJogoUI(onJogoSelected: (Jogo?) -> Unit) {
    val selectedJogoButton = remember { mutableStateOf<Jogo?>(null) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Jogo.values().forEach { jogo ->
                val isJogoSelected = jogo == selectedJogoButton.value

                val painterJogo = rememberImagePainter(data = jogo.imageRes)

                Card(
                    modifier = Modifier.size(90.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Box(
                        modifier = Modifier.clickable {
                            selectedJogoButton.value = if (isJogoSelected) null else jogo
                            onJogoSelected(selectedJogoButton.value)
                        }
                            .background(
                                if (isJogoSelected) RedProliseum else Color.White,
                                shape = RoundedCornerShape(24.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterJogo,
                            contentDescription = null,
                            modifier = Modifier
                                .size(90.dp)
                                .padding(10.dp)
                                .background(
                                    if (isJogoSelected) RedProliseum else Color.White,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            alignment = Alignment.Center
                        )
                    }
                }
            }
        }
    }
}