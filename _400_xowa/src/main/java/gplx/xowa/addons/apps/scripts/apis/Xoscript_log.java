package gplx.xowa.addons.apps.scripts.apis; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.scripts.*;
public class Xoscript_log {
	public void log(String... v) {
		Gfo_usr_dlg_.Instance.Log_many("", "", String_.Concat_with_str(" ", v));
	}
}