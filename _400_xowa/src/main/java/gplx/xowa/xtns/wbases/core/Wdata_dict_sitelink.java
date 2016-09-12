package gplx.xowa.xtns.wbases.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wdata_dict_sitelink {
	public static final byte
	  Tid__site									= 0
	, Tid__title								= 1
	, Tid__badges								= 2
	;
	public static final    Wbase_enum_hash Reg = new Wbase_enum_hash("core.sitelink", 3);
	public static final    Wbase_enum_itm
	  Itm__site									= Reg.Add(Tid__site			, "site")
	, Itm__title								= Reg.Add(Tid__title		, "title")
	, Itm__badges								= Reg.Add(Tid__badges		, "badges")
	;
}
