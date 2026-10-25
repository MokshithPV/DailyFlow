package com.dailyflow

import java.time.LocalDate

data class DailyTask(
    val id: Int,
    val name: String,
    val lastCompletedDate: LocalDate? = null,
    val streak: Int = 0,
    val bestStreak: Int = 0,
)

class DailyTaskTracker(initialTasks: List<DailyTask> = emptyList()) {
    private val tasks = initialTasks.associateBy { it.id }.toMutableMap()
    private var nextId = (initialTasks.maxOfOrNull { it.id } ?: 0) + 1

    fun createTask(name: String): DailyTask {
        require(name.isNotBlank()) { "Task name must not be blank" }

        val task = DailyTask(id = nextId++, name = name.trim())
        tasks[task.id] = task
        return task
    }

    fun allTasks(): List<DailyTask> = tasks.values.sortedBy { it.id }

    fun completeTask(taskId: Int, onDate: LocalDate = LocalDate.now()): DailyTask {
        val task = tasks[taskId] ?: error("Task not found: $taskId")

        val updated = when (task.lastCompletedDate) {
            null -> task.copy(lastCompletedDate = onDate, streak = 1, bestStreak = 1)
            onDate -> task
            onDate.minusDays(1) -> {
                val newStreak = task.streak + 1
                task.copy(
                    lastCompletedDate = onDate,
                    streak = newStreak,
                    bestStreak = maxOf(task.bestStreak, newStreak),
                )
            }
            else -> task.copy(lastCompletedDate = onDate, streak = 1, bestStreak = maxOf(task.bestStreak, 1))
        }

        tasks[taskId] = updated
        return updated
    }

    fun widgetSummary(maxTasks: Int = 5): List<String> =
        allTasks()
            .sortedByDescending { it.streak }
            .take(maxTasks)
            .map { "${it.name}: ${it.streak} day streak" }
}
