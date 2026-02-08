# GithubInnova

Browse GitHub users' repositories and view repo details with tags. An Android app built with Kotlin and Jetpack Compose.

## Description

GithubInnova lets you browse GitHub from your phone. Enter a GitHub username to fetch and list their public repositories; each item shows name, description, and open-issues count. Tap a repo to open a details screen with owner info, fork/watch counts, and a list of tags with commit SHAs. The app uses the [GitHub REST API](https://docs.github.com/en/rest) and supports an optional personal access token for higher rate limits.

## Features

- Search by GitHub username to list public repositories
- Repo list shows name, description, and open issues count
- Repo details screen with owner avatar, name, fork and watcher counts
- List of tags with commit SHAs
- Material 3 theming with light/dark and dynamic color support
- Clean MVVM architecture with Hilt dependency injection

## Tech stack

- **Language:** Kotlin  
- **UI:** Jetpack Compose, Material 3  
- **DI:** Hilt  
- **Networking:** Retrofit, OkHttp, Kotlinx Serialization  
- **Async:** Kotlin Coroutines, StateFlow  
- **Images:** Coil (Compose, SVG)

## Prerequisites

- [Android Studio](https://developer.android.com/studio) (latest stable)
- Android SDK (minSdk 26, targetSdk 36)
- Optional: [GitHub personal access token](https://github.com/settings/tokens) for higher API rate limits (without token, unauthenticated limits apply)

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/GithubInnova.git
   cd GithubInnova
   ```

2. **(Optional)** Add a GitHub token for authenticated API calls. Create or edit `local.properties` in the project root and add:
   ```properties
   GITHUB_TOKEN=your_github_personal_access_token
   ```
   If omitted, the app still runs with unauthenticated API access.

3. Open the project in Android Studio and sync Gradle.

## Build and run

- **Debug:** Run the `app` configuration or use:
  ```bash
  ./gradlew :app:installDebug
  ```
- **Release:** Build an APK or app bundle with the usual Android Studio build menus or:
  ```bash
  ./gradlew :app:assembleRelease
  ```

## License

This project is for demonstration purposes. Use and modify as you like.
