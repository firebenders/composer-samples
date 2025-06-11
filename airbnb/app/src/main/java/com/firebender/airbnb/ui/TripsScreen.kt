package com.firebender.airbnb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.firebender.airbnb.ui.theme.AirbnbTheme
import com.firebender.airbnb.ui.theme.Neutral10
import com.firebender.airbnb.ui.theme.Neutral40
import com.firebender.airbnb.ui.theme.Neutral70
import com.firebender.airbnb.ui.theme.Neutral100
import com.firebender.airbnb.ui.theme.Primary70

@Composable
fun TripsScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TripsTopBar()
        },
        bottomBar = {
            TripsBottomNavigationBar()
        },
        containerColor = Neutral10
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Main content
            TripsContent()
        }
    }
}

@Composable
fun TripsTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Trips",
            fontSize = 32.sp,
            fontWeight = FontWeight.Medium,
            color = Neutral100,
            lineHeight = 42.sp
        )
    }
}

@Composable
fun TripsContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Upcoming reservations section
        Text(
            text = "Upcoming reservations",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Neutral100,
            lineHeight = 28.sp
        )

        YonkersCard()

        Spacer(modifier = Modifier.height(24.dp))

        // Explore section
        ExploreSection()
    }
}

@Composable
fun YonkersCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color.Black.copy(alpha = 0.2f)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Neutral10),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            // Image section with badge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.beachfront_room_view),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.Crop
                )

                // Pending badge
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .background(
                            Neutral10,
                            RoundedCornerShape(6.dp)
                        )
                        .border(
                            1.dp,
                            Color(0xFFF7F7F7),
                            RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopStart)
                ) {
                    Text(
                        text = "Pending",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Neutral100
                    )
                }
            }

            // Content section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Neutral10,
                        RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Main title and description
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Yonkers",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Neutral100,
                        lineHeight = 22.sp
                    )
                    Text(
                        text = "Private room in home hosted by Craig",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Neutral70,
                        lineHeight = 18.sp
                    )
                }

                // Divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFF7F7F7))
                )

                // Date and location section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // Date section
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Feb",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Neutral100,
                            lineHeight = 22.sp
                        )
                        Text(
                            text = "13 - 14",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Neutral100,
                            lineHeight = 22.sp
                        )
                        Text(
                            text = "2023",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Neutral70,
                            lineHeight = 18.sp
                        )
                    }

                    // Location section
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Yonkers, New York",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Neutral100,
                            lineHeight = 22.sp
                        )
                        Text(
                            text = "United States",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Neutral70,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExploreSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Explore things to do near Yonkers",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Neutral100,
            lineHeight = 24.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            ExploreCard(
                imageRes = R.drawable.pottery_wheel_crafting,
                title = "Just for you",
                description = "18 experiences",
                modifier = Modifier.weight(1f)
            )

            ExploreCard(
                imageRes = R.drawable.trip_recommendation_meat_platter,
                title = "Food",
                description = "23 experiences",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ExploreCard(
    imageRes: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Neutral100,
                lineHeight = 22.sp
            )
            Text(
                text = description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Neutral70,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun TripsBottomNavigationBar() {
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
                .background(Color(0xFFD8DCE0))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TripsNavigationItem(
                icon = R.drawable.icon_outline_search_1,
                label = "Explore",
                isSelected = false
            )
            TripsNavigationItem(
                icon = R.drawable.icon_outline_heart_0,
                label = "Wishlist",
                isSelected = false
            )
            TripsNavigationItem(
                icon = R.drawable.icon_airbnb,
                label = "Trips",
                isSelected = true
            )
            TripsNavigationItem(
                icon = R.drawable.icon_outline_message,
                label = "Inbox",
                isSelected = false,
                showIndicator = true
            )
            TripsNavigationItem(
                icon = R.drawable.icon_outline_user,
                label = "Profile",
                isSelected = false
            )
        }
    }
}

@Composable
fun TripsNavigationItem(
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
fun TripsScreenPreview() {
    AirbnbTheme {
        TripsScreen()
    }
}
