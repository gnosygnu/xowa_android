package gplx;
public class Keyval implements To_str_able {
	@gplx.Internal protected Keyval(int key_tid, Object key, Object val) {this.key_tid = key_tid; this.key = key; this.val = val;}
	public String	Key() {return Object_.Xto_str_strict_or_null(key);}
	public Object	Key_as_obj() {return key;} private Object key;
	public int		Key_tid() {return key_tid;} private int key_tid;
	public Object	Val() {return val;} private Object val;
	public String	Val_to_str_or_empty() {return Object_.Xto_str_strict_or_empty(val);}
	public String	Val_to_str_or_null() {return Object_.Xto_str_strict_or_null(val);}
	public byte[]	Val_to_bry() {return Bry_.new_u8(Object_.Xto_str_strict_or_null(val));}
	public Keyval	Key_(Object v) {this.key = v; return this;}
	public Keyval	Val_(Object v) {this.val = v; return this;} 
	public String	To_str() {return Key() + "=" + Object_.Xto_str_strict_or_null_mark(val);}
	@Override public String toString() {return To_str();}
}
