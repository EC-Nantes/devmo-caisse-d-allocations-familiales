package com.example.devmo_caisse_d_allocations_familiales.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.*

/**
 * Écran affichant la liste des démarches guidées.
 * Réutilise les composants de l'en-tête et de la barre de navigation.
 * 
 * @param onNavigateBack Callback pour retourner à l'écran précédent.
 * @param onNavigateToChatbot Callback pour ouvrir l'assistant virtuel.
 */
@Composable
fun MenuOptionsScreen(onNavigateBack: () -> Unit, onNavigateToChatbot: () -> Unit) {
    var accessibilityEnabled by remember { mutableStateOf(false) }

    val demarches = listOf(
        DemarcheData("Allocation veuvage", "Aide pour les personnes veuves ou veufs", Icons.Outlined.Description),
        DemarcheData("Allocations logement", "Aide au logement (APL, ALS ou ALF)", Icons.Outlined.Home),
        DemarcheData("RSA", "Revenu de Solidarité Active", Icons.Outlined.AccessibilityNew),
        DemarcheData("Prime d'activité", "Aide aux travailleurs à revenus modestes", Icons.Outlined.WorkOutline),
        DemarcheData("CMU-C", "Complémentaire Santé Solidaire", Icons.Outlined.FavoriteBorder),
        DemarcheData("Simplifier ma Prime Renov'", "Aide aux rénovations énergétiques", Icons.Outlined.Apartment),
        DemarcheData("Déclarer un changement", "Adresse, famille, IBAN, etc.", Icons.Outlined.Edit)
    )

    Scaffold(
        containerColor = CafWhite,
        bottomBar = { BottomNavigationBar(onHomeClick = onNavigateBack, onChatbotClick = onNavigateToChatbot) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(CafWhite),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                HeaderSection(
                    userName = "Emmanuel M.",
                    accessibilityEnabled = accessibilityEnabled,
                    onAccessibilityChange = { accessibilityEnabled = it }
                )
            }

            item {
                BreadcrumbSection(onBackClick = onNavigateBack)
            }

            item {
                InfoBox()
            }

            items(demarches) { item ->
                DemarcheCard(item)
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

@Composable
private fun BreadcrumbSection(onBackClick: () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onBackClick() }
        ) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Retour", tint = CafNavGray)
            Text(
                text = "Démarches guidées",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CafTitle
            )
        }
        Text(
            text = "Accueil > Démarches guidées",
            fontSize = 12.sp,
            color = CafSubText,
            modifier = Modifier.padding(start = 24.dp)
        )
    }
}

@Composable
private fun InfoBox() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6F7F8)),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Outlined.Lightbulb, contentDescription = null, tint = CafTurquoise)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Sélectionnez une démarche pour voir les conditions d'éligibilité, les documents à fournir et démarrer la demande.",
                fontSize = 13.sp,
                color = CafText
            )
        }
    }
}

@Composable
private fun DemarcheCard(data: DemarcheData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CafWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, CafBorder)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(data.icon, contentDescription = null, tint = CafTurquoise, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = data.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = CafText)
                Text(text = data.subtitle, color = CafSubText, fontSize = 12.sp)
            }
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = CafTurquoise)
        }
    }
}

@Composable
private fun HeaderSection(userName: String, accessibilityEnabled: Boolean, onAccessibilityChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(text = userName, color = CafTitle, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Switch(
                checked = accessibilityEnabled,
                onCheckedChange = onAccessibilityChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF3D475A),
                    uncheckedThumbColor = Color(0xFF3D475A),
                    checkedTrackColor = Color(0xFFD8DCE4),
                    uncheckedTrackColor = Color(0xFFD8DCE4)
                )
            )
            Text(text = "Accessibilité", fontSize = 12.sp, color = CafText)
        }
    }
}

@Composable
private fun BottomNavigationBar(onHomeClick: () -> Unit, onChatbotClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
        Box(modifier = Modifier.align(Alignment.TopCenter).offset(y = (-50).dp)) {
            Box(
                modifier = Modifier.size(76.dp).clip(CircleShape).background(CafTurquoise).clickable { onChatbotClick() },
                contentAlignment = Alignment.Center
            ) {
                // Utilisation de l'icône de visage souriant pour l'assistant
                Icon(Icons.Filled.SentimentSatisfiedAlt, null, tint = CafWhite, modifier = Modifier.size(40.dp).offset(y = (-12).dp))
            }
        }
        Column(modifier = Modifier.fillMaxWidth().background(CafWhite).windowInsetsPadding(WindowInsets.navigationBars)) {
            HorizontalDivider(thickness = 1.dp, color = CafBorder)
            Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 10.dp)) {
                val items = listOf(Icons.Filled.Home to "Accueil", Icons.Filled.Info to "Allocations", Icons.Filled.Article to "Démarches", Icons.Filled.AccountCircle to "Profil", Icons.Filled.Menu to "Menu")
                items.forEachIndexed { index, item ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { if (index == 0) onHomeClick() }
                            .padding(vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val color = if (index == 2) CafTurquoise else CafNavGray
                        Icon(item.first, null, tint = color, modifier = Modifier.size(24.dp))
                        Text(item.second, color = color, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

data class DemarcheData(val title: String, val subtitle: String, val icon: ImageVector)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuOptionsScreenPreview() {
    DevmoTheme {
        MenuOptionsScreen(onNavigateBack = {}, onNavigateToChatbot = {})
    }
}
