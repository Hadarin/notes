package com.presentation.screen.geolocation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.URL

@Composable
fun GoogleApiLocationScreen() {
    var location by remember { mutableStateOf<LocationData?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val apiKey = "AIzaSyAfu6f2MERYp5dwbzXZfZY5V9YXC17hXw4"

    fun getLocationFromGoogle() {
        scope.launch {
            isLoading = true
            try {
                val response = withContext(Dispatchers.IO) {
                    val client = OkHttpClient()
                    val url = "https://www.googleapis.com/geolocation/v1/geolocate?key=$apiKey"
                    val request = Request
                        .Builder()
                        .url(url)
                        .post(
                            "{}".toRequestBody(null)
                        )
                        .build()
                    val response = client.newCall(request).execute()
                    response.body?.string() ?: ""
                }
                val locationResponse = Gson().fromJson(response, LocationResponse::class.java)
                location = locationResponse.location
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { getLocationFromGoogle() },
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(16.dp))
            } else {
                Text("Get Location (Google API)")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        location?.let { loc ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Latitude: ${loc.lat}")
                    Text("Longitude: ${loc.lng}")
                    Text("Source: Google Geolocation API")
                }
            }
        }
    }
}