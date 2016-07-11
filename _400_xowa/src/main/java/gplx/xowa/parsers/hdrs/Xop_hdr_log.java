package gplx.xowa.parsers.hdrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.log_msgs.*;
public class Xop_hdr_log {
	private static final    Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "hdr");
	public static final    Gfo_msg_itm
		  Dangling_hdr						= Gfo_msg_itm_.new_warn_(owner, "dangling_hdr")
		, Mismatched						= Gfo_msg_itm_.new_warn_(owner, "mismatched")
		, Len_1								= Gfo_msg_itm_.new_warn_(owner, "len_1")
		, Len_7_or_more						= Gfo_msg_itm_.new_warn_(owner, "len_7_or_more")
		;
}
