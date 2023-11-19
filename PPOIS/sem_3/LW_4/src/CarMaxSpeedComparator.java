import java.util.Comparator;

public class CarMaxSpeedComparator implements Comparator<Car> {
    @Override
    public int compare(Car firstCar, Car secondCar) {
        return Integer.compare(firstCar.getMaxSpeed(), secondCar.getMaxSpeed());
    }
}