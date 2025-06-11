package com.firebender.airbnb.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.*

@Composable
fun PropertyDetailScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutral10)
                .verticalScroll(rememberScrollState())
        ) {
            // Property Images Section
            PropertyImageSection()

            // Property Info Section
            PropertyInfoSection()

            // Property Details Section
            PropertyDetailsSection()

            // Amenities Section
            AmenitiesSection()

            // AirCover Section
            AirCoverSection()

            // Description Section
            DescriptionSection()

            // Sleep Section
            SleepSection()

            // Amenities List Section
            AmenitiesListSection()

            // Location Section
            LocationSection()

            // Reviews Section
            ReviewsSection()

            // Host Section
            HostSection()

            // Policies Section
            PoliciesSection()

            // Report Section
            ReportSection()

            // Bottom spacing for fixed bottom bar
            Spacer(modifier = Modifier.height(100.dp))
        }

        // Status Bar
        StatusBarSection(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(10f)
        )

        // Top Navigation Bar
        TopBarSection(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 56.dp)
                .zIndex(10f)
        )

        // Bottom Reservation Bar
        BottomReservationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(10f)
        )
    }
}

@Composable
fun StatusBarSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = 33.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "9:41",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = Color.White
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.network_signal_dark),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color.White
            )
            Icon(
                painter = painterResource(R.drawable.wifi_signal_dark),
                contentDescription = null,
                modifier = Modifier.size(15.dp),
                tint = Color.White
            )
            Icon(
                painter = painterResource(R.drawable.battery_dark),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
fun TopBarSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            color = Neutral10
        ) {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(R.drawable.icon_outline_arrow_left),
                    contentDescription = "Back",
                    modifier = Modifier.size(20.dp),
                    tint = Neutral100
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = Neutral10
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(R.drawable.icon_outline_download),
                        contentDescription = "Download",
                        modifier = Modifier.size(20.dp),
                        tint = Neutral100
                    )
                }
            }

            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = Neutral10
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(R.drawable.icon_outline_menu),
                        contentDescription = "Menu",
                        modifier = Modifier.size(20.dp),
                        tint = Neutral100
                    )
                }
            }
        }
    }
}

@Composable
fun PropertyImageSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(375.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.cozy_bedroom_interior_0),
            contentDescription = "Property Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Image indicator
        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            shape = RoundedCornerShape(4.dp),
            color = Color.Black.copy(alpha = 0.4f)
        ) {
            Text(
                text = "1 / 27",
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                color = Color.White
            )
        }
    }
}

@Composable
fun PropertyInfoSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Private room in Yonkers close to bus/train station",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 26.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Medium
            ),
            color = Neutral100
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_filled_star),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Neutral100
                )
                Text(
                    text = "5.0 ∙ 3 reviews",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = Neutral100
                )
            }

            Text(
                text = "Yonkers, New York, United States",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                color = Neutral100
            )
        }
    }
}

@Composable
fun PropertyDetailsSection() {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Neutral10
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Private room in home hosted by Craig",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 22.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = Neutral100
                    )
                    Text(
                        text = "2 guests ∙ 1 bedroom ∙ 1 bed",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                        color = Neutral90
                    )
                    Text(
                        text = "1 private bath",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                        color = Neutral90
                    )
                }

                Image(
                    painter = painterResource(R.drawable.avatar_ellipse_9),
                    contentDescription = "Host Avatar",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Neutral40)
            )
        }
    }
}

@Composable
fun AmenitiesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PropertyFeatureItem(
            iconRes = R.drawable.icon_outline_door,
            title = "Self check-in",
            description = "Check yourself in with the keypad."
        )

        PropertyFeatureItem(
            iconRes = R.drawable.icon_outline_location,
            title = "Great location",
            description = "100% of recent guests gave the location a 5-star rating."
        )

        PropertyFeatureItem(
            iconRes = R.drawable.icon_outline_calendar_2,
            title = "Free cancellation before Feb 12.",
            description = null
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Neutral40)
        )
    }
}

@Composable
fun PropertyFeatureItem(
    iconRes: Int,
    title: String,
    description: String?
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = if (description != null) Alignment.Top else Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Neutral100
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Neutral100
            )

            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = Neutral70
                )
            }
        }
    }
}

@Composable
fun AirCoverSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Primary60)) {
                    append("air")
                }
                withStyle(style = SpanStyle(color = Neutral100)) {
                    append("cover")
                }
            },
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 33.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-1.98).sp
            )
        )

        Text(
            text = "Every booking includes free protection from Host cancellations, listing inaccuracies, and other issues like trouble checking in.",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            color = Neutral100
        )

        TextButton(
            onClick = { },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Learn more",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline
                ),
                color = Neutral100
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Neutral40)
        )
    }
}

@Composable
fun DescriptionSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Enjoy a private and quiet bedroom and bathroom in Yonkers. Bus and train station are only minutes away. Train will take you to Manhattan in about 45 minutes. Cross County Mall and many restaurants are close by. Free parking. Fast WiFi. You have access",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            color = Neutral100,
            maxLines = 6,
            overflow = TextOverflow.Ellipsis
        )

        TextButton(
            onClick = { },
            contentPadding = PaddingValues(0.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Show more",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline
                    ),
                    color = Neutral100
                )
                Icon(
                    painter = painterResource(R.drawable.icon_outline_chevron_right),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Neutral100
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Neutral40)
        )
    }
}

@Composable
fun SleepSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Where you'll sleep",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            ),
            color = Neutral100
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(122.dp),
            shape = RoundedCornerShape(10.dp),
            color = Neutral10,
            border = BorderStroke(1.dp, Neutral40)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_outline_bed),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Neutral100
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Bedroom",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = Neutral100
                    )
                    Text(
                        text = "1 queen bed",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        color = Neutral70
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Neutral40)
        )
    }
}

@Composable
fun AmenitiesListSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "What this place offers",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Neutral100
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AmenityItem(R.drawable.icon_outline_view, "River view")
                AmenityItem(R.drawable.icon_outline_kitchen, "Kitchen")
                AmenityItem(R.drawable.icon_outline_wifi, "Wifi")
                AmenityItem(R.drawable.icon_outline_car, "Free parking on premises")
                AmenityItem(R.drawable.icon_outline_arctic_0, "AC - split type ductless system")
            }
        }

        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(1.dp, Neutral100),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Neutral100
            )
        ) {
            Text(
                text = "Show all 52 amenities",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Neutral100
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Neutral40)
        )
    }
}

@Composable
fun AmenityItem(iconRes: Int, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Neutral100
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
            color = Neutral100
        )
    }
}

@Composable
fun LocationSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Where you'll be",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            ),
            color = Neutral100
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                ) {
                    Image(
                        painter = painterResource(R.drawable.explore_maps_section),
                        contentDescription = "Map",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Icon(
                        painter = painterResource(R.drawable.icon_filled_home),
                        contentDescription = "Location",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(32.dp),
                        tint = Primary60
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
                    color = Neutral10,
                    border = BorderStroke(1.dp, Neutral40)
                ) {
                    Text(
                        text = "Exact location provided after booking.",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        color = Neutral100
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Yonkers, New York, United States",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = Neutral100
                    )
                    Text(
                        text = "Located on a quiet suburban street.",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        color = Neutral70
                    )
                }

                TextButton(
                    onClick = { },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Show more",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                textDecoration = TextDecoration.Underline
                            ),
                            color = Neutral100
                        )
                        Icon(
                            painter = painterResource(R.drawable.icon_outline_chevron_right),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Neutral100
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Neutral40)
        )
    }
}

@Composable
fun ReviewsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_filled_star),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    tint = Neutral100
                )
                Text(
                    text = "5.0 ∙ 3 reviews",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Neutral100
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ReviewCard(
                    modifier = Modifier.weight(1f),
                    avatarRes = R.drawable.profile_avatar_explore_card,
                    name = "Sarah",
                    date = "2 weeks ago",
                    comment = "Great place to stay! Very clean and comfortable. Host was very responsive and helpful."
                )
                ReviewCard(
                    modifier = Modifier.weight(1f),
                    avatarRes = R.drawable.profile_avatar_ellipse,
                    name = "Mike",
                    date = "1 month ago",
                    comment = "Perfect location and exactly as described. Would definitely stay here again!"
                )
            }
        }

        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(1.dp, Neutral100),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Neutral100
            )
        ) {
            Text(
                text = "Show all 3 reviews",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Neutral100
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Neutral40)
        )
    }
}

@Composable
fun ReviewCard(
    modifier: Modifier = Modifier,
    avatarRes: Int,
    name: String,
    date: String,
    comment: String
) {
    Surface(
        modifier = modifier.height(140.dp),
        shape = RoundedCornerShape(12.dp),
        color = Neutral10,
        border = BorderStroke(1.dp, Neutral40)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(avatarRes),
                    contentDescription = name,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = Neutral100
                    )
                    Text(
                        text = date,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = Neutral70
                    )
                }
            }

            Text(
                text = comment,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                color = Neutral100,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun HostSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Hosted by Craig",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Neutral100
                        )
                        Text(
                            text = "Joined in January 2016",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                            color = Neutral70
                        )
                    }

                    Image(
                        painter = painterResource(R.drawable.avatar_ellipse_9),
                        contentDescription = "Craig",
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_filled_star),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Neutral100
                        )
                        Text(
                            text = "3 Reviews",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                            color = Neutral100
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_filled_verification),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Neutral100
                        )
                        Text(
                            text = "Identity verified",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                            color = Neutral100
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Military: US Air Force Reserves",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        color = Neutral100
                    )
                    Text(
                        text = "Civilian: Director of Distribution Operations at a NY hospital...",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        color = Neutral100
                    )
                    TextButton(
                        onClick = { },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Show more",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    textDecoration = TextDecoration.Underline
                                ),
                                color = Neutral100
                            )
                            Icon(
                                painter = painterResource(R.drawable.icon_outline_chevron_right),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Neutral100
                            )
                        }
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "During your stay",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = Neutral100
                    )
                    Text(
                        text = "I will be available on site. When I'm not on site you can reach me via text, phone or through Airbnb.",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        color = Neutral100
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Language: English",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        color = Neutral100
                    )
                    Text(
                        text = "Response rate: 100%",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        color = Neutral100
                    )
                    Text(
                        text = "Response time: within an hour",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        color = Neutral100
                    )
                }
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, Neutral100),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Neutral100
                )
            ) {
                Text(
                    text = "Contact Host",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Neutral100
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "To protect your payment, never transfer money or communicate outside of the Airbnb website or app.",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                color = Neutral100
            )

            Icon(
                painter = painterResource(R.drawable.icon_twotone_airbnb_protect),
                contentDescription = "Airbnb Protect",
                modifier = Modifier.size(24.dp),
                tint = Neutral100
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Neutral40)
        )
    }
}

@Composable
fun PoliciesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
    ) {
        PolicyItem(
            title = "Availability",
            subtitle = "Feb 13 - 14"
        )
        PolicyItem(
            title = "House rules",
            subtitle = "Check-in: After 1:00 PM"
        )
        PolicyItem(
            title = "Health & safety",
            subtitle = "Airbnb's COVID-19 safety practices apply\nSecurity camera/recording device"
        )
        PolicyItem(
            title = "Cancellation policy",
            subtitle = "Free cancellation before Feb 12.\nReview the Host's full cancellation policy which\napplies even if you cancel for illness or\ndisruptions caused by COVID-19."
        )
    }
}

@Composable
fun PolicyItem(
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Neutral100
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                color = Neutral70
            )
        }

        Icon(
            painter = painterResource(R.drawable.icon_outline_chevron_right),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Neutral100
        )
    }
}

@Composable
fun ReportSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Neutral10)
            .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_filled_flag),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Neutral100
        )

        TextButton(
            onClick = { },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Report this listing",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline
                ),
                color = Neutral100
            )
        }
    }
}

@Composable
fun BottomReservationBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Neutral10,
        shadowElevation = 8.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Neutral40)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "$32",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Neutral100
                        )
                        Text(
                            text = " night",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                            color = Neutral100
                        )
                    }

                    Text(
                        text = "Feb 13 - 14",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            textDecoration = TextDecoration.Underline
                        ),
                        color = Neutral100
                    )
                }

                Button(
                    onClick = { },
                    modifier = Modifier.height(48.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary70,
                        contentColor = Neutral10
                    ),
                    contentPadding = PaddingValues(horizontal = 24.dp)
                ) {
                    Text(
                        text = "Reserve",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PropertyDetailScreenPreview() {
    AirbnbTheme {
        PropertyDetailScreen()
    }
}
