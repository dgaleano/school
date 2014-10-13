import java.io.*;      // required for Serialising objects
/**
 * Models Date for Appointment
 */
public class Date implements Comparable<Date>, Serializable {
	private int day;
	private int month;
	private int year;
	
	public Date(int theDay, int theMonth, int theYear) {
		day = theDay;
		month = theMonth;
		year = theYear;
	}
	
	/**
	 * Sets the attribute: day
	 * 
	 * @param theDay the day of the month
	 */
	public void setDay(int theDay) {
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
	public void setMonth(int theMonth) {
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
	public void setYear(int theYear) {
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

    public String toString()
    {
        String temp = "";
        temp = day + "/" + month + "/" + year + "\n";
        return temp;
    }
   
    public int compareTo(Date theOther) {
       // write some code which
       // returns 0 if they are equal
       // -1 if theOther is less than this
       // +1 if theOther is greater than this
/*
       if (this.year > theOther.year)
           return -1;
       if (this.year < theOther.year)
           return 1;
       // at this point the years are equal
       if (this.month > theOther.month)
           return -1;
       if (this.month < theOther.month)
           return 1;
       // at this point the months are equal
       if (this.day > theOther.day)
           return -1;
       if (this.day < theOther.day)
           return 1;
       return 0;
*/
       // alternatively
       // We use 385 as this is greater than 32 * 12
       Integer thisDate = new Integer(this.year * 385 +
                                      this.month *  32 +
                                      this.day);
       // We use 32 as this is greater than num of days
       Integer otherDate = new Integer(theOther.year * 385 +
                                      theOther.month *  32 +
                                      theOther.day);
       return thisDate.compareTo(otherDate);
    }

}

