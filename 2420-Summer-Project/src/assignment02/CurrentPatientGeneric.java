package assignment02;

import java.util.GregorianCalendar;

/**
 * This class represents a current patient that extends the patient class and has a
 * physician generic and date of last visit.
 * 
 * @author Eric Heisler, Everett Oglesby and Parker Catten
 * @version May 5, 2023
 */
public class CurrentPatientGeneric<Type> extends Patient {
	
	

	//Initialize Variables
	private String firstName;
	private String lastName;
	private Type physician; 
	private GregorianCalendar lastVisit;
	
	/**
	 * Creates the current patient with their name, physician
	 * and date of last visit.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param UHealthID
	 */
	 public CurrentPatientGeneric(String firstName, String lastName, UHealthID uHealthID, 
			 Type physician, GregorianCalendar lastVisit) {
		super(firstName, lastName, uHealthID);

		this.firstName = firstName;
		this.lastName = lastName;
		this.physician = physician;
		this.lastVisit = lastVisit;
	 }
	
	
	/**
	 * Return the physician as an Type
	 * 
	 * @return Type - physician
	 */
	public Type getPhysician() {	
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
	 * @param Type - newPhysician
	 */
	public void updatePhysician(Type newPhysician) {
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
