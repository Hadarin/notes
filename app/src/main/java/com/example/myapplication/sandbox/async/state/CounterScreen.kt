package com.example.myapplication.sandbox.async.state

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel()) {

    //val count by viewModel.counter.collectAsState()

    Column {
        Text("Count: count")
        Button(onClick = { viewModel.increment() }) {
            Text("Increment")
        }
        Button(onClick = { viewModel.decrement() }) {
            Text("Decrement")
        }
    }
}