# 📝 **Notes App**

## 📖 **Overview**
The **Note Management Application** is an Android app developed using **Kotlin** and **SQLite** to help users create, manage, and organize notes. The app supports basic **CRUD** operations, color-coded prioritization, and functionality for sorting notes by priority (High, Urgent, Medium, Normal). Additionally, it includes a **theme switcher** (light/dark mode) and a **recycle bin** feature for deleted notes. This app also provides an intuitive user interface with **Tab Layout** or **Bottom Navigation Bar** for easy navigation.

## 🌟 **Features**

1. **📂 SQLite Database Integration**
   - The app uses **SQLite** to store and manage notes in the local database.
   - Each note includes:
     - 🕒 **Date and Time** (automatically captured on creation).
     - ✍️ **Title** (user-entered).
     - 📜 **Description** (user-entered).
     - 🚦 **Priority** (user-selected: High, Urgent, Medium, Normal).

2. **🔄 CRUD Operations**
   - **📋 Read Notes**: All notes are displayed in a list, sorted by their creation date, and color-coded based on priority.
   - **✏️ Update Notes**: Users can edit the title, description, and priority of an existing note.
   - **🗑️ Delete Notes**: Notes are removed from the main list but are placed in the recycle bin for possible restoration.

3. **🖥️ UI Components**
   - The app offers either a **Tab Layout** or **Bottom Navigation Bar** for navigating between different sections of the app:
     - **Tabs**: All Notes, High Priority, Settings.
     - **Bottom Navigation**: Home, Add Note, Settings.

4. **🎨 Theming**
   - **SharedPreferences** is used to save the selected theme (light/dark mode).
   - The theme preference is saved even when the app is closed and reopened, ensuring a consistent UI experience.
   - The app adapts all UI components to fit the selected theme.

5. **♻️ Recycle Bin**
   - Deleted notes are moved to the **Recycle Bin**, allowing users to:
     - 🔄 **Restore notes** to the main list.
     - 🗑️ **Permanently delete notes** from the database.

## 🛠️ **Technologies Used**
- **📱 Kotlin**: Main programming language for development.
- **💾 SQLite**: For local database storage of notes.
- **⚙️ SharedPreferences**: To save and manage the theme preference (light/dark mode).
- **🔲 Tab Layout or Bottom Navigation Bar**: For navigation between app sections.
- **📝 RecyclerView**: For displaying notes in a list format.
- **🖼️ WallpaperManager**: For setting themes (optional).

## 📸 **App Screenshots**

<p>
  <img src="https://github.com/user-attachments/assets/374e1fcb-97df-4e1a-8cb6-669201e7712b" width="200" height="400"/>
  <img src="https://github.com/user-attachments/assets/328946ba-f061-4f80-8334-2c9970d3e54e" width="200" height="400"/>
  <img src="https://github.com/user-attachments/assets/90b09df1-9b3c-4569-a0d0-f52c5bb007ed" width="200" height="400"/>
  <img src="https://github.com/user-attachments/assets/ac078e5e-36ac-47b4-a298-b1a8eed9c45c" width="200" height="400"/>
  <img src="https://github.com/user-attachments/assets/aff9371e-1880-4e59-8180-58889577e3d5" width="200" height="400"/>
  <img src="https://github.com/user-attachments/assets/3fe1f972-41b9-452d-b64a-1b05e9fd8242" width="200" height="400"/>
</p>

## 📝 **License**
This project is open-source and available for modification and enhancement.
