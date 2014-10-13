import java.util.*;
import java.io.*;      // required for Serialising objects

/**
 * The list of appointments
 */
public class AppointmentList implements Serializable {
	private ArrayList<Appointment> collection;

	/**
	 * Creates the list to a predefined size
	 *
	 */
	public AppointmentList() {
		collection = new ArrayList<Appointment>();
	}

	/**
	 * Adds an appointment to the list - done by the Doctor or Nurse owning the list
	 *
	 * @param theApp
	 */
	public void addFreeAppointment(Appointment newAppmt) throws HealthException {
		
		Iterator<Appointment> iter = collection.iterator();
		Appointment tempAppmt;
		
		while (iter.hasNext()) {
			tempAppmt = iter.next();
			
			// Use the compare function (0 means they are both the same).
			if (tempAppmt.getDate().compareTo(newAppmt.getDate()) == 0) {
				if (tempAppmt.getTime() == newAppmt.getTime()) {
					throw new HealthException("Slot already taken.");
				}
			}
		}
		
		// If no exception thrown, we will arrive here.
        collection.add(newAppmt);
    }

	/**
	 * Allows user to iterate over the list
	 *
	 * @return Iterator<Appointment>
	 */
	public Iterator<Appointment> iterator() {
       return collection.iterator();
    }

}
