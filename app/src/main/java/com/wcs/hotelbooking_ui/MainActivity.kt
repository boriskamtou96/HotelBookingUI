package com.wcs.hotelbooking_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wcs.hotelbooking_ui.ui.theme.HotelBookingUITheme


private val menus: List<String> = listOf(
    "City-Center",
    "Luxury",
    "Luxury",
    "Instant Booking",
    "Exclusive Deal",
    "Early Bird Discount",
    "Romantic Getaway",
    "24/7 Support",
)

private val offers: Map<Int, String> = mapOf(
    R.drawable.bed to "2 Bed",
    R.drawable.breakfast to "Breakfast",
    R.drawable.cutlery to "Kitchen",
    R.drawable.serving_dish to "Dinner",
    R.drawable.snowflake to "Air Conditionning",
    R.drawable.television to "TV",
    R.drawable.wi_fi_icon to "Wifi",
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            HotelBookingUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HotelBookingApp(
                        windowClass = windowSizeClass,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HotelBanner(windowClass: WindowSizeClass, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.living_room),
            contentDescription = "Photo de l'hôtel California Strawberry",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth()
                .background(color = Color.White.copy(alpha = 0.6f))
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(
                    space = 4.dp,
                    alignment = Alignment.Bottom
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Hotel California Strawberry",
                    fontWeight = FontWeight.Bold,
                    fontSize = when(windowClass.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> 18.sp
                        WindowWidthSizeClass.Medium -> 22.sp
                        WindowWidthSizeClass.Expanded -> 28.sp
                        else -> 18.sp
                    },
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                HotelInfos(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Localisation",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    info = "Los Angeles, California"
                )

                HotelInfos(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Note",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    info = "4.8 (1,245 reviews)"
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = when(windowClass.widthSizeClass) {
                                WindowWidthSizeClass.Compact -> 16.sp
                                WindowWidthSizeClass.Medium -> 18.sp
                                WindowWidthSizeClass.Expanded -> 22.sp
                                else -> 16.sp
                            },
                        )
                    ) {
                        append("$500/")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = when(windowClass.widthSizeClass) {
                                WindowWidthSizeClass.Compact -> 12.sp
                                WindowWidthSizeClass.Medium -> 16.sp
                                WindowWidthSizeClass.Expanded -> 18.sp
                                else -> 16.sp
                            }
                        )
                    ) {
                        append("night")
                    }
                }
            )
        }
    }
}

@Composable
fun HotelInfos(icon: @Composable () -> Unit, info: String, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        icon()
        Spacer(Modifier.width(8.dp))
        Text(
            text = info,
            fontSize = 12.sp,
        )
    }
}

@Composable
fun HorizontalBar(modifier: Modifier = Modifier) {
    HorizontalDivider(modifier.padding(vertical = 14.dp), color = Color.Gray.copy(alpha = 0.5f))
}

@Composable
fun OptionsMenu(modifier: Modifier = Modifier) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        menus.forEach { it ->
            AssistChip(
                onClick = {},
                label = {
                    Text(
                        text = it,
                    )
                }
            )
        }
    }
}

@Composable
fun HotelBookingApp(windowClass: WindowSizeClass, modifier: Modifier = Modifier) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
        modifier = modifier
    ) {
        item {
            HotelBanner(windowClass)
        }
        item {
            HorizontalBar()
        }
        item { OptionsMenu() }
        item {
            HorizontalBar()
        }

        item {
            Text(
                text = """
                    The advertisement features a vibrant and inviting design, showcasing the Hotel California Strawberry nestled in the heart of Los Angeles. Surrounded by the iconic Hollywood Sign, Griffith Park, and stunning beaches, the hotel is perfectly located for guests to explore L.A.’s best attractions.
                """.trimIndent(),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            Text(
                text = "What we offer",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                items(offers.entries.toList()) { (imageResourceId, label) ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color.LightGray)
                            .padding(horizontal = 16.dp, vertical = 13.dp)
                    ) {
                        Icon(
                            painter = painterResource(imageResourceId),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(label)
                    }
                }
            }
        }

        item {
            Button(
                onClick = {},
                modifier = Modifier
                    .widthIn(max = 300.dp)
                    .fillMaxWidth()
            ) {
                Text("Book Now")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HotelBookingPreview() {
    HotelBookingUITheme {

        HotelBookingApp(
            windowClass = WindowSizeClass.calculateFromSize(
                size = DpSize(400.dp, 800.dp)
            )
        )
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, showSystemUi = true, device = Devices.NEXUS_10)
@Composable
private fun HotelBookingPreviewTablet() {
    HotelBookingUITheme {
        HotelBookingApp(
            windowClass = WindowSizeClass.calculateFromSize(
                size = androidx.compose.ui.unit.DpSize(1280.dp, 800.dp)
            )
        )
    }
}