package jp.ac.keio.sfc.ht.cpsf;

public class CPSFArrayList<E> implements SimpleList<E> {
	
	public static final int INITIAL_SIZE = 10;
	public static final float REVERAGE = 1.5f; 
	
	private int pos;
	private Object[] buffer;
	
	public <E> CPSFArrayList() {
		buffer = new Object[INITIAL_SIZE];
	}

	@Override
	public int size() {
		return pos;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return (E)buffer[index];
	}

	@Override
	public void remove(int index) {
		Object[] temp = new Object[buffer.length];
		System.arraycopy(buffer, 0, temp, 0, index);
		System.arraycopy(buffer, index+1, temp, index, pos - index - 1);
		buffer = temp;
		pos--;
	}

	@Override
	public void add(E value) {
		if (pos == buffer.length) {
			// have to expand buffer
			Object[] tmp = new Object[(int)(buffer.length * REVERAGE)];
			System.arraycopy(buffer, 0, tmp, 0, pos-1);
			buffer = tmp;
		}
		
		buffer[pos] = value;
		pos++;
	}

}
