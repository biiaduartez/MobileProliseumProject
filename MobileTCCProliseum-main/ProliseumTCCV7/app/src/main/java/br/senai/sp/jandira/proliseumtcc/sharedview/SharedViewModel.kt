package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var username: String = ""
    var fullname: String = ""
    var email: String = ""
    var password: String = ""
}

