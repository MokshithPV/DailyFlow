package com.dailyflow

import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DailyTaskTrackerTest {
    @Test
    fun `starts streak when completing first time`() {
        val tracker = DailyTaskTracker()
        val task = tracker.createTask("Read")

        val updated = tracker.completeTask(task.id, LocalDate.of(2026, 1, 1))

        assertEquals(1, updated.streak)
        assertEquals(1, updated.bestStreak)
    }

    @Test
    fun `does not increase streak twice on same day`() {
        val tracker = DailyTaskTracker()
        val task = tracker.createTask("Read")

        tracker.completeTask(task.id, LocalDate.of(2026, 1, 1))
        val updated = tracker.completeTask(task.id, LocalDate.of(2026, 1, 1))

        assertEquals(1, updated.streak)
        assertEquals(1, updated.bestStreak)
    }

    @Test
    fun `increases streak on consecutive days`() {
        val tracker = DailyTaskTracker()
        val task = tracker.createTask("Read")

        tracker.completeTask(task.id, LocalDate.of(2026, 1, 1))
        val updated = tracker.completeTask(task.id, LocalDate.of(2026, 1, 2))

        assertEquals(2, updated.streak)
        assertEquals(2, updated.bestStreak)
    }

    @Test
    fun `resets streak after missed day`() {
        val tracker = DailyTaskTracker()
        val task = tracker.createTask("Read")

        tracker.completeTask(task.id, LocalDate.of(2026, 1, 1))
        tracker.completeTask(task.id, LocalDate.of(2026, 1, 2))
        val updated = tracker.completeTask(task.id, LocalDate.of(2026, 1, 4))

        assertEquals(1, updated.streak)
        assertEquals(2, updated.bestStreak)
    }

    @Test
    fun `shows top streaks for widget summary`() {
        val tracker = DailyTaskTracker()
        val read = tracker.createTask("Read")
        val walk = tracker.createTask("Walk")

        tracker.completeTask(read.id, LocalDate.of(2026, 1, 1))
        tracker.completeTask(read.id, LocalDate.of(2026, 1, 2))
        tracker.completeTask(walk.id, LocalDate.of(2026, 1, 1))

        assertEquals(listOf("Read: 2 day streak", "Walk: 1 day streak"), tracker.widgetSummary())
    }

    @Test
    fun `rejects blank task names`() {
        val tracker = DailyTaskTracker()

        assertFailsWith<IllegalArgumentException> {
            tracker.createTask("   ")
        }
    }
}
