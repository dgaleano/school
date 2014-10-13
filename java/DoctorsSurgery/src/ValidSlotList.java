import java.util.*;
import java.io.*;

/**
 * The list of slots we can select from.
 */
public class ValidSlotList implements Serializable {
	
	private ArrayList<ValidSlot> collection = new ArrayList<ValidSlot>();
	
	/**
	 * Adds a slot to the collection.
	 *
	 * @param theSlot
	 */
	public void addSlot(ValidSlot theSlot){
		collection.add(theSlot);
    }

	/**
	 * Allows user to iterate over the list
	 *
	 * @return Iterator<ValidSlot>
	 */
	public Iterator<ValidSlot> iterator() {
    	return collection.iterator();
    }
}
