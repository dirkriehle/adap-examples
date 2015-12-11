package adap.names.objects;

import org.junit.*;
import java.util.*;

/**
 * 
 */
public class NameTest {
	
	/**
	 * 
	 */
	public static final String[] helloWorld = { "hello", "world", "!" };
	public static final String[] charMalaise =  { "\\###\\", "#", "\\", "\\\\\\\\#", "#" };
	
	/**
	 * 
	 */
	protected Name defaultName;
	protected Name emptyDefaultName;
	protected Name compactName;
	protected Name emptyCompactName;
	
	@Test
	public void testEquality() {
		initNames(helloWorld);
		Assert.assertEquals(defaultName, compactName);

		initNames(charMalaise);
		Assert.assertEquals(defaultName, compactName);
		
		initEmptyNames();
		Assert.assertEquals(emptyDefaultName, emptyCompactName);
	}

	@Test
	public void testEqualityByIteration() {
		initNames(helloWorld);
		assertEqualityByIteration(defaultName, helloWorld);
		assertEqualityByIteration(compactName, helloWorld);

		initNames(charMalaise);
		assertEqualityByIteration(defaultName, charMalaise);
		assertEqualityByIteration(compactName, charMalaise);
	}		
		
	protected void assertEqualityByIteration(Name name, String[] expected) {
		Iterator<String> iterator = name.iterator();
		for (int i = 0; i < name.getNoComponents(); i++) {
			Assert.assertEquals(expected[i], name.getComponent(i));
			Assert.assertEquals(expected[i], iterator.next());			
		}
	}
	
	@Test
	public void testInsertRemoveReplace() {
		initEmptyNames();
		initNames(helloWorld);
		
		assertInsertRemoveReplace(helloWorld, emptyDefaultName, defaultName);
		assertInsertRemoveReplace(helloWorld, emptyCompactName, defaultName);
	}
		
	protected void assertInsertRemoveReplace(String[] source, Name target, Name expected) {
		for (int i = 0; i < source.length; i++) {
			target.insert(i, source[i]);
		}
		Assert.assertEquals(expected, target);

		for (int j = 0; j < source.length; j++) {
			Assert.assertEquals(source[j], target.getComponent(0));
			target.remove(0);
		}
	}
	
	protected void initNames(String[] arg) {
		defaultName = new StringArrayName(arg);
		compactName = new StringName(arg);		
	}
	
	protected void initEmptyNames() {
		emptyDefaultName = new StringArrayName();
		emptyCompactName = new StringName();
	}

}
