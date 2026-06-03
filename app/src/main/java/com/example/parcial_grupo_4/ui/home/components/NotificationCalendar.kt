package com.example.parcial_grupo_4.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.res.painterResource
import com.example.parcial_grupo_4.R


private val CalendarShape = RoundedCornerShape(16.dp)
private val DividerColor = Color(0xFFE5E2E1)
private val AccentColor = Color(0xFF7BF179)

@Composable
fun NotificationCalendar(
    onOkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .height(504.dp)
            .background(Color.White, CalendarShape),
    ) {
        CalendarHeader()

        CalendarMonthRow()

        CalendarGrid()

        CalendarFooter(onOkClick = onOkClick)
    }
}

@Composable
private fun CalendarHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 24.dp, top = 16.dp, end = 12.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = "Due dates",
            style = MaterialTheme.typography.labelLarge,
            color = Color(0xFF454745),
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            text = "Mon, Aug 17",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF1D1B20),
            fontWeight = FontWeight.SemiBold,
        )
    }

    HorizontalDivider(thickness = 1.dp, color = DividerColor)
}

@Composable
private fun CalendarMonthRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 16.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier.height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "August 2023",
                style = MaterialTheme.typography.labelLarge,
                color = Color(0xFF454745),
                fontWeight = FontWeight.SemiBold,
            )

            Icon(
                painter = painterResource(R.drawable.ic_arrow_down),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }

        Row(
            modifier = Modifier.height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_mini_arrow_left),
                contentDescription = null,
                tint = Color.Unspecified,
            )

            Icon(
                painter = painterResource(R.drawable.ic_mini_arrow_right),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }
    }
}

@Composable
private fun CalendarGrid() {
    val weekDays = listOf("S", "M", "T", "W", "T", "F", "S")
    val days = listOf(
        listOf("", "", "1", "2", "3", "4", "5"),
        listOf("6", "7", "8", "9", "10", "11", "12"),
        listOf("13", "14", "15", "16", "17", "18", "19"),
        listOf("20", "21", "22", "23", "24", "25", "26"),
        listOf("27", "28", "29", "30", "31", "", ""),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            weekDays.forEach { day ->
                CalendarCell {
                    Text(
                        text = day,
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }

        days.forEach { week ->
            Row(modifier = Modifier.fillMaxWidth()) {
                week.forEach { day ->
                    CalendarCell {
                        CalendarDay(day = day)
                    }
                }
            }
        }
    }
}

@Composable
private fun androidx.compose.foundation.layout.RowScope.CalendarCell(
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(48.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Composable
private fun CalendarDay(day: String) {
    when (day) {
        "17" -> {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(AccentColor, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                DayText(day)
            }
        }

        "5" -> {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, AccentColor, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                DayText(day)
            }
        }

        else -> DayText(day)
    }
}

@Composable
private fun DayText(day: String) {
    Text(
        text = day,
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Black,
    )
}

@Composable
private fun CalendarFooter(
    onOkClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 12.dp, top = 8.dp, end = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            onClick = onOkClick,
            shape = RoundedCornerShape(8.dp),
            colors = androidx.compose.material3.ButtonDefaults.textButtonColors(
                containerColor = Color(0xFFFCF8F8),
                contentColor = Color(0xFF1F3701),
            ),
        ) {
            Text(
                text = "OK",
                style = MaterialTheme.typography.labelLarge,
                color = Color(0xFF1F3701),
                fontWeight = FontWeight.Medium,
            )
        }
    }
}