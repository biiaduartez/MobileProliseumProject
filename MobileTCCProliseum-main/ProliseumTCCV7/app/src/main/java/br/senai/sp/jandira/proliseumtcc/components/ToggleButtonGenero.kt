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
fun ToggleButtonGeneroUI(onGenderSelected: (Int?) -> Unit) {

    val toggleButtons = listOf(
        ToggleButtonGenero(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.generomasculino, id = 1),
        ToggleButtonGenero(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.generofeminino, id = 2),
        ToggleButtonGenero(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.generoindefinido, id = 3)
    )

    val selectedButtonGenero = remember { mutableStateOf<Int?>(null) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            toggleButtons.forEach { button ->
                val isSelected = button.id == selectedButtonGenero.value

                val painter = rememberImagePainter(data = button.imageRes)

                Card(
                    modifier = Modifier.size(80.dp),
                    shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                ) {
                    Box(
                        modifier = Modifier.clickable {
                            if (isSelected) {
                                selectedButtonGenero.value = null
                            } else {
                                selectedButtonGenero.value = button.id
                            }
                            // Chame a função de retorno para notificar a seleção
                            onGenderSelected(selectedButtonGenero.value)
                        }
                            .background(
                                if (isSelected) RedProliseum else Color.White,
                                shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier.size(80.dp).padding(10.dp)
                                .background(
                                    if (isSelected) RedProliseum else Color.White,
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

data class ToggleButtonGenero(val imageRes: Int, val id: Int)