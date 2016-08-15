package gplx.xowa.htmls.core.htmls.tidy; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.htmls.*;
public class Xow_tidy_mgr_interface_ {
	public static final    Xow_tidy_mgr_interface Noop = new Xow_tidy_mgr_interface__noop();
}
class Xow_tidy_mgr_interface__noop implements Xow_tidy_mgr_interface {
	public void Exec_tidy(Bry_bfr bfr, boolean indent, byte[] page_url) {}
}
