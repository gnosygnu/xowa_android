package gplx.xowa.xtns.xowa_cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.langs.htmls.*;
class Xo_custom_html_pos_ {
	public static final byte Tid__head_end = 1, Tid__tail = 2;
	private static final    Hash_adp_bry hash = Hash_adp_bry.ci_a7()
	.Add_str_byte("head_end", Tid__head_end)
	.Add_str_byte("tail", Tid__tail)
	;
	public static byte To_tid(byte[] bry) {
		byte tid = hash.Get_as_byte_or(bry, Byte_.Max_value_127);
		if (tid == Byte_.Max_value_127) throw Err_.new_wo_type("unknown pos", "pos", bry);
		return tid;
	}
}
class Xo_custom_html_type_ {
	public static final byte Tid__css_code = 1, Tid__js_code = 2;
	private static final    Hash_adp_bry hash = Hash_adp_bry.ci_a7()
	.Add_bry_byte(Gfh_tag_.Bry__style	, Tid__css_code)
	.Add_bry_byte(Gfh_tag_.Bry__script	, Tid__js_code)
	;
	public static byte[] To_bry(byte tid) {
		switch (tid) {
			case Tid__css_code: return Gfh_tag_.Bry__style;
			case Tid__js_code : return Gfh_tag_.Bry__script;
			default: throw Err_.new_unhandled_default(tid);
		}
	}
	public static byte To_tid(byte[] bry) {
		byte tid = hash.Get_as_byte_or(bry, Byte_.Max_value_127); if (tid == Byte_.Max_value_127) throw Err_.new_wo_type("unknown type", "type", bry);
		return tid;
	}
}
