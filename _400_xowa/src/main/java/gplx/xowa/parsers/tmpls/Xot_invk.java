package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public interface Xot_invk {
	byte Defn_tid();
	int Src_bgn();
	int Src_end();
	boolean Frame_is_root();
	byte Frame_tid(); void Frame_tid_(byte v);
	byte[] Frame_ttl(); void Frame_ttl_(byte[] v);
	int Frame_lifetime(); void Frame_lifetime_(int v);
	boolean Rslt_is_redirect(); void Rslt_is_redirect_(boolean v);
	int Args_len();
	Arg_nde_tkn Name_tkn();
	Arg_nde_tkn Args_get_by_idx(int i);
	Arg_nde_tkn Args_eval_by_idx(byte[] src, int idx);
	Arg_nde_tkn Args_get_by_key(byte[] src, byte[] key);
}
