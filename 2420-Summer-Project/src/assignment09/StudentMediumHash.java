package assignment09;

import java.text.DecimalFormat;

/**
 * This class provides a more complex representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object, but one that does an adequate job of distributing students in a hash table.
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 07:09:23 CS-2420_001 SUM-2023
 */

public class StudentMediumHash {
	
	// Fields
	private int uid;
	private String firstName;
	private String lastName;
		
	/**
	 * @Constructor for a new student with the specified uid, firstName, and lastName.
	 * 
	 * @param uid: 
	 * @param firstName
	 * @param lastName
	 */
	public StudentMediumHash(int uid, String firstName, String lastName) {

		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	
	/**
	 * @return the UID for this student object
	 */
	public int getUid() { return this.uid; }
	

	/**
	 * @return the first name for this student object
	 */
	public String getFirstName() { return this.firstName; }
	


	/**
	 * @return the last name for this student object
	 */
	public String getLastName() { return this.lastName; }
	


	/**
	 * @return true if this student and 'other' have the same UID, first name, and last name; false otherwise
	 */
	public boolean equals(Object other) {


		if (!(other instanceof StudentMediumHash)) {
			return false;
		}

		StudentMediumHash rhs = (StudentMediumHash) other;

		return this.uid == rhs.uid && this.firstName.equals(rhs.firstName) && this.lastName.equals(rhs.lastName);
	}
	
	/**
	 * @return a textual representation of this student
	 */
	public String toString() {
		
		DecimalFormat formatter = new DecimalFormat("0000000");
		return firstName + " " + lastName + " (u" + formatter.format(uid) + ")";
	}
	
	

	public int hashCode() {
		// FILL IN and add method comment
		
		return 0;
		DecimalFormat formatter = new DecimalFormat("0000000");
		return firstName + " " + lastName + " (u" + formatter.format(uid) + ")";
	}

	public int hashCode() {
		int hashCode = 0;
		int first = firstName.length();
		int last = lastName.length();
		hashCode = (first^2) * (last^2);
		hashCode *= (uid)^2;
		return hashCode;
	}
}
