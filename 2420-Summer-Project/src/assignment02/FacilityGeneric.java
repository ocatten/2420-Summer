package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * This class represents a record of patients that have visited a UHealth
 * facility. 
 * 
 * @author Eric Heisler, Everett Oglesby and Parker Catten
 * @version May 5, 2023
 */
public class FacilityGeneric<Type> {
	
	private ArrayList<CurrentPatientGeneric<Type>> patientList;
	
	/**
	 * Creates an empty facility record.
	 */
	public FacilityGeneric() {
		this.patientList= new ArrayList<CurrentPatientGeneric<Type>>();
	}
	
	/**
	 * Adds the given patient to the list of patients, avoiding duplicates.
	 * 
	 * @param patient - patient to be added to this record
	 * @return true if the patient was added, 
	 *         false if the patient was not added because they already exist in the record
	 */
	public boolean addPatient(CurrentPatientGeneric<Type> givenPatient) {
		
		//Loop through the patients in the ArrayList and if the given patient
		//is equal to any of the patients in the list, return false
		for(CurrentPatientGeneric<Type> currentPatient : patientList) {
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
	public CurrentPatientGeneric<Type> lookupByUHID(UHealthID patientID) {

		//Loop through the patients in the ArrayList and if the given patientID
		//is equal to any of the patient's ID in the list, return the patient
		for(CurrentPatientGeneric<Type> currentPatient : patientList) {
			
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
	public ArrayList<CurrentPatientGeneric<Type>> lookupByPhysician(Type physician) {
		//Create an empty ArrayList of CurrentPatients
		ArrayList<CurrentPatientGeneric<Type>> physicianPatients = new ArrayList<CurrentPatientGeneric<Type>>();
		
		//Loop through the patients in the Patient List
		for(CurrentPatientGeneric<Type> patient : patientList) {
			//If the patient has the given physician, add them to the list
			if(patient.getPhysician().equals(physician)) {
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
	public ArrayList<CurrentPatientGeneric<Type>> getInactivePatients(GregorianCalendar date) {
		//Create new ArrayList to add the inactive patients to
		ArrayList<CurrentPatientGeneric<Type>> inactivePatients = new ArrayList<CurrentPatientGeneric<Type>>();
		
		//Loop through the patients in the Patient List
		for(CurrentPatientGeneric<Type> patient : patientList) {
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
	public ArrayList<Type> getPhysicianList() {
		//Create a new ArrayList of physicians
		ArrayList<Type> physicianList = new ArrayList<Type>();
		
		//Loop through the patients in the Patient List
		for(CurrentPatientGeneric<Type> patient : patientList) {
			//Check if each patient's physician is in the physician list
			if(!physicianList.contains(patient.getPhysician())) {
				//Add the physician to the list if not already in the list
				physicianList.add( patient.getPhysician());
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
	public void setPhysician(UHealthID patientID, Type physician) {
		//Loop through the patient list and find the patient with the given patinetID
		for(CurrentPatientGeneric<Type> patient : patientList) {
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
		for(CurrentPatientGeneric<Type> patient : patientList) {
			if(patient.getUHealthID().equals(patientID)) {
				//If patient exists, update the date of their last visit to the given date
				patient.updateLastVisit(date);
			}
		}
	}	
	
	
    /**
	 * Returns the list of current patients in this facility, 
	 * sorted by uHealthID in lexicographical order.
	 */
	public ArrayList<CurrentPatientGeneric<Type>> getOrderedByUHealthID() {
	    ArrayList<CurrentPatientGeneric<Type>> patientListCopy = new ArrayList<CurrentPatientGeneric<Type>>();
		for (CurrentPatientGeneric<Type> patient : patientList) {
			patientListCopy.add(patient);
		}
	    sort(patientListCopy, new OrderByUHealthID());

	    return patientListCopy;
	}

	/**
	 * Returns the list of current patients in this facility, 
	 * sorted by last name in lexicographical order.
	 * Breaks ties in last names using first names (lexicographical order).
	 * Breaks ties in first names using uHealthIDs (lexicographical order).
	 */
	public ArrayList<CurrentPatientGeneric<Type>> getOrderedByName() {
		 ArrayList<CurrentPatientGeneric<Type>> patientListByName = new ArrayList<CurrentPatientGeneric<Type>>();
		 for(CurrentPatientGeneric<Type> patient : patientList) {
			 patientListByName.add(patient);
		 }
		 sort(patientListByName, new OrderByName());
		 return patientListByName;
	}

	/**
	 * Returns the list of current patients in this facility with a date of last visit
	 * later than a cutoff date, sorted by date (chronological order).  
	 * Breaks ties in dates using uHealthIDs (lexicographical order).
	 * 
	 * @param cutoffDate - value that a patient's last visit must be later than to be 
	 * 						included in the returned list
	 */
	public ArrayList<CurrentPatientGeneric<Type>> getOrderedByDate(GregorianCalendar cutoffDate) {
		ArrayList<CurrentPatientGeneric<Type>> patientListByDate = new ArrayList<CurrentPatientGeneric<Type>>();
		 for(CurrentPatientGeneric<Type> patient : patientList) {
			 if(patient.getLastVisit().after(cutoffDate)) {
				 patientListByDate.add(patient); 
			 }
		 }
		 sort(patientListByDate, new OrderByDate());
		 return patientListByDate;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList. 
	 * 
	 * 1. Finds the smallest item in the list. 
	 * 2. Swaps the smallest item with the first item in the list. 
	 * 3. Reconsiders the list to be the remaining unsorted portion (second item to Nth item) and 
	 *    repeats steps 1, 2, and 3.
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++) {
				if (c.compare(list.get(j), list.get(minIndex)) < 0) {
					minIndex = j;
				}
			}
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	/**
	 * Comparator that defines an ordering among current patients using their uHealthIDs.
	 * uHealthIDs are guaranteed to be unique, making a tie-breaker unnecessary.
	 */
	protected class OrderByUHealthID implements Comparator<CurrentPatientGeneric<Type>> {

		/**
		 * Returns a negative value if lhs (left-hand side) is less than rhs (right-hand side). 
		 * Returns a positive value if lhs is greater than rhs.
		 * Returns 0 if lhs and rhs are equal.
		 */
		public int compare(CurrentPatientGeneric<Type> lhs, CurrentPatientGeneric<Type> rhs) {
			return lhs.getUHealthID().toString().compareTo(rhs.getUHealthID().toString());
		}
	}

	/**
	 * Comparator that defines an ordering among current patients using their names.
	 * Compares by last name, then first name (if last names are the same), then uHealthID 
	 * (if both names are the same).  uHealthIDs are guaranteed to be unique.
	 */
	protected class OrderByName implements Comparator<CurrentPatientGeneric<Type>> {

		public int compare(CurrentPatientGeneric<Type> lhs, CurrentPatientGeneric<Type> rhs) {
			if(lhs.getLastName().compareTo(rhs.getLastName()) == 0) {
				return lhs.getFirstName().compareTo(rhs.getFirstName());
			}
			return lhs.getLastName().compareTo(rhs.getLastName());
		}
	}
	
	protected class OrderByDate implements Comparator<CurrentPatientGeneric<Type>>{
		
		public int compare(CurrentPatientGeneric<Type> lhs, CurrentPatientGeneric<Type> rhs) {
			if(lhs.getLastVisit().compareTo(rhs.getLastVisit()) != 0) {
				return lhs.getLastVisit().compareTo(rhs.getLastVisit());
			}
			return lhs.getUHealthID().toString().compareTo(rhs.getUHealthID().toString());
		}
	}
}
