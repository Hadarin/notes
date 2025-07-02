package com.presentation.screen.home.components.biometric

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biometric.BiometricPromptManager.BiometricResult

@Composable
fun AuthenticationScreen(
    biometricResult: BiometricResult?,
    onAuthenticateClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onAuthenticateClick) {
            Text(text = "Authenticate")
        }

        biometricResult?.let { result ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = when(result) {
                    is BiometricResult.AuthenticationError -> {
                        result.error
                    }
                    BiometricResult.AuthenticationFailed -> {
                        "Authentication failed"
                    }
                    BiometricResult.AuthenticationNotSet -> {
                        "Authentication not set"
                    }
                    BiometricResult.AuthenticationSuccess -> {
                        "Authentication success"
                    }
                    BiometricResult.FeatureUnavailable -> {
                        "Feature unavailable"
                    }
                    BiometricResult.HardwareUnavailable -> {
                        "Hardware unavailable"
                    }
                }
            )
        }
    }
}