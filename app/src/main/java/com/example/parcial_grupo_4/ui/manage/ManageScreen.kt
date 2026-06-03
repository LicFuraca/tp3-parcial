package com.example.parcial_grupo_4.ui.manage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.ui.theme.LendlyColors

@Composable
fun ManageScreen(
    onNavigateToProfile: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        // --- HEADER ---
        Text(
            text = "Manage",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = LendlyColors.Content.Primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- CURRENTLY USING AS ---
        Text(
            text = "Currently using as",
            fontSize = 14.sp,
            color = LendlyColors.Content.Tertiary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta de cuenta
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Avatar "KB"
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(LendlyColors.Background.Neutral),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "KB", fontWeight = FontWeight.Bold)
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .align(Alignment.BottomEnd)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Account details",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = LendlyColors.Content.Primary
                    )
                    Text(
                        text = "Your personal Account",
                        fontSize = 12.sp,
                        color = LendlyColors.Content.Tertiary
                    )
                }
            }

            Button(
                onClick = onNavigateToProfile,
                colors = ButtonDefaults.buttonColors(containerColor = LendlyColors.Interactive.Primary),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text("Edit", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- GENERAL SECTION ---
        Text(
            text = "General",
            fontSize = 14.sp,
            color = LendlyColors.Content.Tertiary
        )

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = LendlyColors.Background.Subtle)
        Spacer(modifier = Modifier.height(8.dp))


        ManageListItem(
            icon = Icons.Default.Person,
            title = "Account details",
            onClick = onNavigateToProfile
        )


        ManageListItem(
            icon = Icons.Default.Email,
            title = "Receiving by email or phone",
            onClick = { }
        )
        ManageListItem(
            icon = Icons.Default.DateRange,
            title = "Scheduled pay",
            onClick = { }
        )
        ManageListItem(
            icon = Icons.Outlined.CheckCircle,
            title = "Credit score",
            onClick = { }
        )
        ManageListItem(
            icon = Icons.Default.Settings,
            title = "Settings",
            onClick = { }
        )
        ManageListItem(
            icon = Icons.AutoMirrored.Filled.List,
            title = "Terms and Conditions",
            onClick = { }
        )
        ManageListItem(
            icon = Icons.AutoMirrored.Filled.HelpOutline,
            title = "Help",
            onClick = { }
        )

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = LendlyColors.Background.Subtle)
        Spacer(modifier = Modifier.height(16.dp))

        ManageListItem(
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            title = "Log Out",
            onClick = onLogout
        )

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun ManageListItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = LendlyColors.Content.Secondary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                color = LendlyColors.Content.Primary,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Go",
            tint = LendlyColors.Content.Tertiary
        )
    }
}