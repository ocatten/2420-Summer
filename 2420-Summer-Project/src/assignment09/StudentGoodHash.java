package assignment09;

import java.text.DecimalFormat;

/**
 * This class provides a simple representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object, but one that does a good job of distributing students in a hash
 * table.
 * 
 * @author Everett Oglesby and Parker Catten
 * @version 07/09/2023
 */
public class StudentGoodHash {

	private int uid;
	private String firstName;
	private String lastName;

	/**
	 * Creates a new student with the specified uid, firstName, and lastName.
	 * 
	 * @param uid
	 * @param firstName
	 * @param lastName
	 */
	public StudentGoodHash(int uid, String firstName, String lastName) {
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the UID for this student object
	 */
	public int getUid() {
		return this.uid;
	}

	/**
	 * @return the first name for this student object
	 */
	
	public String getFirstName() {
		return this.firstName;
	}
 
	/**
	 * @return the last name for this student object
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @return true if this student and 'other' have the same UID, first name, and last name; false otherwise
	 */
	public boolean equals(Object other) {

		if (!(other instanceof StudentGoodHash)) {
			return false;
		}

		StudentGoodHash rhs = (StudentGoodHash) other;

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
		int hashCode = 1;
		int first = firstName.length();
		int last = lastName.length();
		for(int i = 0; i < first; i++) {
			char letter = firstName.charAt(i);
			int value = Character.getNumericValue(letter);
			value = value^i;
			hashCode *= value;
		}
		
		for(int i = 0; i < last; i++) {
			char letter = lastName.charAt(i);
			int value = Character.getNumericValue(letter);
			value = value^i;
			hashCode *= value;
		}
		
		hashCode *= uid;
		
		return hashCode;
	}
}