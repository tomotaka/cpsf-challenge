package jp.ac.keio.sfc.ht.cpsf.test;

import static org.junit.Assert.assertEquals;
import jp.ac.keio.sfc.ht.cpsf.CpsfSimpleList;
import jp.ac.keio.sfc.ht.cpsf.MyLinkedList;

import org.junit.Before;
import org.junit.Test;

public class TestCpsfSimpleList {
	
	private CpsfSimpleList list;
	
	@Before
	public void setUp() {
//		list = new TomotakaLinkedList();
		list = new MyLinkedList();
	}
	
	@Test
	public void testGet() {
		list.add("a");
		assertEquals("a", list.get(0));
		
		list.add("b");
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		
		list.add("c");
		list.add("d");
		assertEquals("d", list.get(3));
		assertEquals("c", list.get(2));
		assertEquals("b", list.get(1));
		assertEquals("a", list.get(0));

		setUp();
		list.add(null);
		assertEquals(null, list.get(0));
		
		setUp();
		list.add(Integer.valueOf(123));
		assertEquals(Integer.valueOf(123), list.get(0));
		
		setUp();
		list.add(null);
		list.add(Double.valueOf(123.456));
		list.add("xyz");
		list.add(Integer.valueOf(987));
		assertEquals(null, list.get(0));
		assertEquals(Double.valueOf(123.456), list.get(1));
		assertEquals("xyz", list.get(2));
		assertEquals(Integer.valueOf(987), list.get(3));
	}
	
	@Test
	public void testAdd() {
		list.add("a");
		assertEquals(1, list.size());
		assertEquals("a", list.get(0));
		
		list.add("b");
		assertEquals(2, list.size());
		assertEquals("b", list.get(1));
		assertEquals("a", list.get(0));
	}
	
	@Test
	public void testRemove() {
		list.add("a");
		list.remove(0);
		assertEquals(0, list.size());

		setUp();
		list.add("aa");
		list.add("bb");
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("bb", list.get(0));
		
		setUp();
		list.add("aaa");
		list.add("bbb");
		list.remove(1);
		assertEquals(1, list.size());
		assertEquals("aaa", list.get(0));

		setUp();
		list.add("aaaa");
		list.add("bbbb");
		list.add("cccc");
		list.remove(1);
		assertEquals(2, list.size());
		assertEquals("aaaa", list.get(0));
		assertEquals("cccc", list.get(1));
		
		setUp();
		list.add("x");
		list.add("y");
		list.remove(0);
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testSize() {
		list.add("a");
		assertEquals(1, list.size());
		
		list.add("b");
		assertEquals(2, list.size());
		
		list.add("c");
		list.add("d");
		assertEquals(4, list.size());
	}
	

}
