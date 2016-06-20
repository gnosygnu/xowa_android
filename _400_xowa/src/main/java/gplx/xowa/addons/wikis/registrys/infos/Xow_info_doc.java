package gplx.xowa.addons.wikis.registrys.infos; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.registrys.*;
import gplx.langs.mustaches.*;
class Xow_info_doc implements Mustache_doc_itm {
	private final    Mustache_doc_itm[] ary;
	public Xow_info_doc(Xow_info_doc_wiki itm) {this.ary = new Mustache_doc_itm[] {itm};}
	public boolean Mustache__write(String key, Mustache_bfr bfr) {return false;}
	public Mustache_doc_itm[] Mustache__subs(String key) {
		if		(String_.Eq(key, "wiki_info"))		return ary;
		return Mustache_doc_itm_.Ary__empty;
	}
}
class Xow_info_doc_wiki implements Mustache_doc_itm {
	private final    byte[] domain;
	private final    String date, size, dir;
	public Xow_info_doc_wiki(byte[] domain, String date, String dir, String size) {
		this.domain = domain; this.date = date; this.dir = dir; this.size = size;
	}
	public boolean Mustache__write(String key, Mustache_bfr bfr) {
		if		(String_.Eq(key, "wiki_domain"))	bfr.Add_bry(domain);
		else if	(String_.Eq(key, "wiki_date"))		bfr.Add_str_u8(date);
		else if	(String_.Eq(key, "wiki_dir"))		bfr.Add_str_u8(dir);
		else if	(String_.Eq(key, "wiki_size"))		bfr.Add_str_u8(size);
		else										return false;
		return true;
	}
	public Mustache_doc_itm[] Mustache__subs(String key) {return Mustache_doc_itm_.Ary__empty;}
}
