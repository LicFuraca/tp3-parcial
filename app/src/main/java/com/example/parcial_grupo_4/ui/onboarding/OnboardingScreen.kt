package com.example.parcial_grupo_4.ui.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.R
import java.util.Locale

val montserratFamily = FontFamily(
    Font(R.font.montserrat_extrabold, FontWeight.ExtraBold)
)

val interFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal)
)

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel,
    onNavigateToLogin: () -> Unit,
    onFinishOnboarding: () -> Unit
) {
    val currentPage by viewModel.currentPage.observeAsState(initial = 0)

    val slides = listOf(
        OnboardingSlide(
            R.drawable.avatar_onboarding_1,
            R.string.title_onboarding_1,
            R.string.subtitle_onboarding_1
        ),
        OnboardingSlide(
            R.drawable.avatar_onboarding_2,
            R.string.title_onboarding_2,
            R.string.subtitle_onboarding_2
        ),
        OnboardingSlide(R.drawable.avatar_onboarding_3, R.string.title_onboarding_3, null)
    )

    val currentSlide = slides[currentPage]
    val isLastPage = currentPage == slides.size - 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.dark_green))
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.25f))

        Image(
            painter = painterResource(id = R.drawable.logo_lendly),
            contentDescription = stringResource(id = R.string.desc_lendly_logo),
            modifier = Modifier
                .height(40.dp)
                .width(120.dp),
            contentScale = ContentScale.Fit
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(x = 28.dp, y = (-145).dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_rectangle),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )

                Image(
                    painter = painterResource(id = currentSlide.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(0.9f),
                    contentScale = ContentScale.FillWidth
                )
            }

            if (currentPage == 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = (-30).dp, y = (-85).dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                    ) {
                        LoanCard(
                            title = stringResource(id = R.string.loan_successful),
                            amount = stringResource(id = R.string.loan_amount)
                        )
                        LoanCard(
                            title = stringResource(id = R.string.loan_successful),
                            amount = stringResource(id = R.string.loan_amount)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .offset(x = 10.dp, y = (-13).dp)
                            .clip(CircleShape)
                            .background(colorResource(id = R.color.light_green)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_money),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_applause),
                        contentDescription = stringResource(id = R.string.desc_applause_emoji),
                        modifier = Modifier
                            .size(28.dp)
                            .align(Alignment.TopCenter)
                            .offset(x = 30.dp, y = (-65).dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_emoji),
                        contentDescription = stringResource(id = R.string.desc_smile_emoji),
                        modifier = Modifier
                            .size(28.dp)
                            .align(Alignment.BottomStart)
                            .offset(x = 50.dp, y = 45.dp)
                    )
                }
            } else if (currentPage == 1) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = 10.dp, y = (-190).dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ProductCard(
                            imageRes = R.drawable.ic_laptop,
                            title = stringResource(id = R.string.product_iphone_title)
                        )
                        ProductCard(
                            imageRes = R.drawable.ic_iphone,
                            title = stringResource(id = R.string.product_iphone_title)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .offset(x = (-15).dp, y = (-15).dp)
                            .clip(CircleShape)
                            .background(colorResource(id = R.color.light_green)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_shopping_bag),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp) // Ajustado a 24.dp para encajar mejor
                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_surprise),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = 50.dp, y = (-175).dp)
                        .size(30.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_money_wings),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = 50.dp, y = (-120).dp)
                        .size(30.dp)
                )
            } else {
                // LÓGICA PARA LA TERCERA PANTALLA
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        // Posicionamos la tarjeta pegada a la izquierda
                        .offset(x = 0.dp, y = (-120).dp)
                ) {
                    TransactionCard()

                    // Ícono flotante del Calendario
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .offset(x = 150.dp, y = (-15).dp)
                            .clip(CircleShape)
                            .background(colorResource(id = R.color.light_green)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_calendar_),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }

                // Emoji Flotante 1 (Billete/Dólar)
                Image(
                    painter = painterResource(id = R.drawable.ic_dolar_),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = 20.dp, y = (-170).dp)
                        .size(30.dp)
                )

                // Emoji Flotante 2 (Cara sonriente)
                Image(
                    painter = painterResource(id = R.drawable.ic_emoji_smile),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = 60.dp, y = (-60).dp)
                        .size(30.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(0.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = (-110).dp)
        ) {
            Text(
                text = stringResource(id = currentSlide.titleRes).uppercase(Locale.getDefault()),
                color = colorResource(id = R.color.light_mint),
                fontFamily = montserratFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                lineHeight = 35.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            currentSlide.subtitleRes?.let { subtitle ->
                Text(
                    text = stringResource(id = subtitle).replace(", ", ",\n"),
                    color = colorResource(id = R.color.touring_green),
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 28.sp
                )
            }
        }

        // PUNTOS Y BOTONES INFERIORES
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            slides.forEachIndexed { index, _ ->
                Dot(active = index == currentPage)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // LÓGICA DE BOTONES PARA LA ÚLTIMA PANTALLA
        if (isLastPage) {
            OutlinedButton(
                onClick = onNavigateToLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                border = BorderStroke(1.dp, Color.White),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.btn_log_in),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { onFinishOnboarding() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.light_green)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.btn_sign_up_free),
                    color = colorResource(id = R.color.dark_green),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Button(
                onClick = { viewModel.setPage(currentPage + 1) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.light_green)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.onboarding_btn_get_started),
                    color = colorResource(id = R.color.dark_green),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun Dot(active: Boolean) {
    Box(
        modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(
                color = if (active) colorResource(R.color.light_green) else Color.White.copy(alpha = 0.3f)
            )
    )
}

@Composable
fun LoanCard(title: String, amount: String) {
    Row(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.glass_card_bg),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            fontFamily = interFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
        )
        Text(
            text = amount,
            color = colorResource(id = R.color.light_green),
            fontFamily = interFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
        )
    }
}

@Composable
fun ProductCard(imageRes: Int, title: String) {
    Column(
        modifier = Modifier
            .width(80.dp)
            .height(70.dp)
            .background(
                color = colorResource(id = R.color.glass_card_bg),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(42.dp)
                .padding(bottom = 2.dp),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        )
        Text(
            text = title,
            color = Color.White,
            fontFamily = interFamily,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1
        )
    }
}

@Composable
fun TransactionCard() {
    Row(
        modifier = Modifier
            .width(200.dp)
            .height(55.dp)
            .background(
                color = colorResource(id = R.color.glass_card_bg),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_nike),
                contentDescription = stringResource(id = R.string.desc_nike_logo),
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
            Text(
                text = stringResource(id = R.string.transaction_nike_title),
                color = Color.White,
                fontFamily = interFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                maxLines = 1
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = stringResource(id = R.string.transaction_amount),
                color = colorResource(id = R.color.light_green),
                fontFamily = interFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                maxLines = 1

            )
            Text(
                text = stringResource(id = R.string.transaction_nike_subtitle),
                color = Color.White.copy(alpha = 0.7f),
                fontFamily = interFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 8.sp,
                maxLines = 1

            )
        }
    }
}