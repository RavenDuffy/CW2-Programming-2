package CW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Date;

/** The SmartCardNumber class is an <b>immutable</b> class that generates a unique
 * 	smart card number for smart card to hold. Along with that it will also save the
 *  date it was created as the issue date for the card.
 *  <p>This class has one constructor that generates the unique smart card number
 *  and saves its date of issue using a single String input that should hold a
 *  student's name.
 *  @author Raven Duffy B7044359
 */
public final class SmartCardNumber {
	
	private final String initials;
	private final String serialNum;
	private final String dateOfIssue;
	private final int yearOfIssue;
	
	/** This constructor defines the new SmartCardNumber object using the student's
	 *  name, which it converts to initials, and current date. This is then combined
	 *  with a unique two digit serial number to create a SmartCardNumber.
	 *  @param name : This is the String representation of the student's first and 
	 *  			  last name ONLY.
	 *  @throws FileNotFoundException
	 */
	public SmartCardNumber(String name) throws FileNotFoundException {
		// creates a new scanner to hold iterate through the names to get first and
		// last
		Scanner names = new Scanner(name);
		
		// saves first and last name to their respective variables
		String firstName = names.next();
		String secondName = names.next();
		
		// the initials constant is set to the first letters of the first and last 
		// names
		initials = Character.toString(firstName.charAt(0)) + Character.toString(secondName.charAt(0));
		
		
		// separates the year from the date of issue
		Date currentDate = new Date();
		String dateInfo = currentDate.toString();
		dateOfIssue = dateInfo.substring(4, 11) + dateInfo.substring(30); // full date
		yearOfIssue = Integer.parseInt(dateInfo.substring(30)); // just year
		
		
		// Reads the SmartCardRecords file to determine the 2 digit serial to use
		File SmartCardRecords = new File("SmartCardRecords");
		Scanner SCRReader = new Scanner(SmartCardRecords); // opens a new scanner 
														   // that reads
														   // SmartCardRecords
	
		int duplicateCount = 0; // holds number of other serials that have the same
								// first two parts
		// checks to see if the first two parts are the same as the current info
		while (SCRReader.hasNextLine()) {
			String prevSC = SCRReader.nextLine();
			prevSC = prevSC.substring(0, 7);
			
			// if the parts are the same increment the duplicateCount
			if (prevSC.equals((initials + "-" + yearOfIssue)))
				duplicateCount++;
		}
		
		String serialStr; // this holds the finished serial
		if (duplicateCount < 10) // if the serial is 1 digit it adds a 0
			serialStr = ("0" + duplicateCount); 
		else if (duplicateCount < 99) { // if the serial is greater than 99 an
										// exception is thrown
			SCRReader.close(); // Scanners are closed to prevent leaks
			names.close();
			throw new IndexOutOfBoundsException("There are too many students with this SmartCard Code, consider deleting previous entries");
		}
		else // if the serial is more than two digits and isn't 99 then its the same
			serialStr = ("" + duplicateCount); 
			
		serialNum = serialStr; // the serialNum = the finished serialStr generated
		
		names.close(); // Scanners are close to prevent leaks
		SCRReader.close();
	}
	
	/** This method overrides the original toString method to return "initials-year-serialNum"
	 *  @return The initials, year of issue and serial number formatted like the above
	 */
	public String toString() {
		return (new String(initials) + "-" + yearOfIssue + "-" + new String(serialNum));
	}
	
	/** This methods returns the date the card was created as a String
	 *  @return A new String object holding the date the card was created (dateOfIssue)
	 */
	public String getDateOfIssue() {
		return new String(dateOfIssue);
	}
}
