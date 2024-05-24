import java.util.ArrayList;
import java.util.Scanner;

public class Task3{
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Booking> bookings  = new ArrayList<>();
    private static ArrayList<Payment> payments = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // Sample data
        users.add(new User("admin", "admin123", "admin123@gmail.com", "1234567890", "admin"));
        rooms.add(new Room("Standard", 100.0, "WiFi - TV", "A standard room", "available"));

        while(true){
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    register(sc);
                    break;
                case 2:
                    login(sc);
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    private static void register(Scanner sc){
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();
        System.out.print("Enter phone: ");
        String phone = sc.next();
        System.out.print("Enter email: ");
        String email = sc.next();

        users.add(new User(username, password, email, phone, "user"));
        System.out.println("Registration successful!");
    }

    private static void login(Scanner sc){
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                loggedInUser = user;
                System.out.println("Login Successfully");
                showMenu(sc);
                return;
            }
        }
        System.out.println("Invalid credentials");
    }

    private static void showMenu(Scanner sc){
        if(loggedInUser.getRole().equals("admin")){
            adminMenu(sc);
        }
        else{
            userMenu(sc);
        }
    }

    private static void adminMenu(Scanner sc){
        while(true){
            System.out.println("1. Manage Rooms");
            System.out.println("2. View Booking");
            System.out.println("3. Logout");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    manageRooms(sc);
                    break;
                case 2:
                    viewBookings();
                    break;
                case 3:
                    loggedInUser = null;
                    return;
            }
        }
    }

    private static void manageRooms(Scanner sc){
        while(true){
            System.out.println("1. Add Room");
            System.out.println("2. Update Room");
            System.out.println("3. Delete Room");
            System.out.println("4. Back");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    addRoom(sc);
                    break;
                case 2:
                    updateRoom(sc);
                    break;
                case 3:
                    deleteRoom(sc);
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void addRoom(Scanner sc){
        System.out.print("Enter room type: ");
        String roomType = sc.next();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter amenities: ");
        String amenities = sc.next();
        System.out.print("Enter description: ");
        String description = sc.next();
        System.out.print("Enter status: ");
        String status = sc.next();

        rooms.add(new Room(roomType, price, amenities, description, status));
        System.out.println("Room added successfully");
    }

    private static void updateRoom(Scanner sc){
        System.out.print("Enter room ID to update: ");
        int roomId = sc.nextInt();

        Room room = findRoomById(roomId);
        if(room != null){
            System.out.println("Enter new room type (current: " + room.getRoomType() + "):");
            String roomType = sc.next();
            System.out.println("Enter new price (current: " + room.getPrice() + "):");
            double price = sc.nextDouble();
            System.out.println("Enter new amenities (current: " + room.getAmenities() + "):");
            String amenities = sc.next();
            System.out.println("Enter new description (current: " + room.getDescription() + "):");
            String description = sc.next();
            System.out.println("Enter new status (current: " + room.getStatus() + "):");
            String status = sc.next();

            room.setRoomType(roomType);
            room.SetPrice(price);
            room.setAminities(amenities);
            room.setDescription(description);
            room.setStatus(status);

            System.out.println("Room updated successfully");
        }
        else{
            System.out.println("Room not found");
        }
    }

    private static void deleteRoom(Scanner sc){
        System.out.print("Enter room ID to delete: ");
        int roomId = sc.nextInt();

        Room room = findRoomById(roomId);
        if(room != null){
            rooms.remove(room);
            System.out.println("Room deleted successfully");
        }
        else{
            System.out.println("Room not found!");
        }
    }

    private static Room findRoomById(int roomId){
        for(Room room : rooms){
            if(room.getRoomId() == roomId){
                return room;
            }
        }
        return null;
    }

    private static void viewBookings(){
        if(bookings.isEmpty()){
            System.out.println("No booking found.");
            return;
        }

        for(Booking booking : bookings){
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("User ID: " + booking.getUserId());
            System.out.println("Room ID: " + booking.getRoomId());
            System.out.println("Check-in Date: " + booking.getCheckInDate());
            System.out.println("Check-out Date: " + booking.getCheckOutDate());
            System.out.println("Status: " + booking.getStatus());
            System.out.println("------------");
        }
    }

    private static void userMenu(Scanner sc){
        while(true){
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. View My Bookings");
            System.out.println("4. Logout");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom(sc);
                    break;
                case 3:
                    viewMyBookings();
                    break;
                case 4:
                    loggedInUser = null;
                    return;
            }
        }
    }

    private static void viewRooms(){
        if(rooms.isEmpty()){
            System.out.println("No rooms available.");
            return;
        }
        for (Room room : rooms){
            System.out.println("Room ID: " + room.getRoomId());
            System.out.println("Room Type: " + room.getRoomType());
            System.out.println("Price: " + room.getPrice());
            System.out.println("Amenities: " + room.getAmenities());
            System.out.println("Description: " + room.getDescription());
            System.out.println("Status: " + room.getStatus());
            System.out.println("-----------");
        }
    }

    private static void bookRoom(Scanner sc){
        System.out.print("Enter room ID to book: ");
        int roomId = sc.nextInt();

        Room room = findRoomById(roomId);
        if(room != null && room.getStatus().equalsIgnoreCase("available")){
            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            String checkInDate = sc.next();
            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            String checkOutDate = sc.next();

            Booking booking = new Booking(loggedInUser.getUserId(), roomId, checkInDate, checkOutDate, "booked");
            bookings.add(booking);

            room.setStatus("booked");

            // Simulated payment
            System.out.print("Enter payment method (e.g., card, paypal): ");
            String paymentMethod = sc.next();
            Payment payment = new Payment(booking.getBookingId(), room.getPrice(), "completed", paymentMethod);
            payments.add(payment);

            System.out.println("Room booked successfully! Payment completed.");
        }
        else{
            System.out.println("Room is not available or not found.");
        }
    }

    private static void viewMyBookings(){
        boolean hasBooking = false;

        for(Booking booking : bookings){
            if(booking.getUserId() == loggedInUser.getUserId()){
                hasBooking = true;
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("Room ID: " + booking.getRoomId());
                System.out.println("Check-in Date: " + booking.getCheckInDate());
                System.out.println("Check-out Date: " + booking.getCheckOutDate());
                System.out.println("Status: " + booking.getStatus());
                System.out.println("------------");
            }
        }

        if(!hasBooking){
            System.out.println("You have no bookings.");
        }
    }

    // User class
    static class User{
        private static int idCounter = 1;
        private int userId;
        private String username;
        private String password;
        private String email;
        private String phone;
        private String role;

        public User(String username, String password, String email, String phone, String role){
            this.userId = idCounter++;
            this.username = username;
            this.password = password;
            this.email = email;
            this.phone = phone;
            this.role = role;
        }

        // Getters and setters
        public int getUserId(){
            return userId;
        }

        public String getUsername(){
            return username;
        }

        public String getPassword(){
            return password;
        }

        public String getEmail(){
            return email;
        }

        public String getPhone(){
            return phone;
        }

        public String getRole(){
            return role;
        }

        public void setPassword(String password){
            this.password = password;
        }

        public void setEmail(String email){
            this.email = email;
        }

        public void setPhone(String phone){
            this.phone = phone;
        }
    }

    // Room class
    static class Room{
        private static int idCounter = 1;
        private int roomId;
        private String roomType;
        private double price;
        private String amenities;
        private String description;
        private String status;

        public Room(String roomType, double price, String amenities, String description, String status){
            this.roomId = idCounter++;
            this.roomType = roomType;
            this.price = price;
            this.amenities = amenities;
            this.description = description;
            this.status = status;
        }

        // Getters and setters
        public int getRoomId(){
            return roomId;
        }

        public String getRoomType(){
            return roomType;
        }

        public double getPrice(){
            return price;
        }

        public String getAmenities(){
            return amenities;
        }

        public String getDescription(){
            return description;
        }

        public String getStatus(){
            return status;
        }

        public void setRoomType(String roomType){
            this.roomType = roomType;
        }

        public void SetPrice(double price){
            this.price = price;
        }

        public void setAminities(String amenities){
            this.amenities = amenities;
        }

        public void setDescription(String description){
            this.description = description;
        }

        public void setStatus(String status){
            this.status = status;
        }
    }

    // Booking class
    static class Booking{
        private static int idCounter = 1;
        private int bookingId;
        private int userId;
        private int roomId;
        private String checkInDate;
        private String checkOutDate;
        private String status;

        public Booking(int userId, int roomId, String checkInDate, String checkOutDate, String status){
            this.bookingId = idCounter++;
            this.userId = userId;
            this.roomId = roomId;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.status = status;
        }

        // Getters and setters
        public int getBookingId(){
            return bookingId;
        }

        public int getUserId(){
            return userId;
        }

        public int getRoomId(){
            return roomId;
        }

        public String getCheckInDate(){
            return checkInDate;
        }

        public String getCheckOutDate(){
            return checkOutDate;
        }

        public String getStatus(){
            return status;
        }

        public void setStatus(String status){
            this.status = status;
        }
    }

    // Payment class
    static class Payment{
        private static int idCounter = 1;
        private int paymentId;
        private int bookingId;
        private double amount;
        private String paymentStatus;
        private String paymentMethod;

        public Payment(int bookingId, double amount, String paymentStatus, String paymentMethod){
            this.paymentId = idCounter++;
            this.bookingId = bookingId;
            this.amount = amount;
            this.paymentStatus = paymentStatus;
            this.paymentMethod = paymentMethod;
        }

        // Getters and setters
        public int getPaymentId(){
            return paymentId;
        }

        public int getBookingId(){
            return bookingId;
        }

        public double getAmount(){
            return amount;
        }

        public String getPaymentStatus(){
            return paymentStatus;
        }

        public String getPaymentMethod(){
            return paymentMethod;
        }

        public void setPaymentStatus(String paymentStatus){
            this.paymentStatus = paymentStatus;
        }
    }
}
