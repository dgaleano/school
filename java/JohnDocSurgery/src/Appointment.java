import java.io.*;      // required for Serialising objects
/**
 * Represents a single appointment
 */
public class Appointment implements Serializable {
	private Time time;
	private Date date;
	private Patient patient;

	
	public Appointment(Time theTime, Date theDate, Patient thePatient) {
		time = theTime;
		date = theDate;
		patient = thePatient;
	}
	
	
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
	 * Sets the attribute: time
	 *
	 * @param theTime Time of the appointment
	 */
	public void setTime(Time theTime) {
        time = theTime;
    }


	/**
	 * Gets the attribute: time
	 *
	 * @return Time Time of the appointment
	 */
	public Time getTime() {
        return time;
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

	public String toString(){
		String output = date + " " + time + " " + patient.getName();
		return output;
	}
}

