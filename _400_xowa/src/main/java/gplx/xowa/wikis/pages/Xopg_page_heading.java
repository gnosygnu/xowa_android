package gplx.xowa.wikis.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.brys.*; import gplx.core.brys.fmtrs.*;
import gplx.xowa.wikis.pages.htmls.*;
public class Xopg_page_heading implements Bfr_arg {
	private Xopg_html_data html_data;
	private byte[] display_title;
	public Xopg_page_heading Init(Xopg_html_data html_data, byte[] display_title) {
		this.html_data = html_data;
		this.display_title = display_title;
		return this;
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		if (html_data.Xtn_pgbnr() != null) return;	// pgbnr exists; don't add title
		fmtr.Bld_many(bfr, display_title);
	}
	private final    Bry_fmt fmtr = Bry_fmt.New(Bry_.New_u8_nl_apos("<h1 id='firstHeading' class='firstHeading'>~{page_title}</h1>"), "page_title");	// <span>~{page_title}</span>
}
