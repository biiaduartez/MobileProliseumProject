package br.senai.sp.jandira.proliseumtcc.components


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerComponent() {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var isTimePickerVisible by remember { mutableStateOf(false) }
            var selectedTime by remember { mutableStateOf(LocalTime.now()) }

            Button(
                onClick = { isTimePickerVisible = true },
                colors = ButtonDefaults.buttonColors(RedProliseum)
            ) {
                Text(text = "Tempo de inicio")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${selectedTime.hour}:${selectedTime.minute}",
                color = Color.White,
                fontWeight = FontWeight(900),
                fontSize = 22.sp
            )

            if (isTimePickerVisible) {
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
                        isTimePickerVisible = false
                        selectedTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(RedProliseum)
                ) {
                    Text(text = "OK")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isTimePickerVisible by remember { mutableStateOf(false) }
        var selectedTime by remember { mutableStateOf(LocalTime.now()) }

        Button(
            onClick = { isTimePickerVisible = true },
            colors = ButtonDefaults.buttonColors(RedProliseum)
        ) {
            Text(text = "Tempo de inicio")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${selectedTime.hour}:${selectedTime.minute}",
            color = Color.White,
            fontWeight = FontWeight(900),
            fontSize = 22.sp
        )

        if (isTimePickerVisible) {
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
                    isTimePickerVisible = false
                    selectedTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
                },
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(RedProliseum)
            ) {
                Text(text = "OK")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

    }
}

