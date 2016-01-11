package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.core.primitives.*;
public class Wdata_dict_rank {
	public static final byte	// SERIALIZED:
	  Tid_preferred								= 2
	, Tid_normal								= 1
	, Tid_deprecated							= 0
	, Tid_unknown								= Byte_.Max_value_127
	;
	public static final String
	  Str_preferred								= "preferred"
	, Str_normal								= "normal"
	, Str_deprecated							= "deprecated"
	;
	public static byte[] 
	  Bry_preferred								= Bry_.new_a7(Str_preferred)
	, Bry_normal								= Bry_.new_a7(Str_normal)
	, Bry_deprecated							= Bry_.new_a7(Str_deprecated)
	;
	public static final Hash_adp_bry Dict = Hash_adp_bry.cs()
	.Add_bry_byte(Bry_preferred					, Tid_preferred)
	.Add_bry_byte(Bry_normal					, Tid_normal)
	.Add_bry_byte(Bry_deprecated				, Tid_deprecated)
	;
	public static String Xto_str(byte tid) {
		switch (tid) {
			case Tid_preferred					: return Str_preferred;
			case Tid_normal						: return Str_normal;
			case Tid_deprecated					: return Str_deprecated;
			default								: throw Err_.new_unhandled(tid);
		}
	}
	public static byte Xto_tid(byte[] v) {
		Object rv_obj = Dict.Get_by_bry(v); if	(rv_obj == null) throw Err_.new_wo_type("unknown rank", "val", String_.new_u8(v));
		return ((Byte_obj_val)rv_obj).Val();
	}
}