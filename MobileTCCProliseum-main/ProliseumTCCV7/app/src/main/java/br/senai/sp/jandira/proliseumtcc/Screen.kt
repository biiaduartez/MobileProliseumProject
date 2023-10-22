package br.senai.sp.jandira.proliseumtcc

sealed class Screen(val route: String){
    object OtherMainScreen : Screen("other_main_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }
}
