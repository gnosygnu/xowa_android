package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
class Srch_crt_tkn {
	public final byte	tid;
	public final byte[]	val;
	public Srch_crt_tkn(byte tid, byte[] val) {this.tid = tid; this.val = val;}
	public static final byte 
	  Tid__root			=  1
	, Tid__word			=  2
	, Tid__word_quoted	=  3
	, Tid__space		=  4
	, Tid__quote		=  5
	, Tid__not			=  6
	, Tid__paren_bgn	=  7
	, Tid__paren_end	=  8
	, Tid__or			=  9
	, Tid__and			= 10
	, Tid__eos			= 11
	;
}
