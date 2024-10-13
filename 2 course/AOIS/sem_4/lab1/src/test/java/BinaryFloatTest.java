import org.example.Models.BinaryFloat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryFloatTest {
    @Test
    public void binaryFloatSum() {
        BinaryFloat binaryFloat1 = new BinaryFloat(3.3F);
        BinaryFloat binaryFloat2 = new BinaryFloat(6.2F);

        Assertions.assertEquals(9.499999F, BinaryFloat.sum(binaryFloat1, binaryFloat2).toFloat());
    }
}
