package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
class Srch2_crt_tkn {
	public final byte	tid;
	public final byte[]	val;
	public Srch2_crt_tkn(byte tid, byte[] val) {this.tid = tid; this.val = val;}

	public static final byte 
	  Tid_root			=  1
	, Tid_word			=  2
	, Tid_word_quoted	=  3
	, Tid_space			=  4
	, Tid_quote			=  5
	, Tid_not			=  6
	, Tid_paren_bgn		=  7
	, Tid_paren_end		=  8
	, Tid_or			=  9
	, Tid_and			= 10
	, Tid_eos			= 11
	;
}
