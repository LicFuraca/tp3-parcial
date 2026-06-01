package com.example.parcial_grupo_4.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private val IconContainerSize = 40.dp
private val IconSize = 15.dp
private val ItemGap = 10.dp

data class TransactionUiModel(
    val id: String,
    val title: String,
    val subtitleCompany: String,
    val time: String,
    val amount: String,
    val icon: ImageVector = Icons.Outlined.ArrowUpward,
    // Campos que se muestran en el detalle / se usan para agrupar.
    val description: String = "",
    val dateTime: String = "",
    val referenceNumber: String = "",
    val status: String = "",
    val monthLabel: String = "",
    // type crudo de la API ("LOAN_PAYMENT", "CASH_IN"...), usado para filtrar.
    val type: String = "",
)

@Composable
fun TransactionItem(
    transaction: TransactionUiModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(ItemGap),
    ) {
        Box(
            modifier = Modifier
                .size(IconContainerSize)
                .background(color = LendlyColors.Background.Icon, shape = CircleShape)
                .padding(8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = transaction.icon,
                contentDescription = stringResource(R.string.history_transaction_icon),
                tint = LendlyColors.Content.Icon,
                modifier = Modifier.size(IconSize),
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = transaction.time,
                    style = MaterialTheme.typography.labelMedium,
                    color = LendlyColors.Content.Tertiary,
                )
                Text(
                    text = transaction.subtitleCompany,
                    style = MaterialTheme.typography.labelMedium,
                    color = LendlyColors.Content.Tertiary,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = transaction.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = LendlyColors.Content.Strong,
                )
                Text(
                    text = transaction.amount,
                    style = MaterialTheme.typography.bodyLarge,
                    color = LendlyColors.Content.Strong,
                )
            }
        }
    }
}
