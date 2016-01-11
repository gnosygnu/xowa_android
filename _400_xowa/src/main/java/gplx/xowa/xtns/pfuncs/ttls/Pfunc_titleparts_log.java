package gplx.xowa.xtns.pfuncs.ttls; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.core.log_msgs.*;
public class Pfunc_titleparts_log {
	private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "tmpl_func_titleparts");
	public static final Gfo_msg_itm
		  Len_is_invalid					= Gfo_msg_itm_.new_warn_(owner, "Len_is_invalid")
		, Bgn_is_invalid					= Gfo_msg_itm_.new_warn_(owner, "Bgn_is_invalid")
		;
}
