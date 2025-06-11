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
    val distance: String,
    val dates: String,
    val rating: String,
    val price: String
)

@Composable
fun ExploreScreen() {
    val categories = listOf(
        CategoryItem("omg", "OMG!", R.drawable.icon_outline_u_f_o, true),
        CategoryItem("beach", "Beach", R.drawable.icon_outline_beach),
        CategoryItem("pools", "Amazing pools", R.drawable.icon_outline_pool),
        CategoryItem("islands", "Islands", R.drawable.icon_outline_island),
        CategoryItem("arctic", "Arctic", R.drawable.icon_outline_arctic)
    )

    val properties = listOf(
        PropertyCard(
            id = "1",
            imageRes = R.drawable.poolside_garden_patio,
            title = "Abiansemal, Indonesia",
            distance = "1,669 kilometers",
            dates = "Jul 2 - 7",
            rating = "4.87 (71)",
            price = "$360 night"
        ),
        PropertyCard(
            id = "2",
            imageRes = R.drawable.modern_house_poolside_view,
            title = "Kintamani, Indonesia",
            distance = "971 kilometers",
            dates = "Jul 6 - 11",
            rating = "4.91 (89)",
            price = "$360 night"
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

            Spacer(modifier = Modifier.height(17.dp))

            // Categories
            CategoriesSection(categories)

            Spacer(modifier = Modifier.height(17.dp))

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
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun SearchBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Main search area
        Row(
            modifier = Modifier
                .weight(1f)
                .shadow(
                    16.dp,
                    RoundedCornerShape(43.dp),
                    spotColor = Color.Black.copy(alpha = 0.12f)
                )
                .background(Neutral10, RoundedCornerShape(43.dp))
                .border(0.5.dp, Neutral40, RoundedCornerShape(43.dp))
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_outline_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp),
                    tint = Neutral100
                )

                Column {
                    Text(
                        text = "Where to?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Neutral100,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Anywhere • Any week • Add guests",
                        style = MaterialTheme.typography.labelSmall,
                        color = Neutral70
                    )
                }
            }

            // Filter button
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .border(0.5.dp, Neutral40, CircleShape)
                    .background(Neutral10, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_outline_filter),
                    contentDescription = "Filter",
                    modifier = Modifier.size(16.dp),
                    tint = Neutral100
                )
            }
        }
    }
}

@Composable
fun CategoriesSection(categories: List<CategoryItem>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(categories) { category ->
                CategoryChip(category)
            }
        }

        // Bottom border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Neutral20)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun CategoryChip(category: CategoryItem) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painterResource(category.iconRes),
            contentDescription = category.label,
            modifier = Modifier.size(24.dp),
            tint = if (category.isActive) Neutral100 else Neutral70
        )

        Text(
            text = category.label,
            style = MaterialTheme.typography.bodySmall,
            color = if (category.isActive) Neutral100 else Neutral70,
            fontWeight = FontWeight.Medium
        )

        // Active indicator line
        if (category.isActive) {
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .height(2.dp)
                    .background(Neutral100)
            )
        } else {
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Composable
fun PropertyCardsSection(properties: List<PropertyCard>) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
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
                    text = property.distance,
                    style = MaterialTheme.typography.labelSmall,
                    color = Neutral70
                )

                Text(
                    text = property.dates,
                    style = MaterialTheme.typography.labelSmall,
                    color = Neutral70
                )

                Text(
                    text = property.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Neutral100,
                    fontWeight = FontWeight.Medium
                )
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
fun NavigationBar(modifier: Modifier = Modifier) {
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
                NavigationItem("Explore", R.drawable.icon_outline_search, isActive = true)
                NavigationItem("Wishlist", R.drawable.icon_outline_heart, hasIndicator = false)
                NavigationItem("Trips", R.drawable.airbnb, hasIndicator = false)
                NavigationItem("Inbox", R.drawable.icon_outline_message, hasIndicator = true)
                NavigationItem("Profile", R.drawable.icon_outline_user, hasIndicator = false)
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
