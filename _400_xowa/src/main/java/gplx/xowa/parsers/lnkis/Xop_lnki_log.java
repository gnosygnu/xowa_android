package gplx.xowa.parsers.lnkis; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.log_msgs.*;
public class Xop_lnki_log {		
	private static final Gfo_msg_grp owner = Gfo_msg_grp_.new_(Xoa_app_.Nde, "lnki");
	public static final Gfo_msg_itm
	  Upright_val_is_invalid			= Gfo_msg_itm_.new_warn_(owner, "upright_val_is_invalid")
	, Escaped_lnki						= Gfo_msg_itm_.new_warn_(owner, "escaped_lnki")
	, Key_is_empty						= Gfo_msg_itm_.new_warn_(owner, "key_is_empty")
	, Ext_is_missing					= Gfo_msg_itm_.new_warn_(owner, "ext_is_missing")
	, Invalid_ttl						= Gfo_msg_itm_.new_warn_(owner, "invalid_ttl")
	;
}
