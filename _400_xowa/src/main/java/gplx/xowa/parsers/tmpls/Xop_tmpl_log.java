package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.log_msgs.*;
public class Xop_tmpl_log {
	private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "tmpl");
	public static final Gfo_msg_itm
		  Escaped_tmpl						= Gfo_msg_itm_.new_warn_(owner, "Escaped_tmpl")
		, Escaped_end						= Gfo_msg_itm_.new_warn_(owner, "Escaped_end")
		, Key_is_empty						= Gfo_msg_itm_.new_note_(owner, "Key_is_empty")
		, Dangling							= Gfo_msg_itm_.new_note_(owner, "Dangling_tmpl")
		, Tmpl_end_autoCloses_something		= Gfo_msg_itm_.new_note_(owner, "Tmpl_end_autoCloses_something")
		, Tmpl_is_empty						= Gfo_msg_itm_.new_note_(owner, "Tmpl_is_empty")
		;
}
