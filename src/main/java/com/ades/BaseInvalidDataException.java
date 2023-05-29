package main.java.com.ades;

public abstract class BaseInvalidDataException extends Exception {
	private static final long serialVersionUID = 1L;
	private final String dataType; // Type of data that caused the exception

    public BaseInvalidDataException(String message, String dataType) {
        super(message);
        this.dataType = dataType;
    }

    /**
     * Returns the type of data that caused the exception.
     *
     * @return The type of data that caused the exception.
     */
    public String getDataType() {
        return dataType;
    }
}
