package adap.names.objects;

import java.util.Iterator;

/**
 * Abstract superclass for various Name implementations
 */
public abstract class AbstractName implements Name {

	/**
	 * @MethodType constructor
	 */
	protected AbstractName() {
		// do nothing
	}

	/**
	 * @MethodType getter
	 * @return Returns hashcode following java.lang.Object hashcode contract
	 */
	public int hashCode() {
		return asString().hashCode();
	}
	
	/**
	 * @MethodType boolean-query
	 * @Returns Returns true if other object equals this one
	 */
	public boolean equals(Object o) {
		// We follow the java.lang.Object contract here
		if ((o == null) || !(o instanceof Name)) {
			return false;
		}
		
		Name n = (Name) o;
		boolean result = doIsEqual(n);
		return result;
	}
	
	/**
	 * @MethodType boolean-query
	 * @MethodProperties primitive, inheritance-interface
	 */
	protected boolean doIsEqual(Name n) {
		int noComponents = getNoComponents();
		if (n.getNoComponents() != noComponents) {
			return false;
		}

		for (int i = 0; i < noComponents; i++) {
			if (!getComponent(i).equals(n.getComponent(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @MethodType conversion
	 * @MethodProperties convenience
	 */
	public String asString() {
		return asString(DEFAULT_DELIMITER_CHAR);
	}
	
	/**
	 *
	 */
	public String asString(char delimiter) {
		return NameHelper.asNameString(asStringArray(), delimiter, DEFAULT_ESCAPE_CHAR);
	}

	/**
	 * @MethodType conversion
	 */
	public String[] asStringArray() {
		int max = getNoComponents();
		String[] sa = new String[max];
		for (int i = 0; i < max; i++) {
			sa[i] = getComponent(i);
		}
		
		return sa;
	}
	
	/**
	 * @MethodType factory
	 * @return Returns iterator over components.
	 */
	public Iterator<String> iterator() {
		return new NameIterator(this);
	}
	
	/**
	 * @MethodType getter
	 * @MethodProperty inheritance-interface
	 * @return Returns number of components in name.
	 */
	public abstract int getNoComponents();

	/**
	 * @MethodType boolean-query
	 * @return Returns true, if Name has no components.
	 */
	public boolean isEmpty() {
		return getNoComponents() == 0;
	}
	
	/**
	 * @MethodType boolean-query
	 * @return Returns true if name has component at given index.
	*/
	public boolean hasComponent(int index) {
		return doHasComponent(index);
	}
	
	/**
	 * @MethodType boolean-query
	 * @MethodProperty inheritance-interface
	 */
	protected boolean doHasComponent(int index) {
		return (0 <= index) && (index < getNoComponents());
	}
	
	/**
	 * @MethodType getter
	 * @return Returns unmasked name component.
	 */
	public String getComponent(int index) {
		assertIsValidIndex(index);
		return doGetComponent(index);
	}
	
	/**
	 * @return Returns unmasked name component at given index.
	 */
	protected abstract String doGetComponent(int index);
	
	/**
	 * @MethodType setter
	 */
	public void setComponent(int i, String c) {
		assertIsValidIndex(i);
		assertIsNonNullArgument(c);
		int oldNoComponents = getNoComponents();
		
		doSetComponent(i, c);

		assert c.equals(getComponent(i)) : "postcondition failed";
		assert oldNoComponents == getNoComponents() : "postcondition failed";
	}

	/**
	 * @MethodType setter
	 * @MethodProperty primitive
	 */
	protected void doSetComponent(int i, String c) {
		doInsert(i, c);
		doRemove(i + 1);
	}
	
	/**
	 * @MethodType command
	 */
	public void insert(int i, String c) {
		assertIsValidIndex(i, getNoComponents() + 1);
		assertIsNonNullArgument(c);
		int oldNoComponents = getNoComponents();

		doInsert(i, c);
	
		assert (oldNoComponents + 1) == getNoComponents() : "postcondition failed";
	}
	
	/**
	 * @MethodType command
	 * @MethodProperty primitive, inheritance-interface
	 */
	protected abstract void doInsert(int i, String c);
	
	/**
	 * @MethodType command
	 */
	public void remove(int i) {
		assertIsValidIndex(i);
		int oldNoComponents = getNoComponents();
		
		doRemove(i);
		
		assert (oldNoComponents - 1) == getNoComponents() : "postcondition failed";
	}

	/**
	 * @MethodType command
	 * @MethodProperty primitive, inheritance-interface
	 */
	protected abstract void doRemove(int i);
	
	/**
	 * @MethodType assertion
	 */
	protected void assertIsValidIndex(int i) throws IndexOutOfBoundsException {
		assertIsValidIndex(i, getNoComponents());
	}
	
	/**
	 * @MethodType assertion
	 */
	protected void assertIsValidIndex(int i, int upperLimit) throws IndexOutOfBoundsException {
		if ((i < 0) || (i >= upperLimit)) {
			throw new IndexOutOfBoundsException("invalid index = " + i);
		}
	}
	
	/**
	 * @MethodType assertion
	 */
	protected void assertIsNonNullArgument(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("received null argument");
		}
	}
	
}
