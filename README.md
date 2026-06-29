# Phone Catalog App

A functional Android application designed for managing a database of smartphones. This app allows users to view, add, edit, and delete phone records, with data persistence and a robust user interface.

##  Features
* **CRUD Operations:** Create, Read, Update, and Delete phone records.
* **Database Management:** * Initial population with sample data upon first launch.
    * Swipe-to-delete functionality for easy record removal.
* **Navigation & UI:**
    * Floating Action Button  for adding new phones.
    * Action Bar with a menu for bulk data deletion.
    * Back navigation with arrow support.
    * Detailed input form with validation.
* **Data Persistence:** Database state is preserved during configuration changes .
* **External Integration:** Dedicated button to open a phone's official website.

##  Technology Stack
* **Architecture:** MVVM (Model-View-ViewModel) to ensure data stability.
* **Database:** SQLite / Room Database for local data storage.
* **UI Components:** RecyclerView for list display, ItemTouchHelper for swipe gestures.
* **Language:** Java.

##  Key Features Implementation
* **Validation:** Custom validation for all input fields.
* **Communication:** `ActivityResultLauncher` used for smooth transitions between activities.
* **Responsiveness:** Designed to work correctly in both portrait and landscape modes.
