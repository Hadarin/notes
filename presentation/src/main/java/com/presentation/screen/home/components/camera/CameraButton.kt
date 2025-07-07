package com.presentation.screen.home.components.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File

@Composable
fun CameraButton(
    modifier: Modifier = Modifier,
    onCameraResult: (Uri?) -> Unit = {}
) {
    val context = LocalContext.current

    // Create a launcher for the camera intent
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                // Photo was taken successfully
                onCameraResult(getCameraImageUri(context))
            } else {
                // Photo was not taken
                onCameraResult(null)
            }
        }
    )

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                // Permission granted, launch camera
                val uri = getCameraImageUri(context)
                cameraLauncher.launch(uri)
            }
        }
    )

    Button(
        onClick = {
            when {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // Permission already granted, launch camera
                    val uri = getCameraImageUri(context)
                    cameraLauncher.launch(uri)
                }
                else -> {
                    // Request permission
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Face,
            contentDescription = "Take Photo"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Take Photo")
    }
}

// Helper function to create URI for camera image
private fun getCameraImageUri(context: Context): Uri {
    val imageFile = File(
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "camera_photo_${System.currentTimeMillis()}.jpg"
    )

    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        imageFile
    )
}