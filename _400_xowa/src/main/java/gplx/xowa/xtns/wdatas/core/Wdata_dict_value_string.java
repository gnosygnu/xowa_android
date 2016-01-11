package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public class Wdata_dict_value_string {
	public static final byte
	  Tid_value									= 0
	, Tid_type									= 1
	;
	public static byte[] 
	  Bry_value									= Bry_.new_a7("value")
	, Bry_type									= Bry_.new_a7("type")
	;
	public static final Hash_adp_bry Dict = Hash_adp_bry.cs()
	.Add_bry_byte(Bry_value						, Tid_value)
	.Add_bry_byte(Bry_type						, Tid_type)
	;
}
