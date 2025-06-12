package com.firebender.duolingo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.duolingo.ui.theme.DuolingoGreen
import com.firebender.duolingo.ui.theme.DuolingoTheme
import com.firebender.duolingo.ui.theme.White

// Data class for lesson options
data class LessonOption(
    val id: Int,
    val imageRes: Int,
    val label: String
)

@Composable
fun LessonScreen() {
    var selectedOptionId by remember { mutableStateOf(0) } // First option selected by default

    val lessonOptions = listOf(
        LessonOption(0, R.drawable.icon_2_0, "one"),
        LessonOption(1, R.drawable.icon_1, "the man"),
        LessonOption(2, R.drawable.illustration_1, "the cat"),
        LessonOption(3, R.drawable.icon_1_0, "the boy")
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = androidx.compose.ui.graphics.Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Top Navigation Bar with Progress
                TopNavigationBarWithProgress()

                // Content Area
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // NEW WORD Badge
                    NewWordBadge()

                    // Instruction and Controls
                    InstructionSection()

                    // Options Grid
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(18.dp),
                        verticalArrangement = Arrangement.spacedBy(18.dp),
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        itemsIndexed(lessonOptions) { index, option ->
                            LessonOptionCard(
                                option = option,
                                isSelected = option.id == selectedOptionId,
                                onClick = { selectedOptionId = option.id }
                            )
                        }
                    }
                }
            }

            // Bottom Button Layout
            BottomButtonLayout(
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun TopNavigationBarWithProgress() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Close button
        Icon(
            painter = painterResource(id = R.drawable.close_icon),
            contentDescription = "Close",
            tint = Color(0xFFB0ADB1),
            modifier = Modifier.size(24.dp)
        )

        // Progress Bar
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circular start of progress bar
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        Color(0xFF5CCF04),
                        shape = RoundedCornerShape(6.dp)
                    )
            )

            // Progress track
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .background(
                        Color(0xFFE7E5E7),
                        shape = RoundedCornerShape(100.dp) // Make it pill-shaped
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.20f) // Show 20% progress
                        .height(8.dp)
                        .background(
                            Color(0xFF5CCF04),
                            shape = RoundedCornerShape(100.dp) // Make it pill-shaped
                        )
                )
            }
        }
    }
}

@Composable
fun NewWordBadge() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Badge icon - circular background with sparkle/star
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(
                    Color(0xFFCE82FF),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "✦",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }

        Text(
            text = "NEW WORD",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            ),
            color = Color(0xFFCE82FF)
        )
    }
}

@Composable
fun InstructionSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        Text(
            text = "Select the correct image",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 28.sp
            ),
            color = Color(0xFF4B4B4B)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sound button with circular blue background
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        Color(0xFF1CB0F6),
                        shape = RoundedCornerShape(20.dp) // Make it circular
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.talk_icon),
                    contentDescription = "Play sound",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Spelling text
            Text(
                text = "un",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color(0xFF4B4B4B)
            )
        }
    }
}

@Composable
fun LessonOptionCard(
    option: LessonOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .shadow(
                elevation = if (isSelected) 0.dp else 2.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = if (isSelected) Color(0xFF77D0FA) else Color(0xFFEBEBEB)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFE1F4FF) else Color.White
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 2.dp,
            color = if (isSelected) Color(0xFF77D0FA) else Color(0xFFEBEBEB)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Increased padding
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Increased spacing
        ) {
            // Image
            Box(
                modifier = Modifier
                    .size(120.dp), // Increased image size
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = option.imageRes),
                    contentDescription = option.label,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Label
            Text(
                text = option.label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold
                ),
                color = if (isSelected) Color(0xFF1CB0F6) else Color(0xFF4B4B4B)
            )
        }
    }
}

@Composable
fun BottomButtonLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Button(
            onClick = { /* TODO: Handle check */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                    spotColor = Color(0xFF5AA703)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF58CC02)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "CHECK",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LessonScreenPreview() {
    DuolingoTheme {
        LessonScreen()
    }
}
