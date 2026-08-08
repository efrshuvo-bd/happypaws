# HappyPets — Pet Routine & Medication Tracker 🐾💊

**HappyPets** is an Android application built with **Jetpack Compose** and **Material 3**, designed to help cat and dog parents manage daily routines, track veterinarian-prescribed medications, monitor custom health metrics, and seamlessly coordinate care with family members.

---

## Key Features

* 🗓️ **Routine & Medication Schedules:** Set up one-time or recurring tasks, including multi-phase tapering schedules (e.g., loading doses, alternating days).
* 👥 **Shared Caregiver Syncing:** Real-time household activity feed with explicit duplicate-dose protection to prevent accidental double-dosing.
* 📊 **Smart Health Insights:** Track body weight, water intake, blood glucose, and veterinary-oriented fecal scoring with interactive charts.
* 📦 **Refill Forecasting:** Inventory tracking with automated low-stock notifications and refill countdowns.
* 🚨 **Emergency Vet Pass:** Local, offline-first emergency health pass storing microchip info, allergies, active prescriptions, and contact details with QR code sharing.
* ✈️ **Travel Mode & Time Zones:** Safe schedule adjustments across time zone and daylight-saving transitions without shifting medication intervals silently.
* 📱 **Glance Widgets:** Home-screen widgets for quick 1-tap routine logging and secure medication confirmation.

---

## Tech Stack & Architecture

* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Material 3 Adaptive Layouts)
* **Architecture:** Clean Architecture + MVVM / Unidirectional Data Flow
* **Local Storage:** Room Database (Encrypted for local emergency cache)
* **Widgets:** Jetpack Glance
* **Async & Reactive:** Kotlin Coroutines + Flow

---

## Getting Started

### Prerequisites
* Android Studio Ladybug (or newer)
* JDK 17+
* Android SDK 24+ (Android 7.0+)

### Installation
1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/HappyPets.git](https://github.com/your-username/HappyPets.git)
