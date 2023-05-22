package main.java.com.ades;

public class InvalidDataException extends Exception {
	private static final long serialVersionUID = 1L;
    private String dataType; // Type of data that caused the exception
    private String dataValue; // Invalid value that caused the exception

    /**
     * Constructor for InvalidDataException
     *
     * @param message    The message describing the error.
     * @param dataType   The type of data that caused the exception.
     * @param dataValue  The invalid value that caused the exception.
     */
    public InvalidDataException(String message, String dataType, String dataValue) {
        super(message);
        this.dataType = dataType;
        this.dataValue = dataValue;
    }

    /**
     * Returns the type of data that caused the exception.
     *
     * @return The type of data that caused the exception.
     */
    public String getDataType() {
        return this.dataType;
    }

    /**
     * Returns the invalid value that caused the exception.
     *
     * @return The invalid value that caused the exception.
     */
    public String getDataValue() {
        return this.dataValue;
    }

    /**
     * Returns a string representation of this exception, including the message,
     * data type and invalid value.
     *
     * @return A string representation of this exception.
     */
    @Override
    public String toString() {
        return "InvalidDataException: " + super.getMessage() + " Type: " + this.dataType + " Value: " + this.dataValue;
    }
}
