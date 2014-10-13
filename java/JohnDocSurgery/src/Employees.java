import java.util.*;
import java.io.*;      // required for Serialising objects

/**
 * The employees at the surgery
 */
public class Employees implements Iterable, Serializable {
	private ArrayList<Person> staff;

    public Employees () {
        staff = new ArrayList<Person>();
    }

	
	public Person getPerson(String theName) {
		Iterator<Person> iter = staff.iterator();
		Person temp;
		
		while (iter.hasNext()) {
			temp = iter.next();
			if (temp.getName().equalsIgnoreCase(theName)) {
				return temp;
			}
		} return null;
	}
	
	/**
	 * Allows user to add a Person to the Employee collection
	 *
	 * @param thePerson
	 */
		
	public void addPerson(Person thePerson) {
        staff.add(thePerson);
    }

	/**
	 * Allows a user to iterate over the employees
	 *
	 * @return Iterator<Person>
	 */
	public Iterator<Person> iterator() {
        return staff.iterator();
    }

}