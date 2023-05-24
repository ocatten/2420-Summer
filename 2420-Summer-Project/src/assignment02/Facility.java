package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * This class represents a record of patients that have visited a UHealth
 * facility. 
 * 
 * @author Eric Heisler,Everett Oglesby and Parker Catten
 * @version May 5, 2023
 */
public class Facility {
	
	private ArrayList<CurrentPatient> patientList;
	
	/**
	 * Creates an empty facility record.
	 */
	public Facility() {
		this.patientList= new ArrayList<CurrentPatient>();
	}
	
	/**
	 * Adds the given patient to the list of patients, avoiding duplicates.
	 * 
	 * @param patient - patient to be added to this record
	 * @return true if the patient was added, 
	 *         false if the patient was not added because they already exist in the record
	 */
	public boolean addPatient(CurrentPatient givenPatient) {
		
		//Loop through the patients in the ArrayList and if the given patient
		//is equal to any of the patients in the list, return false
		for(CurrentPatient currentPatient : patientList) {
			if(currentPatient.equals(givenPatient)) {
				return false;
			}
		}

		//Add the patient to the patient list
		patientList.add(givenPatient);
		return true;
	}
	
	/**
	 * Retrieves the patient with the given UHealthID.
	 * 
	 * @param UHealthID of patient to be retrieved
	 * @return the patient with the given ID, or null if no such patient 
	 * 			exists in the record
	 */
	public CurrentPatient lookupByUHID(UHealthID patientID) {

		//Loop through the patients in the ArrayList and if the given patientID
		//is equal to any of the patient's ID in the list, return the patient
		for(CurrentPatient currentPatient : patientList) {
			
			//Return the current patient if the UHealthIDs are equal
			if(currentPatient.getUHealthID().equals(patientID)) {
				return currentPatient;
			}
		}
		//Return null if the given UHealthID doesn't match any of the
		//UHealthIDs in the list
		return null;
	}
	
	/**
	 * Retrieves the patient(s) with the given physician.
	 * 
	 * @param physician - physician of patient(s) to be retrieved
	 * @return a list of patient(s) with the given physician (in any order), 
	 * 	     or an empty list if no such patients exist in the record
	 */
	public ArrayList<CurrentPatient> lookupByPhysician(int physician) {
		//Create an empty ArrayList of CurrentPatients
		ArrayList<CurrentPatient> physicianPatients = new ArrayList<CurrentPatient>();
		
		//Loop through the patients in the Patient List
		for(CurrentPatient patient : patientList) {
			//If the patient has the given physician, add them to the list
			if(patient.getPhysician() == physician) {
				physicianPatients.add(patient);
			}
		}
		return physicianPatients;
	}
	
	/**
	 * Retrieves the patient(s) with last visits older than a given date.
	 * 
	 * NOTE: If the last visit date equals this date, do not add the patient.
	 * 
	 * @param date - cutoff date later than visit date of all returned patients.
	 * @return a list of patient(s) with last visit date before cutoff (in any order), 
	 * 	     or an empty list if no such patients exist in the record
	 */
	public ArrayList<CurrentPatient> getInactivePatients(GregorianCalendar date) {
		//Create new ArrayList to add the inactive patients to
		ArrayList<CurrentPatient> inactivePatients = new ArrayList<CurrentPatient>();
		
		//Loop through the patients in the Patient List
		for(CurrentPatient patient : patientList) {
			//If the last date visited is after the date given, add the patient
			//to the inactive patients list
			if(patient.getLastVisit().before(date)) {
				inactivePatients.add(patient);
			}
		}
		return inactivePatients;
	}
	
	/**
	 * Retrieves a list of physicians assigned to patients at this facility.
	 * 
	 * * NOTE: Do not put duplicates in the list. Make sure each physician
	 *       is only added once.
	 * 
	 * @return a list of physician(s) assigned to current patients, 
	 * 	     or an empty list if no patients exist in the record
	 */
	public ArrayList<Integer> getPhysicianList() {
		//Create a new ArrayList of physicians
		ArrayList<Integer> physicianList = new ArrayList<Integer>();
		
		//Loop through the patients in the Patient List
		for(CurrentPatient patient : patientList) {
			//Check if each patient's physician is in the physician list
			if(!physicianList.contains(patient.getPhysician())) {
				//Add the physician to the list if not already in the list
				physicianList.add(patient.getPhysician());
			}
		}
		return physicianList;
	}
	
	/**
	 * Sets the physician of a patient with the given UHealthID.
	 * 
	 * NOTE: If no patient with the ID exists in the collection, then this 
	 * 		method has no effect.
	 * 
	 * @param patientID - UHealthID of patient to modify
	 * @param physician - identifier of patient's new physician
	 */
	public void setPhysician(UHealthID patientID, int physician) {
		//Loop through the patient list and find the patient with the given patinetID
		for(CurrentPatient patient : patientList) {
			if(patient.getUHealthID().equals(patientID)) {
				//If patient exists, update its physician to the physician given
				patient.updatePhysician(physician);
			}
		}
	}
	
	/**
	 * Sets the last visit date of a patient with the given UHealthID.
	 * 
	 * NOTE: If no patient with the ID exists in the collection, then this 
	 * 		method has no effect.
	 * 
	 * @param patientID - UHealthID of patient to modify
	 * @param date - new date of last visit
	 */
	public void setLastVisit(UHealthID patientID, GregorianCalendar date) {
		//Loop through the patient list and find the patient with the given patinetID
		for(CurrentPatient patient : patientList) {
			if(patient.getUHealthID().equals(patientID)) {
				//If patient exists, update the date of their last visit to the given date
				patient.updateLastVisit(date);
			}
		}
	}
	
	// The methods below should not be changed -----------------------------------
	
	/**
	 * Adds the patients specified by the input file to the record of patients.
	 * 
	 * Assumes a very strict file format:
	 * (each line): FirstName LastName ABCD-0123 u0123456 2023 05 16
	 *     
	 * Also assumes there are no duplicate patients in the file.
	 * 
	 * @param filename - full or relative path to file containing patient data
	 */
	public void addAll(String filename) {
		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNumber = 0;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				lineNumber++;
				CurrentPatient patient = parsePatient(line, lineNumber);
				
				addPatient(patient);
			}  // repeat for more patients
			
			fileIn.close();
		}
		catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " No patients added to facility record.");
		}
		catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". No patients added to facility record.");
		}
	}
	
	/**
	 * Helper method for parsing the information about a patient from file.
	 * 
	 * @param line - string to be parsed
	 * @param lineNumber - line number in file, used for error reporting (see above)
	 * @return the Patient constructed from the information
	 * @throws ParseException if file containing line is not properly formatted (see above)
	 */
	private CurrentPatient parsePatient(String line, int lineNumber) throws ParseException {
		Scanner lineIn = new Scanner(line);
		lineIn.useDelimiter(" ");

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("First name", lineNumber);
		}
		String firstName = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("Last name", lineNumber);
		}
		String lastName = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("UHealth ID", lineNumber);
		}
		String patientIDString = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("physician", lineNumber);
		}
		String physicianString = lineIn.next();
		int physician = Integer.parseInt(physicianString.substring(1, 8));
		
		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("year of last visit", lineNumber);
		}
		String yearString = lineIn.next();
		int year = Integer.parseInt(yearString);
		
		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("month of last visit", lineNumber);
		}
		String monthString = lineIn.next();
		int month = Integer.parseInt(monthString);
		
		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("day of last visit", lineNumber);
		}
		String dayString = lineIn.next();
		int day = Integer.parseInt(dayString);
		
		GregorianCalendar lastVisit = new GregorianCalendar(year, month, day);
		
		lineIn.close();
		
		return new CurrentPatient(firstName, lastName, new UHealthID(patientIDString), 
								physician, lastVisit);
	}
}
