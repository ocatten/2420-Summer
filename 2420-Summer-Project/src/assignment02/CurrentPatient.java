package assignment02;

import java.util.GregorianCalendar;

/**
 * This class represents a current patient that extends the patient class and has a
 * physician and date of last visit.
 * 
 * @author Eric Heisler, Everett Oglesby and Parker Catten
 * @version May 5, 2023
 */
public class CurrentPatient extends Patient {
	
	

	//Initialize Variables
	private String firstName;
	private String lastName;
	private int physician; 
	private GregorianCalendar lastVisit;
	
	/**
	 * Creates the current patient with their name, physician
	 * and date of last visit.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param UHealthID
	 */
	 public CurrentPatient(String firstName, String lastName, UHealthID uHealthID, 
			 int physician, GregorianCalendar lastVisit) {
		super(firstName, lastName, uHealthID);

		this.firstName = firstName;
		this.lastName = lastName;
		this.physician = physician;
		this.lastVisit = lastVisit;
	 }
	
	
	/**
	 * Return the physician as an int
	 * 
	 * @return physician
	 */
	public int getPhysician() {	
		return physician;
	}

	/**
	 * Return the date of the last visit as a GregorianCalendar date
	 * 
	 * @return lastVisit
	 */
	public GregorianCalendar getLastVisit() {
		return lastVisit;
	}
	
	/**
	 * Update the physician to the given physician
	 * 
	 * @param int - newPhysician
	 */
	public void updatePhysician(int newPhysician) {
		physician = newPhysician;
	}
	
	/**
	 * Update the last visit to the given date
	 * 
	 * @param GregorianCalendar - date
	 */
	public void updateLastVisit(GregorianCalendar date) {
		lastVisit = date;
	}
}
