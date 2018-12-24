package CW1;

/** Name is an abstract class that the Students class and it's children inherit
 *  from. It is an abstract class because there are no instances of it ever made
 *  by itself.<p>It has a constructor to that sets the name String to whatever is 
 *  entered. It also allows for itself to be changed, through the use of set name, 
 *  and returned as a String, through the use of get name</p>
 *  @author Raven Duffy B7044359
 */

public abstract class Name {
	private String name;
	
	/** This constructor takes one parameter of a String called name. This string
	 *  is used to set the name variable contained in Name
	 *  @param name : This will set the variable name and has to be <b>ONLY</b> the
	 *  			  first and last name
	 */
	public Name(String name) {
		this.name = name;
	}
	
	/** This takes a new name String and replaces the current Name variable with
	 *  the new input.
	 *  @param name : This will reset the variable name and has to be <b>ONLY</b> 
	 *  			  the first and last name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** This will return the current value of the Name variable in the form of a 
	 *  String.
	 *  @return This will return a String representation of the current Name
	 *  		  variable
	 */
	public String getName() {
		return name;
	}
}
