import java.util.*;
import java.io.*;      // required for Serialising objects

/**
 * The collection of health problems for a patient
 */
public class HealthProblemHistory implements Iterable, Serializable {
	private ArrayList<HealthProblem> history;
	
    public HealthProblemHistory() {
        history = new ArrayList<HealthProblem>();
    }

	/**
	 * Allows a user to add a new Health Problem
	 * 
	 * @return boolean 
	 * @param problem 
	 */
	public void addProblem(HealthProblem problem) throws HealthException {
        Iterator<HealthProblem> iter = history.iterator();
        int position = 0;
        while (iter.hasNext()) {
            if (iter.next().getDate().compareTo(problem.getDate()) > 0 ) {
                break;
            }
            position++;
        }
        history.add(position, problem);

    }

    public Iterator<HealthProblem> iterator() {
        return history.iterator();
    }

}

