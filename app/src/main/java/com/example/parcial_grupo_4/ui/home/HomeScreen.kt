package com.example.parcial_grupo_4.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private val ScreenHorizontalPadding = 16.dp
private val AccountCardHeight = 136.dp
private val LoanItemHeight = 76.dp
private val ProductPlaceholderWidth = 132.dp
private val ProductPlaceholderHeight = 145.dp
private val CardColor = Color(0xFFFCF8F8)
private val CardShape = RoundedCornerShape(16.dp)
private val SkeletonColor = Color(0xFFEDE7E7)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onCashInClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        HomeHeaderSection(onCashInClick = onCashInClick)

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            UnpaidLoansPlaceholderSection()

            RecommendedForYouPlaceholderSection()
        }
    }
}

@Composable
private fun HomeHeaderSection(
    onCashInClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = ScreenHorizontalPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(R.string.home_account_title),
            style = MaterialTheme.typography.headlineMedium,
            color = LendlyColors.Content.Primary,
        )

        AccountBalanceCard(onCashInClick = onCashInClick)
    }
}

@Composable
private fun AccountBalanceCard(
    onCashInClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(AccountCardHeight)
            .background(CardColor, CardShape),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text(
                text = stringResource(R.string.home_available_balance),
                style = MaterialTheme.typography.labelMedium,
                color = LendlyColors.Content.Secondary,
            )

            Text(
                text = stringResource(R.string.home_balance_amount),
                style = MaterialTheme.typography.headlineLarge,
                color = LendlyColors.Content.Primary,
            )
        }

        Button(
            onClick = onCashInClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
                .height(48.dp),
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LendlyColors.Interactive.Accent,
                contentColor = LendlyColors.Content.Primary,
            ),
            contentPadding = PaddingValues(horizontal = 20.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.home_cash_in),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
private fun UnpaidLoansPlaceholderSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HomeSectionHeader(
            title = stringResource(R.string.home_unpaid_loans),
            modifier = Modifier.padding(horizontal = ScreenHorizontalPadding),
        )

        Column(
            modifier = Modifier.padding(horizontal = ScreenHorizontalPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            LoanItemPlaceholder()
            LoanItemPlaceholder()
        }
    }
}

@Composable
private fun LoanItemPlaceholder(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(LoanItemHeight)
            .background(CardColor, CardShape)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.White, CircleShape),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Box(
            modifier = Modifier
                .height(20.dp)
                .width(92.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(SkeletonColor),
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .width(72.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(SkeletonColor),
            )

            Box(
                modifier = Modifier
                    .height(12.dp)
                    .width(92.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(SkeletonColor),
            )
        }
    }
}

@Composable
private fun RecommendedForYouPlaceholderSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HomeSectionHeader(
            title = stringResource(R.string.home_recommended_for_you),
            modifier = Modifier.padding(horizontal = ScreenHorizontalPadding),
        )

        Row(
            modifier = Modifier.padding(horizontal = ScreenHorizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            repeat(3) {
                ProductItemPlaceholder()
            }
        }
    }
}

@Composable
private fun ProductItemPlaceholder(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(ProductPlaceholderWidth)
            .height(ProductPlaceholderHeight)
            .background(CardColor, CardShape)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier = Modifier
                .size(width = 48.dp, height = 56.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(SkeletonColor),
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(100.dp))
                .background(SkeletonColor),
        )

        Box(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth(0.7f)
                .clip(RoundedCornerShape(100.dp))
                .background(SkeletonColor),
        )
    }
}

@Composable
private fun HomeSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = LendlyColors.Content.Primary,
            fontWeight = FontWeight.SemiBold,
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.home_see_all),
                style = MaterialTheme.typography.labelLarge,
                color = LendlyColors.Content.Primary,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = null,
                tint = LendlyColors.Content.Primary,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}