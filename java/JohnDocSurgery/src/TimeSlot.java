public class TimeSlot {
	
	String name;
	Time time;
		
	public TimeSlot(String theName, Time theTime) {
		
	name = theName;
	time = theTime;
		
	}
	
	/**
	 * Sets the attribute: name
	 * 
	 * @param theName the name
	 */
	public void setName(String theName) {
		name = theName;
    }

	
	/**
	 * Gets the attribute: name
	 * 
	 * @return string the name
	 */
	public String getName() {
        return name;
    }

	
	/**
	 * Sets the attribute: time
	 * 
	 * @param theTime the time
	 */
	public void setTime(Time theTime) {
        time = theTime;
    }

	
	/**
	 * Gets the attribute: time
	 * 
	 * @return int the time
	 */
	public Time getTime() {
        return time;
    }


}