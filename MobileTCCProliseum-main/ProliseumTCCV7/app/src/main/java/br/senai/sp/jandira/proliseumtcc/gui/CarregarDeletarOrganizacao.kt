package br.senai.sp.jandira.proliseumtcc.gui

import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import br.senai.sp.jandira.proliseumtcc.MainActivity
import br.senai.sp.jandira.proliseumtcc.components.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.components.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarDeletarOrganizacao(sharedViewModelTokenEId: SharedViewTokenEId, sharedViewModelPerfilEditar: SharedViewModelPerfil, onNavigate: (String) -> Unit) {

    var loading by remember { mutableStateOf(true) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        // Espera por 3 segundos antes de continuar
        delay(5000)
        loading = false
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        AzulEscuroProliseum, AzulEscuroProliseum
                    )
                )
            )
    ) {
        // Imagem Capa
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {


            if (loading) {
                // Exibe um indicador de progresso enquanto carrega
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )

                val token = sharedViewModelTokenEId.token

                if(token != null && token.isNotEmpty() && sharedViewModelPerfilEditar.id != 0){

                    Log.d("INFORMAÇOES DE USUARIO 02", "Token: $token, Id: ${sharedViewModelPerfilEditar.id}, Nome de usuario: ${sharedViewModelPerfilEditar.nome_usuario}")

                    // Coloque sua lógica de validação aqui
                    val suaCondicaoDeValidacao = true // Substitua por sua condição real

                    if (suaCondicaoDeValidacao) {
                        // Reinicie o aplicativo
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as ComponentActivity).finish()
                    }
                }

            }


        }
    }
}