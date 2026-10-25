package com.dailyflow.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dailyflow.DailyTask
import com.dailyflow.app.DailyFlowViewModel

@Composable
fun DailyFlowScreen(viewModel: DailyFlowViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("DailyFlow", style = MaterialTheme.typography.headlineLarge)
        Text("Build a streak by completing your daily activities.")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            OutlinedTextField(
                value = uiState.taskName,
                onValueChange = viewModel::updateTaskName,
                modifier = Modifier.weight(1f),
                label = { Text("New activity") },
                singleLine = true,
                isError = uiState.errorMessage != null,
            )
            Button(
                onClick = viewModel::addTask,
                modifier = Modifier.padding(top = 8.dp),
            ) {
                Text("Add")
            }
        }

        uiState.errorMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(uiState.tasks, key = DailyTask::id) { task ->
                TaskCard(task = task, onComplete = { viewModel.completeTask(task.id) })
            }
        }
    }
}

@Composable
private fun TaskCard(task: DailyTask, onComplete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(task.name, style = MaterialTheme.typography.titleMedium)
                Text("Current streak: ${task.streak} days")
                Text("Best streak: ${task.bestStreak} days")
            }
            Button(onClick = onComplete) {
                Text("Complete")
            }
        }
    }
}
