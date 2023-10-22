package br.senai.sp.jandira.proliseumtcc.components

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModelImageUri : ViewModel() {
    var imageUri: Uri? by mutableStateOf(null)
    var imageCapaUri: Uri? by mutableStateOf(null)
}