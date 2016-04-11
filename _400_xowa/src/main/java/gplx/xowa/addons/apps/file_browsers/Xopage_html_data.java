package gplx.xowa.addons.apps.file_browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*;
import gplx.xowa.wikis.pages.*;
public class Xopage_html_data {
	public Xopage_html_data(byte[] display_ttl, byte[] body) {
		this.display_ttl = display_ttl;
		this.body = body;
	}
	public byte[] Display_ttl()		{return display_ttl;} private byte[] display_ttl;
	public byte[] Body()			{return body;} private final    byte[] body;
	public Xopg_tag_mgr Head_tags() {return head_tags;} private final    Xopg_tag_mgr head_tags = new Xopg_tag_mgr();
	public Xopg_tag_mgr Tail_tags() {return tail_tags;} private final    Xopg_tag_mgr tail_tags = new Xopg_tag_mgr();

	public void Apply(Xoa_page page) {
		page.Html_data().Html_restricted_n_();
		page.Html_data().Skip_parse_(Bool_.Y);
		Bry_bfr bfr = Bry_bfr.new_();
		page.Html_data().Custom_head_end_concat(head_tags.To_html(bfr));
		page.Html_data().Custom_html_end_concat(tail_tags.To_html(bfr));
		page.Html_data().Display_ttl_(display_ttl);
		page.Html_data().Custom_body_(this.Body());
		page.Html_data().Custom_head_tags().Copy(head_tags);
		page.Html_data().Custom_tail_tags().Copy(head_tags);
	}

	public static Xopage_html_data err_(String msg) {return new Xopage_html_data(Bry_.Empty, Bry_.new_u8(msg));}
}
