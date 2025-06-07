package com.firebender.airbnb.ui.screen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.AirbnbNeutral10
import com.firebender.airbnb.ui.theme.AirbnbNeutral100
import com.firebender.airbnb.ui.theme.AirbnbNeutral70
import com.firebender.airbnb.ui.theme.AirbnbPrimary
import com.firebender.airbnb.ui.theme.AirbnbTheme

@Composable
fun ExploreScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AirbnbNeutral10)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp) // Space for bottom navigation
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(32.dp))
            PropertyCards()
        }

        // Map button overlay
        MapButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 140.dp)
        )

        // Bottom Navigation
        BottomNavigation(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun SearchBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(43.dp),
                ambientColor = Color.Black.copy(alpha = 0.12f)
            ),
        shape = RoundedCornerShape(43.dp),
        colors = CardDefaults.cardColors(containerColor = AirbnbNeutral10)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_outline_search_0),
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp),
                    tint = AirbnbNeutral100
                )
                Column {
                    Text(
                        text = "Manhattan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = AirbnbNeutral100
                    )
                    Text(
                        text = "Feb 13 - 14, 2023 (±1) ∙ 1 guest",
                        fontSize = 12.sp,
                        color = AirbnbNeutral70
                    )
                }
            }

            // Filter button with indicator
            Box {
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    border = androidx.compose.foundation.BorderStroke(2.dp, AirbnbNeutral100)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_outline_filter_0),
                        contentDescription = "Filter",
                        modifier = Modifier
                            .size(44.dp)
                            .padding(10.dp),
                        tint = AirbnbNeutral100
                    )
                }
                // Red indicator dot
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(AirbnbPrimary)
                        .align(Alignment.TopEnd)
                        .offset(x = (-2).dp, y = 2.dp)
                )
            }
        }
    }
}

@Composable
fun PropertyCards() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        PropertyCard(
            imageRes = R.drawable.cozy_bedroom_interior,
            title = "Private room in Yonkers",
            description = "Private room in Yonkers close to bus/train station",
            bedSize = "1 Queen Bed",
            date = "Feb 13 - 14",
            rating = "5.0 (3)",
            price = "$38",
            totalPrice = "$48 total"
        )

        PropertyCard(
            imageRes = R.drawable.modern_bedroom_interior,
            title = "Condo in Los Angeles",
            description = "Your Stylish Home Away From Home In Downtown LA!",
            bedSize = "1 Standard Bed",
            date = "Feb 19 - 20",
            rating = "4.93 (42)",
            price = "$360",
            totalPrice = "$720 total"
        )
    }
}

@Composable
fun PropertyCard(
    imageRes: Int,
    title: String,
    description: String,
    bedSize: String,
    date: String,
    rating: String,
    price: String,
    totalPrice: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Image container with overlay elements
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // Heart icon
            Icon(
                painter = painterResource(id = R.drawable.icon_outline_heart_0),
                contentDescription = "Favorite",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(24.dp),
                tint = AirbnbNeutral10
            )

            // Progress indicators
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(5) { index ->
                    Box(
                        modifier = Modifier
                            .size(if (index == 0) 8.dp else 6.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == 0) AirbnbNeutral10 else AirbnbNeutral10.copy(alpha = 0.4f)
                            )
                    )
                }
            }
        }

        // Property information
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = AirbnbNeutral100,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = AirbnbNeutral70,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = bedSize,
                    fontSize = 14.sp,
                    color = AirbnbNeutral70
                )
                Text(
                    text = date,
                    fontSize = 14.sp,
                    color = AirbnbNeutral70
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "$price night",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = AirbnbNeutral100,
                        textDecoration = TextDecoration.Underline
                    )
                    Text(
                        text = " ∙ $totalPrice",
                        fontSize = 16.sp,
                        color = AirbnbNeutral70
                    )
                }
            }

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_filled_star_0),
                    contentDescription = "Rating",
                    modifier = Modifier.size(12.dp),
                    tint = AirbnbNeutral100
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = rating,
                    fontSize = 14.sp,
                    color = AirbnbNeutral100
                )
            }
        }
    }
}

@Composable
fun MapButton(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .shadow(8.dp, RoundedCornerShape(40.dp)),
        shape = RoundedCornerShape(40.dp),
        colors = CardDefaults.cardColors(containerColor = AirbnbNeutral100)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Map",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = AirbnbNeutral10
            )
            // Map icon - using simple bars to match Figma
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(12.dp)
                        .background(AirbnbNeutral10)
                )
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(12.dp)
                        .background(AirbnbNeutral10)
                )
            }
        }
    }
}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
        colors = CardDefaults.cardColors(containerColor = AirbnbNeutral10),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = Color(0xFFD8DCE0)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomNavItem(
                iconRes = R.drawable.icon_outline_search_0,
                label = "Explore",
                isActive = true
            )
            BottomNavItem(
                iconRes = R.drawable.icon_outline_heart_0,
                label = "Wishlist",
                isActive = false,
                hasIndicator = true
            )
            BottomNavItem(
                iconRes = R.drawable.airbnb,
                label = "Trips",
                isActive = false
            )
            BottomNavItem(
                iconRes = R.drawable.icon_outline_message_0,
                label = "Inbox",
                isActive = false
            )
            BottomNavItem(
                iconRes = R.drawable.icon_outline_user_0,
                label = "Profile",
                isActive = false
            )
        }
    }
}

@Composable
fun BottomNavItem(
    iconRes: Int,
    label: String,
    isActive: Boolean,
    hasIndicator: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = if (isActive) AirbnbPrimary else AirbnbNeutral70
            )

            if (hasIndicator) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(AirbnbPrimary)
                        .align(Alignment.TopEnd)
                        .offset(x = 2.dp, y = 0.dp)
                )
            }
        }

        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (isActive) FontWeight.Medium else FontWeight.Normal,
            color = if (isActive) AirbnbPrimary else AirbnbNeutral70
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    AirbnbTheme {
        ExploreScreen()
    }
}
