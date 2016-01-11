package gplx.core.lists; import gplx.*; import gplx.core.*;
public class Iterator_null implements java.util.Iterator {//_20110320 //#<>java.util.Iterator~java.util.Iterator
	public boolean hasNext() {return false;}//#<>MoveNext~hasNext
	public Object next() {return null;}//#<>Current {get {return null;}}~next() {return null;}
	public void remove() {}//#<>Reset~remove
	public static final Iterator_null Instance = new Iterator_null();
}
