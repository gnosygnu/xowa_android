package gplx.xowa.addons.wikis.ctgs.htmls.catpages.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*; import gplx.xowa.addons.wikis.ctgs.htmls.*; import gplx.xowa.addons.wikis.ctgs.htmls.catpages.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.tbls.*;	
import gplx.xowa.addons.wikis.ctgs.htmls.catpages.doms.*;
public class Xoctg_page_loader implements Select_in_cbk {
	private final    Xow_wiki wiki;
	private final    Ordered_hash hash = Ordered_hash_.New();
	public Xoctg_page_loader(Xow_wiki wiki) {this.wiki = wiki;}
	public Ordered_hash Hash() {return hash;}
	public int Hash_max() {return hash.Len();}
	public void Write_sql(Bry_bfr bfr, int idx) {
		Xoctg_catpage_itm itm = (Xoctg_catpage_itm)hash.Get_at(idx);
		bfr.Add_int_variable(itm.Page_id());
	}
	public void Read_data(Db_rdr rdr) {
		// read values from page_tbl
		int page_id = rdr.Read_int("page_id");
		int page_ns = rdr.Read_int("page_namespace");
		byte[] page_ttl = rdr.Read_bry_by_str("page_title");

		// get itm and set data
		Xoctg_catpage_itm itm = (Xoctg_catpage_itm)hash.Get_by(page_id);
		if (itm == null) return; // NOTE: itms can exist in cat_links_tbl, but not in page_tbl; EX:User:Any_page
		itm.Page_ttl_(wiki.Ttl_parse(page_ns, page_ttl));
	}
}
