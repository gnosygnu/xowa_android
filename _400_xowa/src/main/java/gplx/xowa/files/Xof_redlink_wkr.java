package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
import gplx.core.threads.*;
import gplx.xowa.files.gui.*;
class Xof_redlink_wkr implements Gfo_thread_wkr {
	private Xog_js_wkr js_wkr; private int[] uids;
	public Xof_redlink_wkr(Xog_js_wkr js_wkr, int[] uids) {
		this.js_wkr = js_wkr; this.uids = uids;
	}
	public String Name() {return "xowa.redlinks";}
	public boolean Resume() {return true;}
	public void Exec() {
		int len = uids.length;
		for (int i = 0; i < len; ++i) {
			int uid = uids[i];
			js_wkr.Html_atr_set(Int_.To_str(uid), "", "");
		}
	}
}
