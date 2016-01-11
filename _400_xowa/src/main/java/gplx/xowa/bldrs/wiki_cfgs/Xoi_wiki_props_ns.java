package gplx.xowa.bldrs.wiki_cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
public class Xoi_wiki_props_ns {
	public int Id() {return id;} private int id;
	public boolean Subpages_enabled() {return subpages_enabled;} private boolean subpages_enabled;
	public Xoi_wiki_props_ns Init_by_ctor(int id, boolean subpages_enabled) {this.id = id; this.subpages_enabled = subpages_enabled; return this;}
	public void Init_by_xml(gplx.langs.xmls.XmlNde ns_nde) {
		this.id = Int_.parse(ns_nde.Atrs().FetchValOr("id", "-1"));
		this.subpages_enabled = ns_nde.Atrs().Fetch_or_null("subpages") != null;// per api, subpages="" means ns has subpages; no subpages attribute means no subpages
	}
}
