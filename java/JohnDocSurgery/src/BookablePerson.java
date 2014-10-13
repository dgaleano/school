import java.util.*;
import java.io.*;      // required for Serialising objects

/**
 * This is a Person who has an assigned AppointmentList
 */
public class BookablePerson extends Person 
                            implements Serializable, Iterable<Appointment> {
	private AppointmentList appointments;
	private int appointmentLength = 0;

	/**
	 * Constructor requires the name
	 *
	 * @param name reqAppointmentLength name
	 * @param reqAppointmentLength name
	 */
	public BookablePerson(String name, int reqAppointmentLength) {
		super(name);
		appointmentLength = reqAppointmentLength;
        appointments = new AppointmentList();
	}

	public int getAppointmentLength() {
		return appointmentLength;
	}
	
	/**
	 * Returns a new AppointmentList containing links to the next free appointment for this BookablePerson
	 *
	 * @return AppointmentList
	 */
	public AppointmentList getNextFreeAppointments() {
	    return appointments.getNextFreeAppointments(4);
	}

	/**
	 * Allows a user to add a new appointment to their list - probably checks that the times don't overlap
	 *
	 * @return boolean
	 * @param theApp
	 */
	public void addFreeAppointment(Appointment theApp) {
        appointments.addFreeAppointment(theApp);
	}

	/**
	 * Provides user with iterator over Appointments
     * NOTE THIS IS FOR ITERATING OVER THE FULL SET OF APPOINTMENTS FOR A PERSON
     * USE THE AppointmentList iterator OVER THE RESULTS OF getNextFreeAppointments
     * FOR ITERATING OVER FREE APPOINTMENT
	 *
	 * @return Iterator<Appointment>
	 */
	public Iterator<Appointment> iterator() {
	    return appointments.iterator();
	}

}