package com.tomb.ageverificationv1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AgeVerificationScreen(onVerified: () -> Unit) {
    var ageInput by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Age Verification",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Please verify your age to access the Calculator.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = ageInput,
            onValueChange = { input ->
                if (input.all { char -> char.isDigit() }) {
                    ageInput = input
                    errorMessage = "" // Clear error when user types
                }
            },
            label = { Text("Enter your age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // 3. CONDITIONAL UI: Only show this if errorMessage is not empty
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 4. ACTION: Button triggers logic
        Button(
            onClick = {
                val age = ageInput.toIntOrNull()
                when {
                    age == null -> errorMessage = "Please enter a valid number."
                    age < 18 -> errorMessage = "You must be 18 or older to use this app."
                    age > 120 -> errorMessage = "Please enter a realistic age."
                    else -> onVerified() // 🚀 SUCCESS: Triggers the callback!
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Verify & Continue", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}