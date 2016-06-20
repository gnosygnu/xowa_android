package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
import gplx.core.threads.*;
import gplx.xowa.files.gui.*;
class Xof_redlink_wkr implements Gfo_thread_wkr {
	private Xog_js_wkr js_wkr; private int[] uids;
	public Xof_redlink_wkr(Xog_js_wkr js_wkr, int[] uids) {
		this.js_wkr = js_wkr; this.uids = uids;
	}
	public String			Thread__name() {return "xowa.redlinks";}
	public boolean			Thread__resume() {return true;}
	public void Thread__exec() {
		int len = uids.length;
		for (int i = 0; i < len; ++i) {
			int uid = uids[i];
			js_wkr.Html_atr_set(Int_.To_str(uid), "", "");
		}
	}
}
