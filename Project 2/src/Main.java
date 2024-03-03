import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        Route route1 = new Route("Ad Dammam", "Al Khobar", 50.0);
        Route route2 = new Route("Al Khobar", "Al Riyadh", 400.0);

        Car car1 = new Car("TC1043", route1, 4);
        Car car2 = new Car("FT8431", route2, 0);
        Car car3 = new Car("GF9482", route2, 6);

        Passenger[] passengers = {
                new Subscribers("Nahj", "Na1242"),
        new NonSubscribers("Mansour", "Man5432", true),
        new NonSubscribers("Abbad", "Abb1654", false)
        };

        //Extra functions
        System.out.println("Hello, Welcome To CarP");
        System.out.println("Please Enter your ID: ");
        String ID = inp.nextLine();
//Extra functions
        Passenger enteredPassenger = null;
        for (Passenger passenger : passengers) {
            if (passenger.getID().equals(ID)) {
                enteredPassenger = passenger;
                break;
            }
        }

        if (enteredPassenger == null) {
            System.out.println("Passenger ID not found.");
            return;
        }
        System.out.println();
        System.out.println();
        //Extra functions
        if (enteredPassenger instanceof Subscribers) {
            System.out.println("Hello " + enteredPassenger.getName() + ", it appears you're subscribed to our app.");
            System.out.println("You have a 50% discount coupon.\n ");
        } else {
            System.out.println("Hello " + enteredPassenger.getName() + ", you're not subscribed to our app.");
            if (((NonSubscribers) enteredPassenger).isDiscountCoupon()) {
                System.out.println("You have a 10% discount coupon.");
            } else {
                System.out.println("You don't have any discount coupon.");
            }
        }
        //Extra functions
        System.out.println("\nOur Available cars for today are: ");
        System.out.println(car1);
        System.out.println();
        System.out.println(car2);
        System.out.println();
        System.out.println(car3);
        //Extra functions
        System.out.println("\nEnter the code of the car you want to reserve: ");
        String code = inp.nextLine();
        Car selectedCar=null;
        for (Car car : new Car[]{car1, car2,car3}) {
            if (code.equals(car.getCode())) {
                selectedCar = car;
                break;
            }
        }

        if (selectedCar == null) {
            System.out.println("Invalid car code.");
            return;
        }

        try {
            enteredPassenger.reserveCar(selectedCar);
            System.out.println("Reservation successful:");
            enteredPassenger.displayInfo();
        } catch (Exception e) {
            System.out.println("Reservation failed: " + e.getMessage());
        }
    }
}
