package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.langs.regxs.*;
public class Srch_crt_node {
	public Srch_crt_node(int uid, int tid, boolean flat, Srch_crt_node[] subs, byte[] raw) {
		this.uid = uid; this.tid = tid; this.flat = flat; this.subs = subs;
		this.raw = raw;
		if (raw != null) {
			int raw_len = raw.length;
			int wildcard_pos = Bry_find_.Find_fwd(raw, Byte_ascii.Star, 0, raw_len);
			if		(wildcard_pos == Bry_find_.Not_found)	raw_where_tid = Srch_crt_node_.Where__eq;
			else if (wildcard_pos == raw_len - 1)			raw_where_tid = Srch_crt_node_.Where__rng;
			else											raw_where_tid = Srch_crt_node_.Where__like;
		}
		else
			raw_where_tid = Srch_crt_node_.Where__eq;
		if (raw_where_tid == Srch_crt_node_.Where__rng) {
			raw_rng_bgn = Bry_.Mid(raw, 0, raw.length - 1);
			raw_rng_end = gplx.core.intls.Utf8_.Increment_char_at_last_pos(raw_rng_bgn);
		}
		else
			raw_rng_bgn = raw_rng_end = Bry_.Empty;
		this.raw_has_wildcard = raw_where_tid != Srch_crt_node_.Where__eq;
		this.raw_pattern = raw_has_wildcard ? new Gfo_pattern(raw) : null;
	}
	public final int uid;
	public final int tid;
	public final boolean flat;
	public final Srch_crt_node[] subs;
	public final byte[] raw;
	public final byte[] raw_rng_bgn, raw_rng_end;
	public final boolean raw_has_wildcard;
	public final Gfo_pattern raw_pattern;
	public final int raw_where_tid;
	public Object raw_as_quoted_phrase;
	public void Accept_visitor(Srch_crt_visitor visitor) {visitor.Visit(this);}

	public static final Srch_crt_node Invalid = new Srch_crt_node(-1, Srch_crt_node_.Tid__invalid, false, Srch_crt_node_.Ary_empty, null);
}
/*
AND,0 nots: hash a; iterate cur_b and add if in a
OR ,0 nots; hash a; add a; iterate cur_b and add if not in a
AND,1 nots; hash notted; iterate comp and add only if not in notted; (a,cur_b,c) AND NOT (cur_b) 
AND,2 nots; fail; NOT xq AND NOT qx will yield all titles
OR ,2 nots; fail; NOT xq OR NOT qx will yield all titles
OR ,1 nots; fail; (a, cur_b, c, d) OR NOT (xq) will yield all titles
*/
