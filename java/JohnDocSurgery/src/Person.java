import java.io.*;
/**
 * The generic Person class
 */
public abstract class Person implements Serializable {
	private String name;
	private boolean bookable;

	/**
	 * All Person objects must have a name.
	 *
	 * @param name
	 */
	public Person(String theName) {
        name = theName;
	}

	/**
	 * Sets the attribute: name
	 *
	 * @param theName The name of the Person
	 */
	public void setName(String theName) {
        name = theName;
    }


	/**
	 * Gets the attribute: name
	 *
	 * @return String The name of the Person
	 */
	public String getName() {
        return name;
    }
	
	public void setBookable(boolean theBookable) {
		bookable = theBookable;
	}

	public boolean getBookable() {
		return bookable;
	}
}

