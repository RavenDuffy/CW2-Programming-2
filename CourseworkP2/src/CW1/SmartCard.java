package CW1;

import java.io.FileNotFoundException;

/** SmartCard is an <b>immutable</b> class that holds some of the information about 
 * 	a student. 
 *  <p>SmartCard has two separate constructors, both of which create a new SmartCard
 *  object. The first of these constructors takes a Students object and creates the
 *  card from the that info (this is used when creating new cards). The other takes
 *  the information from a file hence the five constructors (this is used when
 *  remaking cards from the information stored).
 *  @author Raven Duffy B7044359
 */
public final class SmartCard {
	
	private final String name;
	private final String SCN;
	private final String DOB; // Date of Birth
	private final String DOI; // Date of Issue
	private final String studentID;
	
	/** This constructor takes one input in the form of a Student object which is
	 *  where it will get the information for the SmartCardNumber class and for its
	 *  own entries. This is what the user will interface with.
	 *  @param student : This is the student Object that the class will use to
	 *  				 create its entries.
	 *  @throws FileNotFoundException
	 */
	public SmartCard(Students student) throws FileNotFoundException {
		// initialise the SmartCardNumber object
		SmartCardNumber smartCard;
		
		// sets the name to the student object's name
		this.name = student.getName();
		
		// sets the smartCard variable to a new SmartCardNumber object that is
		// created.
		smartCard = new SmartCardNumber(name);
		SCN = smartCard.toString(); // Sets the SCN variable to the String
									// representation of the SmartCardNumber object
		DOI = smartCard.getDateOfIssue(); // Sets the DOI to the date the 
										  // SmartCardNumber object was created
		DOB = student.getBirthDate(); // Sets the DOB to the student's birth date
		studentID = student.getStudentID(); // Sets the studentID to the student's ID
		
		// Throws an exception if the student's age is less than required, this is
		// causes the program to stop and therefore a SmartCard object for this
		// student is not created.
		if (!(ofAge(student.getBirthDate(), this.getDOI(), student.getStudentID())))
			throw new IllegalArgumentException("Student is not of age");
	}
	
	/** This constructor takes five inputs in the form of the SmartCardNumber as a
	 *  String, the students name as a String, the date of birth as a String, the
	 *  date of issue as a string and the studentID as a String. This should never
	 *  be accessed by a user, this is only to remake existing SmartCard saved in
	 *  previous iterations of the program.
	 *  @param SCN : This is the smart card number the user is assigned, this should
	 *  			 always be a String
	 *  @param name : This should be a String representation of the student's FIRST
	 *  			  and LAST names only.
	 *  @param DOB : This should be a String representation of the student's
	 *  			 date of birth in the format month/dd/year
	 *  @param DOI : This should be a String representation of the smartCard's date
	 *  			 of issue in the format month/dd/year
	 *  @param studentID : This should be the studentID the student was assigned
	 *  				   previously (this can be found in the StudentRecords file)
	 *  @throws FileNotFoundException
	 */
	public SmartCard(String SCN, String name, String DOB, String DOI, String studentID) throws FileNotFoundException {
		// all variables are set to their respective input
		
		this.SCN = SCN;
		this.name = name;
		this.DOB = DOB;
		this.DOI = DOI;
		this.studentID = studentID;
	}
	
	/** This should return the student's name as a String
	 *  @return The name of the student as a new String object
	 */
	public String getName() {
		return new String(name);
	}
	
	/** This should return the student's smart card number as a String
	 *  @return The student's smart card number as a new String object
	 */
	public String getSmartCardNum() {
		return new String(SCN);
	}
	
	/** This should return the student's ID as a String
	 *  @return The student's ID as a new String object
	 */
	public String getStudentID() {
		return new String(studentID);
	}
	
	/** This should return the student's DOB as a String
	 *  @return The student's DOB as a new String object
	 */
	public String getDOB() {
		return new String(DOB);
	}
	
	/** This should return the student's DOI as a String
	 *  @return The student's DOI as a new String object
	 */
	public String getDOI() {
		return new String(DOI);
	}
	
	/** This private method checks to see if the student is of the correct age to 
	 *  receive a smart card.
	 *  <p>It takes the student's DOB, their SmartCard's DOI and their studentID and 
	 *  uses them to calculate the difference in age to insure they are old enough.</p>
	 *  @param DOB : This is the student's date of birth and should be formatted as
	 *  			 such: month/dd/year
	 *  @param DOI : This is the smart card's date of issue and should be formatted 
	 *  			 as such: month/dd/year
	 *  @param studentID : This is the studentID and should be a String
	 *  @return <b>True</b> : if the student is old enough.<p><b>False</b> : if the 
	 *  		student is too young</p>
	 */
	private boolean ofAge(String DOB, String DOI, String studentID) {
		// sets the year of issue and year of birth to variables then subtracts them
		// to find the difference in years
		int DOIYear = Integer.parseInt(DOI.substring(DOI.length() - 4)); 
		int DOBYear = Integer.parseInt(DOB.substring(DOB.length() - 4));
		int yeardiff = DOIYear - DOBYear;
		
		// creates an array to hold the months and two array to hold the days of 
		// each month. These are separate so that leap years can be taken into 
		// account.
		String[] months = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		int[] monthsDaysDOB = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int[] monthsDaysDOI = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		// The year of birth and year of issue are check to see if they occurred on
		// leap years. If they did the number of days in February is changed to 29.
		if (leapYear(DOB))
			monthsDaysDOB[1] = 29;
		if (leapYear(DOI))
			monthsDaysDOI[1] = 29;
		
		// daysDiffDOB and daysDiffDOI are initialised and set to 0.
		// These will be used to calculate the number of days apart the issue and
		// birth dates are (this does not take into account the year)
		int daysDiffDOB = 0; int daysDiffDOI = 0;
		
		// This continues to add up the number of days in the arrays until they
		// equal the date of birth month and date of issue month. Each time they
		// don't equal the number of days of each month is added to their total
		for (int month = 0; month < months.length; month++) {
			if (DOB.substring(0, 3).toLowerCase().equals(months[month])) 
				break;
			else
				daysDiffDOB += monthsDaysDOB[month];
		}
		for (int month = 0; month < months.length; month++) {
			if (DOI.substring(0, 3).toLowerCase().equals(months[month])) 
				break;
			else
				daysDiffDOI += monthsDaysDOI[month];
		}
		
		// When the loops are done the day of the DOB and DOI are added to the totals
		daysDiffDOB += Integer.parseInt(DOB.substring(4, 6));
		daysDiffDOI += Integer.parseInt(DOI.substring(4, 6));
		
		// This checks to see what the minimum age for the student is.
		// If they are an undergrad then the minimum age is set to 17
		if (studentID.charAt(0) <= 97 && studentID.charAt(0) >= 108) {
			if (yeardiff < 17) // if they're younger than 17 returns false
				return false;
			else { // if they're 17 or older
				if (yeardiff == 17) { // if they're 17 check days difference
					if (daysDiffDOI - daysDiffDOB >= 0)
						return true; // true if older than the date the card was made
				}
				else 
					return true; // false otherwise
			}
			return false;
		}
		// If they are a post graduate then the minimum age is set to 20
		else if (studentID.charAt(0) <= 109 && studentID.charAt(0) >= 122) {
			if (yeardiff < 20) // if they're younger than 20 returns false
				return false;
			else { // if they're 20 or older
				if (yeardiff == 20) { // if they're 20 check days difference
					if (daysDiffDOI - daysDiffDOB >= 0)
						return true; // true if older than the date the card was made
				}
				else
					return true; // false otherwise
			}
			return false;
		}
		// If they are neither of these student types the program returns true. This
		// is because this only happens if the SmartCard is being created by a new
		// student object.
		else
			return true;
	}
	
	/** This is a private method that checks to see if the date the sent occurred on
	 *  a leap year (returning true if it was).
	 *  @param date : This is the 
	 *  @return <b>True</b> if the date occurred on a leap year<p><b>False</b> if
	 *  		the date was not on a leap year</p>
	 */
	private boolean leapYear(String date) {
		// This checks to see if the year is divisible by 100
		if (Integer.parseInt(date.substring(date.length() - 4)) % 100 == 0)
			if (Integer.parseInt(date.substring(date.length() - 4)) % 400 == 0)
				return true; // if it is also divisible by 400 then return true
		else if (Integer.parseInt(date.substring(date.length() - 4)) % 4 == 0)
			return true; // if the date is divisible by 4 then return true
		return false; // otherwise return false
	}
}
