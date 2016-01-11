package gplx.xowa.wikis.ttls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.log_msgs.*;
public class Xop_ttl_log {
	private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "ttl");
	public static final Gfo_msg_itm
	  Len_0								= Gfo_msg_itm_.new_warn_(owner, "Len_0")
	, Len_max							= Gfo_msg_itm_.new_warn_(owner, "Len_max")
	, Ttl_has_ns_but_no_page			= Gfo_msg_itm_.new_warn_(owner, "Ttl_has_ns_but_no_page")			
	, Ttl_is_ns_only					= Gfo_msg_itm_.new_warn_(owner, "Ttl_is_ns_only")
	, Amp_unknown						= Gfo_msg_itm_.new_warn_(owner, "Amp_unknown")
	, Comment_eos						= Gfo_msg_itm_.new_warn_(owner, "Comment_eos")
	, Invalid_char						= Gfo_msg_itm_.new_warn_(owner, "Invalid_char")
	;
}
class Xop_redirect_log {
	private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "redirect");
	public static final Gfo_msg_itm
		  False_match						= Gfo_msg_itm_.new_warn_(owner, "False_match")
		, Lnki_not_found					= Gfo_msg_itm_.new_warn_(owner, "Lnki_not_found")
		;
}
class Xop_tag_log {
	private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "tag");
	public static final Gfo_msg_itm
		  Invalid							= Gfo_msg_itm_.new_warn_(owner, "Invalid")
		;
}
//	class Pf_func_lang_log {
//		private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "tmpl_func_lang");
//		public static final Gfo_msg_itm
//			  Arg_out_of_bounds					= Gfo_msg_itm_.new_warn_(owner, "Arg_out_of_bounds")
//			;
//	}
//	class Mwl_expr_log {
//		private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "expr");
//		public static final Gfo_msg_itm
//			  Divide_by_zero					= Gfo_msg_itm_.new_warn_(owner, "Divide_by_zero")
//			, Expr_len0							= Gfo_msg_itm_.new_warn_(owner, "Expr_len0")
//			;
//	}
