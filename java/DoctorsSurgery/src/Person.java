import java.io.*;
import java.util.*;

/**
 * The generic Person class
 */
public abstract class Person implements Serializable {
	
	private String name;
	private boolean bookable;
	private ValidSlotList validSlots = new ValidSlotList();

	/**
	 * All Person objects must have a name.
	 *
	 * @param theName The name of the Person
	 */
	public Person(String theName) {
        setName(theName);
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
	
	/**
	 * Sets the person to bookable (i.e. doctor or nurse).
	 *
	 * @param isBookable Is this person bookable?
	 */
	public void setBookable(boolean isBookable) {
        bookable = isBookable;
    }
	
	/**
	 * Find out if the person is bookable or not.
	 *
	 * @return boolean Is this person bookable?
	 */
	public boolean getBookable() {
        return bookable;
    }
	
	/**
	 * Return collection of valid slots.
	 * Only used for Bookable Persons.
	 *
	 * @return ValidSlotList
	 */
	public ValidSlotList getValidSlots() {
		return validSlots;
	}
	
	/**
	 * Adds a valid slot.
	 *
	 * @param theSlot
	 */
	public void addValidSlot(ValidSlot theSlot) {
		validSlots.addSlot(theSlot);
	}
	
	/**
	 * Argh, I hate to do this, so crude... But I don't know how to work with
	 * objects in combo boxes properly. Oh well, at least it sorta works.
	 */
	public ValidSlot getSlotByLabel(String theLabel) {
		
		Iterator<ValidSlot> iter = validSlots.iterator();
		ValidSlot theSlot;
		
		while(iter.hasNext()) {
			theSlot = iter.next();
			if (theSlot.getLabel().equals(theLabel)) {
				return theSlot;
			}
		}
		
		return null;
	}
	
	/**
	 * Prints info to string.
	 */
	public String toString() {
		return getName();
	}
}

