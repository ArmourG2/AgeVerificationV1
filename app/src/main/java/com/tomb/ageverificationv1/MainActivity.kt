package com.tomb.ageverificationv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.* // Needed for remember and mutableStateOf
import androidx.compose.ui.Modifier
import com.tomb.ageverificationv1.ui.theme.AgeVerificationV1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AgeVerificationV1Theme {
                // 1. STATE: Track if the user has passed verification
                var isVerified by remember { mutableStateOf(false) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 2. CONDITIONAL RENDERING
                    if (!isVerified) {
                        // Show Age Verification first
                        AgeVerificationScreen(
                            onVerified = {
                                // This runs when the user successfully verifies
                                isVerified = true
                            }
                        )
                    } else {
                        // TODO: We will put the Calculator UI here in the next step!
                        // For now, just a placeholder to prove it works.
                        Surface(modifier = Modifier.fillMaxSize()) {
                            androidx.compose.foundation.layout.Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = androidx.compose.ui.Alignment.Center
                            ) {
                                androidx.compose.material3.Text("Calculator Goes Here!")
                            }
                        }
                    }
                }
            }
        }
    }
}