package gplx.xowa.addons.searchs.searchers.rslts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch_rslt_cbk_ {
        public static final    Srch_rslt_cbk Noop = new Srch_rslt_cbk__noop();
}
class Srch_rslt_cbk__noop implements Srch_rslt_cbk {
	public void On_rslts_found(Srch_search_qry qry, Srch_rslt_list rslts_list, int rslts_bgn, int rslts_end) {}
	public void On_cancel() {}
}
