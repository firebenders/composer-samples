package com.firebender.airbnb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.zIndex
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.AirbnbTheme
import com.firebender.airbnb.ui.theme.Neutral10
import com.firebender.airbnb.ui.theme.Neutral40
import com.firebender.airbnb.ui.theme.Neutral100
import com.firebender.airbnb.ui.theme.Primary70

@Composable
fun WishlistMapScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background map image
        Image(
            painter = painterResource(id = R.drawable.north_america_map),
            contentDescription = "Map",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Top bar with navigation buttons
        TopBar(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(2f)
        )

        // Floating price tags on the map
        PriceOverlays(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
        )

        // Add button in the center
        AddButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-20).dp, y = 120.dp)
                .zIndex(3f)
        )

        // Bottom sheet with "Nice" header
        BottomSheet(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(4f)
        )
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(40.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp))
                .background(Neutral10, RoundedCornerShape(24.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_outline_arrow_left),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
                tint = Neutral100
            )
        }

        // Right side buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(40.dp)
                    .shadow(4.dp, RoundedCornerShape(24.dp))
                    .background(Neutral10, RoundedCornerShape(24.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_outline_download),
                    contentDescription = "Download",
                    modifier = Modifier.size(24.dp),
                    tint = Neutral100
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(40.dp)
                    .shadow(4.dp, RoundedCornerShape(24.dp))
                    .background(Neutral10, RoundedCornerShape(24.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_outline_menu),
                    contentDescription = "Menu",
                    modifier = Modifier.size(24.dp),
                    tint = Neutral100
                )
            }
        }
    }
}

@Composable
fun PriceOverlays(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        // Price tag 1 - $447 (left side, middle)
        PriceTag(
            price = "$447",
            modifier = Modifier
                .offset(x = 60.dp, y = 300.dp)
        )

        // Price tag 2 - $447 (bottom left)
        PriceTag(
            price = "$447",
            modifier = Modifier
                .offset(x = 80.dp, y = 440.dp)
        )

        // Price tag 3 - $308 (top right)
        PriceTag(
            price = "$308",
            modifier = Modifier
                .offset(x = 290.dp, y = 240.dp)
        )

        // Price tag 4 - $447 (center)
        PriceTag(
            price = "$447",
            modifier = Modifier
                .offset(x = 180.dp, y = 360.dp)
        )

        // Price tag 5 - Heart icon only (center left)
        HeartOnlyTag(
            modifier = Modifier
                .offset(x = 120.dp, y = 380.dp)
        )

        // Price tag 6 - $1,700 (dark background, center)
        PriceTag(
            price = "$1,700",
            isDark = true,
            modifier = Modifier
                .offset(x = 240.dp, y = 320.dp)
        )

        // Price tag 7 - Blocked heart icon (right side)
        BlockedHeartTag(
            modifier = Modifier
                .offset(x = 320.dp, y = 300.dp)
        )
    }
}

@Composable
fun PriceTag(
    price: String,
    isDark: Boolean = false,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        color = if (isDark) Neutral100 else Neutral10
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = price,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDark) Neutral10 else Neutral100
            )
            Icon(
                painter = painterResource(id = R.drawable.icon_outline_heart_2),
                contentDescription = "Favorite",
                modifier = Modifier.size(16.dp),
                tint = if (isDark) Neutral10 else Primary70
            )
        }
    }
}

@Composable
fun HeartOnlyTag(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        color = Neutral10
    ) {
        Box(
            modifier = Modifier
                .padding(6.dp)
                .size(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_outline_heart_2),
                contentDescription = "Favorite",
                modifier = Modifier.size(16.dp),
                tint = Primary70
            )
        }
    }
}

@Composable
fun BlockedHeartTag(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        color = Neutral10
    ) {
        Box(
            modifier = Modifier
                .padding(6.dp)
                .size(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_outline_block),
                contentDescription = "Blocked",
                modifier = Modifier.size(16.dp),
                tint = Primary70
            )
        }
    }
}

@Composable
fun AddButton(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        color = Neutral10
    ) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_filled_add),
                contentDescription = "Add",
                modifier = Modifier.size(24.dp),
                tint = Neutral100
            )
        }
    }
}

@Composable
fun BottomSheet(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        color = Neutral10
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 34.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header section with gray pill and Nice text
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Gray pill indicator
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(4.dp)
                        .background(Neutral40, RoundedCornerShape(2.dp))
                )

                // Nice text
                Text(
                    text = "Nice",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Neutral100
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WishlistMapScreenPreview() {
    AirbnbTheme {
        WishlistMapScreen()
    }
}
