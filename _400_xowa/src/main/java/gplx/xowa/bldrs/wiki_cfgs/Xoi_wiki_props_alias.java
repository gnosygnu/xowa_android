package gplx.xowa.bldrs.wiki_cfgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
public class Xoi_wiki_props_alias {
	public int Id() {return id;} private int id;
	public String Alias() {return alias;} private String alias;
	public Xoi_wiki_props_alias Init_by_ctor(int id, String alias) {this.id = id; this.alias = alias; return this;}
	public void Init_by_xml(gplx.langs.xmls.XmlNde ns_nde) {
		this.id = Int_.parse(ns_nde.Atrs().FetchValOr("id", "-1"));
		this.alias = String_.Replace(String_.Replace(ns_nde.Text_inner(), " ", "_"), "'", "''");
	}
}
