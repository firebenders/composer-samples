package com.firebender.duolingo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.duolingo.ui.theme.DuolingoTheme
import com.firebender.duolingo.ui.theme.White

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top Navigation Bar
            TopHomeNavigationBar()

            // Scrollable Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    // Main Section
                    SectionCard()
                }

                item {
                    // Start Button
                    StartButton()
                }

                item {
                    // Level progress visualization
                    LevelsSection()
                }
            }
        }
    }
}

@Composable
fun TopHomeNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left: Profile image
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    Color(0xFF1CB0F6),
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 2.dp,
                    color = Color(0xFF1890CB),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "KL",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }

        // Right: Stats
        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Lightning icon with streak count
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            Color(0xFFFFB800),
                            shape = RoundedCornerShape(2.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "1",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                }
                Text(
                    text = "505",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = Color(0xFF4B4B4B)
                )
            }

            // Heart icon with lives count
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            Color(0xFFFF4B4B),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    // Heart shape indicator
                }
                Text(
                    text = "5",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = Color(0xFF4B4B4B)
                )
            }
        }
    }
}

@Composable
fun SectionCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(14.dp),
                spotColor = Color(0xFF485A05)
            ),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            // Left: Text content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        Color(0xFF5ACD05),
                        shape = RoundedCornerShape(topStart = 14.dp, bottomStart = 14.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = Color(0xFF42A501),
                        shape = RoundedCornerShape(topStart = 14.dp, bottomStart = 14.dp)
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "SECTION 1, UNIT 1",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 22.sp
                    ),
                    color = Color(0xFFCEF2AD)
                )
                Text(
                    text = "Use basic phrases",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 24.sp
                    ),
                    color = Color(0xFFFFFE).copy(alpha = 0.996f)
                )
            }

            // Right: Guidebook icon
            Column(
                modifier = Modifier
                    .background(
                        Color(0xFF5ACD05),
                        shape = RoundedCornerShape(topEnd = 14.dp, bottomEnd = 14.dp)
                    )
                    .padding(15.dp)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_2_1),
                    contentDescription = "Guidebook",
                    modifier = Modifier.size(22.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun StartButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(14.dp)
            )
            .border(
                width = 2.dp,
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(14.dp)
            )
            .clip(RoundedCornerShape(14.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "START",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            ),
            color = Color(0xFF5ACD05)
        )
    }
}

@Composable
fun LevelsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        // First level (in progress) - Right side with star character
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .size(74.dp)
                        .background(
                            Color(0xFF5ACD05),
                            shape = CircleShape
                        )
                        .shadow(
                            elevation = 8.dp,
                            shape = CircleShape,
                            spotColor = Color(0xFF5ACD05).copy(alpha = 0.2f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // White star icon
                    Text(
                        text = "★",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                }

                // Progress arc on top
                Box(
                    modifier = Modifier
                        .size(84.dp)
                        .offset(x = (-5).dp, y = (-5).dp)
                        .background(
                            Color.Transparent,
                            shape = CircleShape
                        )
                        .border(
                            width = 4.dp,
                            color = Color(0xFFFFD700),
                            shape = CircleShape
                        )
                )
            }
        }

        // Second level (locked) - Center with lock icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(74.dp)
                    .background(
                        Color(0xFFE5E5E5),
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        spotColor = Color(0xFFE5E5E5).copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Lock icon
                Text(
                    text = "🔒",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 20.sp
                    )
                )
            }
        }

        // Third level (locked) - Left side with treasure chest
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .background(
                        Color(0xFFE5E5E5),
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        spotColor = Color(0xFFE5E5E5).copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mask_group_1),
                    contentDescription = "Treasure chest",
                    modifier = Modifier.size(26.dp)
                )
            }
        }

        // Fourth level (locked) - Right side with green character
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .background(
                        Color(0xFFE5E5E5),
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        spotColor = Color(0xFFE5E5E5).copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_1),
                    contentDescription = "Character",
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        // Fifth level (locked) - Center with book icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .background(
                        Color(0xFFE5E5E5),
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        spotColor = Color(0xFFE5E5E5).copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Lock icon
                Text(
                    text = "🔒",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 20.sp
                    )
                )
            }
        }

        // Sixth level (locked) - Left side with trophy
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .background(
                        Color(0xFFE5E5E5),
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        spotColor = Color(0xFFE5E5E5).copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Lock icon
                Text(
                    text = "🔒",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 20.sp
                    )
                )
            }
        }

        // Seventh level (locked) - Right side with treasure chest
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .background(
                        Color(0xFFE5E5E5),
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        spotColor = Color(0xFFE5E5E5).copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mask_group_1),
                    contentDescription = "Treasure chest",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun LevelCircle(
    isActive: Boolean,
    isCompleted: Boolean,
    levelIcon: Int
) {
    Box(
        modifier = Modifier
            .size(74.dp)
            .background(
                if (isActive || isCompleted) Color(0xFF5ACD05) else Color(0xFFE5E5E5),
                shape = CircleShape
            )
            .shadow(
                elevation = 8.dp,
                shape = CircleShape,
                spotColor = if (isActive || isCompleted) Color(0xFF5ACD05).copy(alpha = 0.2f) else Color(
                    0xFFE5E5E5
                ).copy(alpha = 0.2f)
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = levelIcon),
            contentDescription = "Level",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier
) {
    // Bottom navigation bar is removed to match the target design
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DuolingoTheme {
        HomeScreen()
    }
}
