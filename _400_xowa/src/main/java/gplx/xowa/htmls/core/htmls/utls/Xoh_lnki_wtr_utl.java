package gplx.xowa.htmls.core.htmls.utls; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.htmls.*;
import gplx.langs.htmls.*; import gplx.xowa.htmls.hrefs.*; import gplx.xowa.htmls.core.htmls.*;
public class Xoh_lnki_wtr_utl {
	private final Xoa_app app; private final Xow_wiki wiki; private final Xoh_href_wtr href_wtr; private final Bry_bfr tmp_bfr = Bry_bfr.new_(255);
	public Xoh_lnki_wtr_utl(Xow_wiki wiki, Xoh_href_wtr href_wtr) {
		this.app = wiki.App();
		this.wiki = wiki; this.href_wtr = href_wtr;
	}
	public byte[] Bld_href(byte[] page) {return Bld_href(wiki.Domain_bry(), wiki.Ttl_parse(page));}
	public byte[] Bld_href(byte[] domain_bry, Xoa_ttl ttl) {
		href_wtr.Build_to_bfr(tmp_bfr, app, Xoh_wtr_ctx.Mode_popup, domain_bry, ttl);
		return tmp_bfr.To_bry_and_clear();
	}
	public byte[] Bld_title(byte[] text) {
		return gplx.langs.htmls.Gfh_utl.Escape_html_as_bry(tmp_bfr, text, Bool_.N, Bool_.N, Bool_.N, Bool_.Y, Bool_.Y);
	}
}
