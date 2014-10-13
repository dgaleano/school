import java.io.*;

public class ValidSlot implements Serializable {

	private Time time;
	private String label;
	
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
	 * Sets the attribute: label
	 *
	 * @param theLabel Description
	 */
	public void setLabel(String theLabel) {
        label = theLabel;
    }

	/**
	 * Gets the attribute: label
	 *
	 * @return String Description
	 */
	public String getLabel() {
        return label;
    }
}
