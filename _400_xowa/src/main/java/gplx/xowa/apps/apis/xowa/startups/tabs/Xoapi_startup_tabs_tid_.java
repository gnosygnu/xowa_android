package gplx.xowa.apps.apis.xowa.startups.tabs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.startups.*;
public class Xoapi_startup_tabs_tid_ {
	public static final byte Tid_blank = 0, Tid_xowa = 1, Tid_previous = 2, Tid_custom = 3;
	public static final String Key_blank = "blank", Key_xowa = "xowa", Key_previous = "previous", Key_custom = "custom";
	public static String Xto_key(byte v) {
		switch (v) {
			case Tid_blank:							return Key_blank;
			case Tid_xowa:							return Key_xowa;
			case Tid_previous:						return Key_previous;
			case Tid_custom:						return Key_custom;
			default:								throw Err_.new_unimplemented();
		}
	}
	public static byte Xto_tid(String s) {
		if		(String_.Eq(s, Key_blank))			return Tid_blank;
		else if	(String_.Eq(s, Key_xowa))			return Tid_xowa;
		else if	(String_.Eq(s, Key_previous))		return Tid_previous;
		else if	(String_.Eq(s, Key_custom))			return Tid_custom;
		else										throw Err_.new_unimplemented();
	}
	public static Keyval[] Options__list = Keyval_.Ary(Keyval_.new_(Key_blank), Keyval_.new_(Key_xowa), Keyval_.new_(Key_previous), Keyval_.new_(Key_custom));
}
