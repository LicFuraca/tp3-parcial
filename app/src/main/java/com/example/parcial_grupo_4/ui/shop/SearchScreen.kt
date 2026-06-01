package com.example.parcial_grupo_4.ui.shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    onSearchSubmit: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var recentSearches by remember { 
        mutableStateOf(listOf("blue shirt", "red shirt", "yellow shirt", "Blue Shoes", "Yellow Shoes", "Red Shoes")) 
    }

    Scaffold(
        containerColor = LendlyColors.Background.Screen,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LendlyColors.Background.Screen
                ),
                title = {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { 
                            Text(
                                "Search for product",
                                style = MaterialTheme.typography.bodyMedium,
                                color = LendlyColors.Content.Tertiary
                            ) 
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = LendlyColors.Content.Primary,
                            unfocusedTextColor = LendlyColors.Content.Primary
                        ),
                        leadingIcon = { 
                            Icon(
                                Icons.Default.Search, 
                                contentDescription = null,
                                tint = LendlyColors.Content.Tertiary
                            ) 
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(
                                        Icons.Default.Close, 
                                        contentDescription = null,
                                        tint = LendlyColors.Content.Tertiary
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                if (searchQuery.isNotBlank()) {
                                    onSearchSubmit(searchQuery)
                                }
                            }
                        )
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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(LendlySpacing.Spacing3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = LendlyColors.Content.Primary
                )
                TextButton(onClick = { recentSearches = emptyList() }) {
                    Text(
                        text = "Clear All", 
                        style = MaterialTheme.typography.labelLarge,
                        color = LendlyColors.Interactive.Primary
                    )
                }
            }
            
            LazyColumn {
                items(recentSearches) { search ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSearchSubmit(search) }
                            .padding(vertical = LendlySpacing.Spacing2),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = search, 
                            style = MaterialTheme.typography.bodyMedium,
                            color = LendlyColors.Content.Secondary
                        )
                        IconButton(
                            onClick = { recentSearches = recentSearches.filter { it != search } },
                            modifier = Modifier.size(LendlySpacing.Spacing4)
                        ) {
                            Icon(
                                Icons.Default.Close, 
                                contentDescription = null, 
                                tint = LendlyColors.Border.Neutral
                            )
                        }
                    }
                    HorizontalDivider(color = LendlyColors.Border.Neutral)
                }
            }
        }
    }
}
