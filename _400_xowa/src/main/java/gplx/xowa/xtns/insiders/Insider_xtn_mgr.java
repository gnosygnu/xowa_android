package gplx.xowa.xtns.insiders; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.langs.htmls.*; import gplx.xowa.wikis.*;
public class Insider_xtn_mgr extends Xox_mgr_base {
	public Insider_xtn_mgr() {
		html_bldr = new Insider_html_bldr(this);
	}
	@Override public boolean Enabled_default() {return false;}
	@Override public byte[] Xtn_key() {return XTN_KEY;} public static final    byte[] XTN_KEY = Bry_.new_a7("Insider");
	@Override public Xox_mgr Xtn_clone_new() {return new Insider_xtn_mgr();}
	@Override public void Xtn_init_by_wiki(Xowe_wiki wiki) {
		this.wiki = wiki;
		if (!Enabled()) return;
		Xox_mgr_base.Xtn_load_i18n(wiki, XTN_KEY);
		msg_sidebar_ttl = wiki.Msg_mgr().Val_by_key_obj("insider-title");
		msg_about_ttl = wiki.Msg_mgr().Val_by_key_obj("insider-about");
		msg_about_page = wiki.Msg_mgr().Val_by_key_obj("insider-about-page");
	}
	public Xowe_wiki Wiki() {return wiki;} private Xowe_wiki wiki;
	public Insider_html_bldr Html_bldr() {return html_bldr;} private Insider_html_bldr html_bldr;
	public byte[] Msg_sidebar_ttl() {return msg_sidebar_ttl;} private byte[] msg_sidebar_ttl;
	public byte[] Msg_about_ttl() {return msg_about_ttl;} private byte[] msg_about_ttl;
	public byte[] Msg_about_page() {return msg_about_page;} private byte[] msg_about_page;
}
