package br.senai.sp.jandira.proliseumtcc.components

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInputSample(context: Context, onDateSelected: (String) -> Unit) {
    val customFontFamily = FontFamily(Font(R.font.font_title))
    val customFontFamilyText = FontFamily(Font(R.font.font_poppins))

    val calendar = Calendar.getInstance()

    val dateOfDatePickerDialog = remember { mutableStateOf(calendar.time) }

    var datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            dateOfDatePickerDialog.value = selectedDate.time

            val universalDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val formattedDateForBackend = universalDateFormat.format(selectedDate.time)
            onDateSelected(formattedDateForBackend)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    OutlinedTextField(
        value = formatDateForDisplay(dateOfDatePickerDialog.value, context),
        onValueChange = { datePickerDialog.show() },
        modifier = Modifier.width(350.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(16.dp),
        label = {
            Text(
                text = "Data de Nascimento",
                fontFamily = customFontFamilyText,
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color.White
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color(255, 255, 255, 255),
            focusedBorderColor = Color(255, 255, 255, 255),
            cursorColor = Color.White
        ),
        textStyle = TextStyle(color = Color.White)
    )
}

// Função para formatar a data para exibição de acordo com as configurações locais do dispositivo
private fun formatDateForDisplay(date: Date, context: Context): String {
    val displayFormat = SimpleDateFormat("dd-MM-yyyy", context.resources.configuration.locales[0])
    return displayFormat.format(date)
}

