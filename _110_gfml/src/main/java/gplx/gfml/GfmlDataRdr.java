package gplx.gfml; import gplx.*;
import gplx.core.stores.*;
public class GfmlDataRdr extends GfmlDataRdr_base {
	public static DataRdr raw_root_(String raw) {
		GfmlDoc gdoc = GfmlDoc_.parse_any_eol_(raw);
		GfmlDataRdr rv = new GfmlDataRdr();
		rv.Parse_set(true);
		rv.SetNode(gdoc.RootNde().SubHnds().Get_at(0));
		return rv;
	}
	public static DataRdr raw_list_(String raw) {
		GfmlDoc gdoc = GfmlDoc_.parse_any_eol_(raw);
		GfmlDataRdr rv = new GfmlDataRdr();
		rv.SetNode(gdoc.RootNde());
		return rv;
	}
	public static GfmlDataRdr nde_(GfmlNde nde) {
		GfmlDataRdr rv = new GfmlDataRdr();
		rv.SetNode(nde);
		return rv;
	}
	public static DataRdr wtr_(DataWtr wtr) {return raw_root_(wtr.To_str());}
	@Override public SrlMgr SrlMgr_new(Object o) {return new GfmlDataRdr();}
	@gplx.Internal protected GfmlDataRdr() {
		this.Parse_set(true);
	}
}
class GfmlDataRdrNde extends GfmlDataRdr_base {
	public GfmlDataRdrNde(GfmlNde current) {SetNode(current);}
	@Override public SrlMgr SrlMgr_new(Object o) {return new GfmlDataRdrNde(null);}
}
