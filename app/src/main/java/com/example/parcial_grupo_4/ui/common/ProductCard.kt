package com.example.parcial_grupo_4.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.parcial_grupo_4.data.model.Product
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing
import com.example.parcial_grupo_4.util.FormatUtils.formatCurrencyAmount

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductCard(
    product: Product,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(160.dp) // Fixed width for horizontal scrolling rows
            .padding(LendlySpacing.Spacing1),
        shape = RoundedCornerShape(LendlySpacing.Spacing2),
        colors = CardDefaults.cardColors(containerColor = LendlyColors.Background.Screen),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { onClick(product.id) }
    ) {
        Column {
            GlideImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = LendlySpacing.Spacing2, topEnd = LendlySpacing.Spacing2)),
                contentScale = ContentScale.Crop
            )
            
            Column(modifier = Modifier.padding(LendlySpacing.Spacing1)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = LendlyColors.Content.Primary,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(LendlySpacing.Spacing1))
                
                Text(
                    text = "${product.currency} ${formatCurrencyAmount(product.price)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LendlyColors.Interactive.Primary,
                    fontWeight = FontWeight.ExtraBold
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "${product.installmentMonths} cuotas de ${product.currency} ${formatCurrencyAmount(product.monthlyInstallment)}",
                    style = MaterialTheme.typography.labelSmall,
                    color = LendlyColors.Content.Tertiary
                )
            }
        }
    }
}
