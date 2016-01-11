package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public class Wdata_dict_value_quantity {
	public static final byte
	  Tid_amount								= 0
	, Tid_unit									= 1
	, Tid_upperbound							= 2
	, Tid_lowerbound							= 3
	;
	public static final String
	  Str_amount								= "amount"
	, Str_unit									= "unit"
	, Str_upperbound							= "upperBound"
	, Str_lowerbound							= "lowerBound"
	;
	public static byte[] 
	  Bry_amount								= Bry_.new_a7(Str_amount)
	, Bry_unit									= Bry_.new_a7(Str_unit)
	, Bry_upperbound							= Bry_.new_a7(Str_upperbound)
	, Bry_lowerbound							= Bry_.new_a7(Str_lowerbound)
	;
	public static final Hash_adp_bry Dict = Hash_adp_bry.cs()
	.Add_bry_byte(Bry_amount					, Tid_amount)
	.Add_bry_byte(Bry_unit						, Tid_unit)
	.Add_bry_byte(Bry_upperbound				, Tid_upperbound)
	.Add_bry_byte(Bry_lowerbound				, Tid_lowerbound)
	;
}
