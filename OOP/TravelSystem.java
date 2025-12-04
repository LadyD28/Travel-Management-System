import java.util.*;

abstract class Ticket {
    protected String destination;
    protected String flightTime;
    protected double basePrice = 1000.0; 
    protected double price;

    public Ticket(String destination, String flightTime) {
        this.destination = destination;
        this.flightTime = flightTime;
    }

    public abstract void calculatePrice(int destinationIndex);

    public String getDestination() { return destination; }
    public String getFlightTime() { return flightTime; }
    public double getPrice() { return price; }
    public abstract String getTicketType(); 
    
    public abstract double getClassPremium(); 
    
    public double getDestinationBasePrice(int destinationIndex) {
        return basePrice + (destinationIndex * 5);
    }
}

class FirstClassTicket extends Ticket {
    private static final double PREMIUM = 300.0; 
    public FirstClassTicket(String destination, String flightTime) {
        super(destination, flightTime);
    }

    @Override
    public void calculatePrice(int destinationIndex) {
        price = getDestinationBasePrice(destinationIndex) + PREMIUM;
    }

    @Override
    public String getTicketType() { return "First Class"; }
    
    
    @Override
    public double getClassPremium() { return PREMIUM; }
}

class BusinessClassTicket extends Ticket {
    private static final double PREMIUM = 150.0; 
    public BusinessClassTicket(String destination, String flightTime) {
        super(destination, flightTime);
    }

    @Override
    public void calculatePrice(int destinationIndex) {
        price = getDestinationBasePrice(destinationIndex) + PREMIUM;
    }

    @Override
    public String getTicketType() { return "Business"; }
    
    @Override
    public double getClassPremium() { return PREMIUM; }
}

class EconomyTicket extends Ticket {
    public EconomyTicket(String destination, String flightTime) {
        super(destination, flightTime);
    }

    @Override
    public void calculatePrice(int destinationIndex) {
        price = getDestinationBasePrice(destinationIndex);
    }

    @Override
    public String getTicketType() { return "Economy"; }
    
    @Override
    public double getClassPremium() { return 0.0; }
}

class Booking {
    private final String travelerName;
    private final Ticket ticket;
    
    public Booking(String travelerName, Ticket ticket) {
        this.travelerName = travelerName;
        this.ticket = ticket;
    }

    public String getTravelerName() { return travelerName; }
    public Ticket getTicket() { return ticket; }

    public void showBooking() {
        System.out.println(travelerName + " - " + ticket.getDestination() + 
                            " (" + ticket.getFlightTime() + ") - $" + String.format("%.2f", ticket.getPrice()));
    }

    
    
    public void generateReceipt(int destinationIndex) {
        
        double destBase = ticket.getDestinationBasePrice(destinationIndex);
        double initialBase = ticket.basePrice;
        double destAdj = destBase - initialBase;
        double classPremium = ticket.getClassPremium();
        
        System.out.println("\n================ BOOKING RECEIPT ================");
        System.out.println("Airline: MARIA LEONORA TERESA AIRLINES");
        System.out.println("------------------ DETAILS ----------------------");
        System.out.println("Traveler: " + travelerName);
        System.out.println("Destination: " + ticket.getDestination());
        System.out.println("Flight Time: " + ticket.getFlightTime());
        System.out.println("Ticket Type: " + ticket.getTicketType());
        System.out.println("------------------- CHARGES ---------------------");
        System.out.println(String.format("Base Fare:              $%.2f", initialBase));
        System.out.println(String.format("Destination Adj.:       $%.2f", destAdj));
        System.out.println(String.format("Class Premium:          $%.2f", classPremium));
        System.out.println("-------------------------------------------------");
        System.out.println(String.format("Total Charged:          $%.2f", ticket.getPrice()));
        System.out.println("=================================================");
    }
}

class User {
    private final String username;
    private final String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public boolean checkPassword(String password) { 
        return this.password.equals(password); 
    }
}

public class TravelSystem {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static User currentUser = null;
    
    private static final Ticket PRICE_CALCULATOR = new EconomyTicket("", "");

    static String[] destinations = {"Australia", "Canada", "China", "Guam", "Hong Kong", "Indonesia", "Japan", "South Korea", "Kuwait", "Malaysia", "New Guinea", "Qatar", "Saudi Arabia", "Singapore", "Taiwan", "Thailand", "United Arab Emirates", "United States", "Vietnam", "Cambodia", "Brunei", "India", "New Zealand"};
    static String[][] flightTimes = {
        {"09:00", "14:00", "22:00"},
        {"01:30", "10:00", "22:55"},
        {"08:30", "13:45", "19:20"},
        {"07:45", "12:30", "17:15"},
        {"09:15", "14:40", "20:10"},
        {"08:00", "13:20", "18:45"},
        {"15:05", "19:30", "23:45"},
        {"08:20", "14:10", "20:35"},
        {"02:00", "09:30", "18:00"},
        {"08:45", "13:50", "19:25"},
        {"10:15", "15:40", "21:00"},
        {"03:30", "11:00", "20:15"},
        {"01:45", "10:20", "19:30"},
        {"09:00", "14:25", "20:10"},
        {"07:00", "13:00", "18:45"},
        {"08:40", "14:15", "19:50"},
        {"04:15", "12:45", "21:30"},
        {"23:30", "02:45", "11:20"},
        {"08:25", "13:55", "19:30"},
        {"09:10", "14:35", "20:05"},
        {"08:50", "14:20", "19:55"},
        {"11:30", "17:00", "22:45"},
        {"21:00", "01:15", "09:40"}
    };
    

    public static void main(String[] args) {
        System.out.println("\n========= TRAVEL BOOKING SYSTEM =========");
        
        boolean running = true;
        while (running) {
            if (currentUser == null) {
                running = loginMenu();
            } else {
                running = bookingMenu();
            }
        }
        System.out.println("=========================================");
        System.out.println("================ GOODBYE! ===============");
    }
    
    static boolean loginMenu() {
        System.out.println("WELCOME TO MARIA LEONORA TERESA AIRLINES!");
        System.out.println("=========================================");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("=========================================");
        System.out.print("Choose: ");
        
        String choice = scanner.nextLine();
        
        if (choice.equals("1")) {
            register();
        } else if (choice.equals("2")) {
            
            if (users.isEmpty()) {
                System.out.println("=========================================");
                System.out.println("No accounts registered. Please register first (Option 1).");
                System.out.println("=========================================");
            } else {
                login();
            }
        } else if (choice.equals("3")) {
            return false;
        }
        return true;
    }
    
    static void register() {
        System.out.println("\n============ REGISTER ACCOUNT ===========");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                System.out.println("Username taken!");
                System.out.println("=========================================");
                return;
            }
        }
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        users.add(new User(username, password));
        System.out.println("Registered! Please login.\n");
        System.out.println("=========================================");
    }
    
    static void login() {
        System.out.println("\n=========== LOGIN YOUR ACCOUNT ==========");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        for (User u : users) {
            if (u.getUsername().equals(username) && 
                u.checkPassword(password)) {
                currentUser = u;
                System.out.println("Welcome " + username + "!");
                System.out.println("=========================================");
                return;
            }
        }
        System.out.println("Wrong username/password!");
        System.out.println("=========================================");
    }
    
    static boolean bookingMenu() {
        System.out.println("\n=========== WOULD YOU LIKE TO ===========");
        System.out.println("1. Book Tickets");
        System.out.println("2. View Bookings");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Logout");
        System.out.println("=========================================");
        System.out.print("Choose: ");
        
        String choice = scanner.nextLine();
        
        if (choice.equals("1")) {
            bookTickets();
        } else if (choice.equals("2")) {
            viewBookings();
        } else if (choice.equals("3")) {
            cancelBooking();
        } else if (choice.equals("4")) {
            currentUser = null;
            System.out.println("Logged out!");
            System.out.println("=========================================");
        }
        return true;
    }
    
    static void bookTickets() {
        ArrayList<String> travelerNames = new ArrayList<>(); 
        double totalChargedPrice = 0.0; 
        
        try {
            
            System.out.println("\n=== DESTINATIONS (Economy Base Price) ===");
            for (int i = 0; i < destinations.length; i++) {
                
                double basePrice = PRICE_CALCULATOR.getDestinationBasePrice(i);
                System.out.println(String.format("%d. %-25s ($%.2f)", 
                                                (i + 1), 
                                                destinations[i], 
                                                basePrice));
            }
            System.out.println("=========================================");
            System.out.print("Choose destination: ");
            int dest = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (dest < 0 || dest >= destinations.length) throw new Exception("Invalid destination.");

            
            System.out.println("\n============== FLIGHT TIME ==============");
            System.out.println(destinations[dest] + ":");
            for (int i = 0; i < flightTimes[dest].length; i++) {
                System.out.println((i+1) + ". " + flightTimes[dest][i]);
            }
            System.out.println("=========================================");
            System.out.print("Choose time: ");
            int time = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (time < 0 || time >= flightTimes[dest].length) throw new Exception("Invalid time.");

            String destination = destinations[dest];
            String flightTime = flightTimes[dest][time];
            
            System.out.println("=========================================");
            System.out.print("How many travelers? ");
            int numTravelers = Integer.parseInt(scanner.nextLine());
            
            System.out.println("\n============ TRAVELER DETAILS ===========");
            
            for (int i = 1; i <= numTravelers; i++) {
                System.out.println("--------------- Traveler " + i + " --------------");
                
                System.out.println("Destination: " + destination);
                double ecoPrice = new EconomyTicket(destination, flightTime).getDestinationBasePrice(dest);
                
                System.out.println(String.format("1. First Class: ($%.2f)", ecoPrice + 300));
                System.out.println(String.format("2. Business: ($%.2f)", ecoPrice + 150));
                System.out.println(String.format("3. Economy: ($%.2f)", ecoPrice));
                System.out.println("=========================================");
                System.out.print("Ticket type: ");
                int ticketType = Integer.parseInt(scanner.nextLine());
                
                Ticket ticket = null;
                if (ticketType == 1) {
                    ticket = new FirstClassTicket(destination, flightTime);
                } else if (ticketType == 2) {
                    ticket = new BusinessClassTicket(destination, flightTime);
                } else if (ticketType == 3) {
                    ticket = new EconomyTicket(destination, flightTime);
                }
                
                if (ticket != null) {
                    ticket.calculatePrice(dest); 
                    System.out.print("Traveler name: ");
                    String name = scanner.nextLine();
                    
                    Booking newBooking = new Booking(name, ticket);
                    bookings.add(newBooking); 

                    travelerNames.add(name);
                    totalChargedPrice += ticket.getPrice();
                    
                } else {
                    System.out.println("Invalid ticket type. Skipping traveler.");
                }
            }
            
            System.out.println("=========================================");
            System.out.println("Bookings added!");
        
            double initialBase = PRICE_CALCULATOR.basePrice; 
            double destBase = PRICE_CALCULATOR.getDestinationBasePrice(dest);
            double destAdj = destBase - initialBase;
            
            generateCombinedReceipt(destination, flightTime, travelerNames, initialBase, destAdj, totalChargedPrice);

            System.out.println("=========================================");
            
        } catch (Exception e) {
            System.out.println("=========================================");
            System.out.println("Input error! Try again. Details: " + e.getMessage());
            System.out.println("=========================================");
        }
    }
    
    static void generateCombinedReceipt(String destination, String flightTime, ArrayList<String> names, double initialBase, double destAdj, double totalPrice) {
        System.out.println("=========================================");
        System.out.println("\n============ BOOKING RECEIPT ============");
        System.out.println("Airline: MARIA LEONORA TERESA AIRLINES");
        System.out.println("--------------- DETAILS -----------------");
        System.out.println("Destination: " + destination);
        System.out.println("Flight Time: " + flightTime);
        System.out.println("Total Travelers: " + names.size());
        System.out.println("Travelers: " + String.join(", ", names)); // List all names
        System.out.println("----------------- CHARGES ---------------");
         
        double singleDestinationBase = initialBase + destAdj;
        double totalDestinationBase = singleDestinationBase * names.size();
        
        double totalPremium = totalPrice - totalDestinationBase;
        
        System.out.println(String.format("Total Destination Base: $%.2f", totalDestinationBase));
        System.out.println(String.format("Total Class Premium:    $%.2f", totalPremium));
        System.out.println("-----------------------------------------");
        System.out.println(String.format("GRAND TOTAL CHARGED:    $%.2f", totalPrice));
        System.out.println("=========================================");
    }
    
    static void viewBookings() {
        System.out.println("=========================================");
        System.out.println("\n============= CURRENT BOOKINGS ===========");
        boolean hasBookings = false;
        for (int i = 0; i < bookings.size(); i++) {
            System.out.print((i+1) + ". ");
            bookings.get(i).showBooking();
            hasBookings = true;
        }
        if (!hasBookings) {
            System.out.println("No Bookings.");
        }
        System.out.println("=========================================");
    }
    
    static void cancelBooking() {
        viewBookings();
        if (bookings.isEmpty()) {
            return;
        }
        
        System.out.print("Cancel which booking (number)? ");
        try {
            int num = Integer.parseInt(scanner.nextLine()) - 1;
            if (num >= 0 && num < bookings.size()) {
                Booking canceledBooking = bookings.remove(num);
                System.out.println("Booking for " + canceledBooking.getTravelerName() + " canceled!");
                System.out.println("=========================================");
            } else {
                System.out.println("Invalid number!");
                System.out.println("=========================================");
            }
        } catch (Exception e) {
            System.out.println("Invalid input!");
            System.out.println("=========================================");
        }
    }
}
