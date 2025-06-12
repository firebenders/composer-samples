package com.firebender.whatsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.whatsapp.R
import com.firebender.whatsapp.ui.theme.*

@Composable
fun EditProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFF4))
    ) {
        // Navigation Bar
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 0.33.dp,
                    spotColor = Color(0x4AA6A6AA)
                ),
            color = Color(0xFFF6F6F6)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow_icon),
                        contentDescription = "Back",
                        tint = Blue007AFF,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Settings",
                        color = Blue007AFF,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = (-0.4).sp
                    )
                }

                Text(
                    text = "Edit Profile",
                    color = Black000000,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = (-0.4).sp
                )

                // Empty space for center alignment
                Spacer(modifier = Modifier.width(48.dp))
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Edit Name Section
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 0.33.dp,
                    spotColor = Color(0x4A3C3C43)
                ),
            color = White
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.edit_profile_name_oval_bg),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = "Enter your name and add an optional profile picture",
                            color = Color(0xFF8E8E93),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 0.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Sabohiddin",
                            color = Black000000,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = (-0.33).sp
                        )
                    }
                }

                Text(
                    text = "Edit",
                    color = Blue007AFF,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = (-0.2).sp,
                    modifier = Modifier.clickable { }
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Phone Number and About Section (combined)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 0.33.dp,
                    spotColor = Color(0x4A3C3C43)
                ),
            color = White
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Phone Number Section
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = "PHONE NUMBER",
                        color = Color(0xFF636366),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = (-0.01).sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "+998 90 943 32 00",
                        color = Black000000,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = (-0.33).sp
                    )
                }

                // Divider
                HorizontalDivider(
                    color = Color(0x4A3C3C43).copy(alpha = 0.29f),
                    thickness = 0.33.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                // About Section
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = "ABOUT",
                        color = Color(0xFF636366),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = (-0.01).sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Digital goodies designer - Pixsellz",
                        color = Black000000,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = (-0.33).sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Tab Bar
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 0.33.dp,
                    spotColor = Color(0x4AA6A6AA)
                ),
            color = Color(0xFFF6F6F6)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TabItem(
                    icon = R.drawable.status_icon,
                    label = "Status",
                    isSelected = false
                )
                TabItem(
                    icon = R.drawable.calls_icon,
                    label = "Calls",
                    isSelected = false
                )
                TabItem(
                    icon = R.drawable.camera_icon,
                    label = "Camera",
                    isSelected = false
                )
                TabItem(
                    icon = null,
                    label = "Chats",
                    isSelected = false
                )
                TabItem(
                    icon = R.drawable.settings_icon_0,
                    label = "Settings",
                    isSelected = true
                )
            }
        }
    }
}

@Composable
fun TabItem(
    icon: Int?,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                tint = if (isSelected) Blue007AFF else Color(0xFF979797).copy(alpha = 0.65f),
                modifier = Modifier.size(24.dp)
            )
        } else {
            // For chats tab without icon
            Box(modifier = Modifier.size(24.dp))
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            color = if (isSelected) Blue007AFF else Color(0xFF545458).copy(alpha = 0.65f),
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    WhatsappTheme {
        EditProfileScreen()
    }
}
