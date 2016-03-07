package gplx.xowa.addons.searchs.searchers.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch2_rslt_cbk_ {
        public static final Srch2_rslt_cbk Noop = new Srch2_rslt_cbk__noop();
}
class Srch2_rslt_cbk__noop implements Srch2_rslt_cbk {
	public void On_rslt_found(Srch2_rslt_row rslt) {}
	public void On_rdr_done() {}
}
