package com.firebender.whatsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextOverflow
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
    val isVoiceMessage: Boolean = false,
    val isPhoto: Boolean = false,
    val isSelected: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    val chats = listOf(
        Chat(
            "Martin Randolph",
            "Yes, 2pm is awesome",
            "11/19/19",
            R.drawable.chat_oval_icon_0,
            hasReadIcon = true
        ),
        Chat(
            "Andrew Parker",
            "What kind of strategy is better?",
            "11/16/19",
            R.drawable.chat_oval_icon,
            hasReadIcon = true
        ),
        Chat(
            "Karen Castillo",
            "0:14",
            "11/15/19",
            R.drawable.chat_message_bubble_oval_0,
            isVoiceMessage = true,
            hasReadIcon = false
        ),
        Chat(
            "Maximillian Jacobson",
            "Bro, I have a good idea! ",
            "10/30/19",
            R.drawable.chat_message_oval,
            hasReadIcon = true
        ),
        Chat(
            "Martha Craig",
            "Photo",
            "10/28/19",
            R.drawable.chat_message_bubble_oval,
            isPhoto = true,
            hasReadIcon = false
        ),
        Chat(
            "Tabitha Potter",
            "Actually I wanted to check with you about your online business plan on…",
            "8/25/19",
            R.drawable.chat_message_oval_bg,
            hasReadIcon = false
        ),
        Chat(
            "Maisy Humphrey",
            "     \nprocess faster, look at Pixsellz",
            "8/20/19",
            R.drawable.chat_avatar_placeholder_oval,
            hasReadIcon = true
        ),
        Chat(
            "Kieron Dotson",
            "Ok, have a good trip!",
            "7/29/19",
            R.drawable.chat_avatar_oval,
            hasReadIcon = true
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayEFEFF4)
    ) {
        // Navigation bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = White
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Chats",
                    color = Black000000,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.23).sp,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Text(
                    text = "Done",
                    color = Blue007AFF,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = (-0.4).sp,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }

        // Top Actions bar (Broadcast Lists, New Group)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(0.33.dp, spotColor = Color(0x4A656568)),
            color = GrayF6F6F6
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Broadcast Lists",
                    color = Color(0xFFC7C7CC),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = (-0.4).sp
                )
                Text(
                    text = "New Group",
                    color = Color(0xFFC7C7CC),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = (-0.4).sp
                )
            }
        }

        // Chat list
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(chats) { chat ->
                ChatItem(chat = chat)
                HorizontalDivider(
                    color = Color(0x4A3C3C43).copy(alpha = 0.29f),
                    thickness = 0.33.dp
                )
            }
        }

        // Bottom Actions (Archive, Read All, Delete)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(0.33.dp, spotColor = Color(0x4A3C3C43)),
            color = White
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Archive",
                    color = Color(0xFFC7C7CC),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = (-0.4).sp
                )
                Text(
                    text = "Read All",
                    color = Color(0xFFC7C7CC),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = (-0.4).sp
                )
                Text(
                    text = "Delete",
                    color = Color(0xFFC7C7CC),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = (-0.4).sp
                )
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
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Selection circle
        Box(
            modifier = Modifier
                .size(18.dp)
                .border(
                    width = 1.5.dp,
                    color = Color(0x993C3C43),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            // Empty circle for now - can be filled when selected
        }

        Spacer(modifier = Modifier.width(16.dp))

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
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = (-0.33).sp,
                    lineHeight = 21.sp
                )
                Text(
                    text = chat.time,
                    color = Gray8E8E93,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = (-0.15).sp,
                    lineHeight = 16.7.sp
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

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
                        text = if (chat.name == "Maisy Humphrey") {
                            "Welcome, to make design\nprocess faster, look at Pixsellz"
                        } else {
                            chat.message
                        },
                        color = Gray8E8E93,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = (-0.15).sp,
                        lineHeight = if (chat.name == "Maisy Humphrey") 21.sp else 16.7.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (chat.hasReadIcon) {
                    Icon(
                        painter = painterResource(id = R.drawable.read_check_icon),
                        contentDescription = null,
                        tint = BlueRGB52_151_250,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    WhatsappTheme {
        ChatScreen()
    }
}
