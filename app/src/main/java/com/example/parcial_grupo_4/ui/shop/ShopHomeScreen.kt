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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.parcial_grupo_4.data.model.Category
import com.example.parcial_grupo_4.data.model.Product
import com.example.parcial_grupo_4.data.model.ShopResponse
import com.example.parcial_grupo_4.ui.common.LendlySearchBar
import com.example.parcial_grupo_4.ui.common.ProductCard
import com.example.parcial_grupo_4.ui.common.PromotionalBanner
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing

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

    Box(modifier = Modifier.fillMaxSize().background(LendlyColors.Background.Screen)) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (error != null) {
            Text(
                text = error!!,
                modifier = Modifier.align(Alignment.Center),
                color = LendlyColors.Sentiment.Negative,
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            shopData?.let { data ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(LendlySpacing.Spacing3)
                ) {
                    item {


                            LendlySearchBar(
                                value = "",
                                onValueChange = {},
                                placeholder = "Search for product",
                                readOnly = true,
                                onClick = onSearchClick,
                                modifier = Modifier.fillMaxWidth(),
                                trailingContent = {

                                    Surface(
                                        onClick = onFilterClick,
                                        shape = RoundedCornerShape(8.dp),
                                        color = LendlyColors.Interactive.Primary,
                                        modifier = Modifier.size(40.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.List,
                                                contentDescription = "Filter",
                                                tint = LendlyColors.Background.Screen
                                            )
                                        }
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.height(LendlySpacing.Spacing4))

                    }
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
                        Spacer(modifier = Modifier.height(LendlySpacing.Spacing4))
                    }

                    item {
                        SectionHeader(title = "Shop By Category", onSeeAllClick = {})
                        CategoryList(categories = data.categories, products = data.products)
                        Spacer(modifier = Modifier.height(LendlySpacing.Spacing4))
                    }

                    item {
                        SectionHeader(title = "Popular Brands", onSeeAllClick = {})
                        BrandList(data.brands.map { it.name })
                        Spacer(modifier = Modifier.height(LendlySpacing.Spacing4))
                    }

                    item {
                        SectionHeader(title = "Recommended For You", onSeeAllClick = {})
                        ProductRow(data.products.take(5), onProductClick)
                        Spacer(modifier = Modifier.height(LendlySpacing.Spacing4))
                    }

                    item {
                        SectionHeader(title = "Best Sellers", onSeeAllClick = {})
                        ProductRow(data.products.reversed().take(5), onProductClick)
                        Spacer(modifier = Modifier.height(LendlySpacing.Spacing4))
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
        shape = RoundedCornerShape(LendlySpacing.Spacing4),
        color = LendlyColors.Background.Neutral,
        modifier = modifier.height(48.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = LendlySpacing.Spacing3)
        ) {
            Icon(Icons.Default.Search, contentDescription = null, tint = LendlyColors.Content.Tertiary)
            Spacer(modifier = Modifier.width(LendlySpacing.Spacing1))
            Text(
                text = "Search for product",
                color = LendlyColors.Content.Tertiary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun SectionHeader(title: String, onSeeAllClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = LendlySpacing.Spacing2),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = LendlyColors.Content.Primary
        )
        TextButton(onClick = onSeeAllClick) {
            Text(
                text = "See All ->",
                style = MaterialTheme.typography.labelMedium,
                color = LendlyColors.Content.Tertiary
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoryList(categories: List<Category>, products: List<Product>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing3)) {
        items(categories) { category ->

            val productImage = products.firstOrNull { it.category == category.id }?.image

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(LendlySpacing.Spacing3))
                        .background(LendlyColors.Background.Neutral),
                    contentAlignment = Alignment.Center
                ) {
                    if (productImage != null) {
                   GlideImage(
                       model = productImage,
                       contentDescription = category.name,
                       modifier = Modifier.fillMaxSize(),
                       contentScale = ContentScale.Crop
                   )
                } else {
                        Text(
                            text = category.icon,

                            style = MaterialTheme.typography.headlineMedium
                        )
                }

                }
                Spacer(modifier = Modifier.height(LendlySpacing.Spacing1))
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = LendlyColors.Content.Secondary
                )
            }
        }
    }
}

@Composable
fun BrandList(brands: List<String>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing1)) {
        items(brands) { brand ->
            AssistChip(
                onClick = {},
                label = { Text(brand) },
                shape = RoundedCornerShape(LendlySpacing.Spacing3),
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = LendlyColors.Background.Screen,
                    labelColor = LendlyColors.Content.Primary
                ),
                border = AssistChipDefaults.assistChipBorder(
                    enabled = true,
                    borderColor = LendlyColors.Border.Neutral
                )
            )
        }
    }
}

@Composable
fun ProductRow(products: List<Product>, onProductClick: (String) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing1)) {
        items(products) { product ->
            ProductCard(product = product, onClick = onProductClick)
        }
    }
}
