package gplx.xowa.apps.servers.http; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.servers.*;
class File_retrieve_mode {
	public static String Xto_str(byte v) {
		switch (v) {
			case Mode_skip:				return "skip";
			case Mode_wait:				return "wait";
			case Mode_async_server:		return "async_server";
			default:					throw Err_.new_unimplemented();
		}
	}
	public static byte Xto_byte(String s) {
		if		(String_.Eq(s, "skip"))				return Mode_skip;
		else if	(String_.Eq(s, "wait"))				return Mode_wait;
		else if	(String_.Eq(s, "async_server"))		return Mode_async_server;
		else										throw Err_.new_unimplemented();
	}
	public static final byte Mode_skip = 1, Mode_wait = 2, Mode_async_server = 3;
	public static KeyVal[] Options__list = KeyVal_.Ary(KeyVal_.new_("wait"), KeyVal_.new_("skip"), KeyVal_.new_("async_server", "async server"));
}
