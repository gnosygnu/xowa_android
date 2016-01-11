package gplx.xowa.parsers.miscs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.log_msgs.*;
public class Xop_comm_log {		
	private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "comment");
	public static final Gfo_msg_itm
		  Eos								= Gfo_msg_itm_.new_warn_(owner, "eos")
		;
}
