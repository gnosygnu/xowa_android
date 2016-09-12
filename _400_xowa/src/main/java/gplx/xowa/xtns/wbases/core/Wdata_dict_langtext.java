package gplx.xowa.xtns.wbases.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wdata_dict_langtext {
	public static final byte
	  Tid__language								= 0
	, Tid__value								= 1
	;
	public static final    Wbase_enum_hash Reg = new Wbase_enum_hash("core.langtext", 2);
	public static final    Wbase_enum_itm
	  Itm__language								= Reg.Add(Tid__language		, "language")
	, Itm__value								= Reg.Add(Tid__value		, "value")
	;
}
