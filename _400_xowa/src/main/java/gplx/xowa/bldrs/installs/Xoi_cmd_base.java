package gplx.xowa.bldrs.installs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.threads.*; import gplx.xowa.bldrs.*;
import gplx.xowa.wikis.domains.*;
import gplx.xowa.bldrs.wms.dumps.*;
abstract class Xoi_cmd_base implements Gfo_thread_cmd {
	public void Ctor(Xoi_setup_mgr install_mgr, String wiki_key) {
		this.install_mgr = install_mgr; this.wiki_key = wiki_key;
		this.Owner_(install_mgr);
	}	private Xoi_setup_mgr install_mgr; String wiki_key;
	@gplx.Virtual public void Cmd_ctor() {}
	public abstract String Async_key();
	public int Async_sleep_interval()	{return Gfo_thread_cmd_.Async_sleep_interval_1_second;}
	public boolean Async_prog_enabled()	{return false;}
	public void Async_prog_run(int async_sleep_sum) {}
	public byte Async_init() {return Gfo_thread_cmd_.Init_ok;}
	public boolean Async_term() {
//			install_mgr.App().Gui_wtr().Log_many(GRP_KEY, "import.end", "import.end ~{0} ~{1} ~{2}", wiki_key, wiki_date, dump_type);
		return true;
	}
	public GfoInvkAble Owner() {return owner;} public Xoi_cmd_base Owner_(GfoInvkAble v) {owner = v; return this;} GfoInvkAble owner;
	public Gfo_thread_cmd Async_next_cmd() {return next_cmd;} public void Async_next_cmd_(Gfo_thread_cmd v) {next_cmd = v;} Gfo_thread_cmd next_cmd;
	public void Async_run() {
		running = true;
//			install_mgr.App().Gui_wtr().Log_many(GRP_KEY, "import.bgn", "import.bgn ~{0} ~{1} ~{2}", wiki_key, wiki_date, dump_type);
		Thread_adp_.invk_(this.Async_key(), this, Invk_process_async).Start();			
	}
	public boolean Async_running() {return running;} private boolean running;
	public void Process_async() {
		Xoae_app app = install_mgr.App();
		Xob_bldr bldr = app.Bldr();
		Xowe_wiki wiki = app.Wiki_mgr().Get_by_or_make(Bry_.new_a7(wiki_key));
		wiki.Init_assert();
		bldr.Cmd_mgr().Clear();
		Process_async_init(app, wiki, bldr);
		bldr.Pause_at_end_(false);
		try {bldr.Run();}
		catch (Exception e) {
			running = false;
			install_mgr.Cmd_mgr().Working_(Bool_.N);
			throw Err_.new_exc(e, "xo", "error during import");
		}
		app.Usr_dlg().Prog_none("", "clear", "");
		app.Usr_dlg().Note_none("", "clear", "");
		Process_async_done(app, wiki, bldr);
		running = false;
	}
	public abstract void Process_async_init(Xoae_app app, Xowe_wiki wiki, Xob_bldr bldr);
	public abstract void Process_async_done(Xoae_app app, Xowe_wiki wiki, Xob_bldr bldr);
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_process_async))			Process_async();
		else if	(ctx.Match(k, Invk_owner))					return owner;
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_process_async = "run_async", Invk_owner = "owner";
}
class Xoi_cmd_category2_page_props extends Xoi_cmd_wiki_download {//#*inherit
	public Xoi_cmd_category2_page_props(Xoi_setup_mgr install_mgr, String wiki_key, String dump_date) {this.Ctor_download_(install_mgr, wiki_key, dump_date, Xowm_dump_type_.Str__page_props);}
	@Override public String Download_file_ext() {return ".sql.gz2";}
	public static final String KEY_category2 = "wiki.category2.download.page_props";
}
class Xoi_cmd_category2_categorylinks extends Xoi_cmd_wiki_download {//#*inherit
	public Xoi_cmd_category2_categorylinks(Xoi_setup_mgr install_mgr, String wiki_key, String dump_date) {this.Ctor_download_(install_mgr, wiki_key, dump_date, Xowm_dump_type_.Str__categorylinks);}
	@Override public String Download_file_ext() {return ".sql.gz2";}
	public static final String KEY_category2 = "wiki.category2.download.categorylinks";
}
class Xoi_cmd_category2_build extends Xoi_cmd_base {
	public Xoi_cmd_category2_build(Xoi_setup_mgr install_mgr, String wiki_key) {this.Ctor(install_mgr, wiki_key); this.app = install_mgr.App(); this.wiki_key = wiki_key;} private Xoae_app app; private String wiki_key;
	@Override public void Cmd_ctor() {
		Xowe_wiki wiki = app.Wiki_mgr().Get_by_or_make(Bry_.new_u8(wiki_key));
		wiki.Import_cfg().Category_version_(gplx.xowa.wikis.ctgs.Xoa_ctg_mgr.Version_2);
	}
	@Override public String Async_key() {return KEY;} public static final String KEY = "wiki.category2.build";
	@Override public void Process_async_init(Xoae_app app, Xowe_wiki wiki, Xob_bldr bldr) {
		if (app.Setup_mgr().Dump_mgr().Wiki_storage_type_is_sql()) {
			wiki.Db_mgr_as_sql().Category_version_update(false);				
			bldr.Cmd_mgr().Add_many(wiki, Xob_cmd_keys.Key_text_cat_core, Xob_cmd_keys.Key_text_cat_link, Xob_cmd_keys.Key_text_cat_hidden);
		}
		else
			bldr.Cmd_mgr().Add_many(wiki, Xob_cmd_keys.Key_tdb_cat_hidden_sql, Xob_cmd_keys.Key_tdb_cat_hidden_ttl, Xob_cmd_keys.Key_text_cat_link, Xob_cmd_keys.Key_tdb_ctg_link_idx);
	}
	@Override public void Process_async_done(Xoae_app app, Xowe_wiki wiki, Xob_bldr bldr) {
		app.Usr_dlg().Prog_many("", "", "category2 setup done");
	}
}
class Xoi_cmd_search2_build extends Xoi_cmd_base {
	public Xoi_cmd_search2_build(Xoi_setup_mgr install_mgr, String wiki_key) {this.Ctor(install_mgr, wiki_key);}
	@Override public String Async_key() {return KEY;} public static final String KEY = "wiki.search2.build";
	@Override public void Process_async_init(Xoae_app app, Xowe_wiki wiki, Xob_bldr bldr) {
		if (app.Setup_mgr().Dump_mgr().Wiki_storage_type_is_sql()) {
			wiki.Db_mgr_as_sql().Category_version_update(false);
			bldr.Cmd_mgr().Add_many(wiki, Xob_cmd_keys.Key_text_search_cmd);
			int page_rank_iterations = app.Api_root().Bldr().Wiki().Import().Page_rank().Iteration_max();
			boolean page_rank_enabled = page_rank_iterations > 0;
			if (page_rank_enabled) {
				bldr.Cmd_mgr().Add(new gplx.xowa.bldrs.cmds.utils.Xob_download_cmd(bldr, wiki).Dump_type_(gplx.xowa.addons.pagelinks.bldrs.Pglnk_bldr_cmd.Dump_type_key));
				bldr.Cmd_mgr().Add_many(wiki, Xob_cmd_keys.Key_wiki_page_link);
			}
			bldr.Cmd_mgr().Add(new gplx.xowa.addons.searchs.bldrs.cmds.Srch__page__page_score(bldr, wiki).Iteration_max_(page_rank_iterations));
			bldr.Cmd_mgr().Add(new gplx.xowa.addons.searchs.bldrs.cmds.Srch__link__link_score(bldr, wiki).Page_rank_enabled_(page_rank_enabled));
			bldr.Cmd_mgr().Add(new gplx.xowa.addons.searchs.bldrs.cmds.Srch__word__link_count(bldr, wiki));
			Xob_db_file db_file = Xob_db_file.New__page_link(wiki);
			db_file.Conn().Rls_conn();
			Io_mgr.Instance.DeleteFil(db_file.Url());
		}
	}
	@Override public void Process_async_done(Xoae_app app, Xowe_wiki wiki, Xob_bldr bldr) {
		app.Usr_dlg().Prog_many("", "", "search2 setup done");
		// wiki.Db_mgr().Search_version_refresh();
	}
}
