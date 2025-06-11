package com.firebender.airbnb.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
    var showSearchOverlay by remember { mutableStateOf(false) }
    var searchStep by remember { mutableStateOf(SearchStep.LOCATION) }
    var selectedLocation by remember { mutableStateOf("") }

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
        // Main content with blur overlay when search is active
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (showSearchOverlay) Neutral10.copy(alpha = 0.6f) else Neutral10)
                .verticalScroll(rememberScrollState())
        ) {
            // Status Bar (simulated)
            Spacer(modifier = Modifier.height(44.dp))

            // Search Bar
            SearchBarSection(onSearchClick = {
                showSearchOverlay = true
                searchStep = SearchStep.LOCATION
            })

            // Categories
            CategoriesSection(categories)

            // Property Cards
            PropertyCardsSection(properties)

            Spacer(modifier = Modifier.height(100.dp))
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

        // Search Overlay
        if (showSearchOverlay) {
            when (searchStep) {
                SearchStep.LOCATION -> {
                    SearchOverlay(
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex(10f),
                        onDismiss = { showSearchOverlay = false },
                        onLocationSelected = { location ->
                            selectedLocation = location
                            searchStep = SearchStep.DATES
                        }
                    )
                }

                SearchStep.DATES -> {
                    DateSelectionOverlay(
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex(10f),
                        selectedLocation = selectedLocation,
                        onDismiss = { showSearchOverlay = false },
                        onBack = { searchStep = SearchStep.LOCATION }
                    )
                }

                SearchStep.GUESTS -> {
                    // Future implementation
                }
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

@Composable
fun SearchOverlay(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onLocationSelected: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("Italy") }

    val locationSuggestions = listOf(
        "Italy",
        "Amalfi Coast, Italy",
        "Florence, Italy",
        "Lake Como, Italy",
        "Milan, Italy"
    )

    Column(
        modifier = modifier
            .background(Neutral10)
            .fillMaxSize()
    ) {
        // Status Bar spacer
        Spacer(modifier = Modifier.height(44.dp))

        // Top Bar with back button and tabs
        TopBarSection(onBackClick = onDismiss)

        // Search Input
        SearchInputSection(
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            onClearClick = { searchText = "" }
        )

        // Location suggestions
        LocationSuggestionsSection(
            suggestions = locationSuggestions,
            onLocationSelected = onLocationSelected
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun TopBarSection(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button
        Button(
            onClick = onBackClick,
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
                painter = painterResource(R.drawable.outline_arrow_left_icon),
                contentDescription = "Back",
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
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Composable
fun SearchInputSection(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onClearClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .background(Color(0xFFF7F7F7), RoundedCornerShape(12.dp))
            .padding(24.dp, 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_outline_search_3),
            contentDescription = "Search",
            modifier = Modifier.size(24.dp),
            tint = Neutral100
        )

        Text(
            text = searchText,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp
            ),
            color = Neutral100,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color(0xFFE4E9EC), CircleShape)
                .clickable { onClearClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.close_icon),
                contentDescription = "Clear",
                modifier = Modifier.size(12.dp),
                tint = Neutral100
            )
        }
    }
}

@Composable
fun LocationSuggestionsSection(suggestions: List<String>, onLocationSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        suggestions.forEach { suggestion ->
            LocationSuggestionItem(suggestion = suggestion, onLocationSelected = onLocationSelected)
        }
    }
}

@Composable
fun LocationSuggestionItem(suggestion: String, onLocationSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onLocationSelected(suggestion) },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFF7F7F7), RoundedCornerShape(8.dp))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_outline_location),
                contentDescription = "Location",
                modifier = Modifier.size(24.dp),
                tint = Neutral100
            )
        }

        Text(
            text = suggestion,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp
            ),
            color = Neutral100
        )
    }
}

@Composable
fun DateSelectionOverlay(
    modifier: Modifier = Modifier,
    selectedLocation: String,
    onDismiss: () -> Unit,
    onBack: () -> Unit
) {
    var selectedTab by remember { mutableStateOf("Dates") }
    var selectedDateOption by remember { mutableStateOf("Exact dates") }

    // Use Calendar for August 2023 to match Figma design
    var displayMonth by remember { mutableStateOf(7) } // August (0-based)
    var displayYear by remember { mutableStateOf(2023) }

    Column(
        modifier = modifier
            .background(Neutral10)
            .fillMaxSize()
    ) {
        // Status Bar spacer
        Spacer(modifier = Modifier.height(44.dp))

        // Top Bar with close button and tabs
        DateSelectionTopBar(onCloseClick = onDismiss)

        // Where field showing selected location
        WhereSection(selectedLocation = selectedLocation.ifEmpty { "Amalfi Coast, Italy" })

        // Date selection content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutral10, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .shadow(18.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(24.dp)
        ) {
            // Title
            Text(
                text = "When's your trip?",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                ),
                color = Neutral100,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Tab selector
            DateTabSelector(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Calendar
            if (selectedTab == "Dates") {
                CalendarSection(
                    displayMonth = displayMonth,
                    displayYear = displayYear,
                    onMonthChanged = { month, year ->
                        displayMonth = month
                        displayYear = year
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Date options
                DateOptionsSection(
                    selectedOption = selectedDateOption,
                    onOptionSelected = { selectedDateOption = it }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom buttons
            BottomButtonsSection(
                onSkip = { onDismiss() },
                onNext = { /* Handle next */ }
            )
        }
    }
}

@Composable
fun DateSelectionTopBar(onCloseClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Tabs
        Row(
            modifier = Modifier,
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
                painter = painterResource(R.drawable.close_icon_1),
                contentDescription = "Close",
                modifier = Modifier.size(24.dp),
                tint = Neutral100
            )
        }
    }
}

@Composable
fun WhereSection(selectedLocation: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 14.dp)
            .background(Neutral10, RoundedCornerShape(18.dp))
            .border(1.dp, Neutral40, RoundedCornerShape(18.dp))
            .shadow(8.dp, RoundedCornerShape(18.dp))
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Where",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Neutral70
            )
            Text(
                text = selectedLocation,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Neutral100
            )
        }
    }
}

@Composable
fun DateTabSelector(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    val tabs = listOf("Dates", "Months", "Flexible")

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tabs.forEach { tab ->
            Button(
                onClick = { onTabSelected(tab) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Neutral100
                ),
                border = BorderStroke(1.dp, if (selectedTab == tab) Neutral100 else Neutral40),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text(
                    text = tab,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = if (selectedTab == tab) FontWeight.Medium else FontWeight.Normal
                    )
                )
            }
        }
    }
}

@Composable
fun CalendarSection(
    displayMonth: Int,
    displayYear: Int,
    onMonthChanged: (Int, Int) -> Unit
) {
    Column {
        // Month header
        val monthNames = arrayOf(
            "January", "February", "March", "April", "May", "June",
            "July", "Augustus", "September", "October", "November", "December"
        )

        Text(
            text = "${monthNames[displayMonth]} $displayYear",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            ),
            color = Neutral100,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Days of week header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("S", "M", "T", "W", "T", "F", "S").forEach { day ->
                Text(
                    text = day,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Neutral70,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Calendar grid
        val calendar = Calendar.getInstance()
        calendar.set(displayYear, displayMonth, 1)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1 // Convert to 0-based
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val calendarDays = mutableListOf<Int?>()
        // Add empty cells for days before the first day of the month
        repeat(firstDayOfWeek) {
            calendarDays.add(null)
        }
        // Add all days of the month
        (1..daysInMonth).forEach { day ->
            calendarDays.add(day)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.height(240.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(calendarDays) { day ->
                CalendarDay(
                    day = day,
                    isSelected = false,
                    onDaySelected = { /* Handle day selection */ }
                )
            }
        }
    }
}

@Composable
fun CalendarDay(
    day: Int?,
    isSelected: Boolean,
    onDaySelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .then(
                if (day != null) {
                    Modifier.clickable { onDaySelected(day) }
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (day != null) {
            Text(
                text = day.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp
                ),
                color = Neutral100,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun DateOptionsSection(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val options = listOf("Exact dates", "± 1 day", "± 2 days")

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { option ->
            Button(
                onClick = { onOptionSelected(option) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Neutral100
                ),
                border = BorderStroke(
                    1.dp,
                    if (selectedOption == option) Neutral100 else Neutral40
                ),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = if (selectedOption == option) FontWeight.Medium else FontWeight.Normal
                    )
                )
            }
        }
    }
}

@Composable
fun BottomButtonsSection(
    onSkip: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = onSkip,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
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
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp),
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

@Preview(showBackground = true)
@Composable
fun SearchOverlayPreview() {
    AirbnbTheme {
        SearchOverlay(onDismiss = {})
    }
}

@Preview(showBackground = true)
@Composable
fun DateSelectionOverlayPreview() {
    AirbnbTheme {
        DateSelectionOverlay(
            selectedLocation = "Amalfi Coast, Italy",
            onDismiss = {},
            onBack = {}
        )
    }
}
