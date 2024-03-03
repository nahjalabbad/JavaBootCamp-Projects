
class Car {
    private String code;
    private Route route;
    private int maxCapacity;

    public Car(String code, Route route, int maxCapacity) {
        this.code = code;
        this.route = route;
        this.maxCapacity = maxCapacity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return
                "Car Code " + code +
                "\nRoute " + route +
                "\nMaximum Capacity " + maxCapacity ;
    }
}