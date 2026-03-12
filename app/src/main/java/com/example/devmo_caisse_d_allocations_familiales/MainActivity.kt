package com.example.devmo_caisse_d_allocations_familiales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.devmo_caisse_d_allocations_familiales.ui.theme.DevmoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DevmoTheme {
            }
        }
    }
}