import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MorseCodeConverter
{
	//Make a new MorseCodeTree data object
	private static MorseCodeTree morseCodeTree = new MorseCodeTree();

	//Empty Constructor
	public MorseCodeConverter()
	{

	}

	//Returns a string with the data in the Morse code tree traversed in LNR order
	//should have the result "h s v i f u e l r a p w j  b d x n c k y t z g q m o"
	//Extra space between the j and b due to the empty root
	public static String printTree()
	{
		String result = new String(); //String to hold the result
		ArrayList<String> morseCodeTreeList = morseCodeTree.toArrayList(); //Get the ArrayList from MorseCodeTree
		//Parse the ArrayList
		for(int i=0; i<morseCodeTreeList.size()-1; i++) //For every entry in the ArrayList except for the last entry
		{
			result = result + morseCodeTreeList.get(i) + " "; //Add to the result string with the format of data and a space
		}
		result = result + morseCodeTreeList.get(morseCodeTreeList.size()-1); //The very last entry gets added without a space
		return result;
	}

	//Return a decode English string when given a Morse code string
	public static String convertToEnglish(String code)
	{
		//Creates a Scanner
		Scanner inputScanner = new Scanner(code);
		//Stores the result
		String result = "";

		//As long as the input has lines
		while(inputScanner.hasNextLine())
		{
			result = result + stringSplitter(inputScanner.nextLine()); //Add to result the converted output line by line
			if(inputScanner.hasNextLine()) //If the scanner has a next line, add a new line
			{
				result = result + "\n"; //Add a new line to result
			}
			//Otherwise there is no more lines and no new line gets added
		}
		inputScanner.close(); //Close the scanner

		//Return the decoded string
		return result;
	}

	//Return a decode English string when given a Morse code File
	public static String convertToEnglish(File codeFile) throws FileNotFoundException
	{
		//Creates a Scanner with the File
		Scanner inputScanner = new Scanner(codeFile);
		//Stores the result
		String result = "";

		while(inputScanner.hasNextLine())
		{
			result = result + stringSplitter(inputScanner.nextLine()); //Add to result the converted output line by line
			if(inputScanner.hasNextLine()) //If the scanner has a next line
			{
				result = result + "\n"; //Add a new line to result
			}
		}
		//Otherwise there is no more lines and no new line gets added
		inputScanner.close();

		//Return the decoded string
		return result;
	}

	//This takes a single line String with Morse code "." and "-" and "/" characters, splits it to tokens, and returns the decoded String
	private static String stringSplitter(String stringToSplit)
	{
		//This splits the string when it encounters a space or "/" and keeps any "/" characters
		//Space characters are split into separate word/tokens and "/" characters are converted to new lines below
		String[] splitedString = stringToSplit.split("([ ]|(?<=/)|(?=/))");
		//This stores the result
		String result = "";

		for(int i=0; i<splitedString.length; i++) //For every single split up token
		{

			if(!splitedString[i].contains("/")) //If the token has no "/" characters
			{
				result = result + morseCodeTree.fetch(splitedString[i]); //Pass it to the Morse code decoder method and add it to result
			}
			else if(splitedString[i].equals("/")) //Otherwise the token has an "/" character and is a space
			{
				result = result + " "; //Add a space to the result String
			}
		}

		//Return the decoded string
		return result;
	}
}