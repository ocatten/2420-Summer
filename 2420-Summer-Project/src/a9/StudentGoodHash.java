package a9;

import java.text.DecimalFormat;

/**
 * This class provides a simple representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object, and one that does a good job of distributing students in a hash
 * table.
 * 
 * @author Erin Parker and Parker Catten @u0580588 & Everett Oglesby
 * @version 04:06:23 SP-2420_001
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
		// change to StudentMediumHash and StudentGoodHash for two new classes
		if(!(other instanceof StudentBadHash))
			return false;

		StudentBadHash rhs = (StudentBadHash) other;

		return this.uid == rhs.getUid() && this.firstName.equals(rhs.getFirstName()) && this.lastName.equals(rhs.getLastName());
	}
	
	/**
	 * @return a textual representation of this student
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("0000000");
		return firstName + " " + lastName + " (u" + formatter.format(uid) + ")";
	}
	
	
	
	/**
	 * This hash function converts each character to an ASCII value and raises it to the power of its
	 * position in the string. It then adds each value together, and adds that number to the same process
	 * on the last name
	 * 
	 * @return: The hashed first and last name combined
	 */
	public int hashCode() {
		
		int hash = 0; // Running total to add up the ASCII values
		
		// Iterate through each character, using an int to track position for calculations
		for(int i = 0; i < this.firstName.length(); i++) {
			
			// Takes the character at each position of the string, raises it by its position, adds it to the hash
			hash += Math.pow((int)firstName.charAt(i), i);
		}
		
		// Repeat this process for the last name.
		for(int i = 0; i < this.lastName.length(); i++) {
			
			// Takes the character at each position of the string, raises it by its position, adds it to the hash
			hash += Math.pow((int)lastName.charAt(i), i);
		}
		
		hash += uid;
		
		return hash; // Return the total
	}
}