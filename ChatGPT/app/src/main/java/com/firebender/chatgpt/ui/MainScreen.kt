package com.firebender.chatgpt.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.chatgpt.R
import com.firebender.chatgpt.ui.theme.ChatGPTTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Navigation Bar
            NavigationBar()

            Spacer(modifier = Modifier.weight(1f))

            // Prompt Examples
            PromptExamplesSection()

            Spacer(modifier = Modifier.height(32.dp))

            // Leading Actions
            LeadingActionsSection()

            Spacer(modifier = Modifier.height(20.dp))

            // Input Field
            InputFieldSection()

            Spacer(modifier = Modifier.height(20.dp))

            // Headphones icon
            Icon(
                painter = painterResource(id = R.drawable.headphones),
                contentDescription = "Headphones",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))
        }

        // Animation section with OpenAI logo positioned to match Figma exactly
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.offset(x = 90.dp, y = 40.dp)
            ) {
                AnimationSection()
            }
        }
    }
}

@Composable
private fun NavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.menu),
            contentDescription = "Menu",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "ChatGPT",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = "4",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0x99, 0x3C, 0x3C, 0x43)
            )
            Text(
                text = "􀆊",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0x99, 0x3C, 0x3C, 0x43),
                letterSpacing = (-0.4).sp
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.edit),
            contentDescription = "Edit",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}

@Composable
private fun AnimationSection() {
    Box(
        modifier = Modifier.size(48.dp),
        contentAlignment = Alignment.Center
    ) {
        // Black circle background
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Black)
        )

        // OpenAI logo
        Icon(
            painter = painterResource(id = R.drawable.open_ai),
            contentDescription = "OpenAI",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
    }
}

@Composable
private fun PromptExamplesSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PromptExampleCard(
            title = "Design a database schema",
            subtitle = "for an online merch store",
            modifier = Modifier.weight(1f)
        )

        PromptExampleCard(
            title = "Explain airplain",
            subtitle = "to someone 5 years old",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun PromptExampleCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xF7, 0xF7, 0xF7)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp, 20.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                letterSpacing = (-0.4).sp,
                lineHeight = 21.sp
            )
            Text(
                text = subtitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0x99, 0x3C, 0x3C, 0x43),
                letterSpacing = (-0.4).sp,
                lineHeight = 21.sp
            )
        }
    }
}

@Composable
private fun LeadingActionsSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.camera),
            contentDescription = "Camera",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )

        Icon(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Image",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )

        Icon(
            painter = painterResource(id = R.drawable.folder),
            contentDescription = "Folder",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}

@Composable
private fun InputFieldSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        // Background rounded rectangle
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(32.dp))
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(32.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x99, 0x3C, 0x3C, 0x43),
                    shape = RoundedCornerShape(32.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Message",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0x4D, 0x3C, 0x3C, 0x3C),
                    lineHeight = 22.sp
                )

                Icon(
                    painter = painterResource(id = R.drawable.microphone_01),
                    contentDescription = "Microphone",
                    modifier = Modifier.size(20.dp),
                    tint = Color(0x99, 0x3C, 0x3C, 0x43)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ChatGPTTheme {
        MainScreen()
    }
}
