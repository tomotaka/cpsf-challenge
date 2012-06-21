package jp.ac.keio.sfc.ht.cpsf;


public class MyLinkedList implements CpsfSimpleList {

	private Cell tailCell;
	private Cell startCell;
	private Cell countCell;

	public MyLinkedList() {
		this.tailCell = new Cell(null);
		this.startCell = this.tailCell;
		this.countCell = this.startCell;
	}

	@Override
	public Object get(int index) {

		if (index < 0) {
			throw new IllegalArgumentException();
		}

		index++;
		Cell returnCell;
		for (int i = 0; i < index; i++) {
			this.countCell = this.countCell.getNextCell();
		}
		returnCell = this.countCell;
		this.countCell = this.startCell;
		if (returnCell != null) {
			return returnCell.getObject();
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void add(Object o) {
		this.tailCell.setNextCell(new Cell(o));
		this.tailCell = this.tailCell.getNextCell();
	}

	@Override
	public void remove(int index) {
		if (index < 0) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < index; i++) {
			if (this.countCell.getNextCell() != null) {
				this.countCell = this.countCell.getNextCell();
			} else {
				throw new IllegalArgumentException();
			}
		}
		this.countCell.setNextCell(this.countCell.getNextCell().getNextCell());
		this.countCell = this.startCell;
	}

	@Override
	public int size() {
		int tmp = 0;
		while (this.countCell.getNextCell() != null) {
			this.countCell = this.countCell.getNextCell();
			tmp++;
		}
		this.countCell = this.startCell;
		return tmp;
	}

	public void print() {
		for (int i = 0; i < size(); i++) {
			System.out.println(i + " : " + get(i));
		}
	}

	private class Cell {
		private Object object;
		private Cell nextCell;

		public Cell(Object o) {
			this.object = o;
			this.nextCell = null;
		}

		public void setNextCell(Cell cell) {
			this.nextCell = cell;
		}

		public Object getObject() {
			return this.object;
		}

		public Cell getNextCell() {
			return this.nextCell;
		}
	}

}
