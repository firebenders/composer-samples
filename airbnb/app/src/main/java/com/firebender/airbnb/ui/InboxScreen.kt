package com.firebender.airbnb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.*

@Composable
fun InboxScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutral10)
        ) {
            // Status Bar Space
            Spacer(modifier = Modifier.height(44.dp))

            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Inbox",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium,
                    color = Neutral100
                )
            }

            // Tab Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    // Messages Tab (Active)
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 12.dp)
                        ) {
                            Text(
                                text = "Messages",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Neutral100
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            // Badge
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = Neutral100,
                                        shape = CircleShape
                                    )
                                    .size(18.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "1",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Neutral10
                                )
                            }
                        }
                        Divider(
                            color = Neutral100,
                            thickness = 2.dp,
                            modifier = Modifier.width(82.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Notifications Tab (Inactive)
                    Column {
                        Text(
                            text = "Notifications",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF989B9D),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                }

                // Bottom border
                Divider(
                    color = Neutral20,
                    thickness = 1.dp,
                    modifier = Modifier.offset(y = (-1).dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Chat List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // First Chat - Craig (Simple format)
                ChatItem(
                    isAirbnb = true,
                    name = "Craig",
                    message = "Alright got it we'll make do thanks a lot",
                    avatarResource = null
                )

                Divider(color = Neutral40, thickness = 1.dp)

                // Second Chat - Craig with details
                ChatItemWithDetails(
                    name = "Craig",
                    location = "Yonkers",
                    message = "Airbnb update: Reservation canceled",
                    status = "Canceled",
                    date = "Feb 13 - 14, 2023",
                    avatarResource = R.drawable.chat_avatar_placeholder
                )

                Divider(color = Neutral40, thickness = 1.dp)

                // Third Chat - Erin
                ChatItemWithDetails(
                    name = "Erin",
                    location = "New York",
                    message = "New date and time request",
                    status = "Request pending",
                    date = null,
                    avatarResource = R.drawable.chat_avatar_ellipse
                )
            }

            Spacer(modifier = Modifier.height(100.dp))
        }

        // Navigation Bar (bottom) - using the modified version to show Inbox as selected
        NavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            activeTab = "Inbox"
        )
    }
}

@Composable
fun ChatItem(
    isAirbnb: Boolean,
    name: String,
    message: String,
    avatarResource: Int?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        // Avatar
        if (isAirbnb) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(
                        color = Neutral100,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.company_logo),
                    contentDescription = "Airbnb Logo",
                    tint = Neutral10,
                    modifier = Modifier.size(26.dp)
                )
            }
        } else if (avatarResource != null) {
            Image(
                painter = painterResource(id = avatarResource),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Content
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Neutral100
            )
            Text(
                text = message,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Neutral100
            )
        }
    }
}

@Composable
fun ChatItemWithDetails(
    name: String,
    location: String,
    message: String,
    status: String,
    date: String?,
    avatarResource: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        // Avatar
        Image(
            painter = painterResource(id = avatarResource),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Content
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            // Name & Location
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Neutral100
                )
                Text(
                    text = "∙",
                    fontSize = 14.sp,
                    color = Neutral70
                )
                Text(
                    text = location,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Neutral70
                )
            }

            // Message
            Text(
                text = message,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Neutral100
            )

            // Status & Date
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = status,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Neutral70
                )
                if (date != null) {
                    Text(
                        text = "∙",
                        fontSize = 14.sp,
                        color = Neutral70
                    )
                    Text(
                        text = date,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Neutral70
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InboxScreenPreview() {
    AirbnbTheme {
        InboxScreen()
    }
}
