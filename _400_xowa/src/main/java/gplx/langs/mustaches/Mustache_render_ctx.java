package gplx.langs.mustaches; import gplx.*; import gplx.langs.*;
public class Mustache_render_ctx {
	private final    List_adp stack = List_adp_.new_();
	private Mustache_doc_itm cur;
	private Mustache_doc_itm[] subs; private int subs_idx, subs_len; private byte cur_is_bool;
	public Mustache_render_ctx Init(Mustache_doc_itm cur) {
		this.cur = cur;
		this.subs = null;
		this.subs_idx = subs_len = 0; this.cur_is_bool = Bool_.__byte;
		return this;
	}
	public boolean Render_variable(Mustache_bfr bfr, String key) {
		boolean rv = false;
		Mustache_doc_itm itm = cur;
		while (itm != Mustache_doc_itm_.Null_itm) {
			boolean resolved = cur.Mustache__write(key, bfr);
			if (resolved) {rv = true; break;}
			else break; // TODO: itm = itm.Get_owner();
		}
		return rv;
	}
	public void Section_bgn(String key) {
		Mustache_stack_itm stack_itm = new Mustache_stack_itm(cur, subs, subs_idx, subs_len, cur_is_bool); // note that cur is "owner" since subs_idx == 0
		stack.Add(stack_itm);
		subs = cur.Mustache__subs(key); if (subs == null) subs = Mustache_doc_itm_.Ary__empty;	// subs == null if property does not exist; EX: "folder{{#files}}file{{/files}}" and folder = new Folder(File[0]);
		subs_len = subs.length;
		subs_idx = -1;
	}
	public boolean Section_do(boolean inverted) {
		if (++subs_idx >= subs_len) return false;
		Mustache_doc_itm sub = subs[subs_idx];
		if (subs_idx == 0) {	// special logic to handle 1st item; note that there always be at least one item
			if		(sub == Mustache_doc_itm_.Itm__bool__n) {
				boolean rv = Bool_.N;
				if (inverted) rv = !rv;
				cur_is_bool = Bool_.To_byte(rv);
				return rv;
			}
			else if	(sub == Mustache_doc_itm_.Itm__bool__y) {
				boolean rv = Bool_.Y;
				if (inverted) rv = !rv;
				cur_is_bool = Bool_.To_byte(rv);
				return rv;
			}
			else
				cur_is_bool = Bool_.__byte;
		}			
		cur = sub;
		return true;
	}
	public void Section_end() {
		Mustache_stack_itm itm = (Mustache_stack_itm)List_adp_.Pop(stack);
		subs = itm.subs;
		subs_len = itm.subs_len;
		subs_idx = itm.subs_idx;
		cur = itm.cur;
		cur_is_bool = itm.cur_is_bool;
	}
}
class Mustache_stack_itm {
	public Mustache_stack_itm(Mustache_doc_itm cur, Mustache_doc_itm[] subs, int subs_idx, int subs_len, byte cur_is_bool) {
		this.cur = cur;
		this.cur_is_bool = cur_is_bool;
		this.subs = subs;
		this.subs_idx = subs_idx;
		this.subs_len = subs_len;
	}
	public final    Mustache_doc_itm cur;
	public final    byte cur_is_bool;
	public final    Mustache_doc_itm[] subs;
	public final    int subs_idx;
	public final    int subs_len;
}
