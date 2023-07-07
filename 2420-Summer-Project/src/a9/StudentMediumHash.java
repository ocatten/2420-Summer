package a9;

import java.text.DecimalFormat;

/**
 * This class provides a simple representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object, but one that does a okay job of distributing students in a hash
 * table.
 * 
 * @author Erin Parker and Parker Catten @u0580588 & Everett Oglesby @u0779085
 * @version 04:06:23 SP-2420_001
 */
public class StudentMediumHash {

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
	public StudentMediumHash(int uid, String firstName, String lastName) {
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
		if(!(other instanceof StudentMediumHash))
			return false;

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
	
	
	
	/**
	 * This is the intentionally medium hashing function, which gives a just alright hashcode result.
	 * Multiplies the ASCII character value of every character in each word together to get
	 * the final hashcode.
	 *
	 * 
	 * @return: The length of the total hashcode value
	 */
	public int hashCode() {
		//int to keep track of the hashcode value
		int hashcode = 1;
		
		//A string consisting of the firstName, lastName and uid combined
		String totalVarible = firstName + lastName + uid;
		
		//For each character in the combined string multiply the ascii value of 
		//the character by the total ascii value of the other characters
		for(int i = 0; i < totalVarible.length(); i++) {
			
			// Finds the character at the current position
			char character = totalVarible.charAt(i);
			int ascii = (int) character;
			
			// Multiplies the next ASCII value by the total
			hashcode *= ascii;
		}
		
		return hashcode;
	}
}