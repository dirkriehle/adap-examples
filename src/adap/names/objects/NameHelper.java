package adap.names.objects;

/**
 * A utility (helper) class for the Names package; mostly static methods.
 */
public class NameHelper {

	/**
	 * @MethodType conversion
	 * @MethodProperties convenience
	 */
	public static String asNameString(String[] components) {
		return asNameString(components, Name.DEFAULT_DELIMITER_CHAR, Name.DEFAULT_ESCAPE_CHAR);
	}
	
	/**
	 * @MethodType conversion
	 */
	public static String asNameString(String[] components, char delimiter, char escape) {
		StringBuffer sb = new StringBuffer();
		for (String component : components) {
			component = maskString(component, delimiter, escape);
			sb.append(component);
			sb.append(delimiter);
		}
		
		int sbOffset = (components.length == 0 ? 0 : 1);
		sb.setLength(sb.length() - sbOffset);
		
		return sb.toString();
	}
	
	/**
	 * @MethodType query
	 * @MethodProperties convenience
	 * @return Returns the number of components in (masked) name string
	 */
	public static int getNoComponents(String nameString) {
		return getNoComponents(nameString, Name.DEFAULT_DELIMITER_CHAR, Name.DEFAULT_ESCAPE_CHAR);
	}

	/**
	 * @MethodType query
	 * @MethodProperties convenience
	 * @return Returns the number of components in (masked) name string
	 */
	public static int getNoComponents(String nameString, char delimiter, char escape) {
		if (isNullOrEmptyString(nameString)) {
			return 0;
		}
		
		int result = 1;
		
		int endPos = findChar(nameString, 0, delimiter, escape, 1);
		for(int startPos = 0; endPos != -1; endPos = findChar(nameString, startPos, delimiter, escape, 1)) {
			startPos = endPos + 1;
			result += 1;
		}
		
		return result;
	}

	/**
	 * @MethodType command
 	 * @MethodProperties convenience
	 */
	public static String maskString(String component) {
		return maskString(component, Name.DEFAULT_DELIMITER_CHAR, Name.DEFAULT_ESCAPE_CHAR);
	}

	/**
	 * @MethodType command
	 * @return Returns name argument masked using control chars.
	 */
	public static String maskString(String component, char delimiter, char escape) {
		StringBuffer result = new StringBuffer();
		int size = component.length();
		for (int i = 0; i < size; i++) {
			char c = component.charAt(i);
			if (c == delimiter) {
				result.append(escape);
			} else if (c == escape) {
				result.append(escape);
			}
			result.append(c);
		}
		
		return result.toString();
	}
	
	/**
	 * @MethodType command
 	 * @MethodProperties convenience
	 */
	public static String unmaskString(String component) {
		return unmaskString(component, Name.DEFAULT_DELIMITER_CHAR, Name.DEFAULT_ESCAPE_CHAR);
	}

	/**
	 * @MethodType command
	 * @return Returns component stripped from masking control chars.
	 */
	public static String unmaskString(String component, char delimiter, char escape) {
		boolean escapeFlag = false; // flags escape char
		StringBuffer result = new StringBuffer();
		
		final int size = component.length();
		for (int i = 0; i < size; i++) {
			char c = component.charAt(i);
			if (!escapeFlag && (c == escape)) {
				escapeFlag = true;
				continue;
			}
			result.append(c);
			escapeFlag = false;
		}
		
		return result.toString();
	}

	/**
	 * @MethodType helper
	 * @return Returns position of nth char in string (indicating start of component).
	 */
	public static int findChar(String nameString, int startPos, char searched, char escape, int noOccurrences) {
		if (noOccurrences == 0) {
			return startPos;
		}
		
		for (int no = 0; no < noOccurrences; no++) {
			startPos = findNextChar(nameString, startPos, searched, escape);
			if (startPos == -1) {
				return -1;
			};
			startPos++;
		}
		
		return --startPos;
	}
	
	/**
	 * @MethodType helper
	 * @return Returns position of next char in string.
	 */
	protected static int findNextChar(String nameString, int startPos, char searched, char escape) {
		boolean escapeFlag = false;
		for (int i = startPos; i < nameString.length(); i++) {
			char c = nameString.charAt(i);
			if (!escapeFlag && c == escape) {
				escapeFlag = true;
				continue;
			} else if (!escapeFlag && c == searched) {
				return i;
			}
			escapeFlag = false;
		}
		
		return -1;
	}
	
	/**
	 * @MethodType query
	 * @MethodProperties convenience
	 */	
	protected static boolean isNullOrEmptyString(String nameString) {
		return (nameString == null) || nameString.equals("");
	}

}
