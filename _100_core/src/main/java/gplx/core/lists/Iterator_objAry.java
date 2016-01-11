package gplx.core.lists; import gplx.*; import gplx.core.*;
public class Iterator_objAry implements java.util.Iterator {//#<>java.util.Iterator~java.util.Iterator
	public boolean hasNext() {return ++pos < len;}//#<>MoveNext~hasNext
	public Object next() {return ary[pos];}//#<>Current {get {return ary[pos];}}~next() {return ary[pos];}
	public void remove() {pos = -1;}//#<>Reset~remove
	Object[] ary; int pos = -1; int len = 0;
	public Iterator_objAry(Object[] v, int count) {ary = v; len = count;}
}