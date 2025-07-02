
# 📺 TV App

This is a sample Android TV application built using modern Android development tools. The app showcases how to design a clean, remote-friendly, and intuitive TV interface for browsing and playing videos sourced from the Pexels API.

---

## 🌟 Features

- **🎬 Popular Videos:** View a curated list of trending videos fetched from the Pexels API.
- **🔎 Search Functionality:** Search for videos by keywords with real-time suggestions.
- **▶️ Smooth Playback:** Watch videos with ExoPlayer for seamless playback.
- **🧱 Built with Jetpack Compose:** The entire UI is developed using Jetpack Compose, ensuring modern and reactive layouts.

---

## 📸 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/2ccbf286-8e30-4338-a990-ca2b9de5825e" alt="Screenshot 1" width="700" style="border:1px solid #ccc; border-radius:10px; box-shadow: 2px 2px 8px rgba(0,0,0,0.1); margin:10px;">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/c0d93a0c-8f33-4cd4-b1cf-30f34e428160" alt="Screenshot 2" width="700" style="border:1px solid #ccc; border-radius:10px; box-shadow: 2px 2px 8px rgba(0,0,0,0.1); margin:10px;">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/09b4a73d-64c7-460d-901d-4ce9d3376c17" alt="Screenshot 3" width="700" style="border:1px solid #ccc; border-radius:10px; box-shadow: 2px 2px 8px rgba(0,0,0,0.1); margin:10px;">
</p>

---

## 🛠️ Tech Stack

- **Kotlin** – Primary programming language.
- **Jetpack Compose** – Modern declarative UI toolkit.
- **Coroutines** – Efficient background threading.
- **ViewModel** – Lifecycle-aware UI data handling.
- **Retrofit** – HTTP client for API calls.
- **ExoPlayer** – Powerful media playback library.
- **Coil** – Lightweight image loading library.
- **Pexels API** – Free stock video provider.

---

## 🚀 Getting Started

Follow these steps to set up and run the app:

### 1. Clone the Repository

```bash
git clone https://github.com/soumyajitnandi0/tvapp.git
````

### 2. Open Project

Open the project in the **latest version of Android Studio**.

---

### 3. Get a Pexels API Key

* Visit the [Pexels API page](https://api.pexels.com) and register for a free developer account.
* Generate your API key after signing up.

---

### 4. Add the API Key

* Navigate to:

```text
app/src/main/java/com/example/tvapp/viewmodel/VideoViewModel.kt
```

* Replace the placeholder with your API key:

```kotlin
private val apiKey = "YOUR_PEXELS_API_KEY"
```

---

### 5. Build & Run

* Choose an **Android TV emulator** or connect a **physical Android TV device**.
* Click **Run** in Android Studio to start the app.

---

## 📥 Download APK

You can download the latest build directly from here:

[![Download APK](https://img.shields.io/badge/Download-APK-brightgreen.svg?style=for-the-badge\&logo=android)](https://drive.google.com/file/d/1u9BpTLI0PvP079Ja8n_BuU-f053xgZO0/view?usp=sharing)

---
## 🧩 Folder Structure

```bash
com.example.tvapp/
├── data/
│   ├── api/               # Retrofit interfaces or networking logic
│   └── model/             # Data models (e.g., VideoResponse.kt)
├── ui/
│   ├── screen/            # Composable screens (e.g., HomeScreen, VideoPlayerScreen)
│   └── theme/             # Theme files (colors, typography, shapes)
├── viewmodel/             # ViewModel classes
└── MainActivity.kt        # App's main entry point

```



