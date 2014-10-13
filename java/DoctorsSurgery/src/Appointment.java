import java.io.*;      // required for Serialising objects
/**
 * Represents a single appointment
 */
public class Appointment extends ValidSlot implements Serializable {
	
	private Date date;
	private Time time;
	private Patient patient;

	/**
	 * Sets the attribute: patient
	 *
	 * @param thePatient The patient assigned to this Appointment - null if vacant.
	 */
	public void setPatient(Patient thePatient) {
        patient = thePatient;
    }

	/**
	 * Gets the attribute: patient
	 *
	 * @return Patient The patient assigned to this Appointment - null if vacant.
	 */
	public Patient getPatient() {
        return patient;
    }

	/**
	 * Sets the attribute: date
	 *
	 * @param theDate Date of the Appointment
	 */
	public void setDate(Date theDate) {
        date = theDate;
    }

	/**
	 * Gets the attribute: date
	 *
	 * @return Date Date of the Appointment
	 */
	public Date getDate() {
        return date;
    }

	/**
	 * Sets the attribute: time
	 *
	 * @param theTime Time of the Appointment
	 */
	public void setTime(Time theTime) {
        time = theTime;
    }

	/**
	 * Gets the attribute: time
	 *
	 * @return Time Time of the Appointment
	 */
	public Time getTime() {
        return time;
    }

    /**
     * For testing and such.
     */
	public String toString() {
		
		String out;
		out = "Patient: " + getPatient() + ", ";
		out += "Date: " + getDate() + ", ";
		out += "Time: " + getTime();
		return out;
	}

}

