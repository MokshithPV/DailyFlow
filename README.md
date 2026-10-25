# DailyFlow

DailyFlow is the starting point for a Duolingo-style daily activity tracker.

## Current implementation

- Create daily tasks
- Mark tasks complete once per day
- Track current streak and best streak per task
- Generate a widget-friendly summary list sorted by streaks

## Next Android steps

This repository now contains tested streak logic that can be plugged into:

1. Jetpack Compose screens for task creation and completion
2. A Glance App Widget that renders `widgetSummary()` output
3. Local persistence (Room/DataStore) to keep streaks across app restarts
