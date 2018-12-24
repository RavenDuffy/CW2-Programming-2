package CW1;

/** Students is an <b>abstract</b> class because there are never any versions of it
 *  created on its own. 
 *  <p>This class has two separate constructors: one that takes a student's name and
 *  birth date and one that takes a student's birth date this is combined with the
 *  constructor from the Name class. The first of these constructors is used to
 *  interact with a user while the second one is used for when the class reads from
 *  a file.
 *  @extends Name
 *  @implements Student
 *  @author Raven Duffy B7044359
 */
public abstract class Students extends Name implements Student{
	
	private String birthDate; // birthDate MUST BE SET WHEN CHILD OBJECTS ARE CREATED
	
	private String studentID;
	
	private SmartCard smartCard;
	
	/** This constructor takes in the name and birth date of a student to create a
	 *  new object of whatever child originally called created an instance of itself.
	 *  <p>This constructor is specifically for user interaction and will trim any
	 *  user birth date entry so that the month will only be saved as the first
	 *  three characters <p>i.e September = Sep.</p></p>
	 *  @param name : This should be a String representation of the student's FIRST
	 *  			  and LAST names only.
	 *  @param birthDate : This should be a String representation of the student's
	 *  				   birthdate in the format month/dd/year
	 */
	public Students(String name, String birthDate) {
		super(name);
		
		String[] tempDOBSplit = birthDate.split(" ");
		tempDOBSplit[0] = tempDOBSplit[0].substring(0, 3);
		
		this.birthDate = tempDOBSplit[0] + " " + tempDOBSplit[1] + " " + tempDOBSplit[2];
	}
	
	/** This constructor takes in the birth date, studentID and SmartCard object of 
	 *  a student to create a new object of whatever child originally called created
	 *  an instance of itself.
	 *  <p>This constructor is to be used specifically when reading file entries
	 *  @param birthDate : This should be a String representation of the student's
	 *  				   birthdate in the format month/dd/year
	 *  @param studentID : This should be a String representation of the student's
	 *  				   studentID.
	 *  @param smartCard : This should be an existing SmartCard object created by
	 *  				   the SmartCard constructor dealing with file reading
	 */
	public Students(String birthDate, String studentID, SmartCard smartCard) {
		super(smartCard.getName());
		
		this.birthDate = birthDate;
		this.studentID = studentID;
		this.smartCard = smartCard;
	}
	
	/** This is an <b>implemented</b> method that will return the studentID
	 *  @return This returns a String containing the Student's ID
	 */
	public String getStudentID() {return studentID.toString();}
	
	/** This is a method that will return the current SmartCard object this object is
	 *  holding.
	 *  @return This returns the SmartCard object this class has assigned to itself
	 */
	public SmartCard getSmartCard() {return smartCard;}
	
	/** This is an <b>implemented</b> method that will return a SmartCard number
	 *  @return This returns a String containing the Student's SmartCard number
	 */
	public String getSmartCardNum() {return smartCard.getSmartCardNum();}
	
	/** This is an <b>implemented</b> method that will return the student's birth
	 *  date
	 *  @return This returns a String containing the Student's birth date formatted:
	 *  mon/dd/year
	 */
	public String getBirthDate() {return birthDate;}
	
	/** This is a method that will return the allow the user to change the student's
	 *  birth date
	 *  @param birthDate : This is a String that should be formatted month/dd/year
	 */
	public void setBirthDate(String birthDate) {
		String[] tempDOBSplit = birthDate.split(" ");
		tempDOBSplit[0] = tempDOBSplit[0].substring(0, 3); // Trims the month to 3
		
		// recombines parts and saves to birthdate
		this.birthDate = tempDOBSplit[0] + " " + tempDOBSplit[1] + " " + tempDOBSplit[2];
	}
	
	/** Sets the student ID to a new ID, this is not for use by the user, this is
	 *  here because studentID can only generate if it knows what type of student
	 *  its generating for and this is not a valid type.
	 *  @param studentID : This is the new ID for the student, it should be a String
	 */
	public void setStudentID(String studentID) {this.studentID = studentID;}
	
	/** Sets the student's SmartCard to a new card
	 *  @param smartCard : This is the new SmartCard object for the student, it 
	 *  should be a SmartCard object
	 */
	public void setSmartCard(SmartCard smartCard) {this.smartCard = smartCard;}
}
