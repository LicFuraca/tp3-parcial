package com.example.parcial_grupo_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.parcial_grupo_4.ui.auth.PantallaCreatePassword
import com.example.parcial_grupo_4.ui.auth.PantallaDone
import com.example.parcial_grupo_4.ui.auth.PantallaFaceRecognition
import com.example.parcial_grupo_4.ui.auth.PantallaIdVerification
import com.example.parcial_grupo_4.ui.auth.PantallaLogin
import com.example.parcial_grupo_4.ui.auth.PantallaProfileDetail
import com.example.parcial_grupo_4.ui.auth.PantallaSignature
import com.example.parcial_grupo_4.ui.auth.PantallaSmsVerification
import com.example.parcial_grupo_4.ui.auth.PantallaVerifiedPage
import com.example.parcial_grupo_4.ui.auth.PantallaVerifyPhone
import com.example.parcial_grupo_4.ui.theme.Parcialgrupo4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Usamos la función del tema del grupo 4
            Parcialgrupo4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Metemos nuestra App principal dentro del Scaffold con sus márgenes
                    ParcialTP3App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// El mapa con los nombres de todas tus pantallas del parcial
enum class PantallasParcial {
    LOGIN,
    VERIFY_PHONE,
    SMS_VERIFICATION,
    FACE_RECOGNITION,
    ID_VERIFICATION,
    VERIFIED_PAGE,
    PROFILE_DETAIL,
    SIGNATURE,
    CREATE_PASSWORD,
    DONE
}

@Composable
fun ParcialTP3App(modifier: Modifier = Modifier) {
    var pantallaActual by rememberSaveable { mutableStateOf(PantallasParcial.LOGIN) }

    when (pantallaActual) {
        PantallasParcial.LOGIN -> {
            PantallaLogin(onNext = { pantallaActual = PantallasParcial.VERIFY_PHONE })
        }
        PantallasParcial.VERIFY_PHONE -> {
            PantallaVerifyPhone(
                onNext = { pantallaActual = PantallasParcial.SMS_VERIFICATION },
                onBack = { pantallaActual = PantallasParcial.LOGIN }
            )
        }
        PantallasParcial.SMS_VERIFICATION -> {
            PantallaSmsVerification(
                onNext = { pantallaActual = PantallasParcial.FACE_RECOGNITION },
                onBack = { pantallaActual = PantallasParcial.VERIFY_PHONE }
            )
        }
        PantallasParcial.FACE_RECOGNITION -> {
            PantallaFaceRecognition(
                onNext = { pantallaActual = PantallasParcial.ID_VERIFICATION },
                onBack = { pantallaActual = PantallasParcial.SMS_VERIFICATION }
            )
        }
        PantallasParcial.ID_VERIFICATION -> {
            PantallaIdVerification(
                onNext = { pantallaActual = PantallasParcial.VERIFIED_PAGE },
                onBack = { pantallaActual = PantallasParcial.FACE_RECOGNITION }
            )
        }
        PantallasParcial.VERIFIED_PAGE -> {
            PantallaVerifiedPage(
                onNext = { pantallaActual = PantallasParcial.PROFILE_DETAIL }
            )
        }
        PantallasParcial.PROFILE_DETAIL -> {
            PantallaProfileDetail(
                onNext = { pantallaActual = PantallasParcial.SIGNATURE },
                onBack = { pantallaActual = PantallasParcial.VERIFIED_PAGE }
            )
        }
        PantallasParcial.SIGNATURE -> {
            PantallaSignature(
                onNext = { pantallaActual = PantallasParcial.CREATE_PASSWORD },
                onBack = { pantallaActual = PantallasParcial.PROFILE_DETAIL }
            )
        }
        PantallasParcial.CREATE_PASSWORD -> {
            PantallaCreatePassword(
                onNext = { pantallaActual = PantallasParcial.DONE },
                onBack = { pantallaActual = PantallasParcial.SIGNATURE }
            )
        }
        PantallasParcial.DONE -> {
            // Al presionar el botón final, lo mandamos al inicio del Login otra vez
            PantallaDone(onGetStarted = { pantallaActual = PantallasParcial.LOGIN })
        }
    }
}