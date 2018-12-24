package CW1;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/** The PostgradT class is used to represent post graduate students (that are still 
 *  being taught) at the university.
 *  <p>It extends Students so that it can inherit the methods created in Student and
 *  defined in Students.</p>
 *  <p>This class has two separate constructors: one to create a new PostgradT
 *  object using just the student's name, their birthdate and a list of modules 
 *  (this is the constructor the user sees) and one to create a new PostgradT object
 *  using their birth date, studentID, smartCard object and a list of their modules 
 *  (this is used when recreating the object from the SmartCardRecords and 
 *  StudentRecords files).</p>
 *  @author Raven Duffy B7044359
 */

public class PostgradT extends Students{
	
	// This is the number of credits the Student must have 
	private int maxCredits;
	// This is the list that will hold the modules the student takes
	private ArrayList<Module> modules = new ArrayList<Module>();

	/** This constructor has two inputs: the student's name as a String and the 
	 *  student's birth date as a string. It uses these entries to set the name and
	 *  birthDate variables in the Students class it inherits from.
	 *  <p>After these are set it creates a new studentID object to set the 
	 *  studentID variable initalised in the Students class this inherits from. Then
	 *  it creates a new SmartCard object and assigns it to this object.
	 *  <p>Finally, the object's modules are set to equal to equal the modules list. </p>
	 *  @param name : This should be a String representation of the student's FIRST
	 *  			  and LAST names only.
	 *  @param birthDate : This should be a String representation of the student's
	 *  				   birthdate in the format month/dd/year
	 *  @param modules : This should be an ArrayList of type Module holding the
	 *  				modules that student is taking throughout the year
	 *  @throws FileNotFoundException
	 */
	public PostgradT(String name, String birthDate, ArrayList<Module> modules) throws FileNotFoundException {
		// inherits the constructor from Students
		super(name, birthDate);
		
		// creates a new studentID and SmartCard
		this.setStudentID(new StudentID(this).toString());
		this.setSmartCard(new SmartCard(this));
		
		// sets the modules list to equal the input
		this.modules = modules;
		
		// sets the maxCredits to 180
		maxCredits = 180;
	}
	
	/** This constructor has four inputs: the student's birthDate as a String, the
	 *  student's ID as a String, a SmartCard object and the student's modules list. 
	 *  <p>This is not something the user will ever interact with, instead it should
	 *  be used to recreate PostgradT objects based on the text files: 
	 *  SmartCardRecords and StudentRecords</p>
	 *  @param birthDate : This should be a String representation of the student's
	 *  				   birthdate in the format month/dd/year
	 *  @param studentID : This should be a String holding the studentID
	 *  @param smartCard : This should be created by reading the SmartCardRecords
	 *  				   file. 
	 *  @param modules : These are the modules that the student will be taking. It 
	 *  				 should be a String.
	 *  @throws FileNotFoundException
	 */
	public PostgradT(String birthDate, String studentID, SmartCard smartCard, ArrayList<Module> modules) throws FileNotFoundException {
		super(birthDate, studentID, smartCard);
		
		this.modules = modules;
		
		maxCredits = 180;
	}

	/** This will return the required number of credits needed
	 *  @return The necessary number of credits needed as an Integer
	 */
	public int getMaxCredits() {return maxCredits;}
	
	/** This will return the current list of modules
	 *  @return The current list of modules the student is taking as an ArrayList of
	 *  		Module objects
	 */
	public ArrayList<Module> getModules() {return modules;}
	
	/** This will add a new Module object to the list of Module objects
	 *  @param module : This should be a Module object
	 */
	public void addModule(Module module) {modules.add(module);}
	
	/** This will remove a Module object from the list of Module objects
	 *  @param module : This should be an Integer specifying the position of the 
	 *  				module to remove
	 */
	public void removeModule(int pos) {modules.remove(pos);}
	
	
	/** This will throw an exception because this student should not have an advisor
	 */
	public String getSupervisor() {
		throw new IllegalArgumentException("This type of student cannot have a supervisor.");
	}

}
