package br.com.cofermeta.listagem_de_produtos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import br.com.cofermeta.listagem_de_produtos.ui.ListagemDeProdutosNavHost
import br.com.cofermeta.listagem_de_produtos.ui.theme.ToolboxTheme

class ListagemDeProdutosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolboxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ListagemDeProdutosNavHost()
                }
            }
        }
    }
}

