package gplx.xowa.xtns.wbases.claims.enums; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
public class Wbase_claim_value_type_ {
	public static final byte	// SERIALIZED.MW
	  Tid__novalue								=  0
	, Tid__value								=  1
	, Tid__somevalue							=  2
	;
	public static final    Wbase_enum_hash Reg = new Wbase_enum_hash("claim.value_type", 3);
	public static final    Wbase_enum_itm
	  Itm__novalue					= Reg.Add(Tid__novalue			, "novalue")
	, Itm__value					= Reg.Add(Tid__value			, "value")
	, Itm__somevalue				= Reg.Add(Tid__somevalue		, "somevalue")
	;
}