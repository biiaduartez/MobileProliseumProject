package br.senai.sp.jandira.proliseumtcc.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
fun ToggleButtonFuncaoLolUI(onFuncaoLol: (Int?) -> Unit) {
    val toggleButtons = listOf(
        ToggleButtonFuncaoLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.icontoplane, id = 0),
        ToggleButtonFuncaoLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.iconjungle, id = 1),
        ToggleButtonFuncaoLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.iconmidlane, id = 2),
        ToggleButtonFuncaoLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.iconsupport, id = 3),
        ToggleButtonFuncaoLol(imageRes = br.senai.sp.jandira.proliseumtcc.R.drawable.iconadc, id = 4)
    )

    val selectedFuncaoLolButton = remember { mutableStateOf<Int?>(null) }

    Column{

            toggleButtons.forEach { button ->
                val isFuncaoLolSelected = button.id == selectedFuncaoLolButton.value

                // Por via das duvidas, eu pesquisei sobre o rememberImagePainter para ver se tem outro componente
                // importado do jetpack compose porque ele esta sendo depreciado, e achei o rememberAsyncImagePainter,
                // porém para os parametros que estou passando o rememberAsyncImagePainter não serve para mim,
                // o rememberImagePainter continua a carregar imagens de forma assíncrona.
                val painterFuncaoLol = rememberImagePainter(data = button.imageRes)



                Card(
                    modifier = Modifier
                        .size(90.dp),
                    shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                if (isFuncaoLolSelected) {
                                    selectedFuncaoLolButton.value = null
                                } else {
                                    selectedFuncaoLolButton.value = button.id
                                }

                                onFuncaoLol(selectedFuncaoLolButton.value)
                            }
                            .background(
                                if (isFuncaoLolSelected) RedProliseum else Color.White,
                                shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                            ),

                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterFuncaoLol,
                            contentDescription = null,
                            modifier = Modifier
                                .size(90.dp)
                                .padding(20.dp) // Adicione margem ao redor da imagem
                                .background(
                                    if (isFuncaoLolSelected) RedProliseum else Color.White,
                                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
                                ),
                            alignment = Alignment.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

//        Text(
//            text = "Selecionado: ${selectedButton.value ?: "Nenhum"}",
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        )
    }
}

data class ToggleButtonFuncaoLol(val imageRes: Int, val id: Int)
