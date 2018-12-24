package CW1;

/** Module is an <b>immutable</b> class that is used to hold information about 
 *  the modules the student is taking. <p>It has two separate constructors: one that
 *  takes three String values and one that takes one String. The reasoning for the
 *  different constructors is this: one, will get its information from user input and
 *  one will get its information from the StudentRecords file which holds some of the
 *  information about each student including their modules.</p>
 *  @author Raven Duffy B7044359
 */
public final class Module {
	
	private final String courseCode;
	private final String courseName;
	private final String creditValue;
	
	/** This constructor takes three values and sets the respective String value in
	 *  the class based on the inputs
	 *  @param courseCode : Course code goes here, this should be a string and should
	 *  					be formatted like so "CSC1022"
	 *  @param courseName : The course name goes here, this should be a string and
	 *  					can have any format. However, there <b>CAN NOT</b> be any 
	 *  					commas in it
	 *  @param creditValue : This is the number of credits the course is worth. This
	 *  					 is an should be an integer as it will be parsed from a
	 *  					 String to and int later in the code
	 */
	public Module (String courseCode, String courseName, String creditValue) {
		// Because this class is immutable the String entries are checked to make 
		// sure that they're not null. An appropriate exception will be thrown
		// otherwise.
		if (courseCode == null)
			throw new IllegalArgumentException("Course Code not entered");
		if (courseName == null)
			throw new IllegalArgumentException("Course Name not entered");
		if (creditValue == null)
			throw new IllegalArgumentException("Credit Value not entered");
		
		// This sets the courseCode, courseName and creditValue variables using the
		// user's entries
		this.courseCode = new String(courseCode);
		this.courseName = new String(courseName);
		this.creditValue = creditValue;
	}
	
	/** This constructor only takes one String value and splits it into 3 parts to be
	 *  saved separately. 
	 *  @param courseInfo : This is a combination of all the needed entries - courseCode,
	 *  					courseName and creditValue. These are all separated by commas
	 *  					which means that like before non of parts should have commas
	 */
	public Module (String courseInfo) {
		// This splits the user's entry into three parts and saves in into a String array
		String[] courseInfoA = courseInfo.split(","); 
		
		// Because this class is immutable each part of the array is checked to see if it
		// is null. If any are an exception is thrown
		if (courseInfoA[0] == null || courseInfoA[1] == null || courseInfoA[2] == null)
			throw new IllegalArgumentException("Invalid Format");
		
		// This sets the courseCode, courseName and creditValue variables using the
		// user's entry
		this.courseCode = new String(courseInfoA[0]);
		this.courseName = new String(courseInfoA[1]);
		this.creditValue = courseInfoA[2];
	}
	
	/** This overrides the toString method for this method to return the course code, 
	 *  course name and credit value separated by commas. It returns new String objects
	 *  to make the class more defensive.
	 */
	public String toString() {
		return (new String(courseCode) + "," + new String(courseName) + "," + new String(creditValue));
	}
	
	/** This returns the number of credits this module is worth, after checking to make
	 *  sure it actually is a number. If it isn't an exception is thrown.
	 *  @return An Integer representation of the creditValue saved in the class
	 */
	public int getcreditValue() {
		int creditValueInt;
		try {
			creditValueInt = Integer.parseInt(creditValue);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("The credit value should be a number");
		}
		return (creditValueInt);
	}
}
