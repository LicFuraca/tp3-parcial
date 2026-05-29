package com.example.parcial_grupo_4.ui.shop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.parcial_grupo_4.data.model.Product
import com.example.parcial_grupo_4.ui.theme.LendlyColors

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailScreen(
    product: Product,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(product.name, fontSize = 16.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite")
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "From as low as", fontSize = 12.sp, color = Color.Gray)
                        Text(
                            text = "${product.currency} ${product.price}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = LendlyColors.Content.Primary
                        )
                        Text(text = "per month", fontSize = 12.sp, color = Color.Gray)
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LendlyColors.Interactive.Accent,
                            contentColor = LendlyColors.Interactive.Primary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(48.dp).padding(horizontal = 8.dp)
                    ) {
                        Text("Continue", fontWeight = FontWeight.Bold)
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
            // Header Image and Highlights
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Color.White)
            ) {
                GlideImage(
                    model = product.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().padding(32.dp),
                    contentScale = ContentScale.Fit
                )
            }
            
            // Benefits Bar (Green bar from Figma)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LendlyColors.Interactive.Accent)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                BenefitChip("Low Interest")
                Spacer(modifier = Modifier.width(8.dp))
                BenefitChip("0% installment")
                Spacer(modifier = Modifier.width(8.dp))
                BenefitChip("Extra P50 Off")
            }
            
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "From as low as", fontSize = 12.sp, color = Color.Gray)
                Text(
                    text = "${product.currency} ${product.price} / per month",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = LendlyColors.Content.Primary
                )
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Location Section
                Text(text = "WHERE DO YOU WANT TO SHOP?", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedCard(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Davao City, Davao del Sur", modifier = Modifier.weight(1f))
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Marketplace Partners
                ExpandableSection(title = "MARKETPLACE PARTNER MERCHANTS") {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        MerchantItem("Power Mac Center", "Limited Availability", "From P1,200 x 12 months", "P1,800 total price", "45% Downpayment")
                        MerchantItem("The Loop", "Limited Availability", "From P1,208 x 12 months", "P1,800 total price", "45% Downpayment")
                        MerchantItem("i-Mac Center", "Limited Availability", "From P1,208 x 12 months", "P1,800 total price", "45% Downpayment")
                    }
                }
                
                // Features
                ExpandableSection(title = "FEATURES") {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        FeatureRow(Icons.AutoMirrored.Filled.List, "How To Apply For A Loan", "(1) Only 1 ID needed for the loan approval and...")
                        FeatureRow(Icons.Default.Info, "Disclaimer", "Estimated calculation only. Down Payment and other loan terms may vary.")
                    }
                }
                
                // Product Specifications
                ExpandableSection(title = "PRODUCT SPECIFICATIONS") {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
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
        Box(modifier = Modifier.size(24.dp).background(Color.Black, RoundedCornerShape(4.dp)))
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = name, fontWeight = FontWeight.Bold)
            Text(text = availability, color = LendlyColors.Sentiment.Warning, fontSize = 10.sp)
            Text(text = installment, fontSize = 12.sp)
            Text(text = total, fontSize = 12.sp, color = Color.Gray)
            Text(text = downpayment, fontSize = 12.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Gray)
    }
}

@Composable
fun FeatureRow(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, description: String) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp), tint = Color.Gray)
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text(text = description, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun SpecItem(label: String, value: String) {
    Column {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        Text(text = value, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun BenefitChip(text: String) {
    Surface(
        color = Color.White.copy(alpha = 0.3f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = Color(0xFF122300)
        )
    }
}

@Composable
fun ExpandableSection(title: String, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(true) } // Open by default like in Figma
    
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        HorizontalDivider(color = Color(0xFFF0F0F0))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Gray)
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color.Gray
            )
        }
        AnimatedVisibility(visible = expanded) {
            Box(modifier = Modifier.padding(bottom = 12.dp)) {
                content()
            }
        }
    }
}
