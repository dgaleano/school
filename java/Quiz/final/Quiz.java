import java.io.*;
import java.util.*;

/**
 * Quiz Class: The main class of the quiz
 * Author: John Tyler
 * Last Edited: 06.05.05
 */

public class Quiz implements Iterable
{
	/**
	 * Creates an arrayList of type Question called questionList
	 */
	private ArrayList<Category> itemList = new ArrayList<Category>();
	
	public Quiz() {
		//... Backwards compatability.
	}
	
	public Quiz(String theValue) {
		//... Backwards compatability.
	}

	/**
	 * Method to update category
	 */
	public void updateItem(Category theItem, Category newItem) {
        
    	Category thisItem;
        
        for(int i = 0; i < itemList.size(); i++) {
        	
        	thisItem = itemList.get(i);
        	if(theItem == thisItem) {
        		itemList.set(i, newItem);
        	}
        }
    }
	
	/**
	 * Generates a random number
	 */
	 public int randomNumber(int num) {
	 	Random generator = new Random();
	 	int randomNum = generator.nextInt(num);
	 	return randomNum;
	 }
	
	/*
	 *Adds a question to the collection of questions
	 */
	public void addItem(Category theItem) {
        itemList.add(theItem);
    }
    
    /*
     *Removes an item which matches the object.
     */
	public void removeItem(Category theItem) {
    	itemList.remove(theItem);
    }
    
    /**
     * Allows a user to iterate over the questions
	 *
	 * @return Iterator<Question>
	 */
    public Iterator<Category> iterator() {
        return itemList.iterator();
    }
}