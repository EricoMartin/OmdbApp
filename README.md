# 🎬 OMDb Movie App

A modern Android application built using **Jetpack Compose** that lets users search and view movie details using the **OMDb API**.  
The app follows clean architecture principles with **MVVM**, **Hilt** for dependency injection, **Room** for local caching, and **Retrofit** for network calls.

---

## ✨ Features

- 🔍 **Search Movies** — Search any movie by title using OMDb API.
- 🎞️ **Movie Grid** — Displays results in a clean 2-column grid with posters and titles.
- 📄 **Movie Detail Screen** — Tap a movie to view its full details like title, year, and genre.
- 💾 **Offline Caching** — Saves movie search results locally with Room for quick access.
- ⚡ **Reactive UI** — Built entirely with Jetpack Compose and StateFlow.
- 💉 **Dependency Injection** — Powered by Hilt for modular and testable code.

---

## 🧱 Architecture

The app is built using **MVVM (Model–View–ViewModel)** pattern and **Clean Architecture** principles.

---

## 🧩 Tech Stack

| Category | Technology |
|-----------|-------------|
| UI | **Jetpack Compose** |
| Dependency Injection | **Hilt (Dagger)** |
| Networking | **Retrofit + OkHttp** |
| Serialization | **Moshi** |
| Local Storage | **Room Database** |
| Image Loading | **Coil** |
| State Management | **Kotlin Flow / StateFlow** |

---

## ⚙️ Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/EricoMartin/OmdbApp.git
   cd OmdbApp
    ```
2.   **Add your OMDb API Key:**
     - Open your app-level build.gradle file. 
     - Add your API key inside buildConfigField:
          ```buildConfigField("String", "OMDB_API_KEY", "YOUR_API_KEY_HERE")```
3. Sync the project in Android Studio. 
4. Run the app on an emulator or physical device.

## 📸 Screenshot!

