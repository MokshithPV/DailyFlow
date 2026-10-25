# DailyFlow

DailyFlow is the starting point for a Duolingo-style daily activity tracker.

## Current implementation

- Android app shell with a Compose task list
- Create daily tasks
- Mark tasks complete once per day
- Track current streak and best streak per task
- Generate a widget-friendly summary list sorted by streaks

## Next Android steps

The Compose app currently provides task creation, completion, and streak display.
The next Android steps are:

1. Local persistence (Room/DataStore) to keep streaks across app restarts
2. A Glance App Widget that renders `widgetSummary()` output

## Build and install the APK without a laptop

GitHub Actions builds a debug APK automatically. From the repository's **Actions**
tab, open **Build Android APK**, run the workflow, and download the
`dailyflow-debug-apk` artifact on your phone. Open the downloaded APK to install
it; Android may ask you to allow installation from your browser or file manager.

To create a downloadable GitHub release, create a tag such as `v1.0.0`. The
release includes the same installable debug APK.
