package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.langs.jsons.*;
import gplx.xowa.wikis.domains.*;
public class Wdata_sitelink_itm implements Wdata_lang_sortable {
	public Wdata_sitelink_itm(byte[] site, byte[] name, byte[][] badges) {this.site = site; this.name = name; this.badges = badges;} 
	public byte[] Site() {return site;} private final byte[] site;
	public byte[] Name() {return name;} private final byte[] name;
	public byte[][] Badges() {return badges;} private final byte[][] badges;
	public byte[] Lang() {return lang;} public void Lang_(byte[] v) {lang = v;} private byte[] lang = Bry_.Empty;
	public byte[] Lang_code() {return lang;}
	public int Lang_sort() {return lang_sort;} public void Lang_sort_(int v) {lang_sort = v;} private int lang_sort = Wdata_lang_sorter.Sort_null;
	public Xow_domain_itm Domain_info() {if (domain_info == null) domain_info = Xow_abrv_wm_.Parse_to_domain_itm(site); return domain_info;} private Xow_domain_itm domain_info;
	public Xoa_ttl Page_ttl() {return page_ttl;} public Wdata_sitelink_itm Page_ttl_(Xoa_ttl v) {page_ttl = v; return this;} private Xoa_ttl page_ttl;	// PERF: cache title to avoid creating new Object for "In Other langs"; DATE:2014-10-20
	@Override public String toString() {// TEST:
		return String_.Concat_with_str("|", String_.new_u8(site), String_.new_u8(name), String_.Concat_with_str(",", String_.Ary(badges)));
	}
}
