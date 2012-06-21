package jp.ac.keio.sfc.ht.cpsf;

public interface CpsfSimpleList {
    public Object get(int index);
    public void add(Object o);
    public void remove(int index);
    public int size();
}
