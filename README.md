<h1 align = "center">✈️ Travel Management System</h1>
<h3 align = "center">A Java console-based flight system </h3>
<p align = "center">
<b>BSIT 2110 </b> <br/>
Cortez, Angela Mae M. <br/>
Dimalibot, Lady Arowen D. <br/>
Macatangay, Leewell Layne M.
</p>

## 🌟 ┊OVERVIEW

This **Travel Management System** is a console application designed to simplify flight reservations by providing a structured, interactive platform. Its primary purpose is to allow users to securely book flights after completing a user registration and login process. Key functionality includes the ability for users to choose their desired travel destination, select a specific flight time, and reserve tickets for multiple passengers in various ticket classes. The system also supports viewing, canceling existing bookings and generating receipts.
<br/><br/>
The application is built using fundamental **Object-Oriented Programming (OOP) concepts**— such as encapsulation, inheritance, polymorphism, and abstraction—to provide an authentic and robust booking experience that features data encapsulation, validation, and session management, effectively replicating a real-life travel booking platform.
<br>

### Users can:
🔑 Login/Register
🎟️ Add a new booking<br/>
📜 View all active bookings<br/>
❌ Cancel a specific booking<br/>
🧾 Generate a detailed receipt upon booking completion<br/>
🚪 Logout and return to main menu

---

## 📁 ┊ PROGRAM STRUCTURE
```
📂 OOP/
└── TravelSystem.java          
├── User.java                  
├── Booking.java               
├── Ticket.java                
├── FirstClassTicket.java      
├── BusinessClassTicket.java   
├── EconomyTicket.java         
└── README.md                  
```
**CLASS DESCRIPTIONS**<br>
└── **`TravelSystem`**
      | `Contains the main() method and runs the whole program`
```
    Entry point of the program (main method)
    Manages: User registration and login, Booking of tickets, Viewing bookings, Canceling bookings and
    Combined receipts for multiple travelers.
    Stores: 
        -   ArrayList<User> users
        -	ArrayList<Booking> bookings
        -	User currentUser
```

└── **`User`**
      | `User login/registration authentication`
```
    Represents a system user with username and password
    Used for login/registration authentication
```

└── **`Booking`**
      | `Used by the main system to store all bookings`
```
    Encapsulates (Encapsulation) the traveler’s name and a Ticket object.
    Provides:
        -	showBooking() – displays a short booking summary.
        -	generateReceipt(...) – prints a detailed breakdown (per booking).
```

└── **`Ticket (Abstract Class)`**
      | `The structure for all ticket types`
```
    Defines the abstract structure for all ticket types.
    Contains shared attributes: destination, flightTime, basePrice, and price
    Also provides a helper method: getDestinationBasePrice(int index).
```

└── **`FirstClassTicket`, `BusinessClassTicket`, `EconomyTicket`** | <br/>
`These classes inherit from Ticket and each implements the abstract methods.`
    
  ── **`FirstClassTicket`**<br/>
  
  ```
        - Adds a fixed premium of 300.
        - Overrides calculation and type name.
  ```
    
  ── **`BusinessClassTicket`**<br/>

 ```
        -Adds a fixed premium of 150.
 ```
    
  ── **`EconomyTicket`**<br/>
  ```  
        -	No premium; uses only the base price
  ```  
    

## 🛠️ ┊ HOW TO RUN THE PROGRAM

**Execution Flow:**<br/>
    `Authentication:` The user is first taken to the login and registration menu when the program starts. <br/>
    `Main Menu:` Once logged in, the user can choose to Book Tickets, View Bookings, Cancel Booking, or Log out<br/>
    `Booking Process:` Select "Book Tickets", then the user selects a destination from the list of destination displayed by the system and each with its corresponding economy base fare, choose an available flight time, and input the number of travelers. For each traveler, the user selects the ticket type (First Class, Business, or Economy) and enters the traveler's name.<br/>
    `Receipt Generation:` A combined receipt is generated, summarizing the details including the chosen destination, flight time, the list of traveler names, and providing a breakdown of charges (base fare, premiums, and final total cost).<br/>
    `Management:` The user can use View Bookings or Cancel Booking as needed. Selecting Logout returns the user to the login screen, while choosing “Exit” from the initial menu closes the program.<br/>

### 🧩 OOP CONCEPTS APPLIED 

The project is structured around several classes, each designed to illustrate a fundamental OOP principle.

### 💡 Abstraction (The Ticket Class) 

Abstraction is achieved using the abstract class Ticket. It defines a common interface for all ticket types without providing the specific implementation details for pricing. It contains common fields like destination**, flightTime, and basePrice. It declares the abstract methods calculatePrice(int destinationIndex) and getClassPremium(), forcing all subclasses (concrete ticket types) to define their own specific pricing logic and premium amount.

### 🧬 Inheritance and  🎭 Polymorphism (The Ticket Subclasses)

**Inheritance** allows the subclasses `FirstClassTicket,` `BusinessClassTicket,` and `EconomyTicket` all extend Ticket, inheriting the common flight details and the getDestinationBasePrice helper logic. They then use **Polymorphism** to calculate their specific final prices (with premiums) using the shared calculatePrice() method.

>EconomyTicket.calculatePrice(): Price is just the destination base price<br/>
BusinessClassTicket.calculatePrice(): Price is base price + $150 premium.<br/>
FirstClassTicket.calculatePrice(): Price is base price + $300 premium.<br/>

### 💊 Encapsulation (The Booking Class)

The Booking class encapsulates a single booking record, containing the traveler's name (travelerName) and the ticket details (ticket). Both fields are declared as private and final, preventing external classes from modifying the booking details directly after creation. Access is provided only through controlled public methods (getTravelerName(), getTicket(), showBooking()).


## 🖥️ ┊SAMPLE OUTPUT 

**This demonstrates:**

>Authentication (Login)<br/>
Navigation (Main Menu)<br/>
Booking Logic (Destination/Class Selection)<br/>
OOP Logic (Price calculation based on class premium)<br/>
Final Output (The receipt)<br/>

```
========= TRAVEL BOOKING SYSTEM =========
WELCOME TO MARIA LEONORA TERESA AIRLINES!
=========================================
1. Register
2. Login
3. Exit
=========================================
Choose: 1

```
```
============ REGISTER ACCOUNT ===========
Username: leewell
Password: leewell01
Registered! Please login.

=========================================
WELCOME TO MARIA LEONORA TERESA AIRLINES!
=========================================
1. Register
2. Login
3. Exit
=========================================
Choose: 2
```
```
=========== LOGIN YOUR ACCOUNT ==========
Username: leewell
Password: leewell01
Welcome leewell!
=========================================

=========== WOULD YOU LIKE TO ===========
1. Book Tickets
2. View Bookings
3. Cancel Booking
4. Logout
=========================================
Choose: 1
```
```
============== DESTINATIONS (Economy Base Price) =============
1. Australia                 ($1000.00)
2. Canada                    ($1005.00)
3. China                     ($1010.00)
4. Guam                      ($1015.00)
5. Hong Kong                 ($1020.00)
6. Indonesia                 ($1025.00)
7. Japan                     ($1030.00)
8. South Korea               ($1035.00)
9. Kuwait                    ($1040.00)
10. Malaysia                  ($1045.00)
11. New Guinea                ($1050.00)
12. Qatar                     ($1055.00)
13. Saudi Arabia              ($1060.00)
14. Singapore                 ($1065.00)
15. Taiwan                    ($1070.00)
16. Thailand                  ($1075.00)
17. United Arab Emirates      ($1080.00)
18. United States             ($1085.00)
19. Vietnam                   ($1090.00)
20. Cambodia                  ($1095.00)
21. Brunei                    ($1100.00)
22. India                     ($1105.00)
23. New Zealand               ($1110.00)
==============================================================
Choose destination: 7
```
```
=============== FLIGHT TIME ===============
Japan:
1. 15:05
2. 19:30
3. 23:45
=========================================
Choose time: 2
=========================================
How many travelers? 2
```
```
================ TRAVELER DETAILS ================
----------------- Traveler 1 ------------------
Destination: Japan
1. First Class: ($1330.00)
2. Business: ($1180.00)
3. Economy: ($1030.00)
Ticket type: 1
Traveler name: leewell
----------------- Traveler 2 ------------------
Destination: Japan
1. First Class: ($1330.00)
2. Business: ($1180.00)
3. Economy: ($1030.00)
Ticket type: 1
Traveler name: angela
=========================================
Bookings added!


============== BOOKING RECEIPT ===============
Airline: MARIA LEONORA TERESA AIRLINES
------------------ DETAILS -------------------------
Destination: Japan
Flight Time: 19:30
Total Travelers: 2
Travelers: leewell, angela
------------------- CHARGES ------------------------
Total Destination Base: $2060.00
Total Class Premium:    $600.00
----------------------------------------------------
GRAND TOTAL CHARGED:    $2660.00
=====================================================
=========================================

=========== WOULD YOU LIKE TO ===========
1. Book Tickets
2. View Bookings
3. Cancel Booking
4. Logout
=========================================
Choose:
```

---

## 👥 ┊ CONTRIBUTORS
| Name | Role |
|------|------|
| **Cortez, Angela Mae M.** | Member |
| **Dimalibot, Lady Arowen D.** | Member |
| **Macatangay, Leewell Layne M.** | Member |

## 💖 ACKNOWLEDGEMENT
We sincerely express our profound gratitude to our instructor for their invaluable guidance and continuous support throughout the completion of this project. Furthermore, we extend our deep appreciation to our classmates and peers for their excellent cooperation, constructive feedback, and unwavering encouragement during the entire development process.
