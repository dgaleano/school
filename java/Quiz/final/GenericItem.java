import java.util.*;

/**
 * Category Class: Used to create Categorys for questions objects, also contains
 *				   an array of questions
 * Author: John Tyler
 * Last Edited: 06.05.05
 */

public abstract class GenericItem
{
	
	private String name;
    
    /**
	 * Sets the attribute: name
	 *
	 * @param theName The name of the Category
	 */
	public void setName(String theName) {
        this.name = theName;
    }


	/**
	 * Gets the attribute: name
	 *
	 * @return name The name of the Category
	 */
	public String getName() {
        return this.name;
    }
    
    
    /**
	 * toString method to print out the questions
	 */
    public String toString() {
        return this.getName();
    }
}