package gplx.xowa.xtns.gallery; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.htmls.modules.*;
public class Gallery_xtn_mgr extends Xox_mgr_base {
	@Override public byte[] Xtn_key() {return XTN_KEY;} public static final    byte[] XTN_KEY = Bry_.new_a7("gallery");
	@Override public Xox_mgr Xtn_clone_new() {return new Gallery_xtn_mgr();}
	public Gallery_itm_parser Parser() {return parser;} private Gallery_itm_parser parser;
	public Gallery_html_wtr Html_wtr() {return html_wtr;} private Gallery_html_wtr html_wtr;
	@Override public void Xtn_init_by_wiki(Xowe_wiki wiki) {
		parser = new Gallery_itm_parser();
		parser.Init_by_wiki(wiki);
		html_wtr = new Gallery_html_wtr();
	}
}
