package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
import gplx.core.threads.*; import gplx.xowa.guis.cbks.js.*;
public class Xog_redlink_thread implements Gfo_thread_wkr {
	private final    int[] redlink_ary; private final    Xog_js_wkr js_wkr;
	public Xog_redlink_thread(int[] redlink_ary, Xog_js_wkr js_wkr) {this.redlink_ary = redlink_ary; this.js_wkr = js_wkr;}
	public String	Thread__name() {return "xowa.gui.html.redlinks.set";}
	public boolean	Thread__resume() {return true;}
	public void Thread__exec() {
		int len = redlink_ary.length;
		for (int i = 0; i < len; ++i) {
			js_wkr.Html_redlink(gplx.xowa.wikis.pages.lnkis.Xopg_lnki_list.Lnki_id_prefix + Int_.To_str(redlink_ary[i]));
		}
	}
}
