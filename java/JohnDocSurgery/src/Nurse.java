import java.io.*;      // required for Serialising objects
/**
 * Represents the concrete class Nurse
 */
public class Nurse extends BookablePerson implements Serializable {
	public static final int APPOINTMENT_LENGTH_NURSE = 15;
	
	/**
	 * Constructor requires the name
	 * 
	 * @param name The name of the nurse
	 */
	public Nurse(String name) {
		super(name, APPOINTMENT_LENGTH_NURSE);
	}

}

