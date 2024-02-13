Block Administration Application
======================================

Overview
--------

The Block Administration Application is a Java-based application designed to manage various aspects of a residential building, including apartments, residents, and expenses. It provides functionality for CRUD operations on apartments and residents, as well as features for managing utility expenses. The app follows the Model-View-Controller (MVC) architecture pattern to ensure a clear separation of concerns and maintainability of code.

Table of Contents
-----------------

*   [Overview](#overview)

*   [Project Purpose](#project-purpose)

*   [Components](#components)

*   [Interactions Between Components](#interactions-between-components)

*   [Functionality](#functionality)

*   [Usage](#usage)


Project Purpose
---------------

The Block Administration Application serves as an educational project, aiming to demonstrate and understand the MVC architecture pattern commonly used in software development.

Components
----------

In the MVC architecture:

*   **Model**: Represents the data and business logic of the application. The Person, Apartment, and UtilityExpenses classes serve as models, encapsulating data related to residents, apartments, and utility expenses, respectively.

*   **View**: Represents the user interface components responsible for displaying information to the user and capturing user input. Although not explicitly defined as a separate component, the menu system acts as the view, presenting options to the user.

*   **Controller**: Acts as an intermediary between the model and view, handling user input, updating the model accordingly, and updating the view to reflect changes in the model. In this app, the MenuController, BlockAdminController, PersonController, ApartmentController, and ExpensesController classes serve as controllers, managing different functionalities and interactions with the models.


Interactions Between Components
-------------------------------

*   **MenuController**: Controls menu navigation and routes user selections to appropriate controllers based on input.

*   **BlockAdminController**: Manages CRUD operations for apartments and residents, interacting with PersonController and ApartmentController to perform operations.

*   **PersonController**: Handles CRUD operations for residents and provides functionality to display residents by specific properties such as age group.

*   **ApartmentController**: Manages CRUD operations for apartments and handles operations related to updating the number of residents in an apartment.

*   **ExpensesController**: Manages utility expense-related functionalities, interacting with the UtilityExpenses model to calculate and display expense information.


Functionality
-------------

The application provides the following key functionalities:

*   **Management of Apartments and Residents**: Allows CRUD operations on apartment and resident information.

*   **Utility Expenses Management**: Enables setting and displaying utility expense details, calculating monthly expenses, and viewing individual owner expenses.

*   **Menu-Based Navigation**: Provides an intuitive menu-driven interface for users to navigate through different functionalities of the application.


Usage
-----

To use the Block Administration Application:

1.  Compile the Java files and run the AppController class.

2.  Follow the menu prompts to perform various operations such as managing apartments, residents, and expenses.

3.  Utilize the CRUD operations to create, read, update, and delete apartment and resident information.

4.  Manage utility expenses by setting expense details, viewing current cost indexes, and calculating monthly expenses.