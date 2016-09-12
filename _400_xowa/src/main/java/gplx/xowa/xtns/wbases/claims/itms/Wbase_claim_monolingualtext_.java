package gplx.xowa.xtns.wbases.claims.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wbase_claim_monolingualtext_ {
	public static final byte
	  Tid__text									= 0
	, Tid__language								= 1
	;
	public static final    Wbase_enum_hash Reg = new Wbase_enum_hash("claim.val.monolingualtext", 2);
	public static final    Wbase_enum_itm
	  Itm__text						= Reg.Add(Tid__text			, "text")
	, Itm__language					= Reg.Add(Tid__language		, "language")
	;
}
