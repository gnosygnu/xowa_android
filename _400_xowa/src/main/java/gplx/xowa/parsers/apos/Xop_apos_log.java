package gplx.xowa.parsers.apos; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.log_msgs.*;
public class Xop_apos_log {
	private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "apos");
	public static final Gfo_msg_itm
		  Bold_converted_to_ital			= Gfo_msg_itm_.new_note_(owner, "Bold_converted_to_ital")
		, Dangling_apos						= Gfo_msg_itm_.new_note_(owner, "Dangling_apos")
		, Multiple_apos						= Gfo_msg_itm_.new_note_(owner, "Multiple_apos")
		;
}
