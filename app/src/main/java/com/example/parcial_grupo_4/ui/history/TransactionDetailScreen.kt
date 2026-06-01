package com.example.parcial_grupo_4.ui.history

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private val HeroCircleSize = 80.dp
private val HeroIconSize = 28.dp
private val ChipShape = RoundedCornerShape(8.dp)

@Composable
fun TransactionDetailScreen(
    title: String,
    amount: String,
    company: String,
    modifier: Modifier = Modifier,
    onHelpClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .verticalScroll(rememberScrollState()),
    ) {
        HeroSection(title = title, amount = amount, company = company)
        DetailsSection()
        Spacer(modifier = Modifier.height(24.dp))
        HelpFooter(onHelpClick = onHelpClick)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun HeroSection(title: String, amount: String, company: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LendlyColors.Background.Icon)
            .padding(top = 8.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(HeroCircleSize)
                .background(color = LendlyColors.Interactive.Accent, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowUpward,
                contentDescription = null,
                tint = LendlyColors.Content.Primary,
                modifier = Modifier.size(HeroIconSize),
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = LendlyColors.Content.Tertiary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = amount,
            style = MaterialTheme.typography.headlineMedium,
            color = LendlyColors.Content.Strong,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.transaction_detail_to_format, company),
            style = MaterialTheme.typography.bodyMedium,
            color = LendlyColors.Content.Tertiary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .border(width = 1.dp, color = LendlyColors.Content.Tertiary, shape = ChipShape)
                .padding(horizontal = 16.dp, vertical = 6.dp),
        ) {
            Text(
                text = stringResource(R.string.transaction_detail_badge),
                style = MaterialTheme.typography.labelLarge,
                color = LendlyColors.Content.Strong,
            )
        }
    }
}

@Composable
private fun DetailsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(R.string.transaction_detail_title),
            style = MaterialTheme.typography.titleLarge,
            color = LendlyColors.Content.Strong,
        )
        DetailRow(
            label = stringResource(R.string.transaction_detail_fee_label),
            value = stringResource(R.string.transaction_detail_fee_value),
        )
        DetailRow(
            label = stringResource(R.string.transaction_detail_date_label),
            value = stringResource(R.string.transaction_detail_date_value),
        )
        DetailRow(
            label = stringResource(R.string.transaction_detail_number_label),
            value = stringResource(R.string.transaction_detail_number_value),
            valueIsLink = true,
        )
    }
}

@Composable
private fun DetailRow(label: String, value: String, valueIsLink: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = LendlyColors.Content.Strong,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = if (valueIsLink) FontWeight.SemiBold else FontWeight.Normal,
                textDecoration = if (valueIsLink) TextDecoration.Underline else null,
            ),
            color = if (valueIsLink) LendlyColors.Content.Link else LendlyColors.Content.Strong,
        )
    }
}

@Composable
private fun HelpFooter(onHelpClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalDivider(thickness = 1.dp, color = LendlyColors.Border.Neutral)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.transaction_detail_help_question),
            style = MaterialTheme.typography.bodyMedium,
            color = LendlyColors.Content.Tertiary,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.transaction_detail_help_link),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
            ),
            color = LendlyColors.Content.Link,
            modifier = Modifier.clickable(onClick = onHelpClick),
        )
    }
}
