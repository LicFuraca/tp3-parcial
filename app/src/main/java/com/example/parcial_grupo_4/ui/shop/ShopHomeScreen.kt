package com.example.parcial_grupo_4.ui.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.parcial_grupo_4.data.model.Category
import com.example.parcial_grupo_4.data.model.Product
import com.example.parcial_grupo_4.data.model.ShopResponse
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.common.ProductCard
import com.example.parcial_grupo_4.ui.common.PromotionalBanner

@Composable
fun ShopHomeScreen(
    viewModel: ShopViewModel,
    onProductClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onFilterClick: () -> Unit
) {
    val shopData: ShopResponse? by viewModel.shopData.observeAsState()
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val error: String? by viewModel.error.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (error != null) {
            Text(text = error!!, modifier = Modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.error)
        } else {
            shopData?.let { data ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    // Search Bar and Filter
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            SearchBar(onClick = onSearchClick, modifier = Modifier.weight(1f))
                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(
                                onClick = onFilterClick,
                                colors = IconButtonDefaults.iconButtonColors(containerColor = LendlyColors.Interactive.Accent)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.List,
                                    contentDescription = "Filter",
                                    tint = LendlyColors.Interactive.Primary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Featured Banner
                    item {
                        data.featured.firstOrNull()?.let { featured ->
                            PromotionalBanner(
                                title = "The New Shoes",
                                subtitle = "Shop this season's Top Silhouette",
                                buttonText = "Shop Now",
                                imageUrl = featured.image,
                                onClick = { onProductClick(featured.id) }
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Shop By Category
                    item {
                        SectionHeader(title = "Shop By Category", onSeeAllClick = {})
                        CategoryList(data.categories)
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Popular Brands
                    item {
                        SectionHeader(title = "Popular Brands", onSeeAllClick = {})
                        BrandList(data.brands.map { it.name })
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Recommended For You
                    item {
                        SectionHeader(title = "Recommended For You", onSeeAllClick = {})
                        ProductRow(data.products.take(5), onProductClick)
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Best Sellers
                    item {
                        SectionHeader(title = "Best Sellers", onSeeAllClick = {})
                        ProductRow(data.products.reversed().take(5), onProductClick)
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        color = LendlyColors.Background.Neutral,
        modifier = modifier.height(48.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Search for product", color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun SectionHeader(title: String, onSeeAllClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        TextButton(onClick = onSeeAllClick) {
            Text(text = "See All ->", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoryList(categories: List<Category>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(categories) { category ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(LendlyColors.Background.Neutral), // Background Neutral
                    contentAlignment = Alignment.Center
                ) {
                   GlideImage(
                       model = category.icon,
                       contentDescription = category.name,
                       modifier = Modifier.size(32.dp)
                   )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = category.name, style = MaterialTheme.typography.labelMedium, fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun BrandList(brands: List<String>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(brands) { brand ->
            AssistChip(
                onClick = {},
                label = { Text(brand) },
                shape = RoundedCornerShape(16.dp),
                colors = AssistChipDefaults.assistChipColors(containerColor = Color.White)
            )
        }
    }
}

@Composable
fun ProductRow(products: List<Product>, onProductClick: (String) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(products) { product ->
            ProductCard(product = product, onClick = onProductClick)
        }
    }
}
