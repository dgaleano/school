import java.io.*;      // required for Serialising objects

/**
 * To represent the concrete class Patient
 */
public class Patient extends Person implements Serializable {
	
	private Date dateOfBirth;
	private String nhsNumber;
	private HealthProblemHistory history;
	private Appointment nextAppointment;
	
	/**
	 * This constructor takes the NHS number
	 * 
	 * @param theName The name of the Patient
	 * @param nhsNumber The NHS Number of the Patient
	 * @param dateOfBirth The date of birth of the Patient
	 */
	public Patient(String theName, String nhsNumber, Date dateOfBirth) {
		super(theName);
		setNhsNumber(nhsNumber);
		setDateOfBirth(dateOfBirth);
		history = new HealthProblemHistory();
	}
	
	/**
	 * Sets the attribute: dateOfBirth
	 *
	 * @param theDateOfBirth All patients require a date of birth
	 */
	public void setDateOfBirth(Date theDateOfBirth) {
        dateOfBirth = theDateOfBirth;
    }


	/**
	 * Gets the attribute: dateOfBirth
	 *
	 * @return Date All patients require a date of birth
	 */
	public Date getDateOfBirth() {
        return dateOfBirth;
    }

	
	/**
	 * Sets the attribute: nhsNumber
	 * 
	 * @param theNhsNumber The NHS number for this patient
	 */
	public void setNhsNumber(String theNhsNumber) {
        nhsNumber = theNhsNumber;
    }

	
	/**
	 * Gets the attribute: nhsNumber
	 * 
	 * @return String The NHS number for this patient
	 */
	public String getNhsNumber() {
        return nhsNumber;
    }

	
	/**
	 * Sets the attribute: nextAppointment
	 * 
	 * @param theNextAppointment This represents the next appointment for this patient (null if not set)
	 */
	public void setNextAppointment(Appointment theNextAppointment) {
        nextAppointment = theNextAppointment;
    }

	
	/**
	 * Gets the attribute: nextAppointment
	 * 
	 * @return Appointment This represents the next appointment for this patient (null if not set)
	 */
	public Appointment getNextAppointment() {
        return nextAppointment;
    }

	/**
	 * Sets the attribute: history
	 *
	 * @param HealthProblemHistory the history associated with patient
	 */
	public void setHistory(HealthProblemHistory theHistory) {
        history = theHistory;
    }


	/**
	 * Gets the attribute: history
	 *
	 * @return HealthProblemHistory - the history associated with patient
	 */
	public HealthProblemHistory getHistory() {
        return history;
    }
    
    /**
     * For testing and such.
     */
    public String toString() {
    	return getName() + " (" + nhsNumber + ", " + dateOfBirth + ")";
    }

}

