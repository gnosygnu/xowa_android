package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public class Wdata_dict_value_entity {
	public static final byte
	  Tid_entity_type							= 0
	, Tid_numeric_id							= 1
	;
	public static final String
	  Str_entity_type							= "entity-type"
	, Str_numeric_id							= "numeric-id"
	;
	public static byte[] 
	  Bry_entity_type							= Bry_.new_a7(Str_entity_type)
	, Bry_numeric_id							= Bry_.new_a7(Str_numeric_id)
	;
	public static final Hash_adp_bry Dict = Hash_adp_bry.cs()
	.Add_bry_byte(Bry_entity_type				, Tid_entity_type)
	.Add_bry_byte(Bry_numeric_id				, Tid_numeric_id)
	;
}
