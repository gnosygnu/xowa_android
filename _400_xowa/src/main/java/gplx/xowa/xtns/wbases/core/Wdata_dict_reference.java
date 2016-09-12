package gplx.xowa.xtns.wbases.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wdata_dict_reference {
	public static final byte
	  Tid__hash									= 0
	, Tid__snaks								= 1
	, Tid__snaks_order							= 2
	;
	public static final    Wbase_enum_hash Reg = new Wbase_enum_hash("core.reference", 3);
	public static final    Wbase_enum_itm
	  Itm__hash									= Reg.Add(Tid__hash			, "hash")
	, Itm__snaks								= Reg.Add(Tid__snaks		, "snaks")
	, Itm__snaks_order							= Reg.Add(Tid__snaks_order	, "snaks-order")
	;
}
