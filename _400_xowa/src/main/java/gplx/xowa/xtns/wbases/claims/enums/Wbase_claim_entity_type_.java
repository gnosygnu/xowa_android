package gplx.xowa.xtns.wbases.claims.enums; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
public class Wbase_claim_entity_type_ {
	public static final byte
	  Tid__item									=  0
	, Tid__property								=  1
	;
	public static final    Wbase_enum_hash Reg = new Wbase_enum_hash("claim.entity_type", 2);
	public static final    Wbase_enum_itm
	  Itm__item						= Reg.Add(Tid__item				, "item")
	, Itm__property					= Reg.Add(Tid__property			, "property")
	;
}