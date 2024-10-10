# Hospital Management System (HMS)

## Overview
The **Hospital Management System (HMS)** is a Java-based console application designed to manage various hospital operations including patient management, appointment scheduling, and medical record updates. This project was developed using object-oriented programming principles, applying encapsulation, inheritance, and polymorphism to create a modular and maintainable system.

The system supports multiple user roles, including **Doctor**, **Patient**, **Pharmacist**, and **Administrator**. This project specifically focuses on the **Doctor** role, allowing doctors to view and update patient records, manage appointments, and record outcomes of appointments.

## Project Structure
/src

    ├── Doctor.java         // Doctor class that handles doctor's functionalities
    
    ├── Patient.java        // Patient class containing patient information
    
    ├── Appointment.java    // Appointment class for managing appointments
    
    ├── TimeSlot.java       // TimeSlot class for doctor's availability
    
    ├── MedicalRecord.java  // MedicalRecord class to manage patient's medical history
    
    └── Main.java           // Main class with the CLI menu for All role


## Features

### User Features:

### Patient Features:

### Doctor Features:
- **View Patient Medical Records:** View the medical history of patients under the doctor's care.
- **Update Patient Medical Records:** Add new diagnoses and prescriptions to patient records.
- **Manage Appointments:** View the doctor's personal schedule and set availability for appointments.
- **Accept or Decline Appointment Requests:** Accept or reject appointment requests from patients.
- **Record Appointment Outcomes:** Record the outcomes of completed appointments, including services provided and prescribed medications.

### Administrator Features:

## Prerequisites
- Java 8 or higher
- Java Development Kit (JDK) installed on your system
- A terminal or command-line interface to run the application

## Installation

1. Clone this repository to your local machine:
```
git clone https://github.com/yourusername/HospitalManagementSystem.git
```

2. Navigate to the project directory:
```
cd HospitalManagementSystem
```

3. Compile the Java files:
```
javac src/*.java
```

4. Run the program:
```
java src.Main
```


## Usage

### User

### Patient

### Doctor
Upon running the program, the Doctor Menu is displayed with the following options:
```
--- Doctor Menu ---
1. View Patient Medical Records
2. Update Patient Medical Records
3. View Personal Schedule
4. Accept Appointment
5. Decline Appointment
6. Record Appointment Outcome
7. Exit
```

### Administrator



## Example Workflow:
1. View Patient Medical Records: Doctors can view the medical history of a specific patient.

2. Update Medical Records: Doctors can update the medical record with new diagnoses and prescriptions.

3. Manage Appointments: Doctors can view their appointment schedule and manage appointment requests.



## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Work Contribution

User class, features, functions, UML Diagram: Max Wong

Patient classes, features, and functions: Xin Min

Doctor classes, features, and functions: Shao Yang

Pharmacist classes, features, and functions: Jin Lin

Administrator classes, features, and functions: Joyanne