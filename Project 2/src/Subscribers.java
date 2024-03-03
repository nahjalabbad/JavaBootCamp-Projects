class Subscribers extends Passenger {
    public Subscribers(String name, String ID) {
        super(name, ID);
    }

    @Override
    public void reserveCar(Car car) throws Exception {
        if (car.getMaxCapacity() == 0) {
            throw new Exception("Maximum capacity of the car is zero.");
        }
        setReservedCar(car);
        setTripCost(car.getRoute().getPrice() * 0.5);
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getID());
        System.out.println("Reserved Car Code: " + getReservedCar().getCode());
        System.out.println("Trip Cost: " + getTripCost());
    }
}