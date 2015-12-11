package adap.names.values;

import java.util.*;

/**
 * A simple Factory class for creating and managing Name objects.
 * 
 */
public class NameFactory {

	/**
	 * @FieldType singleton
	 */
	protected static NameFactory instance;
	
	/**
	 * @return Returns singleton factory instance
	 */
	public static synchronized NameFactory getInstance() {
		if (instance == null) {
			instance = new NameFactory();
		}
		return instance;
	}
	
	/**
	 * Sets NameFactory instance; exists for subclasses to configure.
	 */
	public static synchronized void setInstance(NameFactory nf) {
		assert instance == null : "NameFactory instance already exists";
		instance = nf;
	}
	
	/**
	 * @FieldType instance
	 */
	protected Map<String, StringArrayName> allStringArrayNames = new HashMap<String, StringArrayName>();
	protected Map<String, StringName> allStringNames = new HashMap<String, StringName>();
	
	/**
	 * @MethodType factory
	 * @MethodProperties convenience
	 */
	public static Name getEmptyName() {
		return getInstance().getEmptyStringArrayName();
	}
		
	/**
	 * @MethodType factory
	 * @MethodProperties convenience
	 */
	public static Name getName(String[] components) {
		return getInstance().getStringArrayName(components);
	}
	
	/**
	 * @MethodType factory
	 * @return Returns new Name object using default implementation class.
	 */
	public StringArrayName getEmptyStringArrayName() {
		return getStringArrayName(new String[0]);
	}
	
	/**
	 * @MethodType factory
	 * @return Returns new Name object using default implementation class.
	 */
	public StringArrayName getStringArrayName(String[] components) {
		String  nameString = NameHelper.asNameString(components);
		StringArrayName result = allStringArrayNames.get(nameString);
		if (result == null) {
			synchronized (this) {
				result = allStringArrayNames.get(nameString);
				if (result == null) {
					result = new StringArrayName(components);
					allStringArrayNames.put(nameString, result);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * @MethodType factory
	 * @return Returns new Name object using compact representation.
	 */
	public StringName getEmptyStringName() {
		return getStringName(new String[0]);
	}
	
	/**
	 * @MethodType factory
	 * @return Returns new Name object using compact representation.
	 */
	public StringName getStringName(String[] components) {
		String  nameString = NameHelper.asNameString(components);
		StringName result = allStringNames.get(nameString);
		if (result == null) {
			synchronized (this) {
				result = allStringNames.get(nameString);
				if (result == null) {
					result = new StringName(components);
					allStringNames.put(nameString, result);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * @MethodType factory
	 * @return Returns new Name object using compact representation.
	 */
	public StringName getStringNameFromNameString(String nameString) {
		StringName result = allStringNames.get(nameString);
		if (result == null) {
			synchronized (this) {
				result = allStringNames.get(nameString);
				if (result == null) {
					result = new StringName(nameString);
					allStringNames.put(nameString, result);
				}
			}
		}
		
		return result;
	}
	
}