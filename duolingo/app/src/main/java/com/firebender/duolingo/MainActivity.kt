package com.firebender.duolingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebender.duolingo.ui.theme.BackArrowColor
import com.firebender.duolingo.ui.theme.DefaultLanguageBorder
import com.firebender.duolingo.ui.theme.DuolingoGreen
import com.firebender.duolingo.ui.theme.DuolingoTheme
import com.firebender.duolingo.ui.theme.ProgressActiveColor
import com.firebender.duolingo.ui.theme.ProgressBackgroundColor
import com.firebender.duolingo.ui.theme.SelectedLanguageBackground
import com.firebender.duolingo.ui.theme.SelectedLanguageBorder
import com.firebender.duolingo.ui.theme.SeparatorColor
import com.firebender.duolingo.ui.theme.TextPrimary
import com.firebender.duolingo.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuolingoTheme {
                LessonScreen()
            }
        }
    }
}

data class Language(
    val name: String,
    val subtitle: String,
    val imageRes: Int
)

@Composable
fun LanguageSelectionScreen() {
    var selectedLanguageIndex by remember { mutableStateOf(1) } // French is selected by default

    val languages = listOf(
        Language("Spanish", "", R.drawable.list_item_image_frame),
        Language("French", "", R.drawable.list_item_image_frame_0),
        Language("Spanish", "", R.drawable.list_item_image_frame_1),
        Language("Spanish", "", R.drawable.list_item_image_frame_2),
        Language("Spanish", "", R.drawable.list_item_image_frame_3),
        Language("Spanish", "", R.drawable.list_item_image_frame_4),
        Language("Spanish", "", R.drawable.list_item_image_frame_5)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = White
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Top Navigation Bar
                TopNavigationBar()

                // Duo Voice Section
                DuoVoiceSection()

                // Separator
                SeparatorSection()

                // Section Title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "For English speakers",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black
                        ),
                        color = TextPrimary
                    )
                }

                // Language List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 100.dp),
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    itemsIndexed(languages) { index, language ->
                        LanguageListItem(
                            language = language,
                            isSelected = index == selectedLanguageIndex,
                            onClick = { selectedLanguageIndex = index }
                        )
                    }
                }
            }

            // Bottom Button Layout
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp)
            ) {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = DefaultLanguageBorder
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { /* TODO: Handle continue */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(12.dp),
                            spotColor = Color(0xFF5AA703)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DuolingoGreen
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "CONTINUE",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = White
                    )
                }
            }
        }
    }
}

@Composable
fun TopNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Back Arrow
        Icon(
            painter = painterResource(id = R.drawable.arrow_back_fill),
            contentDescription = "Back",
            tint = BackArrowColor,
            modifier = Modifier.size(24.dp)
        )

        // Progress Bar
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Active progress segment (filled circle)
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        ProgressActiveColor,
                        shape = CircleShape
                    )
            )

            // Connected progress track
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .background(
                        ProgressBackgroundColor,
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}

@Composable
fun DuoVoiceSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Duo mascot
        Image(
            painter = painterResource(id = R.drawable.mask_group_7),
            contentDescription = "Duo mascot",
            modifier = Modifier.size(64.dp)
        )

        // Speech bubble
        Box(
            modifier = Modifier
                .background(
                    Color(0xFFF7F7F7),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 2.dp,
                    color = Color(0xFFE5E5E5),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = "What would you like\nto learn?",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 20.sp
                ),
                color = TextPrimary
            )
        }
    }
}

@Composable
fun SeparatorSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 2.dp,
            color = SeparatorColor
        )

        Spacer(modifier = Modifier.width(8.dp))

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 2.dp,
            color = SeparatorColor
        )
    }
}

@Composable
fun LanguageListItem(
    language: Language,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) SelectedLanguageBackground else White
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 2.dp,
            color = if (isSelected) SelectedLanguageBorder else DefaultLanguageBorder
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 0.dp else 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Language flag/image
            Image(
                painter = painterResource(id = language.imageRes),
                contentDescription = language.name,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            // Language details
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = language.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = if (isSelected) ProgressActiveColor else TextPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LanguageSelectionScreenPreview() {
    DuolingoTheme {
        LanguageSelectionScreen()
    }
}

private val TextSecondary = Color(0xFF777777)
