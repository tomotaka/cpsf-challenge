package jp.ac.keio.sfc.ht.cpsf;

public class TomotakaLinkedList implements CpsfSimpleList {
	
	private static class Tuple {
		
		private Object value = null;
		private Tuple next = null;
		
		public Tuple(final Object value) {
			setValue(value);
		}
		
		public void setValue(final Object value) {
			this.value = value;
		}
		
		public Object getValue() {
			return value;
		}
		
		public void setNext(final Tuple next) {
			this.next = next;
		}
		
		public Tuple getNext() {
			return next;
		}
		
	}
	
	private Tuple first = null;

	@Override
	public Object get(int index) {
		checkIndex(index);
		return getTuple(index).getValue();
	}

	@Override
	public void add(Object o) {
		if (first == null) {
			first = new Tuple(o);
		} else {
			Tuple t = first;
			Tuple n = null;
			
			while ((n = t.getNext()) != null) {
				t = n;
			}
			t.setNext(new Tuple(o));
		}
	}

	@Override
	public void remove(int index) {
		checkIndex(index);
		
		int n = size();
		if (index == 0) {
			first = first.getNext();
		} else if (index == n-1) {
			Tuple prevOfLast = getTuple(n-2);
			prevOfLast.setNext(null);
		} else {
			Tuple prev = getTuple(index-1);
			Tuple next = getTuple(index+1);
			prev.setNext(next);
		}
	}

	@Override
	public int size() {
		Tuple t = first;
		if (t == null) {
			return 0;
		}
		int count = 1;
		Tuple n = null;
		while ((n = t.getNext()) != null) {
			t = n;
			count++;
		}
		return count;
	}
	
	private Tuple getTuple(final int index) {
		int i = 0;
		Tuple t = first;
		while (i < index) {
			t = t.getNext();
			i++;
		}
		return t;
	}
	
	private void checkIndex(final int index) {
		if (size() <= index || index < 0) {
			throw new IllegalArgumentException("out of bound index: " + index);
		}
	}

}
