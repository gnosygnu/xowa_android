package gplx.xowa.bldrs.cmds.texts; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.bldrs.xmls.*; import gplx.xowa.xtns.wdatas.*; import gplx.xowa.wikis.data.tbls.*; import gplx.xowa.wikis.dbs.*;	
public abstract class Xob_term_base implements Xob_cmd, GfoInvkAble {
	public Xob_term_base Ctor(Xob_bldr bldr, Xowe_wiki wiki) {this.wiki = wiki; return this;} private Xowe_wiki wiki;
	public abstract String Cmd_key();
	public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return null;}
	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {}
	public void Cmd_run() {}
	public void Cmd_end() {
		Xoae_app app = wiki.Appe();
		app.Gui_mgr().Html_mgr().Portal_mgr().Wikis().Itms_reset();	// NOTE: dirty wiki list so that next refresh will load itm			
		app.Free_mem(false);	// clear cache, else import will load new page with old items from cache; DATE:2013-11-21
		wiki.Props().Main_page_update(wiki);
		app.Bldr().Import_marker().End(wiki);
		wiki.Init_needed_(true);// flag init_needed prior to show; dir_info will show page_txt instead of page_gz;
		wiki.Init_assert();	// force load; needed to pick up MediaWiki ns for MediaWiki:mainpage
		Cmd_end_hook();
	}
	public abstract void Cmd_end_hook();
	public void Cmd_term() {}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		return this;
	}
}
