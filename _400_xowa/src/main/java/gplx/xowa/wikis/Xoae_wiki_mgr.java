package gplx.xowa.wikis; import gplx.*; import gplx.xowa.*;
import gplx.xowa.langs.*; import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.wikis.domains.*; import gplx.xowa.wikis.domains.crts.*; import gplx.xowa.wikis.nss.*; import gplx.xowa.wikis.metas.*;
public class Xoae_wiki_mgr implements Xoa_wiki_mgr, Gfo_invk {
	private final    Xoae_app app;
	private final    List_adp list = List_adp_.New(); private final    Hash_adp_bry hash = Hash_adp_bry.ci_a7();	// ASCII:url_domain; EX:en.wikipedia.org
	public Xoae_wiki_mgr(Xoae_app app) {
		this.app = app;
		this.wiki_regy = new Xoa_wiki_regy(app);
		this.wdata_mgr = new Wdata_wiki_mgr(app);
	}
	public Xoa_wiki_regy Wiki_regy() {return wiki_regy;} private final    Xoa_wiki_regy wiki_regy;
	public Xow_script_mgr Scripts() {return scripts;} private final    Xow_script_mgr scripts = new Xow_script_mgr();
	public Wdata_wiki_mgr Wdata_mgr() {return wdata_mgr;} private final    Wdata_wiki_mgr wdata_mgr;
	public Xowe_wiki Wiki_commons() {
		synchronized (this) {	// LOCK:app-level; DATE:2016-07-06
			Xowe_wiki rv = (Xowe_wiki)this.Get_by_or_null(Xow_domain_itm_.Bry__commons);
			if (rv != null) rv.Init_assert();
			return rv;
		}
	}
	public void Init_by_app() {wdata_mgr.Init_by_app();}
	public int			Count()							{return list.Count();}
	public boolean			Has(byte[] key)					{return hash.Has(key);}
	public Xow_wiki		Get_at(int idx)					{return (Xow_wiki)list.Get_at(idx);}
	public Xowe_wiki	Get_at_or_null(int i)			{return Int_.Between(i, 0, this.Count() - 1) ? (Xowe_wiki)list.Get_at(i) : null;}
	public Xow_wiki		Get_by_or_null(byte[] key)		{return Bry_.Len_eq_0(key) ? null : (Xowe_wiki)hash.Get_by(key);}
	public Xow_wiki		Get_by_or_make_init_y(byte[] key) {
		synchronized (this) {	// LOCK:app-level; DATE:2016-07-06
			Xowe_wiki rv = (Xowe_wiki)this.Get_by_or_null(key); if (rv == null) rv = Make_and_add(key);
			rv.Init_assert();
			return rv;
		}
	}
	public Xow_wiki		Get_by_or_make_init_n(byte[] key) {return Get_by_or_make(key);}
	public Xowe_wiki	Get_by_or_make(byte[] key) {
		Xowe_wiki rv = (Xowe_wiki)this.Get_by_or_null(key); if (rv == null) rv = Make_and_add(key);
		return rv;
	}
	public void Add(Xow_wiki wiki) {
		if (hash.Get_by_bry(wiki.Domain_bry()) != null) return;	// if already there, don't add again; basically, Add_if_dupe_use_1st
		hash.Add(wiki.Domain_bry(), wiki);
		list.Add(wiki);
	}
	public Xowe_wiki Make_and_add(byte[] domain_bry) {
		Io_url wiki_root_url = app.Fsys_mgr().Wiki_dir().GenSubDir(String_.new_a7(domain_bry));
		Xowe_wiki rv = (Xowe_wiki)Make(domain_bry, wiki_root_url);
		Add(rv);
		return rv;
	}
	public Xow_wiki Make(byte[] domain_bry, Io_url wiki_root_dir) {
		Xow_domain_itm domain_itm = Xow_domain_itm_.parse(domain_bry);
		byte[] lang_key = domain_itm.Lang_actl_key();
		if (lang_key == Xol_lang_stub_.Key__unknown) lang_key = Xol_lang_itm_.Key_en;	// unknown langs default to english; note that this makes nonwmf english by default
		Xol_lang_itm lang = app.Lang_mgr().Get_by_or_new(lang_key);			
		if (domain_itm.Domain_type_id() == Xow_domain_tid_.Int__other) {
			lang = new Xol_lang_itm(app.Lang_mgr(), Xol_lang_itm_.Key_en).Kwd_mgr__strx_(true);	// create a new english lang, but enable strx functions; DATE:2015-08-23
			Xol_lang_itm_.Lang_init(lang);
		}
		Xow_ns_mgr ns_mgr = Xow_ns_mgr_.default_(lang.Case_mgr()); //app.Dbmeta_mgr().Ns__get_or_load(key);
		return new Xowe_wiki(app, lang, ns_mgr, domain_itm, wiki_root_dir);
	}
	public Xow_wiki		Import_by_url(Io_url url) {return Xoa_wiki_mgr_.Import_by_url(app, this, url);}
	public void Del(byte[] key) {hash.Del(key);}
	public void Clear() {hash.Clear(); list.Clear();}
	public void Free_mem(boolean clear_ctx) {
		int list_len = list.Count();
		for (int i = 0; i < list_len; i++) {
			Xowe_wiki wiki = (Xowe_wiki)list.Get_at(i);
//				wiki.Defn_cache().ReduceCache();
			if (clear_ctx) wiki.Parser_mgr().Ctx().Clear_all();	// NOTE: clear_ctx will reset toc and refs
			wiki.Cache_mgr().Page_cache().Free_mem_all();
			wiki.Cache_mgr().Tmpl_result_cache().Clear();
		}
	}
	public void Rls() {
		int len = list.Count();
		for (int i = 0; i < len; i++) {
			Xowe_wiki wiki = (Xowe_wiki)list.Get_at(i);
			wiki.Rls();
		}
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_get))							return Get_by_or_make(m.ReadBry("v"));
		else if	(ctx.Match(k, Invk_scripts))						return scripts;
		else if	(ctx.Match(k, Invk_wdata))							return wdata_mgr;
		else if	(ctx.Match(k, Invk_len))							return this.Count();
		else if	(ctx.Match(k, Invk_get_at))							return this.Get_at_or_null(m.ReadInt("v"));
		else if	(ctx.Match(k, Xoa_wiki_mgr_.Invk__import_by_url))	return this.Import_by_url(m.ReadIoUrl("v"));
		else	return Gfo_invk_.Rv_unhandled;
	}	private static final String Invk_get = "get", Invk_scripts = "scripts", Invk_wdata = "wdata";
	private static final    String Invk_len = "len", Invk_get_at = "get_at";
}
