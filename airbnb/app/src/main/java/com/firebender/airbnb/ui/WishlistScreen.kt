package com.firebender.airbnb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.zIndex
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.AirbnbTheme
import com.firebender.airbnb.ui.theme.Neutral10
import com.firebender.airbnb.ui.theme.Neutral40
import com.firebender.airbnb.ui.theme.Neutral70
import com.firebender.airbnb.ui.theme.Neutral100
import com.firebender.airbnb.ui.theme.Primary60
import com.firebender.airbnb.ui.theme.Primary70

data class WishlistPropertyCard(
    val title: String,
    val description: String,
    val bedSize: String,
    val dates: String,
    val distance: String,
    val rating: String,
    val pricePerNight: String,
    val totalPrice: String,
    val imageRes: Int
)

@Composable
fun WishlistScreen() {
    val properties = listOf(
        WishlistPropertyCard(
            title = "Entire home in Putnam Valley Modern luxury in woods 5B 3.5B heated",
            description = "Villa with private swimming pool",
            bedSize = "1 Queen Bed",
            dates = "Jul 2 - 7",
            distance = "1,669 kilometers",
            rating = "4.98 (61)",
            pricePerNight = "$1,700",
            totalPrice = "$10,156",
            imageRes = R.drawable.wishlist_card_picture_outdoor_house
        ),
        WishlistPropertyCard(
            title = "Abiansemal, Indonesia",
            description = "Villa with private swimming pool",
            bedSize = "1 Queen Bed",
            dates = "Jul 2 - 7",
            distance = "1,669 kilometers",
            rating = "4.87 (71)",
            pricePerNight = "$1,700",
            totalPrice = "$10,156",
            imageRes = R.drawable.wishlist_card_picture_outdoor_house
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBarSection()
            },
            bottomBar = {
                BottomNavigationBar()
            },
            containerColor = Neutral10
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header section with Nice title and tabs
                HeaderSection()

                // Property cards
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    properties.forEach { property ->
                        WishlistPropertyCardItem(property = property)
                    }

                    // Bottom padding for floating button
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }

        // Floating Map Button
        FloatingMapButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
                .zIndex(1f)
        )
    }
}

@Composable
fun TopBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(40.dp)
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
                    .background(Neutral10, RoundedCornerShape(24.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_outline_download),
                    contentDescription = "Share",
                    modifier = Modifier.size(24.dp),
                    tint = Neutral100
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(40.dp)
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
fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Nice title
        Text(
            text = "Nice",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Neutral100,
            lineHeight = 32.sp
        )

        // Tabs
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Active tab
            OutlinedButton(
                onClick = { },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Neutral100
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, Neutral100),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.height(32.dp)
            ) {
                Text(
                    text = "May 14 - 19",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Inactive tab
            OutlinedButton(
                onClick = { },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Neutral70
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, Neutral40),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.height(32.dp)
            ) {
                Text(
                    text = "Guests",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun WishlistPropertyCardItem(property: WishlistPropertyCard) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Property Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Image(
                painter = painterResource(id = property.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // SUPERHOST badge
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .background(
                        Color.White.copy(alpha = 0.9f),
                        RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = "SUPERHOST",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Neutral100
                )
            }

            // Heart icon
            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_outline_heart_1),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(24.dp),
                    tint = Primary60
                )
            }

            // Page indicators (dots)
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(5) { index ->
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(
                                if (index == 0) Neutral10 else Neutral10.copy(alpha = 0.5f),
                                RoundedCornerShape(3.dp)
                            )
                    )
                }
            }
        }

        // Property Details
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_filled_star_0),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp),
                    tint = Primary60
                )
                Text(
                    text = property.rating,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Neutral100
                )
            }

            // Title and description
            Text(
                text = property.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Neutral100,
                lineHeight = 22.sp
            )

            Text(
                text = property.description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Neutral70,
                lineHeight = 18.sp
            )

            // Bed size
            Text(
                text = property.bedSize,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Neutral70,
                lineHeight = 18.sp
            )

            // Date
            Text(
                text = property.dates,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Neutral70,
                lineHeight = 18.sp
            )

            // Distance
            Text(
                text = property.distance,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Neutral70,
                lineHeight = 18.sp
            )

            // Price
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = property.pricePerNight,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Neutral100
                    )
                    Text(
                        text = "night •",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Neutral100
                    )
                    Text(
                        text = "${property.totalPrice} total",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Neutral100
                    )
                }
                Text(
                    text = "${property.totalPrice} total",
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
fun FloatingMapButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Neutral100
        ),
        shape = RoundedCornerShape(40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Map",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Neutral10
            )
            Icon(
                painter = painterResource(id = R.drawable.icon_filled_maps),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Neutral10
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
    ) {
        // Top border
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
                icon = R.drawable.icon_outline_search_1,
                label = "Explore",
                isSelected = false
            )
            NavigationItem(
                icon = R.drawable.icon_outline_heart_3,
                label = "Wishlist",
                isSelected = true
            )
            NavigationItem(
                icon = R.drawable.airbnb_logo,
                label = "Trips",
                isSelected = false
            )
            NavigationItem(
                icon = R.drawable.icon_outline_message,
                label = "Inbox",
                isSelected = false,
                showIndicator = true
            )
            NavigationItem(
                icon = R.drawable.icon_outline_user,
                label = "Profile",
                isSelected = false
            )
        }
    }
}

@Composable
fun NavigationItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    showIndicator: Boolean = false
) {
    Column(
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
                        .background(Primary70, RoundedCornerShape(4.dp))
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
