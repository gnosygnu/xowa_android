package gplx.xowa.addons.searchs.searchers.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public interface Srch2_rslt_cbk {
	void On_rslt_found(Srch2_rslt_row rslt);
	void On_rdr_done();
}
