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
        modifier = modifier.fillMaxSize()
    ) {
        // Status Bar
        Spacer(modifier = Modifier.height(44.dp))

        // Top Bar with close button and tabs
        TopBarWithCloseButton(onCloseClick = onDismiss)

        // Where field (showing "Amalfi Coast, Italy")
        WhereField()

        // Main date selection content
        DateSelectionContent()
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
        // Tabs
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TabItem(text = "Stays", isActive = true)
            TabItem(text = "Experiences", isActive = false)
        }

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
                painter = painterResource(R.drawable.close_icon_0),
                contentDescription = "Close",
                modifier = Modifier.size(24.dp),
                tint = Neutral100
            )
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
fun WhereField() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 14.dp)
            .background(Neutral10, RoundedCornerShape(18.dp))
            .border(1.dp, Neutral40, RoundedCornerShape(18.dp))
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(18.dp),
                spotColor = Color.Black.copy(alpha = 0.08f)
            )
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Where",
                style = MaterialTheme.typography.labelMedium.copy(
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
}

@Composable
fun DateSelectionContent() {
    var selectedTab by remember { mutableStateOf("Months") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Neutral10, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                spotColor = Color.Black.copy(alpha = 0.24f)
            )
    ) {
        // Content
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            // Title
            Text(
                text = "When's your trip?",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 26.sp
                ),
                color = Neutral100,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Tab selector
            DateTabSelector(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Circular month picker - matches Figma design
            CircularMonthPicker()

            Spacer(modifier = Modifier.weight(1f))

            // Text and calendar link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Starting July 1 • ",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp
                    ),
                    color = Neutral70
                )

                Text(
                    text = "Edit",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline
                    ),
                    color = Neutral100
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        // Bottom buttons
        BottomButtonsSection(
            onSkip = { /* Handle skip */ },
            onNext = { /* Handle next */ }
        )
    }
}

@Composable
fun DateTabSelector(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    val tabs = listOf("Dates", "Months", "Flexible")

    Row(
        modifier = Modifier
            .background(Neutral20, RoundedCornerShape(24.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(23.dp)
    ) {
        tabs.forEach { tab ->
            val isSelected = selectedTab == tab
            Button(
                onClick = { onTabSelected(tab) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Neutral10 else Color.Transparent,
                    contentColor = Neutral100
                ),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.height(36.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = if (isSelected) 2.dp else 0.dp
                )
            ) {
                Text(
                    text = tab,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

@Composable
fun CircularMonthPicker() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // Main circular background with shadow
        Box(
            modifier = Modifier
                .size(200.dp)
                .shadow(
                    elevation = 28.dp,
                    shape = CircleShape,
                    spotColor = Color.Black.copy(alpha = 0.12f)
                )
                .background(Neutral10, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Background circle
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(Color(0xFFF5F5F5), CircleShape)
            )

            // Gradient arc - partial arc
            Canvas(
                modifier = Modifier.size(180.dp)
            ) {
                val strokeWidth = 16.dp.toPx()
                val radius = (size.minDimension - strokeWidth) / 2
                val center = Offset(size.width / 2, size.height / 2)

                // Draw gradient arc from approximately 0 degrees to 150 degrees
                drawArc(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            Color(0xFFFF385C), // Airbnb red
                            Color(0xFFE91E63), // Magenta
                            Color(0xFFFF385C) // Back to red
                        ),
                        center = center
                    ),
                    startAngle = 0f,
                    sweepAngle = 150f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                    topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                    size = Size(size.width - strokeWidth, size.height - strokeWidth)
                )

                // Draw white circle at the end of the arc
                val endAngle = 0f + 150f
                val endRadians = Math.toRadians(endAngle.toDouble())
                val arcRadius = (size.minDimension - strokeWidth) / 2
                val endX = center.x + (arcRadius * kotlin.math.cos(endRadians)).toFloat()
                val endY = center.y + (arcRadius * kotlin.math.sin(endRadians)).toFloat()

                drawCircle(
                    color = Color.White,
                    radius = strokeWidth / 2,
                    center = Offset(endX, endY)
                )
            }

            // Center content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "3",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Neutral100
                )
                Text(
                    text = "months",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Neutral100
                )
            }

            // Month indicator dots around the circle
            val months = listOf(
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct",
                "Nov",
                "Dec"
            )
            val selectedMonth = 6 // July (0-based)

            months.forEachIndexed { index, _ ->
                val angle = (index * 30f) - 90f // Start from top, 30 degrees per month
                val isSelected = index == selectedMonth
                val radiusFromCenter = 75.dp.value // Adjust this to position dots correctly

                Box(
                    modifier = Modifier
                        .offset(
                            x = (radiusFromCenter * kotlin.math.cos(Math.toRadians(angle.toDouble()))).dp,
                            y = (radiusFromCenter * kotlin.math.sin(Math.toRadians(angle.toDouble()))).dp
                        )
                        .size(if (isSelected) 8.dp else 6.dp)
                        .background(
                            if (isSelected) Neutral100 else Neutral40,
                            CircleShape
                        )
                )
            }
        }

        // Additional gradient overlay elements (smaller decorative circles)
        Box(
            modifier = Modifier
                .offset(x = 60.dp, y = (-20).dp)
                .size(20.dp)
                .background(
                    Color(0xFFFF385C).copy(alpha = 0.15f),
                    CircleShape
                )
        )

        Box(
            modifier = Modifier
                .offset(x = (-70).dp, y = 30.dp)
                .size(16.dp)
                .background(
                    Color(0xFFFF385C).copy(alpha = 0.1f),
                    CircleShape
                )
        )
    }
}

@Composable
fun BottomButtonsSection(
    onSkip: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .border(
                width = 1.dp,
                color = Neutral40,
                shape = RectangleShape
            )
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = onSkip,
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Skip",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline
                ),
                color = Neutral100
            )
        }

        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(
                containerColor = Neutral100,
                contentColor = Neutral10
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(horizontal = 48.dp, vertical = 14.dp),
            modifier = Modifier.height(48.dp)
        ) {
            Text(
                text = "Next",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Neutral10
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
