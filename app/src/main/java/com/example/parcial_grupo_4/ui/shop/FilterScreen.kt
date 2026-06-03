package com.example.parcial_grupo_4.ui.shop

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    onBackClick: () -> Unit,
    onApplyFilters: () -> Unit
) {
    var selectedBrand by remember { mutableStateOf("All") }
    var selectedGender by remember { mutableStateOf("All") }
    var selectedSortBy by remember { mutableStateOf("Most Recent") }
    var selectedPriceRange by remember { mutableStateOf("All") }

    Scaffold(
        containerColor = LendlyColors.Background.Screen,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = LendlyColors.Background.Screen
                ),
                title = { 
                    Text(
                        "Filter", 
                        style = MaterialTheme.typography.titleLarge,
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
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LendlySpacing.Spacing3),
                horizontalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing3)
            ) {
                OutlinedButton(
                    onClick = { /* Reset */ },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(LendlySpacing.Spacing2),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(
                        brush = androidx.compose.ui.graphics.SolidColor(LendlyColors.Border.Neutral)
                    )
                ) {
                    Text(
                        "Reset Filter",
                        style = MaterialTheme.typography.labelLarge,
                        color = LendlyColors.Content.Secondary
                    )
                }
                Button(
                    onClick = onApplyFilters,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(LendlySpacing.Spacing2),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LendlyColors.Interactive.Accent,
                        contentColor = LendlyColors.Interactive.Primary
                    )
                ) {
                    Text(
                        "Apply",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = LendlySpacing.Spacing3)
                .verticalScroll(rememberScrollState())
        ) {
            FilterSection(title = "Brands") {
                FilterChips(
                    items = listOf("All", "Nike", "Adidas", "Puma", "Jordan"),
                    selectedItem = selectedBrand,
                    onItemSelected = { selectedBrand = it }
                )
            }
            
            FilterSection(title = "Gender") {
                FilterChips(
                    items = listOf("All", "Men", "Women"),
                    selectedItem = selectedGender,
                    onItemSelected = { selectedGender = it }
                )
            }
            
            FilterSection(title = "Sort by") {
                FilterChips(
                    items = listOf("Most Recent", "Popular", "Low Interest"),
                    selectedItem = selectedSortBy,
                    onItemSelected = { selectedSortBy = it }
                )
            }
            
            FilterSection(title = "Price Range") {
                FilterChips(
                    items = listOf("All", "$100 - $1000", "$1000 - $5000"),
                    selectedItem = selectedPriceRange,
                    onItemSelected = { selectedPriceRange = it }
                )
            }
        }
    }
}

@Composable
fun FilterSection(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(vertical = LendlySpacing.Spacing2)) {
        Text(
            text = title, 
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = LendlyColors.Content.Primary
        )
        Spacer(modifier = Modifier.height(LendlySpacing.Spacing1))
        content()
    }
}

@Composable
fun FilterChips(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing1),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(items) { item ->
            FilterChip(
                selected = selectedItem == item,
                onClick = { onItemSelected(item) },
                label = { 
                    Text(
                        item,
                        style = MaterialTheme.typography.labelMedium
                    ) 
                },
                shape = RoundedCornerShape(LendlySpacing.Spacing3),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = LendlyColors.Interactive.Accent,
                    selectedLabelColor = LendlyColors.Interactive.Primary,
                    containerColor = LendlyColors.Background.Neutral,
                    labelColor = LendlyColors.Content.Secondary
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = selectedItem == item,
                    borderColor = LendlyColors.Border.Neutral,
                    selectedBorderColor = LendlyColors.Interactive.Accent
                )
            )
        }
    }
}
