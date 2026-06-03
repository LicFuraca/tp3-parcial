package com.example.parcial_grupo_4.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.common.PrimaryButton
import com.example.parcial_grupo_4.ui.theme.Inter
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.Montserrat

private val ScreenBackground = Color.White
private val HeaderBackground = LendlyColors.Background.Soft
private val SuccessGreen = Color(0xFF7BF179)
private val CloseCircleColor = Color(0xFFE5E2E1)
private val ContentHorizontalPadding = 16.dp

@Composable
fun SuccessfulTransactionScreen(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit = {},
    onDoneClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 88.dp),
        ) {
            SuccessTopActions(onCloseClick = onCloseClick)

            SuccessHero()

            TransactionDetails()
        }

        PrimaryButton(
            text = stringResource(R.string.success_done),
            onClick = onDoneClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = ContentHorizontalPadding)
                .padding(bottom = 24.dp),
        )
    }
}

@Composable
private fun SuccessTopActions(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(HeaderBackground)
            .padding(horizontal = ContentHorizontalPadding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onCloseClick,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(CloseCircleColor),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_cross),
                contentDescription = stringResource(R.string.cd_close),
                tint = LendlyColors.Content.Primary,
                modifier = Modifier.size(18.dp),
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_info),
                    contentDescription = stringResource(R.string.cd_info),
                    tint = LendlyColors.Content.Primary,
                    modifier = Modifier.size(20.dp),
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_more),
                    contentDescription = stringResource(R.string.cd_more),
                    tint = LendlyColors.Content.Primary,
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}

@Composable
private fun SuccessHero(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(234.dp)
            .background(HeaderBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(SuccessGreen),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.cd_add),
                tint = LendlyColors.Content.Primary,
                modifier = Modifier.size(32.dp),
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.success_added_to_account),
            style = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
                color = Color(0xFF6A6C6A),
                textAlign = TextAlign.Center,
            ),
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = stringResource(R.string.cash_in_success_amount),
            style = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                lineHeight = 36.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
            ),
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = stringResource(R.string.success_from, stringResource(R.string.cash_in_success_source)),
            style = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
                color = Color(0xFF6A6C6A),
                textAlign = TextAlign.Center,
            ),
        )

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.Transparent,
            border = BorderStroke(1.dp, Color(0xFF6A6C6A)),
        ) {
            Text(
                text = stringResource(R.string.cash_in_success_chip),
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    color = Color(0xFF6A6C6A),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            )
        }
    }
}

@Composable
private fun TransactionDetails(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(ScreenBackground)
            .padding(horizontal = ContentHorizontalPadding)
            .padding(top = 28.dp),
    ) {
        Text(
            text = stringResource(R.string.success_transaction_details),
            style = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                color = Color(0xFF171D1E),
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TransactionDetailRow(
            label = stringResource(R.string.cash_in_success_transfer_fee),
            value = stringResource(R.string.cash_in_success_transfer_fee_value),
        )

        Spacer(modifier = Modifier.height(8.dp))

        TransactionDetailRow(
            label = stringResource(R.string.success_date_time),
            value = stringResource(R.string.cash_in_success_date_time_value),
        )

        Spacer(modifier = Modifier.height(8.dp))

        TransactionDetailRow(
            label = stringResource(R.string.success_transaction_number),
            value = stringResource(R.string.cash_in_success_transaction_number_value),
            isLink = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        HorizontalDivider(color = LendlyColors.Border.Subtle)

        Spacer(modifier = Modifier.height(28.dp))

        HelpSection()
    }
}

@Composable
private fun TransactionDetailRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    isLink: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = DetailLabelTextStyle,
            modifier = Modifier.weight(1f),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = value,
            style = if (isLink) DetailLinkTextStyle else DetailValueTextStyle,
            textAlign = TextAlign.End,
        )
    }
}

@Composable
private fun HelpSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.success_need_help),
            style = HelpTextStyle,
            color = Color(0xFF6A6C6A),
            textAlign = TextAlign.Center,
        )

        Text(
            text = stringResource(R.string.success_help_center),
            style = HelpTextStyle.copy(
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            ),
            color = Color(0xFF4C662B),
            textAlign = TextAlign.Center,
        )
    }
}

private val DetailLabelTextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Color(0xFF6A6C6A),
)

private val DetailValueTextStyle = DetailLabelTextStyle.copy(
    textAlign = TextAlign.End,
)

private val DetailLinkTextStyle = DetailLabelTextStyle.copy(
    fontWeight = FontWeight.Bold,
    color = Color(0xFF4C662B),
    textDecoration = TextDecoration.Underline,
    textAlign = TextAlign.End,
)

private val HelpTextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.25.sp,
)