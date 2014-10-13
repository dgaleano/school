import java.util.*;

/**
 * Category Class: Used to create Categorys for questions objects, also contains
 *				   an array of questions
 * Author: John Tyler
 * Last Edited: 06.05.05
 */

public abstract class GenericList extends GenericItem implements Iterable
{
	
	private ArrayList<GenericItem> itemsList;
	
	/**
	 * Method to update category
	 */
	 public void updateItem(GenericItem theItem, GenericItem newItem) {
        
        GenericItem thisItem;
        for (int i = 0; i<itemsList.size(); i++) {
        	
        	thisItem = itemsList.get(i);
        	if(theItem == thisItem) {
        		this.itemsList.set(i, newItem);
        	}
        }
    }

	/*
	 *Adds a question to the collection of questions
	 */
	public void addItem(GenericItem theItem) {
        this.itemsList.add(theItem);
    }

	public void removeItem() {
		//...
	}
    
    /**
     * Allows a user to iterate over the questions
	 *
	 * @return Iterator<Question>
	 */
    public Iterator<GenericItem> iterator() {
        return itemsList.iterator();
    }
}