package gplx.xowa.parsers; import gplx.*; import gplx.xowa.*;
public interface Xop_tkn_grp {
	int Subs_len();
	Xop_tkn_itm Subs_get(int i);
	void Subs_add(Xop_tkn_itm sub);
	void Subs_add_grp(Xop_tkn_itm sub, Xop_tkn_grp old_grp, int old_sub_idx);
	void Subs_del_after(int pos_bgn);
	void Subs_clear();
	void Subs_move(Xop_tkn_itm tkn);
	int Subs_src_bgn(int sub_idx);
	int Subs_src_end(int sub_idx);
	void Subs_src_pos_(int sub_idx, int bgn, int end);
	Xop_tkn_itm Immutable_clone(Xop_ctx ctx, Xop_tkn_itm tkn, int sub_idx);
}
class Xop_tkn_grp_ {
	public static final Xop_tkn_grp Null = null;
}
