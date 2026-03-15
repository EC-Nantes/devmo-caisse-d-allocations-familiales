package com.example.devmo_caisse_d_allocations_familiales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.example.devmo_caisse_d_allocations_familiales.ui.screens.ChatbotScreen
import com.example.devmo_caisse_d_allocations_familiales.ui.screens.ChatbotViewModel
import com.example.devmo_caisse_d_allocations_familiales.ui.screens.HomeCafScreen
import com.example.devmo_caisse_d_allocations_familiales.ui.screens.MenuOptionsScreen
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.DevmoTheme

/**
 * Activité principale de l'application "Mon Compte CAF".
 * Cette classe sert de point d'entrée et configure le thème ainsi que la navigation globale.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DevmoTheme {
                // Point d'entrée de la navigation
                AppNavigation()
            }
        }
    }
}

/**
 * Gère la navigation entre les différents écrans de l'application à l'aide de Jetpack Navigation.
 * Les écrans disponibles sont : l'accueil (Home), les démarches guidées et l'assistant virtuel (Chatbot).
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    // Instance partagée du ViewModel pour le Chatbot afin de conserver l'état des messages
    // pendant toute la durée de vie de la navigation.
    val chatbotViewModel: ChatbotViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        // Écran d'accueil principal
        composable("home") {
            HomeCafScreen(
                onNavigateToDemarches = { navController.navigate("demarches") },
                onNavigateToChatbot = { navController.navigate("chatbot") }
            )
        }
        
        // Écran affichant la liste des démarches guidées
        composable("demarches") {
            MenuOptionsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToChatbot = { navController.navigate("chatbot") }
            )
        }

        // Fenêtre de dialogue pour l'assistant virtuel (Chatbot)
        // L'utilisation de 'dialog' permet de superposer l'assistant sur l'écran actuel (Overlay).
        dialog(
            route = "chatbot",
            dialogProperties = DialogProperties(
                usePlatformDefaultWidth = false // Permet d'occuper toute la largeur de l'écran
            )
        ) {
            ChatbotScreen(
                onClose = { navController.popBackStack() },
                viewModel = chatbotViewModel
            )
        }
    }
}
