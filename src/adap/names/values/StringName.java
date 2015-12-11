package adap.names.values;

/**
 * A Name implementation that uses a single strong for storing name components.
 * The benefit of this implementation is that it is as compact as possible.
 * The downside is that each name component needs to be masked (from the delimiter char).
 */
public class StringName extends AbstractName {

	/**
	 * @FieldType implementation
	 */
	protected String name;
	
	/**
	 * @FieldType implementation
	 * @FieldProperty caching
	 */
	protected int noComponents;
	
	/**
	 * Constructs empty string name.
	 */
	protected StringName() {
		name = "";
		noComponents = 0;
	}

	/**
	 * Initializes name from name-string
	 * @MethodType constructor
	 */
	protected StringName(String nameString) {
		name = nameString;
		noComponents = NameHelper.getNoComponents(nameString);
	}
	
	/**
	 * Copies over fields from other StringName.
	 * @MethodType constructor
	 */
	protected StringName(StringName newName) {
		name = newName.name;
		noComponents = newName.noComponents;
	}
	
	/**
	 * Creates single name string from string array.
	 * @MethodType constructor
	 */
	protected StringName(String[] components) {
		name = NameHelper.asNameString(components);
		noComponents = NameHelper.getNoComponents(name);
	}
	
	/**
	 * @MethodType boolean-query
	 * @MethodProperties primitive, inheritance-interface
	 */
	protected boolean doIsEqual(Name n) {
		if (!(n instanceof StringName)) {
			return super.doIsEqual(n);
		}
		
		StringName stringName = (StringName) n;
		boolean result = name.equals(stringName.name);
		return result;
	}
	
	/**
	 * @return Returns number of components in name.
	 */
	public int getNoComponents() {
		return noComponents;
	}
	
	/**
	 * @return Returns unmasked name component.
	 */
	protected String doGetComponent(int i) {
		int startPos = getStartPositionOfComponent(i);
		int endPos = getEndPositionOfComponent(i);
		String maskedComponent = name.substring(startPos, endPos);
		return NameHelper.unmaskString(maskedComponent);
	}
	
	/**
	 * Inserts component at given index
	 */
	protected AbstractName doInsert(int index, String component) {
		StringBuffer sb = new StringBuffer(name);
		component = NameHelper.maskString(component);

		if (noComponents == 0) {
			sb.append(component);
		} else if (index == noComponents) {
			sb.append(DEFAULT_DELIMITER_CHAR);
			sb.append(component);
		} else {
			component = component + DEFAULT_DELIMITER_CHAR;
			int startPos = getStartPositionOfComponent(index);
			sb.insert(startPos, component);
		}
	
		return getName(sb.toString());
	}
	
	/**
	 * Removes component at given index.
	 */
	protected AbstractName doRemove(int index) {
		int startPos = getStartPositionOfComponent(index);
		int endPos = getEndPositionOfComponent(index);
		StringBuffer sb = new StringBuffer();
	
		if (index == 0) {
			int endPosOffset = (endPos >= name.length() ? 0 : 1);
			sb.append(name.substring(endPos + endPosOffset, name.length()));
		} else if (index == (noComponents - 1)) {
			int startPosOffset = (startPos >= name.length() ? 0 : 1);
			sb.append(name.substring(0, startPos - startPosOffset));
		} else {
			sb.append(name.substring(0, startPos));
			sb.append(name.substring(endPos + 1, name.length()));
		}

		return getName(sb.toString());
	}
	
	/**
	 * @MethodType factory
	 */
	protected AbstractName getName(String nameString) {
		NameFactory factory = NameFactory.getInstance();
		return factory.getStringNameFromNameString(nameString);
	}

	/**
	 * @MethodType helper
	 * @return Returns start position of indexed component in name string.
	 */
	protected int getStartPositionOfComponent(int index) {
		if (index == 0) {
			return 0;
		}
		
		int pos = NameHelper.findChar(name, 0, Name.DEFAULT_DELIMITER_CHAR, Name.DEFAULT_ESCAPE_CHAR, index);
		if (pos == -1) {
			throw new IllegalArgumentException("invalid index = " + index);
		}

		return pos + 1;
	}
	
	/**
	 * @MethodType helper
	 * @return Returns end position of indexed component in name string.
	 */
	protected int getEndPositionOfComponent(int index) {
		int lastComponentIndex = getNoComponents() - 1;
		if (index == lastComponentIndex) {
			return name.length();
		}
		
		return getStartPositionOfComponent(index + 1) - 1;
	}
	
}
	