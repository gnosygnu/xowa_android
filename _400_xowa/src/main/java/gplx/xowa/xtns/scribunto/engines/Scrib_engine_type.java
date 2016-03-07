package gplx.xowa.xtns.scribunto.engines; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.scribunto.*;
public class Scrib_engine_type {
	public static String Xto_str(byte v) {
		switch (v) {
			case Type_lua:				return "lua";
			case Type_luaj:				return "luaj";
			default:					throw Err_.new_unimplemented();
		}
	}
	public static byte Xto_byte(String s) {
		if		(String_.Eq(s, "lua"))				return Type_lua;
		else if	(String_.Eq(s, "luaj"))				return Type_luaj;
		else										throw Err_.new_unimplemented();
	}
	public static final byte Type_lua = 0, Type_luaj = 1;
	public static Keyval[] Options__list = Keyval_.Ary(Keyval_.new_("luaj"), Keyval_.new_("lua"));
}
