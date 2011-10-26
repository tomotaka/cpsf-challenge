package jp.ac.keio.sfc.ht.cpsf;

public class CPSFLinkedList<E> implements SimpleList<E> {
	
	private class Entry {
		
		private E value;
		
		private Entry next = null;
		
		public Entry(E value) {
			this.value = value;
		}
		
		public E getValue() {
			return value;
		}
		
		public void setNext(Entry next) { 
			this.next = next;
		}
		
		public Entry getNext() {
			return next;
		}
		
		public boolean hasNext() {
			return (next != null);
		}
		
	}
	
	private Entry first;

	@Override
	public int size() {
		if (first == null) {
			return 0;
		} else {
			int count = 1;
			Entry entry = first;
			while (entry.hasNext()) {
				entry = entry.getNext();
				count++;
			}
			return count;
		}
	}

	@Override
	public E get(int index) {
		int count = 0;
		Entry entry = first;
		while (count < index) {
			entry = entry.getNext();
			count++;
		}
		return entry.getValue();
	}

	@Override
	public void remove(int index) {
		if (index == 0) {
			Entry next = first.getNext();
			if (next != null) {
				first = next;
			}
		} else {
			Entry before = null;
			Entry entry = first;
			int count = 0;
			while (count < index) {
				before = entry;
				entry = entry.next;
				count++;
			}
			
			if (entry.hasNext()) {
				before.setNext(entry.getNext());
			} else {
				before.setNext(null);
			}
		}

	}

	@Override
	public void add(E value) {
		if (first == null) {
			first = new Entry(value);
		} else {
			Entry entry = first;
			while (entry.hasNext()) {
				entry = entry.getNext();
			}
			entry.setNext(new Entry(value));
		}
	}

}
