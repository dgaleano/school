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
	
	/*
	 *Adds a question to the collection of questions
	 */
	public void addItem(Answer theItem) {
        itemList.add(theItem);
    }
    
    /*
     *Removes an item which matches the object.
     */
	public void removeItem(Answer theItem) {
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
    public Iterator<Answer> iterator() {
        return itemList.iterator();
    }
}