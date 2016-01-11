package gplx.xowa.wikis.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xoa_page__commons_mgr {
	public boolean			Xowa_mockup() {return xowa_mockup;} public void Xowa_mockup_(boolean v) {xowa_mockup = v;} private boolean xowa_mockup;
	public Xow_wiki		Source_wiki() {return source_wiki;} public void Source_wiki_(Xow_wiki v) {source_wiki = v;} private Xow_wiki source_wiki;
	public Xow_wiki		Source_wiki_or(Xow_wiki or) {return source_wiki == null ? or : source_wiki;}
	public void Clear() {
		this.xowa_mockup = false;
		this.source_wiki = null;
	}
}
