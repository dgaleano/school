import java.io.*;      // required for Serialising objects

/**
 * Represents the concrete class Doctor
 */
public class Doctor extends BookablePerson implements Serializable {
	public static final int APPOINTMENT_LENGTH_DOCTOR = 10;
	
	/**
	 * Constructor requires the name
	 * 
	 * @param name The name of the Doctor
	 */
	public Doctor(String name) {
		super(name, APPOINTMENT_LENGTH_DOCTOR);
	}

}

