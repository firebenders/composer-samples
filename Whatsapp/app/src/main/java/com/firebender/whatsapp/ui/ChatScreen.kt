package com.firebender.whatsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.whatsapp.R
import com.firebender.whatsapp.ui.theme.*

data class Chat(
    val name: String,
    val message: String,
    val time: String,
    val avatar: Int,
    val hasReadIcon: Boolean = true,
    val hasArrow: Boolean = true,
    val isVoiceMessage: Boolean = false,
    val isPhoto: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    val chats = listOf(
        Chat("Martin Randolph", "Yes, 2pm is awesome", "11/19/19", R.drawable.chat_oval_icon_0),
        Chat(
            "Andrew Parker",
            "What kind of strategy is better?",
            "11/16/19",
            R.drawable.chat_oval_icon
        ),
        Chat(
            "Karen Castillo",
            "0:14",
            "11/15/19",
            R.drawable.chat_message_bubble_oval_0,
            isVoiceMessage = true
        ),
        Chat(
            "Maximillian Jacobson",
            "Bro, I have a good idea!",
            "10/30/19",
            R.drawable.chat_message_oval
        ),
        Chat(
            "Martha Craig",
            "Photo",
            "10/28/19",
            R.drawable.chat_message_bubble_oval,
            isPhoto = true
        ),
        Chat(
            "Tabitha Potter",
            "Actually I wanted to check with you about your online business plan on our…",
            "8/25/19",
            R.drawable.chat_message_oval_bg
        ),
        Chat(
            "Maisy Humphrey",
            "Welcome, to make design process\nfaster, look at Pixsellz",
            "8/20/19",
            R.drawable.chat_avatar_placeholder_oval
        ),
        Chat("Kieron Dotson", "Ok, have a good trip!", "7/29/19", R.drawable.chat_avatar_oval)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayEFEFF4)
    ) {
        // Navigation bar
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(0.33.dp),
            color = GrayF6F6F6
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Edit",
                    color = Blue007AFF,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Chats",
                    color = Black000000,
                    style = MaterialTheme.typography.headlineMedium
                )
                Icon(
                    painter = painterResource(id = R.drawable.edit_icon),
                    contentDescription = "Edit",
                    tint = Blue007AFF,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Action buttons
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(0.33.dp),
            color = White
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    modifier = Modifier.weight(1f),
                    color = GrayMore,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.more_icon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "More",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Surface(
                    modifier = Modifier.weight(1f),
                    color = BlueArchive,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.archive_icon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Archive",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            }
        }

        // Chat list
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(chats) { chat ->
                ChatItem(chat = chat)
                Divider(
                    color = Color(0x4A3C3C43),
                    thickness = 0.33.dp,
                    modifier = Modifier.padding(start = 72.dp)
                )
            }
        }

        // Tab bar
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(0.33.dp),
            color = GrayF6F6F6
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TabItem("Status", R.drawable.home_icon, false)
                TabItem("Calls", R.drawable.calls_icon, false)
                TabItem("Camera", R.drawable.camera_icon, false)
                TabItem("Chats", R.drawable.chats_icon, true)
                TabItem("Settings", R.drawable.settings_icon, false)
            }
        }
    }
}

@Composable
fun ChatItem(chat: Chat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Image(
            painter = painterResource(id = chat.avatar),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Content
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = chat.name,
                    color = Black000000,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = chat.time,
                    color = Gray8E8E93,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    if (chat.isPhoto) {
                        Icon(
                            painter = painterResource(id = R.drawable.photo_icon),
                            contentDescription = null,
                            tint = Gray8E8E93,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    } else if (chat.isVoiceMessage) {
                        Icon(
                            painter = painterResource(id = R.drawable.voice_record_icon),
                            contentDescription = null,
                            tint = GreenVoice,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }

                    Text(
                        text = chat.message,
                        color = Gray8E8E93,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (chat.hasReadIcon) {
                        Icon(
                            painter = painterResource(id = R.drawable.read_check_icon),
                            contentDescription = null,
                            tint = BlueRGB52_151_250,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    if (chat.hasArrow) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right_icon),
                            contentDescription = null,
                            tint = ArrowGray,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TabItem(label: String, iconRes: Int, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = if (isSelected) Blue007AFF else Color(0xA8545458),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            color = if (isSelected) Blue007AFF else Color(0xA8545458),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    WhatsappTheme {
        ChatScreen()
    }
}
