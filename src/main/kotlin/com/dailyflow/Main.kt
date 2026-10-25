package com.dailyflow

fun main() {
    val tracker = DailyTaskTracker()
    val hydration = tracker.createTask("Drink water")
    tracker.completeTask(hydration.id)

    println("DailyFlow task tracker initialized.")
    println("Widget preview:")
    tracker.widgetSummary().forEach(::println)
}
