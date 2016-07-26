package gplx.xowa.wikis.caches; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.core.caches.*;
import gplx.xowa.wikis.xwikis.sitelinks.*;
public class Xow_cache_mgr {
	private final    Xowe_wiki wiki;
	public Xow_cache_mgr(Xowe_wiki wiki) {
		this.wiki = wiki;
		this.page_cache = new Xow_page_cache(wiki);
		this.defn_cache = new Xow_defn_cache(wiki.Lang());
		this.lst_cache = new Xow_defn_cache(wiki.Lang());
	}
	public Hash_adp			Tmpl_result_cache() {return tmpl_result_cache;} private final    Hash_adp tmpl_result_cache = Hash_adp_bry.cs();
	public Xow_page_cache	Page_cache()		{return page_cache;}		private Xow_page_cache page_cache;
	public Xow_defn_cache	Defn_cache()		{return defn_cache;}		private final    Xow_defn_cache defn_cache;
	public Xow_defn_cache	Lst_cache()			{return lst_cache;}			private final    Xow_defn_cache lst_cache;
	public Hash_adp			Misc_cache()		{return misc_cache;}		private final    Hash_adp misc_cache = Hash_adp_.New();
	public Gfo_cache_mgr	Commons_cache()		{return commons_cache;}		private Gfo_cache_mgr commons_cache = new Gfo_cache_mgr().Max_size_(64 * Io_mgr.Len_mb).Reduce_by_(32 * Io_mgr.Len_mb);
	public Gfo_cache_mgr	Ifexist_cache()		{return ifexist_cache;}		private Gfo_cache_mgr ifexist_cache = new Gfo_cache_mgr().Max_size_(64 * Io_mgr.Len_mb).Reduce_by_(32 * Io_mgr.Len_mb);

	public Xow_cache_mgr	Page_cache_(Xow_page_cache v) {this.page_cache = v; return this;}
	public Xow_cache_mgr	Commons_cache_(Gfo_cache_mgr v) {this.commons_cache = v; return this;}
	public Xow_cache_mgr	Ifexist_cache_(Gfo_cache_mgr v) {this.ifexist_cache = v; return this;}
	public Keyval[] Scrib_lang_names() {
		if (scrib_lang_names == null) {
			List_adp list = List_adp_.New();
			Xoa_sitelink_itm_mgr itm_mgr = wiki.App().Xwiki_mgr__sitelink_mgr().Itm_mgr();
			int len = itm_mgr.Len();
			for (int i = 0; i < len; ++i) {
				Xoa_sitelink_itm itm = itm_mgr.Get_at(i);
				Keyval kv = Keyval_.new_(String_.new_u8(itm.Key()), String_.new_u8(itm.Name()));
				list.Add(kv);
			}
			scrib_lang_names = (Keyval[])list.To_ary(Keyval.class);
		}
		return scrib_lang_names;
	}
	public void Free_mem_all() {this.Free_mem_all(Bool_.Y);}
	public void Free_mem_all(boolean free_page_cache) {
		if (free_page_cache) {
			page_cache.Free_mem_all();
			commons_cache.Clear();
			ifexist_cache.Clear();
			wiki.Appe().Wiki_mgr().Wdata_mgr().Clear();	// moved from ctx.Clear(); DATE:2016-07-21
		}
		tmpl_result_cache.Clear();
		defn_cache.Free_mem_all();
		misc_cache.Clear();
		lst_cache.Free_mem_all();
		scrib_lang_names = null;
	}
	private static Keyval[] scrib_lang_names;
}
