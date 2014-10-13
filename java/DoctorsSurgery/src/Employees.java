import java.util.*;
import java.io.*;      // required for Serialising objects

/**
 * The employees at the surgery
 */
public class Employees implements Iterable, Serializable {
	private ArrayList<Person> staff = new ArrayList<Person>();

	/**
	 * Allows user to add a Person to the Employee collection
	 *
	 * @param thePerson
	 */
	public void addPerson(Person thePerson) {
        staff.add(thePerson);
    }
    
    /**
     * Pulls out an employee by their name.
     *
     * @param theName Name of the employee.
     */
    public Person getByName(String theName) throws HealthException {
    	
    	Iterator<Person> iter = staff.iterator();
    	Person temp;
    	
    	while (iter.hasNext()) {
    		temp = iter.next();
    		if (temp.getName() == theName) {
    			return temp;
    		}
    	}
    	
    	throw new HealthException("Employee not found.");
    }

	/**
	 * Allows a user to iterate over the employees
	 *
	 * @return Iterator<Person>
	 */
	public Iterator<Person> iterator() {
        return staff.iterator();
    }
    
    /**
     * How many staff do we have?
     */
    public int staffCount() {
    	return staff.size();
    }
}