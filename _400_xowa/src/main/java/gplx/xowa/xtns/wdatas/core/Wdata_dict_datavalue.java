package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public class Wdata_dict_datavalue {
	public static final byte
	  Tid_value									= 0
	, Tid_type									= 1
	, Tid_error									= 2
	;
	public static final String
	  Str_value									= "value"
	, Str_type									= "type"
	, Str_error									= "error"
	;
	public static byte[] 
	  Bry_value									= Bry_.new_a7(Str_value)
	, Bry_type									= Bry_.new_a7(Str_type)
	, Bry_error									= Bry_.new_a7(Str_error)
	;
	public static final Hash_adp_bry Dict = Hash_adp_bry.cs()
	.Add_bry_byte(Bry_value						, Tid_value)
	.Add_bry_byte(Bry_type						, Tid_type)
	.Add_bry_byte(Bry_error						, Tid_error)
	;
}
