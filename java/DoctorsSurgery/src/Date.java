import java.io.*;      // required for Serialising objects
/*import java.text.SimpleDateFormat; 
import java.util.Date; */

/**
 * Models Date for Appointment
 */
public class Date implements Comparable<Date>, Serializable {
	private int day;
	private int month;
	private int year;
	
	public Date(int theDay, int theMonth, int theYear) throws HealthException {
		setDay(theDay);
		setMonth(theMonth);
		setYear(theYear);
	}
	
	/**
	 * Sets the attribute: day
	 * 
	 * @param theDay the day of the month
	 */
	public void setDay(int theDay) throws HealthException {
        
        if ((theDay < 1) || (theDay > 31)) {
        	throw new HealthException("Day dosen't seem valid.");
        }
        
        day = theDay;
    }

	
	/**
	 * Gets the attribute: day
	 * 
	 * @return int the day of the month
	 */
	public int getDay() {
        return day;
    }

	
	/**
	 * Sets the attribute: month
	 * 
	 * @param theMonth the month
	 */
	public void setMonth(int theMonth) throws HealthException {
        
        if ((theMonth < 1) || (theMonth > 12)) {
        	throw new HealthException("Month dosen't seem valid.");
        }
        
        month = theMonth;
    }

	
	/**
	 * Gets the attribute: month
	 * 
	 * @return int the month
	 */
	public int getMonth() {
        return month;
    }

	
	/**
	 * Sets the attribute: year
	 * 
	 * @param theYear the year
	 */
	public void setYear(int theYear) throws HealthException {
        
        // Not year 10000 compliant?
        if ((theYear < 999) || (theYear > 9999)) {
        	throw new HealthException("Year must be in format YYYY.");
        }
        
        year = theYear;
    }

	
	/**
	 * Gets the attribute: year
	 * 
	 * @return int the year
	 */
	public int getYear() {
        return year;
    }

    public int compareTo(Date theOther) {
       // write some code which
       // returns 0 if they are equal
       // -1 if theOther is less than this
       // +1 if theOther is greater than this
       
       if (this.year > theOther.year)
           return -1;
           
       if (this.year < theOther.year)
           return 1;
           
       // At this point the years are equal
       if (this.month > theOther.month)
           return -1;
           
       if (this.month < theOther.month)
           return 1;
           
       // At this point the months are equal
       if (this.day > theOther.day)
           return -1;
           
       if (this.day < theOther.day)
           return 1;
       
       return 0;
    }
    
    public String toString() {
    	
    	String theDay = Integer.toString(day);
    	String theMonth = Integer.toString(month);
    	String theYear = Integer.toString(year);
    	
    	// Apply time format to hours.
		if (day < 10) {
			theDay = "0" + theDay;
		}
		
		// Apply time format to minutes.
		if (month < 10) {
			theMonth = "0" + theMonth;
		}
		
    	return theDay + "/" + theMonth + "/" + theYear;
    }

}