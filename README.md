# Hospital Management System (HMS)

## Group: SCE4 Group 1

## Overview
The **Hospital Management System (HMS)** is a Java-based console application designed to manage various hospital operations including patient management, appointment scheduling, and medical record updates. This project was developed using object-oriented programming principles, applying encapsulation, inheritance, and polymorphism to create a modular and maintainable system.

The system supports multiple user roles, including **Doctor**, **Patient**, **Pharmacist**, and **Administrator**. Each role will have their own actions, i.e doctors are able to view and update patient records, manage appointments, and record outcomes of appointments.

## Prerequisites
- Java 8 or higher
- Java Development Kit (JDK) installed on your system
- A terminal or command-line interface to run the application

## Usage

Navigate to the project directory and run the MainView.java file to start the program

## Classes and Their Responsibilities

### Main Classes
- **[`MainView`](MainView.java)**: Entry point of the application.
- **[`DatabaseHelper`](DatabaseHelper.java)**: Contains methods for initializing and managing the database.

### User Roles
- **[`User`](User.java)**: Base class for all user types.
- **[`Doctor`](Doctor.java)**: Extends `User` with doctor-specific functionalities.
- **[`Patient`](Patient.java)**: Extends `User` with patient-specific functionalities.
- **[`Pharmacist`](Pharmacist.java)**: Extends `User` with pharmacist-specific functionalities.
- **[`Administrator`](Administrator.java)**: Extends `User` with administrator-specific functionalities.

### Views
- **[`DoctorView`](DoctorView.java)**: Interface for doctor interactions.
- **[`PatientView`](PatientView.java)**: Interface for patient interactions.
- **[`PharmacistView`](PharmacistView.java)**: Interface for pharmacist interactions.
- **[`AdminView`](AdminView.java)**: Interface for administrator interactions.


### Supporting Classes
- **[`Appointment`](Appointment.java)**: Manages appointment details.
- **[`ApptManager`](ApptManager.java)**: Manages appointment scheduling.
- **[`Availability`](Availability.java)**: Manages availability slots.
- **[`HMSDatabase`](HMSDatabase.java)**: Represents the hospital management system database.
- **[`MedicalRecord`](MedicalRecord.java)**: Manages medical records.
- **[`Medicine`](Medicine.java)**: Manages medicine details.
- **[`ReplenishmentRequest`](ReplenishmentRequest.java)**: Manages medicine replenishment requests.
- **[`Staff`](Staff.java)**: Represents staff members.
- **[`Status`](Status.java)**: Enum for status types.
- **[`TimeSlot`](TimeSlot.java)**: Manages time slots.
- **[`UserType`](UserType.java)**: Enum for user types.

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.


## Work Contributions
- User class, features, functions, UML Diagram: Max Wong
- Patient classes, features, and functions: Xin Min
- Doctor classes, features, and functions: Shao Yang
- Pharmacist classes, features, and functions: Jin Lin
- Administrator classes, features, and functions: Joyanne