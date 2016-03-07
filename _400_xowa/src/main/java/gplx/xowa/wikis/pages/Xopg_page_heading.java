package gplx.xowa.wikis.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.brys.*; import gplx.core.brys.fmtrs.*;
public class Xopg_page_heading implements Bfr_arg {
	private Xopg_html_data html_data;
	private Xoa_ttl page_ttl;
	public Xopg_page_heading Init(Xoae_page page) {
		this.html_data = page.Html_data();
		this.page_ttl = page.Ttl();
		return this;
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (html_data.Xtn_pgbnr() != null) return;	// pgbnr exists; don't add title
		fmtr.Bld_many(bfr, page_ttl.Page_txt());
	}
	private final Bry_fmt fmtr = Bry_fmt.New(Bry_.New_u8_nl_apos("<h1 id='firstHeading' class='firstHeading'><span>~{page_title}</span></h1>"), "page_title");
}
