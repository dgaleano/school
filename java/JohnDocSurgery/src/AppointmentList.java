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


	public AppointmentList getNextFreeAppointments(int numRequired)  {
        AppointmentList tempAppts = new AppointmentList();
        Iterator<Appointment> iter = collection.iterator();
        Appointment currentAppt;
        while (iter.hasNext()) {
            currentAppt = iter.next();
            if (currentAppt.getPatient() == null) {
                // add to the new list of free appts
                tempAppts.collection.add(currentAppt);
            }
            if (tempAppts.collection.size() == numRequired) {
                return tempAppts;
            }
        }
        return tempAppts; // But it will be incomplete
	}

	
	public void addAppointment(Appointment theApp) {
        collection.add(theApp);
        System.out.println(theApp);	
    }
	
	/**
	 * Adds an appointment to the list - done by the Doctor or Nurse owning the list
	 *
	 * @param theApp
	 */
	public void addFreeAppointment(Appointment theApp){
        // to get extra marks, I realise that we should check for duplicates
        // but this is better in BookablePerson as we know durations
        collection.add(theApp);
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
