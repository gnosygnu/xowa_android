package gplx.xowa.addons.apps.file_browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*;
import gplx.core.ios.*;
import gplx.langs.mustaches.*;
class Wikis_list_mgr implements Mustache_doc_itm {
	private final    byte[] import_root;
	private final    Wikis_list_itm[] subs;
	public Wikis_list_mgr(byte[] import_root, Wikis_list_itm[] subs) {this.import_root = import_root; this.subs = subs;}
	public boolean Mustache__write(String key, Mustache_bfr bfr) {
		if		(String_.Eq(key, "import_root"))	bfr.Add_bry(import_root);
		return false;
	}
	public Mustache_doc_itm[] Mustache__subs(String key) {
		if		(String_.Eq(key, "subs"))			return subs;
		return Mustache_doc_itm_.Ary__empty;
	}
}
class Wikis_list_itm implements Mustache_doc_itm {
	private final    byte[] domain, date;
	public Wikis_list_itm(byte[] domain, byte[] date) {
		this.domain = domain; this.date = date;
	}
	public boolean Mustache__write(String key, Mustache_bfr bfr) {
		if		(String_.Eq(key, "domain"))			bfr.Add_bry(domain);
		else if	(String_.Eq(key, "date"))			bfr.Add_bry(date);
		else										return false;
		return true;
	}
	public Mustache_doc_itm[] Mustache__subs(String key) {return Mustache_doc_itm_.Ary__empty;}
}
