package CW1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UndergradTest {

	// checks to see if the max credits returns the expected Integer of 180
		@Test
		void testGetMaxCredits() throws FileNotFoundException {
			ArrayList<Module> modules = new ArrayList<Module>();
			modules.add(new Module("CSC1021,Programming II,20"));
			modules.add(new Module("CSC1029,Fill,100"));
			
			Undergrad test = new Undergrad("Raven Duffy", "Mar 15 1996", modules);
			int output = test.getMaxCredits();
			assertEquals(120, output);
		}

		// checks to see if the first module is saved correctly into the list by checking
		// the toString of the module
		@Test
		void testGetModule1() throws FileNotFoundException {
			ArrayList<Module> modules = new ArrayList<Module>();
			modules.add(new Module("CSC1021,Programming II,20"));
			modules.add(new Module("CSC1029,Fill,100"));
			
			Undergrad test = new Undergrad("Raven Duffy", "Mar 15 1996", modules);
			String output = test.getModules().get(0).toString();
			assertEquals("CSC1021,Programming II,20", output);
		}
		
		// checks to see if the all the modules are saved correctly by checking to see
		// if the original modules List equals the one saved
		@Test
		void testGetModules() throws FileNotFoundException {
			ArrayList<Module> modules = new ArrayList<Module>();
			modules.add(new Module("CSC1021,Programming II,20"));
			modules.add(new Module("CSC1029,Fill,100"));
			
			Undergrad test = new Undergrad("Raven Duffy", "Mar 15 1996", modules);
			ArrayList<Module> output = test.getModules();
			assertEquals(modules, output);
		}

}
