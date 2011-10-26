package jp.ac.keio.sfc.ht.cpsf;

public class Test1 {

	/**
	 * functionality testing program
	 * @param args
	 */
	public static void main(String[] args) {
		//SimpleList<String> a = new CPSFArrayList<String>();
		SimpleList<String>a = new CPSFLinkedList<String>();
		System.out.println("created, (should 0)a.size=" + a.size());
		
		a.add("a");
		System.out.println("added, (should 1))a.size=" + a.size());
		System.out.println("(should a)a[0]=" + a.get(0));
		
		a.add("b");
		System.out.println("added, (should 2)a.size=" + a.size());
		System.out.println("(should a)a[0]=" + a.get(0));
		System.out.println("(should b)a[1]=" + a.get(1));
		
		a.remove(0);
		System.out.println("removed index=0, (should 1)a.size=" + a.size());
		System.out.println("(should b)a[0]=" + a.get(0));
		
		a.add("c");
		System.out.println("added, (should 2)a.size=" + a.size());
		System.out.println("(should b)a[0]=" + a.get(0));
		System.out.println("(should c)a[1]=" + a.get(1));
		
		a.add("d");
		System.out.println("added, (should 3)a.size=" + a.size());
		System.out.println("(should b)a[0]=" + a.get(0));
		System.out.println("(should c)a[1]=" + a.get(1));
		System.out.println("(should d)a[2]=" + a.get(2));
		
		a.remove(1);
		System.out.println("removed index=1, (should 2)a.size=" + a.size());
		System.out.println("(should b)a[0]=" + a.get(0));
		System.out.println("(should d)a[1]=" + a.get(1));
		
		a.remove(1);
		System.out.println("removed index=1, (should 1)a.size=" + a.size());
		System.out.println("(should b)a[0]=" + a.get(0));


	}

}
