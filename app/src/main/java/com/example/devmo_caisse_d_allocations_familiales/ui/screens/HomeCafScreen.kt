package com.example.devmo_caisse_d_allocations_familiales.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.CafBorder
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.CafLightGray
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.CafNavGray
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.CafPrimary
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.CafSubText
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.CafText
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.CafTitle
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.CafTurquoise
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.CafWhite

@Composable
fun HomeCafScreen() {
    var accessibilityEnabled by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = CafWhite,
        bottomBar = {
            BottomNavigationBar()
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(CafWhite),
            contentPadding = PaddingValues(
                start = 12.dp,
                end = 12.dp,
                top = 12.dp,
                bottom = 90.dp
            ),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                HeaderSection(
                    userName = "Emmanuel M.",
                    accessibilityEnabled = accessibilityEnabled,
                    onAccessibilityChange = { accessibilityEnabled = it }
                )
            }

            item {
                PaymentCard()
            }

            item {
                ActionRowCard(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Relevé",
                            tint = CafTurquoise,
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    title = "Mon relevé de compte",
                    trailing = {
                        PdfBadge()
                    }
                )
            }

            item {
                ActionRowCard(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Attestations",
                            tint = CafTurquoise,
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    title = "Mes attestations",
                    trailing = {
                        ArrowSquareButton()
                    }
                )
            }

            item {
                ActionRowCard(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "Courriers",
                            tint = CafTurquoise,
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    title = "Mes courriers, courriels",
                    trailing = {
                        ArrowSquareButton()
                    }
                )
            }

            item {
                ActionRowCard(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Démarches guidées",
                            tint = CafTurquoise,
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    title = "Démarches guidées",
                    trailing = {
                        ArrowSquareButton()
                    }
                )
            }
        }
    }
}

@Composable
private fun HeaderSection(
    userName: String,
    accessibilityEnabled: Boolean,
    onAccessibilityChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 4.dp, end = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = userName,
            color = CafTitle,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Switch(
                checked = accessibilityEnabled,
                onCheckedChange = onAccessibilityChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF3D475A),
                    uncheckedThumbColor = Color(0xFF3D475A),
                    checkedTrackColor = Color(0xFFD8DCE4),
                    uncheckedTrackColor = Color(0xFFD8DCE4),
                    checkedBorderColor = Color.Transparent,
                    uncheckedBorderColor = Color.Transparent
                )
            )

            Text(
                text = "Accessibilité",
                fontSize = 12.sp,
                color = CafText
            )
        }
    }
}

@Composable
private fun PaymentCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CafLightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mon dernier paiement",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = CafText
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "le 12/03/2026",
                fontSize = 14.sp,
                color = CafSubText
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "0.01 €*",
                fontSize = 65.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(-2.dp))

            Text(
                text = "* Versé à un tiers",
                fontSize = 13.sp,
                color = CafSubText
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CafPrimary,
                    contentColor = CafWhite
                )
            ) {
                Text(
                    text = "Voir le détail de mes allocations",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun ActionRowCard(
    icon: @Composable () -> Unit,
    title: String,
    trailing: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CafWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = BorderStroke(1.dp, CafBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                color = CafText,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )

            trailing()
        }
    }
}

@Composable
private fun PdfBadge() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(CafPrimary)
            .padding(horizontal = 12.dp, vertical = 9.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "PDF",
            color = CafWhite,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ArrowSquareButton() {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(CafPrimary)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "Voir",
            tint = CafWhite
        )
    }
}

@Composable
private fun FloatingAssistantButton() {
    Box(
        modifier = Modifier
            .size(76.dp)
            .clip(CircleShape)
            .background(CafTurquoise)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.SmartToy,
            contentDescription = "Assistant",
            tint = CafWhite,
            modifier = Modifier
                .size(40.dp)
                .offset(y = (-12).dp)
        )
    }
}


@Composable
private fun BottomNavigationBar() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        // 1. On place le bouton en PREMIER pour qu'il soit au second plan
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-50).dp) // Ajuste la hauteur ici
        ) {
            FloatingAssistantButton()
        }

        // 2. On place la barre en DEUXIÈME pour qu'elle soit au-dessus
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CafWhite)
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            HorizontalDivider(thickness = 1.dp, color = CafBorder)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val items = listOf(
                    Icons.Filled.Home to "Accueil",
                    Icons.Filled.Info to "Allocations",
                    Icons.Filled.Article to "Démarches",
                    Icons.Filled.AccountCircle to "Profil",
                    Icons.Filled.Menu to "Menu"
                )

                items.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        NavItem(
                            icon = item.first,
                            label = item.second,
                            selected = index == 0
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean
) {
    val color = if (selected) CafTurquoise else CafNavGray

    Column(
        modifier = Modifier.padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            color = color,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}