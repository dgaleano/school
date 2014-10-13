import java.io.*;      // required for Serialising objects

/**
 * Models Time for appointments
 */
public class Time implements Serializable {

	private int hours;
	private int mins;
	
	public Time(int theHours, int theMins) {
		hours = theHours;
		mins = theMins;
	}
	
    /*
	 * Sets the attribute: hour
	 * 
	 * @param theDay the day of the month
	 */
	public void setHours(int theHours) {
        hours = theHours;
    }

	
	/**
	 * Gets the attribute: hour
	 * 
	 * @return int the hours
	 */
	public int getHours() {
        return hours;
    }

	
	/**
	 * Sets the attribute: mins
	 * 
	 * @param theMins the mins
	 */
	public void setMins(int theMins) {
        mins = theMins;
    }

	
	/**
	 * Gets the attribute: month
	 * 
	 * @return int the month
	 */
	public int getMins() {
        return mins;
    }

	
	public String toString()
    {
        String temp = "";
        temp = hours + ":" + mins + "\n";
        return temp;
    }
   }


