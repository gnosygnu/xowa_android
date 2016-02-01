package gplx.gfui; import gplx.*;
public class Gxw_html_load_tid_ {
	public static final byte Tid_mem = 0, Tid_url = 1;
	public static final String Key_mem = "mem", Key_url = "url";
	public static String Xto_key(byte v) {
		switch (v) {
			case Tid_mem:							return Key_mem;
			case Tid_url:							return Key_url;
			default:								throw Err_.new_unimplemented();
		}
	}
	public static byte Xto_tid(String s) {
		if		(String_.Eq(s, Key_mem))			return Tid_mem;
		else if	(String_.Eq(s, Key_url))			return Tid_url;
		else										throw Err_.new_unimplemented();
	}
	public static KeyVal[] Options__list = KeyVal_.Ary(KeyVal_.new_(Key_mem), KeyVal_.new_(Key_url));
}