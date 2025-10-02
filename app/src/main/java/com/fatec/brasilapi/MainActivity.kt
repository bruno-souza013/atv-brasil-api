package com.fatec.brasilapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.fatec.brasilapi.ui.cep.CepScreen
import com.fatec.brasilapi.ui.theme.BrasilAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrasilAPITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    CepScreen()
                }
            }
        }
    }
}