import java.io.*;      // required for Serialising objects
/**
 * Details of a a particular health problem
 */
public class HealthProblem implements Serializable {
	private Date date;
	private String description;
	private String treatment;

    public HealthProblem (Date theDate, String theProb, String theMed) {
        date = theDate;
        description = theProb;
        treatment = theMed;
    }

	/**
	 * Sets the attribute: date
	 *
	 * @param theDate The date of the problem report
	 */
	public void setDate(Date theDate) {
        date = theDate;
    }


	/**
	 * Gets the attribute: date
	 *
	 * @return Date The date of the problem report
	 */
	public Date getDate() {
        return date;
    }


	/**
	 * Sets the attribute: description
	 *
	 * @param theDescription The problem description
	 */
	public void setDescription(String theDescription) {
        description = theDescription;
    }


	/**
	 * Gets the attribute: description
	 *
	 * @return String The problem description
	 */
	public String getDescription() {
        return description;
    }


	/**
	 * Sets the attribute: treatment
	 *
	 * @param theTreatment Colection of treatments
	 */
	public void setTreatment(String theTreatment) {
        treatment = theTreatment;
    }


	/**
	 * Gets the attribute: treatment
	 *
	 * @return String Colection of treatments
	 */
	public String getTreatment() {
        return treatment;
    }


}