# Student Management App

A simple and user-friendly Android application designed to manage student records efficiently. The application allows users to add, view, search, update, and delete student information with persistent local storage using SQLite.

## Features

- **Student Registration:** Add students with name, email, phone number, and department.
- **Student Directory:** Display all registered students using RecyclerView.
- **Search Students:** Search student records by name.
- **Edit Student Details:** Update existing student information.
- **Delete Student Records:** Delete records with confirmation.
- **Input Validation:** Validate required fields, email addresses, and 10-digit phone numbers.
- **SQLite Database:** Store and retrieve student information locally.
- **User-Friendly Interface:** Clean UI with navigation and transition animations.
- **Custom App Icon:** Personalized launcher icon.

## Technologies Used

- Java
- XML
- Android Studio
- SQLite
- RecyclerView
- Android SDK

## Application Screens

Screenshots of the application will be added here.

## Project Structure

```text
StudentManagementApp/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/studentmanagementapp/
│           │   ├── MainActivity.java
│           │   ├── AddStudentActivity.java
│           │   ├── EditStudentActivity.java
│           │   ├── StudentListActivity.java
│           │   ├── Student.java
│           │   ├── StudentAdapter.java
│           │   └── StudentDatabaseHelper.java
│           ├── res/
│           │   └── layout/
│           └── AndroidManifest.xml
├── build.gradle.kts
└── settings.gradle.kts
```

## Installation and Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/nafisathafa/StudentManagementApp.git
   ```

2. Open Android Studio.
3. Select **Open** and choose the cloned project folder.
4. Allow Gradle to sync.
5. Run the application using an Android emulator or connected Android device.

## Internship Task Implementation

This project demonstrates the following Android development concepts:

- Button interactions and event handling.
- UI design using XML layouts.
- Dynamic list display using RecyclerView.
- Student registration forms and input validation.
- Fetching and displaying locally stored student data.
- SQLite database operations (Create, Read, Update, Delete).
- Navigation between multiple activities using explicit intents.

## Author

**Nafisa Thafa**

- GitHub: [@nafisathafa](https://github.com/nafisathafa)

---

Developed as part of the Cognifyz Technologies Android Development Virtual Internship.
