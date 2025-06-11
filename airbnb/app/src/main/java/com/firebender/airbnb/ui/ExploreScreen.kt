package com.firebender.airbnb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.blur
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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
    val price: String,
    val priceTotal: String
)

data class SearchDestination(
    val id: String,
    val title: String,
    val imageRes: Int,
    val isFlexible: Boolean = false
)

@Composable
fun ExploreScreen() {
    var showSearchModal by remember { mutableStateOf(true) }

    val categories = listOf(
        CategoryItem("cabins", "Cabins", R.drawable.cabin_icon, true),
        CategoryItem("rooms", "Rooms", R.drawable.room_icon),
        CategoryItem("amazing_views", "Amazing views", R.drawable.view_2_icon),
        CategoryItem("beachfront", "Beachfront", R.drawable.beach_2_icon),
        CategoryItem("caves", "Caves", R.drawable.caves_icon)
    )

    val properties = listOf(
        PropertyCard(
            id = "1",
            imageRes = R.drawable.hotel_room_living_area,
            title = "Maidstone, Kent, UK",
            distance = "34 miles away",
            dates = "Aug 31 - Sep 5",
            rating = "4.96",
            price = "$1,031",
            priceTotal = "$1,031 total"
        ),
        PropertyCard(
            id = "2",
            imageRes = R.drawable.hotel_room_living_area_0,
            title = "Hertfordshire, UK",
            distance = "42 miles away",
            dates = "Aug 31 - Sep 5",
            rating = "4.98",
            price = "$38",
            priceTotal = "$48 total"
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content (blurred when modal is shown)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutral10)
                .blur(if (showSearchModal) 100.dp else 0.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Status Bar (simulated)
            Spacer(modifier = Modifier.height(44.dp))

            // Search Bar
            SearchBarSection(onSearchClick = { showSearchModal = true })

            // Categories
            CategoriesSection(categories)

            // Property Cards
            PropertyCardsSection(properties)

            Spacer(modifier = Modifier.height(100.dp))
        }

        // Map Button (floating)
        if (!showSearchModal) {
            MapButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-104).dp)
            )
        }

        // Navigation Bar (bottom)
        if (!showSearchModal) {
            NavigationBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                activeTab = "Explore"
            )
        }

        // Search Modal Overlay
        if (showSearchModal) {
            SearchModal(
                onDismiss = { showSearchModal = false },
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
            )
        }
    }
}

@Composable
fun SearchModal(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val destinations = listOf(
        SearchDestination("flexible", "I'm flexible", R.drawable.hotel_search_map_preview, true),
        SearchDestination("us", "United States", R.drawable.search_hotel_map_preview),
        SearchDestination("japan", "Japan", R.drawable.search_hotel_map_japan)
    )

    Column(
        modifier = modifier
            .background(Color.White.copy(alpha = 0.6f))
            .fillMaxSize()
    ) {
        // Status Bar
        Spacer(modifier = Modifier.height(44.dp))

        // Top Bar with Close and Tabs
        SearchTopBar(onClose = onDismiss)

        // Main Search Content Container
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Search Card
                SearchCard(destinations = destinations)

                // When Field
                SearchField(
                    label = "When",
                    value = "Any week"
                )

                // who Field
                SearchField(
                    label = "Who",
                    value = "Add guests"
                )
            }
        }

        // Bottom Section with Clear All and Search Button
        SearchBottomSection()
    }
}

@Composable
fun SearchTopBar(onClose: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Close Button
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.White, CircleShape)
                .border(1.dp, Neutral40, CircleShape)
                .clickable { onClose() }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.close_icon),
                contentDescription = "Close",
                modifier = Modifier.size(24.dp),
                tint = Neutral100
            )
        }

        // Tabs
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TabItem("Stays", isActive = true)
            TabItem("Experiences", isActive = false)
        }
    }
}

@Composable
fun TabItem(text: String, isActive: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (isActive) FontWeight.Medium else FontWeight.Normal,
                fontSize = 16.sp
            ),
            color = if (isActive) Neutral100 else Neutral70
        )

        if (isActive) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(2.dp)
                    .background(Neutral100)
            )
        }
    }
}

@Composable
fun SearchCard(destinations: List<SearchDestination>) {
    Card(
        modifier = Modifier
            .width(334.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Title
            Text(
                text = "Where to?",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                ),
                color = Neutral100
            )

            // Search Field
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Neutral20, RoundedCornerShape(18.dp))
                    .border(1.dp, Neutral40, RoundedCornerShape(18.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.search_icon),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp),
                    tint = Neutral70
                )
                Text(
                    text = "Search destinations",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Neutral70
                )
            }

            // Destination Options Grid
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 0.dp)
            ) {
                items(destinations.take(3)) { destination ->
                    DestinationCard(destination)
                }
            }
        }
    }
}

@Composable
fun DestinationCard(destination: SearchDestination) {
    Column(
        modifier = Modifier.width(88.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = if (destination.isFlexible) 2.dp else 0.dp,
                    color = if (destination.isFlexible) Neutral100 else Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Image(
                painter = painterResource(destination.imageRes),
                contentDescription = destination.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = destination.title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            ),
            color = Neutral100
        )
    }
}

@Composable
fun SearchField(label: String, value: String) {
    Row(
        modifier = Modifier
            .width(334.dp)
            .background(Color.White, RoundedCornerShape(18.dp))
            .border(1.dp, Neutral40, RoundedCornerShape(18.dp))
            .shadow(8.dp, RoundedCornerShape(18.dp))
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            color = Neutral100
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Neutral70
        )
    }
}

@Composable
fun SearchBottomSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Clear all",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline
            ),
            color = Neutral100
        )

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary60
            ),
            shape = RoundedCornerShape(24.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.search_icon),
                    contentDescription = "Search",
                    modifier = Modifier.size(16.dp),
                    tint = Color.White
                )
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    ),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun SearchBarSection(onSearchClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(43.dp),
                    spotColor = Color.Black.copy(alpha = 0.12f)
                )
                .background(Neutral10, RoundedCornerShape(43.dp))
                .border(0.5.dp, Neutral40, RoundedCornerShape(43.dp))
                .clickable { onSearchClick() }
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(R.drawable.search_icon),
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp),
                    tint = Neutral100
                )

                Column {
                    Text(
                        text = "Where to?",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        color = Neutral100
                    )
                    Text(
                        text = "Anywhere ∙ Any week ∙ Add guests",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 12.sp
                        ),
                        color = Neutral70
                    )
                }
            }

            // Filter button
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .border(0.5.dp, Neutral40, CircleShape)
                    .background(Neutral10, CircleShape)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.filter_icon),
                    contentDescription = "Filter",
                    modifier = Modifier.size(24.dp),
                    tint = Neutral100
                )
            }
        }
    }
}

@Composable
fun CategoriesSection(categories: List<CategoryItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(categories) { category ->
                CategoryItem(category)
            }
        }

        // Bottom border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFF7F7F7))
        )
    }
}

@Composable
fun CategoryItem(category: CategoryItem) {
    Column(
        modifier = Modifier
            .padding(8.dp),
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
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            ),
            color = if (category.isActive) Neutral100 else Neutral70
        )

        // Active indicator
        if (category.isActive) {
            Box(
                modifier = Modifier
                    .width(40.dp)
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = property.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        color = Neutral100,
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.star_filled_icon),
                            contentDescription = "Rating",
                            modifier = Modifier.size(12.dp),
                            tint = Neutral100
                        )
                        Text(
                            text = property.rating,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 12.sp
                            ),
                            color = Neutral100
                        )
                    }
                }

                Text(
                    text = property.distance,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 12.sp
                    ),
                    color = Neutral70
                )

                Text(
                    text = property.dates,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 12.sp
                    ),
                    color = Neutral70
                )

                if (property.id == "1") {
                    Text(
                        text = property.priceTotal,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        color = Neutral100
                    )
                } else {
                    Text(
                        text = "${property.price} night • ${property.priceTotal}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        color = Neutral100
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
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                color = Neutral10
            )
            Icon(
                painter = painterResource(R.drawable.maps_filled_icon),
                contentDescription = "Map",
                modifier = Modifier.size(16.dp),
                tint = Neutral10
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
                    isActive = activeTab == "Wishlist"
                )
                NavigationItem(
                    "Trips",
                    R.drawable.airbnb,
                    isActive = activeTab == "Trips"
                )
                NavigationItem(
                    "Inbox",
                    R.drawable.icon_outline_message,
                    isActive = activeTab == "Inbox"
                )
                NavigationItem(
                    "Profile",
                    R.drawable.icon_outline_user,
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
    isActive: Boolean = false
) {
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
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 12.sp,
                fontWeight = if (isActive) FontWeight.Medium else FontWeight.Normal
            ),
            color = if (isActive) Primary70 else Neutral70
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
