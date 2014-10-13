import java.util.*;
import java.io.*;      // required for Serialising objects

/**
 * This is a Person who has an assigned AppointmentList
 */
public class BookablePerson extends Person 
                            implements Serializable, Iterable<Appointment> {
	
	private AppointmentList appointments;
	private int appointmentLength = 0;
	private int surgeryOpens = 9;
	private int surgeryCloses = 17;

	/**
	 * Constructor requires the name.
	 *
	 * @param name Person's name.
	 * @param reqAppointmentLength Max length of appointment.
	 * @param bookable Are they bookable?
	 */
	public BookablePerson(String name, int reqAppointmentLength, boolean bookable) {
		
		super(name);
		appointmentLength = reqAppointmentLength;
        appointments = new AppointmentList();
        setBookable(bookable);
        
        // Does the input return an uneven number?
        if ((60 % appointmentLength) > 0) {
        	System.out.println(
        		"Bookable person appointment time not "
        		+ "divisable by 60, sorry can't continue."
        	);
        	
        	// Don't go any further.
			return;
        }
        
        generateValidSlots(appointmentLength);
	}
	
	/**
	 * Generate a list of valid slots to choose from.
	 * @param appointmentLength length of appointments.
	 */
	public void generateValidSlots(int appointmentLength) {
		
		int slotsPerHour = 60 / appointmentLength;
        int thisHour;
        int thisMins;
        int slotNumber = 0;
        String slotLabel;
        String hourText;
        String minsText;
        String nextHourText;
        String nextMinsText;
        ValidSlot theSlot;
        Time slotTime;
        
        for (int hour = surgeryOpens; hour < surgeryCloses; hour++) {
        	for (int slot = 0; slot < slotsPerHour; slot++) {
        	
        		thisMins = slot * appointmentLength;
        		theSlot = new ValidSlot();
        		slotTime = new Time(hour, thisMins);
        		
        		hourText = Integer.toString(hour);
        		minsText = Integer.toString(thisMins);
        		nextHourText = Integer.toString(hour);
        		nextMinsText = Integer.toString(thisMins + appointmentLength);
        		
        		// Apply time format to hours.
        		if (hour < 10) {
        			hourText = "0" + hourText;
	        		nextHourText = "0" + nextHourText;
        		}
        		
        		// Apply time format to minutes.
        		if (thisMins < 10) {
        			minsText = "0" + minsText;
        			
        		} else if (thisMins == 0) {
        			minsText = "00";
        		}
        		
        		// For last slot, move next hour on one.
        		if ((slot + 1) == slotsPerHour) {
        			nextHourText = Integer.toString(hour + 1);
        			
        			if ((hour + 1) < 10) {
	        			nextHourText = "0" + nextHourText;
	        		}
        			
        			nextMinsText = "00";
        		}
        		
        		slotLabel = hourText + ":" + minsText + " - ";
        		slotLabel += nextHourText + ":" + nextMinsText;
        		
        		theSlot.setTime(slotTime);
        		theSlot.setLabel(slotLabel);
        		addValidSlot(theSlot);
        	}
        }
	}

	/**
	 * Allows a user to add a new appointment to their list - probably checks
	 * that the times don't overlap
	 *
	 * @return boolean
	 * @param theApp
	 */
	public void addFreeAppointment(Appointment theApp) throws HealthException {
        appointments.addFreeAppointment(theApp);
	}

	/**
	 * Provides user with iterator over Appointments
     * NOTE THIS IS FOR ITERATING OVER THE FULL SET OF APPOINTMENTS FOR A
     * PERSON USE THE AppointmentList iterator OVER THE RESULTS OF
     * getNextFreeAppointments FOR ITERATING OVER FREE APPOINTMENT
	 *
	 * @return Iterator<Appointment>
	 */
	public Iterator<Appointment> iterator() {
	    return appointments.iterator();
	}
}