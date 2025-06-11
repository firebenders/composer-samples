package com.firebender.airbnb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.*

data class PriceTag(
    val id: String,
    val price: String,
    val x: Float,
    val y: Float
)

@Composable
fun SearchHotelScreen() {
    val priceTags = listOf(
        PriceTag("1", "$6,232", 0.82f, 0.15f),
        PriceTag("2", "$13,538", 0.02f, 0.82f),
        PriceTag("3", "$7,181", 0.25f, 0.85f),
        PriceTag("4", "$3,217", 0.8f, 0.75f),
        PriceTag("5", "$5,482", 0.65f, 0.6f),
        PriceTag("6", "$15,289", 0.45f, 0.75f),
        PriceTag("7", "$2,165", 0.35f, 0.55f),
        PriceTag("8", "$19,357", 0.85f, 0.45f),
        PriceTag("9", "$17,103", 0.68f, 0.35f),
        PriceTag("10", "$12,208", 0.28f, 0.3f),
        PriceTag("11", "$5,602", 0.12f, 0.2f),
        PriceTag("12", "$11,802", 0.55f, 0.25f)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Background map image
        Image(
            painter = painterResource(R.drawable.hotel_search_map),
            contentDescription = "Map",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Search Bar at top
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 44.dp)
                .zIndex(5f)
        )

        // Price tags floating on map
        priceTags.forEach { priceTag ->
            PriceTagChip(
                price = priceTag.price,
                modifier = Modifier
                    .offset(
                        x = (priceTag.x * 310).dp,
                        y = (priceTag.y * 380).dp + 130.dp
                    )
                    .zIndex(3f)
            )
        }

        // Bottom Sheet
        PropertyBottomSheet(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(4f)
        )

        // Navigation Bar
        NavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            activeTab = "Explore"
        )
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(43.dp),
        color = Neutral20,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.search_icon),
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp),
                    tint = Neutral100
                )

                Column {
                    Text(
                        text = "Amalfi Coast",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        color = Neutral100
                    )
                    Text(
                        text = "Jul 5 - Dec 5 (±7) • 4 guests",
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
fun PriceTagChip(
    price: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = Neutral10,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = price,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                color = Neutral100
            )
        }
    }
}

@Composable
fun PropertyBottomSheet(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(520.dp),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        color = Neutral10,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Handle bar
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .height(4.dp)
                    .background(Neutral40, RoundedCornerShape(2.dp))
                    .align(Alignment.CenterHorizontally)
            )

            // Places text
            Text(
                text = "204 places",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                color = Neutral100,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Property card
            PropertyCard()

            // Add spacing to make the bottom sheet taller and accommodate navigation
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun PropertyCard() {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Image with heart and progress indicators
        Box {
            Image(
                painter = painterResource(R.drawable.beach_view_chairs_patio),
                contentDescription = "Property",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // Heart icon
            Icon(
                painter = painterResource(R.drawable.heart_icon),
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
                        text = "Apartment in Praiano",
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
                            painter = painterResource(R.drawable.star_icon),
                            contentDescription = "Rating",
                            modifier = Modifier.size(12.dp),
                            tint = Neutral100
                        )
                        Text(
                            text = "4.89",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 12.sp
                            ),
                            color = Neutral100
                        )
                    }
                }

                Text(
                    text = "4 Bedroom Apartment on the Beach",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 12.sp
                    ),
                    color = Neutral70
                )

                Text(
                    text = "Jul 5 - Dec 5",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 12.sp
                    ),
                    color = Neutral70
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$1,031 total",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        color = Neutral100
                    )

                    Button(
                        onClick = { },
                        modifier = Modifier.size(width = 80.dp, height = 36.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary70,
                            contentColor = Neutral10
                        ),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "Book",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            ),
                            color = Neutral10
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchHotelScreenPreview() {
    AirbnbTheme {
        SearchHotelScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    AirbnbTheme {
        SearchBar(modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PriceTagChipPreview() {
    AirbnbTheme {
        PriceTagChip(price = "$6,232")
    }
}

@Preview(showBackground = true)
@Composable
fun PropertyBottomSheetPreview() {
    AirbnbTheme {
        PropertyBottomSheet()
    }
}
