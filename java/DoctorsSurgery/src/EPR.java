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
	public void addPatient(Patient thePatient) throws HealthException {
		
		if (thePatient.getName().equalsIgnoreCase("")) {
			throw new HealthException("Must enter a name!");
		}
		
		if (thePatient.getNhsNumber().equalsIgnoreCase("")) {
			throw new HealthException("NHS Number missing.");
		}
		
    	thePatients.add(thePatient);
    }
	
	/**
	 * get a Patient searching by NHS number. Returns null if not found
	 * 
	 * @return Patient 
	 * @param nhsNumber 
	 */
	public Patient getPatient(String nhsNumber) throws HealthException {
        
        Iterator<Patient> iter = thePatients.iterator();
    	Patient tempPatient;
    	
        while (iter.hasNext()) {
        	tempPatient = iter.next();
            if (tempPatient.getNhsNumber().equals(nhsNumber)) {
                return tempPatient;
            }
        }
        
        // If still here then throw exception.
        throw new HealthException("Patient not found.");
    }

    public Iterator<Patient> iterator() {
        return thePatients.iterator();
    }
}

