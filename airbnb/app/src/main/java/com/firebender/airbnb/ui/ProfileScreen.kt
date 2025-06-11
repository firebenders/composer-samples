package com.firebender.airbnb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.airbnb.R
import com.firebender.airbnb.ui.theme.*

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutral10)
                .verticalScroll(rememberScrollState())
        ) {
            // User Profile Section (Top)
            UserProfileSection()

            // Earn Money Section 
            EarnMoneySection()

            // Account Settings Section 
            AccountSettingsSection()

            // Add bottom padding for navigation bar
            Spacer(modifier = Modifier.height(100.dp))
        }

        // Navigation Bar (Bottom)
        NavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            activeTab = "Profile"
        )
    }
}

@Composable
fun AccountSettingsSection() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Neutral10
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Title
            Text(
                text = "Account Settings",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 28.sp
                ),
                color = Neutral100
            )

            // Menu Items
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                MenuSettingsItem(
                    icon = R.drawable.icon_outline_user_1,
                    title = "Personal information"
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Neutral40
                )

                MenuSettingsItem(
                    icon = R.drawable.icon_outline_money,
                    title = "Payments and payouts"
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Neutral40
                )

                MenuSettingsItem(
                    icon = R.drawable.icon_outline_translate,
                    title = "Translation"
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Neutral40
                )

                MenuSettingsItem(
                    icon = R.drawable.icon_outline_bell,
                    title = "Notifications"
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Neutral40
                )

                MenuSettingsItem(
                    icon = R.drawable.icon_outline_lock,
                    title = "Privacy and sharing"
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Neutral40
                )

                MenuSettingsItem(
                    icon = R.drawable.icon_outline_briefcase,
                    title = "Travel for work"
                )
            }
        }
    }
}

@Composable
fun MenuSettingsItem(
    icon: Int,
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = Neutral100
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 22.sp
                ),
                color = Neutral100
            )
        }

        Icon(
            painter = painterResource(R.drawable.icon_outline_chevron_right),
            contentDescription = "Navigate",
            modifier = Modifier.size(24.dp),
            tint = Neutral70
        )
    }
}

@Composable
fun EarnMoneySection() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Neutral10
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Neutral10,
                    RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
                )
                .padding(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Neutral10)
                    .padding(bottom = 1.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Neutral40)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_twotone_earn_money),
                    contentDescription = "Earn money",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Earn money from your extra space",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 22.sp
                        ),
                        color = Neutral90
                    )

                    Text(
                        text = "Learn more",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 18.sp,
                            textDecoration = TextDecoration.Underline
                        ),
                        color = Neutral100
                    )
                }
            }
        }
    }
}

@Composable
fun UserProfileSection() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Neutral10
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.profile_avatar_ellipse_0),
                contentDescription = "Profile picture",
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "John",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 42.sp
                    ),
                    color = Neutral100
                )

                Text(
                    text = "View profile",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    color = Neutral100
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    AirbnbTheme {
        ProfileScreen()
    }
}
