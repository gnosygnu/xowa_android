package gplx.xowa.addons.bldrs.mass_parses.parses; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.cases.*;
public class Xow_wiki_utl_ {
	public static Xowe_wiki Clone_wiki(Xowe_wiki wiki, Io_url wiki_dir) {
		Xol_lang_itm lang = new Xol_lang_itm(wiki.App().Lang_mgr(), wiki.Lang().Key_bry());
		Xol_lang_itm_.Lang_init(lang);
		Xowe_wiki rv = new Xowe_wiki(wiki.Appe(), lang, gplx.xowa.wikis.nss.Xow_ns_mgr_.default_(lang.Case_mgr()), wiki.Domain_itm(), wiki_dir);
		rv.Init_by_wiki();
		return rv;
	}
}
