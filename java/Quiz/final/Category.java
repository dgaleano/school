import java.util.*;

/**
 * Category Class: Used to create Categorys for questions objects, also contains
 *				   an array of questions
 * Author: John Tyler
 * Last Edited: 06.05.05
 */

public class Category implements Iterable
{
	
	private String value;
	
	/**
	 * Creates an arrayList of type Question called questionList
	 */
	private ArrayList<Question> itemList = new ArrayList<Question>();
		
	/**
	 * Constructor requires the name
	 * 
	 * @param name The name of the category
	 */
	public Category(String theValue) {
		this.setValue(theValue);
	}	

	/**
	 * Method to update category
	 */
	public void updateItem(Question theItem, Question newItem) {
        
    	Question thisItem;
        
        for(int i = 0; i < itemList.size(); i++) {
        	
        	thisItem = itemList.get(i);
        	if(theItem == thisItem) {
        		itemList.set(i, newItem);
        	}
        }
    }
	
	/*
	 *Adds a question to the collection of questions
	 */
	public void addItem(Question theItem) {
        itemList.add(theItem);
    }
    
    /*
     *Removes an item which matches the object.
     */
	public void removeItem(Question theItem) {
    	itemList.remove(theItem);
    }
    
    /**
	 * Sets the attribute: name
	 *
	 * @param theName The name of the Category
	 */
	public void setValue(String theValue) {
        this.value = theValue;
    }
    
	/**
	 * Gets the attribute: name
	 *
	 * @return name The name of the Category
	 */
	public String getValue() {
        return this.value;
    }
    
    /**
	 * toString method to print out the questions
	 */
    public String toString() {
        return this.getValue();
    }
    
    /**
     * Allows a user to iterate over the questions
	 *
	 * @return Iterator<Question>
	 */
    public Iterator<Question> iterator() {
        return itemList.iterator();
    }
}