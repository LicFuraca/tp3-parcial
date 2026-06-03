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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.parcial_grupo_4.data.model.LoanItem
import com.example.parcial_grupo_4.data.model.Product
import com.example.parcial_grupo_4.util.FormatUtils.formatCurrencyAmount
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

private val ScreenHorizontalPadding = 16.dp
private val AccountCardHeight = 136.dp
private val LoanItemHeight = 76.dp
private val ProductPlaceholderWidth = 132.dp
private val ProductPlaceholderHeight = 145.dp
private val CardColor = LendlyColors.Background.Soft
private val CardShape = RoundedCornerShape(16.dp)
private val SkeletonColor = Color(0xFFEDE7E7)

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    onCashInClick: () -> Unit = {},
    onProductClick: (String) -> Unit = {},
    onSeeAllProductsClick: () -> Unit = {},
    onSeeAllLoansClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()
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
            when {
                uiState.isLoading -> {
                    UnpaidLoansPlaceholderSection()
                    RecommendedForYouPlaceholderSection()
                }

                uiState.error != null -> {
                    Text(
                        text = uiState.error.orEmpty(),
                        modifier = Modifier.padding(horizontal = ScreenHorizontalPadding),
                        color = LendlyColors.Sentiment.Negative,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                else -> {
                    UnpaidLoansSection(
                        loans = uiState.unpaidLoans,
                        onSeeAllClick = onSeeAllLoansClick,
                    )

                    RecommendedForYouSection(
                        products = uiState.recommendedProducts,
                        onProductClick = onProductClick,
                        onSeeAllClick = onSeeAllProductsClick,
                    )
                }
            }
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
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(12.dp),
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
            onSeeAllClick = {},
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
            onSeeAllClick = {},
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
    onSeeAllClick: () -> Unit,
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onSeeAllClick() },
        ) {
            Text(
                text = stringResource(R.string.home_see_all),
                style = MaterialTheme.typography.labelLarge,
                color = LendlyColors.Content.Primary,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(14.dp),
            )
        }
    }
}

@Composable
private fun UnpaidLoansSection(
    loans: List<LoanItem>,
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HomeSectionHeader(
            title = stringResource(R.string.home_unpaid_loans),
            onSeeAllClick = onSeeAllClick,
            modifier = Modifier.padding(horizontal = ScreenHorizontalPadding),
        )

        Column(
            modifier = Modifier.padding(horizontal = ScreenHorizontalPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            loans.forEach { loan ->
                LoanItemCard(loan = loan)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun LoanItemCard(
    loan: LoanItem,
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
        GlideImage(
            model = loan.imageUrl,
            contentDescription = loan.lender,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentScale = ContentScale.Fit,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = loan.lender,
            style = MaterialTheme.typography.bodyLarge,
            color = LendlyColors.Content.Primary,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "₱${formatCurrencyAmount(loan.monthlyFee)}",
                style = MaterialTheme.typography.bodyMedium,
                color = LendlyColors.Content.Primary,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = loan.fees.orEmpty(),
                style = MaterialTheme.typography.labelSmall,
                color = LendlyColors.Content.Secondary,
            )
        }
    }
}

@Composable
private fun RecommendedForYouSection(
    products: List<Product>,
    onProductClick: (String) -> Unit,
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HomeSectionHeader(
            title = stringResource(R.string.home_recommended_for_you),
            onSeeAllClick = onSeeAllClick,
            modifier = Modifier.padding(horizontal = ScreenHorizontalPadding),
        )

        LazyRow(
            modifier = Modifier.padding(horizontal = ScreenHorizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(products) { product ->
                HomeProductCard(
                    product = product,
                    onClick = onProductClick,
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun HomeProductCard(
    product: Product,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(132.dp)
            .height(145.dp)
            .background(CardColor, RoundedCornerShape(12.dp))
            .clickable { onClick(product.id) }
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        GlideImage(
            model = product.image,
            contentDescription = product.name,
            modifier = Modifier
                .size(width = 90.dp, height = 70.dp),
            contentScale = ContentScale.Fit,
        )

        Text(
            text = product.name,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF454745),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = "${product.currency} ${formatCurrencyAmount(product.monthlyInstallment)} × ${product.installmentMonths} mo",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelSmall,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF3C6839),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}