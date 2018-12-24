package CW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/** The PostGradR class is a class to represent a post graduate student studying at
 *  the university.
 *  <p>It extends Students so that it can inherit the methods created in Student and
 *  defined in Students.</p>
 *  <p>This class has two separate constructors: one to create a new PostgradR
 *  object using just the student's name and their birthdate (this is the
 *  constructor the user sees) and one to create a new PostgradR object using their
 *  birth date, studentID, smartCard object and their supervisor (this is used when
 *  recreating the object from the SmartCardRecords and StudentRecords files)</p>
 *  @author Raven Duffy B7044359
 */
public class PostgradR extends Students{
	
	private String supervisor;
	
	/** This constructor has two inputs: the student's name as a String and the 
	 *  student's birth date as a string. It uses these entries to set the name and
	 *  birthDate variables in the Students class it inherits from.
	 *  <p>After these are set it creates a new studentID object to set the 
	 *  studentID variable initalised in the Students class this inherits from. Then
	 *  it creates a new SmartCard object and assigns it to this object.
	 *  <p>Finally, the object is assigned a supervisor using the assignSupervisor 
	 *  method. </p>
	 *  @param name : This should be a String representation of the student's FIRST
	 *  			  and LAST names only.
	 *  @param birthDate : This should be a String representation of the student's
	 *  				   birthdate in the format month/dd/year
	 *  @throws FileNotFoundException
	 */
	public PostgradR(String name, String birthDate) throws FileNotFoundException {
		// inherits the constructor of Students
		super(name, birthDate);
		
		// creates new studentID and SmartCard
		this.setStudentID(new StudentID(this).toString());
		this.setSmartCard(new SmartCard(this));
		
		// assigns a new supervisor
		supervisor = assignSupervisor();
	}
	
	/** This constructor has four inputs: the student's birthDate as a String, the
	 *  student's ID as a String, a SmartCard object and the student's supervisor. 
	 *  <p>This is not something the user will ever interact with, instead it should
	 *  be used to recreate PostgradR objects based on the text files: 
	 *  SmartCardRecords and StudentRecords</p>
	 *  @param birthDate : This should be a String representation of the student's
	 *  				   birthdate in the format month/dd/year
	 *  @param studentID : This should be a String holding the studentID
	 *  @param smartCard : This should be created by reading the SmartCardRecords
	 *  				   file. 
	 *  @param supervisor : This is the supervisor that the student will have. It 
	 *  					should be a String.
	 *  @throws FileNotFoundException
	 */
	public PostgradR(String birthDate, String studentID, SmartCard smartCard, String supervisor) throws FileNotFoundException {
		// inherits the secondary constructor of Students
		super(birthDate, studentID, smartCard);
		
		// sets the supervisor to the inputed one
		this.supervisor = supervisor;
	}
	
	/** This returns a String representation of the supervisor String saved in this
	 *  object.
	 *  @return This will return the supervisor String
	 */
	public String getSupervisor() {
		return supervisor;
	}
	
	/** This method will pick a new supervisor for the research student by checking
	 *  the previous postgrads to see what supervisor they're assigned. Then it
	 *  picks the next one in the list.
	 *  @return This will return one of the four supervisor Strings (either: Mr.
	 *  Patterson, Mr. Aimers, Ms. Blanks or Ms. Wilson).
	 *  @throws FileNotFoundException
	 */
	private String assignSupervisor() throws FileNotFoundException {
		// This will create a new File object named StudentRecords based on the
		// original file called StudentRecords. Then the Scanner SRReader is
		// assigned to the file.
		File StudentRecords = new File("StudentRecords");
		Scanner SRReader = new Scanner(StudentRecords);
		
		// The array of possible supervisors is created
		String[] supervisorList = {"Mr. Patterson", "Mr. Aimers", "Ms. Blanks", "Ms. Wilson"};
		
		int superPos = 0; // This keeps track of the number of supervisors found
		// This scans through the whole StudentRecords file and looks for any of the
		// supervisors in the supervisorList array. If it finds them it increments
		// superPos by 1.
		while (SRReader.hasNextLine()) {
			String line = SRReader.nextLine();
			for (int superIter = 0; superIter < supervisorList.length; superIter++) {
				if (line.equals(supervisorList[superIter]))
					superPos++;
			}
		}
		
		// The superPos variable is reduced until it equals 0 - 3 so that one of the
		// supervisors in the array can be selected
		while (superPos > 3) {superPos -= 4;}
		SRReader.close();
		
		return supervisorList[superPos];
	}

	/** This an inherited method from the Student interface. In this case it throws
	 *  an exception because Research students don't take modules.
	 */
	public ArrayList<Module> getModules() {
		throw new IllegalArgumentException("This student type does not have modules");
	}

}
