package gplx.xowa.xtns.wbases.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wdata_dict_datavalue {
	public static final byte
	  Tid__value								= 0
	, Tid__type									= 1
	, Tid__error								= 2
	;
	public static final    Wbase_enum_hash Reg = new Wbase_enum_hash("core.datavalue", 3);
	public static final    Wbase_enum_itm
	  Itm__value								= Reg.Add(Tid__value		, "value")
	, Itm__type									= Reg.Add(Tid__type			, "type")
	, Itm__error								= Reg.Add(Tid__error		, "error")
	;
}
