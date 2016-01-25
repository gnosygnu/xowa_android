package gplx.langs.mustaches; import gplx.*; import gplx.langs.*;
class Mustache_render_ctx {
	private Mustache_doc_itm doc;
	public void Init_dom_doc(Mustache_doc_itm doc) {this.doc = doc;}
	public byte[] Render_variable(byte[] key) {
		byte[] rv = Mustache_doc_itm_.Null_val;
		Mustache_doc_itm cur = doc;
		while (cur != Mustache_doc_itm_.Null_itm) {
			rv = doc.Get_by_key(key);
			if (rv != Mustache_doc_itm_.Null_val) break;
			cur = cur.Get_owner();
		}
		return rv;
	}
}
