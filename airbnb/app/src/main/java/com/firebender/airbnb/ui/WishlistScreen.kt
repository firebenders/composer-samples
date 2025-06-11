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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.AirbnbTheme
import com.firebender.airbnb.ui.theme.Neutral10
import com.firebender.airbnb.ui.theme.Neutral40
import com.firebender.airbnb.ui.theme.Neutral70
import com.firebender.airbnb.ui.theme.Neutral100
import com.firebender.airbnb.ui.theme.Primary70

data class WishlistItem(
    val title: String,
    val date: String?,
    val imageRes: Int,
    val showDate: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen() {
    val wishlistItems = listOf(
        WishlistItem(
            title = "Nice",
            date = "May 14 - 19, 2023",
            imageRes = R.drawable.cozy_bedroom_interior_0,
            showDate = true
        ),
        WishlistItem(
            title = "Chill",
            date = "May 14 - 19, 2023",
            imageRes = R.drawable.poolside_garden_patio,
            showDate = false
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                // Status Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .background(Neutral10)
                )

                // Top Bar
                TopAppBar(
                    title = {
                        Text(
                            text = "Wishlist",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Medium,
                            color = Neutral100,
                            lineHeight = 42.sp
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Neutral10
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Neutral10)
            ) {
                // Top border only
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Neutral40)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NavigationItem(
                        icon = R.drawable.icon_outline_search,
                        label = "Explore",
                        isSelected = false,
                        modifier = Modifier.weight(1f)
                    )
                    NavigationItem(
                        icon = R.drawable.icon_outline_heart,
                        label = "Wishlist",
                        isSelected = true,
                        modifier = Modifier.weight(1f)
                    )
                    NavigationItem(
                        icon = R.drawable.airbnb,
                        label = "Trips",
                        isSelected = false,
                        modifier = Modifier.weight(1f)
                    )
                    NavigationItem(
                        icon = R.drawable.icon_outline_message,
                        label = "Inbox",
                        isSelected = false,
                        showIndicator = true,
                        modifier = Modifier.weight(1f)
                    )
                    NavigationItem(
                        icon = R.drawable.icon_outline_user,
                        label = "Profile",
                        isSelected = false,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        containerColor = Neutral10
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            wishlistItems.forEach { item ->
                WishlistStackItem(item = item)
            }
        }
    }
}

@Composable
fun WishlistStackItem(item: WishlistItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Picture
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        // Text Content
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Neutral100,
                lineHeight = 22.sp
            )

            if (item.showDate && item.date != null) {
                Text(
                    text = item.date,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Neutral70,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
fun NavigationItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    showIndicator: Boolean = false
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = if (isSelected) Primary70 else Neutral70
            )

            if (showIndicator) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Primary70)
                        .align(Alignment.TopEnd)
                )
            }
        }

        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
            color = if (isSelected) Primary70 else Neutral70,
            lineHeight = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WishlistScreenPreview() {
    AirbnbTheme {
        WishlistScreen()
    }
}
