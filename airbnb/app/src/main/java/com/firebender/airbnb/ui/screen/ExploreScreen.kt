package com.firebender.airbnb.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.AirbnbNeutral10
import com.firebender.airbnb.ui.theme.AirbnbNeutral100
import com.firebender.airbnb.ui.theme.AirbnbNeutral20
import com.firebender.airbnb.ui.theme.AirbnbNeutral40
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
            Spacer(modifier = Modifier.height(17.dp))
            CategoriesSection()
            Spacer(modifier = Modifier.height(24.dp))
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
            .shadow(8.dp, RoundedCornerShape(43.dp), ambientColor = Color.Black.copy(alpha = 0.1f)),
        shape = RoundedCornerShape(43.dp),
        colors = CardDefaults.cardColors(containerColor = AirbnbNeutral10),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, AirbnbNeutral40)
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_outline_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp),
                    tint = AirbnbNeutral100
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Where to?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = AirbnbNeutral100
                    )
                    Text(
                        text = "Anywhere • Any week • Add guests",
                        fontSize = 12.sp,
                        color = AirbnbNeutral70
                    )
                }
            }

            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                border = androidx.compose.foundation.BorderStroke(0.5.dp, AirbnbNeutral40)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_outline_filter),
                    contentDescription = "Filter",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(10.dp),
                    tint = AirbnbNeutral100
                )
            }
        }
    }
}

@Composable
fun CategoriesSection() {
    val categories = listOf(
        CategoryItem("OMG!", R.drawable.icon_outline_u_f_o, true),
        CategoryItem("Beach", R.drawable.icon_outline_beach, false),
        CategoryItem("Amazing pools", R.drawable.icon_outline_pool, false),
        CategoryItem("Islands", R.drawable.icon_outline_island, false),
        CategoryItem("Arctic", R.drawable.icon_outline_arctic, false)
    )

    Column {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            itemsIndexed(categories) { _, category ->
                CategoryTab(category = category)
            }
        }

        // Bottom border
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            thickness = 1.dp,
            color = AirbnbNeutral20
        )
    }
}

@Composable
fun CategoryTab(category: CategoryItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = category.iconRes),
            contentDescription = category.title,
            modifier = Modifier.size(24.dp),
            tint = if (category.isActive) AirbnbNeutral100 else AirbnbNeutral70
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = category.title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (category.isActive) AirbnbNeutral100 else AirbnbNeutral70
        )

        // Active indicator line
        if (category.isActive) {
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(2.dp)
                    .background(AirbnbNeutral100)
            )
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
            imageRes = R.drawable.poolside_garden_patio,
            title = "Abiansemal, Indonesia",
            distance = "1,669 kilometers",
            date = "Jul 2 - 7",
            rating = "4.87 (71)",
            price = "$360 night"
        )

        PropertyCard(
            imageRes = R.drawable.modern_house_pool_view,
            title = "Kintamani, Indonesia",
            distance = "971 kilometers",
            date = "Jul 6 - 11",
            rating = "4.91 (89)",
            price = "$360 night"
        )
    }
}

@Composable
fun PropertyCard(
    imageRes: Int,
    title: String,
    distance: String,
    date: String,
    rating: String,
    price: String
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
            Card(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_outline_heart),
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .size(28.dp)
                        .padding(4.dp),
                    tint = AirbnbNeutral10
                )
            }

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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = AirbnbNeutral100
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = distance,
                    fontSize = 14.sp,
                    color = AirbnbNeutral70
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = date,
                    fontSize = 14.sp,
                    color = AirbnbNeutral70
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = AirbnbNeutral100
                )
            }

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_filled_star),
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
            .shadow(8.dp, CircleShape),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = AirbnbNeutral100)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Map",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = AirbnbNeutral10
            )
            // Two vertical lines icon to match Figma
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
            width = 0.5.dp,
            color = AirbnbNeutral40
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomNavItem(
                iconRes = R.drawable.icon_outline_search,
                label = "Explore",
                isActive = true
            )
            BottomNavItem(
                iconRes = R.drawable.icon_outline_heart,
                label = "Wishlist",
                isActive = false
            )
            BottomNavItem(
                iconRes = R.drawable.icon_airbnb,
                label = "Trips",
                isActive = false
            )
            BottomNavItem(
                iconRes = R.drawable.icon_outline_message,
                label = "Inbox",
                isActive = false,
                hasIndicator = true
            )
            BottomNavItem(
                iconRes = R.drawable.icon_outline_user,
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

data class CategoryItem(
    val title: String,
    val iconRes: Int,
    val isActive: Boolean
)

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    AirbnbTheme {
        ExploreScreen()
    }
}
