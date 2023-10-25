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

enum class FuncaoLol(val imageRes: Int, val id: Int) {
    TOP(br.senai.sp.jandira.proliseumtcc.R.drawable.icontoplane, 0),
    JUNGLE(br.senai.sp.jandira.proliseumtcc.R.drawable.iconjungle, 1),
    MIDLANE(br.senai.sp.jandira.proliseumtcc.R.drawable.iconmidlane, 2),
    SUPPORT(br.senai.sp.jandira.proliseumtcc.R.drawable.iconsupport, 3),
    ADC(br.senai.sp.jandira.proliseumtcc.R.drawable.iconadc, 4);

    fun toRepresentationStrinFuncao(): String {
        return when (this) {
            TOP -> "0"
            JUNGLE -> "1"
            MIDLANE -> "2"
            SUPPORT -> "3"
            ADC -> "4"
        }
    }
}

@Composable
fun ToggleButtonFuncaoLolUI(onFuncaoLol: (FuncaoLol?) -> Unit) {
    val selectedFuncaoLolButton = remember { mutableStateOf<FuncaoLol?>(null) }

    Column {
        FuncaoLol.values().forEach { funcao ->
            val isFuncaoLolSelected = funcao == selectedFuncaoLolButton.value

            val painterFuncaoLol = rememberImagePainter(data = funcao.imageRes)

            Card(
                modifier = Modifier.size(90.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clickable {
                            selectedFuncaoLolButton.value = if (isFuncaoLolSelected) null else funcao
                            onFuncaoLol(selectedFuncaoLolButton.value)
                        }
                        .background(
                            if (isFuncaoLolSelected) RedProliseum else Color.White,
                            shape = RoundedCornerShape(24.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterFuncaoLol,
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                            .padding(20.dp)
                            .background(
                                if (isFuncaoLolSelected) RedProliseum else Color.White,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        alignment = Alignment.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
