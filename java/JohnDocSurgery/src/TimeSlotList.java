import java.util.*;
import java.io.*;

public class TimeSlotList implements Iterable, Serializable {
	
	private ArrayList<TimeSlot> timeslots;
	
	public TimeSlotList () {
        timeslots = new ArrayList<TimeSlot>();
        
    }
    
    public TimeSlot getTimeSlot(String theName) {
		Iterator<TimeSlot> iter = timeslots.iterator();
		TimeSlot temp;
		
		while (iter.hasNext()) {
			temp = iter.next();
			if (temp.getName().equalsIgnoreCase(theName)) {
				return temp;
			}
		}
		
		return null;
	}
	
	/**
	 * Allows user to add a TimeSlot to the timeslot collection
	 *
	 * @param theTimeSlot
	 */
		
	public void addTimeSlot(TimeSlot theTimeSlot) {
        timeslots.add(theTimeSlot);
    }

	/**
	 * Allows a user to iterate over the employees
	 *
	 * @return Iterator<TimeSlot>
	 */
	public Iterator<TimeSlot> iterator() {
        return timeslots.iterator();
    }

}