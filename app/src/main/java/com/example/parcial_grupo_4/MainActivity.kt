package com.example.parcial_grupo_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.parcial_grupo_4.ui.MainScreen
import com.example.parcial_grupo_4.ui.theme.Parcialgrupo4Theme
import com.example.parcial_grupo_4.ui.navigation.AppNavGraph

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcialgrupo4Theme {
                MainScreen()
                AppNavGraph()
            }
        }
    }
}
