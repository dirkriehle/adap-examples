package adap.names.objects;

import java.util.*;

/**
 * An iterator class for Name objects.
 * Remove is not supported. Iteration is not stable.
 *
 * @DesignPattern Iterator
 * @DesignPatternParticipant Iterator
 */
public class NameIterator implements Iterator {

	/**
	 * @FieldType implementation state
	 */
	private int index;
	private Name name;
	
	/**
	 *
	 */
	protected NameIterator() {
		// do nothing
	}
	
	/**
	 *
	 */
	public NameIterator(Name newName) {
		this();
		name = newName;
		index = 0;
	}
	
	/**
	 * @MethodType boolean-query
	 */
	public boolean hasNext() {
		return index < name.getNoComponents();
	}

	/**
	 * @MethodType getter
	 */
	public String next() throws NoSuchElementException {
		assertHasNext();
		return name.getComponent(index++);
	}

	/**
	 * @MethodType assertion
	 */
	protected void assertHasNext() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
	}

	/**
	 *
	 */
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("NameIterator does not support remove!");
	}

}

