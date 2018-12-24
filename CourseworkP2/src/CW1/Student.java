package CW1;

import java.util.ArrayList;

/** Student is the interface that the abstract class 'Students' will define and its
 *  children will define the classes Students leaves out. <p>This is an interface to
 *  aid the creation of higher level objects such as Students. It sets out some
 *  methods the classes that implement is will have.</p>
 *  @author Raven Duffy B7044359
 */
public interface Student {
	/** This method specifics that this method should exist in the children of any
	 *  class that implements it and says that this should return a String
	 *  @return This should return a String that holds a StudentID.
	 */
	public String getStudentID();
	
	/** This method specifics that this method should exist in the children of any
	 *  class that implements it and says that this should return a String
	 *  @return This should return a String that holds the Student's SmartCardNumber
	 */
	public String getSmartCardNum();
	
	/** This method specifics that this method should exist in the children of any
	 *  class that implements it and says that this should return a String
	 *  @return This should return a String that holds a Student's date of birth.
	 */
	public String getBirthDate();
	
	/** This method specifics that this method should exist in the children of any
	 *  class that implements it and says that this should return a String
	 *  @return This should return a String that holds a supervisor.
	 */
	public String getSupervisor();
	
	/** This method specifics that this method should exist in the children of any
	 *  class that implements it and says that this should return an ArrayList of
	 *  the type Module.
	 *  @return This should return an ArrayList that holds a list of Module objects.
	 */
	public ArrayList<Module> getModules();
}
