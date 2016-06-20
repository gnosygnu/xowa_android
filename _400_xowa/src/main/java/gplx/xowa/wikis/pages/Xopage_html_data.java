package gplx.xowa.wikis.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.xowa.wikis.pages.tags.*;
public class Xopage_html_data {
	public Xopage_html_data(byte[] display_ttl, byte[] body) {
		this.display_ttl = display_ttl;
		this.body = body;
	}
	public byte[] Display_ttl()			{return display_ttl;} private byte[] display_ttl;
	public byte[] Body()				{return body;} private final    byte[] body;
	public boolean			Cbk_enabled()	{return cbk_enabled;} private boolean cbk_enabled; public void Cbk_enabled_y_() {this.cbk_enabled = true;}
	public Xopg_tag_mgr Head_tags()		{return head_tags;} private final    Xopg_tag_mgr head_tags = new Xopg_tag_mgr(Bool_.Y);
	public Xopg_tag_mgr Tail_tags()		{return tail_tags;} private final    Xopg_tag_mgr tail_tags = new Xopg_tag_mgr(Bool_.N);

	public void Apply(Xoa_page page) {
		Xopg_html_data html_data = page.Html_data();
		html_data.Html_restricted_n_();
		html_data.Skip_parse_(Bool_.Y);
		html_data.Display_ttl_(display_ttl);
		html_data.Custom_body_(this.Body());
		html_data.Custom_head_tags().Copy(head_tags);
		html_data.Custom_tail_tags().Copy(tail_tags);
		html_data.Cbk_enabled_(cbk_enabled);
	}

	public static Xopage_html_data err_(String msg) {return new Xopage_html_data(Bry_.Empty, Bry_.new_u8(msg));}
}
