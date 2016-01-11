package gplx.xowa.parsers.miscs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.log_msgs.*;
public class Xop_misc_log {
	private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "super");
	public static final Gfo_msg_itm
		  Eos								= Gfo_msg_itm_.new_warn_(owner, "End_of_string")
		;
}
