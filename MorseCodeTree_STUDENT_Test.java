import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class MorseCodeTree_STUDENT_Test
{
	MorseCodeTree morseTree;

	@Before
	public void setUp() throws Exception
	{
		morseTree = new MorseCodeTree();
	}

	@After
	public void tearDown() throws Exception
	{
		morseTree = null;
	}

	@Test
	public void testInsert()
	{
		//Try an insert with bad input, nothing should happen
		morseTree.insert("-hello.-", "Bad");

		//Try inserting something that will not insert
		morseTree.insert("--.--", "Incorrect");
		//Attempt to access, input validation will reject it with an empty string
		assertEquals("", morseTree.fetch("--.--"));
		//Check for null pointer exception when it is fetched directly
		try
		{
			morseTree.fetchNode(morseTree.getRoot(), "--.--");
		}
		catch(NullPointerException e)
		{
			assertTrue("Successfully threw a NullPointerExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides a NullPointerExcepetion",false);
		}

		//Insert it directly
		morseTree.addNode(morseTree.getRoot(), "--.--", "Incorrect");
		//Attempt to access, input validation will still reject it with an empty string
		assertEquals("", morseTree.fetch("--.--"));
		//Access it directly
		assertEquals("Incorrect", morseTree.fetchNode(morseTree.getRoot(), "--.--"));

		//Test a super deep insert directly
		morseTree.addNode(morseTree.getRoot(), ".-.--.-.--.-.--", "Deep");
		morseTree.addNode(morseTree.getRoot(), ".-.-..-.-..-.-.", "DeepAlso");
		morseTree.addNode(morseTree.getRoot(), "-.--.--.-.--.--.-.--.--.-", "SuperDeep");
		//Attempt to access, input validation will still reject it with an empty string
		assertEquals("", morseTree.fetch(".-.--.-.--.-.--"));
		assertEquals("", morseTree.fetch(".-.-..-.-..-.-."));
		assertEquals("", morseTree.fetch("-.--.--.-.--.--.-.--.--.-"));
		//Access it directly
		assertEquals("Deep", morseTree.fetchNode(morseTree.getRoot(), ".-.--.-.--.-.--"));
		assertEquals("DeepAlso", morseTree.fetchNode(morseTree.getRoot(), ".-.-..-.-..-.-."));
		assertEquals("SuperDeep", morseTree.fetchNode(morseTree.getRoot(), "-.--.--.-.--.--.-.--.--.-"));
	}

	@Test
	public void testFetch()
	{
		//Some normal fetches
		String test1 = ".-";
		String test2 = "-...";
		String test3 = "-.-.";
		String test4 = "...-";
		assertEquals("a", morseTree.fetch(test1));
		assertEquals("b", morseTree.fetch(test2));
		assertEquals("c", morseTree.fetch(test3));
		assertEquals("v", morseTree.fetch(test4));
		//Normal direct fetches
		assertEquals("a", morseTree.fetchNode(morseTree.getRoot(), test1));
		assertEquals("b", morseTree.fetchNode(morseTree.getRoot(), test2));
		assertEquals("c", morseTree.fetchNode(morseTree.getRoot(), test3));
		assertEquals("v", morseTree.fetchNode(morseTree.getRoot(), test4));

		//The four, four character tree locations with no letter
		String test5 = "..--";
		String test6 = ".-.-";
		String test7 = "---.";
		String test8 = "----";
		//Incorrect inputs
		String test9 = "helloworld";
		String test10 = "--/--";
		//Should give a blank outputs
		assertEquals("", morseTree.fetch(test5));
		assertEquals("", morseTree.fetch(test6));
		assertEquals("", morseTree.fetch(test7));
		assertEquals("", morseTree.fetch(test8));
		assertEquals("", morseTree.fetch(test9));
		assertEquals("", morseTree.fetch(test10));

		//Check for a null pointer exception four character tree locations with no letter are fetched directly
		try
		{
			morseTree.fetchNode(morseTree.getRoot(), test5);
		}
		catch(NullPointerException e)
		{
			assertTrue("Successfully threw a NullPointerExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides a NullPointerExcepetion",false);
		}

		//Check for a null pointer exception four character tree locations with no letter are fetched directly
		try
		{
			morseTree.fetchNode(morseTree.getRoot(), test6);
		}
		catch(NullPointerException e)
		{
			assertTrue("Successfully threw a NullPointerExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides a NullPointerExcepetion",false);
		}

		//Check for a null pointer exception four character tree locations with no letter are fetched directly
		try
		{
			morseTree.fetchNode(morseTree.getRoot(), test7);
		}
		catch(NullPointerException e)
		{
			assertTrue("Successfully threw a NullPointerExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides a NullPointerExcepetion",false);
		}

		//Check for a null pointer exception four character tree locations with no letter are fetched directly
		try
		{
			morseTree.fetchNode(morseTree.getRoot(), test8);
		}
		catch(NullPointerException e)
		{
			assertTrue("Successfully threw a NullPointerExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides a NullPointerExcepetion",false);
		}
	}

	@Test
	public void testDelete()
	{
		//This operation is unsupported so this will throw the exception
		try
		{
			morseTree.delete("");
		}
		catch(UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides a UnsupportedOperationException",false);
		}
	}

	@Test
	public void testUpdate()
	{
		//This operation is unsupported so this will throw the exception
		try
		{
			morseTree.update();
		}
		catch(UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides a UnsupportedOperationException",false);
		}
	}

	@Test
	public void testPrintTree()
	{
		//When the ArrayList is toString-ed it should look like this "h s v i f u e l r a p w j  b d x n c k y t z g q m o" in array form
		String result = "[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o]";
		ArrayList<String> tree = morseTree.toArrayList();
		assertEquals(result, tree.toString());
	}
}