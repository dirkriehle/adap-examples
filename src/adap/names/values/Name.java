package adap.names.values;

import java.util.Iterator;

/**
 * An interface for homogeneous (same type of components) names.
 *
 */
public interface Name extends Iterable<String> {
	
	/**
	 * Delimiter and escape chars
	 * @FieldType constant
	 */
	public static final char DEFAULT_DELIMITER_CHAR = '#';
	public static final char DEFAULT_ESCAPE_CHAR = '\\';
	
	/**
	 * @MethodType conversion
	 * @MethodProperties convenience
	 * @return Returns name as single string using default delimiter and escape chars.
	 */
	public String asString();
	
	/**
	 * @MethodType conversion
	 * @return Returns name as single string using delimiter char to separate components.
	 */
	public String asString(char delimiter);
	
	/**
	 * @MethodType conversion
	 * @return Returns name components as a string array.
	 */
	public String[] asStringArray();
	
	/**
	 * @MethodType factory
	 * @return Returns iterator over components.
	 */
	public Iterator<String> iterator();
	
	/**
	 * @MethodType boolean-query
	 * @return Returns true, if Name has no components.
	 */
	public boolean isEmpty();
	
	/**
	 * @MethodType getter
	 * @return Returns number of components in Name.
	 */
	public int getNoComponents();
	
	/**
	 * @MethodType boolean-query
	 * @return Returns true if name has component at given index.
	 */
	public boolean hasComponent(int i);
	
	/**
	 * @MethodType getter
	 * @return Returns unmasked name component.
	 */
	public String getComponent(int i);
	
	/**
	 * @MethodType setter
	 * @return Returns new name with changed component
	 */
	public Name setComponent(int i, String c);
	
	/**
	 * @MethodType command
	 * @return Returns new name with inserted component
	 */
	public Name insert(int i, String c);
	
	/**
	 * @MethodType command
	 * @return Returns new name with component removed
	 */
	public Name remove(int i);

}
