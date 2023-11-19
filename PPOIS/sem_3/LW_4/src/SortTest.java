import org.junit.Test;
import org.junit.Assert;

public class SortTest {
    @Test
    public void PersonSortingTest() {
        Person[] people = {
                new Person("Lars", 58),
                new Person("James", 63),
                new Person("Robert", 45)
        };
        Sort.quickSort(people);

        Person[] peopleTest = {
                new Person("Robert", 45),
                new Person("Lars", 58),
                new Person("James", 63)
        };
        Assert.assertArrayEquals(peopleTest, people);
    }

    @Test
    public void CarSortingTest() {
        Car[] cars = {
                new Car("Toyota", 250),
                new Car("BMW", 350),
                new Car("Honda", 280)
        };
        Sort.quickSort(cars);

        Car[] carsTest = {
                new Car("BMW", 350),
                new Car("Honda", 280),
                new Car("Toyota", 250)
        };

        Assert.assertArrayEquals(carsTest, cars);
    }

    @Test
    public void PersonSortingWithComparatorTest() {
        Person[] people = {
                new Person("Till", 60),
                new Person("Richard", 48),
                new Person("Paul", 55)
        };
        Sort.quickSort(people, new PersonNameComparator());

        Person[] peopleTest = {
                new Person("Paul", 55),
                new Person("Richard", 48),
                new Person("Till", 60)
        };

        Assert.assertArrayEquals(peopleTest, people);
    }

    @Test
    public void CarSortingWithComparatorTest() {
        Car[] cars = {
                new Car("Mercedes-Benz", 380),
                new Car("Nissan", 320),
                new Car("Volkswagen", 500)
        };
        Sort.quickSort(cars, new CarMaxSpeedComparator());

        Car[] carsTest = {
                new Car("Nissan", 320),
                new Car("Mercedes-Benz", 380),
                new Car("Volkswagen", 500)
        };
        Assert.assertArrayEquals(carsTest, cars);
    }

    @Test
    public void IntSorterTest() {
        Integer[] num = {9, 4, 1, 8, 6};
        Sort.quickSort(num);

        Integer[] numTest = {1, 4, 6, 8, 9};

        Assert.assertArrayEquals(numTest, num);
    }


}
