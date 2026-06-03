package com.example.parcial_grupo_4.ui.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing
import androidx.compose.ui.res.painterResource
import com.example.parcial_grupo_4.data.model.Brand
import com.example.parcial_grupo_4.R

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
                            Image(
                                painter = painterResource(id = R.drawable.promotional_card),
                                contentDescription = "Promotional Banner",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    // Le damos la forma redondeada del diseño
                                    .clip(RoundedCornerShape(16.dp))
                                    // Hacemos que toda la imagen sea clickeable
                                    .clickable { onProductClick(featured.id) },
                                // Ajusta la imagen para que llene el ancho sin deformarse
                                contentScale = ContentScale.FillWidth
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
                        BrandList(data.brands)
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

            val productImage = when (category.id) {
                "electronics" -> products.find { it.id == "PROD-001" }?.image
                "fashion" -> products.find { it.id == "PROD-004" }?.image
                "appliances" -> products.find { it.id == "PROD-005" }?.image
                else -> null
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(92.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(LendlyColors.Background.Screen),
                    contentAlignment = Alignment.Center
                ) {
                    if (productImage != null) {
                   GlideImage(
                       model = productImage,
                       contentDescription = category.name,
                       modifier = Modifier.fillMaxSize().padding(4.dp),
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BrandList(brands: List<Brand>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing3)) {
        items(brands) { brand ->

            val displayName = if (brand.id == "samsung") "Jordan" else brand.name
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = LendlyColors.Background.Neutral),
                // Eliminamos el borde gris para que sea igual al diseño limpio de Figma
                modifier = Modifier
                    .width(140.dp)
                    .height(110.dp) // Tamaño rectangular
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

                    // Mitad superior: Imagen de fondo hardcodeada desde res/drawable
                    Box(modifier = Modifier.weight(1f).fillMaxWidth()) {


                        val bgDrawableRes = when (brand.id) {
                            "apple" -> R.drawable.appleimage
                            "nike" -> R.drawable.adidasimage
                            "samsung" -> R.drawable.jordanimage
                            else -> R.drawable.ic_launcher_background
                        }

                        Image(
                            painter = painterResource(id = bgDrawableRes),
                            contentDescription = brand.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop // Crop recorta la imagen para llenar el espacio sin deformarla
                        )
                    }

                    // Mitad inferior: Franja blanca con nombre y loguito
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(LendlyColors.Background.Subtle)
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = displayName,
                            style = MaterialTheme.typography.labelMedium,
                            color = LendlyColors.Content.Primary,
                            fontWeight = FontWeight.SemiBold
                        )

                        // 👇 Loguito pequeño de la API
                        if (brand.id == "samsung") {
                            // Como Samsung ahora es Jordan, usamos tu logo local de Jordan
                            Image(
                                painter = painterResource(id = R.drawable.vector), // Asegúrate de tener este archivo
                                contentDescription = "Jordan logo",
                                modifier = Modifier.size(16.dp),
                                contentScale = ContentScale.Fit
                            )
                        } else { GlideImage(
                            model = brand.logo,
                            contentDescription = "${brand.name} logo",
                            modifier = Modifier.size(16.dp), // Tamaño pequeñito como en el diseño
                            contentScale = ContentScale.Fit
                        )
                        }
                    }
                }
            }
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


