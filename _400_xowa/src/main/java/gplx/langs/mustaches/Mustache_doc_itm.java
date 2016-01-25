package gplx.langs.mustaches; import gplx.*; import gplx.langs.*;
import gplx.langs.jsons.*;
interface Mustache_doc_itm {
	byte[] Get_by_key(byte[] key);
	Mustache_doc_itm Get_owner();
	void Move_next();
	void Move_down(byte[] key);
	void Move_up();
}
class Mustache_doc_itm_ {
	public static final byte[] Null_val = null;
	public static final Mustache_doc_itm Null_itm = null;
}
class Mustache_doc_itm__json implements Mustache_doc_itm {
	// private Json_doc jdoc; 
	private Json_nde cur;
	public void Init_by_jdoc(Json_doc jdoc) {
		// this.jdoc = jdoc;
		this.cur = jdoc.Root_nde();
	}
	public byte[] Get_by_key(byte[] key) {return cur.Get_bry_or_null(key);}
	public Mustache_doc_itm Get_owner() {return Mustache_doc_itm_.Null_itm;}
	public void Move_next() {
		// cur = cur.Owner().Get_at();
	}
	public void Move_down(byte[] key) {
		cur = (Json_nde)cur.Get_itm(key);
	}
	public void Move_up() {}
}
