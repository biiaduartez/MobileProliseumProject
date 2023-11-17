package br.senai.sp.jandira.proliseumtcc.gui.deletar

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilOrganizacao
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun DeletarOrganizacaoScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,
    onNavigate: (String) -> Unit
) {

    //TOKEN
    val token = sharedViewModelTokenEId.token

    //  VERIFICAR CAMPOS PREENCHIDOS INCORRETAMENTE
    var camposPreenchidosCorretamente by rememberSaveable { mutableStateOf(true) }
    var mensagemErroInputsPerfil = rememberSaveable { mutableStateOf("") }

    // FONTE
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    val idUser = sharedViewModelPerfilEditar.id
    val nomeUser = sharedViewModelPerfilEditar.nome_usuario
    val nomeOrganizacao = sharedViewModelPerfilOrganizador.nome_organizacao
    val biografiaOrganizacao = sharedViewModelPerfilOrganizador.biografia

    val aceitarTermosState = remember { mutableStateOf(false) }

    //DESIGN DA TELA
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
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {


            Row(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        //rememberNavController.navigate("home")
                        onNavigate("navigation_configuracoes_perfil")
                    },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Voltar para menu de navegação",
                    fontFamily = customFontFamilyText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(900),
                    color = Color.White
                )

            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                LaunchedEffect(camposPreenchidosCorretamente) {
                    if (!camposPreenchidosCorretamente) {
                        delay(15000)
                        camposPreenchidosCorretamente = true
                    }
                }

                AnimatedVisibility(
                    visible = !camposPreenchidosCorretamente,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    Snackbar(
                        modifier = Modifier.padding(top = 16.dp),
                        action = {}
                    ) {
                        Text(text = mensagemErroInputsPerfil.value)
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 130.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
            ) {
                Text(
                    text = "DELETAR ORGANIZAÇÃO",
                    fontFamily = customFontFamilyText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight(900),
                    color = RedProliseum
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(350.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                AzulEscuroProliseum, AzulEscuroProliseum
                            )
                        )
                    )
                    .border( // Adiciona uma borda
                        width = 4.dp, // Largura da borda
                        color = RedProliseum, // Cor da borda
                        shape = RoundedCornerShape(32.dp) // Forma da borda (neste caso, cantos arredondados)
                    )
                    .padding(16.dp),
            ){
                Text(
                    text = "Eu organizador(a) ${nomeUser}, estou ciente da exclusão da organização ${nomeOrganizacao}.",
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(600),
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = aceitarTermosState.value,
                    onCheckedChange = { aceitarTermosState.value = it },
                    modifier = Modifier
                        .scale(scale = 1.6f)
                        .size(40.dp)
                        .padding(16.dp),
                    colors = CheckboxDefaults.colors(checkedColor = Color(0, 255, 165, 255)),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Confirmar a exclusão",
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(600),
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    if(token == null || idUser == null || aceitarTermosState.value == false){

                        camposPreenchidosCorretamente = false
                        mensagemErroInputsPerfil.value = "Confirme a exclusão da organização"

                    }else if (token != null && idUser != null && aceitarTermosState.value == true ){
                        deletarOrganizacao(sharedViewModelTokenEId)

                        sharedViewModelPerfilOrganizador.nome_organizacao == nomeOrganizacao
                        sharedViewModelPerfilOrganizador.biografia == biografiaOrganizacao
                        onNavigate("carregar_deletar_organizacao")
                    }


                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(RedProliseum)

            ) {
                Text(
                    text = "DELETAR ORGANIZAÇÃO",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(900),
                )
            }
        }
    }
}

fun deletarOrganizacao(
    sharedViewModelTokenEId: SharedViewTokenEId
){

    val token = sharedViewModelTokenEId.token

    // Obtenha o serviço Retrofit para editar o perfil do usuário
    val excluirPerfilOrganizacao = RetrofitFactoryCadastro().deletarOrganizadorService()

    // Realize a chamada de API para editar o perfil
    excluirPerfilOrganizacao.deleteProfileOrganizador("Bearer " + token)
        .enqueue(object : Callback<EditarPerfilOrganizacao> {
            override fun onResponse(
                call: Call<EditarPerfilOrganizacao>,
                response: Response<EditarPerfilOrganizacao>
            ) {
                if (response.isSuccessful) {
                    Log.d(
                        "EditarPerfilJogadorPart1",
                        "Perfil de usuário atualizado com sucesso: ${response.code()}"
                    )
                    // Trate a resposta bem-sucedida, se necessário
                } else {
                    // Trate a resposta não bem-sucedida
                    Log.d(
                        "EditarPerfilJogadorPart1",
                        "Falha ao atualizar o perfil do usuário: ${response.code()}"
                    )
                    // Log do corpo da resposta (se necessário)
                    Log.d(
                        "EditarPerfilJogadorPart1",
                        "Corpo da resposta: ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<EditarPerfilOrganizacao>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("EditarPerfilJogadorPart1", "Erro de rede: ${t.message}")
            }
        })
}