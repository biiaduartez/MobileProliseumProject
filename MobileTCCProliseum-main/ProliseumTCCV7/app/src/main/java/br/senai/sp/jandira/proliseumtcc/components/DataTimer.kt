package br.senai.sp.jandira.proliseumtcc.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum

import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerComponent(
    selectedStartTime: LocalTime,
    onStartTimeSelected: (LocalTime) -> Unit
) {
    fun getTimeString(time: LocalTime): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return time.format(formatter)
    }

    var isStartTimePickerVisible by remember { mutableStateOf(false) }
    var selectedTime by remember(selectedStartTime) { mutableStateOf(selectedStartTime) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tempo de Início
            Button(
                onClick = { isStartTimePickerVisible = true },
                colors = ButtonDefaults.buttonColors(RedProliseum)
            ) {
                Text(text = "Tempo de início")
            }

            Spacer(modifier = Modifier.height(16.dp))

            var selectedTimeString by remember(selectedTime) {
                mutableStateOf(getTimeString(selectedTime))
            }

            Text(
                text = selectedTimeString,
                color = Color.Black,
                fontWeight = FontWeight(900),
                fontSize = 22.sp
            )

            if (isStartTimePickerVisible) {
                val timePickerState by remember {
                    mutableStateOf(
                        androidx.compose.material3.TimePickerState(
                            initialHour = selectedTime.hour,
                            initialMinute = selectedTime.minute,
                            is24Hour = false,
                        )
                    )
                }

                TimePicker(
                    state = timePickerState,
                    modifier = Modifier.background(
                        Brush.horizontalGradient(
                            listOf(
                                AzulEscuroProliseum, AzulEscuroProliseum
                            )
                        )
                    )
                )

                Button(
                    onClick = {
                        isStartTimePickerVisible = false
                        selectedTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
                        onStartTimeSelected(selectedTime)
                        selectedTimeString = getTimeString(selectedTime)
                        Log.d("TimePickerComponent", " $selectedTimeString")
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(RedProliseum)
                ) {
                    Text(text = "OK")
                }
            }
        }
    }
}



