package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.log_msgs.*;
public class Xop_curly_log {
	private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "curly");
	public static final Gfo_msg_itm
		  Bgn_not_found						= Gfo_msg_itm_.new_warn_(owner, "Bgn_not_found")
		, End_should_not_autoclose_anything	= Gfo_msg_itm_.new_warn_(owner, "End_should_not_autoclose_anything")
		, Bgn_len_0							= Gfo_msg_itm_.new_warn_(owner, "Bgn_len_0")
		, Bgn_len_1							= Gfo_msg_itm_.new_warn_(owner, "Bgn_len_1")
		, Tmpl_is_empty						= Gfo_msg_itm_.new_warn_(owner, "Tmpl_is_empty")
		;
}
