import java.util.Objects;

public class Car implements Comparable<Car> {
    private String brand;
    private int maxSpeed;

    public Car(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public int compareTo(Car secondCar) {
        return this.brand.compareTo(secondCar.brand);
    }

    @Override
    public String toString() {
        return "{Brand: " + brand + ", MaxSpeed: " + maxSpeed + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return maxSpeed == car.maxSpeed && Objects.equals(brand, car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, maxSpeed);
    }
}