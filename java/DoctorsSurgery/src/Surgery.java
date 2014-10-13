import java.io.*;      // required for Serialising objects
import java.util.*;

/**
 * The top level class
 */
public class Surgery implements Serializable {
	
	private EPR patientRecords;
	private Employees staff;

    public Surgery() {
        patientRecords = new EPR();
        staff = new Employees();
    }

	/**
	 * Gets EPR - returns the patient records
	 *
	 * @return EPR
	 */
	public EPR getEPR() {
        return patientRecords;
    }

	/**
	 * Gets Employees - - returns the patient records
	 *
	 * @return Employees
	 */
	public Employees getEmployees() {
        return staff;
    }
    
	/**
	 * Allows the complete system to be read from an external file
	 *
	 * @param theFileName
	 */
	public static Surgery readFromFile(String theFileName) throws HealthException {
	    
	    Surgery tempSurgery;
        try {
            // Lets get a handle on the file to read in
            File myFile = new File (theFileName);

            if ((!myFile.isFile()) || (!myFile.canRead()))
            {
                // Let's leave
                throw new HealthException("Data file path invalid!");
            }

            ObjectInputStream myStream =
                new ObjectInputStream(
                    new BufferedInputStream(
                    new FileInputStream(myFile)));

            System.out.println("Reading data file...");

            // Read the object from file - it needs to be cast
            tempSurgery = (Surgery) myStream.readObject();
            myStream.close();
            
        } catch (ClassNotFoundException e) {
            throw new HealthException("ReadFromFile error: Objects of incorrect class.");
            
        } catch (InvalidClassException e) {
            throw new HealthException("ReadFromFile error Class definition changed.");
            
        } catch (Exception e) {
            throw new HealthException("ReadFromFile error Couldn't read data.");
        }
        
        return tempSurgery;
	}

	/**
	 * Allows storage of the system to file
	 *
	 * @param theFileName
	 */
	public void writeToFile(String theFileName) throws HealthException {
        
        try {
            // First, get a handle on the file.
            File myFile = new File (theFileName);

            ObjectOutputStream myStream =
                new ObjectOutputStream(
                    new BufferedOutputStream(
                    new FileOutputStream(myFile)));

            System.out.println("Writing to file...");

            myStream.writeObject(this);
            myStream.close();
            
        } catch (Exception e) {
           throw new HealthException("Failed to write to file!");
        }
	}

    /**
     * Startup loads from file or creates a new surgery
     */
    public void startUp(String theFileName){
        Surgery practice = null;

        // Try to read existing surgery.
        try {
        	
            practice = Surgery.readFromFile(theFileName);
            this.patientRecords = practice.getEPR();
            this.staff = practice.getEmployees();
            System.out.println("Read succesfull!");
        
        } catch (HealthException e) {
            // Failed, let's create a new one.
            System.out.println("Making new data file.");
            practice = new Surgery();
        }
    }
    
    /**
     * shutDown   is used to save everything to file
     */
    public void shutDown(String theFileName) {
        
        try {
            this.writeToFile(theFileName);
            System.out.println("Save succesfull!");
            
        } catch (HealthException e) {
            System.out.println("Save failed!");
        }
    }
    
	/**
	 * For diagnostic purposes. Consider removing when program is final.
	 */
	public void printEPR() {
		Iterator<Patient> iter = getEPR().iterator();
		Patient temp;
		System.out.println("\nPrinting EPR...");
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	
	/**
	 * For diagnostic purposes. Consider removing when program is final.
	 */
	public void printEmployees() {
		Iterator<Person> iter = getEmployees().iterator();
		Person temp;
		System.out.println("\nPrinting Employees...");
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}