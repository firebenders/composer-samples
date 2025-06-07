package com.firebender.whatsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.whatsapp.ui.theme.WhatsAppBlue
import com.firebender.whatsapp.ui.theme.WhatsAppDateBackground
import com.firebender.whatsapp.ui.theme.WhatsAppDateText
import com.firebender.whatsapp.ui.theme.WhatsAppGray
import com.firebender.whatsapp.ui.theme.WhatsAppGreen
import com.firebender.whatsapp.ui.theme.WhatsAppLightGray
import com.firebender.whatsapp.ui.theme.WhatsappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsappTheme {
                WhatsAppChatScreen()
            }
        }
    }
}

data class Message(
    val text: String,
    val time: String,
    val isSent: Boolean,
    val hasReadReceipt: Boolean = false,
    val fileName: String? = null,
    val fileSize: String? = null,
    val fileType: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatsAppChatScreen() {
    val messages = listOf(
        Message("Good morning!", "10:10", true, true),
        Message("Japan looks amazing!", "10:10", true, true),
        Message("", "10:15", true, true, "IMG_0475", "2.4 MB", "png"),
        Message("", "10:15", true, true, "IMG_0481", "2.8 MB", "png"),
        Message("Do you know what time is it?", "11:40", false),
        Message("It's morning in Tokyo 😎", "11:43", true, true),
        Message("What is the most popular meal in Japan?", "11:45", false),
        Message("Do you like it?", "11:45", false),
        Message("I think top two are:", "11:50", true, true),
        Message("", "11:51", true, true, "IMG_0483", "2.8 MB", "png"),
        Message("", "11:51", true, true, "IMG_0484", "2.6 MB", "png")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhatsAppLightGray)
    ) {
        // Contact Header
        ContactHeader()

        // Chat Background with Messages
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // Background pattern
            Image(
                painter = painterResource(id = R.drawable.whatsapp_chat_background_pattern),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                // Date header
                item {
                    DateHeader("Fri, Jul 26")
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(messages) { message ->
                    MessageBubble(message = message)
                }
            }
        }

        // Message Input Area
        MessageInputArea()
    }
}

@Composable
fun ContactHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(WhatsAppLightGray)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back_icon),
            contentDescription = "Back",
            tint = WhatsAppBlue,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Image(
            painter = painterResource(id = R.drawable.whatsapp_contact_action_oval),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Martha Craig",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = "tap here for contact info",
                style = MaterialTheme.typography.labelLarge,
                color = WhatsAppGray
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.video_call_icon),
            contentDescription = "Video call",
            tint = WhatsAppBlue,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            painter = painterResource(id = R.drawable.call_icon),
            contentDescription = "Call",
            tint = WhatsAppBlue,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun DateHeader(date: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = WhatsAppDateBackground)
        ) {
            Text(
                text = date,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                style = MaterialTheme.typography.labelMedium,
                color = WhatsAppDateText
            )
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (message.isSent) 60.dp else 0.dp,
                end = if (message.isSent) 0.dp else 60.dp
            ),
        horizontalArrangement = if (message.isSent) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = if (message.fileName != null) 140.dp else 200.dp),
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp,
                bottomStart = if (message.isSent) 18.dp else 4.dp,
                bottomEnd = if (message.isSent) 4.dp else 18.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isSent) WhatsAppGreen else Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = if (message.fileName != null) 8.dp else 10.dp,
                    vertical = if (message.fileName != null) 6.dp else 8.dp
                )
            ) {
                if (message.fileName != null) {
                    // File message
                    FileAttachment(
                        fileName = message.fileName,
                        fileSize = message.fileSize ?: "",
                        fileType = message.fileType ?: ""
                    )
                } else {
                    // Text message
                    Text(
                        text = message.text,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            lineHeight = 20.sp
                        ),
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = message.time,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        color = Color.Black.copy(alpha = 0.5f)
                    )

                    if (message.isSent && message.hasReadReceipt) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.read_receipt_icon),
                            contentDescription = "Read receipt",
                            modifier = Modifier.size(12.dp),
                            tint = WhatsAppBlue
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FileAttachment(fileName: String, fileSize: String, fileType: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier.size(28.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF2F2F7)
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.document_icon),
                    contentDescription = "File",
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(6.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = fileName,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp),
                color = Color.Black.copy(alpha = 0.87f)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = fileSize,
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 11.sp),
                    color = Color.Black.copy(alpha = 0.5f)
                )
                Text(
                    text = " • ",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 11.sp),
                    color = Color.Black.copy(alpha = 0.5f)
                )
                Text(
                    text = fileType,
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 11.sp),
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun MessageInputArea() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(WhatsAppLightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add_icon),
            contentDescription = "Add",
            tint = WhatsAppBlue,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Card(
            modifier = Modifier
                .weight(1f)
                .height(36.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.45f)),
            border = androidx.compose.foundation.BorderStroke(0.5.dp, WhatsAppGray)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.stickers_icon),
                    contentDescription = "Stickers",
                    tint = WhatsAppBlue,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(id = R.drawable.camera_icon),
            contentDescription = "Camera",
            tint = WhatsAppBlue,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(id = R.drawable.record_audio_icon),
            contentDescription = "Record audio",
            tint = WhatsAppBlue,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WhatsAppChatScreenPreview() {
    WhatsappTheme {
        WhatsAppChatScreen()
    }
}
