package com.example.parcial_grupo_4.ui.shop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.parcial_grupo_4.data.model.Product
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing
import com.example.parcial_grupo_4.util.FormatUtils.formatCurrencyAmount

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailScreen(
    product: Product,
    onBackClick: () -> Unit
) {
    Scaffold(
        containerColor = LendlyColors.Background.Screen,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LendlyColors.Background.Screen
                ),
                title = { 
                    Text(
                        product.name, 
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = LendlyColors.Content.Primary
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, 
                            contentDescription = "Back",
                            tint = LendlyColors.Content.Primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.FavoriteBorder, 
                            contentDescription = "Favorite",
                            tint = LendlyColors.Content.Primary
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Share, 
                            contentDescription = "Share",
                            tint = LendlyColors.Content.Primary
                        )
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
                color = LendlyColors.Background.Screen,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(LendlySpacing.Spacing3)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "From as low as", 
                            style = MaterialTheme.typography.labelSmall,
                            color = LendlyColors.Content.Tertiary
                        )
                        Text(
                            text = "${product.currency} ${formatCurrencyAmount(product.monthlyInstallment)} / per month for ${product.installmentMonths} mos",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = LendlyColors.Content.Primary
                        )
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = LendlyColors.Content.Tertiary
                        )
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LendlyColors.Interactive.Accent,
                            contentColor = LendlyColors.Interactive.Primary
                        ),
                        shape = RoundedCornerShape(LendlySpacing.Spacing2),
                        modifier = Modifier.height(48.dp).padding(horizontal = LendlySpacing.Spacing1)
                    ) {
                        Text(
                            "Continue", 
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(LendlyColors.Background.Screen),
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    model = product.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().padding(LendlySpacing.Spacing5),
                    contentScale = ContentScale.Fit
                )
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LendlyColors.Interactive.Accent)
                    .padding(LendlySpacing.Spacing1),
                horizontalArrangement = Arrangement.Center
            ) {
                BenefitChip("Low Interest")
                Spacer(modifier = Modifier.width(LendlySpacing.Spacing1))
                BenefitChip("0% installment")
                Spacer(modifier = Modifier.width(LendlySpacing.Spacing1))
                BenefitChip("Extra P50 Off")
            }
            
            Column(modifier = Modifier.padding(LendlySpacing.Spacing3)) {
                Text(
                    text = "From as low as", 
                    style = MaterialTheme.typography.labelSmall,
                    color = LendlyColors.Content.Tertiary
                )
                Text(
                    text = "${product.currency} ${product.price} / per month",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = LendlyColors.Content.Primary
                )
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = LendlyColors.Content.Tertiary
                )
                
                Spacer(modifier = Modifier.height(LendlySpacing.Spacing4))
                
                Text(
                    text = "WHERE DO YOU WANT TO SHOP?", 
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = LendlyColors.Content.Primary
                )
                Spacer(modifier = Modifier.height(LendlySpacing.Spacing1))
                OutlinedCard(
                    shape = RoundedCornerShape(LendlySpacing.Spacing1),
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, LendlyColors.Border.Neutral)
                ) {
                    Row(
                        modifier = Modifier.padding(LendlySpacing.Spacing2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.LocationOn, 
                            contentDescription = null, 
                            tint = LendlyColors.Content.Tertiary
                        )
                        Spacer(modifier = Modifier.width(LendlySpacing.Spacing1))
                        Text(
                            text = "Davao City, Davao del Sur", 
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f),
                            color = LendlyColors.Content.Primary
                        )
                        Icon(
                            Icons.Default.KeyboardArrowDown, 
                            contentDescription = null,
                            tint = LendlyColors.Content.Tertiary
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(LendlySpacing.Spacing4))
                
                ExpandableSection(title = "MARKETPLACE PARTNER MERCHANTS") {
                    Column(verticalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing3)) {
                        MerchantItem("Power Mac Center", "Limited Availability", "From P1,200 x 12 months", "P1,800 total price", "45% Downpayment")
                        MerchantItem("The Loop", "Limited Availability", "From P1,208 x 12 months", "P1,800 total price", "45% Downpayment")
                        MerchantItem("i-Mac Center", "Limited Availability", "From P1,208 x 12 months", "P1,800 total price", "45% Downpayment")
                    }
                }
                
                ExpandableSection(title = "FEATURES") {
                    Column(verticalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing2)) {
                        FeatureRow(Icons.AutoMirrored.Filled.List, "How To Apply For A Loan", "(1) Only 1 ID needed for the loan approval and...")
                        FeatureRow(Icons.Default.Info, "Disclaimer", "Estimated calculation only. Down Payment and other loan terms may vary.")
                    }
                }

                if (!product.description.isNullOrEmpty()) {
                    ExpandableSection(title = "PRODUCT DESCRIPTION") {
                        Text(
                            text = product.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = LendlyColors.Content.Tertiary
                        )
                    }
                }

                
                ExpandableSection(title = "PRODUCT SPECIFICATIONS") {
                    Column(verticalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing2)) {
                        SpecItem("Chip", "A15 Bionic chip\n6-core CPU with 2 performance and 4 efficiency cores\n5-core GPU\n16-core Neural Engine")
                        SpecItem("Camera", "12MP camera\nf/1.8 aperture\nAutofocus with Focus Pixels\nRetina Flash")
                    }
                }



            }

        }

    }

}

@Composable
fun MerchantItem(name: String, availability: String, installment: String, total: String, downpayment: String) {
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(LendlyColors.Interactive.Primary, RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(LendlySpacing.Spacing2))
        Column {
            Text(
                text = name, 
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = LendlyColors.Content.Primary
            )
            Text(
                text = availability, 
                style = MaterialTheme.typography.labelSmall,
                color = LendlyColors.Sentiment.Warning
            )
            Text(
                text = installment, 
                style = MaterialTheme.typography.bodySmall,
                color = LendlyColors.Content.Primary
            )
            Text(
                text = total, 
                style = MaterialTheme.typography.bodySmall,
                color = LendlyColors.Content.Tertiary
            )
            Text(
                text = downpayment, 
                style = MaterialTheme.typography.bodySmall,
                color = LendlyColors.Content.Tertiary
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.Default.KeyboardArrowDown, 
            contentDescription = null, 
            tint = LendlyColors.Content.Tertiary
        )
    }
}

@Composable
fun FeatureRow(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, description: String) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(
            icon, 
            contentDescription = null, 
            modifier = Modifier.size(20.dp), 
            tint = LendlyColors.Content.Tertiary
        )
        Spacer(modifier = Modifier.width(LendlySpacing.Spacing2))
        Column {
            Text(
                text = title, 
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = LendlyColors.Content.Primary
            )
            Text(
                text = description, 
                style = MaterialTheme.typography.bodySmall,
                color = LendlyColors.Content.Tertiary
            )
        }
    }
}

@Composable
fun SpecItem(label: String, value: String) {
    Column {
        Text(
            text = label, 
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = LendlyColors.Content.Primary
        )
        Text(
            text = value, 
            style = MaterialTheme.typography.bodySmall,
            color = LendlyColors.Content.Tertiary
        )
    }
}

@Composable
fun BenefitChip(text: String) {
    Surface(
        color = LendlyColors.Contrast.Light.copy(alpha = 0.3f),
        shape = RoundedCornerShape(LendlySpacing.Spacing3)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = LendlySpacing.Spacing1, vertical = 4.dp),
            color = LendlyColors.Interactive.Primary
        )
    }
}

@Composable
fun ExpandableSection(title: String, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(true) }
    
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = LendlySpacing.Spacing1)) {
        HorizontalDivider(color = LendlyColors.Border.Neutral)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(vertical = LendlySpacing.Spacing2),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title, 
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold, 
                color = LendlyColors.Content.Tertiary
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = LendlyColors.Content.Tertiary
            )
        }
        AnimatedVisibility(visible = expanded) {
            Box(modifier = Modifier.padding(bottom = LendlySpacing.Spacing2)) {
                content()
            }
        }
    }
}
