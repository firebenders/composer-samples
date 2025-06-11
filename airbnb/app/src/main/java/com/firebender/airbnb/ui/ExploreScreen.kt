package com.firebender.airbnb.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

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

enum class SearchStep {
    LOCATION,
    DATES,
    GUESTS
}

@Composable
fun ExploreScreen() {
    var showSearchOverlay by remember { mutableStateOf(true) } // Start with overlay showing to match Figma

    val categories = listOf(
        CategoryItem("cabins", "Cabins", R.drawable.cabin_icon_0, true),
        CategoryItem("rooms", "Rooms", R.drawable.room_icon_0),
        CategoryItem("amazing_views", "Amazing views", R.drawable.view_2_icon_0),
        CategoryItem("beachfront", "Beachfront", R.drawable.beach_2_icon_0),
        CategoryItem("caves", "Caves", R.drawable.caves_icon_0)
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
        // Main content - blurred when overlay is showing
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (showSearchOverlay) {
                        Modifier.blur(100.dp, BlurredEdgeTreatment.Unbounded)
                    } else {
                        Modifier
                    }
                )
                .background(Neutral10)
                .verticalScroll(rememberScrollState())
        ) {
            // Status Bar with background
            StatusBarSection()

            // Search Bar
            SearchBarSection(onSearchClick = {
                showSearchOverlay = true
            })

            // Categories
            CategoriesSection(categories)

            // Property Cards
            PropertyCardsSection(properties)

            Spacer(modifier = Modifier.height(100.dp))
        }

        // Semi-transparent overlay
        if (showSearchOverlay) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Neutral10.copy(alpha = 0.6f))
                    .zIndex(5f)
            )
        }

        // Map Button (floating)
        if (!showSearchOverlay) {
            MapButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-104).dp)
            )
        }

        // Navigation Bar (bottom)
        NavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            activeTab = "Explore"
        )

        // Search Overlay matching Figma design
        if (showSearchOverlay) {
            SearchOverlayMatchingFigma(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(10f),
                onDismiss = { showSearchOverlay = false }
            )
        }
    }
}

@Composable
fun StatusBarSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(Neutral10)
    )
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
                .background(Neutral20)
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
                            painter = painterResource(R.drawable.icon_filled_star),
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
        shape = RoundedCornerShape(8.dp),
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
                painter = painterResource(R.drawable.icon_filled_maps),
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
                    R.drawable.icon_outline_search_3,
                    isActive = activeTab == "Explore"
                )
                NavigationItem(
                    "Wishlist",
                    R.drawable.icon_outline_heart_5,
                    isActive = activeTab == "Wishlist"
                )
                NavigationItem(
                    "Trips",
                    R.drawable.airbnb,
                    isActive = activeTab == "Trips"
                )
                NavigationItem(
                    "Inbox",
                    R.drawable.icon_outline_message_2,
                    isActive = activeTab == "Inbox"
                )
                NavigationItem(
                    "Profile",
                    R.drawable.icon_outline_user_1,
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
            tint = if (isActive) Neutral100 else Neutral70
        )

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 12.sp,
                fontWeight = if (isActive) FontWeight.Medium else FontWeight.Normal
            ),
            color = if (isActive) Neutral100 else Neutral70
        )
    }
}

@Composable
fun SearchOverlayMatchingFigma(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Neutral10)
    ) {
        // Status Bar
        Spacer(modifier = Modifier.height(44.dp))

        // Top Bar with close button and tabs
        TopBarWithCloseButton(onCloseClick = onDismiss)

        // Search Fields Container
        SearchFieldsContainer()

        // Who's coming section
        WhosComingSection()
    }
}

@Composable
fun TopBarWithCloseButton(onCloseClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Close button
        Button(
            onClick = onCloseClick,
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Neutral10,
                contentColor = Neutral100
            ),
            border = BorderStroke(1.dp, Neutral40),
            contentPadding = PaddingValues(8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.close_outline_icon),
                contentDescription = "Close",
                modifier = Modifier.size(24.dp),
                tint = Neutral100
            )
        }

        // Tabs
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TabItem(text = "Stays", isActive = true)
            TabItem(text = "Experiences", isActive = false)
        }
    }
}

@Composable
fun TabItem(text: String, isActive: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            color = if (isActive) Neutral100 else Color(0xFF989B9D),
            modifier = Modifier.padding(vertical = 6.dp)
        )

        if (isActive) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(2.dp)
                    .background(Neutral100)
            )
        } else {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(1.dp)
                    .background(Neutral20)
            )
        }
    }
}

@Composable
fun SearchFieldsContainer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Where field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Neutral10, RoundedCornerShape(16.dp))
                .border(1.dp, Neutral40, RoundedCornerShape(16.dp))
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = Color.Black.copy(alpha = 0.08f)
                )
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Where",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Neutral70
                )
                Text(
                    text = "Amalfi Coast, Italy",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Neutral100
                )
            }
        }

        // When field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Neutral10, RoundedCornerShape(16.dp))
                .border(1.dp, Neutral40, RoundedCornerShape(16.dp))
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = Color.Black.copy(alpha = 0.08f)
                )
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "When",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Neutral70
                )
                Text(
                    text = "Jul 5 - Dec 5",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Neutral100
                )
            }
        }
    }
}

@Composable
fun WhosComingSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Neutral10, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .border(1.dp, Neutral40, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                spotColor = Color.Black.copy(alpha = 0.24f)
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(24.dp)
        ) {
            // Title
            Text(
                text = "Who's coming?",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 26.sp
                ),
                color = Neutral100,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Guest counters
            GuestCounterItem(
                title = "Adults",
                subtitle = "Ages 13 or above",
                count = 0
            )

            GuestCounterItem(
                title = "Children",
                subtitle = "Ages 2-12",
                count = 0
            )

            GuestCounterItem(
                title = "Infants",
                subtitle = "Under 2",
                count = 0
            )

            GuestCounterItem(
                title = "Pets",
                subtitle = "Bringing a service animal?",
                count = 0
            )

        }

        // Bottom buttons
        SearchBottomButtons()
    }
}

@Composable
fun GuestCounterItem(
    title: String,
    subtitle: String,
    count: Int
) {
    var currentCount by remember { mutableStateOf(count) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Neutral100
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    textDecoration = if (title == "Pets") TextDecoration.Underline else TextDecoration.None
                ),
                color = Neutral70
            )
        }

        // Counter controls
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Minus button
            Button(
                onClick = { if (currentCount > 0) currentCount-- },
                modifier = Modifier.size(32.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Neutral10,
                    contentColor = if (currentCount > 0) Neutral100 else Neutral40,
                    disabledContainerColor = Neutral10,
                    disabledContentColor = Neutral40
                ),
                border = BorderStroke(1.dp, if (currentCount > 0) Neutral100 else Neutral40),
                contentPadding = PaddingValues(0.dp),
                enabled = currentCount > 0
            ) {
                Icon(
                    painter = painterResource(R.drawable.minus_icon),
                    contentDescription = "Decrease",
                    modifier = Modifier.size(16.dp),
                    tint = if (currentCount > 0) Neutral100 else Neutral40
                )
            }

            // Count display
            Text(
                text = currentCount.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp
                ),
                color = Neutral100,
                modifier = Modifier.width(24.dp),
                textAlign = TextAlign.Center
            )

            // Plus button
            Button(
                onClick = { currentCount++ },
                modifier = Modifier.size(32.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Neutral10,
                    contentColor = Neutral100
                ),
                border = BorderStroke(1.dp, Neutral100),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.plus_icon),
                    contentDescription = "Increase",
                    modifier = Modifier.size(16.dp),
                    tint = Neutral100
                )
            }
        }
    }
}

@Composable
fun SearchBottomButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .border(
                width = 1.dp,
                color = Neutral40,
                shape = RectangleShape
            )
            .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Clear all button
        TextButton(
            onClick = { /* Handle clear all */ },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Clear all",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline
                ),
                color = Neutral100
            )
        }

        // Search button
        Button(
            onClick = { /* Handle search */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary70,
                contentColor = Neutral10
            ),
            shape = RoundedCornerShape(6.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
            modifier = Modifier.height(48.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.search_outline_icon),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp),
                    tint = Neutral10
                )
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Neutral10
                )
            }
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

@Preview(showBackground = true)
@Composable
fun SearchOverlayPreview() {
    AirbnbTheme {
        SearchOverlayMatchingFigma(
            onDismiss = { }
        )
    }
}
