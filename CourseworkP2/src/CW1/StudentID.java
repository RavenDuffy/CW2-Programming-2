package CW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** StudentID is an <b>immutable</b> class that generates a unique String that will
 *  be used to identify the student object.
 *  <p>This class has one constructor that generates a unique studentID String. It
 *  creates this string by taking a Students object and checking the StudentRecords
 *  file. 
 *  @author Raven Duffy B7044359
 */
public final class StudentID {
	private final String ID;
	
	/** This constructor generates a new unique StudentID based on the type of
	 *  student that is entered (this ID will be returned as a String). 
	 *  <p>If the student type is an undergrad the ID generated will be between a0001
	 *  and l9999.</p>
	 *  <p>If the student type is a postgrad Taught the ID generated will be 
	 *  between m0001 and s9999.</p>
	 *  <p>If the student type is a postgrad Research the ID generated will be 
	 *  between t0001 and z9999.</p>
	 *  @param student : This should be a child object of the Students class, so
	 *  				 either: Undergrad, PostgradT or PostgradR
	 *  @throws FileNotFoundException
	 */
	public StudentID(Students student) throws FileNotFoundException {
		// A new File and scanner are created here, the Scanner reads the file which
		// is the StudentRecords file.
		File StudentRecords = new File("StudentRecords");
		Scanner SRReader = new Scanner(StudentRecords);
		
		// These variables are the starting values IDs for each student type. 
		// highestAL refers to the IDs that can represent the Undergrad student type
		// while the other two refer to the IDs that can represent the other two
		// student types.
		String highestAL = "a0000"; String highestMS = "m0000"; String highestTZ = "t0000";
		
		// These counters will count the amount of each ID type they find in the
		// file
		int ALCount = 0; int MSCount = 0; int TZCount = 0;
		
		// This loop runs until there are no more lines left in the file
		while (SRReader.hasNextLine()) {
			// every time the loop runs the next line is saved into the variable
			// line. this will stop unnecessary increments and will speed up runtime
			// because the program does not have to re-check every time
			String line = SRReader.nextLine();
			
			// This checks to see if the ASCII value of the first letter in each
			// line is equal to a lowercase a-z. Because none of the other entries 
			// in this document will begin with a lowercase letter this can stay
			// like this
			if ((int) line.charAt(0) >= 97 && (int) line.charAt(0) <= 122 && line.length() == 5) {
				// This checks to see if the first letter is a-l
				if ((int) line.charAt(0) >= 97 && ((int) line.charAt(0)) <= 108) { 
					ALCount++; // if it is ALCount gets incremented by 1
					if (Integer.parseInt(line.substring(1, 5)) + ((int) (line.charAt(0)) * 26) > 
					Integer.parseInt(highestAL.substring(1, 5)) + ((int) (highestAL.charAt(0))) * 26) {
						// The above if statement checks to see if the value of the
						// current line is larger than the current highestAL, if it
						// is then highestAL = line.
						highestAL = line;
					}
				}
				
				// This checks to see if the first letter is m-s
				else if ((int) line.charAt(0) >= 109 && ((int) line.charAt(0)) <= 115) { 
					MSCount++; // if it is MSCount get incremented by 1
					if (Integer.parseInt(line.substring(1, 5)) + ((int) (line.charAt(0)) * 26) > 
					Integer.parseInt(highestMS.substring(1, 5)) + ((int) (highestMS.charAt(0))) * 26) {
						// The above if statement checks to see if the value of the
						// current line is larger than the current highestMS, if it
						// is then highestMS = line.
						highestMS = line;
					}
				}
				
				// This checks to see if the first letter is t-z
				else if ((int) line.charAt(0) >= 116 && ((int) line.charAt(0)) <= 122) { 
					TZCount++; // if it is TZCount get incremented by 1
					if (Integer.parseInt(line.substring(1, 5)) + ((int) (line.charAt(0)) * 26) > 
					Integer.parseInt(highestTZ.substring(1, 5)) + ((int) (highestTZ.charAt(0))) * 26) {
						// The above if statement checks to see if the value of the
						// current line is larger than the current highestTZ, if it
						// is then highestTZ = line.
						highestTZ = line;
					}
				}
			}
		}
		
		SRReader.close(); // the Scanner is closed to prevent leaks
		
		// checks to see if the student type is an undergrad
		if (student instanceof Undergrad) {
			// Can use letters a - l (first 12)
			// if the four numbers are between 0 and 9998 then they are incremented
			// by 1 and the leading zeros are added back on to the number
			if (Integer.parseInt(highestAL.substring(1)) >= 0 && Integer.parseInt(highestAL.substring(1)) < 9999) {
				String tempID2 = highestAL.substring(1);
				tempID2 = (Integer.parseInt(tempID2) + 1) + "";
				if (ALCount < Integer.parseInt(tempID2) - 1) {tempID2 = ALCount + "";}
				int extraZeros = 4 - tempID2.length();
				String finalID = "";
				for (int zero = 0; zero < extraZeros; zero++) {finalID += "0";}
				ID = highestAL.substring(0, 1) + finalID + tempID2;
			}
			// if the 4 numbers in highestAL are equal to 9999 then the numbers are
			// set to 0001 and the letter is increased
			else if (Integer.parseInt(highestAL.substring(1)) == 9999) {
				char tempID1 = highestAL.charAt(0);
				tempID1 = (char) (((int) tempID1) + 1);
				ID = tempID1 + "0001";
				
				if (tempID1 == 'l') 
					throw new IndexOutOfBoundsException("The are no available slots for new students, consider deleting older entries");
			}
			// if it doesn't equal the previous criteria the program throws an exception
			else
				throw new IndexOutOfBoundsException("There are no available slots for new students, consider deleting older entries");
		}
		
		// checks to see if the student type is a postgradT
		else if (student instanceof PostgradT) {
			// Can use letters m - s (next 7)
			// if the four numbers are between 0 and 9998 then they are incremented
			// by 1 and the leading zeros are added back on to the number
			if (Integer.parseInt(highestMS.substring(1)) >= 0 && Integer.parseInt(highestMS.substring(1)) < 9999) {
				String tempID2 = highestMS.substring(1);
				tempID2 = (Integer.parseInt(tempID2) + 1) + "";
				if (MSCount < Integer.parseInt(tempID2) - 1) {tempID2 = MSCount + "";}
				int extraZeros = 4 - tempID2.length();
				String finalID = "";
				for (int zero = 0; zero < extraZeros; zero++) {finalID += "0";}
				ID = highestMS.substring(0, 1) + finalID + tempID2;
			}
			// if the 4 numbers in highestAL are equal to 9999 then the numbers are
			// set to 0001 and the letter is increased
			else if (Integer.parseInt(highestMS.substring(1)) == 9999) {
				char tempID1 = highestMS.charAt(0);
				tempID1 = (char) (((int) tempID1) + 1);
				ID = tempID1 + "0001";
				
				if (tempID1 == 's') 
					throw new IndexOutOfBoundsException("The are no available slots for new students, consider deleting older entries");
			}
			// if it doesn't equal the previous criteria the program throws an exception
			else
				throw new IndexOutOfBoundsException("There are no available slots for new students, consider deleting older entries");
		}
		
		// checks to see if the student type is a postgradR
		else if (student instanceof PostgradR){
			// Can use letters t - z (next 7)
			// if the four numbers are between 0 and 9998 then they are incremented
			// by 1 and the leading zeros are added back on to the number
			if (Integer.parseInt(highestTZ.substring(1)) >= 0 && Integer.parseInt(highestTZ.substring(1)) < 9999) {
				String tempID2 = highestTZ.substring(1);
				tempID2 = (Integer.parseInt(tempID2) + 1) + "";
				if (TZCount < Integer.parseInt(tempID2) - 1) {tempID2 = TZCount + "";}
				int extraZeros = 4 - tempID2.length();
				String finalID = "";
				for (int zero = 0; zero < extraZeros; zero++) {finalID += "0";}
				ID = highestTZ.substring(0, 1) + finalID + tempID2;
			}
			// if the 4 numbers in highestAL are equal to 9999 then the numbers are
			// set to 0001 and the letter is increased
			else if (Integer.parseInt(highestTZ.substring(1)) == 9999) {
				char tempID1 = highestTZ.charAt(0);
				
				// if the letter is z here the program throws an exception because
				// there are no more available slots for students
				if (tempID1 == 'z') 
					throw new IndexOutOfBoundsException("The are no available slots for new students, consider deleting older entries");
				
				tempID1 = (char) (((int) tempID1) + 1);
				ID = tempID1 + "0001";
			}
			// if it doesn't equal the previous criteria the program throws an exception
			else
				throw new IndexOutOfBoundsException("There are no available slots for new students, consider deleting older entries");
		}
		
		// if the student is not of the first 3 types then the ID is set to XXXXX
		// and the program throws an exception
		else {
			ID = "XXXXX";
			throw new IllegalArgumentException("The student is an invalid type");
		}
	}
	
	/** This overrides the toString method in java to send back the String version
	 *  of a studentID
	 *  @return This sends back a new String object containing the studentID
	 */
	public String toString() {
		return new String(ID);
	}
}
