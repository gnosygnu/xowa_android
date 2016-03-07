package gplx;
public class Keyval_hash {
	private final Ordered_hash hash = Ordered_hash_.New();
	public int			Count()				{return hash.Count();}
	public Keyval_hash	Clear()				{hash.Clear(); return this;}
	public boolean			Has(String key)		{return hash.Has(key);}
	public Keyval		Get_at(int i)		{return (Keyval)hash.Get_at(i);}
	public Object		Get_val_or(String key, Object or) {Keyval rv = Get_kvp_or_null(key); return rv == null ? or : rv.Val();}
	public Object		Get_val_or_null(String key) {return Get_val_or(key, null);}
	public Object		Get_val_or_fail(String key) {return Keyval_.as_(hash.Get_by_or_fail(key)).Val();}
	public String		Get_val_as_str_or_fail(String key) {return (String)Get_val_or_fail(key);}
	public Keyval		Get_kvp_or_null(String key) {return Keyval_.as_(hash.Get_by(key));}
	public Keyval_hash	Add(Keyval kv)				{hash.Add(kv.Key(), kv); return this;}
	public Keyval_hash	Add(String key, Object val) {hash.Add(key, Keyval_.new_(key, val)); return this;}
	public Keyval_hash	Add_if_dupe_use_nth(String key, Object val) {hash.Add_if_dupe_use_nth(key, Keyval_.new_(key, val)); return this;}
	public void			Del(String key) {hash.Del(key);}
	public Keyval[]		To_ary() {
		int len = this.Count();
		Keyval[] rv = new Keyval[len];
		for (int i = 0; i < len; ++i)
			rv[i] = this.Get_at(i);
		return rv;
	}
}