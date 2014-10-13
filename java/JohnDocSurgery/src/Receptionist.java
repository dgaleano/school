import java.io.*;      // required for Serialising objects

/**
 * Represents the concrete class Receptionist
 */
public class Receptionist extends Person implements Serializable {
	
	/**
	 * Constructor requires the name
	 * 
	 * @param name 
	 */
	public Receptionist(String name) {
		super(name);
	}

}

