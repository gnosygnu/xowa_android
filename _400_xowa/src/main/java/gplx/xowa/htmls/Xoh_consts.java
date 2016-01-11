package gplx.xowa.htmls; import gplx.*; import gplx.xowa.*;
public class Xoh_consts {
	public static final String
	  Atr_xowa_title_str		= "xowa_title"
	, Img_w_str					= "width"
	, Img_h_str					= "height"
	;
	public static final byte[] 
	  __end				= Bry_.new_a7(">")
	, __inline			= Bry_.new_a7("/>")
	, __end_quote		= Bry_.new_a7("\">")
	, __inline_quote	= Bry_.new_a7("\"/>")
	, Space_2			= Bry_.new_a7("  ")

	, A_mid_id = Bry_.new_a7("\" id=\"xolnki_")
	, Div_bgn_open = Bry_.new_a7("<div ")

	, Img_bgn					= Bry_.new_a7("<img src=\"")
	, Span_bgn_open				= Bry_.new_a7("<span")
	, Span_end					= Bry_.new_a7("</span>")
	, Span_bgn					= Bry_.new_a7("<span>")

	, Pre_bgn					= Bry_.new_a7("<pre>"), Pre_end = Bry_.new_a7("</pre>")
	, Pre_bgn_open				= Bry_.new_a7("<pre")
	, Pre_bgn_overflow			= Bry_.new_a7("<pre style=\"overflow:auto\">")

	, Code_bgn_closed			= Bry_.new_a7("<code>")
	, Code_bgn_open				= Bry_.new_a7("<code")
	, Code_end					= Bry_.new_a7("</code>")
	, Id_atr					= Bry_.new_a7(" id=\"")
	, Style_atr					= Bry_.new_a7(" style=\"")
	, Atr_xowa_title_bry		= Bry_.new_a7(Atr_xowa_title_str)
	;
	public static final int Nbsp_int = 160;
	public static String Escape_apos(String s) {return String_.Replace(s, "'", "\"");}
}
