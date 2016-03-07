package gplx.xowa.parsers.xndes; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.primitives.*;
public class Xop_xnde_tag_lang {
	public Xop_xnde_tag_lang(int lang_code_int, String name_str) {
		lang_code = Int_obj_ref.new_(lang_code_int);
		this.name_str = name_str;
		this.name_bry = Bry_.new_u8(name_str);
		this.xtnEndTag_tmp = Bry_.Add(Xop_xnde_tag_.Bry__end_tag_bgn, name_bry);
	}
	public Int_obj_ref Lang_code() {return lang_code;} private Int_obj_ref lang_code;
	public String Name_str() {return name_str;} private String name_str;
	public byte[] Name_bry() {return name_bry;} private byte[] name_bry;
	public byte[] Xtn_end_tag_tmp() {return xtnEndTag_tmp;} private byte[] xtnEndTag_tmp;
	public static final Xop_xnde_tag_lang Instance = new Xop_xnde_tag_lang(-1, String_.Empty);
}
