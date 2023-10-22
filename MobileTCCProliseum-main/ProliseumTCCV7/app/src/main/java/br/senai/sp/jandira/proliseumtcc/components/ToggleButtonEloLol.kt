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

@Composable
fun ToggleButtonEloLol(onJogoSelected: (Int?) -> Unit) {

    val toggleButtons = listOf(
        ToggleButtonEloLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.icone_iron, id = 0),
        ToggleButtonEloLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.icone_bronze, id = 1),
        ToggleButtonEloLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.icone_silver, id = 2),
        ToggleButtonEloLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.icone_gold, id = 3),
        ToggleButtonEloLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.icone_platinum, id = 4),
        ToggleButtonEloLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.icone_diamond, id = 5),
        ToggleButtonEloLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.icone_master, id = 6),
        ToggleButtonEloLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.icone_grandmaster, id = 7),
        ToggleButtonEloLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.icone_challenger, id = 8)
    )

    val selectedjogoButton = remember { mutableStateOf<Int?>(null) }

    Column {

            toggleButtons.forEach { button ->
                val isEloLolSelected = button.id == selectedjogoButton.value

                // Por via das duvidas, eu pesquisei sobre o rememberImagePainter para ver se tem outro componente
                // importado do jetpack compose porque ele esta sendo depreciado, e achei o rememberAsyncImagePainter,
                // porém para os parametros que estou passando o rememberAsyncImagePainter não serve para mim,
                // o rememberImagePainter continua a carregar imagens de forma assíncrona.
                val painterJogo = rememberImagePainter(data = button.imageRes)


                Card(
                    modifier = Modifier
                        .size(90.dp),
                    shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                if (isEloLolSelected) {
                                    selectedjogoButton.value = null
                                } else {
                                    selectedjogoButton.value = button.id
                                }

                                onJogoSelected(selectedjogoButton.value)
                            }
                            .background(
                                if (isEloLolSelected) RedProliseum else Color.White,
                                shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                            ),

                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterJogo,
                            contentDescription = null,
                            modifier = Modifier
                                .size(90.dp)
                                .padding(10.dp) // Adicione margem ao redor da imagem
                                .background(
                                    if (isEloLolSelected) RedProliseum else Color.White,
                                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
                                ),
                            alignment = Alignment.Center
                        )
                    }
                }


            }

    }
}

data class ToggleButtonEloLol(val imageRes: Int, val id: Int)