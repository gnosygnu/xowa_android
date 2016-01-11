package gplx.xowa.guis; import gplx.*; import gplx.xowa.*;
import gplx.core.net.*;
import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.makes.imgs.*; import gplx.xowa.guis.history.*;
import gplx.xowa.apps.*; import gplx.xowa.wikis.*; import gplx.xowa.apps.urls.*;
import gplx.core.threads.*;
public abstract class Xogv_tab_base {
	private Gfo_url_parser url_parser;
	private Xoav_wiki_mgr wiki_mgr;
	private Gfo_thread_pool thread_pool;
	public void Ctor(Xoav_wiki_mgr wiki_mgr, Gfo_thread_pool thread_pool, Gfo_url_parser url_parser) {this.wiki_mgr = wiki_mgr; this.thread_pool = thread_pool; this.url_parser = url_parser;}
	public Xog_history_stack History_stack()		{return history_stack;} private final Xog_history_stack history_stack = new Xog_history_stack(); 
	public Xog_history_itm Cur_itm()				{return history_stack.Cur_itm();}
	public Xow_wiki Get_wiki_or_null(byte[] key)	{return wiki_mgr.Get_by_domain(key);}
	public Xoh_page Go_to(byte[] page)				{return Go_to(history_stack.Cur_itm().Wiki(), page, Bry_.Empty, Bry_.Empty, false, "");}
	public Xoh_page Go_to(byte[] wiki, byte[] page)	{return Go_to(wiki, page, Bry_.Empty, Bry_.Empty, false, "");}
	public Xoh_page Go_to(byte[] wiki, byte[] page, byte[] anch, byte[] qarg, boolean redirect_force, String bmk_pos) {
		Xog_history_itm old_itm = this.Cur_itm();
		Xog_history_itm new_itm = new Xog_history_itm(wiki, page, anch, qarg, redirect_force, bmk_pos);
		Xoh_page rv = Fetch_page_and_show(old_itm, new_itm);
		if (rv.Exists())
			history_stack.Add(new_itm);
		return rv;
	}
	public Xoh_page Go_bwd() {return Go_by_dir(Bool_.Y);}
	public Xoh_page Go_fwd() {return Go_by_dir(Bool_.N);}
	public Xoh_page Reload() {return Fetch_page_and_show(Cur_itm(), Cur_itm());}
	private Xoh_page Go_by_dir(boolean bwd) {
		Xog_history_itm old_itm = this.Cur_itm();
		Xog_history_itm new_itm = bwd ? history_stack.Go_bwd() : history_stack.Go_fwd();
		return Fetch_page_and_show(old_itm, new_itm);
	}
	private Xoh_page Fetch_page_and_show(Xog_history_itm old_itm, Xog_history_itm new_itm) {
		if (new_itm == Xog_history_itm.Null) return new Xoh_page().Exists_n_();
		Fetch_page__bgn(new_itm.Wiki(), new_itm.Page(), new_itm.Qarg());
		Xoh_page new_hpg = new Xoh_page();
		// Thread_adp_.invk_(Xogv_page_load_wkr.Thread_name, new Xogv_page_load_wkr(wiki_mgr, url_parser, this, old_itm, new_itm), Xogv_page_load_wkr.Invk_exec).Start();
		thread_pool.Add_at_end(new Xogv_page_load_wkr(wiki_mgr, url_parser, this, old_itm, new_itm));
		thread_pool.Run();
		return new_hpg;
	}
	@gplx.Virtual protected void Fetch_page__bgn(byte[] wiki_domain, byte[] page_bry, byte[] qarg_bry) {}
	public void Srl_save(Bry_bfr bfr)					{history_stack.Srl_save(bfr);}
	public void Srl_load(byte[] raw)					{history_stack.Srl_load(raw);}
	public abstract void Show_page(Xog_history_itm old_itm, Xog_history_itm new_itm, Xoh_page new_hpg);
}
