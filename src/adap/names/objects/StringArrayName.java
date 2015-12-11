package adap.names.objects;

import java.util.Arrays;

/**
 * A Name implementation that uses a String[] for storing name components.
 */
public class StringArrayName extends AbstractName {

	/**
	 * @FieldType implementation
	 */
	protected String[] components;
	
	/**
	 * Creates empty StringArrayName
	 * @MethodType constructor
	 */
	public StringArrayName() {
		components = new String[0];
	}
	
	/**
	 * Makes copy of parameters so that clients don't have to.
	 * @MethodType constructor
	 */
	public StringArrayName(String[] args) {
		components = Arrays.copyOf(args, args.length);
	}
	
	/**
	 * @return Returns number of components in name.
	 */
	public int getNoComponents() {
		return components.length;
	}
	
	/**
	 * @return Returns unmasked name component.
	 */
	protected String doGetComponent(int i) {
		return components[i];
	}
	
	/**
	 * @MethodType setter
	 * @MethodProperty primitive
	 */
	protected void doSetComponent(int i, String c) {
		components[i] = c;
	}
	
	/**
	 * Inserts component at given index.
	 * @FixMe Not very efficient (copies array rather than extending it).
	 */
	protected void doInsert(int index, String component) {
		int newSize = getNoComponents() + 1;
		String[] newComponents = new String[newSize];
		for (int i = 0, j = 0; j < newSize; j++) {
			if (j != index) {
				newComponents[j] = components[i++];
			} else {
				newComponents[j] = component;
			}
		}
		components = newComponents;
	}
	
	/**
	 * Removes component at given index.
	 * @FixMe Not very efficient (copies array rather than extending it).
	 */
	protected void doRemove(int index) {
		int newSize = getNoComponents() - 1;
		String[] newComponents = new String[newSize];
		for (int i = 0, j = 0; i <= newSize; ) {
			if (i != index) {
				newComponents[j++] = components[i++];
			} else {
				i++;
			}
		}
		components = newComponents;
	}
	
}

