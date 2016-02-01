package gplx.core.caches; import gplx.*; import gplx.core.*;
public class Gfo_cache_mgr_base {
	private Ordered_hash hash = Ordered_hash_.New_bry();
	public int Compress_max() {return compress_max;} public void Compress_max_(int v) {compress_max = v;} private int compress_max = 16;
	public int Compress_to() {return compress_to;} public void Compress_to_(int v) {compress_to = v;} private int compress_to = 8;
	protected Object Base_get_or_null(byte[] key) {
		Object rv_obj = hash.Get_by(key);
		return rv_obj == null ? null : ((Gfo_cache_itm)rv_obj).Val();
	}
	protected void Base_add(byte[] key, Object val) {
		if (hash.Count() >= compress_max) Compress(); 
		Gfo_cache_itm itm = new Gfo_cache_itm(key, val); 
		hash.Add(key, itm);
	}
	protected void Base_del(byte[] key) {
		hash.Del(key);
	}
	public void Compress() {
		hash.Sort_by(Gfo_cache_itm_comparer.Touched_asc);
		int del_len = hash.Count() - compress_to;
		List_adp del_list = List_adp_.new_();
		for (int i = 0; i < del_len; i++) {
			Gfo_cache_itm itm = (Gfo_cache_itm)hash.Get_at(i);
			del_list.Add(itm);
		}
		for (int i = 0; i < del_len; i++) {
			Gfo_cache_itm itm = (Gfo_cache_itm)del_list.Get_at(i);
			hash.Del(itm.Key());
		}
	}
}