package com.firebender.whatsapp.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.whatsapp.R
import com.firebender.whatsapp.ui.theme.*

data class ChatMessage(
    val text: String,
    val time: String,
    val isOutgoing: Boolean,
    val isRead: Boolean = false,
    val fileName: String? = null,
    val fileSize: String? = null,
    val fileType: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailScreen() {
    val messages = listOf(
        ChatMessage(
            text = "Good morning!",
            time = "10:10",
            isOutgoing = true,
            isRead = true
        ),
        ChatMessage(
            text = "Japan looks amazing!",
            time = "10:10",
            isOutgoing = true,
            isRead = true
        ),
        ChatMessage(
            text = "IMG_0475",
            time = "10:15",
            isOutgoing = true,
            isRead = true,
            fileName = "IMG_0475",
            fileSize = "2.4",
            fileType = "png"
        ),
        ChatMessage(
            text = "IMG_0481",
            time = "10:15",
            isOutgoing = true,
            isRead = true,
            fileName = "IMG_0481",
            fileSize = "2.8",
            fileType = "png"
        ),
        ChatMessage(
            text = "Do you know what time is it?",
            time = "11:40",
            isOutgoing = false
        ),
        ChatMessage(
            text = "It's morning in Tokyo 😎",
            time = "11:43",
            isOutgoing = true,
            isRead = true
        ),
        ChatMessage(
            text = "What is the most popular meal in Japan?",
            time = "11:45",
            isOutgoing = false
        ),
        ChatMessage(
            text = "Do you like it?",
            time = "11:45",
            isOutgoing = false
        ),
        ChatMessage(
            text = "I think top two are:",
            time = "11:50",
            isOutgoing = true,
            isRead = true
        ),
        ChatMessage(
            text = "IMG_0483",
            time = "11:51",
            isOutgoing = true,
            isRead = true,
            fileName = "IMG_0483",
            fileSize = "2.8",
            fileType = "png"
        ),
        ChatMessage(
            text = "IMG_0484",
            time = "11:51",
            isOutgoing = true,
            isRead = true,
            fileName = "IMG_0484",
            fileSize = "2.6",
            fileType = "png"
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFF4))
    ) {
        // Background pattern
        Image(
            painter = painterResource(id = R.drawable.whatsapp_chat_background_pattern),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Contact header
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFF6F6F6),
                shadowElevation = 0.33.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back button
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right_icon),
                        contentDescription = "Back",
                        tint = Color(0xFF007AFF),
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // Profile image
                    Image(
                        painter = painterResource(id = R.drawable.contact_action_oval),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // Contact info
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Martha Craig",
                            color = Color(0xFF000000),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = (-0.3).sp
                        )
                        Text(
                            text = "tap here for contact info",
                            color = Color(0xFF8E8E93),
                            fontSize = 12.sp,
                            letterSpacing = (-0.01).sp
                        )
                    }

                    // Action buttons
                    Icon(
                        painter = painterResource(id = R.drawable.video_call_icon),
                        contentDescription = "Video call",
                        tint = Color(0xFF007AFF),
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.call_icon),
                        contentDescription = "Call",
                        tint = Color(0xFF007AFF),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Date separator
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    color = Color(0xFFDDDDE8),
                    shape = RoundedCornerShape(8.dp),
                    shadowElevation = 2.dp
                ) {
                    Text(
                        text = "Fri, Jul 26",
                        color = Color(0xFF3C3C43),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            // Messages
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages) { message ->
                    MessageBubble(message = message)
                }
            }

            // Message input
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFF6F6F6),
                shadowElevation = 0.33.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Camera button
                    Icon(
                        painter = painterResource(id = R.drawable.camera_icon_0),
                        contentDescription = "Camera",
                        tint = Color(0xFF007AFF),
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Message input field
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(32.dp),
                        color = Color(0x73FFFFFF),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(0.5.dp, Color(0xFF8E8E93))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.voice_record_icon),
                                contentDescription = "Stickers",
                                tint = Color(0xFF007AFF),
                                modifier = Modifier
                                    .size(16.dp)
                                    .align(Alignment.CenterEnd)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Add button
                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "Add",
                        tint = Color(0xFF007AFF),
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Record audio button
                    Icon(
                        painter = painterResource(id = R.drawable.record_audio_icon),
                        contentDescription = "Record audio",
                        tint = Color(0xFF007AFF),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MessageBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isOutgoing) Arrangement.End else Arrangement.Start
    ) {
        if (message.isOutgoing) {
            Spacer(modifier = Modifier.width(64.dp))
        }

        Surface(
            color = if (message.isOutgoing) Color(0xFFDCF7C5) else Color(0xFFFAFAFA),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 1.6.dp,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                if (message.fileName != null) {
                    // File attachment
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = Color(0x1F76767D),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.file_icon),
                                    contentDescription = null,
                                    tint = Color(0xFF007AFF),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = message.fileName,
                                color = Color(0xFF000000),
                                fontSize = 16.sp,
                                letterSpacing = (-0.3).sp,
                                fontWeight = FontWeight.Normal
                            )

                            Text(
                                text = "${message.fileType} • ${message.fileSize} MB",
                                color = Color(0x80000000),
                                fontSize = 11.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                } else {
                    // Text message
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = message.text,
                            color = Color(0xFF000000),
                            fontSize = 16.sp,
                            letterSpacing = (-0.3).sp,
                            modifier = Modifier.weight(1f, fill = false)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = message.time,
                                color = Color(0x40000000),
                                fontSize = 11.sp,
                                letterSpacing = 0.5.sp
                            )

                            if (message.isOutgoing && message.isRead) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.read_icon),
                                    contentDescription = null,
                                    tint = Color(0xFF3497FA),
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }

                // Time and read indicator for file messages
                if (message.fileName != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = message.time,
                            color = Color(0x40000000),
                            fontSize = 11.sp,
                            letterSpacing = 0.5.sp
                        )

                        if (message.isOutgoing && message.isRead) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.read_icon),
                                contentDescription = null,
                                tint = Color(0xFF3497FA),
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                }
            }
        }

        if (!message.isOutgoing) {
            Spacer(modifier = Modifier.width(64.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatDetailScreenPreview() {
    WhatsappTheme {
        ChatDetailScreen()
    }
}
