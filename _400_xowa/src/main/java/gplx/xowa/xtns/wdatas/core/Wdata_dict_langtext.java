package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public class Wdata_dict_langtext {
	public static final byte
	  Tid_language								= 0
	, Tid_value									= 1
	;
	public static final String
	  Str_language								= "language"
	, Str_value									= "value"
	;
	public static byte[] 
	  Bry_language								= Bry_.new_a7(Str_language)
	, Bry_value									= Bry_.new_a7(Str_value)
	;
	public static final Hash_adp_bry Dict = Hash_adp_bry.cs()
	.Add_bry_byte(Bry_language					, Tid_language)
	.Add_bry_byte(Bry_value						, Tid_value)
	;
}
