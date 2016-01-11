package gplx.core.lists; import gplx.*; import gplx.core.*;
public abstract class Hash_adp_base implements Hash_adp {//_20110428
	public boolean Has(Object key) {return Has_base(key);}
	public Object Get_by(Object key) {return Fetch_base(key);}
	public Object Get_by_or_fail(Object key) {return Get_by_or_fail_base(key);}
//		public Object Get_by_or_new(Object key, NewAble proto) {
//			Object rv = Fetch_base(key);
//			if (rv == null) {
//				rv = proto.NewByKey(key);
//				Add_base(key, rv);
//			}
//			return rv;
//		}
	public void Add(Object key, Object val) {Add_base(key, val);}
	public void Add_as_key_and_val(Object val) {Add_base(val, val);}
	public void Add_if_dupe_use_nth(Object key, Object val) {
		Object existing = Fetch_base(key); if (existing != null) Del(key);	// overwrite if exists
		Add(key, val);
	}
	public boolean Add_if_dupe_use_1st(Object key, Object val) {
		if (Has(key)) return false;
		Add(key, val);
		return true;
	}
	@gplx.Virtual public void Del(Object key) {Del_base(key);}
	protected Object Get_by_or_fail_base(Object key) {
		if (key == null) throw Err_.new_wo_type("key cannot be null");
		if (!Has_base(key)) throw Err_.new_wo_type("key not found", "key", key);
		return Fetch_base(key);
	}

	private final java.util.Hashtable hash = new java.util.Hashtable();			//#<>System.Collections.Hashtable~java.util.Hashtable
	@gplx.Virtual public int Count() {return hash.size();}														//#<>.Count~.size()
	@gplx.Virtual public void Clear() {hash.clear();}															//#<>.Clear~.clear
	@gplx.Virtual protected void Add_base(Object key, Object val) {hash.put(key, val);}						//#<>.Add~.put
	@gplx.Virtual protected void Del_base(Object key) {hash.remove(key);}										//#<>.Remove~.remove
	@gplx.Virtual protected boolean Has_base(Object key) {return hash.containsKey(key);}							//#<>.Contains~.containsKey
	@gplx.Virtual protected Object Fetch_base(Object key) {return hash.get(key);}									//#<>[key]~.get(key)
	@gplx.Virtual public java.util.Iterator iterator() {return hash.values().iterator();}	//#<>.Values~.values()
}
