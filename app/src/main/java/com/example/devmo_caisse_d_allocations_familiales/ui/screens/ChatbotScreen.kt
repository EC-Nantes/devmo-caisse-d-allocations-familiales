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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.*

/**
 * Écran de l'assistant virtuel (Chatbot) de la CAF.
 * Cet écran est conçu pour s'afficher en superposition (Overlay) sur l'écran précédent.
 * 
 * @param onClose Fonction de rappel pour fermer l'assistant.
 * @param viewModel Le ViewModel gérant l'état de la conversation.
 */
@Composable
fun ChatbotScreen(
    onClose: () -> Unit,
    viewModel: ChatbotViewModel = viewModel()
) {
    // Structure principale avec un espacement en haut pour l'effet de superposition
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
            .background(Color.White)
    ) {
        Scaffold(
            topBar = { 
                ChatbotHeader(
                    onClose = onClose, 
                    onClear = { viewModel.clearChat() }
                ) 
            },
            bottomBar = { 
                ChatbotInputSection(onSendMessage = { viewModel.sendMessage(it) }) 
            },
            containerColor = Color.White
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                
                // Affichage dynamique des messages de la conversation
                items(viewModel.messages) { message ->
                    ChatBubble(message.text, message.isUser)
                }

                item {
                    Text(
                        text = "Questions fréquentes :",
                        fontSize = 14.sp,
                        color = CafSubText,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Boutons de suggestions rapides
                item { SuggestionButton("Comment faire une demande APL ?", onClick = { viewModel.sendMessage("Comment faire une demande APL ?") }) }
                item { SuggestionButton("Quels documents sont nécessaires ?", onClick = { viewModel.sendMessage("Quels documents sont nécessaires ?") }) }
                item { SuggestionButton("Comment contacter la CAF ?", onClick = { viewModel.sendMessage("Comment contacter la CAF ?") }) }
            }
        }
    }
}

/**
 * En-tête de l'assistant contenant le titre et les actions de contrôle.
 */
@Composable
private fun ChatbotHeader(onClose: () -> Unit, onClear: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CafTurquoise)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icône de l'assistant dans un cercle blanc
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.SentimentSatisfiedAlt, null, tint = CafTurquoise)
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Text(
            text = "Assistant CAF", 
            color = Color.White, 
            fontSize = 20.sp, 
            fontWeight = FontWeight.Bold, 
            modifier = Modifier.weight(1f)
        )

        // Actions de l'en-tête (Paramètres, Langue, Supprimer, Fermer)
        IconButton(onClick = { /* Action Paramètres */ }) { 
            Icon(Icons.Default.Settings, null, tint = Color.White) 
        }
        IconButton(onClick = { /* Action Langue */ }) { 
            Icon(Icons.Default.Language, null, tint = Color.White) 
        }
        IconButton(onClick = onClear) { 
            Icon(Icons.Default.Delete, null, tint = Color.White) 
        }
        IconButton(onClick = onClose) { 
            Icon(Icons.Default.Close, null, tint = Color.White) 
        }
    }
}

/**
 * Composant représentant une bulle de message dans le chat.
 */
@Composable
private fun ChatBubble(text: String, isUser: Boolean) {
    val alignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    val color = if (isUser) Color(0xFFE1F5FE) else Color.White
    val shape = if (isUser) {
        RoundedCornerShape(topStart = 16.dp, topEnd = 4.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
    } else {
        RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
    }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = alignment) {
        Card(
            shape = shape,
            colors = CardDefaults.cardColors(containerColor = color),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E0E0)),
            modifier = Modifier.fillMaxWidth(0.85f)
        ) {
            Text(text = text, modifier = Modifier.padding(12.dp), fontSize = 15.sp, color = CafText)
        }
    }
}

/**
 * Bouton de suggestion pour poser une question prédéfinie.
 */
@Composable
private fun SuggestionButton(text: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, CafTurquoise),
        modifier = Modifier.fillMaxWidth().clickable { onClick() }
    ) {
        Text(text = text, modifier = Modifier.padding(16.dp), color = CafTurquoise, fontSize = 14.sp)
    }
}

/**
 * Section de saisie de texte en bas de l'écran.
 */
@Composable
private fun ChatbotInputSection(onSendMessage: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text,
                onValueChange = { if (it.length <= 100) text = it },
                placeholder = { Text("Posez-moi votre question", fontSize = 14.sp) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                trailingIcon = { Text("${text.length}/100", fontSize = 11.sp) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { 
                    onSendMessage(text)
                    text = "" 
                },
                modifier = Modifier.size(48.dp).background(CafTurquoise, CircleShape),
                enabled = text.isNotBlank()
            ) {
                Icon(Icons.Default.Send, null, tint = Color.White)
            }
        }
        Text(
            text = "${text.length} caractères",
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}
