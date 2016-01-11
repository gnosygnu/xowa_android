package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public class Wdata_dict_value_monolingualtext {
	public static final byte
	  Tid_text									= 0
	, Tid_language								= 1
	;
	public static final String
	  Str_text									= "text"
	, Str_language								= "language"
	;
	public static byte[] 
	  Bry_text									= Bry_.new_a7(Str_text)
	, Bry_language								= Bry_.new_a7(Str_language)
	;
	public static final Hash_adp_bry Dict = Hash_adp_bry.cs()
	.Add_bry_byte(Bry_text						, Tid_text)
	.Add_bry_byte(Bry_language					, Tid_language)
	;
}
