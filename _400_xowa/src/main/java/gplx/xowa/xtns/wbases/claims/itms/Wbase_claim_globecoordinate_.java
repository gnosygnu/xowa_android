package gplx.xowa.xtns.wbases.claims.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wbase_claim_globecoordinate_ {
	public static final byte
	  Tid__latitude								= 0
	, Tid__longitude							= 1
	, Tid__altitude								= 2
	, Tid__precision							= 3
	, Tid__globe								= 4
	;
	public static final    Wbase_enum_hash Reg = new Wbase_enum_hash("claim.val.globecoordinate", 5);
	public static final    Wbase_enum_itm
	  Itm__latitude					= Reg.Add(Tid__latitude			, "latitude")
	, Itm__longitude				= Reg.Add(Tid__longitude		, "longitude")
	, Itm__altitude					= Reg.Add(Tid__altitude			, "altitude")
	, Itm__precision				= Reg.Add(Tid__precision		, "precision")
	, Itm__globe					= Reg.Add(Tid__globe			, "globe")
	;

	public static String
	  Val_globe_dflt_str						= "http://www.wikidata.org/entity/Q2"
	;
	public static byte[]
	  Val_globe_dflt_bry						= Bry_.new_a7(Val_globe_dflt_str)
	;
}
