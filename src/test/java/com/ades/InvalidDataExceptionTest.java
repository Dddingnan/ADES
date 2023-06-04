package test.java.com.ades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ades.InvalidDataException;

public class InvalidDataExceptionTest {

    @Test
    public void testGetDataValue_String() {
        InvalidDataException exception = new InvalidDataException("Invalid data", "String", "Invalid value");

        String dataValue = (String) exception.getDataValue();

        Assertions.assertEquals("Invalid value", dataValue);
    }

    @Test
    public void testGetDataValue_Integer() {
        InvalidDataException exception = new InvalidDataException("Invalid data", "Integer", 42);

        Integer dataValue = (Integer) exception.getDataValue();

        Assertions.assertEquals(42, dataValue);
    }

    @Test
    public void testToString() {
        InvalidDataException exception = new InvalidDataException("Invalid data", "String", "Invalid value");

        String toStringResult = exception.toString();

        String expectedString = "InvalidDataException: Invalid data Type: String Value: Invalid value";
        Assertions.assertEquals(expectedString, toStringResult);
    }
}
