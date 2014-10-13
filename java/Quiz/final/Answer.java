/**
 * Answer Class: Used to create answer objects
 * Author: John Tyler
 * Last Edited: 06.05.05
 */

public class Answer
{
	private String value;
	private boolean correct;
		
	public Answer(String theValue, boolean isCorrect) {
		this.setValue(theValue);
		this.setCorrect(isCorrect);
	}
	
	/**
	 * Constructor requires the name
	 * 
	 * @param name The name of the category
	 */
	public Answer(String theValue) {
		this.setValue(theValue);
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
	 * Sets the attribute: correct
	 *
	 * @param isCorrect Sets wether the answer is correct or not
	 */
	public void setCorrect(boolean isCorrect) {
        correct = isCorrect;
    }

	/**
	 * Gets the attribute: correct
	 *
	 * @return correct Sees wether the answer is correct or not
	 */
	public boolean getCorrect() {
        return correct;
    }
    
    /**
	 * toString method to print out the answers also 
	 * puts an asterix next to answer if correct
	 */
    public String toString() {
    	String theOutput = getValue();
      
        if (correct){
        	theOutput +=" (*)";
        }
        return theOutput;
    }
}    