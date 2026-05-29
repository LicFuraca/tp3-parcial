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
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Filter", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = { /* Reset */ },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Reset Filter")
                }
                Button(
                    onClick = onApplyFilters,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7BF179), contentColor = Color(0xFF122300))
                ) {
                    Text("Apply")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
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
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(text = title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
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
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(items) { item ->
            FilterChip(
                selected = selectedItem == item,
                onClick = { onItemSelected(item) },
                label = { Text(item) },
                shape = RoundedCornerShape(12.dp),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0xFF7BF179),
                    selectedLabelColor = Color(0xFF122300)
                )
            )
        }
    }
}
