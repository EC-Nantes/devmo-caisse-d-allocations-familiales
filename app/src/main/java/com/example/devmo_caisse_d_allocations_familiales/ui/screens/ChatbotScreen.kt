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

@Composable
fun ChatbotScreen(
    onClose: () -> Unit,
    viewModel: ChatbotViewModel = viewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
            .background(Color.White)
    ) {
        Scaffold(
            topBar = { ChatbotHeader(onClose, onClear = { viewModel.clearChat() }) },
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

                item { SuggestionButton("Comment faire une demande APL ?", onClick = { viewModel.sendMessage("Comment faire une demande APL ?") }) }
                item { SuggestionButton("Quels documents sont nécessaires ?", onClick = { viewModel.sendMessage("Quels documents sont nécessaires ?") }) }
                item { SuggestionButton("Comment contacter la CAF ?", onClick = { viewModel.sendMessage("Comment contacter la CAF ?") }) }
            }
        }
    }
}

@Composable
private fun ChatbotHeader(onClose: () -> Unit, onClear: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CafTurquoise)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.SentimentSatisfiedAlt, null, tint = CafTurquoise)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text("Assistant CAF", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        IconButton(onClick = onClear) { Icon(Icons.Default.Delete, null, tint = Color.White) }
        IconButton(onClick = onClose) { Icon(Icons.Default.Close, null, tint = Color.White) }
    }
}

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
