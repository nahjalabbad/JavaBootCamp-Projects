    class NonSubscribers extends Passenger {
    private boolean discountCoupon;

    public NonSubscribers(String name, String ID, boolean discountCoupon) {
        super(name, ID);
        this.discountCoupon = discountCoupon;
    }

    @Override
    public void reserveCar(Car car) throws Exception {
        if (car.getMaxCapacity() == 0) {
            throw new Exception("Maximum capacity of the car is zero.");
        }
        setReservedCar(car);
        if (discountCoupon) {
            setTripCost(car.getRoute().getPrice() - (car.getRoute().getPrice() * 0.1));
        } else {
            setTripCost(car.getRoute().getPrice());
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getID());
        if (getReservedCar() != null) {
            System.out.println("Reserved Car Code: " + getReservedCar().getCode());
        } else {
            System.out.println("Reserved Car: None");
        }
        System.out.println("Trip Cost: " + getTripCost());
    }

    public boolean isDiscountCoupon() {
        return discountCoupon;
    }

    public void setDiscountCoupon(boolean discountCoupon) {
        this.discountCoupon = discountCoupon;
    }
}
