package com.firebender.whatsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.whatsapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsappTheme {
                WhatsAppChatsScreen()
            }
        }
    }
}

data class ChatItem(
    val name: String,
    val message: String,
    val date: String,
    val avatar: Int,
    val hasRead: Boolean = true,
    val hasArrow: Boolean = true,
    val messageType: MessageType = MessageType.TEXT
)

enum class MessageType {
    TEXT, PHOTO, VOICE
}

val sampleChats = listOf(
    ChatItem(
        "Andrew Parker",
        "What kind of strategy is better?",
        "11/16/19",
        R.drawable.chat_oval_icon
    ),
    ChatItem(
        "Karen Castillo",
        "0:14",
        "11/15/19",
        R.drawable.chat_oval_background,
        messageType = MessageType.VOICE,
        hasArrow = false
    ),
    ChatItem(
        "Maximillian Jacobson",
        "Bro, I have a good idea!",
        "10/30/19",
        R.drawable.chat_message_bubble_oval
    ),
    ChatItem(
        "Martha Craig",
        "Photo",
        "10/28/19",
        R.drawable.chat_avatar_placeholder_oval,
        messageType = MessageType.PHOTO,
        hasArrow = false
    ),
    ChatItem(
        "Tabitha Potter",
        "Actually I wanted to check with you about your online business plan on our…",
        "8/25/19",
        R.drawable.chat_oval_icon_0,
        hasRead = false
    ),
    ChatItem(
        "Maisy Humphrey",
        "Welcome, to make design process faster, look at Pixsellz",
        "8/20/19",
        R.drawable.chat_avatar_oval_0,
        hasRead = false
    ),
    ChatItem("Kieron Dotson", "Ok, have a good trip!", "7/29/19", R.drawable.chat_avatar_oval)
)

@Composable
fun WhatsAppChatsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhatsAppBackground)
    ) {
        // Navigation Bar
        NavigationBar()

        // Actions Row
        ActionsRow()

        // Chat List
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(sampleChats.take(1)) { chat ->
                Box {
                    // Swipe action background
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        // More Action
                        Box(
                            modifier = Modifier
                                .width(75.dp)
                                .fillMaxHeight()
                                .background(Color(0xFFC6C6CC)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.more_icon),
                                    contentDescription = "More",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "More",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp)
                                )
                            }
                        }
                        // Archive Action
                        Box(
                            modifier = Modifier
                                .width(75.dp)
                                .fillMaxHeight()
                                .background(Color(0xFF3E70A7)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.archive_icon),
                                    contentDescription = "Archive",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Archive",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp)
                                )
                            }
                        }
                    }

                    // Chat item with partial slide
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = (-150).dp)
                            .background(WhatsAppWhite)
                    ) {
                        ChatListItem(chat = chat)
                    }
                }
                Divider(
                    color = WhatsAppSeparatorGray,
                    thickness = 0.33.dp,
                    modifier = Modifier
                        .padding(start = 72.dp)
                        .offset(x = (-150).dp)
                )
            }
            items(sampleChats.drop(1)) { chat ->
                ChatListItem(chat = chat)
                Divider(
                    color = WhatsAppSeparatorGray,
                    thickness = 0.33.dp,
                    modifier = Modifier.padding(start = 72.dp)
                )
            }
        }

        // Tab Bar
        TabBar()
    }
}

@Composable
fun NavigationBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = WhatsAppNavigationGray,
        shadowElevation = 0.33.dp
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
                style = MaterialTheme.typography.labelLarge,
                color = WhatsAppBlue
            )

            Text(
                text = "Chats",
                style = MaterialTheme.typography.headlineSmall,
                color = WhatsAppBlack
            )

            Icon(
                painter = painterResource(id = R.drawable.edit_icon),
                contentDescription = "Edit",
                tint = WhatsAppBlue,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ActionsRow() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = WhatsAppWhite,
        shadowElevation = 0.33.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Broadcast Lists",
                style = MaterialTheme.typography.labelLarge,
                color = WhatsAppBlue
            )

            Text(
                text = "New Group",
                style = MaterialTheme.typography.labelLarge,
                color = WhatsAppBlue
            )
        }
    }
}

@Composable
fun ChatListItem(chat: ChatItem) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = WhatsAppWhite
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Image(
                painter = painterResource(id = chat.avatar),
                contentDescription = "Avatar",
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
                // Name and Date Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = chat.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = WhatsAppBlack,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = chat.date,
                        style = MaterialTheme.typography.labelMedium,
                        color = WhatsAppGray
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Message and Status Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Message with icon prefix
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        when (chat.messageType) {
                            MessageType.PHOTO -> {
                                Icon(
                                    painter = painterResource(id = R.drawable.photo_icon),
                                    contentDescription = "Photo",
                                    tint = WhatsAppGray,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                            }

                            MessageType.VOICE -> {
                                Icon(
                                    painter = painterResource(id = R.drawable.voice_record_icon),
                                    contentDescription = "Voice",
                                    tint = WhatsAppVoiceGreen,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                            }

                            else -> {}
                        }

                        Text(
                            text = chat.message,
                            style = MaterialTheme.typography.bodyMedium,
                            color = WhatsAppGray,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    // Status indicators
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (chat.hasRead) {
                            Icon(
                                painter = painterResource(id = R.drawable.read_icon),
                                contentDescription = "Read",
                                tint = WhatsAppReadBlue,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }

                        if (chat.hasArrow) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_right_icon),
                                contentDescription = "Arrow",
                                tint = WhatsAppArrowGray,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TabBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = WhatsAppNavigationGray,
        shadowElevation = 0.33.dp
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

@Composable
fun TabItem(label: String, iconRes: Int, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = if (isSelected) WhatsAppBlue else WhatsAppTabGray,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) WhatsAppBlue else WhatsAppTabGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WhatsAppChatsScreenPreview() {
    WhatsappTheme {
        WhatsAppChatsScreen()
    }
}
