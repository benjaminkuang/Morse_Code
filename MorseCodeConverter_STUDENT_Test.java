import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class MorseCodeConverter_STUDENT_Test
{
	@Before
	public void setUp() throws Exception
	{

	}

	@After
	public void tearDown() throws Exception
	{

	}

	@Test
	public void testPrintTree()
	{
		//Test that printTree() delivers the correct String result
		String correct = "h s v i f u e l r a p w j  b d x n c k y t z g q m o";
		String result = MorseCodeConverter.printTree();
		assertEquals(correct, result);
	}

	@Test
	public void testEnglishString()
	{
		String result;
		//Normal test string
		String test1 = "-- --- .-. ... . / -.-. --- -.. . / .. ... / .- / -- . - .... --- -.. / ..- ... . -.. / .. -. / - . .-.. "
				+ ". -.-. --- -- -- ..- -. .. -.-. .- - .. --- -. / - --- / . -. -.-. --- -.. . / - . -..- - / -.-. .... .- .-. "
				+ ".- -.-. - . .-. ... / .- ... / ... - .- -. -.. .- .-. -.. .. --.. . -.. / ... . --.- ..- . -. -.-. . ...";
		String correct1 = "morse code is a method used in telecommunication to encode text characters as standardized sequences";
		result = MorseCodeConverter.convertToEnglish(test1);
		assertEquals(correct1, result);

		//Shorter normal test string
		String test2 = "-- --- .-. ... . / -.-. --- -.. .";
		String correct2 = "morse code";
		result = MorseCodeConverter.convertToEnglish(test2);
		assertEquals(correct2, result);

		//Test string with odd "/" input and some new lines but still correct input
		String test3 = "/ //-- --- / .-. ... .//\n\n\n//-.-. ---///-.. ./ //";
		String correct3 = "   mo rse  \n\n\n  co   de   ";
		result = MorseCodeConverter.convertToEnglish(test3);
		assertEquals(correct3, result);

		//Test strings with incorrect inputs
		//Foreign characters
		String test4 = "-- --=- .<-. ..k. a. / -.[-. --'- -.|. .";
		//Should reject letters with bad characters and keep letters with good characters
		String correct4 = "m e";
		result = MorseCodeConverter.convertToEnglish(test4);
		assertEquals(correct4, result);

		//Multiple five character inputs
		String test5 = "-.-.-.- --.-- ..-.. ..... --.-- / .-.-. .---. // ..-.. --...";
		//Since every letter has five or more characters it should give the spaces for the /
		String correct5 = "   ";
		result = MorseCodeConverter.convertToEnglish(test5);
		assertEquals(correct5, result);

		//The four, four character tree locations with no letter
		String test6 = "..--";
		String test7 = ".-.-";
		String test8 = "---.";
		String test9 = "----";
		//Should give a blank output
		String correct = "";
		result = MorseCodeConverter.convertToEnglish(test6);
		assertEquals(correct, result);
		result = MorseCodeConverter.convertToEnglish(test7);
		assertEquals(correct, result);
		result = MorseCodeConverter.convertToEnglish(test8);
		assertEquals(correct, result);
		result = MorseCodeConverter.convertToEnglish(test9);
		assertEquals(correct, result);

		//One four letter run to prove the four character letter works normally
		String test10 = ".-..";
		String correct10 = "l";
		result = MorseCodeConverter.convertToEnglish(test10);
		assertEquals(correct10, result);
	}

	@Test
	public void testEnglishFile() throws FileNotFoundException
	{
		//Grab the input file
		File inputFile = null;
		JFileChooser chooser = new JFileChooser();
		int status;
		chooser.setDialogTitle("Select Input File - " + "morse_code.txt");
		status = chooser.showOpenDialog(null);
		if(status == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				inputFile = chooser.getSelectedFile();
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "There is a problem with this file", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		//Normal run
		String correct1 = "morse code is a method used in telecommunication to encode text characters as\n"
				+ " standardized sequences of two different signal durations called dots and dashes or dits and dahs";
		String result = MorseCodeConverter.convertToEnglish(inputFile);
		assertEquals(correct1,result);
	}
}