import java.io.*;
import java.util.*;
      // required for Serialising objects
/**
 * The top level class
 */
public class Surgery implements Serializable {
	private EPR patientRecords;
	private Employees staff;

    public Surgery()
    {
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
                throw new HealthException("Path is not valid in readFromFile ....");
            }

            ObjectInputStream myStream =
                new ObjectInputStream(
                    new BufferedInputStream(
                    new FileInputStream(myFile)));

            System.out.println("Reading from the file now....");

            // Read the object from file - it needs to be cast

            tempSurgery = (Surgery) myStream.readObject();
            myStream.close();
        }
        catch(ClassNotFoundException e)
        {
            throw new HealthException("ReadFromFile error - Objects of incorrect class");
        }
        catch(InvalidClassException e)
        {
            throw new HealthException("ReadFromFile error - Class definition changed");
        }
        catch (Exception e)
        {
            throw new HealthException("ReadFromFile - could not read data");
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
            // Lets get a handle on the file to write to
            File myFile = new File (theFileName);

            ObjectOutputStream myStream =
                new ObjectOutputStream(
                    new BufferedOutputStream(
                    new FileOutputStream(myFile)));

            System.out.println("Writing to the file now....");

            myStream.writeObject(this);        // Write object to file
            myStream.close();                  // Don't forget this!!
        }
        catch (Exception e) {
           throw new HealthException("WriteToFile failed");
        }
	}

    /**
     * Startup loads from file or creates a new surgery
     */
    public void startUp(String theFileName){
        Surgery practice = null;

        // Let's try to read existing surgery
        try {
            practice = Surgery.readFromFile(theFileName);
            this.patientRecords = practice.getEPR();
            this.staff = practice.getEmployees();
            System.out.println("Existing Surgery found, reading in\n");
        }
        catch (HealthException e) {
            // failed to let's create a new one
            System.out.println("Failed to find existing surgery,"
                               + "so initialising a new one");
            practice = new Surgery();
        }
    }

    /**
     * shutDown   is used to save everything to file
     */
    public void shutDown(String theFileName) {
        try {
            System.out.println("Writing to file");
            this.writeToFile(theFileName);
        }
        catch (HealthException e) {
            // failed to write
            System.out.println("Failed to save existing surgery,"
                               + " a wasted day perhaps!");
        }
    }

	/*
    public static void main (String [] args) {
        Surgery demo = new Surgery();
        demo.startUp("DataFile.epr");
        demo.shutDown("DataFile.epr");
    }
	*/
}