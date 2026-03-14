package com.example.devmo_caisse_d_allocations_familiales.ui.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

/**
 * Modèle de données représentant un message dans la conversation.
 * @property text Le contenu textuel du message.
 * @property isUser Vrai si le message a été envoyé par l'utilisateur, faux si c'est l'assistant.
 */
data class Message(
    val text: String,
    val isUser: Boolean
)

/**
 * ViewModel responsable de la gestion de l'état de la conversation du Chatbot.
 * Cette classe permet de conserver les messages même si l'utilisateur change d'écran
 * ou met l'application en arrière-plan.
 */
class ChatbotViewModel : ViewModel() {
    
    // Liste réactive des messages. L'utilisation de mutableStateListOf permet à l'UI
    // de se mettre à jour automatiquement dès qu'un élément est ajouté.
    private val _messages = mutableStateListOf<Message>(
        Message("Bonjour ! Je suis votre assistant virtuel CAF. Comment puis-je vous aider aujourd'hui ?", false)
    )
    val messages: List<Message> = _messages

    /**
     * Ajoute un nouveau message envoyé par l'utilisateur à la conversation.
     * @param text Le texte saisi par l'utilisateur.
     */
    fun sendMessage(text: String) {
        if (text.isNotBlank()) {
            _messages.add(Message(text, true))
            // Simule une absence de réponse automatique pour cette version simplifiée.
        }
    }

    /**
     * Réinitialise la conversation en effaçant tous les messages.
     */
    fun clearChat() {
        _messages.clear()
        _messages.add(Message("Conversation réinitialisée. Comment puis-je vous aider ?", false))
    }
}
