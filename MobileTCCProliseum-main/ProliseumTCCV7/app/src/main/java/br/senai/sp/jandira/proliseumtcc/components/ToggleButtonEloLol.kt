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

enum class EloLol(val imageRes: Int, val id: Int) {
    IRON(br.senai.sp.jandira.proliseumtcc.R.drawable.icone_iron, 0),
    BRONZE(br.senai.sp.jandira.proliseumtcc.R.drawable.icone_bronze, 1),
    SILVER(br.senai.sp.jandira.proliseumtcc.R.drawable.icone_silver, 2),
    GOLD(br.senai.sp.jandira.proliseumtcc.R.drawable.icone_gold, 3),
    PLATINUM(br.senai.sp.jandira.proliseumtcc.R.drawable.icone_platinum, 4),
    DIAMOND(br.senai.sp.jandira.proliseumtcc.R.drawable.icone_diamond, 5),
    MASTER(br.senai.sp.jandira.proliseumtcc.R.drawable.icone_master, 6),
    GRANDMASTER(br.senai.sp.jandira.proliseumtcc.R.drawable.icone_grandmaster, 7),
    CHALLENGER(br.senai.sp.jandira.proliseumtcc.R.drawable.icone_challenger, 8)
}

@Composable
fun ToggleButtonEloLol(onJogoSelected: (EloLol?) -> Unit) {
    val selectedJogoButton = remember { mutableStateOf<EloLol?>(null) }

    Column {
        EloLol.values().forEach { elo ->
            val isEloLolSelected = elo == selectedJogoButton.value

            val painterJogo = rememberImagePainter(data = elo.imageRes)

            Card(
                modifier = Modifier
                    .size(90.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clickable {
                            selectedJogoButton.value = if (isEloLolSelected) null else elo
                            onJogoSelected(selectedJogoButton.value)
                        }
                        .background(
                            if (isEloLolSelected) RedProliseum else Color.White,
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
                                if (isEloLolSelected) RedProliseum else Color.White,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        alignment = Alignment.Center
                    )
                }
            }
        }
    }
}