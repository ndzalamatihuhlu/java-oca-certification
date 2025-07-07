# 🚗 Vehicle Registration Console App

**Practical Project 2 – Java OCA Certification**  
**Author**: Ndzalama Tihuhlu  
**Institution**: FMTALI (Faith Mangope Technology and Leadership Institute)  
**Date**: Monday, 7 July 2025  
**IDE Used**: IntelliJ IDEA Community Edition

---

## 📘 Overview

This console-based Java application is designed to simulate a **simple vehicle registration system**. The project introduces learners to the fundamental concepts of:

- Object-Oriented Programming (OOP)
- Java Collections (`ArrayList`)
- Input validation using `Scanner`
- Control flow using `if`, `while`, and `for-each` loops

It was developed as part of **Practice Project 2** for the **Java OCA Certification coursework**.

---

## 📂 Project Structure

```
vehicle-reg-app/
├── src/
│   ├── Main.java            # Main application logic
│   └── Car.java             # Vehicle data model with getters/setters
├── README.md                # Project documentation
└── .gitignore               # Ignore files like .class or /out folder (optional)
```

---

## 🧠 Learning Outcomes

By completing this project, learners will understand how to:

- Define and use custom Java classes
- Use `Scanner` for real-time terminal input
- Store and manage objects using `ArrayList<Car>`
- Perform input validation (e.g., VIN must be 17 characters)
- Loop and re-prompt users until valid data is provided
- Display structured data using formatted console output

---

## 🧾 Features

### Menu System
```
Welcome to Vehicle Reg – Java Console Vehicle Registration App

Menu:
1. Register a new vehicle
2. View vehicle report
3. Exit application
```

### Vehicle Details Captured:
- ✅ Make (e.g. "BMW")
- ✅ Model (e.g. "M4")
- ✅ VIN (17-character string, validated)
- ✅ License Plate (GP only — old format or new format)
    - Old format: ABC123GP
    - New format: AB12CDGP
- ✅ Mileage (integer)
- ✅ Year of Manufacture (e.g. 2025)

### Example Vehicle Registration Flow
```
Enter vehicle make: BMW
Enter vehicle model: M4
Enter 17-character VIN: 12345678901234567
Select license plate format:
1. Old format
2. New format
Choice: 1
Enter old format plate number (e.g. ABC123GP): MPT451GP
Enter mileage: 15200
Enter year of manufacture: 2025
Vehicle registered successfully.
```

### Viewing Vehicle Report
When selecting option 2:
```
========= VEHICLE REPORT =========
Make: BMW
Model: M4
VIN: 12345678901234567
Plate: MPT451GP
Mileage: 15200
Year: 2025
---------------------------
```
If no vehicles exist:
```
No vehicles registered yet.
```

---

## 📦 Technologies Used
- Java SE 8+
- IntelliJ IDEA (Community Edition)
- Java Console I/O (Scanner)
- OOP Principles
- Java Collections (`ArrayList`)

---

## 💻 How to Run the App

1. Open IntelliJ IDEA
2. Create a new **Java Project** named `vehicle-reg-app`
3. Copy `Main.java` and `Car.java` into the `src/` directory
4. Run `Main.java`
5. Interact with the terminal prompts

---

## 🗂 GitHub Instructions (for FMTALI Students)

If you’re adding this project to your GitHub repo:

- Place the folder under:
```
/java-oca-certification/practice-projects/mini-console-apps/vehicle-reg-app/
```
- Include:
    - `Main.java`
    - `Car.java`
    - `README.md`
- Commit message:
```bash
Practical Project 2: Added Vehicle Registration Console App
```

---

## 🧑‍🏫 Instructor Note
This project forms part of the **hands-on training** for FMTALI learners preparing for the Oracle Java OCA 8 exam. It helps reinforce concepts from:
- Week 2 (Operators and Statements)
- Week 3 (Java APIs and Collections)
- Week 4 (Methods and Encapsulation)

Please make sure to test edge cases such as:
- VIN shorter or longer than 17 characters
- Invalid menu options (e.g. `-1`, `0`, `4`)
- Empty input for make/model

---

## ✅ Status
✔️ **Completed – Ready for submission**  
🗓️ **Released by FMTALI: Friday, 20 June 2025**

---

## 📬 Contact
**Author**: Ndzalama Tihuhlu  
📧 Email: [tihuhlundzalama@gmail.com](mailto:tihuhlundzalama@gmail.com)

> “Happy coding and good luck with your Java certification!”
