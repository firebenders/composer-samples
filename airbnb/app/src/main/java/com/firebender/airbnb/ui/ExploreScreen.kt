package com.firebender.airbnb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.*

data class CategoryItem(
    val id: String,
    val label: String,
    val iconRes: Int,
    val isActive: Boolean = false
)

data class PropertyCard(
    val id: String,
    val imageRes: Int,
    val title: String,
    val description: String,
    val bedSize: String,
    val dates: String,
    val rating: String,
    val price: String,
    val priceTotal: String
)

@Composable
fun ExploreScreen() {
    val properties = listOf(
        PropertyCard(
            id = "1",
            imageRes = R.drawable.cozy_bedroom_interior,
            title = "Private room in Yonkers",
            description = "Private room in  Yonkers close to bus/train station",
            bedSize = "1 Queen Bed",
            dates = "Feb 13 - 14",
            rating = "5.0 (3)",
            price = "$38",
            priceTotal = "$48 total"
        ),
        PropertyCard(
            id = "2",
            imageRes = R.drawable.explore_card_bedroom_picture,
            title = "Condo in Los Angeles",
            description = "Your Stylish Home Away From Home In Downtown LA!",
            bedSize = "1 Standard Bed",
            dates = "Feb 19 - 20",
            rating = "4.93 (42)",
            price = "$38",
            priceTotal = "$48 total"
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutral10)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Search Bar
            SearchBarSection()

            // Property Cards
            PropertyCardsSection(properties)

            Spacer(modifier = Modifier.height(100.dp))
        }

        // Map Button (floating)
        MapButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-104).dp)
        )

        // Navigation Bar (bottom)
        NavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            activeTab = "Inbox"
        )
    }
}

@Composable
fun SearchBarSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .shadow(
                elevation = 2.dp,
                spotColor = Color.Black.copy(alpha = 0.12f)
            )
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Neutral20, RoundedCornerShape(43.dp))
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_outline_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp),
                    tint = Neutral100
                )

                Column {
                    Text(
                        text = "Manhattan",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Neutral100,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Feb 13 - 14, 2023 (±1) ∙ 1 guest",
                        style = MaterialTheme.typography.labelSmall,
                        color = Neutral70
                    )
                }
            }

            // Filter button with indicator
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .border(1.dp, Neutral100, CircleShape)
                    .background(Neutral10, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box {
                    Icon(
                        painter = painterResource(R.drawable.icon_outline_filter),
                        contentDescription = "Filter",
                        modifier = Modifier.size(16.dp),
                        tint = Neutral100
                    )
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(Primary70, CircleShape)
                            .offset(x = 6.dp, y = (-6).dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PropertyCardsSection(properties: List<PropertyCard>) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        properties.forEach { property ->
            PropertyCardItem(property)
        }
    }
}

@Composable
fun PropertyCardItem(property: PropertyCard) {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Image with heart and progress indicators
        Box {
            Image(
                painter = painterResource(property.imageRes),
                contentDescription = property.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // Heart icon
            Icon(
                painter = painterResource(R.drawable.icon_outline_heart),
                contentDescription = "Favorite",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(24.dp),
                tint = Neutral10
            )

            // Progress indicators
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(5) { index ->
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(
                                if (index == 0) Neutral10 else Neutral10.copy(alpha = 0.5f),
                                CircleShape
                            )
                    )
                }
            }
        }

        // Property details
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = property.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Neutral100,
                    fontWeight = FontWeight.Medium
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_filled_star),
                        contentDescription = "Rating",
                        modifier = Modifier.size(12.dp),
                        tint = Neutral100
                    )
                    Text(
                        text = property.rating,
                        style = MaterialTheme.typography.labelSmall,
                        color = Neutral100
                    )
                }
            }

            Text(
                text = property.description,
                style = MaterialTheme.typography.labelSmall,
                color = Neutral70
            )

            Text(
                text = property.bedSize,
                style = MaterialTheme.typography.labelSmall,
                color = Neutral70
            )

            Text(
                text = property.dates,
                style = MaterialTheme.typography.labelSmall,
                color = Neutral70
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Row {
                        Text(
                            text = property.price,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Neutral100,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = " night",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Neutral100
                        )
                    }
                    Text(
                        text = property.priceTotal,
                        style = MaterialTheme.typography.labelSmall.copy(
                            textDecoration = TextDecoration.Underline
                        ),
                        color = Neutral70
                    )
                }
            }
        }
    }
}

@Composable
fun MapButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Neutral100,
            contentColor = Neutral10
        ),
        shape = RoundedCornerShape(40.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Map",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Icon(
                painter = painterResource(R.drawable.icon_filled_maps),
                contentDescription = "Map",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun NavigationBar(modifier: Modifier = Modifier, activeTab: String = "Explore") {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Neutral10,
        shadowElevation = 0.dp
    ) {
        Column {
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
                    "Explore",
                    R.drawable.icon_outline_search_0,
                    isActive = activeTab == "Explore"
                )
                NavigationItem(
                    "Wishlist",
                    R.drawable.icon_outline_heart_0,
                    hasIndicator = true,
                    isActive = activeTab == "Wishlist"
                )
                NavigationItem(
                    "Trips",
                    R.drawable.airbnb,
                    hasIndicator = false,
                    isActive = activeTab == "Trips"
                )
                NavigationItem(
                    "Inbox",
                    R.drawable.icon_outline_message,
                    hasIndicator = activeTab == "Inbox",
                    isActive = activeTab == "Inbox"
                )
                NavigationItem(
                    "Profile",
                    R.drawable.icon_outline_user,
                    hasIndicator = false,
                    isActive = activeTab == "Profile"
                )
            }
        }
    }
}

@Composable
fun NavigationItem(
    label: String,
    iconRes: Int,
    isActive: Boolean = false,
    hasIndicator: Boolean = false
) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = if (isActive) Primary70 else Neutral70
            )

            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = if (isActive) Primary70 else Neutral70,
                fontWeight = if (isActive) FontWeight.Medium else FontWeight.Normal
            )
        }

        if (hasIndicator) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 4.dp, y = (-2).dp)
                    .size(8.dp)
                    .background(Primary70, CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    AirbnbTheme {
        ExploreScreen()
    }
}
