package com.dailyflow.app

import androidx.lifecycle.ViewModel
import com.dailyflow.DailyTask
import com.dailyflow.DailyTaskTracker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

data class DailyFlowUiState(
    val tasks: List<DailyTask> = emptyList(),
    val taskName: String = "",
    val errorMessage: String? = null,
)

class DailyFlowViewModel : ViewModel() {
    private val tracker = DailyTaskTracker()
    private val _uiState = MutableStateFlow(DailyFlowUiState())
    val uiState: StateFlow<DailyFlowUiState> = _uiState.asStateFlow()

    fun updateTaskName(name: String) {
        _uiState.value = _uiState.value.copy(taskName = name, errorMessage = null)
    }

    fun addTask() {
        try {
            tracker.createTask(_uiState.value.taskName)
            _uiState.value = DailyFlowUiState(tasks = tracker.allTasks())
        } catch (error: IllegalArgumentException) {
            _uiState.value = _uiState.value.copy(errorMessage = error.message)
        }
    }

    fun completeTask(taskId: Int) {
        tracker.completeTask(taskId, LocalDate.now())
        _uiState.value = _uiState.value.copy(tasks = tracker.allTasks(), errorMessage = null)
    }
}
