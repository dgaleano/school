import java.util.*;
import java.io.*;      // required for Serialising objects

/**
 * The Electronic Patient Record
 */
public class EPR implements Iterable, Serializable{
	private ArrayList<Patient> thePatients;
	
	/**
	 * The constructor creates the collection to the required size
	 *
	 */
	public EPR() {
		thePatients = new ArrayList<Patient>();
	}
	
	/**
	 * Allows a new patient to be added to the EPR system
	 * 
	 * @param thePatient
	 */
	public void addPatient(Patient thePatient) {
        thePatients.add(thePatient);
    }
	
	/**
	 * get a Patient searching by NHS number. Returns null if not found
	 * 
	 * @return Patient 
	 * @param nhsNumber 
	 */
	public Patient getPatient(String nhsNumber) {
        Iterator<Patient> iter = thePatients.iterator();
        // iterate through patients
        Patient tempPatient = null;
        while (iter.hasNext()) {
            tempPatient = iter.next();
            // call getNhsNumber
            // when the number equals the nhsNumber return current patient
            if (tempPatient.getNhsNumber().equalsIgnoreCase(nhsNumber)) {
                return tempPatient;
            }
        }
        return null;
    }
	
	/**
	 * Search for a Patient given name and dateOfBirth. Returns null if not found
	 * 
	 * @return Patient 
	 * @param name 
	 * @param dateOfBirth 
	 */
	public Patient getPatient(String name, Date dateOfBirth) {
	    return null; // needs to be completed
	}

    public Iterator<Patient> iterator() {
        return thePatients.iterator();
    }

}

