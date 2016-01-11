package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
import gplx.core.threads.*; import gplx.xowa.files.gui.*;
public class Xog_redlink_thread implements Gfo_thread_wkr {
	private final int[] redlink_ary; private final Xog_js_wkr js_wkr;
	public Xog_redlink_thread(int[] redlink_ary, Xog_js_wkr js_wkr) {this.redlink_ary = redlink_ary; this.js_wkr = js_wkr;}
	public String Name() {return "xowa.gui.html.redlinks.set";}
	public boolean Resume() {return true;}
	public void Exec() {
		int len = redlink_ary.length;
		for (int i = 0; i < len; ++i) {
			js_wkr.Html_redlink(gplx.xowa.parsers.lnkis.redlinks.Xopg_redlink_lnki_list.Lnki_id_prefix + Int_.To_str(redlink_ary[i]));
		}
	}
}
