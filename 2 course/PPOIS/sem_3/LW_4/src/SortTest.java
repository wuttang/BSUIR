import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;

public class SortTest {
    @Test
    public void PersonSortingTest() throws Exception {
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
    public void CarSortingTest() throws Exception {
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
    public void PersonSortingWithComparatorTest() throws Exception {
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
    public void CarSortingWithComparatorTest() throws Exception {
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
    public void IntSorterTest() throws Exception {
        Integer[] num = {9, 4, 1, 8, 6};
        Sort.quickSort(num);

        Integer[] numTest = {1, 4, 6, 8, 9};

        Assert.assertArrayEquals(numTest, num);
    }

    @Test
    public void VectorSortTest() throws Exception {
        List<String> vector = new ArrayList<>();
        vector.add("jingle");
        vector.add("bells");
        vector.add("xmas");

        List<String> vectorTest = new ArrayList<>();
        vectorTest.add("bells");
        vectorTest.add("jingle");
        vectorTest.add("xmas");

        Sort.quickSort(vector);
        Assert.assertEquals(vectorTest,vector);
    }

    @Test
    public void VectorComparatorSortTest() throws Exception {
        List<Car> vector = new ArrayList<>();
        vector.add(new Car("Acura", 350));
        vector.add(new Car("Toyota", 300));
        vector.add(new Car("Lexus", 380));

        List<Car> vectorTest = new ArrayList<>();
        vectorTest.add(new Car("Toyota", 300));
        vectorTest.add(new Car("Acura", 350));
        vectorTest.add(new Car("Lexus", 380));

        Sort.quickSort(vector, new CarMaxSpeedComparator());
        Assert.assertEquals(vectorTest,vector);
    }
}
