package gplx;
import gplx.core.primitives.*;
public class Hash_adp_ {//_20110428
	public static Hash_adp new_() {return new Hash_adp_obj();}
	public static final Hash_adp Noop = new Hash_adp_noop();
}
class Hash_adp_obj extends gplx.core.lists.Hash_adp_base implements Hash_adp {}//_20110428
class Hash_adp_noop implements Hash_adp {//_20110428
	public int Count() {return 0;}
	public boolean Has(Object key) {return false;}
	public Object Get_by(Object key) {return null;}
	public Object Get_by_or_fail(Object key)				{throw Err_.new_missing_key(Object_.Xto_str_strict_or_null_mark(key));}
	public void Add(Object key, Object val) {}
	public void Add_as_key_and_val(Object val) {}
	public void Add_if_dupe_use_nth(Object key, Object val) {}
	public boolean Add_if_dupe_use_1st(Object key, Object val) {return false;}
	public void Del(Object key) {}
	public void Clear() {}
	public java.util.Iterator iterator() {return gplx.core.lists.Iterator_null.Instance;}
}
