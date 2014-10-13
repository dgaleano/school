import java.io.*;
import java.util.*;

/**
 * Question Class: Used to create question objects, also contains an array of
 *				   answers
 * Author: John Tyler
 * Last Edited: 06.05.05
 */

public class Question implements Iterable
{
	private String value;
	
	/**
	 * Creates an arrayList of type Question called questionList
	 */
	private ArrayList<Answer> itemList = new ArrayList<Answer>();
		
	/**
	 * Constructor requires the name
	 * 
	 * @param name The name of the category
	 */
	public Question(String theValue) {
		this.setValue(theValue);
	}	

	/**
	 * Method to update category
	 *
	 * @param theItem; the item that will be changed
	 *
	 * @param newItem; the item that will be replacing original item
	 */
	public void updateItem(Answer theItem, Answer newItem) {
        
    	Answer thisItem;
        
        for(int i = 0; i < itemList.size(); i++) {
        	
        	thisItem = itemList.get(i);
        	if(theItem == thisItem) {
        		itemList.set(i, newItem);
        	}
        }
    }
	
	/**
	 * Adds a question to the collection of questions
	 *
	 * @param theItem; item that will be added to the collection
	 */
	public void addItem(Answer theItem) {
        itemList.add(theItem);
    }
    
    /**
     * Removes an item which matches the object.
     *
     * @param theItem; the item that will be removed from the collection
     */
	public void removeItem(Answer theItem) {
    	itemList.remove(theItem);
    }
    
    /**
	 * Sets the attribute: Value
	 *
	 * @param theValue; The value of the object
	 */
	public void setValue(String theValue) {
        this.value = theValue;
    }
    
	/**
	 * Gets the attribute: value
	 *
	 * @return name The value of the object
	 */
	public String getValue() {
        return this.value;
    }
    
    /**
	 * toString method to print out the questions
	 * 
	 * @return theValue
	 */
    public String toString() {
        return this.getValue();
    }
    
    /**
     * Allows a user to iterate over the answers
	 *
	 * @return Iterator<Answer>
	 */
    public Iterator<Answer> iterator() {
        return itemList.iterator();
    }
}