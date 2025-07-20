package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.biometric.BiometricPromptManager
import com.biometric.BiometricPromptManager.BiometricResult
import com.domain.model.PersonModel
import com.presentation.screen.home.NotesApp
import com.presentation.screen.home.components.biometric.AuthenticationScreen

class MainActivity : AppCompatActivity() {
    private val promptManager by lazy {
        BiometricPromptManager(this)
    }

    override fun onNewIntent(intent: Intent, caller: ComponentCaller) {
        super.onNewIntent(intent, caller)
        val receivedPerson: PersonModel? = intent.getParcelableExtra("person_object", PersonModel::class.java)
        println(receivedPerson.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedPerson: PersonModel? = intent.getParcelableExtra("person_object", PersonModel::class.java)


        //Ask for set notification permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this as Activity,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1001
            )
        }
        setContent {
            println("Check for person!!!!")
            if(receivedPerson != null) {
                PersonDetailScreen(person = receivedPerson)
            } else {
                println("Person is null!!!!")
                val biometricResult by promptManager.promptResults.collectAsState(
                    initial = null
                )
                val enrollLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult(),
                    onResult = {
                        println("Activity result: $it")
                    }
                )

                LaunchedEffect(biometricResult) {
                    if (biometricResult is BiometricResult.AuthenticationNotSet) {
                        if (Build.VERSION.SDK_INT >= 30) {
                            val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                                putExtra(
                                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                    BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                                )
                            }
                            enrollLauncher.launch(enrollIntent)
                        }
                    }
                }

                // Conditional navigation based on authentication state
                when (biometricResult) {
                    is BiometricResult.AuthenticationSuccess -> {
                        if (receivedPerson != null) {
                            PersonDetailScreen(receivedPerson)
                        } else {
                            NotesApp()
                        }
                    }

                    else -> {
                        // Show authentication screen
                        AuthenticationScreen(
                            biometricResult = biometricResult,
                            onAuthenticateClick = {
                                promptManager.showBiometricPrompt(
                                    title = "Sample prompt",
                                    description = "Sample prompt description"
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PersonDetailScreen(person: PersonModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Person Details",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            color = MaterialTheme.colorScheme.primary,
            text = "Name: ${person.name}",
            fontSize = 20.sp
        )
        Text(
            color = MaterialTheme.colorScheme.primary,
            text = "Age: ${person.age}",
            fontSize = 20.sp
        )
        Text(
            color = MaterialTheme.colorScheme.primary,
            text = "Latitude: ${person.latitude}",
            fontSize = 20.sp
        )
        Text(
            color = MaterialTheme.colorScheme.primary,
            text = "Amount: ${person.amount}",
            fontSize = 20.sp
        )
        Text(
            color = MaterialTheme.colorScheme.primary,
            text = "Enabled: ${person.isEnabled}",
            fontSize = 20.sp
        )
        Text(
            color = MaterialTheme.colorScheme.primary,
            text = "ID: ${person.id}",
            fontSize = 20.sp
        )
    }
}

