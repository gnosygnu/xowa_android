package gplx.xowa.wikis.pages.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_db_data {
	public Xopg_db_page			Page()			{return page;}			private final    Xopg_db_page page = new Xopg_db_page();
	public Xopg_db_text			Text()			{return text;}			private final    Xopg_db_text text = new Xopg_db_text();
	public Xopg_db_html			Html()			{return html;}			private final    Xopg_db_html html = new Xopg_db_html();
	public Xopg_db_protection	Protection()	{return protection;}	private final    Xopg_db_protection protection = new Xopg_db_protection();
	public void Clear() {
		page.Clear();
		html.Clear();
		// text.Clear();
		// protection.Clear();
	}
}
