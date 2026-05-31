package com.example.parcial_grupo_4.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.ui.theme.Inter
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private val BottomBarLabelColor = Color(0xFF102000)

private val BottomBarLabelStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp,
    color = BottomBarLabelColor,
)

data class LendlyBottomBarItem(
    val route: String,
    @param:StringRes val labelRes: Int,
    val icon: ImageVector,
)

@Composable
fun LendlyBottomBar(
    items: List<LendlyBottomBarItem>,
    currentRoute: String?,
    onItemSelected: (LendlyBottomBarItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        HorizontalDivider(thickness = 1.dp, color = LendlyColors.Border.Neutral)
        NavigationBar(
            containerColor = LendlyColors.Background.Screen,
            tonalElevation = 0.dp,
        ) {
            items.forEach { item ->
                val label = stringResource(item.labelRes)
                NavigationBarItem(
                    selected = item.route == currentRoute,
                    onClick = { onItemSelected(item) },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = label)
                    },
                    label = {
                        Text(text = label, style = BottomBarLabelStyle)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BottomBarLabelColor,
                        selectedTextColor = BottomBarLabelColor,
                        indicatorColor = LendlyColors.Background.Neutral,
                        unselectedIconColor = BottomBarLabelColor,
                        unselectedTextColor = BottomBarLabelColor,
                    ),
                )
            }
        }
    }
}
