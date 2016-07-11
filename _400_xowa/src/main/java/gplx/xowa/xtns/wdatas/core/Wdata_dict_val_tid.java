package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.core.primitives.*;
public class Wdata_dict_val_tid {
	public static final byte 
	  Tid_unknown								= 0
	, Tid_value									= 1
	, Tid_bad									= 1
	, Tid_string								= 2
	, Tid_entity								= 3
	, Tid_time									= 4
	, Tid_globecoordinate						= 5
	, Tid_quantity								= 6
	, Tid_monolingualtext						= 7
	;
	public static final String 
	  Str_bad									= "bad"
	, Str_string								= "str"+"ing"
	, Str_entity								= "wikibase-entityid"
	, Str_time									= "time"
	, Str_globecoordinate						= "globecoordinate"
	, Str_quantity								= "quantity"
	, Str_monolingualtext						= "monolingualtext"
	, Str_unknown								= "unknown"
	;
	public static final    byte[]
	  Bry_bad									= Bry_.new_a7(Str_bad)
	, Bry_string								= Bry_.new_a7(Str_string)
	, Bry_entity								= Bry_.new_a7(Str_entity)
	, Bry_time									= Bry_.new_a7(Str_time)
	, Bry_globecoordinate						= Bry_.new_a7(Str_globecoordinate)
	, Bry_quantity								= Bry_.new_a7(Str_quantity)
	, Bry_monolingualtext						= Bry_.new_a7(Str_monolingualtext)
	, Bry_unknown								= Bry_.new_a7(Str_unknown)
	;
	private static final    Hash_adp_bry Dict = Hash_adp_bry.cs()
	.Add_bry_byte(Bry_string					, Tid_string)
	.Add_bry_byte(Bry_entity					, Tid_entity)
	.Add_bry_byte(Bry_time						, Tid_time)
	.Add_bry_byte(Bry_globecoordinate			, Tid_globecoordinate)
	.Add_bry_byte(Bry_quantity					, Tid_quantity)
	.Add_bry_byte(Bry_monolingualtext			, Tid_monolingualtext)
	.Add_bry_byte(Bry_bad						, Tid_bad)
	;
	public static String Xto_str(byte tid) {
		switch (tid) {
			case Tid_string						: return Str_string;
			case Tid_entity						: return Str_entity;
			case Tid_time						: return Str_time;
			case Tid_globecoordinate			: return Str_globecoordinate;
			case Tid_quantity					: return Str_quantity;
			case Tid_monolingualtext			: return Str_monolingualtext;
			case Tid_bad						: return Str_bad;	// NOTE: wikidata identifies several entries as "bad"; Q1615351|'s-Graveland, Q107538|Baco; DATE:2013-10-20
			default								: return Str_unknown;
		} 
	}
	public static String To_str__srl(byte tid) {	// for scrib.wbase
		switch (tid) {
			case Tid_string						: return Str_string;
			case Tid_entity						: return "wikibase-item";		// changed from "wikibase-entityid"; PAGE:ht.w:Srilanka DATE:2016-07-08
			case Tid_time						: return Str_time;
			case Tid_globecoordinate			: return "globe-coordinate";
			case Tid_quantity					: return Str_quantity;
			case Tid_monolingualtext			: return Str_monolingualtext;
			case Tid_bad						: return Str_bad;	// NOTE: wikidata identifies several entries as "bad"; Q1615351|'s-Graveland, Q107538|Baco; DATE:2013-10-20
			default								: return Str_unknown;
		} 
	}
	public static byte Xto_tid(byte[] src) {return Xto_tid(src, 0, src.length);}
	public static byte Xto_tid(byte[] src, int bgn, int end) {
		Object bval_obj = Dict.Get_by_mid(src, bgn, end);
		if	(bval_obj == null) return Tid_unknown;		
		return ((Byte_obj_val)bval_obj).Val();
	}
}
