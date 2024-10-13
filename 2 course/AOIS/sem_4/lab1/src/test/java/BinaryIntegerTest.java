import org.example.Models.BinaryInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryIntegerTest {
    @Test
    public void binaryIntegerSum() {
        BinaryInteger binaryInteger1 = new BinaryInteger(11);
        BinaryInteger binaryInteger2 = new BinaryInteger(19);

        Assertions.assertEquals(30, BinaryInteger.sum(binaryInteger1, binaryInteger2).toDecimal());
    }

    @Test
    public void binaryIntegerDifference() {
        BinaryInteger binaryInteger1 = new BinaryInteger(11);
        BinaryInteger binaryInteger2 = new BinaryInteger(19);

        Assertions.assertEquals(-8, BinaryInteger.difference(binaryInteger1, binaryInteger2).toDecimal());
    }

    @Test
    public void binaryIntegerMultiply() {
        BinaryInteger binaryInteger1 = new BinaryInteger(11);
        BinaryInteger binaryInteger2 = new BinaryInteger(19);

        Assertions.assertEquals(208, BinaryInteger.multiply(binaryInteger1, binaryInteger2).toDecimal());
    }

    @Test
    public void binaryIntegerDivision() {
        BinaryInteger binaryInteger1 = new BinaryInteger(11);
        BinaryInteger binaryInteger2 = new BinaryInteger(19);

        Assertions.assertEquals(0.5625F, BinaryInteger.division(binaryInteger1, binaryInteger2).toFloat());
    }
}
